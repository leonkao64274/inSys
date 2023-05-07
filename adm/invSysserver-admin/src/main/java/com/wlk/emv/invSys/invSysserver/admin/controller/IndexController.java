/*
 * @(#)IndexController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理 "系統首頁" 控制器類別
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.01,  /10/19, LeonKao
 *   1) logout,變更密碼
 * v1.02,  /11/27, LeonKao
 * 	 1) 增加第一次登入需修改密碼功能。
 * 	 2) 增加預設系統管理者第一次登入需修改帳號及密碼功能。
 * v1.03,   /01/15, LeonKao
 * 	 1) 新增系統健康管理功能
 * v1.04,   /03/09, LeonKao
 * 	 1) 增加登入後踢前功能
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.HttpClientUtility;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroupPrivilege;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;
import com.invSet.emv.invSys.invSysserver.core.bean.LoginSessionKey;
import com.invSet.emv.invSys.invSysserver.core.bean.SystemUrl;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.AdminMenuService;
import com.invSet.emv.invSys.invSysserver.core.service.AdminUserService;
import com.invSet.emv.invSys.invSysserver.core.service.LoginSessionKeyService;
import com.invSet.emv.invSys.invSysserver.core.service.SystemUrlService;
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 "系統首頁" 控制器類別
 * 
 * @author   LeonKao
 */
@SessionAttributes({ "adminUser", "locale", "loginSessionKey" })
@Controller
public class IndexController {

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	private AdminMenuService adminMenuService;

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private SystemUrlService systemUrlService;

	@Autowired
	private LoginSessionKeyService loginSessionKeyService;

	// 網頁公用標籤。
	private static final String TAG_REQUIRE_NAME = "req";
	private static final String TAG_REQUIRE_VALUE = "&nbsp;<span style='color: red;'>*</span>";

	/**
	 * Session Scope 的用戶登錄訊息。
	 */
	@Autowired
	private IndexLoginSession indexLoginSession;

	/**
	 * Session 變數名，儲存 /template/main_page.jsp 用的左側選單(access_id)集合。
	 */
	private static final String GRANTED_ACCESS_ID_LIST = "grantedAccessIdList";

	/**
	 * 系統根路徑，用戶訪問時導頁至系統預設首頁
	 * 
	 * @return 系統預設首頁
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:/index/login";
	}

	/**
	 * 系統根路徑，用戶訪問時導頁至系統預設首頁
	 * 
	 * @return 系統預設首頁
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String redirect2() {
		return "redirect:/index/login";
	}

	/**
	 * 系統首頁
	 * 
	 * @param form 後台管理 "系統登入" 業表單類別, 提供 Spring 綁定表單提交資料
	 * @return 系統首頁
	 */
	@RequestMapping(value = "/index/login", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("form", new IndexLoginForm());

		return "/index/login";
	}

