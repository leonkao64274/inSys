/*
 * @(#)SystemHealthForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理 / 系統健康管理 表單類別
 *
 * Modify History:
 * v1.00,   /01/11, LeonKao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import java.util.List;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理 / 系統健康管理 表單類別
 * 
 * @author LeonKao
 */

public class SystemHealthForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * 系統名稱
     */
    private String systemName;
    
    /**
     * 系統狀態
     */
    private String status;
    
    /**
     * 總記憶體(MB)
     */
    private Double totalMemory;
    
    /**
     * 可使用記憶體(MB)
     */
    private Double freeMemory;
    
    /**
     * 總磁碟空間 (GB)
     */
    private Double totalDiskSpace;
    
    /**
     * 可使用磁碟空間 (GB)
     */
    private Double freeDiskSpace;

	/**
	* 多筆系統健康集合
	*/
	private List <SystemHealthForm> systemHealth;   
	   
	/**
	* 新增系統名稱
	*/
	private String addSystemName;
	
	/**
	* 新增系統位置
	*/
	private String addSystemUrl;
	  
	/**
	* 系統oid
	*/
	private Long systemOid;
    
    //=================================================
    // constructors
    //=================================================

    /**
     * 預設建構子
     */
    public SystemHealthForm() {
        super();
    }
    
    public SystemHealthForm(String systemName, String status, Double totalMemory
    						, Double freeMemory, Double totalDiskSpace, Double freeDiskSpace) {
    	this.systemName = systemName ;
    	this.status = status ;
    	this.totalMemory = totalMemory ;
    	this.freeMemory = freeMemory ;
    	this.totalDiskSpace = totalDiskSpace ;
    	this.freeDiskSpace = freeDiskSpace ;
    }

   
    //=================================================
    // getter & setter
    //=================================================

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(Double totalMemory) {
		this.totalMemory = totalMemory;
	}

	public Double getFreeMemory() {
		return freeMemory;
	}

	public void setFreeMemory(Double freeMemory) {
		this.freeMemory = freeMemory;
	}

	public Double getTotalDiskSpace() {
		return totalDiskSpace;
	}

	public void setTotalDiskSpace(Double totalDiskSpace) {
		this.totalDiskSpace = totalDiskSpace;
	}

	public Double getFreeDiskSpace() {
		return freeDiskSpace;
	}

	public void setFreeDiskSpace(Double freeDiskSpace) {
		this.freeDiskSpace = freeDiskSpace;
	}
 
    public Double getUsedDiskSpace() {
        return totalDiskSpace - freeDiskSpace;
    }
    
    public Integer getUsedDiskSpacePercentage() {
        return (int) Math.round((getUsedDiskSpace() / totalDiskSpace) * 100);
    }
    public Double getUsedMemory() {
        return totalMemory - freeMemory;
    }
    
    public Integer getUsedMemoryPercentage() {
        return (int) Math.round((getUsedMemory() / totalMemory) * 100);
    }

	public List <SystemHealthForm> getSystemHealth() {
		return systemHealth;
	}

	public void setSystemHealth(List <SystemHealthForm> systemHealth) {
		this.systemHealth = systemHealth;
	}

	public String getAddSystemName() {
		return addSystemName;
	}

	public void setAddSystemName(String addSystemName) {
		this.addSystemName = addSystemName;
	}

	public String getAddSystemUrl() {
		return addSystemUrl;
	}

	public void setAddSystemUrl(String addSystemUrl) {
		this.addSystemUrl = addSystemUrl;
	}

	public Long getSystemOid() {
		return systemOid;
	}

	public void setSystemOid(Long systemOid) {
		this.systemOid = systemOid;
	}




        
	
	
}
