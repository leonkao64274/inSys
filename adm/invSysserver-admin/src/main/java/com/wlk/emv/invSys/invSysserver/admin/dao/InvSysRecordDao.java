//package com.invSet.emv.invSys.invSysserver.core.base.dao;
package com.invSet.emv.invSys.invSysserver.admin.dao;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRecord;

public interface invSysRecordDao extends JpaRepository<invSysRecord, Long> {

	/**
	 * 依據invSys交易序號查詢對應的invSys 1.0驗證交易資料
	 *
	 * @param invSysTransID invSys交易序號
	 * @return 對應的invSys 1.0驗證交易資料
	 */
	public invSysRecord findByinvSysTransID(String invSysTransID);

}
