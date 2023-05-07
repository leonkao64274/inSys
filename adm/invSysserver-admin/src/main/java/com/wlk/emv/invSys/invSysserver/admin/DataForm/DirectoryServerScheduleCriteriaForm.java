/*
 * @(#)DirectoryServerScheduleCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理 "卡段下載設定" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,  /07/31, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

public class DirectoryServerScheduleCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;


	/**
	 * 查詢條件(1)-卡組織
	 */
	private String criteriaCardScheme;

	public String getCriteriaCardScheme() {
		return criteriaCardScheme;
	}

	public void setCriteriaCardScheme(String criteriaCardScheme) {
		this.criteriaCardScheme = criteriaCardScheme;
	}
	 
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
