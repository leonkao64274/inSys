//package com.invSet.emv.invSys.invSysserver.core.base.service.impl;
package com.invSet.emv.invSys.invSysserver.admin.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.invSet.emv.invSys.invSysserver.admin.bean.VinvSysTransMerge;
import com.invSet.emv.invSys.invSysserver.admin.controller.invSysData;
import com.invSet.emv.invSys.invSysserver.admin.dao.VinvSysTransMergeDao;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.admin.service.VinvSysTransMergeService;
import com.invSet.emv.invSys.invSysserver.admin.util.DateTimeUtil;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.VinvSysTransMerge;
//import com.invSet.emv.invSys.invSysserver.core.base.dao.VinvSysTransMergeDao;
//import com.invSet.emv.invSys.invSysserver.core.base.enums.invSysData;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.service.VinvSysTransMergeService;
//import com.invSet.emv.invSys.invSysserver.core.base.util.DateTimeUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Exception.class})
public class BaseVinvSysTransMergeService implements VinvSysTransMergeService {

	private static final Logger logger = LoggerFactory.getLogger(BaseVinvSysTransMergeService.class);

	@Autowired
	private VinvSysTransMergeDao vinvSysTransMergeDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends VinvSysTransMerge> void save(S s) {
		// 設定資料建立、異動時間
		s.setCreateDate(DateTimeUtil.getCurrentDate());
		s.setCreateTime(DateTimeUtil.getCurrentTime());
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 新增資料數據
		this.vinvSysTransMergeDao.save(s);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends VinvSysTransMerge> void update(S s) {
		// 設定資料異動時間
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 更新資料數據
		this.vinvSysTransMergeDao.save(s);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public void delete(Long id) {
		// 依據主鍵值刪除資料數據
		this.vinvSysTransMergeDao.delete(id);
	}

	@Override
	public VinvSysTransMerge findById(Long id) {
		// 依據主鍵值查詢資料數據
		return this.vinvSysTransMergeDao.findOne(id);
	}

	@Override
	public VinvSysTransMerge findByinvSysServerTransId(String invSysServerTransID) throws invSysDataNotFoundException {
		VinvSysTransMerge VinvSysTransMerge = null;
		try {
			VinvSysTransMerge = this.vinvSysTransMergeDao.findByinvSysServerTransId(invSysServerTransID);

		} catch (Exception e) {
			logger.error("exception when finding VinvSysTransMerge by invSysServerTransId", e);
			throw new invSysDataNotFoundException(invSysData.invSys_REQUEST);
		}

		// 查無對應資料時回傳null
		if (VinvSysTransMerge == null) {
			logger.debug("VinvSysTransMerge not found by invSysServerTransId = " + invSysServerTransID);
			throw new invSysDataNotFoundException("VinvSysTransMerge not found by invSysServerTransId", invSysData.invSys_REQUEST);
		}

		return VinvSysTransMerge;
	}

	@Override
	public VinvSysTransMerge findByOid(String oid) throws invSysDataNotFoundException {
		VinvSysTransMerge VinvSysTransMerge;
		try {
			Long id = Long.parseLong(oid);
			VinvSysTransMerge = this.vinvSysTransMergeDao.findOne(id);

		} catch (IllegalArgumentException | NoSuchElementException e) {
			/*
			 * NumberFormatException: oid is parsed long failed.
			 * IllegalArgumentException: id is null.
			 * NoSuchElementException: VinvSysTransMerge not exists.
			 */
			logger.error("exception when finding VinvSysTransMerge by oid string: " + e.getClass().getName());
			throw new invSysDataNotFoundException(invSysData.invSys_REQUEST);
		}

		return VinvSysTransMerge;
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public Page<VinvSysTransMerge> findByCriteria(
        String createDateStart, String createDateEnd, String cardScheme, String messageVersion,
        String acsTransID, String invSysServerTransID, String transStatus, String acctID,
        String acctNumberPrefix, String acctNumberPostfix, String acquirerMerchantID, String deviceChannel,
        String messageCategory, String purchaseAmount,
        String integratorRequestorId, String invSysRequestorOrderId, Pageable pageable) {
        //查询总表的数据
        Page<VinvSysTransMerge> queryResult = vinvSysTransMergeDao.findByCriteria(
                createDateStart, createDateEnd, cardScheme, messageVersion,
                acsTransID, invSysServerTransID, transStatus, acctID,
                acctNumberPrefix, acctNumberPostfix, acquirerMerchantID, deviceChannel,
                messageCategory, purchaseAmount, integratorRequestorId,
                invSysRequestorOrderId, pageable);

        return queryResult;
    }
    
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
    public List<VinvSysTransMerge> findAll(){
    	return this.vinvSysTransMergeDao.findAll();
    }
    

}
