//package com.invSet.emv.invSys.invSysserver.core.base.exception;
package com.invSet.emv.invSys.invSysserver.admin.exception;

import com.invSet.emv.invSys.invSysserver.admin.controller.Errors;
import com.invSet.emv.invSys.invSysserver.admin.controller.invSysData;

//import com.invSet.emv.invSys.invSysserver.core.base.enums.Errors;
//import com.invSet.emv.invSys.invSysserver.core.base.enums.invSysData;

public class invSysDataNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	private invSysData dataType;

	public invSysDataNotFoundException() {
		super(Errors.invSys_DATA_NOT_FOUND.getCode(), Errors.invSys_DATA_NOT_FOUND.getMessage());
	}

	public invSysDataNotFoundException(invSysData dataType) {
		super(Errors.invSys_DATA_NOT_FOUND.getCode(), Errors.invSys_DATA_NOT_FOUND.getMessage());
		this.dataType = dataType;
	}

	public invSysDataNotFoundException(String errorMessage, invSysData dataType) {
		super(Errors.invSys_DATA_NOT_FOUND.getCode(), errorMessage);
		this.dataType = dataType;
	}

	public invSysData getDataType() {
		return dataType;
	}

	public void setDataType(invSysData dataType) {
		this.dataType = dataType;
	}

}
