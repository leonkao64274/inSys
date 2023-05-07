/*
 * @(#)AdminGroupController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定 Controller(控制器)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.00,  /07/26, Milo Gao
 *   2) 接手開發。
 * v1.01,  /07/30, Milo Gao
 *   3) Second release
 * v1.02,   /02/07, LeonKao
 *   1) 調整驗證
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
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
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroupPrivilege;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupPrivilegeService;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;
import com.invSet.emv.invSys.invSysserver.core.service.AdminMenuService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定 Controller(控制器)。<br/>
 * Session 級屬性：<br/>
 * (1) adminGroupCriteriaForm "功能群組設定" 作業用的查詢條件 Form。 (2)
 * adminGroupPrivilegeForm "功能群組權限設定" 作業用的設定 Form。
 * 
 * @author   LeonKao, MiloGao
 */
@SessionAttributes({ "adminGroupCriteriaForm", "adminGroupPrivilegeForm" })
@Controller
public class AdminGroupController {

	private static final Logger logger = LoggerFactory.getLogger(AdminGroupController.class);

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
	 * 功能選單設定 Service(已實作)。
	 */
	@Autowired
	private AdminMenuService adminMenuService;

	/**
	 * 功能群組設定 Service(已實作)。
	 */
	@Autowired
	private AdminGroupService adminGroupService;

	/**
	 * 功能群組權限設定 Service(已實作)。
	 */
	@Autowired
	private AdminGroupPrivilegeService adminGroupPrivilegeService;

