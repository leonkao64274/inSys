//package com.invSet.emv.invSys.invSysserver.core.base.service;
package com.invSet.emv.invSys.invSysserver.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.invSet.emv.invSys.invSysserver.admin.bean.VinvSysTransMerge;
import com.invSet.emv.invSys.invSysserver.admin.exception.invSysDataNotFoundException;
//import com.invSet.emv.invSys.invSysserver.core.base.bean.VinvSysTransMerge;
//import com.invSet.emv.invSys.invSysserver.core.base.exception.invSysDataNotFoundException;
import com.invSet.emv.invSys.invSysserver.core.service.GenericEntityService;

public interface VinvSysTransMergeService extends GenericEntityService<VinvSysTransMerge, Long> {

	/**
	 * 依據invSys Server交易序號查詢對應的invSys驗證請求資料
	 *
	 * @param invSysServerTransID invSys Server交易序號
	 * @return 對應的invSys驗證請求資料
	 * @throws invSysDataNotFoundException 無法取得對應資料時拋出
	 */
	public VinvSysTransMerge findByinvSysServerTransId(String invSysServerTransID) throws invSysDataNotFoundException;

	/**
	 * 依據oid值字串查詢對應的invSys驗證請求資料
	 *
	 * @param oid oid字串
	 * @return 對應的invSys驗證請求資料
	 * @throws invSysDataNotFoundException 無法取得對應資料時拋出
	 */
	public VinvSysTransMerge findByOid(String oid) throws invSysDataNotFoundException;


	/**
	 *
	 * @Description: 查询交易信息
	 *
	 * @auther: liuhao
	 * @date: 13:47  /6/15
	 * @param: [var1, var2, var3, var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17]
	 * @return: org.springframework.data.domain.Page<com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRequest>
	 *
	 */
    Page<VinvSysTransMerge> findByCriteria(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8, String var9, String var10, String var11, String var12, String var13, String var14, String var15, String var16, Pageable var17);
    
    List<VinvSysTransMerge> findAll();
    
}
