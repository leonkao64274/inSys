/*
 * @(#)CaCertController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server 後台管理系統  "CA 憑證管理作業" 控制器類別
 *
 * Modify History:
 * v1.00,  /08/02, LeonKao
 *   1) First release
 * v1.01,  /08/09, LeonKao
 * 	 1) 增加驗證 識別名稱不可為null
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.cert.CertificateException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.CaCert;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.service.CaCertService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys-Server 後台管理系統 "CA 憑證管理作業" 控制器類別
 * 
 * @author LeonKao
 */
@Controller
public class CaCertController {

	private static final Logger logger = LoggerFactory.getLogger(CaCertController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private CaCertService caCertService;

	/**
	 * 查詢作業(直接查詢全部資料)
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @param pageNumber
	 *            指定分頁查詢頁次
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@RequestMapping(value = "/ca_certificate/query")
	@AcessRightCheck(accessId = "ca_cert_query")
	@DoOperationLog(accessId = "ca_cert_query", operation = "Q", targetObject = "T_CA_CERT")
	public String query(Model model, @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {

		// 1. 建立查詢用CaCert實體物件
		CaCert caCert = new CaCert();

		// 2. 設定查詢條件(因此功能無查詢條件 預設查出所有資料 故無放入條件)

		// 3. 取得查詢結果
		Page<CaCert> queryResult = caCertService.findByCriteria(caCert,
				new PageRequest(pageNumber, appProperties.getPageSize()));

		// 4. 建立綁定物件queryResult給jsp頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 返回查詢結果頁面
		return "/ca_certificate/query";
	}

	/**
	 * 顯示CA證書載入作業表單
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return CA證書載入作業表單頁面
	 */
	@RequestMapping(value = "/ca_certificate/load", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ca_cert_load")
	public String showLoad(Model model) {

		// 1. 建立綁定物件form給jsp頁面
		model.addAttribute("form", new CaCertFrom());

		// 2. 返回頁面
		return "/ca_certificate/load";
	}

	/**
	 * 提交CA證書載入作業表單資料
	 * 
	 * @param form
	 *            CA 憑證管理作業 管理的 Entity 類別, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param file
	 *            表單上傳的CA證書檔案
	 * @param certEncode
	 *            表單選取的CA證書格式
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料, 如查詢結果、錯誤訊息等
	 * @return 如表單資料驗證有誤或新增作業失敗，則返回 "新增作業表單頁面"，新增成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@RequestMapping(value = "/ca_certificate/load", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "ca_cert_load")
	@DoOperationLog(accessId = "ca_cert_load", operation = "A", targetObject = "T_CA_CERT")
	public String load(@Valid @ModelAttribute("form") CaCertFrom form, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model) {

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e1) {
			logger.warn("CA load json after error");
		}
		// 2. 檢驗識別名稱是否為空
		if (result.hasErrors() || form.getCertEncode() == 0 || file.getSize() == 0) {
			logger.warn("form error: \n"+result.getAllErrors());
			// 1.1 檢驗證書格式是否為空
			if (form.getCertEncode() == 0) {
				model.addAttribute("certEncodeError", messageSource.getMessage(
						"warn.ca-cert-certificate.certificate-format", null, LocaleContextHolder.getLocale()));
			}
			// 1.2 驗證是否有上傳檔案
			if (file.getSize() == 0) {
				model.addAttribute("fileError", messageSource.getMessage(
						"warn.ca-cert-certificate.select-certificate-party", null, LocaleContextHolder.getLocale()));
			}
			model.addAttribute("certEncode", form.getCertEncode());
			return "/ca_certificate/load";
		}

		// 5. 執行匯入
		try {
			// (1). 執行匯入
			caCertService.importCaCert(form.getCertAlias(), file.getBytes(), form.getCertEncode());

			// (2). 資料成功匯入訊息寫入
			model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource.getMessage(
					"warn.ca-cert-certificate.certificate-loaded-success", null, LocaleContextHolder.getLocale()));

		} catch (ResourceAlreadyExistException e) {
			// 憑證序號已存在時拋出
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.ca-cert-certificate.credential-information-already-exists", null,
							LocaleContextHolder.getLocale()));
			logger.warn("CaCert with serialNumber already exists", e);
			return "/ca_certificate/load";

		} catch (KeyStoreException e) {
			// 儲存憑證資訊發生異常時拋出
			model.addAttribute(WebKeyConst.ERRORS, messageSource
					.getMessage("warn.ca-cert-certificate.key-store-exception", null, LocaleContextHolder.getLocale()));
			logger.warn("There was an exception to save the credential information", e);
			return "/ca_certificate/load";

		} catch (CertificateException e) {
			// 匯入的憑證檔案內容有異常時拋出
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.ca-cert-certificate.certificate-exception", null, LocaleContextHolder.getLocale()));
			logger.warn("The contents of the imported document file are abnormal", e);
			return "/ca_certificate/load";

		} catch (IOException e) {
			// IO錯誤時拋出
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.issuer-cert.io-exception", null, LocaleContextHolder.getLocale()));
			logger.warn("IOException", e);
			return "/ca_certificate/load";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5.返回CA證書載入頁面
		return "/ca_certificate/load";
	}

	/**
	 * 顯示CA證書明細作業表單
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @param oid
	 *            由頁面傳入用於查詢該筆資料
	 * @return CA 憑證管理作業明細表單頁面
	 */
	@RequestMapping(value = "/ca_certificate/detail", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ca_cert_load")
	public String detail(Long oid, Model model) {

		// 1. 檢核表單提交資料
		if (null == oid) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 透過oid取得該筆資料查詢結果
		CaCert caCert = caCertService.findById(oid);
		if (null == caCert) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		
		// 3. 建立綁定物件caCert傳至jsp頁面
		model.addAttribute("caCert", caCert);

		// 4. 返回查詢結果頁面
		return "/ca_certificate/detail";
	}
	private String recallQuery(Model model) {

		CaCert caCert = new CaCert();

		Page<CaCert> queryResult = caCertService.findByCriteria(caCert,
				new PageRequest(0, appProperties.getPageSize()));

		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/ca_certificate/query";
	}
}
