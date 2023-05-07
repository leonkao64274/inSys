/*
 * @(#)OperationLogController.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理系統 "操作記錄查詢" 控制器類別
 *
 * Modify History:
 * v1.00,   /03/13, Leon Kao
 * 1) First release
 *   
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;
import com.invSet.emv.invSys.invSysserver.core.bean.OperationLog;
import com.invSet.emv.invSys.invSysserver.core.service.AdminMenuService;
import com.invSet.emv.invSys.invSysserver.core.service.OperationLogService;
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - ACS 後台管理系統 "操作記錄查詢" 控制器類別
 * 
 * @author   LeonKao
 */
@SessionAttributes("operationLogCriteriaForm")
@Controller
public class OperationLogController {

	/**
	 * Logging utility
	 */
	private static final Logger logger = LoggerFactory.getLogger(OperationLogController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	/**
	 * 發卡行管理服務
	 */
	// @Autowired
	// private IssuerService issuerService;

	/**
	 * 系統功能選單設定服務
	 */
	@Autowired
	private AdminMenuService adminMenuService;

	/**
	 * 操作記錄服務
	 */
	@Autowired
	private OperationLogService operationLogService;

	/**
	 * 準備 "模型的組態" 信息
	 * 
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 */
	private void prepareConfigModel(Model model) {

		// // (1): 發卡銀行
		// model.addAttribute("issuerConfigModel", issuerService.findAll());

		// 功能選單
		model.addAttribute("menuConfigModel", adminMenuService.getFunctionList("Y", "Y"));
	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 * 
	 * @return 查詢條件表單 Model 物件
	 */
	@ModelAttribute("operationLogCriteriaForm")
	public OperationLogCriteriaForm initialModel() {

		OperationLogCriteriaForm form = new OperationLogCriteriaForm();

		// 查詢日期起訖
		form.setCriteriaStartDate(DateUtil.formateDate("yyyy-MM-dd", new Date()));
		form.setCriteriaEndDate(DateUtil.formateDate("yyyy-MM-dd", new Date()));

		return form;
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
	 * @return 查詢條件及查詢結果顯示頁面路徑
	 */
	@AcessRightCheck(accessId = "operation_log")
	@RequestMapping(value = "/operation_log/query")
	public String query(@Valid @ModelAttribute("operationLogCriteriaForm") OperationLogCriteriaForm form,
			BindingResult result, Model model) {

		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/operation_log/query";
		}
		logger.debug("[pageNumber]:" + form.getPageNumber());

		// 3. 依據查詢條件進行查詢
		Page<OperationLog> queryResult = operationLogService.queryByCriterial(
				DateUtil.revertDate(form.getCriteriaStartDate()), DateUtil.revertDate(form.getCriteriaEndDate()),
				form.getCriteriaAccount(), form.getCriteriaAccessId(), form.getCriteriaAction(),
				form.getCriteriaResult(), new PageRequest(form.getPageNumber(), appProperties.getPageSize(),
						new Sort(Sort.Direction.DESC, "createDate", "createTime")));

		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/operation_log/query";

	}

	/**
	 * 查詢異動明細
	 * 
	 * @param oid
	 *            物件識別碼，指定欲查看的操作記錄資料
	 * @param model
	 *            UI Model, 用於存放返回網頁顯示的資料
	 * 
	 * @return 異動明細頁面
	 */
	@AcessRightCheck(accessId = "operation_log")
	@RequestMapping(value = "/operation_log/detail")
	public String showDetail(@RequestParam Long oid, Model model) {

		// 1. 檢核表單提交資料
		if (oid == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 查詢取得操作記錄
		OperationLog operationLog = operationLogService.findById(oid);
		if (operationLog == null) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 轉換異動明細 Json 為 HashMap, 供前端網頁顯示使用
		// 查詢條件內容
		if (operationLog.getDataQuery() != null) {
			try {
				HashMap<String, Object> dataQuery = JsonUtil.readValue(operationLog.getDataQuery(),
						new TypeReference<HashMap<String, Object>>() {
						});
				model.addAttribute("dataQuery", dataQuery);
			} catch (IOException ex) {
				logger.warn("Failed to convert data_before JOSN into Hashmap!", ex);
			}
		}

		// 異動前資料
		if (operationLog.getDataBefore() != null) {
			try {
				HashMap<String, Object> dataBefore = JsonUtil.readValue(operationLog.getDataBefore(),
						new TypeReference<HashMap<String, Object>>() {
						});
				model.addAttribute("dataBefore", dataBefore);
			} catch (IOException ex) {
				logger.warn("Failed to convert data_before JOSN into Hashmap!", ex);
			}
		}

		// 異動後資料
		if (operationLog.getDataAfter() != null) {
			try {
				HashMap<String, Object> dataAfter = JsonUtil.readValue(operationLog.getDataAfter(),
						new TypeReference<HashMap<String, Object>>() {
						});
				model.addAttribute("dataAfter", dataAfter);
			} catch (IOException ex) {
				logger.warn("Failed to convert data_before JOSN into Hashmap!", ex);
			}
		}
		if (operationLog.getAccessId().equals("admin_group") && !operationLog.getAction().equals("Q"))
			putMenuMap(model);

		// 4. 返回異動明細頁面
		model.addAttribute("operationLog", operationLog);
		return "/operation_log/detail";
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
		OperationLogCriteriaForm form = (OperationLogCriteriaForm) model.asMap().get("operationLogCriteriaForm");

		// 3. 依據查詢條件進行查詢
		Page<OperationLog> queryResult = operationLogService.queryByCriterial(
				DateUtil.revertDate(form.getCriteriaStartDate()), DateUtil.revertDate(form.getCriteriaEndDate()),
				form.getCriteriaAccount(), form.getCriteriaAccessId(), form.getCriteriaAction(),
				form.getCriteriaResult(), new PageRequest(form.getPageNumber(), appProperties.getPageSize(),
						new Sort(Sort.Direction.DESC, "createDate", "createTime")));

		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/operation_log/query";

	}

	private void putMenuMap(Model model) {
		List<AdminMenu> list = adminMenuService.getFunctionList("Y", "Y");
		HashMap<String, String> map = new HashMap<String, String>();
		list.forEach(adminMenu -> map.put(adminMenu.getAccessId(), adminMenu.getI18nCode()));
		model.addAttribute("accessIdMap", map);
	}
}
