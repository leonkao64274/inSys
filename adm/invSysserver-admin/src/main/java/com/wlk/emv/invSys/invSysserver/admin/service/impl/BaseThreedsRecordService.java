//package com.invSet.emv.invSys.invSysserver.core.base.service.impl;
package com.invSet.emv.invSys.invSysserver.admin.service.impl;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRecord;
//import com.invSet.emv.invSys.invSysserver.core.base.dao.invSysRecordDao;
//import com.invSet.emv.invSys.invSysserver.core.base.enums.invSysData;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.service.invSysRecordService;
//import com.invSet.emv.invSys.invSysserver.core.base.util.DateTimeUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRecord;
import com.invSet.emv.invSys.invSysserver.admin.util.DateTimeUtil;
import com.invSet.emv.invSys.invSysserver.admin.controller.invSysData;

import com.invSet.emv.invSys.invSysserver.admin.dao.invSysRecordDao;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.admin.service.invSysRecordService;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true, rollbackFor = {Exception.class})
public class BaseinvSysRecordService implements invSysRecordService {

	private static final Logger logger = LoggerFactory.getLogger(BaseinvSysRequestService.class);

	@Autowired
	private invSysRecordDao invSysRecordDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends invSysRecord> void save(S s) {
		// 設定資料建立、異動時間
		s.setCreateDate(DateTimeUtil.getCurrentDate());
		s.setCreateTime(DateTimeUtil.getCurrentTime());
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 新增資料數據
		this.invSysRecordDao.save(s);

		// 產生invSys 1.0交易序號
		s.setinvSysTransID(this.generateinvSysTransID(s.getOid().toString()));
	}

	private String generateinvSysTransID(String oid) {
		StringBuilder sb = new StringBuilder(DateTimeUtil.getCurrentDateTime());
		if (oid.length() < 6)
			sb.append(StringUtils.leftPad(oid, 6, "0"));
		else
			sb.append(StringUtils.right(oid, 6));
		return sb.toString();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public <S extends invSysRecord> void update(S s) {
		// 設定資料異動時間
		s.setUpdateDate(DateTimeUtil.getCurrentDate());
		s.setUpdateTime(DateTimeUtil.getCurrentTime());
		// 更新資料數據
		this.invSysRecordDao.save(s);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, rollbackFor = {Exception.class})
	public void delete(Long id) {
		// 依據主鍵值刪除資料數據
		this.invSysRecordDao.delete(id);
	}

	@Override
	public invSysRecord findById(Long id) {
		// 依據主鍵值查詢資料數據
		return this.invSysRecordDao.findOne(id);
	}

	@Override
	public invSysRecord findByinvSysTransID(String invSysTransID) throws invSysDataNotFoundException {
		invSysRecord invSysRecord = null;
		try {
			invSysRecord = this.invSysRecordDao.findByinvSysTransID(invSysTransID);

		} catch (Exception e) {
			logger.error("exception when finding invSysRecord by invSysTransId", e);
			throw new invSysDataNotFoundException(invSysData.invSys_RECORD);
		}

		// 查無對應資料時回傳null
		if (invSysRecord == null) {
			logger.debug("invSysRecord not found by invSysTransId = " + invSysTransID);
			throw new invSysDataNotFoundException("invSysRecord not found by invSysTransId", invSysData.invSys_RECORD);
		}

		return invSysRecord;
	}

}
