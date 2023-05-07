/*
 * @(#)AdminGroupController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組使用者 Controller(控制器)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.01,  /07/26, Milo Gao
 *   1) 接手開發。
 * v1.02,  /10/20, LeonKao
 *   1) 密碼加密。
 * v1.03,  /11/27, LeonKao
 *   1) 新增使用者時給予"LastPswdDttm"值
 *   2) 有修改密碼時給予"LastPswdDttm"值
 * v1.04,  /12/26, Bob Shih
 *   1) 修改與刪除剃除管理者帳號   
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;
import com.invSet.emv.invSys.invSysserver.core.service.AdminUserService;
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組使用者 Controller(控制器)。<br/>
 * Session 級屬性：<br/>
 * (1) adminUserCriteriaForm "查詢條件" Form。
 * 
 * @author   LeonKao, MiloGao
 */
@SessionAttributes({ "adminUserCriteriaForm" })
@Controller
public class AdminUserController {

	private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

	/**
	 * 用戶訊息 Resource。
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * 配置屬性 Resource。
	 */
	@Autowired
	private AppProperties appProperties;

	/**
	 * <功能群組使用者> Service(已實作)。
	 */
	@Autowired
	private AdminUserService adminUserService;

	/**
	 * <功能群組設定> Service(已實作)。
	 */
	@Autowired
	private AdminGroupService adminGroupService;

