//package com.invSet.emv.invSys.invSysserver.core.base.service;
package com.invSet.emv.invSys.invSysserver.admin.service;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRequest;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRequest;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.GenericEntityService;

public interface invSysRequestService extends GenericEntityService<invSysRequest, Long> {

	/**
	 * 依據invSys Server交易序號查詢對應的invSys驗證請求資料
	 *
	 * @param invSysServerTransID invSys Server交易序號
	 * @return 對應的invSys驗證請求資料
	 * @throws invSysDataNotFoundException 無法取得對應資料時拋出
	 */
	public invSysRequest findByinvSysServerTransID(String invSysServerTransID) throws invSysDataNotFoundException;

	/**
	 * 依據oid值字串查詢對應的invSys驗證請求資料
	 *
	 * @param oid oid字串
	 * @return 對應的invSys驗證請求資料
	 * @throws invSysDataNotFoundException 無法取得對應資料時拋出
	 */
	public invSysRequest findByOid(String oid) throws invSysDataNotFoundException;
}
