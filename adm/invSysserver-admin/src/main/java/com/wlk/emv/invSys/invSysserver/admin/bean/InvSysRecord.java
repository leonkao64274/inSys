//package com.invSet.emv.invSys.invSysserver.core.base.bean;
package com.invSet.emv.invSys.invSysserver.admin.bean;

import com.invSet.emv.invSys.invSysserver.core.bean.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "T_I_invSys_RECORD")
public class invSysRecord extends BaseBean {

	private static final long serialVersionUID = 1L;

	@Column(name = "invSys_INTEGRATOR_OID",length = 19)
	private Long invSysIntegratorOid;

	@Column(name = "ACCT_NUMBER",length = 64)
	private String acctNumber;

	@Column(name = "MERCHANT_ACQ_BIN",length = 11)
	private String merchantAcqBin;

	@Column(name = "MERCHANT_ID",length = 35)
	private String merchantID;

	@Column(name = "MERCHANT_PASSWORD",length = 8)
	private String merchantPassword;

	@Column(name = "MERCHANT_URL",length = 2048)
	private String merchantURL;

	@Column(name = "MERCHANT_COUNTRY",length = 3)
	private String merchantCountry;

	@Column(name = "MERCHANT_NAME",length = 40)
	private String merchantName;

	@Column(name = "BROWSER_ACCEPT",length = 2048)
	private String browserAccept;

	@Column(name = "BROWSER_USER_AGENT",length = 2048)
	private String browserUserAgent;

	@Column(name = "BROWSER_DEVICE_CATEGORY",length = 2)
	private String browserDeviceCategory;

	@Column(name = "CARD_EXPIRY_DATE",length = 4)
	private String cardExpiryDate;

	@Column(name = "PURCHASE_AMOUNT",length = 48)
	private String purchaseAmount;

	@Column(name = "PURCHASE_PURCH_AMOUNT",length = 48)
	private String purchasePurchAmount;

	@Column(name = "PURCHASE_CURRENCY",length = 3)
	private String purchaseCurrency;

	@Column(name = "PURCHASE_EXPONENT",length = 1)
	private String purchaseExponent;

	@Column(name = "PURCHASE_DATE",length = 17)
	private String purchaseDate;

	@Column(name = "PURCHASE_DESC",length = 125)
	private String purchaseDesc;

	@Column(name = "PURCHASE_INSTALL",length = 3)
	private String purchaseInstall;

	@Column(name = "PURCHASE_RECUR_END",length = 8)
	private String purchaseRecurEnd;

	@Column(name = "PURCHASE_RECUR_FREQUENCY",length = 4)
	private String purchaseRecurFrequency;

	@Column(name = "VERSION",length = 20)
	private String version;

	@Lob
	@Column(name = "EXTENSION", length = 9999, nullable = true)
	private String extension;

	@Column(name = "invSys_TRANS_ID",length = 36)
	private String invSysTransID;

	@Column(name = "VE_RESULT_CODE",length = 4)
	private String veResultCode;

	@Column(name = "PA_RESULT_CODE",length = 4)
	private String paResultCode;

	@Column(name = "TRANS_STATUS",length = 1)
	private String transStatus;

	@Column(name = "ECI",length = 2)
	private String eci;

	@Column(name = "CAVV",length = 28)
	private String cavv;

	@Column(name = "CAVV_ALGORITHM",length = 1)
	private String cavvAlgorithm;
	
	@Column(name = "ACCT_NUMBER_PREFIX",length = 6)
	private String acctNumberPrefix;
	
	@Column(name = "ACCT_NUMBER_POSTFIX",length = 4)
	private String acctNumberPostfix;
	
	@Column(name = "CARD_SCHEME",length = 1)
	private String cardScheme;
	
	public invSysRecord() {
		super();
	}

	public Long getinvSysIntegratorOid() {
		return invSysIntegratorOid;
	}

	public void setinvSysIntegratorOid(Long invSysIntegratorOid) {
		this.invSysIntegratorOid = invSysIntegratorOid;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public String getMerchantAcqBin() {
		return merchantAcqBin;
	}

	public void setMerchantAcqBin(String merchantAcqBin) {
		this.merchantAcqBin = merchantAcqBin;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public String getMerchantURL() {
		return merchantURL;
	}

	public void setMerchantURL(String merchantURL) {
		this.merchantURL = merchantURL;
	}

	public String getMerchantCountry() {
		return merchantCountry;
	}

	public void setMerchantCountry(String merchantCountry) {
		this.merchantCountry = merchantCountry;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBrowserAccept() {
		return browserAccept;
	}

	public void setBrowserAccept(String browserAccept) {
		this.browserAccept = browserAccept;
	}

	public String getBrowserUserAgent() {
		return browserUserAgent;
	}

	public void setBrowserUserAgent(String browserUserAgent) {
		this.browserUserAgent = browserUserAgent;
	}

	public String getBrowserDeviceCategory() {
		return browserDeviceCategory;
	}

	public void setBrowserDeviceCategory(String browserDeviceCategory) {
		this.browserDeviceCategory = browserDeviceCategory;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public String getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public String getPurchasePurchAmount() {
		return purchasePurchAmount;
	}

	public void setPurchasePurchAmount(String purchasePurchAmount) {
		this.purchasePurchAmount = purchasePurchAmount;
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

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getPurchaseDesc() {
		return purchaseDesc;
	}

	public void setPurchaseDesc(String purchaseDesc) {
		this.purchaseDesc = purchaseDesc;
	}

	public String getPurchaseInstall() {
		return purchaseInstall;
	}

	public void setPurchaseInstall(String purchaseInstall) {
		this.purchaseInstall = purchaseInstall;
	}

	public String getPurchaseRecurEnd() {
		return purchaseRecurEnd;
	}

	public void setPurchaseRecurEnd(String purchaseRecurEnd) {
		this.purchaseRecurEnd = purchaseRecurEnd;
	}

	public String getPurchaseRecurFrequency() {
		return purchaseRecurFrequency;
	}

	public void setPurchaseRecurFrequency(String purchaseRecurFrequency) {
		this.purchaseRecurFrequency = purchaseRecurFrequency;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getinvSysTransID() {
		return invSysTransID;
	}

	public void setinvSysTransID(String invSysTransID) {
		this.invSysTransID = invSysTransID;
	}

	public String getVeResultCode() {
		return veResultCode;
	}

	public void setVeResultCode(String veResultCode) {
		this.veResultCode = veResultCode;
	}

	public String getPaResultCode() {
		return paResultCode;
	}

	public void setPaResultCode(String paResultCode) {
		this.paResultCode = paResultCode;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

	public String getCavv() {
		return cavv;
	}

	public void setCavv(String cavv) {
		this.cavv = cavv;
	}

	public String getCavvAlgorithm() {
		return cavvAlgorithm;
	}

	public void setCavvAlgorithm(String cavvAlgorithm) {
		this.cavvAlgorithm = cavvAlgorithm;
	}

	public String getAcctNumberPrefix() {
		return acctNumberPrefix;
	}

	public void setAcctNumberPrefix(String acctNumberPrefix) {
		this.acctNumberPrefix = acctNumberPrefix;
	}

	public String getAcctNumberPostfix() {
		return acctNumberPostfix;
	}

	public void setAcctNumberPostfix(String acctNumberPostfix) {
		this.acctNumberPostfix = acctNumberPostfix;
	}

	public String getCardScheme() {
		return cardScheme;
	}

	public void setCardScheme(String cardScheme) {
		this.cardScheme = cardScheme;
	}
	
	

}
