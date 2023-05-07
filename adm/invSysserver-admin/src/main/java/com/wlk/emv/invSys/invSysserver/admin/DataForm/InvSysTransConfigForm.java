/*
 * @(#)invSysTransConfigController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/invSys 交易參數設定 Form (綁定表單)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.00,  /08/01, Milo Gao
 *   2) 接手開發。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import java.util.List;
import com.invSet.emv.invSys.invSysserver.core.bean.SysCode;
import com.invSet.emv.invSys.invSysserver.core.bean.invSysTransConfig;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/invSys 交易參數設定 Form (綁定表單)。
 * 
 * @author   LeonKao, MiloGao
 */
public class invSysTransConfigForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 組態屬性(1): <卡組織> 集合。
	 */
	private List<SysCode> cardSchemaList;
	
	/**
	 * 組態屬性(2): 交易通道的I18N文字。
	 */
	private List<String> channelEnableLabel;
		
	/**
	 * 資料屬性(1): <交易參數設定> 集合。
	 */
	private List<invSysTransConfig> entities;
	
    //=================================================
    // constructors
    //=================================================

    /**
     * 預設建構子
     */
	public invSysTransConfigForm() {}

    //=================================================
    // getter & setter
    //=================================================

	public List<SysCode> getCardSchemaList() {
		return cardSchemaList;
	}

	public void setCardSchemaList(List<SysCode> cardSchemaList) {
		this.cardSchemaList = cardSchemaList;
	}

	public List<String> getChannelEnableLabel() {
		return channelEnableLabel;
	}

	public void setChannelEnableLabel(List<String> channelEnableLabel) {
		this.channelEnableLabel = channelEnableLabel;
	}

	public List<invSysTransConfig> getEntities() {
		return entities;
	}

	public void setEntities(List<invSysTransConfig> entities) {
		this.entities = entities;
	}	
}
