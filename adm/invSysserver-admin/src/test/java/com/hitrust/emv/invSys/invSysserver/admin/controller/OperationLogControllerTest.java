/*
 * @(#)OperationLogControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的操作紀錄查詢 JUnit。
 *
 * Modify History:
 * v1.00,   /09/06, LeonKao 
 *  1) First release
 *  
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
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
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.core.bean.OperationLog;
import com.invSet.emv.invSys.invSysserver.core.service.OperationLogService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "操作紀錄查詢" 控制器類別測試
 * 
 * @author LeonKao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class OperationLogControllerTest {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private OperationLogService operationLogService;

	@Autowired
	@InjectMocks
	private OperationLogController operationLogController;
    
    
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(operationLogController).setViewResolvers(resolver).build();

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
		testQuery();	
		testQueryByAccessId();
		testDetail();
		testDetailEdit();
		testDetailEditGroup();
		testDetailFailOidNull();
		testDetailFail();
		testForm();
	}
    
    /**
     * 查詢全部資料
     * 
     * @throws Exception 
     */
    public void testQuery() throws Exception {
        System.out.println("query");
		MvcResult result = mockMvc.perform(
                (get("/operation_log/query"))
                        .session(session)
        ).andReturn();
        
		Page<OperationLog> page = (Page<OperationLog>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
        
        System.out.println("query end");
        
    }
    
    /**
     * 依據條件查詢資料
     * 
     * @throws Exception 
     */
    public void testQueryByAccessId() throws Exception {
        System.out.println("query");
		MvcResult result = mockMvc.perform(
                (get("/operation_log/query"))
                		.param("accessId", "kek")
                        .session(session)
        ).andReturn();
        
		Page<OperationLog> page = (Page<OperationLog>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() > 0);
        
        System.out.println("query end");
        
    }

    
    /**
	 * 測試明細頁-查詢
	 * 
	 * @throws Exception
	 */
	public void testDetail() throws Exception {
		System.out.println("detail");
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/operation_log/detail"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/operation_log/detail")
        );
        		
		System.out.println("detail end");
	}
	
	
    /**
	 * 測試明細頁-查詢-edit
	 * 
	 * @throws Exception
	 */
	public void testDetailEdit() throws Exception {
		System.out.println("testDetailEdit");
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/operation_log/detail"))
                        .param("oid", "2")
                        .session(session)
        ).andExpect(
                view().name("/operation_log/detail")
        );
        		
		System.out.println("testDetailEdit end");
	}
	
    /**
	 * 測試明細頁-查詢-admingroup
	 * 
	 * @throws Exception
	 */
	public void testDetailEditGroup() throws Exception {
		System.out.println("testDetailEditGroup");
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/operation_log/detail"))
                        .param("oid", "3")
                        .session(session)
        ).andExpect(
                view().name("/operation_log/detail")
        );
        		
		System.out.println("testDetailEditGroup end");
	}
	
	/**
	 * 測試明細頁失敗
	 * 
	 * @throws Exception
	 */
	public void testDetailFail() throws Exception {
		System.out.println("detailFail");

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/operation_log/detail"))
                        .param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/operation_log/query")
        );
        		
		System.out.println("detailFail end");
	}
	
	/**
	 * 測試明細頁失敗-Oid空值
	 * 
	 * @throws Exception
	 */
	public void testDetailFailOidNull() throws Exception {
		System.out.println("testDetailFailOidNull");

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/operation_log/detail"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/operation_log/query")
        );
        		
		System.out.println("testDetailFailOidNull end");
	}
	
	/**
	 * testForm
	 * 
	 * @throws Exception
	 */
	public void testForm() throws Exception {
		System.out.println("testForm");

		OperationLogCriteriaForm form = new OperationLogCriteriaForm();
		
		form.setCriteriaAccessId("login");
		form.setCriteriaAccount("alex");
		form.setCriteriaAction("L");
		form.setCriteriaEndDate("  0920");
		form.setCriteriaIssuerOid(1L);
		form.setCriteriaResult("0");
		form.setCriteriaStartDate("  0921");
		form.setPageNumber(1);
		
		assertEquals(form.getCriteriaAccessId(), "login");
		assertEquals(form.getCriteriaAccount(), "alex");
		assertEquals(form.getCriteriaAction(), "L");
		assertEquals(form.getCriteriaEndDate(), "  0920");
		assertEquals(form.getCriteriaIssuerOid().toString(), "1");
		assertEquals(form.getCriteriaResult(), "0");
		assertEquals(form.getCriteriaStartDate(), "  0921");
		assertEquals(form.getPageNumber().toString(), "1");
        		
		System.out.println("testForm end");
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
