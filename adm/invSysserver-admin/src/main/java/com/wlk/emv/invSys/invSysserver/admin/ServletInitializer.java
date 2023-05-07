/*
 * @(#)ServletInitializer.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 Spring Boot Servlet 初始化類別
 *
 * Modify History:
 * v1.00,  /07/10, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 Spring boot Servlet 初始化類別<br>
 * 
 * Spring Boot 專案需繼承 SpringBootServletInitializer 並實作 configure 函式，打包出
 * 來的 war 檔方可部署至外部的 J2EE Application Server (ex: Tomcat, WebSphere) 運作使用.
 * 
 * @author   LeonKao
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(invSysserverAdminApplication.class);
	}

}
