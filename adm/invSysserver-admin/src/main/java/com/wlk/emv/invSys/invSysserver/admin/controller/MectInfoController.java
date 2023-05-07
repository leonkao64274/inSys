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
import com.invSet.emv.invSys.invSysserver.admin.bean.MerchantInfo;
import com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog;
import com.invSet.emv.invSys.invSysserver.admin.log.LogContextHolder;
import com.invSet.emv.invSys.invSysserver.admin.service.MerchantInfoService;
import com.invSet.emv.invSys.invSysserver.admin.util.AppProperties;
import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.util.JsonUtil;

@SessionAttributes("merchantInfoCriteriaForm")
@Controller
public class MectInfoController {
	private static final Logger logger = LoggerFactory.getLogger(MectInfoController.class);

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
	 * 目錄服務器連線設定操作
	 */
	@Autowired
	private MerchantInfoService merchantInfoService;
	
	
	/**
	 * 派遣服務請求前的前置程序。
	 * 
	 * @param model 網頁級資料模型
	 * @param session 瀏覽器級資料模型
	 */
	@ModelAttribute
	public void initial(Model model, HttpSession session) {

		MerchantInfoCriteriaForm form = (MerchantInfoCriteriaForm) session
				.getAttribute("merchantInfoCriteriaForm");
		// 2. 檢查 "查詢作業" 用的 Form。
		if (form == null) {
			form = new MerchantInfoCriteriaForm();
		}
		if (model.containsAttribute("merchantInfoCriteriaForm") == false) {
			model.addAttribute("merchantInfoCriteriaForm", form);
		}
	}
	
	
	/**
	 * 查詢作業(1/1) - 管理
	 * 
	 * @param form 查詢作業用的 Form。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 查詢作業的網頁路徑。
	 * @param pageNumber 頁數序號。
	 * @return 查詢作業的網頁路徑。
	 * @throws JsonProcessingException 
	 */
	@RequestMapping(path = "/mect_info/query")
	@AcessRightCheck(accessId = "mect_info")
	@DoOperationLog(accessId = "mect_info", operation = "Q", targetObject = "T_MERCHANT_INFO")
	public String query(@Valid @ModelAttribute("merchantInfoCriteriaForm") MerchantInfoCriteriaForm form,
			BindingResult result,
			Model model) {

		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_QUERY,JsonUtil.writeValueAsBytes(form));
		}catch(JsonProcessingException r) {
			logger.warn("mect_info query json query error");
		}
		// 1. 檢查：查詢條件輸入值，在自動驗證機制處理後是否有錯誤。
		if (result.hasErrors()) {
			return "/mect_info/query";
		}
		MerchantInfo ds = new MerchantInfo();
		ds.setAcquirerMerchantId(form.getAcquirerMerchantId());
		ds.setMerchantName(form.getMerchantName());
		

		// 2. 執行：查詢 "功能群組設定" 的分頁集合。
		model.addAttribute(WebKeyConst.QUERY_RESULT,
				merchantInfoService.findByCriteria(ds, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));
		// 操作記錄 - 操作結果
		LogContextHolder.putLogAttribute(LogContextHolder.ATTR_RESULT,true);
		// 3. 返回：查詢作業的網頁路徑。
		return "/mect_info/query";
	}
	
	
	/**
	 * 新增作業(1/2) - 管理
	 * @param model
	 *            資料模型
	 * @return 新增作業的網頁路徑。
	 */
	@RequestMapping(path = "/mect_info/add", method = RequestMethod.GET)
	@AcessRightCheck(accessId = "mect_info")
	public String showAddForm(Model model) {

		// 1. 返回：新增作業的網頁路徑。
		model.addAttribute("form", new MerchantInfo());
		
		return "/mect_info/add";
	}
	
	
	/**
	 * 新增作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 即將被新增的 Entity。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 新增作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/mect_info/add", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "mect_info")
	@DoOperationLog(accessId = "mect_info", operation = "A" , targetObject = "T_MERCHANT_INFO")
	public String add(@Valid @ModelAttribute("form") MerchantInfo form, 
			BindingResult result, 
			Model model) {
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("mect_info add json after error");
		}

		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/mect_info/add";
		}

		// 判斷商店代號是否重複
		if ( merchantInfoService.findByAcquirerMerchantId(form.getAcquirerMerchantId()) != null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/mect_info/add";
		}

		// 將 "新增內容" 訊息，儲存。
		try {
			merchantInfoService.save(form);
		} catch (ResourceAlreadyExistException e) {
			model.addAttribute(WebKeyConst.ERRORS,
				messageSource.getMessage("warn.resource-already-exist", null, LocaleContextHolder.getLocale()));
			return "/mect_info/add";
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
	@RequestMapping(path = "/mect_info/edit", method = RequestMethod.GET)
    @AcessRightCheck(accessId = "mect_info")
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
		MerchantInfo form = merchantInfoService.findById(id);
		if (form == null) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			return recallQuery(model);
		}

		// 2. 準備資料模型：(1)即將修改用的實體 (2)初始化
		model.addAttribute("form", form);

		// 3. 返回：新增作業的網頁路徑。
		return "/mect_info/edit";
	}

	/**
	 * 修改作業(2/2) - 功能群組設定 (提交)
	 * 
	 * @param form 修改作業用的設定 Form。
	 * @param result 自動驗證結果。
	 * @param model 網頁級資料模型。
	 * @return 修改作業(失敗)或查詢作業(成功)的網頁路徑。
	 */
	@RequestMapping(path = "/mect_info/edit", method = RequestMethod.POST)
    @AcessRightCheck(accessId = "mect_info")
	@DoOperationLog(accessId = "mect_info", operation = "E" , targetObject = "T_MERCHANT_INFO")
	public String edit(@Valid @ModelAttribute("form") MerchantInfo form, BindingResult result, Model model) {
		
		// 操作記錄 - 異動前/後資料
		try {
			LogContextHolder.putLogAttribute(LogContextHolder.ATTR_DATA_AFTER,JsonUtil.writeValueAsBytes(form));
		} catch (JsonProcessingException e) {
			logger.warn("merchant_info edit json after error");
		}

		if (result.hasErrors()) {
			logger.warn("form error: \n"+result.getAllErrors());
			return "/mect_info/edit";
		}

		// 3.把修改資料放進修改實體
		MerchantInfo ds = merchantInfoService.findById(form.getOid());
		
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
			logger.warn("MerchantInfo write before log error");
		}
		
		ds.setAcquirerMerchantId(form.getAcquirerMerchantId());
		ds.setvMerchantPwd(form.getvMerchantPwd());
		ds.setmMerchantPwd(form.getmMerchantPwd());
		ds.setjMerchantPwd(form.getjMerchantPwd());
		ds.setcMerchantPwd(form.getcMerchantPwd());
		ds.setMcc(form.getMcc());
		ds.setMerchantCountryCode(form.getMerchantCountryCode());
		ds.setMerchantName(form.getMerchantName());
		ds.setPurchaseCurrency(form.getPurchaseCurrency());
        ds.setPurchaseExponent(form.getPurchaseExponent());
        ds.setinvSysRequestorUrl(form.getinvSysRequestorUrl());
        ds.setMerStatus(form.getMerStatus());

		// 4. 資料操作：修改 "目錄服務器連線設定" 實體。
		try {
			merchantInfoService.update(ds);
		} catch (ResourceNotFoundException ex) {
			model.addAttribute(WebKeyConst.ERRORS,
					messageSource.getMessage("warn.resource-not-exist", null, LocaleContextHolder.getLocale()));
			logger.info(ex.toString());
			return "/mect_info/edit";
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
		
		MerchantInfoCriteriaForm form=(MerchantInfoCriteriaForm) model.asMap().get("merchantInfoCriteriaForm");
		MerchantInfo ds = new MerchantInfo();

		// 商店代號  商店名稱
		ds.setAcquirerMerchantId(form.getAcquirerMerchantId());
		ds.setMerchantName(form.getMerchantName());

		model.addAttribute(WebKeyConst.QUERY_RESULT,
				merchantInfoService.findByCriteria(ds, new PageRequest(form.getPageNumber(), appProperties.getPageSize())));

		return "/mect_info/query";
	}
}
