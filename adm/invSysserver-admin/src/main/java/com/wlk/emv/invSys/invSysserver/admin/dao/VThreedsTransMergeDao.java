//package com.invSet.emv.invSys.invSysserver.core.base.dao;
package com.invSet.emv.invSys.invSysserver.admin.dao;

//import com.invSet.emv.invSys.invSysserver.core.base.bean.VinvSysTransMerge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.invSet.emv.invSys.invSysserver.admin.bean.VinvSysTransMerge;

public interface VinvSysTransMergeDao extends JpaRepository<VinvSysTransMerge, Long> {

	/**
	 * 依據invSys Server交易序號查詢對應的invSys驗證請求資料
	 *
	 * @param invSysServerTransID invSys Server交易序號
	 * @return 對應的invSys驗證請求資料
	 */
	public VinvSysTransMerge findByinvSysServerTransId(String invSysServerTransID);

    /**
     *
     * @Description: 根据条件查询
     *
     * @auther: liuhao
     * @date: 13:51  /6/15
     * @param: [createDateStart, createDateEnd, cardScheme, messageVersion, acsTransID, invSysServerTransID, transStatus, acctID, acctNumberPrefix, acctNumberPostfix, acquirerMerchantID, deviceChannel, messageCategory, purchaseAmount, integratorRequestorId, invSysRequestorOrderId, pageable]
     * @return: org.springframework.data.domain.Page<com.invSet.emv.invSys.invSysserver.core.base.bean.invSysRequest>
     *
     */
    @Query("select v from VinvSysTransMerge v " +
            " where (:createDateStart      is null or v.createDate >= :createDateStart)  " +
            "and (:createDateEnd        is null or v.createDate <= :createDateEnd)   " +
            "and (:cardScheme           is null or v.cardScheme = :cardScheme) " +
            "and (:messageVersion       is null or v.messageVersion = :messageVersion)   " +
            "and (:acsTransID           is null or v.acsTransId = :acsTransID)   " +
            "and (:invSysServerTransID is null or v.invSysServerTransId = :invSysServerTransID)   " +
            "and (:transStatus          is null or v.transStatus = :transStatus)   " +
            "and (:acctID               is null or v.acctId = :acctID) " +
            "and (:acctNumberPrefix     is null or v.acctNumberPrefix = :acctNumberPrefix)  " +
            "and (:acctNumberPostfix    is null or v.acctNumberPostfix = :acctNumberPostfix)   " +
            "and (:acquirerMerchantID   is null or v.acquirerMerchantId = :acquirerMerchantID)   " +
            "and (:deviceChannel        is null or v.deviceChannel = :deviceChannel)   " +
            "and (:messageCategory      is null or v.messageCategory = :messageCategory)   " +
            "and (:purchaseAmount       is null or v.purchaseAmount = :purchaseAmount) " +
//    		"and (:integratorRequestorId       is null  )  " +
//    		"and (:invSysRequestorOrderId       is null)  "


			"and (:integratorRequestorId   is null or v.integratorRequestorId like %:integratorRequestorId%) " +
			"and (:invSysRequestorOrderId   is null or v.requestorOrderId like %:invSysRequestorOrderId%) "

//            "and (:integratorRequestorId   is null or v.integratorRequestorId like concat('%',:integratorRequestorId)) " +
//            "and (:invSysRequestorOrderId   is null or v.requestorOrderId like concat(:invSysRequestorOrderId)) "

	)
    public Page<VinvSysTransMerge> findByCriteria( 
            @Param("createDateStart") String createDateStart,
            @Param("createDateEnd") String createDateEnd,
            @Param("cardScheme") String cardScheme,
            @Param("messageVersion") String messageVersion,
            @Param("acsTransID") String acsTransID,
            @Param("invSysServerTransID") String invSysServerTransID,
            @Param("transStatus") String transStatus,
            @Param("acctID") String acctID,
            @Param("acctNumberPrefix") String acctNumberPrefix,
            @Param("acctNumberPostfix") String acctNumberPostfix,
            @Param("acquirerMerchantID") String acquirerMerchantID,
            @Param("deviceChannel") String deviceChannel,
            @Param("messageCategory") String messageCategory,
            @Param("purchaseAmount") String purchaseAmount,
            @Param("integratorRequestorId") String integratorRequestorId,
            @Param("invSysRequestorOrderId") String invSysRequestorOrderId,
            Pageable pageable);
}
