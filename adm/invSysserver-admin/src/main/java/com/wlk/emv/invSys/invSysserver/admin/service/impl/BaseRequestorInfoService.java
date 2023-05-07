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

import com.invSet.emv.invSys.invSysserver.admin.bean.RequestorInfo;
import com.invSet.emv.invSys.invSysserver.admin.dao.RequestorInfoDao;
import com.invSet.emv.invSys.invSysserver.admin.service.RequestorInfoService;
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
 * InvCore EMV invSys 系統 - 業者資訊服務操作介面基礎實作類別
 * 本類別提供產品預設實作方式，各專案可以需求自行繼承或覆寫相關介面實作方法
 * 
 * 
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Exception.class})
public class BaseRequestorInfoService implements RequestorInfoService {
    
    @Autowired
    private RequestorInfoDao requestorInfoDao;
    

    @Override
    public RequestorInfo findByRequestorId(String requestorId ) {
        return requestorInfoDao.findByRequestorId(requestorId);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public <S extends RequestorInfo> void save(S s) throws ResourceAlreadyExistException {
    	RequestorInfo requestorInfo = requestorInfoDao.findByRequestorId(s.getRequestorId());
        if (requestorInfo != null) {
            throw new ResourceAlreadyExistException("Requestor ID ("+ s.getRequestorId() +") already exist!");
        }
        
        s.setCreateDate(DateUtil.getCurrentDate());
        s.setCreateTime(DateUtil.getCurrentTime());
        s.setUpdateDate(DateUtil.getCurrentDate());
        s.setUpdateTime(DateUtil.getCurrentTime());
        requestorInfoDao.save(s);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public <S extends RequestorInfo> void update(S s) throws ResourceNotFoundException {
    	RequestorInfo requestorInfo = findById(s.getOid());
        if (requestorInfo == null) {
            throw new ResourceNotFoundException("MerchantInfo with oid(" + s.getOid() + ") does not exist!");
        }
        s.setUpdateDate(DateUtil.getCurrentDate());
        s.setUpdateTime(DateUtil.getCurrentTime());
        requestorInfoDao.save(s);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public void delete(Long id) {
    	requestorInfoDao.delete(id);
    }

    @Override
    public List<RequestorInfo> findAll() {
        List<RequestorInfo> requestorList = new ArrayList();
        requestorInfoDao.findAll().forEach((requestor) -> {
           requestorList.add(requestor);
        });
        return requestorList;
    }
    
    @Override
    public Page<RequestorInfo> findByCriteria(RequestorInfo requestorInfo, Pageable pageable) {
        // 使用 spring-data Query by Example 機制，透過 Example Matcher 指定特定欄位的條件比對方式
        ExampleMatcher matcher = ExampleMatcher.matching();
        
        
        //這邊本意是想防爆錯，但是其實都不會走到這條，因為只有有或無，不會有空值
        //而且也不用再new一個，就算真的是空的前面也已經有物件了 
        if(StringUtils.isEmpty(requestorInfo.getRequestorId()) && StringUtils.isEmpty(requestorInfo.getRequestorName())){
        	requestorInfo=new RequestorInfo();
        }
        
        return requestorInfoDao.findAll(Example.of(requestorInfo, matcher), pageable);
    }

    @Override
    public RequestorInfo findById(Long id) {
        return requestorInfoDao.findOne(id);
    }



	@Override
	public RequestorInfo findByRequestorName(String requestorName) {
		// TODO Auto-generated method stub
		return null;
	}
}
