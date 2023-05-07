
package com.invSet.emv.invSys.invSysserver.admin.exception;

import com.invSet.emv.invSys.invSysserver.admin.controller.Errors;


public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	protected String errorCode;

	protected String errorMessage;

	protected String errorDetail;

	public BaseException() {
		super();
	}

	public BaseException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public BaseException(Errors error) {
		super();
		this.errorCode = error.getCode();
		this.errorMessage = error.getMessage();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

}
