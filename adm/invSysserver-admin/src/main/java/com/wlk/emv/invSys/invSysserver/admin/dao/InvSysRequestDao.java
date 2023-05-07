//package com.invSet.emv.invSys.invSysserver.core.base.dao;
package com.invSet.emv.invSys.invSysserver.admin.dao;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.invSet.emv.invSys.invSysserver.admin.bean.invSysRequest;

public interface invSysRequestDao extends JpaRepository<invSysRequest, Long> {

	/**
	 * 依據invSys Server交易序號查詢對應的invSys驗證請求資料
	 *
	 * @param invSysServerTransID invSys Server交易序號
	 * @return 對應的invSys驗證請求資料
	 */
	public invSysRequest findByinvSysServerTransID(String invSysServerTransID);

}
