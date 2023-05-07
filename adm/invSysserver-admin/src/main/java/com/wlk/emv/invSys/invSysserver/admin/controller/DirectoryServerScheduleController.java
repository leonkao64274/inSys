/*
 * @(#)DirectoryServerScheduleController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理 "卡段下載設定" 控制器類別
 *
 * Modify History:
 * v1.00,  /07/31, LeonKao
 *   1) First release
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
import org.springframework.util.StringUtils;
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
import com.invSet.emv.invSys.invSysserver.core.bean.DirectoryServerSchedule;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.DirectoryServerScheduleService;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
import com.invSet.emv.invSys.invSysserver.core.util.StringUtil;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 "卡段下載設定" 控制器類別
 * 
 * @author LeonKao
 */
@SessionAttributes("directoryServerScheduleCriteriaForm")
@Controller
public class DirectoryServerScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryServerScheduleController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	/**
	 * 卡號範圍業務邏輯服務
	 */
	@Autowired
	private DirectoryServerScheduleService directoryServerScheduleService;

	/**
	 * 卡組織管理服務
	 */
	@Autowired
	private SysCodeService sysCodeService;

	/**
	 * 準備 "模型的組態" 信息
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 */
	private void prepareConfigModel(Model model) {
		// (1): 卡組織
		model.addAttribute("cardSchemeConfigModel",
				sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));
	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 * 
	 * @return directoryServerScheduleCriteriaForm
	 */
	@ModelAttribute("directoryServerScheduleCriteriaForm")
	public DirectoryServerScheduleCriteriaForm initialModel() {
		return new DirectoryServerScheduleCriteriaForm();
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
	 * @param pageNumber
	 *            指定分頁查詢頁次
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@DoOperationLog(accessId = "ds_cardrange", operation = "Q", targetObject = "T_DIRECTORY_SERVER_SCHEDULE")
	@RequestMapping(value = "/directory_server_schedule/query")
	@AcessRightCheck(accessId = "ds_cardrange")
	public String query(
			@Valid @ModelAttribute("directoryServerScheduleCriteriaForm") DirectoryServerScheduleCriteriaForm form,
			BindingResult result, Model model) {

		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server_schedule query json query error");
		}
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/directory_server_schedule/query";
		}

		// 3. 依據查詢條件查詢 "發卡銀行", "卡組織" 資料
		DirectoryServerSchedule example = new DirectoryServerSchedule();
		// 查詢條件(1): 卡組織
		example.setCardScheme(form.getCriteriaCardScheme());

		// 4. 執行查詢作業
		Page<DirectoryServerSchedule> queryResult = directoryServerScheduleService.findByCriteria(example,
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));

		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 5. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/directory_server_schedule/query";
	}

	/**
	 * 顯示新增作業表單
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 新增作業表單頁面
	 */
	@RequestMapping(value = "/directory_server_schedule/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cardrange")
	public String showAddForm(Model model) {

		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", new DirectoryServerSchedule());

		// 3. 返回新增網頁地址。
		return "/directory_server_schedule/add";
	}

	/**
	 * 提交新增作業表單資料
	 * 
	 * @param form
	 *            銀行群組 管理的 Entity 類別, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料, 如查詢結果、錯誤訊息等
	 * @return 如表單資料驗證有誤或新增作業失敗，則返回 "新增作業表單頁面"，新增成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@DoOperationLog(accessId = "ds_cardrange", operation = "A", targetObject = "T_DIRECTORY_SERVER_SCHEDULE")
	@RequestMapping(value = "/directory_server_schedule/add", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "ds_cardrange")
	public String add(@Valid @ModelAttribute("form") DirectoryServerSchedule form, BindingResult result, Model model) {

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server_schedule ADD json AFTER error");
		}
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);
		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/directory_server_schedule/add";
		}

		// 3. 保存數據實體。
		try {
			directoryServerScheduleService.save(form);
		} catch (ResourceAlreadyExistException ex) {
			logger.warn("CardScheme already exist , failed to add DirectoryServerSchedule !");
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.cardScheme-already-exist", null, LocaleContextHolder.getLocale()));
			return "/directory_server_schedule/add";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 4. 執行成功：返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 顯示修改作業表單
	 * 
	 * @param oid
	 *            物件識別碼，供系統取得該筆資料的完整內容於編輯表單畫面顯示
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 修改作業表單頁面
	 */
	@RequestMapping(value = "/directory_server_schedule/edit", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cardrange")
	public String showEditForm(@RequestParam Long oid, Model model) {

		// 1. 檢核表單提交資料
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 3. 取回數據實體。
		DirectoryServerSchedule directoryServerSchedule = directoryServerScheduleService.findById(oid);
		if (directoryServerSchedule == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 4. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", directoryServerSchedule);

		// 5. 返回 "編輯" 網頁的地址。
		return "/directory_server_schedule/edit";
	}

	/**
	 * 提交修改作業表單資料
	 * 
	 * @param form
	 *            群組使用者 管理的 Entity 類別, 供 Spring 綁定用戶提交表單資料使用
	 * @param result
	 *            用戶提交表單資料驗證結果
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @param oid
	 *            物件識別碼，指定欲進行修改的資料
	 * @return 如表單資料驗證有誤或修改作業失敗，則返回 "修改作業表單頁面"，修改成功則返回 "查詢條件及查詢結果顯示頁面"
	 */
	@RequestMapping(value = "/directory_server_schedule/edit", method = RequestMethod.POST)
	@AcessRightCheck(accessId = "ds_cardrange")
	@DoOperationLog(accessId = "ds_cardrange", operation = "E", targetObject = "T_DIRECTORY_SERVER_SCHEDULE")
	public String update(@Valid @ModelAttribute("form") DirectoryServerSchedule form, BindingResult result,
			@RequestParam Long oid, Model model) {

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server_schedule edit json AFTER error");
		}
		// 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 檢核表單提交資料
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return "/directory_server_schedule/edit";
		}

		// 檢核表單提交是否有錯誤
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/directory_server_schedule/edit";
		}


		// 取回數據實體。
		DirectoryServerSchedule directoryServerSchedule = directoryServerScheduleService.findById(oid);
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,
					JsonUtil.writeValueAsBytes(directoryServerSchedule));
		} catch (JsonProcessingException e) {
			logger.warn("DirectoryServerSchedule write before log error");
		}
		if (directoryServerSchedule == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return "/directory_server_schedule/edit";
		}

		directoryServerSchedule.setOpDuration(form.getOpDuration());
		directoryServerSchedule.setOpRunningType(form.getOpRunningType());
		directoryServerSchedule.setServerUrl(form.getServerUrl());
		// 修改時,卡組織是唯讀屬性
		// directoryServerSchedule.setCardScheme(form.getCardScheme());
		// //修改時,卡組織是唯讀屬性

		// 更新數據實體
		try {
			directoryServerScheduleService.update(directoryServerSchedule);
		} catch (ResourceNotFoundException ex) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return "/directory_server_schedule/edit";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 成功：返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 刪除作業
	 * 
	 * @param oid
	 *            物件識別碼，指定欲進行刪除的資料
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@RequestMapping(value = "/directory_server_schedule/delete", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cardrange")
	@DoOperationLog(accessId = "ds_cardrange", operation = "D", targetObject = "T_DIRECTORY_SERVER_SCHEDULE")
	public String delete(@RequestParam Long oid, Model model) {

		// 1. 檢查："主鍵"值不為空值。
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 取回數據實體。
		DirectoryServerSchedule directoryServerSchedule = directoryServerScheduleService.findById(oid);

		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,
					JsonUtil.writeValueAsBytes(directoryServerSchedule));
		} catch (JsonProcessingException e) {
			logger.warn("DirectoryServerSchedule write before log error");
		}
		if (directoryServerSchedule == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 3. 刪除數據實體。
		directoryServerScheduleService.delete(oid);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 4. 返回查詢條件頁面並重新查詢資料
		return recallQuery(model);
	}

	/**
	 * 顯示細節作業表單
	 * 
	 * @param oid
	 *            物件識別碼，供系統取得該筆資料的完整內容於編輯表單畫面顯示
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * @return 修改作業表單頁面
	 */
	@RequestMapping(value = "/directory_server_schedule/detail", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "ds_cardrange")
	public String showDetailForm(@RequestParam Long oid, Model model) {

		// 1. 檢核表單提交資料
		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 3. 取回數據實體。
		DirectoryServerSchedule directoryServerSchedule = directoryServerScheduleService.findById(oid);
		if (directoryServerSchedule == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 4. 建立表單物件，供編輯畫面表單綁定使用
		model.addAttribute("form", directoryServerSchedule);

		// 5. 返回 "編輯" 網頁的地址。
		return "/directory_server_schedule/detail";
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @param pageNumber
	 *            指定分頁查詢頁次
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 從 session attribute 取得查詢表單物件
		DirectoryServerScheduleCriteriaForm form = (DirectoryServerScheduleCriteriaForm) model.asMap()
				.get("directoryServerScheduleCriteriaForm");

		// 3. 依據查詢條件查詢 "發卡銀行", "卡組織" 資料
		DirectoryServerSchedule example = new DirectoryServerSchedule();
		// 查詢條件(1): 卡組織
		example.setCardScheme(form.getCriteriaCardScheme());

		// 4. 執行查詢作業
		Page<DirectoryServerSchedule> queryResult = directoryServerScheduleService.findByCriteria(example,
				new PageRequest(form.getPageNumber(), appProperties.getPageSize()));

		// 5. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/directory_server_schedule/query";
	}
}