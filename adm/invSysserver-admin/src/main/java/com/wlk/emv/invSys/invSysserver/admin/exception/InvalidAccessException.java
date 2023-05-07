/*
 * @(#)InvalidAccessException.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 "異常權限操作" 異常類別
 *
 * Modify History:
 * v1.00,  /09/06, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.exception;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "異常權限操作" 異常類別
 * 
 * @author   LeonKao
 */
public class InvalidAccessException extends RuntimeException {

    public InvalidAccessException(String message) {
        super(message);
    }

    public InvalidAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccessException(Throwable cause) {
        super(cause);
    }
    
}
