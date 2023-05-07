package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
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
import org.springframework.web.HttpRequestMethodNotSupportedException;
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
import com.invSet.emv.invSys.invSysserver.core.bean.CertRequest;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.service.CertManagementService;
import com.invSet.emv.invSys.invSysserver.core.service.CertRequestService;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
import java.net.URLEncoder;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組 "DC 客戶端證書請求製作" 控制器類別
 *
 * @author Steven Kao
 */
@SessionAttributes("certificateRequestCriteriaForm")
@Controller
public class DsClientCertificateRequestController {

	private static final Logger logger = LoggerFactory.getLogger(DsClientCertificateRequestController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	@Autowired
	private SysCodeService sysCodeService;

	@Autowired
	private CertManagementService certManagementService;

	@Autowired
	private CertRequestService certRequestService;

	/**
	 * 自定義方法 取出下拉選單所需的資料 並往前傳入jsp
	 */
	private void prepareConfigModel(Model model) {

		// (1): 卡組織
		model.addAttribute("cardSchemeConfigModel",
				sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));

	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 *
	 * @return CertificateRequestCriteriaForm
	 */
	@ModelAttribute("certificateRequestCriteriaForm")
	public CertificateRequestCriteriaForm initialModel() {
		return new CertificateRequestCriteriaForm();
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
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@RequestMapping(value = "/ds_csr/query")
	@AcessRightCheck(accessId = "sign_csr")
	@DoOperationLog(accessId = "sign_csr", operation = "Q", targetObject = "T_CERT_REQUEST")
	public String query(@Valid @ModelAttribute("certificateRequestCriteriaForm") CertificateRequestCriteriaForm form,
			BindingResult result, Model model) {

		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("sign_csr query json query error");
		}
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/ds_csr/query";
		}
		logger.debug("[pageNumber]:" + form.getPageNumber());

		// 3. 依據查詢條件查詢 "發卡銀行", "卡組織" 資料
		CertRequest certificateRequest = new CertRequest();
		// 查詢條件(1): 卡組織
		certificateRequest.setCardScheme(form.getCriteriaCardScheme());
		// 查詢條件(2): 網域名稱(CN)
		certificateRequest.setCommonName(form.getCriteriaCommonName());
		// 查詢條件(3)：憑證申請憑證類別(S:簽名證書)
		certificateRequest.setCertType(CertManagementService.CERT_TYPE_DS_CLIENT);

		// 執行查詢作業
		Page<CertRequest> queryResult = certRequestService.findByCriteria(certificateRequest,
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// // 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/ds_csr/query";
	}

	/**
	 * 顯示新增作業表單
	 *
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 新增作業表單頁面
	 */
	@RequestMapping(value = "/ds_csr/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "sign_csr")
	public String showAddForm(Model model) {

		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 建立表單物件設定預設選取值，供編輯畫面表單綁定使用
		CertRequest certificateRequest = new CertRequest();

		// 3.添加綁定名稱
		model.addAttribute("form", certificateRequest);

		// 4. 返回新增網頁地址。
		return "/ds_csr/add";
	}

	/**
	 * 提交新增作業表單資料
	 *
	 * @param form
	 *            CertificateRequest 管理的 Entity 類別, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料, 如查詢結果、錯誤訊息等
	 * @return 如表單資料驗證有誤或新增作業失敗，則返回 "新增作業表單頁面"，新增成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@RequestMapping(value = "/ds_csr/add", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "sign_csr")
	@DoOperationLog(accessId = "sign_csr", operation = "A", targetObject = "T_CERT_REQUEST")
	public String add(@Valid @ModelAttribute("form") CertRequest form, BindingResult result, Model model) {

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("sign_csr add json after error");
		}
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			logger.warn("form error: \n" + result.getAllErrors());
			return "/ds_csr/add";
		}

		// 5. 預設憑證申請憑證類別
		form.setCertType(CertManagementService.CERT_TYPE_DS_CLIENT);

		// 6. 保存 "CertificateRequest 管理" 的數據實體。
		try {
			certRequestService.save(form);
		} catch (ResourceAlreadyExistException ex) {
			logger.warn("Failed to add CertificateRequest !", ex);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.ds-csr.keyAlias.exist", null, LocaleContextHolder.getLocale()));
			return "/ds_csr/add";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 7. 執行成功：返回查詢條件頁面並重新查詢資料
		return recallQuery(model);

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
	@RequestMapping(value = "/ds_csr/detail", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "sign_csr")
	public String showDetailForm(@RequestParam Long oid, Model model) {
		
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 1. 取回數據實體。
		CertRequest certificateRequest = certRequestService.findById(oid);

		// 2. 驗證是否有取到資料
		if (null == certificateRequest) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource
					.getMessage("warn.data-read-exception-please-re-confirm", null, LocaleContextHolder.getLocale()));

			return recallQuery(model);
		}

		// 3. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", certificateRequest);

		// 4. 返回 "編輯" 網頁的地址。
		return "/ds_csr/detail";
	}

	/**
	 * 刪除作業
	 *
	 * @param oid
	 *            物件識別碼，指定欲進行刪除的資料
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@RequestMapping(value = "/ds_csr/delete", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "sign_csr")
	@DoOperationLog(accessId = "sign_csr", operation = "D", targetObject = "T_CERT_REQUEST")
	public String delete(@RequestParam Long oid, Model model) throws HttpRequestMethodNotSupportedException {

		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		CertRequest cr = certRequestService.findById(oid);
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(cr));
		} catch (JsonProcessingException e) {
			logger.warn("CertRequest write before log error");
		}

		if (cr == null || cr.getEnrollStatus().equals("1")) {
			logger.warn("oid:" + oid + " EnrollStatus is 1 throw 405");
			throw new HttpRequestMethodNotSupportedException(null);
		}
		// 2. 刪除數據實體。
		certRequestService.delete(oid);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 3. 返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 返回查詢頁面再次查詢
	 *
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 從 session attribute 取得查詢表單物件
		CertificateRequestCriteriaForm form = (CertificateRequestCriteriaForm) model.asMap()
				.get("certificateRequestCriteriaForm");

		// 3. 依據查詢條件查詢 "發卡銀行", "卡組織", "網域名稱(CN)" 資料
		CertRequest certificateRequest = new CertRequest();

		// 查詢條件(2): 卡組織
		certificateRequest.setCardScheme(form.getCriteriaCardScheme());
		// 查詢條件(3): 網域名稱(CN)
		certificateRequest.setCommonName(form.getCriteriaCommonName());
		// 固定搜尋 DC 客戶端請求
		certificateRequest.setCertType(CertManagementService.CERT_TYPE_DS_CLIENT);

		// 執行查詢作業
		Page<CertRequest> queryResult = certRequestService.findByCriteria(certificateRequest,
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));

		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/ds_csr/query";
	}

	/**
	 * 下載作業
	 *
	 * 按下下載鈕後進入 執行下載 csr檔案
	 *
	 * @param oid
	 *            用於取得該筆資料
	 * @param moder
	 *            用於儲存錯誤訊息
	 */
	@RequestMapping(value = "/ds_csr/doGet")
	@AcessRightCheck(accessId = "sign_csr")
	@DoOperationLog(accessId = "sign_csr", operation = "O", targetObject = "T_CERT_REQUEST")
	protected String doGet(HttpServletRequest request, HttpServletResponse response, Long oid, Model model)
			throws ServletException, IOException {

		// 1.建立CertificateRequest物件 利用oid取得資料
		CertRequest certificateRequest = new CertRequest();
		certificateRequest = certRequestService.findById(oid);
		// 操作記錄 - 異動前/後資料
		//   /03/22 Steven Kao 修改
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,
					JsonUtil.writeValueAsBytes(certificateRequest));
		} catch (JsonProcessingException e) {
			logger.warn("CertRequest write before log error");
		}

		if (certificateRequest == null || certificateRequest.getEnrollStatus().equals("1")) {
			logger.warn("oid:" + oid + " EnrollStatus is 1 throw 405");
			throw new HttpRequestMethodNotSupportedException(null);
		}

		try {
			// 2.從實體物件取出資料 叫用genCsr方法產生csr檔案內容(字串)
			String csr = certManagementService.genCsr(certificateRequest.getKeyAlias(),
					certificateRequest.getSignAlgorithm(), certificateRequest.getKeyLength(),
					certificateRequest.getCommonName(), certificateRequest.getOrganization(),
					certificateRequest.getOrganizationUnit(), certificateRequest.getCountry(),
					certificateRequest.getProvince(), certificateRequest.getLocality());

			// 3.將字串轉為位元組
			byte[] csrbyte = csr.getBytes();

			// 4.設定輸出檔案名稱 格式
			PrintWriter out = response.getWriter();
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setContentLength(csrbyte.length);
			response.setHeader("Content-Disposition",
					"attachment; filename=\"" + URLEncoder.encode(certificateRequest.getFileName()) + "\"");

			// 5.將位元組(資料)放入inputStream讀取
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(csrbyte);

			// 6.設定變數 將數據輸出
			int i;
			while ((i = byteArrayInputStream.read()) != -1) {
				out.write(i);
			}

			// 7.關閉輸入與輸出
			byteArrayInputStream.close();
			out.close();

		} catch (KeyStoreException e) {
			logger.warn("Unable to access key save file !", e);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.certificate-request.key-save-file-failed", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		} catch (KeyManagementException e) {
			logger.warn("Unable to load key !", e);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS, messageSource
					.getMessage("warn.certificate-request.load-key-exception", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		} catch (InvalidAlgorithmParameterException e) {
			logger.warn("The algorithm is not supported !", e);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.certificate-request.specified-algorithm-not-supported", null,
							LocaleContextHolder.getLocale()));
			return recallQuery(model);
		} catch (CertificateException e) {
			logger.warn("Can not generate certificate request file !", e);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.certificate-request.make-certificate-request-file-failed", null,
							LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return null;
	}

}
