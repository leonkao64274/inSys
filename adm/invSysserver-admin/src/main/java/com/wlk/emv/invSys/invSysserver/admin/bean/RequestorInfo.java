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
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import com.invSet.emv.invSys.invSysserver.core.bean.BaseBean;

/**
 * InvCore EMV invSys 系統 - 業者管理設定 Entity 類別
 * 
 * @author LeonKao
 */
@Entity
@Table(name = "T_I_REQUESTOR")
public class RequestorInfo extends BaseBean {
    
    private final static long serialVersionUID = 1L;
    

	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE", length = 1, nullable = false, unique = true)
    private String enable;
    

	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE_AE", length = 1, nullable = false)
    private String enable_ae;

	
	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE_CUP", length = 1, nullable = false)
    private String enableCup;
    

	@NotEmpty
    @Column(name = "ENABLE_DISCOVER", length = 1, nullable = false)
    private String enableDiscover;
    

	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE_JCB", length = 1, nullable = false)
    private String enableJCB;
    

	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE_MASTERCARD", length = 1, nullable = false)
    private String enableMastercard;
    

	@NotEmpty
    @Size(max = 1)
    @Column(name = "ENABLE_VISA", length = 1, nullable = false)
    private String enableVisa;
    

//	@NotEmpty
//    @Column(name = "LAST_OPERATOR", length = 20, nullable = true)
//    private String lastOperator;
    

	@NotEmpty
    @Size(max = 10)
    @Column(name = "REQUESTOR_ID", length = 10, nullable = false)
    private String requestorId;
	
	@NotEmpty
	@Size(max = 128)
    @Column(name = "REQUESTOR_PASSWORD", length = 128, nullable = false)
    private String requestorPassword;
	
	@NotEmpty
    @Size(max = 20)
    @Column(name = "REQUESTOR_NAME", length = 50, nullable = true)
    private String requestorName;

    //=================================================
    // constructors
    //=================================================
    
    public RequestorInfo() {
        super();
    }
    //=================================================
    // getter & setter
    //=================================================

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getEnable_ae() {
		return enable_ae;
	}

	public void setEnable_ae(String enable_ae) {
		this.enable_ae = enable_ae;
	}

	public String getEnableCup() {
		return enableCup;
	}

	public void setEnableCup(String enableCup) {
		this.enableCup = enableCup;
	}

	public String getEnableDiscover() {
		return enableDiscover;
	}

	public void setEnableDiscover(String enableDiscover) {
		this.enableDiscover = enableDiscover;
	}

	public String getEnableJCB() {
		return enableJCB;
	}

	public void setEnableJCB(String enableJCB) {
		this.enableJCB = enableJCB;
	}

	public String getEnableMastercard() {
		return enableMastercard;
	}

	public void setEnableMastercard(String enableMastercard) {
		this.enableMastercard = enableMastercard;
	}


	public String getEnableVisa() {
		return enableVisa;
	}

	public void setEnableVisa(String enableVisa) {
		this.enableVisa = enableVisa;
	}

//	public String getLastOperator() {
//		return lastOperator;
//	}
//
//	public void setLastOperator(String lastOperator) {
//		this.lastOperator = lastOperator;
//	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getRequestorPassword() {
		return requestorPassword;
	}

	public void setRequestorPassword(String requestorPassword) {
		this.requestorPassword = requestorPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}
    

}
