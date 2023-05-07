/*
 * @(#)IndexFirstLoginForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台的用戶第一次登錄修改密碼介面類。
 *
 * Modify History:
 * v1.00,  /11/27, LeonKao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import java.security.SecureRandom;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台的用戶第一次登錄修改密碼介面類。
 * 
 * @author AlexYnag
 */
public class IndexFirstLoginForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	
    /**
     * 使用者帳號
     */
    @NotEmpty
	private String account;
	
    /**
     * 使用者密碼
     */
    @NotEmpty
    private String password; 

    
    /**
     * 亂數8碼<br/>
     * 用於登錄流程。
     */
    private String random8digits;
    
    /**
     * 加密後的舊密碼<br/>
     * 企業規則 = sha1( 亂數8碼 + account + sha1(password))
     */
    private String encryptPassword;
    
    /**
     * 加密後的新密碼<br/>
     * 企業規則 = sha1( 亂數8碼 + account + sha1(new-password))
     */
    private String encryptNewPassword;
    
    /**
     * 弱掃需求的隨機變數
     */
    SecureRandom secureRandom = new SecureRandom();
       
    //=================================================
    // constructors
    //=================================================


	public IndexFirstLoginForm() {
    	this.random8digits = String.valueOf((long) Math.floor(secureRandom.nextInt() * 100000000));
    }

    //=================================================
    // getter & setter
    //=================================================

    
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandom8digits() {
		return random8digits;
	}

	public void setRandom8digits(String random8digits) {
		this.random8digits = random8digits;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public String getEncryptNewPassword() {
		return encryptNewPassword;
	}

	public void setEncryptNewPassword(String encryptNewPassword) {
		this.encryptNewPassword = encryptNewPassword;
	}
	

}
