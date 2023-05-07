/*
 * @(#)DirectoryServerDao.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - 商店服務器設定 DAO 介面
 *
 * Modify History:
 * v1.00,  /07/16
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.dao;
//package com.invSet.emv.invSys.invSysserver.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.invSet.emv.invSys.invSysserver.admin.bean.MerchantInfo;
import com.invSet.emv.invSys.invSysserver.admin.bean.RequestorInfo;

//import com.invSet.emv.invSys.invSysserver.core.bean.MerchantInfo;

/**
 * InvCore EMV invSys 系統 - 目錄服務器設定 DAO 介面
 * 對應至資料庫 T_MERCHANT_INFO 表的新增、修改、刪除及查詢相關操作
 * 
 * @author
 */
public interface RequestorInfoDao extends CrudRepository<RequestorInfo, Long> , QueryByExampleExecutor<RequestorInfo> {
    
    /**
     * @param requestor's ID  
     * @return requestor's information
     */
    public RequestorInfo findByRequestorId(String requestorId);
    
    
    /**
     * @param requestor's name 
     * @return requestor's information
     */
    
    public MerchantInfo findByRequestorName(String requestorName);
    

    /**
     * @param requestor's name 
     * @param requestor's ID  
     * @return requestor's information
     */
    public MerchantInfo findByRequestorNameAndRequestorId(String requestorName, String requestorId);



}
