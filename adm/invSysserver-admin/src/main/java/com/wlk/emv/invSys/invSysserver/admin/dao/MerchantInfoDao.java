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

//import com.invSet.emv.invSys.invSysserver.core.bean.MerchantInfo;

/**
 * InvCore EMV invSys 系統 - 目錄服務器設定 DAO 介面
 * 對應至資料庫 T_MERCHANT_INFO 表的新增、修改、刪除及查詢相關操作
 * 
 * @author
 */
public interface MerchantInfoDao extends CrudRepository<MerchantInfo, Long> , QueryByExampleExecutor<MerchantInfo> {
    
    /**
     * 依據 "商店代號" 取得對應的目錄服務器設定 
     * 
     * @param acquirerMerchantId 商店代號
     * @return 目錄服務器設定 
     */
    public MerchantInfo findByAcquirerMerchantId(String acquirerMerchantId);


    /**
     * 依據 "商店代號" 取得對應的目錄服務器設定 
     * 
     * @param acquirerMerchantName 商店名稱
     * @return 目錄服務器設定 
     */
    public MerchantInfo findByMerchantName(String merchantName);
    

    /**
     * 依據 "商店代號" 取得對應的目錄服務器設定 
     * 
     * @param acquirerMerchantId 商店代號 和 
     * @param acquirerMerchantName 商店名稱
     * @return 目錄服務器設定 
     */
    public MerchantInfo findByMerchantNameAndAcquirerMerchantId(String merchantName, String acquirerMerchantId);


}
