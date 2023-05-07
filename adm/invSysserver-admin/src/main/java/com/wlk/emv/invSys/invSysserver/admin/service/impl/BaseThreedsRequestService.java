//package com.invSet.emv.invSys.invSysserver.core.base.service.impl;
package com.invSet.emv.invSys.invSysserver.admin.service.impl;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRequest;
//import com.invSet.emv.invSys.invSysserver.core.base.dao.invSysRequestDao;
//import com.invSet.emv.invSys.invSysserver.core.base.enums.invSysData;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.service.invSysRequestService;
//import com.invSet.emv.invSys.invSysserver.core.base.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRequest;

import com.invSet.emv.invSys.invSysserver.admin.controller.invSysData;

import com.invSet.emv.invSys.invSysserver.admin.dao.invSysRequestDao;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.admin.service.invSysRequestService;
import com.invSet.emv.invSys.invSysserver.admin.util.DateTimeUtil;

import java.util.NoSuchElementException;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Exception.class})
public class BaseinvSysRequestService implements invSysRequestService {

	private static final Logger logger = LoggerFactory.getLogger(BaseinvSysRequestService.class);

	@Autowired
	private invSysRequestDao invSysRequestDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends invSysRequest> void save(S s) {
		// 設定資料建立、異動時間
		s.setCreateDate(DateTimeUtil.getCurrentDate());
		s.setCreateTime(DateTimeUtil.getCurrentTime());
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 新增資料數據
		this.invSysRequestDao.save(s);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends invSysRequest> void update(S s) {
		// 設定資料異動時間
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 更新資料數據
		this.invSysRequestDao.save(s);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public void delete(Long id) {
		// 依據主鍵值刪除資料數據
		this.invSysRequestDao.delete(id);
	}

	@Override
	public invSysRequest findById(Long id) {
		// 依據主鍵值查詢資料數據
		return this.invSysRequestDao.findOne(id);
	}

	@Override
	public invSysRequest findByinvSysServerTransID(String invSysServerTransID) throws invSysDataNotFoundException {
		invSysRequest invSysRequest = null;
		try {
			invSysRequest = this.invSysRequestDao.findByinvSysServerTransID(invSysServerTransID);

		} catch (Exception e) {
			logger.error("exception when finding invSysRequest by invSysServerTransId", e);
			throw new invSysDataNotFoundException(invSysData.invSys_REQUEST);
		}

		// 查無對應資料時回傳null
		if (invSysRequest == null) {
			logger.debug("invSysRequest not found by invSysServerTransId = " + invSysServerTransID);
			throw new invSysDataNotFoundException("invSysRequest not found by invSysServerTransId", invSysData.invSys_REQUEST);
		}

		return invSysRequest;
	}

	@Override
	public invSysRequest findByOid(String oid) throws invSysDataNotFoundException {
		invSysRequest invSysRequest;
		try {
			Long id = Long.parseLong(oid);
			invSysRequest = this.invSysRequestDao.findOne(id);

		} catch (IllegalArgumentException | NoSuchElementException e) {
			/*
			 * NumberFormatException: oid is parsed long failed.
			 * IllegalArgumentException: id is null.
			 * NoSuchElementException: invSysRequest not exists.
			 */
			logger.error("exception when finding invSysRequest by oid string: " + e.getClass().getName());
			throw new invSysDataNotFoundException(invSysData.invSys_REQUEST);
		}

		return invSysRequest;
	}

}
