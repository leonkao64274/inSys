/*
 * @(#)TransLogCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 *
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組  - invSys 驗證交易記錄 查詢條件 Form (綁定表單)。
 *
 * Modify History:
 * v1.00,  /07/28, LeonKao
 *   1) First release
 * v1.01,  /11/09, StevenKao
 * 	 2) 新增兩個搜尋欄位    1.ACS 交易序号   2.invSys Server 交易序号
 * v1.03,    /06/06, Leon Kao
 *   1) 增加查詢條件: pmtTransID
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組  - invSys 交易參數設定 Form (綁定表單)。
 *
 * @author LeonKao
 */
public class TransLogCriteriaForm implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 查詢條件(1)- 開始時間
	 */
	private String startDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * 查詢條件(2)- 截止時間
	 */
	private String endDate;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * 查詢條件(3)-卡組織
	 */
	private String criteriaCardScheme;

	public String getCriteriaCardScheme() {
		return criteriaCardScheme;
	}

	public void setCriteriaCardScheme(String criteriaCardScheme) {
		this.criteriaCardScheme = criteriaCardScheme;
	}

	/**
	 * 查詢條件(4)-訊息版本
	 */
	private String messageVersion;
	public String getMessageVersion() {
		return messageVersion;
	}
	public void setMessageVersion(String messageVersion) {
		this.messageVersion = messageVersion;
	}

	/**
	 * 查詢條件(5)-ACS 交易序号
	 */

	private String acsTransID;
	public String getAcsTransID() {
		return acsTransID;
	}
	public void setAcsTransID(String acsTransID) {
		this.acsTransID = acsTransID;
	}

	/**
	 * 查詢條件(6)-invSys Server 交易序号
	 */

	private String invSysServerTransID;
	public String getinvSysServerTransID() {
		return invSysServerTransID;
	}
	public void setinvSysServerTransID(String invSysServerTransID) {
		this.invSysServerTransID = invSysServerTransID;
	}

	/**
	 * 查詢條件(7)-驗證結果
	 */
	private String transStatus;
	public String getTransStatus() {
		return transStatus;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	/**
	 * 查詢條件(8)-身分證號
	 */
	private String acctId;
	public String getAcctId() {
		return acctId;
	}
	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	/**
	 * 查詢條件(9)-卡號前6碼
	 */
	private String acctNumberPrefix;
	public String getAcctNumberPrefix() {
		return acctNumberPrefix;
	}
	public void setAcctNumberPrefix(String acctNumberPrefix) {
		this.acctNumberPrefix = acctNumberPrefix;
	}

	/**
	 * 查詢條件(10)-卡號後4碼
	 */
	private String acctNumberPostfix;
	public String getAcctNumberPostfix() {
		return acctNumberPostfix;
	}
	public void setAcctNumberPostfix(String acctNumberPostfix) {
		this.acctNumberPostfix = acctNumberPostfix;
	}

	/**
	 * 查詢條件(11)-特店代號
	 */
	private String acquirerMerchantID;
	public String getAcquirerMerchantID() {
		return acquirerMerchantID;
	}
	public void setAcquirerMerchantID(String acquirerMerchantID) {
		this.acquirerMerchantID = acquirerMerchantID;
	}

	/**
	 * 查詢條件(12)-交易金額
	 */
	private String purchaseAmount;
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	/**
	 * 查詢條件(13)-Device Channel
	 */
	private String deviceChannel;
    public String getDeviceChannel() {
		return deviceChannel;
	}

	public void setDeviceChannel(String deviceChannel) {
		this.deviceChannel = deviceChannel;
	}

    /**
     * 查詢條件(14)-Message Category
     */
    private String messageCategory;
	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

    /**
     * 查詢條件(15)-UL STP test case ID
     */
    private String testcaseId;

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    /**
     * Payment Gateway Transaction ID
     */
    private String pmtTransID;

    public String getPmtTransID() {
        return pmtTransID;
    }

    public void setPmtTransID(String pmtTransID) {
        this.pmtTransID = pmtTransID;
    }


    /**
     *
     * @Description: 查询条件： Requestor账号
     *
     * @auther: liuhao
     * @date: 17:42  /5/26
     * @param:
     * @return:
     *
     */
    private String integratorRequestorId;

    public String getIntegratorRequestorId() {
        return integratorRequestorId;
    }

    public void setIntegratorRequestorId(String integratorRequestorId) {
        this.integratorRequestorId = integratorRequestorId;
    }

    /**
     *
     * @Description: 查询条件：Requestor订单序号
     *
     * @auther: liuhao
     * @date: 17:43  /5/26
     * @param:
     * @return:
     *
     */
    private String invSysRequestorOrderId;

    public String getinvSysRequestorOrderId() {
        return invSysRequestorOrderId;
    }

    public void setinvSysRequestorOrderId(String invSysRequestorOrderId) {
        this.invSysRequestorOrderId = invSysRequestorOrderId;
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
