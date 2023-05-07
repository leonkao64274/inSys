/*
 * @(#)AdminGroupController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/目錄服務器連線設定 Controller(控制器)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.00,  /07/26, Steven Kao
 *   2) 接手開發。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import com.invSet.emv.invSys.invSysserver.core.bean.DirectoryServer;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.DirectoryServerService;
import com.invSet.emv.invSys.invSysserver.core.service.SysCodeService;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台系統管理模組/目錄服務器連線設定 Controller(控制器)。 (1)
 * directoryServerCriteriaForm "目錄服務器連線設定" 作業用的查詢條件 Form。
 * 
 * @author   LeonKao, MiloGao
 */
@SessionAttributes("directoryServerCriteriaForm")
@Controller
public class DirectoryServerController {

	private static final Logger logger = LoggerFactory.getLogger(DirectoryServerController.class);

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
	 * 系統代碼業務服務操作
	 */
	@Autowired
	private SysCodeService sysCodeService;
	/**
	 * 目錄服務器連線設定操作
	 */
	@Autowired
	private DirectoryServerService directoryServerService;

	/**
	 * 派遣服務請求前的前置程序。
	 * 
	 * @param model 網頁級資料模型
	 * @param session 瀏覽器級資料模型
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {
		// 1.放入下拉選框資料
		// 1.1: 卡組織
		model.addAttribute("cardSchemeConfigModel",
				sysCodeService.findByCodeTypeOrderByCodeOrder(SysCodeService.TYPE_CARD_SCHEME));

		DirectoryServerCriteriaForm form = (DirectoryServerCriteriaForm) session
				.getAttribute("directoryServerCriteriaForm");
		// 2. 檢查 "查詢作業" 用的 Form。
		if (form == null) {
			form = new DirectoryServerCriteriaForm();
		}
		if (model.containsAttribute("directoryServerCriteriaForm") == false) {
			model.addAttribute("directoryServerCriteriaForm", form);
		}
	}

	/**
	 * 查詢作業(1/1) - 功能群組設定 (管理)
	 * 
	 * @param form 查詢作業用的 Form。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
     * 
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/query")
    @AcessRightCheck(accessId = "directory_server")
	@DoOperationLog(accessId = "directory_server", operation = "Q" , targetObject = "T_DIRECTORY_SERVER")
	public String query(@Valid @ModelAttribute("directoryServerCriteriaForm") DirectoryServerCriteriaForm form,
			BindingResult result,
			Model model) {
		
		// 操作記錄 - 查詢條件
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server query json query error");
		}
		// 1. 檢查：查詢條件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors()) {
			return "/directory_server/query";
		}
		DirectoryServer ds = new DirectoryServer();
		ds.setCardScheme(form.getCardScheme());

		// 2. 執行：查詢 "功能群組設定" 的分頁集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT,
				directoryServerService.findByCriteria(ds, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 3. 返回：查詢作業的網頁路徑。
		return "/directory_server/query";
	}

	/**
	 * 新增作業(1/2) - 功能群組設定 (訪問)
	 * 
	 * @param model 網頁級資料模型。
	 * @return 新增作業的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/add", method = RequestMethod.GET)
    @AcessRightCheck(accessId = "directory_server")
	public String showAddForm(Model model) {

		// 1. 準備資料模型：(1)即將新增用的實體
		model.addAttribute("form", new DirectoryServer());

		// 2. 返回：新增作業的網頁路徑。
		return "/directory_server/add";
	}

	/**
	 * 新增作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 即將被新增的 Entity。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 新增作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/add", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "directory_server")
	@DoOperationLog(accessId = "directory_server", operation = "A" , targetObject = "T_DIRECTORY_SERVER")
	public String add(@Valid @ModelAttribute("form") DirectoryServer form, 
			BindingResult result, 
			Model model) {
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server add json after error");
		}
		// 1. 檢查：網頁控件輸入值，在自動驗證機制處理後是否有錯誤。
        if (form.getProxyEnabled() != null && "1".equals(form.getProxyEnabled())) {
            if (StringUtils.isEmpty(form.getProxyHost())) {
                result.rejectValue("proxyHost", "warn.not-null");
            }
            if (form.getProxyPort() == null) {
                result.rejectValue("proxyPort", "warn.not-null");
            }
        }
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/directory_server/add";
		}

		// 2.判斷卡組織是否重複
		if (directoryServerService.findByCardScheme(form.getCardScheme()) != null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/directory_server/add";
		}

		// 4. 將 "新增內容" 訊息，儲存。
		try {
			directoryServerService.save(form);
		} catch (ResourceAlreadyExistException e) {
			model.addAttribute(WebKeyConst.ERRORS,
				messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/directory_server/add";
		}
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		return recallQuery(model);
	}

	/**
	 * 修改作業(1/2) - 功能群組設定 (訪問)
	 * 
	 * @param id 功能群組設定 Entity 的主鍵值。
	 * @param model 網頁級資料模型。
	 * @return 查詢作業(失敗) 或 修改作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/edit", method = RequestMethod.GET)
    @AcessRightCheck(accessId = "directory_server")
	public String showEditForm(@RequestParam("oid") Long id,
			Model model) {
		
		if (StringUtils.isEmpty(id)) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 1. 讀取實體。
		DirectoryServer form = directoryServerService.findById(id);
		if (form == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備資料模型：(1)即將修改用的實體 (2)初始化
		model.addAttribute("form", form);

		// 3. 返回：新增作業的網頁路徑。
		return "/directory_server/edit";
	}

	/**
	 * 修改作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 修改作業用的設定 Form。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 修改作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/edit", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "directory_server")
	@DoOperationLog(accessId = "directory_server", operation = "E" , targetObject = "T_DIRECTORY_SERVER")
	public String edit(@Valid @ModelAttribute("form") DirectoryServer form, BindingResult result, Model model) {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server edit json after error");
		}
		// 1. 檢查：網頁控件輸入值，在自動驗證機制處理後是否有錯誤。
        if (form.getProxyEnabled() != null && "1".equals(form.getProxyEnabled())) {
            if (StringUtils.isEmpty(form.getProxyHost())) {
                result.rejectValue("proxyHost", "warn.not-null");
            }
            if (form.getProxyPort() == null) {
                result.rejectValue("proxyPort", "warn.not-null");
            }
        }
        
		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/directory_server/edit";
		}

		// 3.把修改資料放進修改實體
		DirectoryServer ds = directoryServerService.findById(form.getOid());
		
		if (null == ds) {
            // 設定錯誤訊息，返回原編輯作業畫面顯示
            model.addAttribute(WebKeyConst.ERRORS, 
                    messageSource.getMessage("warn.oid-not-assigned", 
                            null, LocaleContextHolder.getLocale()));
            return recallQuery(model);
        }
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(ds));
		} catch (JsonProcessingException e) {
			logger.warn("DirectoryServer write before log error");
		}
		
		ds.setCardScheme(form.getCardScheme());
		ds.setAreqUrl(form.getAreqUrl());
		ds.setBackupAreqUrl(form.getBackupAreqUrl());
		ds.setMessageVersion(form.getMessageVersion());
		ds.setRetryLimits(form.getRetryLimits());
		ds.setRetryInterval(form.getRetryInterval());
        ds.setReadTimeout(form.getReadTimeout());
        ds.setPreqUrl(form.getPreqUrl());
        ds.setBackupPreqUrl(form.getBackupPreqUrl());
        ds.setRreqUrl(form.getRreqUrl());
        ds.setBackupRreqUrl(form.getBackupRreqUrl());
        ds.setinvSysServerOperatorID(form.getinvSysServerOperatorID());
        ds.setinvSysServerRefNumber(form.getinvSysServerRefNumber());
        ds.setProxyEnabled(form.getProxyEnabled());
        ds.setProxyHost(form.getProxyHost());
        ds.setProxyPort(form.getProxyPort());

		// 4. 資料操作：修改 "目錄服務器連線設定" 實體。
		try {
			directoryServerService.update(ds);
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/directory_server/edit";
		}	
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 5. 返回：查詢作業的網頁路徑。
		return recallQuery(model);
	}

	/**
	 * 刪除作業(1/1) - 功能群組設定
	 * 
	 * @param id 實體主鍵
	 * @param model 網頁級的資料模型
	 * @return 查詢作業的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/delete")
    @AcessRightCheck(accessId = "directory_server")
	@DoOperationLog(accessId = "directory_server", operation = "D" , targetObject = "T_DIRECTORY_SERVER")
	public String delete(@RequestParam("oid") Long id, Model model) {
		
		if (StringUtils.isEmpty(id)) {
            // 設定錯誤訊息，返回原編輯作業畫面顯示
            model.addAttribute(WebKeyConst.ERRORS, 
                    messageSource.getMessage("warn.oid-not-assigned", 
                            null, LocaleContextHolder.getLocale()));
            return recallQuery(model);
        }
		// 1. 檢查：企業規則：
		DirectoryServer entity = directoryServerService.findById(id);
		// 操作記錄
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE,JsonUtil.writeValueAsBytes(entity));
		} catch (JsonProcessingException e) {
			logger.warn("directory_server delete json before error");
		}
		
		if (entity == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 刪除實體
		directoryServerService.delete(id);
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 3. 返回查詢作業的網頁路徑。
		return recallQuery(model);
	}

	/**
	 * 明細作業(1/2) - 功能群組設定(顯示)
	 * 
	 * @param id 數據實體的主鍵。
	 * @param model 網頁級資料模型
	 * @return 明細作業的網頁路徑。
	 */
	@RequestMapping(path = "/directory_server/detail")
    @AcessRightCheck(accessId = "directory_server")
	public String detail(@RequestParam("oid") Long id, Model model) {
		if (StringUtils.isEmpty(id)) {
			// 設定錯誤訊息，返回原編輯作業畫面顯示
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.oid-not-assigned", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}
		// 1. 讀取實體。
		DirectoryServer form = directoryServerService.findById(id);
		if (form == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備資料模型：(1)即將顯示用的實體 
		model.addAttribute("form", form);

		// 3. 返回：新增作業的網頁路徑。
		return "/directory_server/detail";
	}
	/**
	 * 重新查詢<br>
	 * 供新增、修改及刪除作業於系統處理完畢後，依據查詢條件表單重新查詢取得相關資料.
	 * 
	 * @param model
	 *            UI Model, 供系統取得查詢條件表單物件參考並存放返回網頁顯示的資料
	 * @return 查詢條件及查詢結果顯示頁面
	 */
	private String recallQuery(Model model) {
		
		DirectoryServerCriteriaForm form=(DirectoryServerCriteriaForm) model.asMap().get("directoryServerCriteriaForm");
		DirectoryServer ds = new DirectoryServer();
		ds.setCardScheme(form.getCardScheme());

		model.addAttribute(WebKeyConst.QUERY_RESULT,
				directoryServerService.findByCriteria(ds, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));

		return "/directory_server/query";
	}
	
	
	
}
