/*
 * @(#)AdminUserForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組 "群組使用者管理" 新增、修改用表單類別
 *
 * Modify History:
 * v1.00,  /10/20, LeonKao
 *   1) 增加加密後密碼欄位 
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組      '群組使用者管理' 新增、修改用表單類別
 * 
 * @author LeonKao
 */
public class AdminUserForm extends AdminUser{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 確認密碼
     */
//	@NotEmpty
    @Size(min = 6, max = 12)
	@Pattern(regexp = "^[A-Za-z0-9]+$",message="{passwords.format.is.wrong}")
    private String cnfrPassword;

	public AdminUserForm() {
		super();
	}
	
	public AdminUserForm(String cnfrPassword) {
		super();
		this.cnfrPassword = cnfrPassword;
	}
	
	//確認密碼
	public String getCnfrPassword() {
		return cnfrPassword;
	}

	public void setCnfrPassword(String cnfrPassword) {
		this.cnfrPassword = cnfrPassword;
	}
	
	
    /**
     * 加密後的密碼<br/>
     * 企業規則 = sha1(account + password)
     */
    private String encryptPassword;

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}    
	
}
