/*
 * @(#)AdminGroupCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定作業用的查詢條件 Form(前台綁定)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.01,  /07/26, Milo Gao
 *   2) 接手開發。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定作業用的查詢條件 Form(前台綁定)。
 * 
 * @author   LeonKao, MiloGao
 */
public class AdminGroupCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 查詢條件(1): 群組代碼。
	 */
	private String criteriaGroupId;
	
	/**
	 * 查詢條件(2): 群組名稱。
	 */
	private String criteriaGroupName;
		
    //=================================================
    // constructors
    //=================================================

    /**
     * 預設建構子
     */
	public AdminGroupCriteriaForm() {}

    //=================================================
    // getter & setter
    //=================================================

	public String getCriteriaGroupId() {
		return criteriaGroupId;
	}

	public void setCriteriaGroupId(String criteriaGroupId) {
		this.criteriaGroupId = criteriaGroupId;
	}

	public String getCriteriaGroupName() {
		return criteriaGroupName;
	}

	public void setCriteriaGroupName(String criteriaGroupName) {
		this.criteriaGroupName = criteriaGroupName;
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
