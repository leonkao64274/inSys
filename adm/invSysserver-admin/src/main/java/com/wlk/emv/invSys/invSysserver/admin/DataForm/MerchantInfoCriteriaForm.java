package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定作業用的查詢條件 Form(前台綁定)。
 *
 * 
 */
public class MerchantInfoCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
     * 查詢條件(1): 商店代號 商店名稱。
     */
    private String merchantName;
    private String acquirerMerchantId;
    
    
    /**
     * 指定分頁查詢頁次
     */
    private Integer pageNumber;


    //=================================================
    // constructors
    //=================================================
    
    /**
     * 預設建構子
     */
    public MerchantInfoCriteriaForm() {
        super();
    }

    //=================================================
    // getter & setter
    //=================================================
    
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }
    
 

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
