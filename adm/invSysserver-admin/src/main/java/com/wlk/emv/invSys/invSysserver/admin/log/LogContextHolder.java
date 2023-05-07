/*
 * @(#)LogContextHolder.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 資料儲存物件
 *
 * Modify History:
 * v1.00,   /03/19, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.log;

import java.util.HashMap;
import java.util.Map;

/**
 * InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 資料儲存物件
 * 
 * @author   LeonKao
 */
public class LogContextHolder {
    
    /**
     * 屬性常數 - 查詢條件
     */
    public static final String ATTR_DATA_QUERY = "Q";
    
    /**
     * 屬性常數 - 異動前資料
     */
    public static final String ATTR_DATA_BEFORE = "B";
    
    /**
     * 屬性常數 - 異動後資料
     */
    public static final String ATTR_DATA_AFTER  = "A";
    
    /**
     * 屬性常數 - 操作結果
     */
    public static final String ATTR_RESULT = "RESULT";
    
    /**
     * "線程" 級別儲存變數
     */
    protected static ThreadLocal<Map> logAttributes = new ThreadLocal<Map>(){
        @Override
		protected synchronized Map initialValue(){
			return new HashMap();
		}
    };

    /**
     * 取得操作記錄屬性 Map
     * @return ThreadLocal HashMap物件
     */
    public static HashMap getLogAttributes() {
        return (HashMap)logAttributes.get();
    }

    /**
     * 依據 key 值取得操作記錄屬性值
     * @param key 參數key值
     * @return 參數資料物件
     */
    public static Object getLogAttribute(String key) {
        return ((HashMap)logAttributes.get()).get(key);
    }

    /**
     * 依據 key 值新增或異動操作記錄屬性值
     * @param key 參數key值
     * @param obj 參數資料物件
     */
    public static void putLogAttribute(String key, Object obj) {
        ((HashMap)logAttributes.get()).put(key, obj);
    }

    /**
     * 依據 key 值移除異動操作記錄屬性值
     * @param key 參數key值
     */
    public static void removeLogAttribute(String key) {
        ((HashMap)logAttributes.get()).remove(key);
    }

    /**
     * 清空操作記錄屬性 Map
     */
    public static void clearLogAttributes() {
        ((HashMap)logAttributes.get()).clear();
    }
    
}
