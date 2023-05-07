/*
 * @(#)DoOperationLog.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 註解類別
 *
 * Modify History:
 * v1.00,   /03/19, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 註解類別
 * 
 * @author   LeonKao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DoOperationLog {
    
    /**
     * 指定存取Controller方法的權限代碼
     * @return 
     */
    String accessId();
    
    /**
     * 指定該功能項目下，可使用的操作行為<br>
     * 當需指定多組操作行為時，可以';'間隔
     * @return 
     */
    String operation() default "";
    
    /**
     * 操作資料物件 <br>
     * 系統檔案名稱或存取物件對象名稱 (例如 物件名稱 TABLE/VIEW/MQ QM Q/ESB Web service 名稱)
     * @return 
     */
    String targetObject() default "";
    
}
