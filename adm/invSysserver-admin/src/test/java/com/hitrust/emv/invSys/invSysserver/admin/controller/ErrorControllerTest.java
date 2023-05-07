/**
 * @(#)ErrorControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的錯誤頁面導頁  JUnit。
 *
 * Modify History:
 * v1.00,   /9/14, Bob last
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ErrorControllerTest {

	private MockMvc mockMvc;

	@Autowired
	@InjectMocks
	private ErrorController errorController;

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	/**
	 * 創立mockMvc與登入後台取得權限
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); // 在test中重新配置視圖解析器
		resolver.setPrefix("/WEB_INF/jsp");
		resolver.setSuffix(".jsp");
		// 為了避免路經死循環，需再配置InternalResourceViewResolver，並指定給全域變數
		this.mockMvc = MockMvcBuilders.standaloneSetup(errorController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void test() throws Exception {
		mockMvc.perform(
                (get("/400"))
        ).andExpect(
                view().name("error/400"));
		mockMvc.perform(
                (get("/404"))
        ).andExpect(
                view().name("error/404"));
		mockMvc.perform(
                (get("/405"))
        ).andExpect(
                view().name("error/405"));
		mockMvc.perform(
                (get("/500"))
        ).andExpect(
                view().name("error/500"));
	}

}