	/**
	 * 派遣服務請求前的前置程序。
	 * 
	 * @param model
	 *            網頁級資料模型
	 * @param session
	 *            瀏覽器級資料模型
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {

		// 1. 檢查 "查詢作業" 用的 Form。
		AdminGroupCriteriaForm form = (AdminGroupCriteriaForm) session.getAttribute("adminGroupCriteriaForm");
		
		if (form == null) {
			form = new AdminGroupCriteriaForm();
		}
		
		if (model.containsAttribute("adminGroupCriteriaForm") == false) {
			model.addAttribute("adminGroupCriteriaForm", form);
		}

		// 2. 檢查 "新增作業" 與 "修改作業" 用的 Form。
		if (model.containsAttribute("adminGroupPrivilegeForm") == false) {
			AdminGroupPrivilegeForm adminGroupPrivilegeForm = (AdminGroupPrivilegeForm) session.getAttribute("adminGroupPrivilegeForm");
			if (adminGroupPrivilegeForm == null) {
				adminGroupPrivilegeForm = new AdminGroupPrivilegeForm(adminMenuService.getTopLevelMenuNodes());
			}
			model.addAttribute("adminGroupPrivilegeForm", adminGroupPrivilegeForm);
		}
	}

	/**
	 * 查詢作業(1/2) - 功能群組設定 (管理)
	 * 
	 * @param form
	 *            查詢作業用的 Form。
	 * @param result
	 *            自動驗證結果。
	 * @param model
	 *            網頁級資料模型。
	 * @param pageNumber
	 *            當前頁面序號。
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_group/query")
	@AcessRightCheck(accessId = "admin_group")
	@DoOperationLog(accessId = "admin_group", targetObject = "T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE", operation = "Q")
	public String query(@Valid @ModelAttribute("adminGroupCriteriaForm") AdminGroupCriteriaForm form,
			BindingResult result, Model model) {
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminGroup Query json error");
		}
		// 1. 檢查：查詢條件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors()) {
			logger.warn("error : query(" + form.getPageNumber() + ", " + form.getCriteriaGroupId() + ", "
					+ form.getCriteriaGroupName() + ")");
			return "/admin_group/query";
		}

		Page<AdminGroup> page=adminGroupService.query(form.getCriteriaGroupId(),
				form.getCriteriaGroupName(), form.getPageNumber(), appProperties.getPageSize());
		int maxPage=page.getTotalPages();
		if(form.getPageNumber()>=maxPage && form.getPageNumber()!=0 && form.getPageNumber()!=1) {
			form.setPageNumber(maxPage-1);
			page=adminGroupService.query(form.getCriteriaGroupId(),
					form.getCriteriaGroupName(), form.getPageNumber(), appProperties.getPageSize());
		}
		// 2. 執行：查詢 "功能群組設定" 的分頁集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT, page);

		// 3. 返回：查詢作業的網頁路徑。
		logger.info("success : query(" + form.getPageNumber() + ", " + form.getCriteriaGroupId() + ", "
				+ form.getCriteriaGroupName() + ")");
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return "/admin_group/query";
	}

	/**
	 * 查詢作業(2/2) - 功能群組設定 (只能查詢)
	 * 
	 * @param form
	 *            查詢條件 Form。
	 * @param result
	 *            自動驗證結果。
	 * @param model
	 *            網頁級資料模型。
	 * @param pageNumber
	 *            當前頁面序號。
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_group/op_query")
	@AcessRightCheck(accessId = "admin_group_query")
	@DoOperationLog(accessId = "admin_group_query", targetObject = "T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE", operation = "Q")
	public String queryReadOnly(@ModelAttribute("adminGroupCriteriaForm") AdminGroupCriteriaForm form,
			BindingResult result, Model model) {
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("adminGroup Query json error");
		}
		// 1. 調用相同方法。
		logger.info("redirect : query(), begin");
		this.query(form, result, model);

		// 2. 返回：查詢作業的網頁路徑。
		logger.info("redirect : query(), end");
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return "/admin_group/op_query";
	}

	/**
	 * 新增作業(1/2) - 功能群組設定 (訪問)
	 * 
	 * @param form
	 *            新增作業用的 Form。
	 * @param model
	 *            網頁級資料模型。
	 * @return 新增作業的網頁路徑。
	 */
	@RequestMapping(path = "/admin_group/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "admin_group")
	public String showAddForm(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form, Model model) {

		// 1. 準備資料模型：(1)即將新增用的實體 (2)帶功能選單訊息+初始化
		model.addAttribute("entity", new AdminGroup());
		form.initialPrivileges();

		// 2. 返回：新增作業的網頁路徑。
		logger.info("visit : showAddForm()");
		return "/admin_group/add";
	}

	/**
	 * 新增作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form
	 *            新增作業用的設定 Form。
	 * @param entity
	 *            即將被新增的 Entity。
	 * @param result
	 *            自動驗證結果。
	 * @param privileges
	 *            用戶勾選的 "權限" 選項集合。
	 * @param model
	 *            網頁級資料模型。
	 * @return 新增作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/admin_group/add", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "admin_group")
	@DoOperationLog(accessId = "admin_group", targetObject = "T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE", operation = "A")
	public String add(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form,
			@Valid @ModelAttribute("entity") AdminGroup entity, BindingResult result,
			@RequestParam("privileges") List<String> privileges, Model model) {

		// ----CLONE START----
		AdminGroup clone = new AdminGroup();
		BeanUtils.copyProperties(entity, clone);
		Set<AdminGroupPrivilege> clonePrivilegeList = new HashSet<AdminGroupPrivilege>();
		for (String accessId : privileges) {
			// "e"為前端垃圾訊息 故略過
			if (!accessId.equals("e"))
				clonePrivilegeList.add(new AdminGroupPrivilege(accessId, entity));
		}
		clone.setAdminGroupPrivileges(clonePrivilegeList);
		try {
			// 操作記錄 - 異動前/後資料
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(clone));
		} catch (JsonProcessingException e) {
			logger.warn("adminGroup Edit json after error");
		}
		// ----CLONE END------
		

		// 1. 檢查：網頁控件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/admin_group/add";
		}

		Set<AdminGroupPrivilege> privilegeList = entity.getAdminGroupPrivileges();

		// 2. 將 "權限" 訊息，加到目標實體內。
		for (String accessId : privileges) {
			// "e"為前端垃圾訊息 故略過
			if (!accessId.equals("e"))
				privilegeList.add(new AdminGroupPrivilege(accessId, entity));
		}

		// 3. 驗證目標實體內是否有權限，如果沒有權限則返回顯示錯誤訊息
		if (privilegeList.size() == 0) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.admingroup-privilege.must-choose-one",
					null, LocaleContextHolder.getLocale()));
			return "/admin_group/add";
		}

		// 4. 資料操作：新增 "功能群組設定" 實體。
		try {
			adminGroupService.save(entity);
		} catch (ResourceAlreadyExistException ex) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.admingroup.groupid-cannot-repeat",
					null, LocaleContextHolder.getLocale()));
			logger.warn(ex.toString());
			return "/admin_group/add";
		}

		// 4. 返回：查詢作業的網頁路徑。
		logger.info("success : add(" + entity.getGroupName() + ")");
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		return recallQuery(model);
	}

	/**
	 * 修改作業(1/2) - 功能群組設定 (訪問)
	 * 
	 * @param form
	 *            修改作業用的設定 Form。
	 * @param id
	 *            功能群組設定 Entity 的主鍵值。
	 * @param model
	 *            網頁級資料模型。
	 * @return 查詢作業(失敗) 或 修改作業(成功)的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException 
	 */
	@RequestMapping(path = "/admin_group/edit", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "admin_group")
	public String showEditForm(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form,
			@RequestParam("oid") Long id, Model model) throws HttpRequestMethodNotSupportedException {

		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		
		// 1. 讀取實體。
		AdminGroup entity = adminGroupService.findById(id);
		if (entity == null) {
			logger.warn("405");
			throw new HttpRequestMethodNotSupportedException(null);
		}

		// 2. 準備資料模型：(1)即將修改用的實體 (2)帶功能選單訊息+初始化
		model.addAttribute("entity", entity);
		form.initialPrivileges(entity.getAdminGroupPrivileges());

		// 3. 返回：新增作業的網頁路徑。
		logger.info("visit : showEditForm(" + entity.getGroupId() + "," + entity.getGroupName() + ")");
		return "/admin_group/edit";
	}

	/**
	 * 修改作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form
	 *            修改作業用的設定 Form。
	 * @param entity
	 *            被編輯的 Entity。
	 * @param result
	 *            自動驗證結果。
	 * @param privileges
	 *            用戶勾選的 "權限" 選項集合。
	 * @param model
	 *            網頁級資料模型。
	 * @return 修改作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/admin_group/edit", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "admin_group")
	@DoOperationLog(accessId = "admin_group", targetObject = "T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE", operation = "E")
	public String edit(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form,
			@Valid @ModelAttribute("entity") AdminGroup entity, BindingResult result,
			@RequestParam("privileges") List<String> privileges, Model model) {

		AdminGroup admin = adminGroupService.findById(entity.getOid());
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(admin));
		} catch (JsonProcessingException e) {
			logger.warn("AdminGroup write before log error");
		}

		// ----CLONE START----
		AdminGroup clone = new AdminGroup();
		BeanUtils.copyProperties(entity, clone);
		Set<AdminGroupPrivilege> clonePrivilegeList = new HashSet<AdminGroupPrivilege>();
		for (String accessId : privileges) {
			// "e"為前端垃圾訊息 故略過
			if (!accessId.equals("e"))
				clonePrivilegeList.add(new AdminGroupPrivilege(accessId, entity));
		}
		clone.setAdminGroupPrivileges(clonePrivilegeList);
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(clone));
		} catch (JsonProcessingException e) {
			logger.warn("adminGroup Edit json after error");
		}
		// ----CLONE END------

		Set<AdminGroupPrivilege> privilegeList = entity.getAdminGroupPrivileges();

		// 1. 檢查：網頁控件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors() || privileges.size() <= 1) {
			logger.warn("form error: \n"+result.getAllErrors());
			// 驗證目標實體內是否有權限，如果沒有權限則返回顯示錯誤訊息
			if (privileges.size() <= 1) {
				for (List<AdminMenu> list : form.getfunctions())
					for (AdminMenu adminMenu : list)
						adminMenu.setMark(false);
				model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
						"warn.admingroup-privilege.must-choose-one", null, LocaleContextHolder.getLocale()));
				return "/admin_group/edit";
			} else {
				for (List<AdminMenu> list : form.getfunctions())
					for (AdminMenu adminMenu : list) {
						if (privileges.contains(adminMenu.getAccessId()))
							adminMenu.setMark(true);
						else
							adminMenu.setMark(false);
					}
			}
			logger.warn("error : edit(" + entity.getGroupId() + ", " + entity.getGroupName() + ")");
			return "/admin_group/edit";
		}
		// 清除就有權限 避免累加
		Iterator<AdminGroupPrivilege> it = privilegeList.iterator();
		while (it.hasNext()) {
			AdminGroupPrivilege adminGroupPrivilege = (AdminGroupPrivilege) it.next();
			// adminGroupPrivilege.setAdminGroup(null);
			it.remove();
		}

		// 2. 將 "權限" 訊息，加到目標實體內。
		for (String accessId : privileges) {
			// "e"為前端垃圾訊息 故略過
			if (!accessId.equals("e"))
				privilegeList.add(new AdminGroupPrivilege(accessId, entity));
		}

		// 4. 資料操作一：修改 "功能群組設定" 實體。
		try {
			adminGroupPrivilegeService.deleteByAdminGroupId(entity.getOid());
			adminGroupService.update(entity);
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/admin_group/edit";
		} catch (Exception ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/admin_group/edit";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 返回：查詢作業的網頁路徑。
		return recallQuery(model);
	}

	/**
	 * 刪除作業(1/1) - 功能群組設定
	 * 
	 * @param id
	 *            實體主鍵
	 * @param model
	 *            網頁級的資料模型
	 * @return 查詢作業的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException 
	 */
	@RequestMapping(path = "/admin_group/delete")
	@AcessRightCheck(accessId = "admin_group")
	@DoOperationLog(accessId = "admin_group", targetObject = "T_ADMIN_MENU,T_ADMIN_GROUP_PRIVILEGE", operation = "D")
	public String delete(@Valid @ModelAttribute("adminGroupCriteriaForm") AdminGroupCriteriaForm form,
			@RequestParam("oid") Long id, Model model) throws HttpRequestMethodNotSupportedException {

		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		
		// 1. 檢查：企業原則：
		AdminGroup entity = adminGroupService.findById(id);

		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(entity));
		} catch (JsonProcessingException e) {
			logger.warn("adminGroup delete json before error");
		}
		// -- 程式不處理：實體被別的用戶刪除的情形，理由：也是刪除了。
		if (entity == null) {
			logger.warn("405");
			throw new HttpRequestMethodNotSupportedException(null);
		}
		// (1) 功能群組使用者，必須為空。
		if (entity.getAdminUsers().size() > 0) {
			model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage("warn.admingroup.this-group-has-other-uesr",
					null, LocaleContextHolder.getLocale()));
			logger.warn("error : users = " + entity.getAdminUsers().size());
			model.addAttribute(WebKeyConst.QUERY_RESULT, adminGroupService.query(form.getCriteriaGroupId(),
					form.getCriteriaGroupName(), form.getPageNumber(), appProperties.getPageSize()));
			return "/admin_group/query";
		}

		// 2. 刪除實體與從表實體集合。
		adminGroupService.delete(id);

		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);

		// 3. 返回查詢作業的網頁路徑。
		logger.info("success : delete(" + id + ")");
		return recallQuery(model);
	}

	/**
	 * 明細作業(1/2) - 功能群組設定(管理)
	 * 
	 * @param form
	 *            明細作業用的 Form。
	 * @param id
	 *            數據實體的主鍵。
	 * @param model
	 *            網頁級資料模型
	 * @return 明細作業的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException 
	 */
	@RequestMapping(path = "/admin_group/detail")
	@AcessRightCheck(accessId = "admin_group")
	public String detail(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form,
			@RequestParam("oid") Long id, Model model) throws HttpRequestMethodNotSupportedException {
		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 1. 讀取實體。
		AdminGroup adminGroup = adminGroupService.findById(id);
		if (adminGroup == null) {
			logger.warn("405");
			throw new HttpRequestMethodNotSupportedException(null);
		}

		// 2. 準備資料模型：(1)即將顯示用的實體 (2)帶功能選單訊息+初始化
		model.addAttribute("entity", adminGroup);

		// key 是有權限的功能群組名稱
		// modules 是 4個功能群組
		// adminGroupPrivilege=所擁有的權限

		List<AdminGroupPrivilege> adminGroupPrivilege = adminGroupPrivilegeService
				.findByAdminGroupOid(adminGroup.getOid());

		ArrayList<String> permissionList;
		HashMap<String, ArrayList<String>> permissionMap = new HashMap<>();
		ArrayList<String> key = new ArrayList<>();
		for (int i = 0; i < form.getModules().size(); i++) {// size=4

			for (AdminMenu am : form.getModules().get(i).getChildNodes()) {

				for (int j = 0; j < adminGroupPrivilege.size(); j++) {
					if (am.getAccessId().equals(adminGroupPrivilege.get(j).getAccessId())) {
						if (permissionMap.containsKey(form.getModules().get(i).getI18nCode()))
							permissionList = permissionMap.get(form.getModules().get(i).getI18nCode());
						else {
							permissionList = new ArrayList<>();
							key.add(form.getModules().get(i).getI18nCode());
						}
						permissionList.add(am.getI18nCode());
						permissionMap.put(form.getModules().get(i).getI18nCode(), permissionList);

					}
				}
			}
		}

		// 權限列表,提供明細畫面顯示使用
		model.addAttribute("permissionMap", permissionMap);
		model.addAttribute("key", key);
		// 3. 返回：新增作業的網頁路徑。
		logger.info("success : detail(" + adminGroup.getGroupId() + "," + adminGroup.getGroupName() + ")");
		return "/admin_group/detail";
	}

	/**
	 * 明細作業(2/2) - 功能群組設定(查詢)
	 * 
	 * @param form
	 *            明細作業用的 Form。
	 * @param id
	 *            數據實體的主鍵。
	 * @param model
	 *            網頁級資料模型
	 * @return 明細作業的網頁路徑。
	 * @throws HttpRequestMethodNotSupportedException 
	 */
	@RequestMapping(path = "/admin_group/op_detail")
	@AcessRightCheck(accessId = "admin_group_query")
	public String detailReadOnly(@ModelAttribute("adminGroupPrivilegeForm") AdminGroupPrivilegeForm form,
			@RequestParam("oid") Long id, Model model) throws HttpRequestMethodNotSupportedException {
		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallOpQuery(model);
		}
		// 1. 調用相同程序。
		logger.info("redirect : detail(), begin");
		this.detail(form, id, model);
		// 2. 返回明細作業的網頁路徑。
		logger.info("redirect : detail(), end");
		return "/admin_group/op_detail";
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @param pageNumber
	 *            指定分頁查詢頁次
	 * 
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {

		// 從 session attribute 取得查詢表單物件
		AdminGroupCriteriaForm form = (AdminGroupCriteriaForm) model.asMap().get("adminGroupCriteriaForm");

		// 執行查詢作業
		model.addAttribute(WebKeyConst.QUERY_RESULT, adminGroupService.query(form.getCriteriaGroupId(),
				form.getCriteriaGroupName(), form.getPageNumber(), appProperties.getPageSize()));
		return "/admin_group/query";
	}

	private String recallOpQuery(Model model) {

		// 從 session attribute 取得查詢表單物件
		AdminGroupCriteriaForm form = (AdminGroupCriteriaForm) model.asMap().get("adminGroupCriteriaForm");

		// 執行查詢作業
		model.addAttribute(WebKeyConst.QUERY_RESULT, adminGroupService.query(form.getCriteriaGroupId(),
				form.getCriteriaGroupName(), form.getPageNumber(), appProperties.getPageSize()));
		return "/admin_group/op_query";
	}
}
