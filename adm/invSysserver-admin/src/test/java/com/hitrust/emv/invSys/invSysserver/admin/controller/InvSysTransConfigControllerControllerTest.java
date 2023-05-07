/*
 * @(#)invSysTransConfigControllerControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的交易参数设定 JUnit。
 *
 * Modify History:
 * v1.00,   /09/06, LeonKao 
 *  1) First release
 *  
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpSession;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.service.invSysTransConfigService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "交易参数设定" 控制器類別測試
 * 
 * @author LeonKao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class invSysTransConfigControllerControllerTest {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private invSysTransConfigService configService;

	@Autowired
	@InjectMocks
	private invSysTransConfigController invSysTransConfigController;
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
		//為了取的登入的權限，要使用預設的mockMvc
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		this.session = (MockHttpSession) getLogin(mockMvc);
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); // 在test中重新配置視圖解析器
		resolver.setPrefix("/WEB_INF/jsp");
		resolver.setSuffix(".jsp");
		//為了避免路經死循環，需再配置InternalResourceViewResolver，並指定給全域變數
		this.mockMvc = MockMvcBuilders.standaloneSetup(invSysTransConfigController).setViewResolvers(resolver).build();
    }
    
    @After
    public void tearDown() {
    }
    
	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {

		testShowEditForm();
		testAdd();
		testShowEditForm();
		testEdit();
		testEditFail();

	}
    
    
    /**
     * 顯示修改業面
     *  
     * @throws Exception
     * 
     */
    public void testShowEditForm() throws Exception {
        System.out.println("showEditForm");      
            
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/invSys_trans_config/edit"))
                        .session(session)
        ).andExpect(
                view().name("/invSys_trans_config/edit")
        );
		
		System.out.println("showEditForm end"); 
    }

    
    /**
	 * 測試修改
	 * 第一次修改(新增)
	 * 
	 * @throws Exception
	 */
	public void testAdd() throws Exception {
		System.out.println("Edit");  
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/invSys_trans_config/edit"))
                		.param("entities[0].oid", "1")
                        .param("entities[0].cardScheme", "V")
                        .param("entities[0].channelEnable", "1")
                		.param("entities[1].oid", "2")
                        .param("entities[1].cardScheme", "M")
                        .param("entities[1].channelEnable", "1")
                		.param("entities[2].oid", "3")
                        .param("entities[2].cardScheme", "J")
                        .param("entities[2].channelEnable", "1")
                		.param("entities[3].oid", "4")
                        .param("entities[3].cardScheme", "A")
                        .param("entities[3].channelEnable", "1")
                		.param("entities[4].oid", "5")
                        .param("entities[4].cardScheme", "C")
                        .param("entities[4].channelEnable", "1")
                		.param("entities[5].oid", "6")
                        .param("entities[5].cardScheme", "D")
                        .param("entities[5].channelEnable", "1")
                		.param("entities[6].oid", "7")
                        .param("entities[6].cardScheme", "U")
                        .param("entities[6].channelEnable", "1")                        
		                .session(session)
        ).andExpect(
                view().name("/invSys_trans_config/edit")
        );
        
//		assertEquals(configService.findById(1L).getOpDuration().toString(), "15");
		
		System.out.println("Edit end");
	}
	
	/**
	 * 測試修改
	 * 
	 * @throws Exception
	 */
	public void testEdit() throws Exception {
		System.out.println("Edit");  
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/invSys_trans_config/edit"))
                		.param("entities[0].oid", "1")
                        .param("entities[0].cardScheme", "V")
                        .param("entities[0].channelEnable", "0")
                		.param("entities[1].oid", "2")
                        .param("entities[1].cardScheme", "M")
                        .param("entities[1].channelEnable", "1")
                		.param("entities[2].oid", "3")
                        .param("entities[2].cardScheme", "J")
                        .param("entities[2].channelEnable", "1")
                		.param("entities[3].oid", "4")
                        .param("entities[3].cardScheme", "A")
                        .param("entities[3].channelEnable", "1")
                		.param("entities[4].oid", "5")
                        .param("entities[4].cardScheme", "C")
                        .param("entities[4].channelEnable", "1")
                		.param("entities[5].oid", "6")
                        .param("entities[5].cardScheme", "D")
                        .param("entities[5].channelEnable", "1")
                		.param("entities[6].oid", "7")
                        .param("entities[6].cardScheme", "U")
                        .param("entities[6].channelEnable", "1")
		                .session(session)
        ).andExpect(
                view().name("/invSys_trans_config/edit")
        );
        
		assertEquals(configService.findById(1L).getChannelEnable(), "0");
		
		System.out.println("Edit end");
	}
	
	/**
	 * 測試修改
	 * 
	 * @throws Exception
	 */
	public void testEditFail() throws Exception {
		System.out.println("testEditFail");  
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/invSys_trans_config/edit"))
                		.param("entities[0].oid", "1")
                        .param("entities[0].cardScheme", "V")
                        .param("entities[0].channelEnable", "0")
                		.param("entities[0].oid", "2")
                        .param("entities[0].cardScheme", "M")
                        .param("entities[0].channelEnable", "1")

		                .session(session)
        ).andExpect(
                view().name("/invSys_trans_config/edit")
        );
        
		assertEquals(configService.findById(1L).getChannelEnable(), "0");
		
		System.out.println("testEditFail end");
	}
 

	/**
	 * 登入
	 * 
	 * @return
	 * @throws Exception
	 */
	private HttpSession getLogin(MockMvc mockMvc) throws Exception {
		System.out.println("login");
		MvcResult result = mockMvc.perform(
                (post("/index/login")
                        .param("account", "Bob")
                        .param("password", "000000")
                        .param("locale", "zh-CN")
                        .param("random8digits", "14252849")
                        .param("encryptPassword", "f36a45e9539998afc40296c6950d649fbc53934b"))
        ).andReturn();
        		
		System.out.println("login end");
		
		return result.getRequest().getSession();		
	}    
}
