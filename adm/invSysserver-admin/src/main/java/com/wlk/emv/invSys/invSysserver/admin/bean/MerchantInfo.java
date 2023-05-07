/* Bean
 *
 * @(#)MerchantInfo.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - 目錄服務器設定 Entity 類別
 *
 *
 */
//package com.invSet.emv.invSys.invSysserver.core.bean;
package com.invSet.emv.invSys.invSysserver.admin.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import com.invSet.emv.invSys.invSysserver.core.bean.BaseBean;

/**
 * InvCore EMV invSys 系統 - 目錄服務器設定 Entity 類別
 * 
 * @author Abner Chang
 */
@Entity
@Table(name = "T_MERCHANT_INFO")
public class MerchantInfo extends BaseBean {
    
    private final static long serialVersionUID = 1L;
    
	/**
     * 商店ID 
     */
	@NotEmpty
    @Column(name = "ACQUIRER_MERCHANT_ID", length = 35, nullable = false, unique = true)
    private String acquirerMerchantId;
    

    
    /**
     * 商店類別代碼
     */
	@NotEmpty
    @Column(name = "MCC", length = 4, nullable = false)
    private String mcc;
    
    /**
     * 商店國家代碼
     */
	@NotEmpty
    @Column(name = "MERCHANT_COUNTRY_CODE", length = 3, nullable = false)
    private String merchantCountryCode;
    
    /**
     * 商店名稱
     */
	@NotEmpty
    @Column(name = "MERCHANT_NAME", length = 40, nullable = false)
    private String merchantName;
    
    /**
     * 幣別代碼
     */
	@NotEmpty
    @Column(name = "PURCHASE_CURRENCY", length = 3, nullable = false)
    private String purchaseCurrency;
    
    /**
     * 貨幣指數
     */
	@NotEmpty
    @Column(name = "PURCHASE_EXPONENT", length = 1, nullable = false)
    private String purchaseExponent;
    
    /**
     * invSys URL
     */
	@NotEmpty
    @Column(name = "invSys_REQUESTOR_URL", length = 2048, nullable = false)
    private String invSysRequestorUrl;
    
    /**
     * 商店狀態 
        A = Activate
        L = Lock
     */
	@NotEmpty
    @Size(max = 1)
    @Column(name = "MER_STATUS", length = 1, nullable = false)
    private String merStatus;
	
    /**
     * 商家密碼
     */
    @Column(name = "V_MERCHANT_PWD", length = 16, nullable = false)
    private String vMerchantPwd;
	
    @Column(name = "M_MERCHANT_PWD", length = 16, nullable = false)
    private String mMerchantPwd;
	
    @Column(name = "J_MERCHANT_PWD", length = 16, nullable = false)
    private String jMerchantPwd;
	
    @Column(name = "C_MERCHANT_PWD", length = 16, nullable = false)
    private String cMerchantPwd;

    //=================================================
    // constructors
    //=================================================
    
    public MerchantInfo() {
        super();
    }
    
    //=================================================
    // getter & setter
    //=================================================

    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }



    public String getvMerchantPwd() {
		return vMerchantPwd;
	}

	public void setvMerchantPwd(String vMerchantPwd) {
		this.vMerchantPwd = vMerchantPwd;
	}

	public String getmMerchantPwd() {
		return mMerchantPwd;
	}

	public void setmMerchantPwd(String mMerchantPwd) {
		this.mMerchantPwd = mMerchantPwd;
	}

	public String getjMerchantPwd() {
		return jMerchantPwd;
	}

	public void setjMerchantPwd(String jMerchantPwd) {
		this.jMerchantPwd = jMerchantPwd;
	}

	public String getcMerchantPwd() {
		return cMerchantPwd;
	}

	public void setcMerchantPwd(String cMerchantPwd) {
		this.cMerchantPwd = cMerchantPwd;
	}

	public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMerchantCountryCode() {
        return merchantCountryCode;
    }
    
    public void setMerchantCountryCode(String merchantCountryCode) {
        this.merchantCountryCode = merchantCountryCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public String getPurchaseExponent() {
        return purchaseExponent;
    }

    public void setPurchaseExponent(String purchaseExponent) {
        this.purchaseExponent = purchaseExponent;
    }

    public String getinvSysRequestorUrl() {
        return invSysRequestorUrl;
    }

    public void setinvSysRequestorUrl(String invSysRequestorUrl) {
        this.invSysRequestorUrl = invSysRequestorUrl;
    }

    public String getMerStatus() {
        return merStatus;
    }

    public void setMerStatus(String merStatus) {
        this.merStatus = merStatus;
    }
}
