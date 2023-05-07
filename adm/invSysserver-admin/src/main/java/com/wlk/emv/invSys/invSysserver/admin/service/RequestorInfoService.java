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

import com.invSet.emv.invSys.invSysserver.admin.bean.RequestorInfo;
import com.invSet.emv.invSys.invSysserver.core.service.GenericEntityService;

import java.util.List;

/**
 * InvCore EMV invSys 系統 - 業者管理 業務邏輯服務介面
 * 
 * @author LeonKao
 */
public interface RequestorInfoService extends GenericEntityService<RequestorInfo, Long>{

    /**
     * @param requestorid  
     * @return requestor's information
     */
	public RequestorInfo findByRequestorId(String requestorId ) ;
	
	
    /**
     * 
     * @param acquirerMerchantName
     * @return requestor's information. If not found, return null.
     */

    public RequestorInfo findByRequestorName(String requestorName);
    
 
    /**
     * 依據傳入的 "搜尋條件物件" 中各欄位指定值進行資料比對及搜尋，分頁查詢取得符合條件的CertificateRequest憑證請求檔 管理資料 Entity 集合
     * 
     * @param MerchantInfo 搜尋條件物件, 僅須針對需要比對的欄位填入值, 其餘設為 null
     * @param pageable 分頁訊息, 包含查詢頁次及每頁筆數
     * @return 符合條件的 CertificateRequest憑證請求檔 管理資料 Entity 分頁集合
     */
    public Page<RequestorInfo> findByCriteria(RequestorInfo requestorInfo, Pageable pageable);
    
    /**
     * 查詢取得全部的目錄服務器設定
     * 
     * @return 全部的目錄服務器設定
     */
    public List<RequestorInfo> findAll();
}
