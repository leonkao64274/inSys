/*
 * @(#)DirectoryServerService.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - 目錄服務器 (DS) 業務邏輯服務介面
 *
 * Modify History:

 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.service;
//package com.invSet.emv.invSys.invSysserver.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.invSet.emv.invSys.invSysserver.admin.bean.MerchantInfo;
import com.invSet.emv.invSys.invSysserver.core.service.GenericEntityService;

import java.util.List;

/**
 * InvCore EMV invSys 系統 - 目錄服務器 (DS) 業務邏輯服務介面
 * 
 * @author 
 */
public interface MerchantInfoService extends GenericEntityService<MerchantInfo, Long>{

    /**
     * 依據 "商店代號" 取得對應的目錄服務器設定
     * 
     * @param acquirerMerchantId
     * @return 目錄服務器設定, 查無對應目錄服務器時回傳 null
     */
    public MerchantInfo findByAcquirerMerchantId(String acquirerMerchantId );
    
    /**
     * 依據 "商店名稱" 取得對應的目錄服務器設定
     * 
     * @param acquirerMerchantName
     * @return 目錄服務器設定, 查無對應目錄服務器時回傳 null
     */
    public MerchantInfo findByMerchantName(String merchantName );
    
    /**
     * 依據傳入的 "搜尋條件物件" 中各欄位指定值進行資料比對及搜尋，分頁查詢取得符合條件的CertificateRequest憑證請求檔 管理資料 Entity 集合
     * 
     * @param MerchantInfo 搜尋條件物件, 僅須針對需要比對的欄位填入值, 其餘設為 null
     * @param pageable 分頁訊息, 包含查詢頁次及每頁筆數
     * @return 符合條件的 CertificateRequest憑證請求檔 管理資料 Entity 分頁集合
     */
    public Page<MerchantInfo> findByCriteria(MerchantInfo merchantInfo, Pageable pageable);
    
    /**
     * 查詢取得全部的目錄服務器設定
     * 
     * @return 全部的目錄服務器設定
     */
    public List<MerchantInfo> findAll();
}
