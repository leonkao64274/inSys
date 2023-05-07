///*
// * @(#)CurrencyController.java
// *
// * Copyright (c)   invSet Incorporated.
// * All rights reserved.
// * 
// * Description:
// *      InvCore EMV invSys 系統 - invSys 後台管理系統 - 外幣幣別主檔的控制器類
// *
// * Modify History:
// * v1.00,   /05/14, LeonKao
// *   1) First release
// *   2) ACS移植功能並做相對應調整
// */
//package com.invSet.emv.invSys.invSysserver.admin.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
//import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
//import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
//import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
//import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
//import com.invSet.emv.invSys.invSysserver.core.bean.Currency;
//import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
//import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.service.CurrencyService;
//import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
//
//
//
///**
// * InvCore EMV invSys 系統 - invSys 後台管理系統 - 外幣幣別主檔的控制器類
// * 
// * @author LeonKao
// */
//@Controller
//@SessionAttributes({ "currencyCriteriaForm" })
//public class CurrencyController {
//
//	private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);
//
//	@Autowired
//	private MessageSource messageSource;
//
//	@Autowired
//	private AppProperties appProperties;
//
//	@Autowired
//	private CurrencyService currencyService;
//
//
//	/**
//	 * 初始化程序。
//	 * 
//	 * @param model 網頁級模型。
//	 * @param session 瀏覽器級模型。
//	 */
//	@ModelAttribute
//	public void initial(Model model, HttpSession session) {
//
//		// 1. 初始化：創建查詢條件的表單。
//		CurrencyCriteriaForm form = (CurrencyCriteriaForm) 
//				session.getAttribute("currencyCriteriaForm");
//		if (form == null) {
//			form = new CurrencyCriteriaForm();
//			logger.info("new session attribute : currencyCriteriaForm");
//		}
//		if (model.containsAttribute("currencyCriteriaForm") == false) {
//			model.addAttribute("currencyCriteriaForm", form);
//			logger.info("new model attribute : currencyCriteriaForm");
//		}
//
//		// 2. 添加：查詢頁的第幾頁。
//		model.addAttribute("pageNumber", form.getPageNumber());
//		logger.info("pageNumber = " + form.getPageNumber());
//	}
//
//	/**
//	 * 查詢作業。
//	 * 
//	 * @param form 查詢條件的表單。
//	 * @param result 自動驗證的結果。
//	 * @param model 網頁級模型。
//	 * @param pageNumber 分頁控件中的第幾頁。
//	 * @return 查詢頁。
//	 */
//	@RequestMapping(path = "/currency/query")
//	@AcessRightCheck(accessId = "currency_code")
//	@DoOperationLog(accessId = "currency_code", targetObject = "T_CURRENCY", operation = "Q")
//	public String query(
//			@Valid @ModelAttribute("currencyCriteriaForm") CurrencyCriteriaForm form, 
//			BindingResult result,
//			Model model) {
//		
//		// 操作記錄 - 查詢條件
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
//		} catch (JsonProcessingException e) {
//			logger.warn("currency_code write before log error");
//		}
//		// 驗證：查詢條件。
//		if (result.hasErrors()) {
//			// 返回：查詢頁。
//			logger.warn("query() : error = " + result.getErrorCount());
//			return "/currency/query";
//		}
//
//		// 主程序：查詢作業。
//		// 查詢：幣別主檔的分頁集合。
//		Pageable pageable = new PageRequest(form.getPageNumber(), appProperties.getPageSize());
//		Page<Currency> currencies = currencyService.queryByCriteria(form.criteria(), pageable);
//
//
//		// 準備模型：顯示用資料行。
//		model.addAttribute(WebKeyConst.QUERY_RESULT, currencies);
//		form.setPageNumber(form.getPageNumber());
//
//		// 返回：查詢頁。
//		logger.info("query() : success = " + currencies.getContent().size());
//		// 操作記錄 - 操作結果
//		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
//		return "/currency/query";
//	}
//
//	/**
//	 * 新增作業。
//	 * 
//	 * @param model 網頁級模型。
//	 * @return 新增頁。
//	 */
//	// @AcessRightCheck(accessId = "currency_code")
//	@RequestMapping(path = "/currency/add", method = RequestMethod.GET)
//	@AcessRightCheck(accessId = "currency_code")
//	public String add(Model model) {
//
//		// 模型準備：新增作業的表單。
//		CurrencyDataForm form = new CurrencyDataForm();
//		model.addAttribute("form", form);
//
//		// 返回：新增頁。
//		logger.info("add() : success");
//		return "/currency/add";
//	}
//
//	/**
//	 * 提交新增作業的表單。
//	 * 
//	 * @param form  數據的表單。
//	 * @param result 自動驗證的結果。
//	 * @param model 網頁級模型。
//	 * @param session 瀏覽器級模型。
//	 * @return 查詢用。
//	 */
//	@RequestMapping(path = "/currency/add", method = RequestMethod.POST)
//	@AcessRightCheck(accessId = "currency_code")
//	@DoOperationLog(accessId = "currency_code", targetObject = "T_CURRENCY", operation = "A")
//	public String insert(
//			@Valid @ModelAttribute("form") CurrencyDataForm form, 
//			BindingResult result, 
//			Model model, HttpSession session) {
//		// 操作記錄 - 異動前/後資料
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
//		} catch (JsonProcessingException e) {
//			logger.warn("currency_code write before log error");
//		}
//		// 驗證：輸入值。
//		if (result.hasErrors() == true) {
//			// 返回：新增頁。
//			logger.warn("form error: \n"+result.getAllErrors());
//			return "/currency/add";
//		}
//
//		// 自動完成：輸入值。
//		if (form.getMinorUnit() == null) {
//			form.setMinorUnit(0);
//		}
//		
//		Currency currency = new Currency();
//		
//		// 主程序：新增作業。
//		currency.setEntity(form.getEntity());
//		currency.setCurrency(form.getCurrency());
//		currency.setAlphabeticCode(form.getAlphabeticCode());
//		currency.setNumericCode(form.getNumericCode());
//		currency.setMinorUnit(form.getMinorUnit());
//		
//
//		try {
//			// 保存數據實體。
//			
//			currencyService.save(currency);
//
//		} catch (ResourceAlreadyExistException ex) {
//			return this.error("insert", ex, model);
//		}
//
//		// 返回：查詢頁。
//		logger.info("insert() : success = " + form.getCurrency());
//		// 操作記錄 - 操作結果
//		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
//		return this.redirectQuery(session);
//	}
//
//	/**
//	 * 修改作業。
//	 * 
//	 * @param id 數據實體的主鍵。
//	 * @param model 網頁級模型。
//	 * @return 修改頁。
//	 */
//	@RequestMapping(path = "/currency/edit", method = RequestMethod.GET)
//	@AcessRightCheck(accessId = "currency_code")
//	public String edit(@RequestParam("oid") Long id, Model model) {
//
//		// 驗證：數據實體必須存在
//		Currency currency = currencyService.findById(id);
//		if (currency == null) {
//			return this.error("edit", "warn.resource-not-exist", model);
//		}
//
//		// 模型準備：修改作業的表單。
//
//		CurrencyDataForm form = new CurrencyDataForm();
//		
//		form.setCurrencyOid(currency.getOid());
//		form.setEntity(currency.getEntity());
//		form.setAlphabeticCode(currency.getAlphabeticCode());
//		form.setCurrency(currency.getCurrency());
//		form.setNumericCode(currency.getNumericCode());
//		form.setMinorUnit(currency.getMinorUnit());
//				
//		model.addAttribute("form", form);
//
//		// 返回：修改頁。
//		logger.info("edit() : success = " + form.getCurrency());
//		return "/currency/edit";
//	}
//
//	/**
//	 * 提交修改作業的表單
//	 * 
//	 * @param form 數據的表單。
//	 * @param result 自動驗證的結果。
//	 * @param model 網頁級模型。
//	 * @param session 瀏覽器級模型。
//	 * @return 查詢頁。
//	 */
//	@RequestMapping(path = "/currency/edit", method = RequestMethod.POST)
//	@AcessRightCheck(accessId = "currency_code")
//	@DoOperationLog(accessId = "currency_code", targetObject = "T_CURRENCY", operation = "E")
//	public String update(
//			@Valid @ModelAttribute("form") CurrencyDataForm form, 
//			BindingResult result, 
//			Model model, HttpSession session) {
//		// 操作記錄 - 異動前/後資料
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(form));
//		} catch (JsonProcessingException e) {
//			logger.warn("currency_code write before log error");
//		}
//		
//		// 驗證：輸入值。
//		if (result.hasErrors() == true) {
//			// 返回：修改頁。
//			logger.warn("form error: \n"+result.getAllErrors());
//			return "/currency/edit";
//		}
// 
//		// 自動完成：輸入值。
//		if (form.getMinorUnit() == null) {
//			form.setMinorUnit(0);
//		}
//		// 操作記錄 - 異動前/後資料
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(insertForm(form.getCurrencyOid())));
//		} catch (JsonProcessingException e) {
//			logger.warn("Currency write before log error");
//		}
//		
//		// 主程序：新增作業。
//		Currency currency = new Currency();
//		
//		currency.setOid(form.getCurrencyOid());
//		currency.setEntity(form.getEntity());
//		currency.setCurrency(form.getCurrency());
//		currency.setAlphabeticCode(form.getAlphabeticCode());
//		currency.setNumericCode(form.getNumericCode());
//		currency.setMinorUnit(form.getMinorUnit());
//		
//		try {
//			// 先儲存多對一的參考數據實體。
//			currencyService.update(currency);
//			
//		} catch (ResourceNotFoundException ex) {
//			return this.error("update", ex, model);
//		}
//
//		// 返回：查詢頁。
//		logger.info("update() : success = " + form.getCurrency());
//		// 操作記錄 - 操作結果
//		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
//		return this.redirectQuery(session);
//	}
//
//	/**
//	 * 刪除作業。
//	 * 
//	 * @param id 數據實體的主鍵。
//	 * @param session 瀏覽器級模型。
//	 * @return 查詢頁。
//	 */
//	@RequestMapping(path = "/currency/delete", method = RequestMethod.GET)
//	@AcessRightCheck(accessId = "currency_code")
//	@DoOperationLog(accessId = "currency_code", targetObject = "T_CURRENCY", operation = "D")
//	public String delete(@RequestParam("oid") Long id, HttpSession session) {
//		// 操作記錄 - 異動前/後資料
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(insertForm(id)));
//		} catch (JsonProcessingException e) {
//			logger.warn("Currency write before log error");
//		}
//		// 主程序：刪除作業。
//
//		currencyService.delete(id);
//
//		// 返回：查詢頁。
//		logger.info("delete() : success = " + id);
//		// 操作記錄 - 操作結果
//		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
//		return this.redirectQuery(session);
//	}
//
//	/**
//	 * 上傳作業。
//	 * 
//	 * @param file 用戶上傳的文件內容。
//	 * @param form 查詢用表單。
//	 * @param result 自動驗證結果。
//	 * @param model 網頁級模型。
//	 * @param session 瀏覽器級模型。
//	 * @param pageNumber 第幾頁。
//	 * @return 查詢頁。
//	 */
//	@AcessRightCheck(accessId = "currency_code")
//	@RequestMapping(path = "/currency/upload", method = RequestMethod.POST)
//	@DoOperationLog(accessId = "currency_code", targetObject = "T_CURRENCY", operation = "E")
//	public String upload(
//			@RequestParam("file") MultipartFile[] file,
//			@Valid @ModelAttribute("currencyCriteriaForm") CurrencyCriteriaForm form,
//			BindingResult result,
//			Model model, HttpSession session,
//			@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber) {
//
//		// 驗證：上傳文件是否為空？
//		String context = null;
//		try {
//			byte[] data = file[0].getBytes();
//			if (data.length > 0) {
//				context = new String(data);
//			} else {
//				this.error("upload", "warn.currency.upload.file-empty", model);
//			}
//		} catch (Exception ex) {
//			logger.warn(ex.toString());
//			this.error("upload", "warn.currency.upload.file-not-format", model);
//		}
//		
//		// 轉換：ISO文字格式化為幣別數據集合。
//		List<Currency> newCurrencyList = null;
//		if (context != null) {
//			newCurrencyList = currencyService.parseISOfile(context);
//		}
//		// 操作記錄 - 異動前/後資料
//		try {
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER, JsonUtil.writeValueAsBytes(newCurrencyList));
//			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE , JsonUtil.writeValueAsBytes(currencyService.findAll()));
//		} catch (JsonProcessingException e) {
//			logger.warn("currency_code write before log error");
//		}
//		// 執行：轉換程序。
//		if (newCurrencyList != null && newCurrencyList.size() > 0) {
//			Object[] success = this.rebuild(newCurrencyList);
//			String successMessage = messageSource.getMessage(
//					"warn.currency.upload.success", success, LocaleContextHolder.getLocale());
//			form.reset();	// (理由：數據表重整完成，以前的資料行已經無效)。
//			model.addAttribute(WebKeyConst.ERRORS, successMessage);
//			logger.info("upload : success = " + success[0] + ", " +
//					success[1] + ", " + success[2] + ", " + success[3]);
//		} else {
//			this.error("upload", "warn.currency.upload.file-not-format", model);
//		}
//		
//		// 執行：查詢程序 (成功：數據庫已經更新；文件錯誤：依據原本條件查詢。)。
//		this.query(form, result, model);
//		// 操作記錄 - 操作結果
//		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
//		// 返回：查詢頁。
//		return "/currency/query";
//	}
//	
//	/**
//	 * 跳轉回查詢頁。
//	 * 
//	 * @param session 瀏覽器級模型。
//	 * @return 查詢頁。
//	 */
//	private String redirectQuery(HttpSession session) {
//		
//		// 讀取查詢頁的頁數序號。
//		CurrencyCriteriaForm criteria = (CurrencyCriteriaForm) 
//				session.getAttribute("currencyCriteriaForm");
//		// 返回帶頁數的網址。
//		return "redirect:/currency/query?pageNumber=" + 
//				criteria.getPageNumber();
//	}
//	
//	/**
//	 * 異常程序(1)。
//	 * 
//	 * @param function 字串，功能的名稱。
//	 * @param ex 異常對象。
//	 * @param model 網頁級模型。
//	 * @return 原始頁。
//	 */
//	private String error(String function, Exception ex, Model model) {
//
//		// 轉換：將服務層拋出的異常轉換成文字。
//		String errorMessage = this.getErrorMessage(ex);
//		model.addAttribute(WebKeyConst.ERRORS, errorMessage);
//		logger.warn(function + "() : error = " + errorMessage);
//
//		// 返回：原始頁。
//		if (function.equals("insert")) {
//			return "/currency/add";
//		} else if (function.equals("update")) {
//			return "/currency/edit";
//		} else {
//			return "/currency/query";
//		}
//	}
//
//	/**
//	 * 異常程序(2)。
//	 * 
//	 * @param function 字串，功能的名稱。
//	 * @param i18nCode 多國語言的代碼。
//	 * @param model 網頁級模型。
//	 * @return 查詢頁。
//	 */
//	private String error(String function, String i18nCode, Model model) {
//
//		// 訊息：顯示在網頁的後台訊息區。
//		String errorMessage = messageSource.getMessage(
//				i18nCode, null, LocaleContextHolder.getLocale());
//		model.addAttribute(WebKeyConst.ERRORS, errorMessage);
//
//		// 返回：查詢頁。
//		logger.warn(function + "() : error = " + errorMessage);
//		return "redirect:/currency/query";
//	}
//
//	/**
//	 * 將服務層拋出的異常，轉換成錯誤文字訊息。
//	 * 
//	 * @param ex 異常的對象。
//	 * @return 錯誤文字訊息。
//	 */
//	private String getErrorMessage(Exception ex) {
//
//		String message = ex.getMessage(); // 讀取異常的內部文字。
//		String code = null;
//
//		// oid : 修改作業中主鍵必須存在。
//		if (message.indexOf("oid") >= 0) {
//			code = "warn.resource-not-exist";
//		}
//		// alphabeticCode : unique
//		else if (message.indexOf("alphabeticCode") >= 0) {
//			// "幣別代碼-字母格式"必須唯一！
//			code = "warn.currency.unique.alphabeticCode";
//		}
//		// numericCode : unique
//		else if (message.indexOf("numericCode") >= 0) {
//			// "幣別代碼-字母格式"必須唯一！
//			code = "warn.currency.unique.numericCode";
//		}
//
//		// 返回：錯誤訊息。
//		if (code != null) {
//			return messageSource.getMessage(code, null, 
//					LocaleContextHolder.getLocale());
//		} else {
//			return message;
//		}
//	}
//	
//	/**
//	 * 重整數據表。
//	 * 
//	 * @param newCurencyList 新的數據集合。
//	 * @return 執行結果。[0]總共筆數 [1]新增 [2]修改 [3]刪除。
//	 */
//	private Integer[] rebuild(List<Currency> newCurrencyList) {
//
//		// 返回值的變數。
//		Integer total = newCurrencyList.size();
//		Integer insert = new Integer(0);
//		Integer update = new Integer(0);
//		Integer delete = new Integer(0);
//		
//		// 數據操作用的集合變數。
//		List<Currency> insertList = new ArrayList<Currency>();
//		List<Currency> updateList = new ArrayList<Currency>();
//		List<Currency> deleteList = new ArrayList<Currency>();
//		
//		// 資料處理過程中用的數據地圖。
//		Map<String, Currency> newCurrencyMap = new HashMap<String, Currency>();
//		Map<String, Currency> oldCurrencyMap = new HashMap<String, Currency>();
//		Map<String, Currency> alphaMap = new HashMap<String, Currency>();
//				
//		// 修正新數據集合，和創建新數據地圖。
//		int newSize = newCurrencyList.size();
//		for (int i=0; i<newSize; i++) {
//			Currency newCurrency = newCurrencyList.get(i);
//			String newKey = newCurrency.getNumericCode();
//			if (newCurrencyMap.containsKey(newKey)) {
//				newCurrencyList.remove(i);
//				i -= 1;
//				newSize -= 1;
//			} else {
//				newCurrencyMap.put(newKey, newCurrency);
//			}
//		}
//		logger.info("ISO import lines = " + total);
//		logger.info("ISO new currencies = " + newCurrencyList.size());
//		
//		// 下載舊數據集合，和創建舊數據地圖。
//		List<Currency> oldCurrencyList = currencyService.findAll();
//		for (Currency oldCurrency : oldCurrencyList) {
//			oldCurrencyMap.put(oldCurrency.getNumericCode(), oldCurrency);
//		}
//		logger.info("ISO old currencies = " + oldCurrencyList.size());
//		
//		// 執行：刪除程序，但不要馬上執行數據操作。
//		int oldSize = oldCurrencyList.size();
//		for (int i=0; i < oldSize; i++) {
//			Currency oldCurrency = oldCurrencyList.get(i);
//			String oldKey = oldCurrency.getNumericCode();
//			
//			// 舊數據已經過時了。
//			if (newCurrencyMap.containsKey(oldKey) == false) {
//				deleteList.add(oldCurrency);
//				oldCurrencyList.remove(i);
//				oldCurrencyMap.remove(oldKey);
//				i -= 1;
//				oldSize -= 1;
//			}
//		}
//		
//		// 創建字母代碼的地圖。
//		for (Currency oldCurrency : oldCurrencyList) {
//			alphaMap.put(oldCurrency.getAlphabeticCode(), oldCurrency);
//		}
//		
//		// 執行：新增與修改(也可能出現刪除)程序，但不要馬上執行數據操作。
//		// 本程序中，不再需要舊數據集合了，所以只維護地圖變數就好。
//		newSize = newCurrencyList.size();
//		for (int i=0; i<newSize; i++) {
//			
//			Currency newCurrency = newCurrencyList.get(i);
//			String newKey = newCurrency.getNumericCode();
//			Currency oldCurrency = null;
//			String oldKey = null;
//			
//			if (oldCurrencyMap.containsKey(newKey) == false) {
//				insertList.add(newCurrency);
//			} else {
//				oldCurrency = oldCurrencyMap.get(newKey);
//				oldKey = oldCurrency.getNumericCode();
//				if (oldKey.equals(newKey)) {
//					// 主鍵相同。
//					if (oldCurrency.equals(newCurrency) == false) {
//						// 可是屬性值不同，那就修正。
//						oldCurrency.copyFrom(newCurrency);
//						updateList.add(oldCurrency);						
//					}
//				} else {
//					// 主鍵不相同。
//					
//					// 第2個主鍵。
//					String newAlphaKey = newCurrency.getAlphabeticCode();
//					String oldAlphaKey = oldCurrency.getAlphabeticCode();
//					// 先將舊實體由地圖中移除。
//					oldCurrencyMap.remove(oldKey);
//					alphaMap.remove(oldAlphaKey);
//
//					// 比對：2個主鍵。
//					if (oldCurrencyMap.containsKey(newKey) ||
//						alphaMap.containsKey(newAlphaKey)) {
//
//						// 舊地圖中已經有新主鍵了：先刪除，再新增。
//						deleteList.add(oldCurrency);
//						insertList.add(newCurrency);					
//					} else {
//						
//						// 舊地圖中沒有該主鍵了：修改就行了。
//						oldCurrency.copyFrom(newCurrency);
//						updateList.add(oldCurrency);
//						
//						// 再將主鍵加回到地圖中。
//						oldCurrencyMap.put(newKey, oldCurrency);
//						alphaMap.put(newAlphaKey, oldCurrency);
//					}
//				}
//			}
//		}
//				
//		// 數據操作了。
//		total = newCurrencyList.size();
//		for (Currency currency : deleteList) {
//			currencyService.delete(currency.getOid());
//			delete += 1;
//		}
//		for (Currency currency : updateList) {
//			try {
//				currencyService.update(currency);
//				update += 1;
//			} catch (ResourceNotFoundException ex) {
//				logger.warn(ex.toString());
//			}
//		}
//		for (Currency currency : insertList) {
//			try {
//				currencyService.save(currency);
//				insert += 1;
//			} catch (ResourceAlreadyExistException ex) {
//				logger.warn(ex.toString());
//			}
//		}
//		logger.info("insert = " + insertList.size());
//		logger.info("update = " + updateList.size());
//		logger.info("delete = " + deleteList.size());
//		
//		// 返回：執行結果。
//		return new Integer[] { total, insert, update, delete };
//	}
//	
//	private CurrencyDataForm insertForm(Long currencyId) {
//		Currency currency = currencyService.findById(currencyId);
//		if (currency == null) {
//			return null;
//		}
//
//		// 模型準備：修改作業的表單。
//		
//		CurrencyDataForm form = new CurrencyDataForm();
//		return form;
//	}
//}