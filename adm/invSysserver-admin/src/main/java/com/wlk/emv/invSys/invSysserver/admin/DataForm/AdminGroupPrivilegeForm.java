/*
 * @(#)AdminGroupCriteriaForm.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組權限設定作業用的設定 Form(前台綁定)。
 *
 * Modify History:
 * v1.00,  /07/11, Leon Kao
 *   1) First release
 * v1.01,  /07/26, Milo Gao
 *   2) 接手開發。
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroupPrivilege;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組/功能群組權限設定作業用的設定 Form(前台綁定)。
 * 
 * @author   LeonKao, MiloGao
 */
public class AdminGroupPrivilegeForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 模組級的功能群組設定 Entity 集合。
	 */
	private List<AdminMenu> modules;
	
	/**
	 * 功能級的功能群組設定 Entity 集合。
	 */
	private List<List<AdminMenu>> functions;

    //=================================================
    // constructors
    //=================================================

	/**
	 * 預設建構子。
	 */
	public AdminGroupPrivilegeForm() {}

	/**
	 * 帶參數的建構子。
	 * 
	 * @param modulefunctions "模組級" 功能選單設定 Entity 集合。
	 */
	public AdminGroupPrivilegeForm(List<AdminMenu> moduleMenus) {
		
		// 1. 初始化: modules, functions 二個屬性。
		this.modules = new ArrayList<AdminMenu>();
		this.functions = new ArrayList<List<AdminMenu>>();
		
		// 2. 將參數解析，並填入 modules, functions 二個屬性。
		// (1) 大迴圈 = 模組級之功能選單設定 Entity。
		for (AdminMenu groupMenu : moduleMenus) {
			
			// (2) 小迴圈 = 功能級之功能選單設定 Entity。
			List<AdminMenu> functionList = new ArrayList<AdminMenu>();
			for (AdminMenu functionMenu : groupMenu.getChildNodes()) {
				functionList.add(functionMenu);
			}
			
			// 添加。
			this.modules.add(groupMenu);
			this.functions.add(functionList);
		}
	}

    //=================================================
    // public methods
    //=================================================

	/**
	 * 初始化象徵 "權限" 的屬性值。<br/>
	 * 補充說明：通常此方法被 "新增" 作業的函數調用。 
	 */
	public void initialPrivileges() {
		
		// (1) 大迴圈=模組級。
		for (List<AdminMenu> group : this.functions) {
			// (2) 小迴圈=功能級。
			for (AdminMenu item : group) {
				// 預設值：Boolean.FALSE (沒有勾選)。
				item.setMark(Boolean.FALSE);
			}
		}
	}
	
	/**
	 * 依據參數值，初始化象徵 "權限" 的屬性值。<br/>
	 * 補充說明：通常此方法被 "修改" 作業的函數調用。 
	 */
	public void initialPrivileges(Set<AdminGroupPrivilege> privileges) {
		
		// 1. 收集參數中每一個類別的 accessId 值，成為一個集合，用於 "驗證" 權限值。
		Set<String> accessIdList = new HashSet<String>();
		for (AdminGroupPrivilege item : privileges) {
			// 條件(1): 非空值。
			// 條件(2): 字串不能重覆。
			String accessId = item.getAccessId();
			if (accessId != null && accessId.trim().length() > 0 &&
					accessIdList.contains(accessId) == false) {
				accessIdList.add(accessId);
			}
		}
		
		// 2. 重新設定象徵 "權限" 的旗標值。
		for (List<AdminMenu> group : this.functions) {
			for (AdminMenu item : group) {
				if (accessIdList.contains(item.getAccessId())) {
					item.setMark(Boolean.TRUE);
				} else {
					item.setMark(Boolean.FALSE);
				}
			}
		}
	}
	
    //=================================================
    // getter & setter
    //=================================================

	public List<AdminMenu> getModules() {
		return modules;
	}

	public void setModules(List<AdminMenu> modules) {
		this.modules = modules;
	}

	public List<List<AdminMenu>> getfunctions() {
		return functions;
	}

	public void setfunctions(List<List<AdminMenu>> functions) {
		this.functions = functions;
	}
}
