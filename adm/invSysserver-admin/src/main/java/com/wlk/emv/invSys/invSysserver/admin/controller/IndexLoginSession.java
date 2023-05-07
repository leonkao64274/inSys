/*
 * @(#)IndexLoginSession.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的用戶登錄用 Session 級屬性記錄元件類。
 *
 * Modify History:
 * v1.00,  /10/16, LeonKao
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;
import com.invSet.emv.invSys.invSysserver.core.bean.LoginSessionKey;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 的用戶登錄用 Session 級屬性記錄元件類。
 * 
 * @author LeonKao
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class IndexLoginSession implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 用戶登錄訊息類。
	 */
	private AdminUser adminUser = null;
    
    /**
     * 被授權的功能項目代碼
     */
    private List<String> grantedAccessIdList;
    
    /**
     * 語言國別代碼
     */
    private Locale locale;
    
    /**
     * 登入紀錄
     */
    private LoginSessionKey loginSessionKey;
	
    //=================================================
    // constructors
    //=================================================
    
    /**
     * 預設建構子
     */
	public IndexLoginSession() {}

    //=================================================
    // getter & setter
    //=================================================

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

    public List<String> getGrantedAccessIdList() {
        return grantedAccessIdList;
    }

    public void setGrantedAccessIdList(List<String> grantedAccessIdList) {
        this.grantedAccessIdList = grantedAccessIdList;
    }

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public LoginSessionKey getLoginSessionKey() {
		return loginSessionKey;
	}

	public void setLoginSessionKey(LoginSessionKey loginSessionKey) {
		this.loginSessionKey = loginSessionKey;
	}
    
}
