/*
 * @(#)WebKeyConst.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 Web 常數工具類別
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.util;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 Web 常數工具類別 <br>
 * 
 * 供 Spring UI Model 或 HttpSession 用常數變數指定對應的 attribute, 
 * ex: model.addAttribute(WebKeyConst.QUERY_RESULT, queryResult);
 * 
 * @author   LeonKao
 */
public class WebKeyConst {
    
    /**
     * Web 常數 - 錯誤訊息
     */
    public static final String ERRORS = "errors";
    
    /**
     * Web 常數 - 操作成功訊息
     */
    public static final String SUCCESS_MSG = "successMsg";
    
    /**
     * Web 常數 - 查詢結果
     */
    public static final String QUERY_RESULT = "queryResult";
    
}
