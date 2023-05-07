/*
 * @(#)CardRangeDataCriteriaForm.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理 "卡 BIN 範圍" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,    /05/18, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 "卡 BIN 範圍" 查詢作業表單類別
 * 
 * @author   LeonKao
 */
public class CardRangeDataCriteriaForm implements Serializable {
    
	/**
	 * 查詢條件(1)-卡組織
	 */
    @Size(max = 1)
	private String criteriaCardScheme;

    public String getCriteriaCardScheme() {
        return criteriaCardScheme;
    }

    public void setCriteriaCardScheme(String criteriaCardScheme) {
        this.criteriaCardScheme = criteriaCardScheme;
    }
    
    /**
     * 查詢條件(2)-卡號
     */
    @Size(max = 19)
    private String criteriaAcctNumber;

    public String getCriteriaAcctNumber() {
        return criteriaAcctNumber;
    }

    public void setCriteriaAcctNumber(String criteriaAcctNumber) {
        this.criteriaAcctNumber = criteriaAcctNumber;
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
