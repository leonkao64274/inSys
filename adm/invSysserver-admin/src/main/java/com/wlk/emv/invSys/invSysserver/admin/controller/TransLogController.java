/*
 * @(#)TransLogController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 *
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組  - invSys 驗證交易記錄 Controller(控制器)。
 *
 * Modify History:
 * v1.00,  /08/23 LeonKao
 *   1) First release
 * v1.01,  /11/09 StevenKao
 *   2) 修改query 新增兩個搜尋條件
 * v1.02,    /06/03, Leon Kao
 *   1) 修正 "驗證結果" 查詢條件下拉選單中文 i18n 問題
 *   2) "裝置通道" 下拉選單增加 3RI 選項
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRecord;
import com.invSet.emv.invSys.invSysserver.admin.bean.VinvSysTransMerge;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.service.invSysRecordService;
import com.invSet.emv.invSys.invSysserver.admin.service.VinvSysTransMergeService;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.DropMenuUtil;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRecord;
//import com.invSet.emv.invSys.invSysserver.core.base.bean.VinvSysTransMerge;
//import com.invSet.emv.invSys.invSysserver.core.base.service.invSysRecordService;
//import com.invSet.emv.invSys.invSysserver.core.base.service.VinvSysTransMergeService;
import com.invSet.emv.invSys.invSysserver.core.bean.invSysTrans;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.service.invSysTransService;
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組 - invSys 驗證交易記錄 Controller(控制器)。
 *
 * @author StevenKao
 *
 */
@SessionAttributes({ "transLogForm" })
@Controller
public class TransLogController {

	private static final Logger logger = LoggerFactory.getLogger(TransLogController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AppProperties appProperties;

	/**
	 * Session Scope 的用戶登錄訊息。
	 */
	@Autowired
	private IndexLoginSession indexLoginSession;

	/**
	 * 卡組織管理服務
	   放 卡組織 卡別 交易驗證
	 */
	@Autowired
	private SysCodeService sysCodeService;

	@Autowired
	private invSysTransService invSysTransService;

	@Autowired
    private VinvSysTransMergeService vinvSysTransMergeService;

	@Autowired
    private invSysRecordService invSysRecordService;
	
	
	
	
	
	
	
	
	
	/**
	 * 準備 "模型的組態" 信息
	 *
	 * @param model UI Model, 用於存放返回網頁顯示的資料
	 */
	private void prepareConfigModel(Model model) {
		// 取得語言國別代碼
		Locale locale = this.indexLoginSession.getLocale();

		// (2): 卡組織
		model.addAttribute("cardSchemeConfigModel",
				sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));
		// (3): 訊息版本
		model.addAttribute(
                "messageVersionConfigModel",
                Arrays.asList(
                        new DropMenuUtil("1.0.2", "1.0.2"),
                        new DropMenuUtil("2.1.0", "2.1.0")
                )
        );

		// (4): 驗證結果
		model.addAttribute(
                "transStatusConfigModel",
                DropMenuUtil.getOptions(
                        locale,
                        sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TRANS_STATUS)
                )
        );

		// (5): 裝置通道
		List<DropMenuUtil> deviceChanneConfigModel = Arrays.asList(
                new DropMenuUtil("app", "01"),
				new DropMenuUtil("Browser", "02"),
                new DropMenuUtil("3RI", "03")
        );
		model.addAttribute("deviceChannelConfigModel", deviceChanneConfigModel);

