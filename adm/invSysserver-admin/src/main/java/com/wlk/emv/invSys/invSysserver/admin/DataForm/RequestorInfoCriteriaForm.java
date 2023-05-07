package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組設定作業用的查詢條件 Form(前台綁定)。
 *
 * 
 */
public class RequestorInfoCriteriaForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

    /**
     * for query
     * requestor's ID and its name
     */
    private String requestorName;
    private String requestorId;
    
    
    /**
     * assign the pagination 
     */
    private Integer pageNumber;


    //=================================================
    // constructors
    //=================================================
    
    /**
     * 預設建構子
     */
    public RequestorInfoCriteriaForm() {
        super();
    }

    //=================================================
    // getter & setter
    //=================================================
    

    
    public Integer getPageNumber() {
        if (pageNumber == null) {
            return 0;
        }
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
        
     
    }

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    

}
