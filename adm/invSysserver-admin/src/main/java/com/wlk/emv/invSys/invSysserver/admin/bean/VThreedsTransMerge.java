//package com.invSet.emv.invSys.invSysserver.core.base.bean;
package com.invSet.emv.invSys.invSysserver.admin.bean;

import com.invSet.emv.invSys.invSysserver.core.bean.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "V_invSys_TRANS_MERGE")
public class VinvSysTransMerge extends BaseBean {

	private static final Long serialVersionUID = 1L;

	@Column(name = "INTEGRATOR_OID")
    private String  integratorOid;
     
    @Column(name = "INTEGRATOR_REQUESTOR_ID")
    private String integratorRequestorId;

    @Column(name = "REQUESTOR_ORDER_ID")
    private String requestorOrderId;

    @Column(name = "ACCT_ID")
    private String acctId;

    @Column(name = "ACCT_NUMBER")
    private String acctNumber;

    @Column(name = "ACCT_NUMBER_POSTFIX")
    private String acctNumberPostfix;

    @Column(name = "ACCT_NUMBER_PREFIX")
    private String acctNumberPrefix;

    @Column(name = "ACCT_TYPE")
    private String acctType;

    @Column(name = "ACQUIRER_BIN")
    private String acquirerBIN;

    @Column(name = "ACQUIRER_MERCHANT_ID")
    private String acquirerMerchantId;

    @Column(name = "ACS_OPERATOR_ID")
    private String acsOperatorId;

    @Column(name = "ACS_REFERENCE_NUMBER")
    private String acsReferenceNumber;

    @Column(name = "ACS_SIGNED_CONTENT")
    private String acsSignedContent;

    @Column(name = "ACS_TRANS_ID")
    private String acsTransId;

    @Column(name = "ACS_URL")
    private String acsURL;

    @Column(name = "ACS_UI_INTERFACE")
    private String acsUiInterface;

    @Column(name = "ACS_UI_TYPE")
    private String acsUiType;

    @Column(name = "ACS_CHALLENGE_MANDATED")
    private String acsChallengeMandated;

    @Column(name = "ADDR_MATCH")
    private String addrMatch;

    @Column(name = "AUTHENTICATION_METHOD")
    private String authenticationMethod;

    @Column(name = "AUTHENTICATION_TYPE")
    private String authenticationType;

    @Column(name = "AUTHENTICATION_VALUE")
    private String authenticationValue;

    @Column(name = "CARD_EXPIRY_DATE")
    private String cardExpiryDate;

    @Column(name = "CARD_SCHEME")
    private String cardScheme;

    @Column(name = "CARDHOLDER_INFO")
    private String cardholderInfo;

    @Column(name = "CARDHOLDER_NAME")
    private String cardholderName;

    @Column(name = "CHALLENGE_CANCEL")
    private String challengeCancel;

    @Column(name = "DEVICE_CHANNEL")
    private String deviceChannel;

    @Column(name = "DEVICE_UI_INTERFACE")
    private String deviceUiInterface;

    @Column(name = "DEVICE_UI_TYPE")
    private String deviceUiType;

    @Column(name = "DS_REFERENCE_NUMBER")
    private String dsReferenceNumber;

    @Column(name = "DS_TRANS_ID")
    private String dsTransId;

    @Column(name = "ECI")
    private String eci;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ERROR_CODE")
    private String errorCode;

    @Column(name = "ERROR_COMPONENT")
    private String errorComponent;

    @Column(name = "ERROR_DESCRIPTION")
    private String errorDescription;

    @Column(name = "ERROR_DETAIL")
    private String errorDetail;

    @Column(name = "ERROR_MESSAGE_TYPE")
    private String errormessageType;

    @Column(name = "INTERACTION_COUNTER")
    private String interactionCounter;

    @Column(name = "MCC")
    private String mcc;

    @Column(name = "MERCHANT_COUNTRY_CODE")
    private String merchantCountryCode;

    @Column(name = "MERCHANT_NAME")
    private String merchantName;

    @Column(name = "MESSAGE_CATEGORY")
    private String messageCategory;

