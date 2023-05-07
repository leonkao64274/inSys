/*
 * @(#)invSysServerCertForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys 後台管理系統 "DS憑證資訊" 載入作業表單類別
 *
 * Modify History:
 * v1.00,   /04/02, BOB
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import com.invSet.emv.invSys.invSysserver.core.bean.invSysServerCert;

public class invSysServerCertForm extends invSysServerCert{

	private static final long serialVersionUID = 1L;
	
	private int certEncode;

	public int getCertEncode() {
		return certEncode;
	}

	public void setCertEncode(int certEncode) {
		this.certEncode = certEncode;
	}
}
