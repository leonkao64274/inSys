//package com.invSet.emv.invSys.invSysserver.core.base.enums;
package com.invSet.emv.invSys.invSysserver.admin.controller;

public enum Errors {

	NORMAL("4001", ""),
	ACCT_NUMBER_EMPTY("0101", "Cardholder account number is empty"),
	DATA_INVALID("0102", "Input data is invalid"),
	invSys_DATA_NOT_FOUND("1001", "Transaction original data not found"),
	invSys_MESSAGE_FAILED("2001", "Transaction message not recognized"),
	invSys_1_0_EOS("9901", "invSys 1.0 is out of service"),
	REQUESTOR_AUTHENTICATED_FAILED("9902", "Requestor's account/password authenticated failed"),
	SYSTEM_ERROR("9999", "");

	private String code;

	private String message;

	private Errors(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