	/**
	 * 使用者登入
	 * 
	 * @param form    後台管理 "系統登入" 業表單類別, 提供 Spring 綁定表單提交資料
	 * @param result  用戶提交表單資料驗證結果
	 * @param model   UI Model, 用於存放返回網頁顯示的資料
	 * @param session 瀏覽器級, 屬性儲存實體
	 * @return 表單資料驗證有誤或登入未通過則返回系統首頁，登入成功則進入系統主頁
	 */
	@DoOperationLog(accessId = "login", targetObject = "T_ADMIN_USER", operation = "L")
	@RequestMapping(value = "/index/login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("form") IndexLoginForm form, BindingResult result, Model model,
			HttpSession session) {

		// 驗證(1): 欄位基本格式。
		if (result.hasErrors()) {
			return "/index/login";
		}

		// 驗證(2): 帳號驗證。
		AdminUser adminUser = adminUserService.findByAccount(form.getAccount());
		if (adminUser == null) {
			logger.debug("User not regitered!");
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.login.user-not-registered", null, LocaleContextHolder.getLocale()));
			return "/index/login";
		}

		// 驗證(3): 密碼驗證。
		// 企業規則：sha1(亂數8碼 + account + sha1(password))
		String right = form.getRandom8digits() + adminUser.getPassword();
		String encrypt = DigestUtils.sha1Hex(right);
		logger.info("login - random8digits = " + form.getRandom8digits());
		logger.info("login - account = " + form.getAccount());
		logger.info("login - password = " + form.getPassword());
		logger.info("login - USER = " + form.getEncryptPassword() + ", length = " + form.getEncryptPassword().length());
		logger.info("login - REAL = " + encrypt + ", length = " + encrypt.length());
		if (!encrypt.equals(form.getEncryptPassword())) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.login.incorrect-password", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Password !");
			return "/index/login";
		}

		// 成功後程序(1): 更新登入時間，並儲存 Session 級元件值(登錄訊息)。
		adminUser.setLastLoginDttm(DateUtil.getCurrentDateTime());
		try {
			adminUserService.update(adminUser);
		} catch (Exception ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.login.time-error", null, LocaleContextHolder.getLocale()));
			logger.error(ex.toString());
			return "/index/login";
		}
		indexLoginSession.setAdminUser(adminUser);

		// 成功後程序(2): 準備 Session 級屬性。為 /template/main_page.jsp 用的左側選單，準備
		// access_id 集合變數。
		// 目錄清單：必須由 adminUser 的 userType 值 (0=管理員, 1=操作員)來分用戶身份。
		List<String> grantedAccessIdList = new ArrayList<String>();
		if (adminUser.getUserType().equals("0") == true) {

			// 由 "功能目錄" 直接取清單。
			List<AdminMenu> menus = adminMenuService.getSysAdminMenuList();
			for (AdminMenu menu : menus) {
				grantedAccessIdList.add(menu.getAccessId());
			}
		} else {

			// 由 "群組" 的權限定義獲取清單。
			Set<AdminGroupPrivilege> privileges = adminUser.getAdminGroup().getAdminGroupPrivileges();
			for (AdminGroupPrivilege privilege : privileges) {
				grantedAccessIdList.add(privilege.getAccessId());
			}
		}
		indexLoginSession.setGrantedAccessIdList(grantedAccessIdList);
		session.setAttribute(GRANTED_ACCESS_ID_LIST, grantedAccessIdList);

		// 成功後程序(3): 準備 Model 級屬性。為 /index/main.jsp 準備。
		model.addAttribute("adminUser", adminUser);
		if (adminUser.getAdminGroup() != null) {
			logger.info("adminUser - adminGroup.groupName = " + adminUser.getAdminGroup().getGroupName());
		}
		logger.info("adminUser - account = " + adminUser.getAccount());
		logger.info("adminUser - userName = " + adminUser.getUserName());
		logger.info("adminUser - lastLoginDttm = " + adminUser.getLastLoginDttm());

		// 成功後程序(4): 網頁公用標籤值。
		session.setAttribute(TAG_REQUIRE_NAME, TAG_REQUIRE_VALUE);

		// 成功後程序(5): 語言。
		Locale locale = Locale.TRADITIONAL_CHINESE;

		if (locale != null) {
			((SessionLocaleResolver) this.localeResolver).setDefaultLocale(locale);
			model.addAttribute("locale", locale);
			this.indexLoginSession.setLocale(locale);
		}

		// 驗證是否為預設管理員第一次登入系統，如果是跳轉到修改帳號及密碼頁面
		if (adminUser.getOid() == 1 && adminUser.getUserType().equals(AdminUserService.USER_TYPE_ADM)
				&& adminUser.getLastPswdDttm() == null) {
			IndexSysadmFirstLoginForm indexSysadmFirstLoginForm = new IndexSysadmFirstLoginForm();
			indexSysadmFirstLoginForm.setAccount(adminUser.getAccount());
			model.addAttribute("form", indexSysadmFirstLoginForm);

			return "/index/sysadm_first_login";
		}
		logger.debug("getLastPswdDttm=["+adminUser.getLastPswdDttm()+"]");
		// 驗證是否為第一次登入，如果是跳轉到修改密碼頁面。
		if (adminUser.getLastPswdDttm() == null) {
			IndexFirstLoginForm indexFirstLoginForm = new IndexFirstLoginForm();
			indexFirstLoginForm.setAccount(adminUser.getAccount());

			model.addAttribute("form", indexFirstLoginForm);
			return "/index/first_login";

		}

		// 寫入loginSessionKey用於登入判斷後踢前使用
		LoginSessionKey loginSessionKey = new LoginSessionKey();
		loginSessionKey.setUserOid(adminUser.getOid() + "");
		loginSessionKey.setSessionKey(UUID.randomUUID().toString().replace("-", ""));

		LoginSessionKey loginSessionKey2 = loginSessionKeyService.findByCriteria(loginSessionKey);
		
		try {
			if (loginSessionKey2 != null) {
				loginSessionKey.setOid(loginSessionKey2.getOid());
				loginSessionKeyService.update(loginSessionKey);
			} else {
				loginSessionKeyService.save(loginSessionKey);
			}
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ResourceAlreadyExistException e) {
			e.printStackTrace();
		}
		indexLoginSession.setLoginSessionKey(loginSessionKey);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 成功：跳轉到主頁。
		return "redirect:/index/health";
	}

	/**
	 * 系統健康管理
	 * 
	 * @param form   後台管理 "系統健康管理" 業表單類別, 提供 Spring 綁定表單提交資料
	 * @param result 用戶提交表單資料驗證結果
	 * @param model  UI Model, 用於存放返回網頁顯示的資料
	 * @return 表單資料驗證有誤或登入未通過則返回系統首頁，登入成功則進入系統主頁
	 */
	
	
	@AcessRightCheck(accessId = "")
	@GetMapping(value = "/index/health")
	public String checkSystemHealth(@Valid @ModelAttribute("form") SystemHealthForm form, BindingResult result,
			Model model) {

		List<SystemHealthForm> healthList = new ArrayList<>();
		// 取得所有資料
		List<SystemUrl> systemUrl = systemUrlService.findAll();

		for (int i = 0; i < systemUrl.size(); i++) {

			String url = systemUrl.get(i).getSystemUrl();
			logger.info("url : " + systemUrl.get(i).getSystemUrl());
			logger.info("systemUrl.size() : " + systemUrl.size());

			// check Online Bank System health
			// 設定預設狀態值(連線失敗)
			SystemHealthForm SystemHealth = new SystemHealthForm();
			SystemHealth.setSystemName(systemUrl.get(i).getSystemName());
			SystemHealth.setSystemOid(systemUrl.get(i).getOid());
			SystemHealth.setStatus("DOWN");
			SystemHealth.setTotalMemory(new Double(0));
			SystemHealth.setFreeMemory(new Double(0));
			SystemHealth.setTotalDiskSpace(new Double(0));
			SystemHealth.setFreeDiskSpace(new Double(0));

			ClientHttpRequestFactory requestFactory = HttpClientUtility.getClientHttpRequestFactory();
			RestTemplate restTemplate = new RestTemplate(requestFactory);

			HttpEntity<String> reqEntity = new HttpEntity<>("");
			ResponseEntity<String> responseEntity = null;

			try {
				// 透過url連線server取得返回的json訊息
				responseEntity = restTemplate.exchange(url + "/health", HttpMethod.GET, reqEntity, String.class);
				logger.debug("[response]:" + responseEntity.getBody());

				// json轉換
				HashMap<String, Object> props = parseJsonToHashMap(responseEntity.getBody());

				// 將取回的資料放入form表單
				SystemHealth.setStatus((String) props.get("status"));
				HashMap<String, Object> diskSpace = (HashMap<String, Object>) props.get("diskSpace");
				SystemHealth.setTotalDiskSpace(Double.valueOf(diskSpace.get("total").toString()));
				SystemHealth.setFreeDiskSpace(Double.valueOf(diskSpace.get("free").toString()));

				// 透過url連線server取得返回的json訊息
				responseEntity = restTemplate.exchange(url + "/metrics", HttpMethod.GET, reqEntity, String.class);
				logger.debug("[response]:" + responseEntity.getBody());

				// json轉換
				props = parseJsonToHashMap(responseEntity.getBody());

				// 將取回的資料放入form表單
				SystemHealth.setTotalMemory(Double.valueOf(props.get("mem").toString()));
				SystemHealth.setFreeMemory(Double.valueOf(props.get("mem.free").toString()));

			} catch (NullPointerException ex) {
				logger.error("NullPointerException", ex);
			} catch (RestClientException ex) {
				logger.error("Failed to invoke network api!", ex);
			} catch (IOException ex) {
				logger.error("Failed to parse response JSON!", ex);
			} catch (Exception ex) {
				logger.error("Exception", ex);
			}

			// 將單筆資料放入集合中
			healthList.add(SystemHealth);

		}
		// 將集合放入form表單供呈現
		form.setSystemHealth(healthList);

		// put health check result into model and return
		model.addAttribute("form", form);

		return "/index/main";
	}

	/**
	 * 系統健康管理-新增
	 * 
	 * @param form   後台管理 "系統健康管理" 業表單類別, 提供 Spring 綁定表單提交資料
	 * @param result 用戶提交表單資料驗證結果
	 * @param model  UI Model, 用於存放返回網頁顯示的資料
	 * @return 表單資料驗證有誤或則返回系統首頁
	 */
	
	
	@AcessRightCheck(accessId = "")
	@PostMapping(value = "/index/health-add")
	@DoOperationLog(accessId = "health", targetObject = "T_SYSTEM_URL", operation = "A")
	public String systemHealthaAdd(@Valid @ModelAttribute("form") SystemHealthForm form, BindingResult result,
			Model model) {

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.error("health-add json after error");
		}
		// 檢核form表單中欄位是否有填值
		if (form.getAddSystemName() == null) {
			result.addError(new FieldError("form", "addSystemName",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.error("warning : addSystemName is null");
		}

		if (form.getAddSystemUrl() == null) {
			result.addError(new FieldError("form", "addSystemUrl",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.error("warning : addSystemUrl is null");
		}

		// 3. 檢核表單提交資料
		if (result.hasErrors()) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.system-health.system-add-failed",
					null, LocaleContextHolder.getLocale()));
			// return "/index/main";
			return checkSystemHealth(form, result, model);
		}

		// 檢核是否有相同系統名稱資料
		SystemUrl systemUrl = systemUrlService.findBySystemName(form.getAddSystemName());
		if (systemUrl != null) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
					"warn.system-health.system-name-already-exists", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
			// return checkSystemHealth(form, result, model);
		}

		// 如無重複資料 建立資料物件 放入資料
		systemUrl = new SystemUrl();
		systemUrl.setSystemName(form.getAddSystemName());
		systemUrl.setSystemUrl(form.getAddSystemUrl());

		try {
			systemUrlService.save(systemUrl);

			model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource
					.getMessage("warn.system-health.system-add-success", null, LocaleContextHolder.getLocale()));

		} catch (ResourceAlreadyExistException ex) {
			logger.warn("Failed to add SystemUrl !", ex);
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
			// return checkSystemHealth(form, result, model);
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return recallQuery(model);
		// return checkSystemHealth(new SystemHealthForm(), result, model);
	}

	/**
	 * 系統健康管理-刪除
	 * 
	 * @param form   後台管理 "系統健康管理" 業表單類別, 提供 Spring 綁定表單提交資料
	 * @param result 用戶提交表單資料驗證結果
	 * @param model  UI Model, 用於存放返回網頁顯示的資料
	 * @return 表單資料驗證有誤或則返回系統首頁
	 */
	@AcessRightCheck(accessId = "")
	@PostMapping(value = "/index/health-delete")
	@DoOperationLog(accessId = "health", targetObject = "T_SYSTEM_URL", operation = "D")
	public String systemHealthaDelete(@Valid @ModelAttribute("form") SystemHealthForm form, BindingResult result,
			Model model) {

		if (StringUtils.isEmpty(form.getSystemOid())) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		
		// 搜尋欲刪除的該筆資料是否存在
		SystemUrl systemUrl = systemUrlService.findById(form.getSystemOid());

		if (systemUrl == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));

			return recallQuery(model);
			// return checkSystemHealth(form, result, model);
		}

		// 進行刪除
		systemUrlService.delete(form.getSystemOid());

		// 4.資料刪除成功匯入訊息寫入
		model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource.getMessage("warn.system-health.delete-successful",
				null, LocaleContextHolder.getLocale()));
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.error("health-delete write before log error");
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return recallQuery(model);
	}

	/**
	 * json轉hashMap
	 * 
	 * @param jsonMessage json格式訊息
	 * @return hashMap 物件
	 * 
	 */
	private HashMap<String, Object> parseJsonToHashMap(String jsonMessage) throws IOException {
		// src is a File, InputStream, String or such
		HashMap<String, Object> props = JsonUtil.readValue(jsonMessage, new TypeReference<HashMap<String, Object>>() {
		});
		return props;
	}
	
	/**
	 * 用戶登出。
	 * 
	 * @param session 瀏覽器級變數
	 * @return 跳轉用戶登錄網址
	 */
	@RequestMapping(path = "/index/logout")
	public String logout(HttpSession session) {

		// 1. 清除 @Scope(session) 裡面的設定值。
		AdminUser adminUser = indexLoginSession.getAdminUser();
		if (adminUser != null) {
			logger.info("logout - " + adminUser.getAccount());
			indexLoginSession.setAdminUser(null);
		}

		// 2. 清除 HttpSession 值。
		session.invalidate();

		// 完成：跳轉到登入頁。
		return "redirect:/index/login";
	}

	/**
	 * 變更密碼：訪問
	 * 
	 * @param model 模組級變數
	 * @return 變更密碼的網址
	 */
	@RequestMapping(path = "/index/password", method = RequestMethod.GET)
	public String changePassword(Model model) {

		model.addAttribute("form", new IndexLoginForm());
		return "/index/password";
	}

	/**
	 * 變更密碼：提交
	 * 
	 * @param form   綁定表單
	 * @param result 自動驗證
	 * @param model  模組級變數
	 * @return 變更密碼的網址
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@DoOperationLog(accessId = "password", targetObject = "T_ADMIN_USER", operation = "C")
	@RequestMapping(path = "/index/password", method = RequestMethod.POST)
	public String submitPassword(@Valid @ModelAttribute("form") IndexLoginForm form, BindingResult result, Model model)
			throws HttpRequestMethodNotSupportedException {

		// 驗證(1): 欄位基本格式。
		if (result.hasErrors()) {
			return "/index/password";
		}

		// 驗證(2): 帳號驗證。
		AdminUser adminUser = indexLoginSession.getAdminUser();
		if (adminUser == null) {
			logger.warn("not login");
			throw new HttpRequestMethodNotSupportedException(null);
		}
		logger.info("form.account = " + form.getAccount());
		logger.info("adminUser.account = " + adminUser.getAccount());
		if (adminUser.getAccount().equals(form.getAccount()) == false) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.change-password.validation", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Account !");
			return "/index/password";
		}

		// 驗證(3): 密碼驗證。
		String right = form.getRandom8digits() + adminUser.getPassword();
		String encryptRight = DigestUtils.sha1Hex(right);
		logger.info("encrypt.USER = " + form.getEncryptPassword());
		logger.info("encrypt.REAL = " + encryptRight);
		if (encryptRight.equals(form.getEncryptPassword()) == false) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.change-password.validation", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Password !");
			return "/index/password";
		}

		// 驗證成功：執行-更新密碼。
		adminUser.setPassword(form.getEncryptNewPassword());
		logger.info("password = " + adminUser.getPassword());
		try {
			adminUserService.update(adminUser);
			model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource.getMessage("warn.change-password.success-update",
					null, LocaleContextHolder.getLocale()));
			logger.info("Change password is successful. account = " + adminUser.getAccount());
		} catch (Exception ex) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.change-password.error-update", null,
					LocaleContextHolder.getLocale()));
			logger.error(ex.toString());
		}

		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		model.addAttribute(WebKeyConst.SUCCESS_MSG,
				messageSource.getMessage("warn.change-password.success-update", null, LocaleContextHolder.getLocale()));
		return "/index/password";

	}

	/**
	 * 第一次登入變更密碼：提交
	 * 
	 * @param form   綁定表單
	 * @param result 自動驗證
	 * @param model  模組級變數
	 * @return 登入入口的網址
	 */
	@DoOperationLog(accessId = "password", targetObject = "T_ADMIN_USER", operation = "C")
	@RequestMapping(path = "/index/first_login", method = RequestMethod.POST)
	public String submitEditFirstPassword(@Valid @ModelAttribute("form") IndexFirstLoginForm form, BindingResult result,
			Model model) {

		// 驗證(1): 欄位基本格式。
		if (result.hasErrors()) {
			return "/index/first_login";
		}

		// 驗證(2): 帳號驗證。
		AdminUser adminUser = indexLoginSession.getAdminUser();
		logger.info("form.account = " + form.getAccount());
		logger.info("adminUser.account = " + adminUser.getAccount());
		if (adminUser.getAccount().equals(form.getAccount()) == false) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.change-password.validation", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Account !");
			return "/index/first_login";
		}

		// 驗證(3): 密碼驗證。
		String right = form.getRandom8digits() + adminUser.getPassword();
		String encryptRight = DigestUtils.sha1Hex(right);
		logger.info("encrypt.USER = " + form.getEncryptPassword());
		logger.info("encrypt.REAL = " + encryptRight);
		if (encryptRight.equals(form.getEncryptPassword()) == false) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.change-password.validation", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Password !");
			return "/index/first_login";
		}

		// 驗證成功：執行-更新密碼。
		adminUser.setPassword(form.getEncryptNewPassword());
		adminUser.setLastPswdDttm(DateUtil.getCurrentDateTime());
		logger.info("password = " + adminUser.getPassword());
		try {
			adminUserService.update(adminUser);
			model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource
					.getMessage("warn.change-password.success-login-again", null, LocaleContextHolder.getLocale()));
			logger.info("Change password is successful. account = " + adminUser.getAccount());
		} catch (Exception ex) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.change-password.error-update", null,
					LocaleContextHolder.getLocale()));
			logger.error(ex.toString());
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		model.addAttribute("form", new IndexLoginForm());
		return "/index/login";
	}

	/**
	 * 系統管理員第一次登入變更帳號密碼：提交
	 * 
	 * @param form   綁定表單
	 * @param result 自動驗證
	 * @param model  模組級變數
	 * @return 登入入口的網址
	 */
	@DoOperationLog(accessId = "password", targetObject = "T_ADMIN_USER", operation = "C")
	@RequestMapping(path = "/index/sysadm_first_login", method = RequestMethod.POST)
	public String sysadmFirstLogin(@Valid @ModelAttribute("form") IndexSysadmFirstLoginForm form, BindingResult result,
			Model model) {

		// 驗證(1): 欄位基本格式。
		if (result.hasErrors()) {
			return "/index/sysadm_first_login";
		}

		// 驗證(2): 密碼驗證。
		AdminUser adminUser = indexLoginSession.getAdminUser();

		String right = form.getRandom8digits() + adminUser.getPassword();
		String encryptRight = DigestUtils.sha1Hex(right);
		logger.info("encrypt.USER = " + form.getEncryptPassword());
		logger.info("encrypt.REAL = " + encryptRight);
		if (encryptRight.equals(form.getEncryptPassword()) == false) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.change-password.validation", null, LocaleContextHolder.getLocale()));
			logger.warn("Incorrect Password !");
			return "/index/sysadm_first_login";
		}

		// 驗證成功：執行-更新帳號密碼。
		adminUser.setAccount(form.getNewAccount());
		adminUser.setPassword(form.getEncryptNewPassword());
		adminUser.setLastPswdDttm(DateUtil.getCurrentDateTime());
		logger.info("password = " + adminUser.getPassword());
		try {
			adminUserService.update(adminUser);
			model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource.getMessage(
					"warn.change-password.sysadm-success-login-again", null, LocaleContextHolder.getLocale()));
			logger.info("Change password is successful. account = " + adminUser.getAccount());
		} catch (Exception ex) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.change-password.error-update", null,
					LocaleContextHolder.getLocale()));
			logger.error(ex.toString());
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		model.addAttribute("form", new IndexLoginForm());
		return "/index/login";
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {

		SystemHealthForm form = new SystemHealthForm();

		List<SystemHealthForm> healthList = new ArrayList<>();
		// 取得所有資料
		List<SystemUrl> systemUrl = systemUrlService.findAll();

		for (int i = 0; i < systemUrl.size(); i++) {

			String url = systemUrl.get(i).getSystemUrl();
			logger.info("url : " + systemUrl.get(i).getSystemUrl());
			logger.info("systemUrl.size() : " + systemUrl.size());

			// check Online Bank System health
			// 設定預設狀態值(連線失敗)
			SystemHealthForm SystemHealth = new SystemHealthForm();
			SystemHealth.setSystemName(systemUrl.get(i).getSystemName());
			SystemHealth.setSystemOid(systemUrl.get(i).getOid());
			SystemHealth.setStatus("DOWN");
			SystemHealth.setTotalMemory(new Double(0));
			SystemHealth.setFreeMemory(new Double(0));
			SystemHealth.setTotalDiskSpace(new Double(0));
			SystemHealth.setFreeDiskSpace(new Double(0));

			ClientHttpRequestFactory requestFactory = HttpClientUtility.getClientHttpRequestFactory();
			RestTemplate restTemplate = new RestTemplate(requestFactory);

			HttpEntity<String> reqEntity = new HttpEntity<>("");
			ResponseEntity<String> responseEntity = null;

			try {
				// 透過url連線server取得返回的json訊息
				responseEntity = restTemplate.exchange(url + "/health", HttpMethod.GET, reqEntity, String.class);
				logger.debug("[response]:" + responseEntity.getBody());

				// json轉換
				HashMap<String, Object> props = parseJsonToHashMap(responseEntity.getBody());

				// 將取回的資料放入form表單
				SystemHealth.setStatus((String) props.get("status"));
				HashMap<String, Object> diskSpace = (HashMap<String, Object>) props.get("diskSpace");
				SystemHealth.setTotalDiskSpace(Double.valueOf(diskSpace.get("total").toString()));
				SystemHealth.setFreeDiskSpace(Double.valueOf(diskSpace.get("free").toString()));

				// 透過url連線server取得返回的json訊息
				responseEntity = restTemplate.exchange(url + "/metrics", HttpMethod.GET, reqEntity, String.class);
				logger.debug("[response]:" + responseEntity.getBody());

				// json轉換
				props = parseJsonToHashMap(responseEntity.getBody());

				// 將取回的資料放入form表單
				SystemHealth.setTotalMemory(Double.valueOf(props.get("mem").toString()));
				SystemHealth.setFreeMemory(Double.valueOf(props.get("mem.free").toString()));

			} catch (NullPointerException ex) {
				logger.error("NullPointerException", ex);
			} catch (RestClientException ex) {
				logger.error("Failed to invoke network api!", ex);
			} catch (IOException ex) {
				logger.error("Failed to parse response JSON!", ex);
			} catch (Exception ex) {
				logger.error("Exception", ex);
			}

			// 將單筆資料放入集合中
			healthList.add(SystemHealth);

		}
		// 將集合放入form表單供呈現
		form.setSystemHealth(healthList);

		// put health check result into model and return
		model.addAttribute("form", form);

		return "/index/main";
	}
}
