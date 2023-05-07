/*
 * @(#)CurrencyCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理系統 - 外幣幣別主檔的查詢條件表單類
 *
 * Modify History:
 * v1.00,  /09/22, Milo Gao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

import com.invSet.emv.invSys.invSysserver.core.bean.Currency;
import com.invSet.emv.invSys.invSysserver.core.util.StringUtil;



/**
 * InvCore EMV invSys 系統 - ACS 後台管理系統 - 外幣幣別主檔的查詢條件表單類
 * 
 * @author MiloGao
 */
public class CurrencyCriteriaForm implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 查詢條件(1): 幣別所屬國家或機構
	 */
	private String entity;
	
	/**
	 * 查詢條件(2): 幣別名稱
	 */
	private String currency;
	
	/**
	 * 查詢條件(3): 幣別代碼-字母格式
	 */
	private String alphabeticCode;
	
	/**
	 * 查詢條件(4): 幣別代碼-數字格式
	 */
	private String numericCode;

	/**
	 * 分頁控件上的第幾頁。
	 */
	private Integer pageNumber;
	
    //=================================================
    // constructors
    //=================================================
	
	public CurrencyCriteriaForm() {
		pageNumber = 0;
	}

    //=================================================
    // method
    //=================================================

	/**
	 * 為查詢條件的欄位值添加 "SQL" 語句。
	 * 
	 * @param form 用戶輸入的查詢條件用的表單
	 * @return "幣別"的數據實體，當查詢條件值用。
	 */
	public Currency criteria() {
		
		Currency currency = new Currency();
		currency.setEntity(StringUtil.sqlLike(this.getEntity()));
		currency.setCurrency(StringUtil.sqlLike(this.getCurrency()));
		currency.setAlphabeticCode(StringUtil.sqlLike(this.getAlphabeticCode()));
		currency.setNumericCode(StringUtil.sqlLike(this.getNumericCode()));
		return currency;
	}
	
	/**
	 * 清除查詢條件。
	 */
	public void reset() {
		
		this.entity = null;
		this.currency = null;
		this.alphabeticCode = null;
		this.numericCode = null;
		this.pageNumber = 0;
	}
	
    //=================================================
    // getter & setter
    //=================================================

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAlphabeticCode() {
		return alphabeticCode;
	}

	public void setAlphabeticCode(String alphabeticCode) {
		this.alphabeticCode = alphabeticCode;
	}

	public String getNumericCode() {
		return numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
		
}
