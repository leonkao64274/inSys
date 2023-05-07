/*
 * @(#)AppProperties.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 properties 工具類別
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組 properties 工具類別
 * 
 * @author   LeonKao
 */
@Component
public class AppProperties {
    
    /**
     * 分頁筆數
     */
    @Value("${app.paginator.size}")
    private int pageSize;

    public int getPageSize() {
        return pageSize == 0 ? 5: pageSize;
    }
    
}