	/**
	 * 派遣服務請求前的前置程序。
	 * 
	 * @param model
	 *            資料模型
	 * @param session
	 *            瀏覽器級資料模型。
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {

		// 1. 檢查 "查詢條件" Form 是否存在，缺的話創建一個。
		AdminUserCriteriaForm adminUserCriteriaForm = (AdminUserCriteriaForm) session.getAttribute("adminUserCriteriaForm");
		if (model.containsAttribute("adminUserCriteriaForm") == false) {
			if (adminUserCriteriaForm == null) {
				adminUserCriteriaForm = new AdminUserCriteriaForm();
			}
			model.addAttribute("adminUserCriteriaForm", adminUserCriteriaForm);
		}

		// 2. 檢查 "查詢條件" adminUserForm 是否存在，缺的話創建一個。
		AdminUserForm adminUserForm = (AdminUserForm) session.getAttribute("adminUserForm");
		if (model.containsAttribute("adminUserForm") == false) {
			if (adminUserForm == null) {
				adminUserForm = new AdminUserForm();
			}
			model.addAttribute("adminUserForm", adminUserForm);
		}
	}

	/**
	 * 查詢作業(1/2) - 管理
	 * 
	 * @param form
	 *            "查詢條件" 的 Form。
	 * @param result
	 *            自動驗證的結果集。
	 * @param model
	 *            資料模型
	 * @param pageNumber
	 *            頁數序號。
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_user/query")
	@AcessRightCheck(accessId = "admin_user")
	@DoOperationLog(accessId = "admin_user", operation = "Q", targetObject = "T_ADMIN_USER")
	public String query(@Valid @ModelAttribute("adminUserCriteriaForm") AdminUserCriteriaForm form,
			BindingResult result, Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {
		
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser query json query error");
		}
		// 1. 檢查：查詢條件的輸入值是否有誤？
		if (result.hasErrors()) {
			logger.info("error : query(" + pageNumber + ", " + form.getCriteriaAdminGroupOid() + ", "
					+ form.getCriteriaAccount() + ")");
			return "/admin_user/query";
		}

		// 2. 準備：網頁級資料模型。
		// (1) 組態：<功能群組設定> 群組。它可能會改變，所以需要重新查詢。
		form.setConfigAdminGroupList(adminGroupService.findAll());
		// (2) 查詢：<功能群組使用者> 集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT, adminUserService.query(form.getCriteriaAdminGroupOid(),
				form.getCriteriaAccount(), pageNumber, appProperties.getPageSize()));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 3. 返回：查詢作業的網頁路徑。
		logger.info("success : query(" + pageNumber + ", " + form.getCriteriaAdminGroupOid() + ", "
				+ form.getCriteriaAccount() + ")");
		return "/admin_user/query";
	}

	/**
	 * 查詢作業(2/2) - 只有查詢
	 * 
	 * @param form
	 *            "查詢條件" Form。
	 * @param result
	 *            自動驗證的結果集。
	 * @param model
	 *            資料模型
	 * @param pageNumber
	 *            頁數序號。
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_user/op_query")
	@AcessRightCheck(accessId = "admin_user_query")
	@DoOperationLog(accessId = "admin_user_query", operation = "Q", targetObject = "T_ADMIN_USER")
	public String queryReadOnly(@Valid @ModelAttribute("adminUserCriteriaForm") AdminUserCriteriaForm form,
			BindingResult result, Model model,
			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {
		
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser op_query json query error");
		}
		// 1. 調用查詢程序。
		logger.info("redirect : query, begin");
		this.query(form, result, model, pageNumber);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 2. 返回網頁路徑。
		logger.info("redirect : query, end");
		return "/admin_user/op_query";
	}

	/**
	 * 新增作業(1/1) - 管理
	 * 
	 * @param form
	 *            "查詢條件" Form。需要用到 <功能群組設定> 集合。
	 * @param model
	 *            資料模型
	 * @return 新增作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_user/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "admin_user")
	public String showAddForm(@ModelAttribute("adminUserForm") AdminUserForm form, Model model) {

		// 1. 返回：新增作業的網頁路徑。
		logger.info("visit : showAddForm()");
		return "/admin_user/add";
	}

	/**
	 * 新增作業(2/2) - 管理
	 * 
	 * @param entity
	 *            即將新增用的空實體。
	 * @param result
	 *            自動驗證的結果集。
	 * @param model
	 *            資料模型
	 * @return 新增作業(失敗) 或 查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/admin_user/add", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "admin_user")
	@DoOperationLog(accessId = "admin_user", operation = "A", targetObject = "T_ADMIN_USER")
	public String add(@Valid @ModelAttribute("adminUserForm") AdminUserForm form, BindingResult result, Model model) {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser add json after error");
		}
		// 1. 檢查："群組" 非空值。
		if (form.getAdminGroup().getOid() == null) {
			result.addError(new FieldError("form", "adminGroup.oid",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.warn("warning : group.oid is null");
		}

		if (form.getCnfrPassword() == null) {
			result.addError(new FieldError("form", "cnfrPassword",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.warn("warning : cnfrPassword is null");
		}

		if (form.getPassword() == null) {
			result.addError(new FieldError("form", "password",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.warn("warning : password is null");
		}

		// 2. 檢查：自動驗證結果集(包含(1))。
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/admin_user/add";
		}

		// 3. 檢查：企業規則。
		// 規則(1): 使用者代號不能重覆。
		if (adminUserService.findByAccount(form.getAccount()) != null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.admin-user.account-repeat", null, LocaleContextHolder.getLocale()));
			logger.warn("warning : account is duplication");
			return "/admin_user/add";
		}
		// 規則(2): 密碼的部分,使用加密過後的資料。
		AdminUser entity = new AdminUser();
		BeanUtils.copyProperties(form, entity);
		entity.setPassword(form.getEncryptPassword());
		entity.setLastPswdDttm(DateUtil.getCurrentDateTime());

		// 4. 主流程：新增數據實體。
		try {
			adminUserService.save(entity);
		} catch (ResourceAlreadyExistException ex) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.admin-user.groupid-cannot-repeat",
					null, LocaleContextHolder.getLocale()));
			logger.warn(ex.toString());
			return "/admin_user/add";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 完成：返回查詢作業的網頁路徑。
		logger.info("success : add(" + form.getAccount() + ", " + form.getDepartment() + ")");
		return recallQuery(model);
	}

	/**
	 * 修改作業(2/2) - 管理
	 * 
	 * @param form
	 *            "查詢條件" Form。需要用到 <功能群組設定> 集合。
	 * @param id
	 *            <功能群組使用者> 主鍵值。
	 * @param model
	 *            資料模型。
	 * @return 查詢作業(失敗) 或 修改作業(成功)的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@RequestMapping(path = "/admin_user/edit", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "admin_user")
	public String showEditForm(@ModelAttribute("adminUserForm") AdminUserForm form, @RequestParam("oid") Long id,
			Model model) throws HttpRequestMethodNotSupportedException {

		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		
		// 1. 取回：資料實體。
		AdminUser entity = adminUserService.findById(id);
		if (entity == null) {
			logger.warn("405");
			throw new HttpRequestMethodNotSupportedException(null);
		}

		if (entity.getUserType().equals("0")) {
			logger.warn("error : showEditForm(\" + id + \")   ERR:405");
			throw new HttpRequestMethodNotSupportedException(null);
		}
		// 2. 準備：資料模型。
		// (1) 即將被修改的實體。
		BeanUtils.copyProperties(entity, form);
		// (2)資料傳到瀏覽器之前,將密碼資料清空
		form.setCnfrPassword("");
		form.setEncryptPassword("");
		form.setPassword("");
		model.addAttribute("form", form);

		// 3. 返回：修改作業的網頁路徑。
		logger.info("success : showEditForm(" + entity.getAccount() + ", " + entity.getDepartment() + ")");
		return "/admin_user/edit";
	}

	/**
	 * 修改作業(2/2) - 管理
	 * 
	 * @param entity
	 *            即將被修改的數據實體。
	 * @param result
	 *            自動驗證的結果集。
	 * @param model
	 *            資料模型。
	 * @return 修改作業(失敗) 或 查詢作業(成功) 的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@RequestMapping(path = "/admin_user/edit", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "admin_user")
	@DoOperationLog(accessId = "admin_user", operation = "E", targetObject = "T_ADMIN_USER")
	public String edit(
			// @Valid @ModelAttribute("entity") AdminUser entity,
			@Valid @ModelAttribute("adminUserForm") AdminUserForm form, BindingResult result, Model model)
			throws HttpRequestMethodNotSupportedException {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser edit json after error");
		}
			
		// 1. 檢查："群組" 非空值。
		if (form.getAdminGroup().getOid() == null) {
			result.addError(new FieldError("form", "adminGroup.oid",
					messageSource.getMessage("warn.not-null", null, LocaleContextHolder.getLocale())));
			logger.warn("warning : group.oid is null");
		}

		// 2. 檢查：自動驗證的結果集。
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/admin_user/edit";
		}

		// 3. 密碼的部分
		// 取回數據實體。
		AdminUser entity = adminUserService.findById(form.getOid());

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(entity));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser write before log error");
		}
		
		
		if (entity.getUserType().equals("0")) {
			logger.warn("error : showEditForm(\" + id + \")   ERR:405");
			throw new HttpRequestMethodNotSupportedException(null);
		}
		// 沒有輸入新密碼,表示不修改密碼,繼續使用舊密碼
		if (form.getEncryptPassword() == null || form.getEncryptPassword().length() == 0) {
			form.setPassword(entity.getPassword());
		} else {
			// 使用加密過後的資料
			form.setPassword(form.getEncryptPassword());
			entity.setLastPswdDttm(DateUtil.getCurrentDateTime());

		}

		// 4. 主流程：修改數據實體。
		BeanUtils.copyProperties(form, entity, "account", "lastLoginDttm", "userType", "optype", "createDate",
				"createTime", "updateDate", "updateTime", "lastPswdDttm");

		try {
			adminUserService.update(entity);
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/admin_user/edit";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 返回：查詢作業的網頁路徑。
		logger.info("success : edit(" + form.getAccount() + ", " + form.getDepartment() + ")");
		return recallQuery(model);
	}

	/**
	 * 刪除作業(1/1) - 管理
	 * 
	 * @param id
	 *            <功能群組使用者> 的主鍵值。
	 * @param model
	 *            資料模型。
	 * @return 查詢作業的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException
	 */
	@RequestMapping(path = "/admin_user/delete")
	@AcessRightCheck(accessId = "admin_user")
	@DoOperationLog(accessId = "admin_user", operation = "D", targetObject = "T_ADMIN_USER")
	public String delete(@RequestParam("oid") Long id, Model model) throws HttpRequestMethodNotSupportedException {
		
		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		AdminUser entity = adminUserService.findById(id);
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,JsonUtil.writeValueAsBytes(entity));
		} catch (JsonProcessingException e) {
			logger.warn("adminUser delete json before error");
		}
		
		if (entity==null||entity.getUserType().equals("0")) {
			logger.warn("error : showEditForm(\" + id + \")   ERR:405");
			throw new HttpRequestMethodNotSupportedException(null);
		}

		// 1. 主流程：刪除數據實體。
		adminUserService.delete(id);

		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);

		// 2. 返回查詢作業的網頁路徑。
		logger.info("success : delete(" + id + ")");
		return recallQuery(model);
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model ) {

		// 從 session attribute 取得查詢表單物件
		AdminUserCriteriaForm form = (AdminUserCriteriaForm) model.asMap().get("adminUserCriteriaForm");

		form.setConfigAdminGroupList(adminGroupService.findAll());
		// (2) 查詢：<功能群組使用者> 集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT, adminUserService.query(form.getCriteriaAdminGroupOid(),
				form.getCriteriaAccount(), 0 , appProperties.getPageSize()));
		return "/admin_user/query";
	}
}
