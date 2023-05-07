/*
 * @(#)CurrencyDataForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理系統 - 幣別+匯率的資料表單。
 *
 * Modify History:
 * v1.00,  /09/21, Milo Gao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * InvCore EMV invSys 系統 - ACS 後台管理系統 - 幣別+匯率的資料表單。
 * 
 * @author MiloGao
 */
public class CurrencyDataForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主鍵(1): 外幣匯率的主鍵值。
	 */
	private Long currencyRateOid;
	
	/**
	 * 主鍵(2): 外幣幣別的主鍵值。
	 */
	private Long currencyOid;
	
	
    /**
     * 幣別所屬國家或機構
     */
    @NotEmpty
    private String entity;
    
    /**
     * 幣別名稱
     */
    @NotEmpty
    private String currency;
    
    /**
     * 幣別代碼-字母格式
     */
    @NotEmpty
    @Pattern(regexp="[A-Z]{3}")
    private String alphabeticCode;
    
    /**
     * 幣別代碼-數字格式
     */
    @NotEmpty
    @Pattern(regexp="[0-9]{3}")
    private String numericCode;
    
    /**
     * 金額小數位數
     */
    @Min(0)
    @Max(8)
    private Integer minorUnit;


    //=================================================
    // constructors
    //=================================================
	
	public CurrencyDataForm() {}

	
	
    //=================================================
    // getter & setter
    //=================================================

	public Long getCurrencyRateOid() {
		return currencyRateOid;
	}

	public void setCurrencyRateOid(Long currencyRateOid) {
		this.currencyRateOid = currencyRateOid;
	}

	public Long getCurrencyOid() {
		return currencyOid;
	}

	public void setCurrencyOid(Long currencyOid) {
		this.currencyOid = currencyOid;
	}

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

	public Integer getMinorUnit() {
		return minorUnit;
	}

	public void setMinorUnit(Integer minorUnit) {
		this.minorUnit = minorUnit;
	}


}
