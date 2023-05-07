/*
 * @(#)IssuerCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組 "銀行管理作業" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,  /06/19, Steven Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

import javax.validation.constraints.Size;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組 "發卡單位憑證資訊" 查詢作業表單類別
 * 
 * @author Steven
 */
public class IssuerCertCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
	 * 查詢條件-卡組織
	 */
    @Size(max = 100)
	private String criteriaCardScheme;

	public String getCriteriaCardScheme() {
		return criteriaCardScheme;
	}

	public void setCriteriaCardScheme(String criteriaCardScheme) {
		this.criteriaCardScheme = criteriaCardScheme;
	} 
    
    
    /**
     * 查詢條件-憑證狀態
     */
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	/**
     * 查詢條件-憑證有效期間
     */
	private String notBefore;

	public String getNotBefore() {
		return notBefore;
	}

	public void setNotBefore(String notBefore) {
		this.notBefore = notBefore;
	}

    /**
     * 預設建構子
     */
	public IssuerCertCriteriaForm() {}
	
    /**
     * 指定分頁查詢頁次
     */
    private Integer pageNumber;

    public Integer getPageNumber() {
        if (pageNumber == null) {
            return 0;
        }
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    
}
