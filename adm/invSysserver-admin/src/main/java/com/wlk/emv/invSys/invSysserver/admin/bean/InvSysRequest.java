//package com.invSet.emv.invSys.invSysserver.core.base.bean;
package com.invSet.emv.invSys.invSysserver.admin.bean;

import com.invSet.emv.invSys.invSysserver.core.bean.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "T_I_invSys_REQUEST")
public class invSysRequest extends BaseBean {

	private static final long serialVersionUID = 1L;

	/**
	 * 請求處理狀態
	 */
	@Column(name = "STATUS",length = 2)
	private String status;

	/**
	 * 請求來源數據
	 */
	@Lob
	@Column(name = "ORI_DATA", length = 9999, nullable = true)
	private String origData;

	/**
	 * 用戶端模式
	 */
	@Column(name = "CLIENT_MODE",length = 1)
	private String clientMode;

	/**
	 * invSys Requester通知接收URL
	 */
	@Column(name = "NOTIFICATION_URL",length = 256)
	private String notificationURL;

	/**
	 * invSys Requester是否接收驗證結果數據
	 */
	@Column(name = "ENABLE_RESULT",length = 1)
	private String enableResult;

	/**
	 * 是否須執行invSys Method
	 */
	@Column(name = "ENABLE_METHOD",length = 1)
	private String enableMethod;

	/**
	 * invSys Server交易序號
	 */
	@Column(name = "invSys_SERVER_TRANS_ID",length = 36)
	private String invSysServerTransID;

	/**
	 * invSys Method執行結果標記
	 */
	@Column(name = "invSys_COMP_IND",length = 1)
	private String invSysCompInd;

	/**
	 * ACS版本號
	 */
	@Column(name = "ACS_VERSION",length = 8)
	private String acsVersion;

	/**
	 * 是否停用invSys 2.0 Challenge機制
	 */
	@Column(name = "DISABLE_CHALLENGE",length = 1)
	private String disableChallenge;

    /**
     * invSys Requestor端交易訂單序號
     */
    @Column(name = "invSys_REQUESTOR_ORDER_ID",length = 35)
    private String invSysRequestorOrderID;

    /**
     * invSys DS列管的Requestor識別碼
     */
    @Column(name = "invSys_REQUESTOR_ID",length = 35)
    private String invSysRequestorID;

    /**
     * invSys Integrator列管的Requestor帳號
     */
    @Column(name = "INTEGRATOR_REQUESTOR_ID",length = 36)
    private String integratorRequestorID;

    /**
     * invSys Acquirer編列的商戶識別碼
     */
    @Column(name = "MERCHANT_ID",length = 35)
    private String merchantID;

	public invSysRequest() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrigData() {
		return origData;
	}

	public void setOrigData(String origData) {
		this.origData = origData;
	}

	public String getClientMode() {
		return clientMode;
	}

	public void setClientMode(String clientMode) {
		this.clientMode = clientMode;
	}

	public String getNotificationURL() {
		return notificationURL;
	}

	public void setNotificationURL(String notificationURL) {
		this.notificationURL = notificationURL;
	}

	public String getEnableResult() {
		return enableResult;
	}

	public void setEnableResult(String enableResult) {
		this.enableResult = enableResult;
	}

	public String getEnableMethod() {
		return enableMethod;
	}

	public void setEnableMethod(String enableMethod) {
		this.enableMethod = enableMethod;
	}

	public String getinvSysServerTransID() {
		return invSysServerTransID;
	}

	public void setinvSysServerTransID(String invSysServerTransID) {
		this.invSysServerTransID = invSysServerTransID;
	}

	public String getinvSysCompInd() {
		return invSysCompInd;
	}

	public void setinvSysCompInd(String invSysCompInd) {
		this.invSysCompInd = invSysCompInd;
	}

	public String getAcsVersion() {
		return acsVersion;
	}

	public void setAcsVersion(String acsVersion) {
		this.acsVersion = acsVersion;
	}

	public String getDisableChallenge() {
		return disableChallenge;
	}

	public void setDisableChallenge(String disableChallenge) {
		this.disableChallenge = disableChallenge;
	}

    public String getinvSysRequestorOrderID() {
        return invSysRequestorOrderID;
    }

    public void setinvSysRequestorOrderID(String invSysRequestorOrderID) {
        this.invSysRequestorOrderID = invSysRequestorOrderID;
    }

    public String getinvSysRequestorID() {
        return invSysRequestorID;
    }

    public void setinvSysRequestorID(String invSysRequestorID) {
        this.invSysRequestorID = invSysRequestorID;
    }

    public String getIntegratorRequestorID() {
        return integratorRequestorID;
    }

    public void setIntegratorRequestorID(String integratorRequestorID) {
        this.integratorRequestorID = integratorRequestorID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }
}
