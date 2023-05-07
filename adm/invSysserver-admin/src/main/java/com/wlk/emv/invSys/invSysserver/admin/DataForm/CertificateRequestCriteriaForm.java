/*
 * @(#)CertificateRequestCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組  "憑證請求檔" 查詢作業表單類別
 *
 * Modify History:
 * v1.00,  /07/11, LeonKao
 *   1) First release
 * v1.01,  /07/14, LeonKao 
 *   2) 修正註解
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組  "CertificateRequest憑證請求檔" 查詢作業表單類別
 * 
 * @author LeonKao
 */
public class CertificateRequestCriteriaForm implements Serializable{
	
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
     * 查詢條件(2)-網域名稱(CN)
     */
	private String criteriaCommonName;

	public String getCriteriaCommonName() {
		return criteriaCommonName;
	}

	public void setCriteriaCommonName(String criteriaCommonName) {
		this.criteriaCommonName = criteriaCommonName;
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