    @Column(name = "MESSAGE_VERSION")
    private String messageVersion;

    @Column(name = "NOTIFICATION_URL")
    private String notificationUrl;

    @Column(name = "PAY_TOKEN_IND")
    private String payTokenInd;

    @Column(name = "PMT_TRANS_ID")
    private String pmtTransId;

    @Column(name = "PURCHASE_AMOUNT")
    private String purchaseAmount;

    @Column(name = "PURCHASE_CURRENCY")
    private String purchaseCurrency;

    @Column(name = "PURCHASE_DATE")
    private String purchaseDate;

    @Column(name = "PURCHASE_EXPONENT")
    private String purchaseExponent;

    @Column(name = "PURCHASE_INSTAL_DATA")
    private String purchaseInstalData;

    @Column(name = "RECURRING_EXPIRY")
    private String recurringExpiry;

    @Column(name = "RECURRING_FREQUENCY")
    private String recurringFrequency;

    @Column(name = "REQUESTOR_NOTIFICATION_URL")
    private String requestorNotificationUrl;

    @Column(name = "RESULT_STATUS")
    private String resultStatus;

    @Column(name = "SDK_APP_ID")
    private String sdkAppId;

    @Column(name = "SDK_MAX_TIMEOUT")
    private String sdkMaxTimeout;

    @Column(name = "SDK_TRANS_ID")
    private String sdkTransId;

    @Column(name = "SDK_REFERENCE_NUMBER")
    private String sdkReferenceNumber;






    @Column(name = "SHIP_ADDR_CITY")
    private String shipAddrCity;

    @Column(name = "SHIP_ADDR_COUNTRY")
    private String shipAddrCountry;

    @Column(name = "SHIP_ADDR_LINE1")
    private String shipAddrLine1;

    @Column(name = "SHIP_ADDR_LINE2")
    private String shipAddrLine2;

    @Column(name = "SHIP_ADDR_LINE3")
    private String shipAddrLine3;

    @Column(name = "SHIP_ADDR_POST_CODE")
    private String shipAddrPostCode;

    @Column(name = "SHIP_ADDR_STATE")
    private String shipAddrState;

    @Column(name = "BILL_ADDR_CITY")
    private String billAddrCity;

    @Column(name = "BILL_ADDR_COUNTRY")
    private String billAddrCountry;

    @Column(name = "BILL_ADDR_LINE1")
    private String billAddrLine1;

    @Column(name = "BILL_ADDR_LINE2")
    private String billAddrLine2;

    @Column(name = "BILL_ADDR_LINE3")
    private String billAddrLine3;

    @Column(name = "BILL_ADDR_STATE")
    private String billAddrState;

    @Column(name = "BROWSER_ACCEPT_HEADER")
    private String browserAcceptHeader;

    @Column(name = "BROWSER_COLOR_DEPTH")
    private String browserColorDepth;

    @Column(name = "BROWSER_IP")
    private String browserIP;

    @Column(name = "BROWSER_JAVA_ENABLED")
    private String browserJavaEnabled;

    @Column(name = "BROWSER_LANGUAGE")
    private String browserLanguage;

    @Column(name = "BROWSER_SCREEN_HEIGHT")
    private String browserScreenHeight;

    @Column(name = "BROWSER_SCREEN_WIDTH")
    private String browserScreenWidth;

    @Column(name = "BROWSER_TZ")
    private String browserTZ;

    @Column(name = "BROWSER_USER_AGENT")
    private String browserUserAgent;






    @Column(name = "TESTCASE_ID")
    private String testcaseId;

    @Column(name = "invSys_COMP_IND")
    private String invSysCompInd;

    @Column(name = "invSys_REQUESTOR_3RI_IND")
    private String invSysRequestor3RIInd;

    @Column(name = "invSys_REQUESTOR_CHLG_IND")
    private String invSysRequestorChallengeInd;

    @Column(name = "invSys_REQUESTOR_ID")
    private String invSysRequestorId;

