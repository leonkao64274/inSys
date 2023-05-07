/*
 * @(#)BaseMerchantInfoServerService.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - 目錄服務器業務服務操作介面基礎實作類別
 *
 * Modify History:
 * v1.00,  /07/17, Abner Chang
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.service.impl;
//package com.invSet.emv.invSys.invSysserver.core.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.invSet.emv.invSys.invSysserver.admin.bean.MerchantInfo;
import com.invSet.emv.invSys.invSysserver.admin.dao.MerchantInfoDao;
import com.invSet.emv.invSys.invSysserver.admin.service.MerchantInfoService;
//import com.invSet.emv.invSys.invSysserver.core.dao.MerchantInfoDao;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.service.CardService;
/*
import com.invSet.emv.invSys.invSysserver.core.dao.MerchantInfoDao;
import com.invSet.emv.invSys.invSysserver.core.bean.MerchantInfo;
import com.invSet.emv.invSys.invSysserver.core.service.MerchantInfoServer;
*/
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * InvCore EMV invSys 系統 - 目錄服務器業務服務操作介面基礎實作類別
 * 本類別提供產品預設實作方式，各專案可以需求自行繼承或覆寫相關介面實作方法
 * 
 * 
 */
//@Service("merchantInfoServer")
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Exception.class})
public class BaseMerchantInfoService implements MerchantInfoService {
    
    @Autowired
    private MerchantInfoDao merchantInfoDao;
    

    @Override
    public MerchantInfo findByAcquirerMerchantId(String acquirerMerchantId ) {
        return merchantInfoDao.findByAcquirerMerchantId(acquirerMerchantId);
    }

    @Override
    public MerchantInfo findByMerchantName(String acquirerMerchantName ) {
        return merchantInfoDao.findByMerchantName(acquirerMerchantName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public <S extends MerchantInfo> void save(S s) throws ResourceAlreadyExistException {
        MerchantInfo ds = merchantInfoDao.findByAcquirerMerchantId(s.getAcquirerMerchantId());
        if (ds != null) {
            throw new ResourceAlreadyExistException("Merchant ID ("+ s.getAcquirerMerchantId() +") already exist!");
        }
        s.setCreateDate(DateUtil.getCurrentDate());
        s.setCreateTime(DateUtil.getCurrentTime());
        s.setUpdateDate(DateUtil.getCurrentDate());
        s.setUpdateTime(DateUtil.getCurrentTime());
        merchantInfoDao.save(s);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public <S extends MerchantInfo> void update(S s) throws ResourceNotFoundException {
        MerchantInfo ds = findById(s.getOid());
        if (ds == null) {
            throw new ResourceNotFoundException("MerchantInfo with oid(" + s.getOid() + ") does not exist!");
        }
        s.setUpdateDate(DateUtil.getCurrentDate());
        s.setUpdateTime(DateUtil.getCurrentTime());
        merchantInfoDao.save(s);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public void delete(Long id) {
        merchantInfoDao.delete(id);
    }

    @Override
    public List<MerchantInfo> findAll() {
        List<MerchantInfo> dsList = new ArrayList<>();
        merchantInfoDao.findAll().forEach((t) -> {
            dsList.add(t);
        });
        return dsList;
    }
    
    @Override
    public Page<MerchantInfo> findByCriteria(MerchantInfo merchantInfo, Pageable pageable) {
        // 使用 spring-data Query by Example 機制，透過 Example Matcher 指定特定欄位的條件比對方式
        ExampleMatcher matcher = ExampleMatcher.matching();
        // 1. 執行 "查詢" 作業。
        //    商店名稱 或 商店ID 皆可查詢
        if(StringUtils.isEmpty(merchantInfo.getAcquirerMerchantId()) && StringUtils.isEmpty(merchantInfo.getMerchantName())){
        	merchantInfo=new MerchantInfo();
        }
        
        return merchantInfoDao.findAll(Example.of(merchantInfo, matcher), pageable);
    }

    @Override
    public MerchantInfo findById(Long id) {
        return merchantInfoDao.findOne(id);
    }
}
