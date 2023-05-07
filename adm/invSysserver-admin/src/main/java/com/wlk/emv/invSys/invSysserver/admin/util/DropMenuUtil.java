/*
 * @(#)DropMenuOption.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組  下拉選單顯示不同語言的公用類別
 *
 * Modify History:
 * v1.00,  /09/11, LeonKao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invSet.emv.invSys.invSysserver.core.bean.SysCode;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組  下拉選單(Menu)顯示不同語言的公用類別
 * 
 * @author LeonKao
 */
public class DropMenuUtil {
	private static final Logger logger = LoggerFactory.getLogger(DropMenuUtil.class);
	
	/**
	 * 下拉選單(Menu)的選項顯示的文字
	 */
	private String itemLabel;
	
	/**
	 * 下拉選單(Menu)選項的值
	 */
	private String itemValue;

	
	
	
	
	
	/**
	 * 建立 "下拉選單" 的選項, 提供網頁上顯示
     * 
     * @param locale 		語言國家地區代碼
     * @param sysCodeList 	系統代碼檔 Entity 類別的集合
     * 
     * @return 下拉選單選項的集合
     */
	public static List<DropMenuUtil> getOptions(Locale locale, List<SysCode> sysCodeList) {
		// 先去找 messages_locale變數.properties，找不到去找messages.properties，再找不到則拋出異常
		ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",locale);
    	ArrayList<DropMenuUtil> optionList = new ArrayList<DropMenuUtil>();
    	for(SysCode syscode:sysCodeList){
    		DropMenuUtil option = new DropMenuUtil() ;
    		option.setItemValue(syscode.getCodeId());
    		option.setItemLabel(resourceBundle.getString(syscode.getI18nCode()));
    		optionList.add(option);
    	}
		return optionList;
	}
    //=================================================
    // 建構子
    //=================================================
    
    /**
     * 預設建構子
     */
    public DropMenuUtil() {
        super();
    }

    public DropMenuUtil(String itemLabel, String itemValue) {
        this.itemLabel = itemLabel;
        this.itemValue = itemValue;
    }

    
    
    
    
    //=================================================
    // getter & setter
    //=================================================
	
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	public String getItemLabel() {
		return itemLabel;
	}
	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}
	
}