//package com.invSet.emv.invSys.invSysserver.core.base.enums;
package com.invSet.emv.invSys.invSysserver.admin.controller;

public enum invSysData {

	/* ==========
	 * 	Class
	 * ==========
	 */
	// 驗證交易請求
	invSys_REQUEST,
	// 1.0 驗證交易
	invSys_RECORD,
	// 2.0 驗證交易
	invSys_TRANS,

	/* ==========
	 * 	Field
	 * ==========
	 */
	// invSys交易序號
	invSys_TRANS_ID,
	// 持卡人帳戶號碼(ex: 信用卡號)
	ACCT_NUMBER

}
