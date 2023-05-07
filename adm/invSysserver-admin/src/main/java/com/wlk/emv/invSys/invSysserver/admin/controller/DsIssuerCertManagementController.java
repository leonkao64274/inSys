/*
 * @(#)DsIssuerCertManagementController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組 "憑證請求檔業務" 控制器類別
 *
 * Modify History:
 * v1.00,   /03/06, LeonKao
 *   1) First release
 * v1.01,    /03/06, Leon Kao
 *   1) 修正檔案下載中文檔名無法顯示問題
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.CertRequest;
import com.invSet.emv.invSys.invSysserver.core.bean.invSysServerCert;
import com.invSet.emv.invSys.invSysserver.core.exception.InvalidStatusException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.CertManagementService;
import com.invSet.emv.invSys.invSysserver.core.service.CertRequestService;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.service.invSysServerCertService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
import java.net.URLEncoder;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組 "DS 客戶端證書載入 設定管理作業" 控制器類別
 *
 * @author Steven
 */
@SessionAttributes("IssuerCertManagementCriteriaForm")
@Controller
public class DsIssuerCertManagementController {

	private static final Logger logger = LoggerFactory.getLogger(DsIssuerCertController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	/**
	 * 卡組織管理服務
	 */
	@Autowired
	private SysCodeService sysCodeService;

	/**
	 * 憑證請求檔業務服務
	 */
	@Autowired
	private CertRequestService certRequestService;

	/**
	 * invSys-Server憑證資訊
	 */
	@Autowired
	private invSysServerCertService invSysServerCert;

	/**
	 * 憑證管理業務服務
	 */
	@Autowired
	private CertManagementService certManagementService;

	private void prepareConfigModel(Model model) {
		// (2): 卡組織
		model.addAttribute("cardSchemeConfigModel",
				sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));
	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 *
	 * @return "查詢條件表單" Model
	 */
	@ModelAttribute("IssuerCertManagementCriteriaForm")
	public IssuerCertCriteriaForm initialModel() {
		return new IssuerCertCriteriaForm();
	}

	/**
	 * 顯示DS 客戶端證書載入作業表單
	 *
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 新增作業表單頁面
	 */
	@RequestMapping(value = "/ds_certificate_management/upload", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cert_management")
	public String showUpload(Model model) {
		// 建立表單物件，供編輯畫面表單綁定使用
		this.prepareConfigModel(model);

		model.addAttribute("form", new invSysServerCertForm());
		return "/ds_certificate_management/upload";
	}

	/**
	 * 提交上傳作業表單資料
	 *
	 * @param form
	 *            發卡單位憑證資訊 Entity 類別, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料, 如查詢結果、錯誤訊息等
	 * @param file
	 *            DS 客戶端證書檔
	 * @param certEncode
	 *            證書格式另外帶入
	 * @return 如表單資料驗證有誤或新增作業失敗，則返回 "新增作業表單頁面"，新增成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@RequestMapping(value = "/ds_certificate_management/upload", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "ds_cert_management")
	@DoOperationLog(accessId = "ds_cert_management", operation = "A", targetObject = "T_invSysSERVER_CERT")
	public String addup(@Valid @ModelAttribute("form") invSysServerCertForm form, BindingResult result,
			@RequestParam("file") MultipartFile file, Model model) {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("ds_cert_management add json after error");
		}
		// 建立表單物件，供編輯畫面表單綁定使用
		this.prepareConfigModel(model);
		// 此憑證類別為 "DS-客戶端憑證"
		form.setCertType(CertManagementService.CERT_TYPE_DS_CLIENT);

		// 綁定證書格式select選單
		model.addAttribute("certEncode", form.getCertEncode());

		// 1. 檢核表單提交資料
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
			return "/ds_certificate_management/upload";
		}

		// 判斷卡組織是否與憑證互相符合
		CertRequest cr = certRequestService.findBykeyAlias(form.getKeyAlias());
		if (!(null == cr)) {
			if (!(cr.getCardScheme().equals(form.getCardScheme()))) {
				model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
						"warn.issuer-cert.certificate-corresponds-error", null, LocaleContextHolder.getLocale()));
				return "/ds_certificate_management/upload";
			}
		}
		// 判斷是否為空
		if (file.isEmpty()) {
			// 與簽名證書請求檔資料不符，請確認後重新操作!
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.issuer-cert.select-certificate-party",
					null, LocaleContextHolder.getLocale()));
			return "/ds_certificate_management/upload";

		}

		try {
			invSysServerCert.importCert(form.getCardScheme(), form.getCertType(), form.getKeyAlias(), file.getBytes(),
					form.getCertEncode());
			// 資料成功匯入訊息寫入
			model.addAttribute(WebKeyConst.SUCCESS_MSG,
					messageSource.getMessage("warn.field-not-assigned", null, LocaleContextHolder.getLocale()));
		} catch (KeyStoreException e) {
			// 儲存憑證資訊發生異常時拋出
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.issuer-cert.exception-save-credential-information", null, LocaleContextHolder.getLocale()));
			logger.warn("There was an exception to save the credential information", e);
			return "/ds_certificate_management/upload";
		} catch (CertificateException e) {
			// 匯入的憑證檔案內容有異常時拋出
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.issuer-cert.contents-imported-document-file-abnormal", null,
							LocaleContextHolder.getLocale()));
			logger.warn("The contents of the imported document file are abnormal", e);
			return "/ds_certificate_management/upload";
		} catch (ResourceAlreadyExistException e) {

			// 發卡單位憑證資訊已存在時拋出
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.issuer-cert.credential-information-already-exists", null, LocaleContextHolder.getLocale()));
			logger.warn("Card issuer credential information already exists", e);
			return "/ds_certificate_management/upload";

		} catch (IOException e) {
			// IO錯誤時拋出
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.issuer-cert.io-exception", null, LocaleContextHolder.getLocale()));
			logger.warn("IOException", e);
			return "/ds_certificate_management/upload";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return "/ds_certificate_management/upload";
	}

	/**
	 * 跳轉DS 客戶端證書開啟作業表單
	 *
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 查詢作業表單頁面
	 */
	@RequestMapping(value = "/ds_certificate_management/activate", method = { RequestMethod.POST, RequestMethod.GET })
	@AcessRightCheck(accessId = "ds_cert_management")
	@DoOperationLog(accessId = "ds_cert_management", operation = "Q", targetObject = "T_invSysSERVER_CERT")
	public String showEnabledForm(@ModelAttribute("IssuerCertManagementCriteriaForm") IssuerCertCriteriaForm form,
			BindingResult result, Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {
		
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("ds_cert_management activate json QUERY error");
		}
		
		// 建立表單物件，供編輯畫面表單綁定使用
		this.prepareConfigModel(model);

		// 檢核表單提交資料
		if (result.hasErrors()) {
			return "/ds_certificate_management/query";
		}
		logger.debug("[pageNumber]:" + pageNumber);

		// 依據查詢條件查詢證書啟用資料資料
		Page<invSysServerCert> queryResult = invSysServerCert.queryByForm(
				// form.getCriteriaIssuerOid(),
				form.getCriteriaCardScheme(), form.getStatus(), form.getNotBefore(),
				CertManagementService.CERT_TYPE_DS_CLIENT, new PageRequest(pageNumber, appProperties.getPageSize()));

		// 返回查詢結果條件
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);

		model.addAttribute("form", new invSysServerCert());
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return "/ds_certificate_management/activate";
	}

	/**
	 * DS 客戶端證書開啟作業表單
	 *
	 * @param oid
	 *            物件識別碼，供系統取得該筆資料的完整內容於開啟DS 客戶端證書所用
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 明細作業表單頁面
	 */
	@RequestMapping(value = "/ds_certificate_management/enable", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cert_management")
	@DoOperationLog(accessId = "ds_cert_management", operation = "E", targetObject = "T_invSysSERVER_CERT")
	public String enableCredentialsForm(@RequestParam Long oid, Model model) {
		this.prepareConfigModel(model);
		
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		try {
			// 操作記錄 - 異動前/後資料
			try {
				LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(invSysServerCert.findById(oid)));
			} catch (JsonProcessingException e) {
				logger.warn("invSysServerCert write before log error");
			}
			
			// 帶入oid 開啟當下憑證且關閉其他憑證
			invSysServerCert tsc=invSysServerCert.enableCert(oid);
			// 操作記錄 - 異動前/後資料
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(tsc));
		} catch (CertificateException e) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.issuer-cert.error-certificate", null,
					LocaleContextHolder.getLocale()));
			return "/ds_certificate_management/activate";
		} catch (ResourceNotFoundException e) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.issuer-cert.check-card-issuer-information", null, LocaleContextHolder.getLocale()));
			e.printStackTrace();
			return "/ds_certificate_management/activate";
		} catch (InvalidStatusException e) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.issuer-cert.certificate-status-wrong",
					null, LocaleContextHolder.getLocale()));
			e.printStackTrace();
			return "/ds_certificate_management/activate";
		} catch (JsonProcessingException e) {
			logger.warn("invSysServerCert write after log error");
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 返回 "編輯" 網頁的地址。
		return recallQueryActivate(model);
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		IssuerCertCriteriaForm form = (IssuerCertCriteriaForm) model.asMap().get("IssuerCertManagementCriteriaForm");

		Page<invSysServerCert> queryResult = invSysServerCert.queryByForm(form.getCriteriaCardScheme(),
				form.getStatus(), form.getNotBefore(), CertManagementService.CERT_TYPE_DS_CLIENT,
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));

		// // 返回查詢結果條件
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);

		return "/ds_certificate_management/activate";
	}

	@AcessRightCheck(accessId = "ds_cert_management")
	@DoOperationLog(accessId = "ds_cert_management", operation = "O", targetObject = "T_invSysSERVER_CERT")
	@RequestMapping(value = "/ds_certificate_management/export", method = { RequestMethod.POST, RequestMethod.GET })
	public String export(HttpServletRequest request, HttpServletResponse response, Long oid, Model model,
			String recipient, String checkRecipient) throws ServletException, IOException {
		logger.info("[recipient]:" + recipient);

		if (oid == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		if (recipient == null) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.ds-issuer-cert.password-not-null",
					null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
//		if (checkRecipient == null) {
//			model.addAttribute(WebKeyConst.ERRORS, messageSource
//					.getMessage("warn.ds-issuer-cert.check-password-not-null", null, LocaleContextHolder.getLocale()));
//			return recallQuery(model);
//		}
//		if (!checkRecipient.equals(recipient)) {
//			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
//					"warn.ds-issuer-cert.password-check-password-not-same", null, LocaleContextHolder.getLocale()));
//			return recallQuery(model);
//		}

		invSysServerCert issuerCert = invSysServerCert.findById(oid);
		//  /03/22 Steven Kao 修改
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(issuerCert));
		} catch (JsonProcessingException e) {
			logger.warn("invSysServerCert write before log error");
		}
		
		try {
			KeyStore ks = certManagementService.exportCertificate(issuerCert.getKeyAlias(), "PKCS12",
					recipient.toCharArray());

			OutputStream os = response.getOutputStream();
			response.setContentType("APPLICATION/OCTET-STREAM");
			// response.setContentLength(csrbyte.length);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(issuerCert.getKeyAlias()) + ".p12\"");

			ks.store(response.getOutputStream(), recipient.toCharArray());
			os.flush();
		} catch (KeyStoreException | CertificateException | NoSuchAlgorithmException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.issuer-cert.export-failed", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return null;
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQueryActivate(Model model) {
		recallQuery(model);
		return "/ds_certificate_management/activate";
	}

	/**
	 * 顯示明細作業表單
	 *
	 * @param oid
	 *            物件識別碼，供系統取得該筆資料的完整內容於編輯表單畫面顯示
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 明細作業表單頁面
	 */
	@RequestMapping(value = "/ds_certificate_management/detail", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cert_management")
	public String showDetailForm(@RequestParam Long oid, Model model) {
		
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 取回數據實體
		invSysServerCert ic = invSysServerCert.findById(oid);
		// 2. 驗證是否有取到資料
		if (null == ic) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource
					.getMessage("warn.data-read-exception-please-re-confirm", null, LocaleContextHolder.getLocale()));

			return recallQuery(model);
		}
		CertRequest cr = certRequestService.findBykeyAlias(ic.getKeyAlias());

		// 3. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", ic);
		model.addAttribute("formcr", cr);

		// 4. 返回 "編輯" 網頁的地址。
		return "/ds_certificate_management/detail";
	}

}
