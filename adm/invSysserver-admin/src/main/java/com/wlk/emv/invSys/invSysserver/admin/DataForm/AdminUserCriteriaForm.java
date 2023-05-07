/*
 * @(#)AdminGroupCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組使用者作業用的查詢條件 Form(前台綁定)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.01,  /07/26, Milo Gao
 *   2) 接手開發。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組使用者作業用的查詢條件 Form(前台綁定)。
 * 
 * @author   LeonKao, MiloGao
 */
public class AdminUserCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 查詢條件(1): <功能群組設定> 的主鍵值。
	 */
	private String criteriaAdminGroupOid;
	
	/**
	 * 查詢條件(2): "使用者代號"
	 */
	private String criteriaAccount;

	/**
	 * 組態資料(1): <功能群組設定> 的實體集合。
	 */
	@JsonIgnore
	private List<AdminGroup> configAdminGroupList;
	
    //=================================================
    // constructors
    //=================================================

    /**
     * 預設建構子
     */
	public AdminUserCriteriaForm() {}

    //=================================================
    // getter & setter
    //=================================================

	public String getCriteriaAdminGroupOid() {
		return criteriaAdminGroupOid;
	}

	public void setCriteriaAdminGroupOid(String criteriaAdminGroupOid) {
		this.criteriaAdminGroupOid = criteriaAdminGroupOid;
	}

	public String getCriteriaAccount() {
		return criteriaAccount;
	}

	public void setCriteriaAccount(String criteriaAccount) {
		this.criteriaAccount = criteriaAccount;
	}

	public List<AdminGroup> getConfigAdminGroupList() {
		return configAdminGroupList;
	}

	public void setConfigAdminGroupList(List<AdminGroup> configAdminGroupList) {
		this.configAdminGroupList = configAdminGroupList;
	}
}
