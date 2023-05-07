/*
 * @(#)OperationLogCriteriaForm.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - ACS 後台管理 "操作記錄查詢" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,   /03/13, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * InvCore EMV invSys 系統 - ACS 後台管理 "操作記錄查詢" 查詢作業表單類別
 * 
 * @author   LeonKao
 */
public class OperationLogCriteriaForm implements Serializable {
    
    /**
     * 查詢條件(1)-發卡銀行
     */
    private Long criteriaIssuerOid;

    public Long getCriteriaIssuerOid() {
        return criteriaIssuerOid;
    }

    public void setCriteriaIssuerOid(Long criteriaIssuerOid) {
        this.criteriaIssuerOid = criteriaIssuerOid;
    }
    
    /**
     * 查詢日期起日
     */
    @NotEmpty
    private String criteriaStartDate;

    public String getCriteriaStartDate() {
        return criteriaStartDate;
    }

    public void setCriteriaStartDate(String criteriaStartDate) {
        this.criteriaStartDate = criteriaStartDate;
    }
    
    /**
     * 查詢日期迄日
     */
    @NotEmpty
    private String criteriaEndDate;

    public String getCriteriaEndDate() {
        return criteriaEndDate;
    }

    public void setCriteriaEndDate(String criteriaEndDate) {
        this.criteriaEndDate = criteriaEndDate;
    }
    
    /**
     * 操作人員帳號
     */
    private String criteriaAccount;

    public String getCriteriaAccount() {
        return criteriaAccount;
    }

    public void setCriteriaAccount(String criteriaAccount) {
        this.criteriaAccount = criteriaAccount;
    }
    
    /**
     * 功能代碼
     */
    private String criteriaAccessId;

    public String getCriteriaAccessId() {
        return criteriaAccessId;
    }

    public void setCriteriaAccessId(String criteriaAccessId) {
        this.criteriaAccessId = criteriaAccessId;
    }
    
    /**
     * 執行動作
     */
    private String criteriaAction;

    public String getCriteriaAction() {
        return criteriaAction;
    }

    public void setCriteriaAction(String criteriaAction) {
        this.criteriaAction = criteriaAction;
    }
    
    /**
     * 執行結果
     */
    private String criteriaResult;

    public String getCriteriaResult() {
        return criteriaResult;
    }

    public void setCriteriaResult(String criteriaResult) {
        this.criteriaResult = criteriaResult;
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
