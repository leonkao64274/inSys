/*
 * @(#)KekController.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理系統 "加密金鑰設定" 控制器類別
 *
 * Modify History:
 * v1.00,   /02/08, Steven Kao
 * 1) First release
 *   
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.KmsInfo;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.KmsInfoService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - ACS 後台管理系統 "加密金鑰設定" 控制器類別
 * 
 * @author   LeonKao
 */
@SessionAttributes("kekCriteriaForm")
@Controller
public class KekController {

	/**
	 * Logging utility
	 */
	private static final Logger logger = LoggerFactory.getLogger(KekController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	/**
	 * 金鑰管理資訊服務
	 */
	@Autowired
	private KmsInfoService kmsInfoService;

	/**
	 * 準備 "模型的組態" 信息
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 */
	private void prepareConfigModel(Model model) {
		// do nothing
	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 * 
	 * @return 查詢條件表單 Model 物件
	 */
	@ModelAttribute("kekCriteriaForm")
	public KekCriteriaForm initialModel() {
		return new KekCriteriaForm();
	}

	/**
	 * 查詢作業
	 * 
	 * @param form
	 *            查詢條件表單提交資料綁定物件, 此為 session scope 變數，命名時勿與其他功能使用的名稱衝突
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * 
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@RequestMapping(value = "/kek/query")
	@AcessRightCheck(accessId = "kek")
	@DoOperationLog(accessId = "kek", operation = "Q", targetObject = "T_KMS_INFO")
	public String query(@Valid @ModelAttribute("kekCriteriaForm") KekCriteriaForm form, BindingResult result,
			Model model) {
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser add json query error");
		}
		
		
		
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/kek/query";
		}
		logger.debug("[pageNumber]:" + form.getPageNumber());

		// 3. 依據查詢條件進行查詢
		Page<KmsInfo> queryResult = kmsInfoService.findByCriterial(KmsInfoService.KEY_TYPE_KEK,
				form.getCriteriaKeyAlias(), form.getCriteriaStatus(),
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/kek/query";
	}

	/**
	 * 顯示新增作業表單
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * 
	 * @return 新增作業表單頁面
	 */
	@AcessRightCheck(accessId = "kek")
	@RequestMapping(value = "/kek/add", method = RequestMethod.GET)
	public String showAddForm(Model model) {

		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", new KmsInfo());

		// 3. 返回新增網頁地址。
		return "/kek/add";
	}

	/**
	 * 提交新增作業表單資料
	 * 
	 * @param form
	 *            表單提交資料綁定物件, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料, 如查詢結果、錯誤訊息等
	 * 
	 * @return 如表單資料驗證有誤或新增作業失敗，則返回 "新增作業表單頁面"，新增成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@AcessRightCheck(accessId = "kek")
	@RequestMapping(value = "/kek/add", method = RequestMethod.POST)
	@DoOperationLog(accessId = "kek", operation = "A", targetObject = "T_KMS_INFO")
	public String add(@Valid @ModelAttribute("form") KmsInfo form, BindingResult result, Model model) {
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("KmsInfo add json after error");
		}
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 3. 檢核表單提交資料
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/kek/add";
		}

		// 4. 保存 "ISSUER KEK" 的數據實體。
		try {
			form.setKeyType(KmsInfoService.KEY_TYPE_KEK);
			form.setStatus(KmsInfoService.STATUS_DISABLED);
			kmsInfoService.save(form);
		} catch (ResourceAlreadyExistException ex) {
			logger.warn("Failed to add KEK !", ex);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/kek/add";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 執行成功：返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 啟用金鑰
	 * 
	 * @param oid
	 *            金鑰管理資訊物件識別碼
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * 
	 * @return 返回頁面路徑
	 */
	@AcessRightCheck(accessId = "kek")
	@RequestMapping(value = "/kek/enable", method = RequestMethod.GET)
	@DoOperationLog(accessId = "kek", operation = "E", targetObject = "T_KMS_INFO")
	public String enable(@RequestParam Long oid, Model model) {

		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);
		if (oid == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		try {
			// 操作記錄 - 異動前/後資料
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,kmsInfoService.findById(oid));
			//  /03/22 Steven Kao 修改
			try {
				LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(kmsInfoService.findById(oid)));
			} catch (JsonProcessingException e) {
				logger.warn("KmsInfo write before log error");
			}
			
			
			// 啟用金鑰
			KmsInfo ki=kmsInfoService.enableKey(oid);
			// 操作記錄 - 異動前/後資料
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,ki);
			try {
				LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(ki));
			} catch (JsonProcessingException e) {
				logger.warn("KmsInfo add json after error");
			}
			model.addAttribute(WebKeyConst.SUCCESS_MSG,
					messageSource.getMessage("warn.kms-info.enable-success", null, LocaleContextHolder.getLocale()));
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.kms-info.enable-failed", null, LocaleContextHolder.getLocale()));
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 執行成功：返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * 
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 從 session attribute 取得查詢表單物件
		KekCriteriaForm form = (KekCriteriaForm) model.asMap().get("kekCriteriaForm");

		// 3. 依據查詢條件進行查詢
		Page<KmsInfo> queryResult = kmsInfoService.findByCriterial(KmsInfoService.KEY_TYPE_KEK,
				form.getCriteriaKeyAlias(), form.getCriteriaStatus(),
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));

		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/kek/query";
	}

}
