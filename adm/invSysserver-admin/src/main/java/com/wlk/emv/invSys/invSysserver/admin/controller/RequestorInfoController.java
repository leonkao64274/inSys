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
import com.invSet.emv.invSys.invSysserver.admin.bean.RequestorInfo;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.service.RequestorInfoService;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;



/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 "RequestorInfo" 控制器類別
 * 
 * @author LeonKao
 */

@SessionAttributes("RequestortInfoCriteriaForm")
@Controller
public class RequestorInfoController {
	private static final Logger logger = LoggerFactory.getLogger(RequestorInfoController.class);

	/**
	 * used for i18n
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * Customer Properties
	 */
	@Autowired
	private AppProperties appProperties;

	/**
	 * RequestorInfoService
	 */
	@Autowired
	private RequestorInfoService requestorInfoService;
	
	
	/**
	 * 派遣服務請求前的前置程序。
	 * 
	 * @param model 網頁級資料模型
	 * @param session 瀏覽器級資料模型
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {

		RequestorInfoCriteriaForm form = (RequestorInfoCriteriaForm) session
				.getAttribute("requestorInfoCriteriaForm");
		// 2. 檢查 "查詢作業" 用的 Form。
		if (form == null) {
			form = new RequestorInfoCriteriaForm();
		}
		if (model.containsAttribute("requestorInfoCriteriaForm") == false) {
			model.addAttribute("requestorInfoCriteriaForm", form);
		}
	}
	
	
	/** 
	 * 查詢作業(1/1) - 管理
	 * 
	 * @param form used for query 
	 * @param result the result of validation
	 * @param model instance of Model
     * @param pageNumber page number。
	 * @return the JSP path for query
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(path = "/requestor_info/query")
	@AcessRightCheck(accessId = "requestor_info")
	@DoOperationLog(accessId = "requestor_info", operation = "Q", targetObject = "T_REQUESTOR_INFO")
	public String query(@Valid @ModelAttribute("RequestorInfoCriteriaForm") RequestorInfoCriteriaForm form,
			BindingResult result,
			Model model) {

		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		}catch(JsonProcessingException r) {
			logger.warn("requestor_info query json query error");
		}
		// 1. 檢查：查詢條件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors()) {
			return "/requestor_info/query";
		}
		RequestorInfo requestorInfo = new RequestorInfo();
		requestorInfo.setRequestorId(form.getRequestorId());
		requestorInfo.setRequestorName(form.getRequestorName());
		

		// 2. 執行：查詢 "功能群組設定" 的分頁集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT,
				requestorInfoService.findByCriteria(requestorInfo, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 3. 返回：查詢作業的網頁路徑。
		return "/requestor_info/query";
	}
	
	
	/**
	 * 新增作業(1/2) - 管理
	 * @param model
	 *            資料模型
	 * @return 新增作業的網頁路徑。
	 */
	@RequestMapping(path = "/requestor_info/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "requestor_info")
	public String showAddForm(Model model) {

		// 1. 返回：新增作業的網頁路徑。
		model.addAttribute("form", new RequestorInfo());
		
		return "/requestor_info/add";
	}
	
	
	/**
	 * 新增作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 即將被新增的 Entity。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 新增作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/requestor_info/add", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "requestor_info")
	@DoOperationLog(accessId = "requestor_info", operation = "A" , targetObject = "T_I_REQUESTOR")
	public String add(@Valid @ModelAttribute("form") RequestorInfo form, 
			BindingResult result, 
			Model model) {
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.debug("requestor_info add json after error");
			logger.warn("requestor_info add json after error");
		}

		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/requestor_info/add";
		}

		// 判斷商店代號是否重複
		if ( requestorInfoService.findByRequestorId(form.getRequestorId()) != null) {
			logger.debug("same");
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/requestor_info/add";
		}

		// 將 "新增內容" 訊息，儲存。
		try {
			requestorInfoService.save(form);
		} catch (ResourceAlreadyExistException e) {
			logger.debug("can not save");
			model.addAttribute(WebKeyConst.ERRORS,
				messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/requestor_info/add";
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
	@RequestMapping(path = "/requestor_info/edit", method = RequestMethod.GET)
    @AcessRightCheck(accessId = "requestor_info")
	public String showEditForm(@RequestParam("oid") Long id,
			Model model) {
		
		
		//這段怪怪的，ID都已經點下去了不會傳空值
		if (StringUtils.isEmpty(id)) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 1. 讀取實體。
		//怕接錯DB報的ERROR(其實不會，因為欄位不一樣會報錯)
		//真正這樣寫的原因是因為怕有人同時把欄位刪掉了，所以你剛剛找到了，你就找不到了
		//更好的寫法是把下面兩行寫成一個動作，弄成transaction
		RequestorInfo form = requestorInfoService.findById(id);
		if (form == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備資料模型：(1)即將修改用的實體 (2)初始化
		model.addAttribute("form", form);

		// 3. 返回：新增作業的網頁路徑。
		return "/requestor_info/edit";
	}
 
	/**
	 * 修改作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 修改作業用的設定 Form。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 修改作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/requestor_info/edit", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "requestor_info")
	@DoOperationLog(accessId = "requestor_info", operation = "E" , targetObject = "T_I_REQUESTOR")
	public String edit(@Valid @ModelAttribute("form") RequestorInfo form, BindingResult result, Model model) {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("requestor_info edit json after error");
		}

		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/requestor_info/edit";
		}

		// 3.把修改資料放進修改實體
		RequestorInfo requestorInfo = requestorInfoService.findById(form.getOid());
		
		if (null == requestorInfo) {
            // 設定錯誤訊息，返回原編輯作業畫面顯示
            model.addAttribute(WebKeyConst.ERRORS, 
                    messageSource.getMessage("warn.oid-not-assigned", 
                            null, LocaleContextHolder.getLocale()));
            return recallQuery(model);
        }
		
		
///////////////////改改看直接存form		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_BEFORE, JsonUtil.writeValueAsBytes(requestorInfo));
		} catch (JsonProcessingException e) {
			logger.warn("RequestorInfo write before log error");
		}
		
		requestorInfo.setRequestorId(form.getRequestorId());
		requestorInfo.setRequestorPassword(form.getRequestorPassword());
		requestorInfo.setEnable(form.getEnable());
		requestorInfo.setEnable_ae(form.getEnable_ae());
		requestorInfo.setEnableCup(form.getEnableCup());
		requestorInfo.setEnableDiscover(form.getEnableDiscover());
		requestorInfo.setEnableJCB(form.getEnableJCB());
		requestorInfo.setEnableMastercard(form.getEnableMastercard());
		requestorInfo.setEnableVisa(form.getEnableVisa());
//		requestorInfo.setLastOperator(form.getLastOperator());
		requestorInfo.setRequestorId(form.getRequestorId());
		requestorInfo.setRequestorName(form.getRequestorName());
		requestorInfo.setRequestorPassword(form.getRequestorPassword());

		// 4. 資料操作：修改 "目錄服務器連線設定" 實體。
		try {
			requestorInfoService.update(requestorInfo);
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/requestor_info/edit";
		}	
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 5. 返回：查詢作業的網頁路徑。
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
	private String recallQuery(Model model) {
		
		RequestorInfoCriteriaForm form=(RequestorInfoCriteriaForm) model.asMap().get("requestorInfoCriteriaForm");
		RequestorInfo requestorInfo = new RequestorInfo();

		// 商店代號  商店名稱
		requestorInfo.setRequestorId(form.getRequestorId());
		requestorInfo.setRequestorName(form.getRequestorName());

		model.addAttribute(WebKeyConst.QUERY_RESULT,
				requestorInfoService.findByCriteria(requestorInfo, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));

		return "/requestor_info/query";
	}
}
