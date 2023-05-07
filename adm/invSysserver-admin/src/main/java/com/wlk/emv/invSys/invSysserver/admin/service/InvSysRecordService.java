//package com.invSet.emv.invSys.invSysserver.core.base.service;
package com.invSet.emv.invSys.invSysserver.admin.service;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRecord;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRecord;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.GenericEntityService;

public interface invSysRecordService extends GenericEntityService<invSysRecord, Long> {

	/**
	 * 依據invSys交易序號查詢對應的invSys 1.0驗證交易資料
	 *
	 * @param invSysTransID invSys交易序號
	 * @return 對應的invSys 1.0驗證交易資料
	 * @throws invSysDataNotFoundException 無法取得對應資料時拋出
	 */
	public invSysRecord findByinvSysTransID(String invSysTransID) throws invSysDataNotFoundException;

}
