/*
 * @(#)invSysTransConfigController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/invSys 交易參數設定 Controller(控制器)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.00,  /08/01, Milo Gao
 *   2) 接手開發。
 * v1.01,  /12/26, Bob Shih
 *   1) 修正資料未正確儲存。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.SysCode;
import com.invSet.emv.invSys.invSysserver.core.bean.invSysTransConfig;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.service.invSysTransConfigService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/invSys 交易參數設定 Controller(控制器)。
 * 
 * @author   LeonKao, MiloGao
 *
 */
@SessionAttributes({ "invSysTransConfigForm" })
@Controller
public class invSysTransConfigController {

	private static final Logger logger = LoggerFactory.getLogger(invSysTransConfigController.class);
	
	/**
	 * 用戶訊息 Resource。
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * <交易參數設定> 服務 (已實作)
	 */
	@Autowired
	private invSysTransConfigService configService;

	/**
	 * <系統代碼參數> 服務 (已實作)
	 */
	@Autowired
	private SysCodeService cardSchemaService;
	
	/**
	 * 服務調用之前的初始化程序。
	 * 
	 * @param model 網頁級資料模型。
	 * @param session 瀏覽器級資料模型。
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {
		
		// 檢查(1): 網頁綁定用表單。
		invSysTransConfigForm form = (invSysTransConfigForm)
				session.getAttribute("invSysTransConfigForm");
		
		if (form == null) {
			form = new invSysTransConfigForm();
			logger.info("@SessionAttributes(invSysTransConfigForm)");
		}
		
		if (model.containsAttribute("invSysTransConfigForm") == false) {
			model.addAttribute("invSysTransConfigForm", form);
			logger.info("@ModelAttribute(invSysTransConfigForm)");
			
			// 邏輯：卡組織不容易變，所以初始化時載入一次即可。
			form.setCardSchemaList(cardSchemaService.
					findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));
			logger.info("card-schema = " + form.getCardSchemaList().size());
			
			// 邏輯：交易通道的選項(文字)。
			List<String> channelEnableLabel = new ArrayList<String>();
			channelEnableLabel.add(messageSource.getMessage(
					"ui.invSys-trans-config.channel.disable", null, 
					LocaleContextHolder.getLocale()));
			channelEnableLabel.add(messageSource.getMessage(
					"ui.invSys-trans-config.channel.enable", null, 
					LocaleContextHolder.getLocale()));
			form.setChannelEnableLabel(channelEnableLabel);
		}
	}
	
	/**
	 * 修改作業 (1/2) - 訪問
	 * 
	 * @param form 網頁綁定用表單。
	 * @param model 資料模型。
	 * @return 修改作業的網頁路徑。
	 */
	@RequestMapping(path = "/invSys_trans_config/edit", method = RequestMethod.GET)
    @AcessRightCheck(accessId = "trans_argument")
	public String showEditForm(
			@ModelAttribute("invSysTransConfigForm") invSysTransConfigForm form,
			Model model) {

		// 1. 準備：資料模型。
		
		// (1) 取回：<卡組織> 集合。
		List<SysCode> cardSchemaList = form.getCardSchemaList();
		// (2) 查詢：當前 <交易參數設定> 集合。
		List<invSysTransConfig> currentConfigs = configService.findAll();
		// (3) 掃一遍，<交易參數設定>
		List<invSysTransConfig> entities = new ArrayList<invSysTransConfig>();
		for (SysCode cardSchema : cardSchemaList) {
			// 1. 比對："code_id" 欄位，V/M/J/C。
			invSysTransConfig entity = null;
			for (invSysTransConfig config : currentConfigs) {
				if (cardSchema.getCodeId().equals(config.getCardScheme())) {
					entity = config;
					break;
				}
			}
			// 2. 新卡組織：配置預設值。
			if (entity == null) {
				entity = new invSysTransConfig(cardSchema.getCodeId());
				try {
					configService.save(entity);
				} catch (Exception ex) {
					logger.warn(ex.toString());
				}
			}
			// 3. 加到 buffer 裡，暫時擺著。
			entities.add(entity);
		}
		// (4) "查詢結果" 加到資料模型裡面去。
		form.setEntities(entities);
		logger.info("entities = " + entities.size());
		
		// 2. 返回：修改作業的網頁路徑。
		logger.info("visit : showEditForm(count = " + entities.size() + ")");
		return "/invSys_trans_config/edit";
	}

	/**
	 * 修改作業 (2/2) - 提交。 
	 * 
	 * @param form 網頁綁定用表單。
	 * @param model 資料模型。
	 * @return 修改作業的網頁路徑。
	 */
	@RequestMapping(path = "/invSys_trans_config/edit", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "trans_argument")
	@DoOperationLog(accessId = "trans_argument", operation = "E" , targetObject = "T_invSys_TRANS_CONFIG")
	public String edit(
			@ModelAttribute("invSysTransConfigForm") invSysTransConfigForm form, 
			Model model) {
		// 操作記錄 - 異動前/後資料
		try {
			//需要將list的資料轉換成map
			List<invSysTransConfig> data=configService.findAll();
			HashMap<String, List<invSysTransConfig>> dataMap=new HashMap<>();
			dataMap.put("entities", data);
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(dataMap));
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("invSysTransConfig write before log error");
		}
		
		
		
		// 1. 更新：設定值。
		for (invSysTransConfig config : form.getEntities()) {
			try {
				configService.update(config);
			} catch (ResourceNotFoundException ex) {
				model.addAttribute(WebKeyConst.ERRORS, messageSource.getMessage(
						"warn.resource-not-exist", null, 
						LocaleContextHolder.getLocale()));
				logger.warn("error : edit(" + config.getOid() + ")");
				return "/invSys_trans_config/edit";
			}
		}
		
		// 2. 返回：查詢作業路徑。
		logger.info("success : edit(count = " + form.getEntities().size() + ")");
		model.addAttribute(WebKeyConst.SUCCESS_MSG, messageSource.getMessage(
				"ui.successfully.modified", null, 
				LocaleContextHolder.getLocale()));
		
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		return "/invSys_trans_config/edit";
	}
}
