/*
 * @(#)CardRangeDataController.java
 *
 * Copyright (c)     invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 "卡 BIN 範圍查詢" 控制器類別
 *
 * Modify History:
 * v1.00,    /05/18, Leon Kao
 *   1) First release
 *   
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.CardRangeData;
import com.invSet.emv.invSys.invSysserver.core.service.CardRangeDataService;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "卡 BIN 範圍查詢" 控制器類別
 * 
 * @author   LeonKao
 */
@Controller
@SessionAttributes("cardRangeDataCriteriaForm")
public class CardRangeDataController {
    
	/**
	 * Logging utility
	 */
	private static final Logger logger = LoggerFactory.getLogger(CardRangeDataController.class);

    @Autowired
    private CardRangeDataService cardRangeDataService;
    
    @Autowired
    private SysCodeService sysCodeService;
    
	@Autowired
	private AppProperties appProperties;
    
	/**
	 * 準備 "模型的組態" 信息
	 * 
	 * @param model UI Model, 用於存放返回網頁顯示的資料
	 */
	private void prepareConfigModel(Model model) {
        
        // 下拉選單 - 卡組織
		model.addAttribute(
                "cardSchemeConfigModel", 
                sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME)
        );
        
	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 * 
	 * @return 查詢條件表單 Model 物件
	 */
	@ModelAttribute("cardRangeDataCriteriaForm")
	public CardRangeDataCriteriaForm initialModel() {
        
		return new CardRangeDataCriteriaForm();
	}
    
    /**
     * 查詢作業
     * 
     * @param form   查詢條件表單提交資料綁定物件, 此為 session scope 變數，命名時勿與其他功能使用的名稱衝突
     * @param result 用戶提交表單資料驗證結果
     * @param model  UI Model, 用於存放返回網頁顯示的資料
     * 
     * @return 查詢條件及查詢結果顯示頁面
     */
	@RequestMapping(value = "/card_range_data/query")
	@AcessRightCheck(accessId = "card_range_data")
	@DoOperationLog(accessId = "card_range_data", operation = "Q", targetObject = "T_CARD_RANGE_DATA")
	public String query(@Valid @ModelAttribute("cardRangeDataCriteriaForm") CardRangeDataCriteriaForm form, BindingResult result, Model model) {
        
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException ex) {
			logger.warn("Failed to log CardDataRange criterial!", ex);
		}
        
		// 1. 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 2. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/card_range_data/query";
		}
		logger.debug("[pageNumber]:" + form.getPageNumber());

		// 3. 依據查詢條件進行查詢
		Page<CardRangeData> queryResult = cardRangeDataService.findByCriterial(
                form.getCriteriaCardScheme(), 
                form.getCriteriaAcctNumber(),
                new PageRequest(form.getPageNumber(), appProperties.getPageSize())
        );
        
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
        
		// 4. 返回查詢結果頁面
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/card_range_data/query";
    }
    
}
