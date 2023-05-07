/*
 * @(#)KekCriteriaForm.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理 "加密金鑰設定" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,   /02/08, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * InvCore EMV invSys 系統 - ACS 後台管理 "加密金鑰設定" 查詢作業表單類別
 * 
 * @author   LeonKao
 */
public class KekCriteriaForm implements Serializable {
    
	/**
	 * 查詢條件(2)-金鑰識別碼
	 */
    @Size(max = 64)
	private String criteriaKeyAlias;

    public String getCriteriaKeyAlias() {
        return criteriaKeyAlias;
    }

    public void setCriteriaKeyAlias(String criteriaKeyAlias) {
        this.criteriaKeyAlias = criteriaKeyAlias;
    }
    
    /**
     * 查詢條件(3)-啟用狀態
     */
    private String criteriaStatus;

    public String getCriteriaStatus() {
        return criteriaStatus;
    }

    public void setCriteriaStatus(String criteriaStatus) {
        this.criteriaStatus = criteriaStatus;
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
