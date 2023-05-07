/*
 * @(#)MenuTagHandler.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 後台管理系統功能選單處理標籤
 *
 * Modify History:
 * v1.00,  /07/03, ArnoChang
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
 
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;
import com.invSet.emv.invSys.invSysserver.core.service.AdminMenuService;
import com.invSet.emv.invSys.invSysserver.core.util.SpringContextUtil;

/**
 * InvCore EMV invSys 系統 - 後台管理系統功能選單處理標籤<br>
 * 
 * 依據使用者操作權限決定功能選單顯示之功能項目，功能選單結構如下：<br>
 * 功能模組 (module)<br>
 * - 主功能項 (function/function-parent)<br>
 * - - 子功能 (sub-function)<br>
 * 主/子功能為各功能模組之內層結構，該主從結構可反覆向下延伸
 *
 * @author ArnoChang
 */
public class MenuTagHandler extends SimpleTagSupport {
	
	private static Logger LOG = LoggerFactory.getLogger(MenuTagHandler.class);

    private List<String> grantedAccessIdList;
    
    //語言與地區國家代碼
    private Locale locale;
    
    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // HTML before writing the body content.
        	
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // HTML after writing the body content.

            // 查詢取得功能選單項與個別子功能選項
            AdminMenuService adminMenuService = SpringContextUtil.getApplicationContext().getBean("adminMenuService", AdminMenuService.class);
            List<AdminMenu> menuList = adminMenuService.getTopLevelMenuNodes();
            //LOG.debug("[grantedAccessIdList size]: " + grantedAccessIdList.size());
            //LOG.debug("[menuList size]: " + menuList.size());

            // 比對使用者功能選單權限
            List<AdminMenu> grantedMenuList = new ArrayList<AdminMenu>();
            for (AdminMenu module : menuList) {
            	grantedMenuList.add(compareAccessId(module));
            }
            //LOG.debug("[grantedMenuList size]: " + grantedMenuList.size());
            
            // 組織功能選單HTML標籤
            StringBuilder sb = new StringBuilder();
            //sb.append("<ul class='nav' id='side-menu'>");
	        // sidebar search  9/4 LeonKao 為demo版先不顯示
            
//	        sb.append("    <li class='sidebar-search'>");
//	        sb.append("        <div class='input-group custom-search-form'>");
//	        sb.append("        <input type='text' class='form-control' placeholder='Search...'>");
//	        sb.append("        <span class='input-group-btn'>");
//	        sb.append("            <button class='btn btn-default' type='button'>");
//	        sb.append("                <i class='fa fa-search'></i>");
//	        sb.append("            </button>");
//	        sb.append("        </span>");
//	        sb.append("        </div>");
//	        sb.append("    </li>");
	        // module
	        for (AdminMenu module : grantedMenuList) {
	        	if (module.getChildNodes().isEmpty()) {
	        		continue;
	        	}
	        	sb.append("    <li>");
	        	sb.append("        <a href='#'><i class='"+  getMenu(module,module.getI18nCode()) + "'></i> " + getMenuName(module.getI18nCode()) + "<span class='fa arrow'></span></a>");
	        	sb.append("        <ul class='nav nav-second-level'>");
	        	// function
	        	sb = composeFunctionMenu(sb, module);
	        	sb.append("        </ul>");
	        	sb.append("    </li>");
	        }
	        //sb.append("</ul>");
	        
            out.println(sb.toString());
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in MenuTagHandler tag", ex);
        }
    }

    public void setGrantedAccessIdList(List<String> grantedAccessIdList) {
        this.grantedAccessIdList = grantedAccessIdList;
    }
    
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
     * 遞迴比對使用者權限清單與功能選單
     * 
     * @author ArnoChang  /07/04
     * 
     * @param module 功能模組/主功能項
     * @return 已授權操作之功能模組/主功能項
     */
    private AdminMenu compareAccessId(AdminMenu module) {
    	LinkedHashSet<AdminMenu> functions = new LinkedHashSet<AdminMenu>();
        
        module.getChildNodes().forEach((function) -> {
    		if (grantedAccessIdList.contains(function.getAccessId())) {
    			functions.add(function);
    		}
    		
    		if (!function.getChildNodes().isEmpty()) {
    			function = compareAccessId(function);
    		}
    		
    		if (!function.getChildNodes().isEmpty()) {
    			functions.add(function);
    		}
        });
        
        AdminMenu menu = new AdminMenu();
        BeanUtils.copyProperties(module, menu, "childNodes");
        menu.setChildNodes(functions);
        
        return menu;
    }
    
    /**
     * 組織主功能/子功能選單HTML標籤語法
     * 
     * @author ArnoChang  /07/04
     * 
     * @param sb StringBuilder
     * @param module 功能模組/主功能項
     * @return 功能選單HTML標籤語法
     */
    private StringBuilder composeFunctionMenu(StringBuilder sb, AdminMenu module) {
    	for (AdminMenu function : module.getChildNodes()) {
    		if (function.getChildNodes().isEmpty()) {
    			sb.append("            <li>");
        		sb.append("                <a href='" + function.getHref() + "'><i class='fa fa-angle-right fa-fw'></i> " + getMenuName(function.getI18nCode()) + "</a>");
        		sb.append("            </li>");
    		} else {
	        	sb.append("    <li>");
	        	sb.append("        <a href='#'><i class='"+  getMenu(module,module.getI18nCode()) + "'></i> " + getMenuName(function.getI18nCode()) + "<span class='fa arrow'></span></a>");
	        	sb.append("        <ul class='nav nav-second-level'>");
	        	// function
	        	sb = composeFunctionMenu(sb, function);
	        	sb.append("        </ul>");
	        	sb.append("    </li>");	
    		}
    	}
    	
    	return sb;
    }

    /**
     * 取得多國語系的文字
     * 
     * @author LeonKao  /08/30
     * 
     * @param String i18nCode 
     * @return 多國語系的文字
     */
	private String getMenuName(String i18nCode) {

		// 取得目前的Locale設定
		//Locale locale = LocaleContextHolder.getLocale();
        
        // i18n檔案名稱開頭 - messages
		ResourceBundle res = ResourceBundle.getBundle("messages",this.locale);
		
		String menuName = null;
		try {
			if (i18nCode == null)
				return "";
			menuName = res.getString(i18nCode);
		} catch (java.util.MissingResourceException ex) { 
			menuName = "";
		}
		if (menuName == null) {
			menuName = "";
		}
		return menuName;
	}
	
    /**
     * 測邊圖案
     * 
     * @author Steven Kao  /08/30
     * 
     * @param  AdminMenu module
     * @param  String i18nCode 
     * @return 測邊圖案的 class
     */
	private String getMenu(AdminMenu module, String i18n) {
		
    	if(module.getI18nCode().equals("ui.menu.sys")){
    		return "fa fa-gear fa-fw";
    	}else if(module.getI18nCode().equals("ui.menu.privilege")){
    		return "fa fa-user fa-fw";
    	}else if(module.getI18nCode().equals("ui.menu.trans")){
    		return "fa fa-credit-card fa-fw";
    	}else if(module.getI18nCode().equals("ui.menu.certificate")){
    		return "fa fa-file fa-fw";
    	}else if(module.getI18nCode().equals("ui.menu.key")){
    		return "fa fa-key fa-fw";
    	}else if(module.getI18nCode().equals("ui.mect-info.management")){
    		return "fa fa-credit-card fa-fw";
    	}else if(module.getI18nCode().equals("ui.requestor-info.management")){
    		return "fa fa-credit-card fa-fw";
    	}
    	
		return null;	
	}
	
}