    @Column(name = "invSys_REQUESTOR_NPA_IND")
    private String invSysRequestorNPAInd;

    @Column(name = "invSys_REQUESTOR_NAME")
    private String invSysRequestorName;

    @Column(name = "invSys_REQUESTOR_URL")
    private String invSysRequestorUrl;

    @Column(name = "invSys_SERVER_OPERATOR_ID")
    private String invSysServerOperatorId;

    @Column(name = "invSys_SERVER_REF_NUMBER")
    private String invSysServerRefNumber;

    @Column(name = "invSys_SERVER_TRANS_ID")
    private String invSysServerTransId;

    @Column(name = "invSys_SERVER_URL")
    private String invSysServerURL;

    @Column(name = "invSys_SESSION_DATA")
    private String invSysSessionData;

    @Column(name = "TRANS_STATUS")
    private String transStatus;

    @Column(name = "TRANS_STATUS_REASON")
    private String transStatusReason;

    @Column(name = "TRANS_TYPE")
    private String transType;

    public String getIntegratorOid() { return integratorOid; }

    public void setIntegratorOid(String integratorOid) {
        this.integratorOid = integratorOid;
    }

    public String getIntegratorRequestorId() {
        return integratorRequestorId;
    }

    public void setIntegratorRequestorId(String integratorRequestorId) {
        this.integratorRequestorId = integratorRequestorId;
    }

    public String getRequestorOrderId() {
        return requestorOrderId;
    }

    public void setRequestorOrderId(String requestorOrderId) {
        this.requestorOrderId = requestorOrderId;
    }

    public String getAcctId() {
        return acctId;
    }

    public void setAcctId(String acctId) {
        this.acctId = acctId;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getAcctNumberPostfix() {
        return acctNumberPostfix;
    }

    public void setAcctNumberPostfix(String acctNumberPostfix) {
        this.acctNumberPostfix = acctNumberPostfix;
    }

    public String getAcctNumberPrefix() {
        return acctNumberPrefix;
    }

    public void setAcctNumberPrefix(String acctNumberPrefix) {
        this.acctNumberPrefix = acctNumberPrefix;
    }

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }

    public String getAcquirerBIN() {
        return acquirerBIN;
    }

    public void setAcquirerBIN(String acquirerBIN) {
        this.acquirerBIN = acquirerBIN;
    }

    public String getAcquirerMerchantId() {
        return acquirerMerchantId;
    }

    public void setAcquirerMerchantId(String acquirerMerchantId) {
        this.acquirerMerchantId = acquirerMerchantId;
    }

    public String getAcsOperatorId() {
        return acsOperatorId;
    }

    public void setAcsOperatorId(String acsOperatorId) {
        this.acsOperatorId = acsOperatorId;
    }

    public String getAcsReferenceNumber() {
        return acsReferenceNumber;
    }

    public void setAcsReferenceNumber(String acsReferenceNumber) {
        this.acsReferenceNumber = acsReferenceNumber;
    }

    public String getAcsSignedContent() {
        return acsSignedContent;
    }

    public void setAcsSignedContent(String acsSignedContent) {
        this.acsSignedContent = acsSignedContent;
    }

    public String getAcsTransId() {
        return acsTransId;
    }

    public void setAcsTransId(String acsTransId) {
        this.acsTransId = acsTransId;
    }

    public String getAcsURL() {
        return acsURL;
    }

    public void setAcsURL(String acsURL) {
        this.acsURL = acsURL;
    }

    public String getAcsUiInterface() {
        return acsUiInterface;
    }

    public void setAcsUiInterface(String acsUiInterface) {
        this.acsUiInterface = acsUiInterface;
    }

    public String getAcsUiType() {
        return acsUiType;
    }

    public void setAcsUiType(String acsUiType) {
        this.acsUiType = acsUiType;
    }

    public String getAcsChallengeMandated() {
        return acsChallengeMandated;
    }

    public void setAcsChallengeMandated(String acsChallengeMandated) {
        this.acsChallengeMandated = acsChallengeMandated;
    }

    public String getAddrMatch() {
        return addrMatch;
    }

