/*
 * @(#)AcessRightCheck.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *     InvCore EMV invSys 系統 - invSys Server 後台管理系統  "功能權限檢核" 註解類別
 *
 * Modify History:
 * v1.00,  /09/06, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統  "功能權限檢核" 註解類別 <br>
 * 
 * 用於標記 控制器類別(Controller) 方法的功能權限檢核代碼
 * 
 * @author   LeonKao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AcessRightCheck {
    
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
    
}