		// (6): Message Category
		List<DropMenuUtil> messagecategoryModel = Arrays.asList(new DropMenuUtil("01-PA", "01"),
				new DropMenuUtil("02-NPA", "02"));
		model.addAttribute("messagecategoryModel", messagecategoryModel);

	}

	/**
	 * 建立 "查詢條件表單" Model, 供 Spring 綁定 "查詢條件表單" 提交的數據
	 *
	 * @return "查詢條件表單" Model
	 */
	@ModelAttribute("transLogForm")
	public TransLogCriteriaForm initialModel() {
		return new TransLogCriteriaForm();
	}
	
	
	
	
	/**
	 * 查詢作業表單
	 *
	 * @param form   查詢條件表單提交資料綁定物件, 此為 session scope 變數，命名時勿與其他功能使用的名稱衝突
	 * @param result 用戶提交表單資料驗證結果
	 * @param model  UI Model, 用於存放返回網頁顯示的資料
     *
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	@RequestMapping(value = "/invSys_trans/query")
	@AcessRightCheck(accessId = "trans_query")
	@DoOperationLog(accessId = "trans_query", operation = "Q", targetObject = "T_invSys_TRANS")
	public String query(@Valid @ModelAttribute("transLogForm") TransLogCriteriaForm form,
            BindingResult result,
            Model model) {

		this.prepareConfigModel(model);
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY, JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("trans_query query json query error");
		}

		// 1. 檢核表單提交資料
		if (result.hasErrors()) {
			return "/transaction/query";
		}
		logger.debug("[pageNumber]:" + form.getPageNumber());

		// 2.金額去掉小數點
		String purchaseAmount = form.getPurchaseAmount();
		if (purchaseAmount != null) {
			purchaseAmount = purchaseAmount.replace(".", "");
		}
		
		if(StringUtils.isEmpty(form.getEndDate())) form.setEndDate(DateUtil.getCurrentDate());
		if(StringUtils.isEmpty(form.getStartDate())) form.setStartDate(DateUtil.getCurrentDate());
		
		
		

		// 3 取得查询结果，包括1.0和2.0
		Page<VinvSysTransMerge> queryResult = vinvSysTransMergeService.findByCriteria(
		        DateUtil.revertDate(form.getStartDate()),
                DateUtil.revertDate(form.getEndDate()),
                form.getCriteriaCardScheme(),
                form.getMessageVersion(),
                form.getAcsTransID(),
                form.getinvSysServerTransID(),
                form.getTransStatus(),
                form.getAcctId(),
                form.getAcctNumberPrefix(),
                form.getAcctNumberPostfix(),
                form.getAcquirerMerchantID(),
                form.getDeviceChannel(),
                form.getMessageCategory(),
                purchaseAmount,
                form.getIntegratorRequestorId(),
                form.getinvSysRequestorOrderId(),
                new PageRequest(
                        form.getPageNumber(),
                        appProperties.getPageSize(),
                        new Sort(
                                new Order(Direction.DESC, "createDate"),
                                new Order(Direction.DESC, "createTime"),
                                new Order(Direction.ASC, "cardScheme")
                        )
                )
        );

		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT, true);
		// 4. 返回查詢結果條件
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/transaction/query";
	}
	
	
	
	
	
	

	@RequestMapping(value = "/invSys_trans/detail", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "trans_query")
	public String showDetailForm(@RequestParam String oid, Model model) {

		if (StringUtils.isEmpty(oid)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 1.取回數據實體。
        VinvSysTransMerge vinvSysTransMerge = new VinvSysTransMerge();
        try {
            vinvSysTransMerge = vinvSysTransMergeService.findByinvSysServerTransId(oid);
            if (vinvSysTransMerge == null) {
                // 設定錯誤訊息，返回原查詢作業畫面顯示
                model.addAttribute(
                        WebKeyConst.ERRORS, messageSource.getMessage("warn.data-read-exception-please-re-confirm",
                                null,LocaleContextHolder.getLocale()));
                return recallQuery(model);
            }
        }catch (Exception e) {
            logger.info(e.getMessage());
        }
		String version = vinvSysTransMerge.getMessageVersion();
        String invSysServerTransId = oid;
        if("1.0.2".equals(version)){
            //查询1.0交易详细信息
            try{
                if (!invSysServerTransId.isEmpty()){
                    invSysRecord invSysRecord = invSysRecordService.findByinvSysTransID(invSysServerTransId);
                    // 2.建立表單物件，供編輯畫面表單綁定使用
                   model.addAttribute("form", invSysRecord);
                    // 3.返回 "明細" 網頁的地址。
                    return "/transaction/detail-record";
                }
            }catch (Exception e){
                logger.info(e.getMessage());
                model.addAttribute(
                        WebKeyConst.ERRORS, messageSource.getMessage( "invSys.record.invSysTransId.isEmpty",
                                null,LocaleContextHolder.getLocale()));
                return recallQuery(model);
            }
        }else{
            //查询2.0交易详细信息
            if (!invSysServerTransId.isEmpty()){
                invSysTrans invSysTrans = invSysTransService.findByinvSysServerTransId(invSysServerTransId);
                if (invSysTrans == null){
                    model.addAttribute(
                            WebKeyConst.ERRORS,
                            messageSource.getMessage("invSys.trans.invSysServerTransId.isEmpty",
                                    null,LocaleContextHolder.getLocale()));
                    return recallQuery(model);
                }
                // 2.建立表單物件，供編輯畫面表單綁定使用
                model.addAttribute("form", invSysTrans);
                // 3.返回 "明細" 網頁的地址。
                return "/transaction/detail";
            }
        }

		logger.info("vinvSysTransMerge.getAcsTransId()" + vinvSysTransMerge.getAcsTransId());
		logger.info("vinvSysTransMerge.getOid()" + vinvSysTransMerge.getOid());
		logger.info("vinvSysTransMerge.getinvSysServerTransId()" + vinvSysTransMerge.getinvSysServerTransId());

//		// 2.建立表單物件，供編輯畫面表單綁定使用
//		model.addAttribute("form", vinvSysTransMerge);
//
//		// 3.返回 "明細" 網頁的地址。
		return "/transaction/detail";
	}

	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 *
	 * @param model UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 *
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		// 準備 "模型的組態" 信息
		this.prepareConfigModel(model);

		// 從 session attribute 取得查詢表單物件
		TransLogCriteriaForm form = (TransLogCriteriaForm) model.asMap().get("transLogForm");

		// 金額去掉小數點
		String purchaseAmount = form.getPurchaseAmount();
		if (purchaseAmount != null) {
			purchaseAmount = purchaseAmount.replace(".", "");
		}

        // 3 取得查询结果，包括1.0和2.0
        Page<VinvSysTransMerge> queryResult = vinvSysTransMergeService.findByCriteria(
                DateUtil.revertDate(form.getStartDate()),
                DateUtil.revertDate(form.getEndDate()),
                form.getCriteriaCardScheme(),
                form.getMessageVersion(),
                form.getAcsTransID(),
                form.getinvSysServerTransID(),
                form.getTransStatus(),
                form.getAcctId(),
                form.getAcctNumberPrefix(),
                form.getAcctNumberPostfix(),
                form.getAcquirerMerchantID(),
                form.getDeviceChannel(),
                form.getMessageCategory(),
                purchaseAmount,
                form.getIntegratorRequestorId(),
                form.getinvSysRequestorOrderId(),
                new PageRequest(
                        form.getPageNumber(),
                        appProperties.getPageSize(),
                        new Sort(
                                new Order(Direction.DESC, "createDate"),
                                new Order(Direction.DESC, "createTime"),
                                new Order(Direction.ASC, "cardScheme")
                        )
                )
        );

		// 返回查詢結果條件
		model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
		return "/transaction/query";
	}
}