    public void setAddrMatch(String addrMatch) {
        this.addrMatch = addrMatch;
    }

    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(String authenticationType) {
        this.authenticationType = authenticationType;
    }

    public String getAuthenticationValue() {
        return authenticationValue;
    }

    public void setAuthenticationValue(String authenticationValue) {
        this.authenticationValue = authenticationValue;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardScheme() {
        return cardScheme;
    }

    public void setCardScheme(String cardScheme) {
        this.cardScheme = cardScheme;
    }

    public String getCardholderInfo() {
        return cardholderInfo;
    }

    public void setCardholderInfo(String cardholderInfo) {
        this.cardholderInfo = cardholderInfo;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getChallengeCancel() {
        return challengeCancel;
    }

    public void setChallengeCancel(String challengeCancel) {
        this.challengeCancel = challengeCancel;
    }

    public String getDeviceChannel() {
        return deviceChannel;
    }

    public void setDeviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
    }

    public String getDeviceUiInterface() {
        return deviceUiInterface;
    }

    public void setDeviceUiInterface(String deviceUiInterface) {
        this.deviceUiInterface = deviceUiInterface;
    }

    public String getDeviceUiType() {
        return deviceUiType;
    }

    public void setDeviceUiType(String deviceUiType) {
        this.deviceUiType = deviceUiType;
    }

    public String getDsReferenceNumber() {
        return dsReferenceNumber;
    }

    public void setDsReferenceNumber(String dsReferenceNumber) {
        this.dsReferenceNumber = dsReferenceNumber;
    }

    public String getDsTransId() {
        return dsTransId;
    }

    public void setDsTransId(String dsTransId) {
        this.dsTransId = dsTransId;
    }

    public String getEci() {
        return eci;
    }

    public void setEci(String eci) {
        this.eci = eci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorComponent() {
        return errorComponent;
    }

    public void setErrorComponent(String errorComponent) {
        this.errorComponent = errorComponent;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrormessageType() {
        return errormessageType;
    }

    public void setErrormessageType(String errormessageType) {
        this.errormessageType = errormessageType;
    }

    public String getInteractionCounter() {
        return interactionCounter;
    }

    public void setInteractionCounter(String interactionCounter) {
        this.interactionCounter = interactionCounter;
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

    public String getMessageCategory() {
        return messageCategory;
    }

    public void setMessageCategory(String messageCategory) {
        this.messageCategory = messageCategory;
    }

    public String getMessageVersion() {
        return messageVersion;
    }

    public void setMessageVersion(String messageVersion) {
        this.messageVersion = messageVersion;
    }

    public String getNotificationUrl() {
        return notificationUrl;
    }

    public void setNotificationUrl(String notificationUrl) {
        this.notificationUrl = notificationUrl;
    }

    public String getPayTokenInd() {
        return payTokenInd;
    }

    public void setPayTokenInd(String payTokenInd) {
        this.payTokenInd = payTokenInd;
    }

    public String getPmtTransId() {
        return pmtTransId;
    }

    public void setPmtTransId(String pmtTransId) {
        this.pmtTransId = pmtTransId;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public String getPurchaseCurrency() {
        return purchaseCurrency;
    }

    public void setPurchaseCurrency(String purchaseCurrency) {
        this.purchaseCurrency = purchaseCurrency;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseExponent() {
        return purchaseExponent;
    }

    public void setPurchaseExponent(String purchaseExponent) {
        this.purchaseExponent = purchaseExponent;
    }

    public String getPurchaseInstalData() {
        return purchaseInstalData;
    }

    public void setPurchaseInstalData(String purchaseInstalData) {
        this.purchaseInstalData = purchaseInstalData;
    }

    public String getRecurringExpiry() {
        return recurringExpiry;
    }

    public void setRecurringExpiry(String recurringExpiry) {
        this.recurringExpiry = recurringExpiry;
    }

    public String getRecurringFrequency() {
        return recurringFrequency;
    }

    public void setRecurringFrequency(String recurringFrequency) {
        this.recurringFrequency = recurringFrequency;
    }

    public String getRequestorNotificationUrl() {
        return requestorNotificationUrl;
    }

    public void setRequestorNotificationUrl(String requestorNotificationUrl) {
        this.requestorNotificationUrl = requestorNotificationUrl;
    }

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    public String getSdkAppId() {
        return sdkAppId;
    }

    public void setSdkAppId(String sdkAppId) {
        this.sdkAppId = sdkAppId;
    }

    public String getSdkMaxTimeout() {
        return sdkMaxTimeout;
    }

    public void setSdkMaxTimeout(String sdkMaxTimeout) {
        this.sdkMaxTimeout = sdkMaxTimeout;
    }

    public String getSdkTransId() {
        return sdkTransId;
    }

    public void setSdkTransId(String sdkTransId) {
        this.sdkTransId = sdkTransId;
    }

    public String getSdkReferenceNumber() {
        return sdkReferenceNumber;
    }

    public void setSdkReferenceNumber(String sdkReferenceNumber) {
        this.sdkReferenceNumber = sdkReferenceNumber;
    }

    public String getShipAddrCity() {
        return shipAddrCity;
    }

    public void setShipAddrCity(String shipAddrCity) {
        this.shipAddrCity = shipAddrCity;
    }

    public String getShipAddrCountry() {
        return shipAddrCountry;
    }

    public void setShipAddrCountry(String shipAddrCountry) {
        this.shipAddrCountry = shipAddrCountry;
    }

    public String getShipAddrLine1() {
        return shipAddrLine1;
    }

    public void setShipAddrLine1(String shipAddrLine1) {
        this.shipAddrLine1 = shipAddrLine1;
    }

    public String getShipAddrLine2() {
        return shipAddrLine2;
    }

    public void setShipAddrLine2(String shipAddrLine2) {
        this.shipAddrLine2 = shipAddrLine2;
    }

    public String getShipAddrLine3() {
        return shipAddrLine3;
    }

    public void setShipAddrLine3(String shipAddrLine3) {
        this.shipAddrLine3 = shipAddrLine3;
    }

    public String getShipAddrPostCode() {
        return shipAddrPostCode;
    }

    public void setShipAddrPostCode(String shipAddrPostCode) {
        this.shipAddrPostCode = shipAddrPostCode;
    }

    public String getShipAddrState() {
        return shipAddrState;
    }

    public void setShipAddrState(String shipAddrState) {
        this.shipAddrState = shipAddrState;
    }

    public String getBillAddrCity() {
        return billAddrCity;
    }

    public void setBillAddrCity(String billAddrCity) {
        this.billAddrCity = billAddrCity;
    }

    public String getBillAddrCountry() {
        return billAddrCountry;
    }

    public void setBillAddrCountry(String billAddrCountry) {
        this.billAddrCountry = billAddrCountry;
    }

    public String getBillAddrLine1() {
        return billAddrLine1;
    }

    public void setBillAddrLine1(String billAddrLine1) {
        this.billAddrLine1 = billAddrLine1;
    }

    public String getBillAddrLine2() {
        return billAddrLine2;
    }

    public void setBillAddrLine2(String billAddrLine2) {
        this.billAddrLine2 = billAddrLine2;
    }

    public String getBillAddrLine3() {
        return billAddrLine3;
    }

    public void setBillAddrLine3(String billAddrLine3) {
        this.billAddrLine3 = billAddrLine3;
    }

    public String getBillAddrState() {
        return billAddrState;
    }

    public void setBillAddrState(String billAddrState) {
        this.billAddrState = billAddrState;
    }

    public String getBrowserAcceptHeader() {
        return browserAcceptHeader;
    }

    public void setBrowserAcceptHeader(String browserAcceptHeader) {
        this.browserAcceptHeader = browserAcceptHeader;
    }

    public String getBrowserColorDepth() {
        return browserColorDepth;
    }

    public void setBrowserColorDepth(String browserColorDepth) {
        this.browserColorDepth = browserColorDepth;
    }

    public String getBrowserIP() {
        return browserIP;
    }

    public void setBrowserIP(String browserIP) {
        this.browserIP = browserIP;
    }

    public String getBrowserJavaEnabled() {
        return browserJavaEnabled;
    }

    public void setBrowserJavaEnabled(String browserJavaEnabled) {
        this.browserJavaEnabled = browserJavaEnabled;
    }

    public String getBrowserLanguage() {
        return browserLanguage;
    }

    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage;
    }

    public String getBrowserScreenHeight() {
        return browserScreenHeight;
    }

    public void setBrowserScreenHeight(String browserScreenHeight) {
        this.browserScreenHeight = browserScreenHeight;
    }

    public String getBrowserScreenWidth() {
        return browserScreenWidth;
    }

    public void setBrowserScreenWidth(String browserScreenWidth) {
        this.browserScreenWidth = browserScreenWidth;
    }

    public String getBrowserTZ() {
        return browserTZ;
    }

    public void setBrowserTZ(String browserTZ) {
        this.browserTZ = browserTZ;
    }

    public String getBrowserUserAgent() {
        return browserUserAgent;
    }

    public void setBrowserUserAgent(String browserUserAgent) {
        this.browserUserAgent = browserUserAgent;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getinvSysCompInd() {
        return invSysCompInd;
    }

    public void setinvSysCompInd(String invSysCompInd) {
        this.invSysCompInd = invSysCompInd;
    }

    public String getinvSysRequestor3RIInd() {
        return invSysRequestor3RIInd;
    }

    public void setinvSysRequestor3RIInd(String invSysRequestor3RIInd) {
        this.invSysRequestor3RIInd = invSysRequestor3RIInd;
    }

    public String getinvSysRequestorChallengeInd() {
        return invSysRequestorChallengeInd;
    }

    public void setinvSysRequestorChallengeInd(String invSysRequestorChallengeInd) {
        this.invSysRequestorChallengeInd = invSysRequestorChallengeInd;
    }

    public String getinvSysRequestorId() {
        return invSysRequestorId;
    }

    public void setinvSysRequestorId(String invSysRequestorId) {
        this.invSysRequestorId = invSysRequestorId;
    }

    public String getinvSysRequestorNPAInd() {
        return invSysRequestorNPAInd;
    }

    public void setinvSysRequestorNPAInd(String invSysRequestorNPAInd) {
        this.invSysRequestorNPAInd = invSysRequestorNPAInd;
    }

    public String getinvSysRequestorName() {
        return invSysRequestorName;
    }

    public void setinvSysRequestorName(String invSysRequestorName) {
        this.invSysRequestorName = invSysRequestorName;
    }

    public String getinvSysRequestorUrl() {
        return invSysRequestorUrl;
    }

    public void setinvSysRequestorUrl(String invSysRequestorUrl) {
        this.invSysRequestorUrl = invSysRequestorUrl;
    }

    public String getinvSysServerOperatorId() {
        return invSysServerOperatorId;
    }

    public void setinvSysServerOperatorId(String invSysServerOperatorId) {
        this.invSysServerOperatorId = invSysServerOperatorId;
    }

    public String getinvSysServerRefNumber() {
        return invSysServerRefNumber;
    }

    public void setinvSysServerRefNumber(String invSysServerRefNumber) {
        this.invSysServerRefNumber = invSysServerRefNumber;
    }

    public String getinvSysServerTransId() {
        return invSysServerTransId;
    }

    public void setinvSysServerTransId(String invSysServerTransId) {
        this.invSysServerTransId = invSysServerTransId;
    }

    public String getinvSysServerURL() {
        return invSysServerURL;
    }

    public void setinvSysServerURL(String invSysServerURL) {
        this.invSysServerURL = invSysServerURL;
    }

    public String getinvSysSessionData() {
        return invSysSessionData;
    }

    public void setinvSysSessionData(String invSysSessionData) {
        this.invSysSessionData = invSysSessionData;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getTransStatusReason() {
        return transStatusReason;
    }

    public void setTransStatusReason(String transStatusReason) {
        this.transStatusReason = transStatusReason;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }
}
