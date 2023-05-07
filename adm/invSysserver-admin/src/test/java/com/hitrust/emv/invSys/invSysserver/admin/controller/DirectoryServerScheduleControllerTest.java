/*
 * @(#)DirectoryServerScheduleControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的目錄服務器設定 JUnit。
 *
 * Modify History:
 * v1.00,   /09/05, LeonKao 
 *  1) First release
 *  
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.DirectoryServerSchedule;
import com.invSet.emv.invSys.invSysserver.core.service.DirectoryServerScheduleService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "目錄服務器設定 設定" 控制器類別測試
 * 
 * @author LeonKao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DirectoryServerScheduleControllerTest {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private DirectoryServerScheduleService directoryServerScheduleService;

	@Autowired
	@InjectMocks
	private DirectoryServerScheduleController directoryServerScheduleController;
    
    
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(directoryServerScheduleController).setViewResolvers(resolver).build();

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
		testShowAddForm();
		testAdd();
		testAddFail();
		testAddFailHasErrors();
		testShowEditForm();
		testShowEditFormFail();
		testEdit();
		testEditFail();
		testEditFailOidNull();
		testEditFailHasErrors();
		testQueryByCardScheme();
		testDetail();
		testDetailFail();
		testDetailFailOidNull();
		testDelete();
		testDeleteFailOidNull();
		testDeleteFail();
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
                (get("/directory_server_schedule/query"))
                        .session(session)
        ).andReturn();
        
		Page<DirectoryServerSchedule> page = (Page<DirectoryServerSchedule>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
        
        System.out.println("query end");
        
    }
    

    /**
     * 顯示新增業面
     *  
     * @throws Exception
     * 
     */
    public void testShowAddForm() throws Exception {
        System.out.println("showAddForm");      
            
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/add"))
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/add")
        );
		
		System.out.println("showAddForm end"); 
    }

    /**
     * 新增
     * 
     * @throws Exception 
     */    
    public void testAdd() throws Exception {
        System.out.println("add");
          
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/directory_server_schedule/add"))
                        .param("cardScheme", "V")
                        .param("serverUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("opRunningType", "ALL")
                        .param("opDuration", "10")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
        
		assertNotNull(directoryServerScheduleService.findByCardScheme("V"));		
		
		System.out.println("add end");
    }
    
    
    /**
     * 新增失敗
     * 
     * @throws Exception 
     */
    public void testAddFail() throws Exception {
        System.out.println("addfail");
          
		// MockMvcRequestBuilders.post -> (post("url")
        MvcResult result = mockMvc.perform(
                (post("/directory_server_schedule/add"))
		                .param("cardScheme", "V")
		                .param("serverUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("opRunningType", "ALL")
		                .param("opDuration", "10")
		                .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/add")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
		System.out.println("addfail end");
    }
    
    
    /**
     * 新增失敗-hasErrors
     * 
     * @throws Exception 
     */
    public void testAddFailHasErrors() throws Exception {
        System.out.println("testAddFailHasErrors");
          
		// MockMvcRequestBuilders.post -> (post("url")
        MvcResult result = mockMvc.perform(
                (post("/directory_server_schedule/add"))
		                .param("cardScheme", "V")
		                .param("serverUrl", "")
		                .param("opRunningType", "ALL")
		                .param("opDuration", "10")
		                .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/add")
		).andExpect(
				model().hasErrors()                  
        ).andReturn();
        
		
		System.out.println("testAddFailHasErrors end");
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
                (get("/directory_server_schedule/edit"))
                		.param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/edit")
        );
		
		System.out.println("showEditForm end"); 
    }
    
    /**
     * 顯示修改業面失敗
     *  
     * @throws Exception
     * 
     */
    public void testShowEditFormFail() throws Exception {
        System.out.println("testShowEditFormFail");      
            
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/edit"))
                		.param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
		
		System.out.println("testShowEditFormFail end"); 
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
                (post("/directory_server_schedule/edit"))
                		.param("oid", "1")
                        .param("cardScheme", "V")
                        .param("serverUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("opRunningType", "ALL")
                        .param("opDuration", "15")
		                .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
        
		assertEquals(directoryServerScheduleService.findById(1L).getOpDuration().toString(), "15");
		
		System.out.println("Edit end");
	}
	
	/**
	 * 測試修改失敗
	 * 
	 * @throws Exception
	 */
	public void testEditFail() throws Exception {
		System.out.println("EditFail");  
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (post("/directory_server_schedule/edit"))
		        		.param("oid", "0")
                        .param("cardScheme", "V")
                        .param("serverUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("opRunningType", "ALL")
                        .param("opDuration", "15")
		                .session(session)   
		                
        ).andExpect(
                view().name("/directory_server_schedule/edit")
        ).andReturn();       
				
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		System.out.println("EditFail end");
	}
	
	/**
	 * 測試修改失敗-OidNull
	 * 
	 * @throws Exception
	 */
	public void testEditFailOidNull() throws Exception {
		System.out.println("testEditFailOidNull");  
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (post("/directory_server_schedule/edit"))
		        		.param("oid", "")
                        .param("cardScheme", "V")
                        .param("serverUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("opRunningType", "ALL")
                        .param("opDuration", "15")
		                .session(session)   
		                
        ).andExpect(
                view().name("/directory_server_schedule/edit")
        ).andReturn();       
				
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
		System.out.println("testEditFailOidNull end");
	}
	
	/**
	 * 測試修改失敗-hassErrors
	 * 
	 * @throws Exception
	 */
	public void testEditFailHasErrors() throws Exception {
		System.out.println("testEditFailHasErrors");  
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (post("/directory_server_schedule/edit"))
		        		.param("oid", "1")
                        .param("cardScheme", "V")
                        .param("serverUrl", "")
                        .param("opRunningType", "ALL")
                        .param("opDuration", "15")
		                .session(session)   
		                
        ).andExpect(
                view().name("/directory_server_schedule/edit")
		).andExpect(
				model().hasErrors() 				
        ).andReturn();       				

		System.out.println("testEditFailHasErrors end");
	}
	

    
    /**
     * 根據查詢條件查詢資料
     * 
     * @throws Exception 
     */
    public void testQueryByCardScheme() throws Exception {
        System.out.println("queryByCardScheme");
		MvcResult result = mockMvc.perform(
                (get("/directory_server_schedule/query"))
                		.param("criteriaCardScheme", "V")
                        .session(session)
        ).andReturn();
        
		Page<DirectoryServerSchedule> page = (Page<DirectoryServerSchedule>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        
        System.out.println("queryByCardScheme end");
        
    }
    
    /**
	 * 測試明細頁
	 * 
	 * @throws Exception
	 */
	public void testDetail() throws Exception {
		System.out.println("detail");
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/detail"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/detail")
        );
        		
		System.out.println("detail end");
	}
	
	/**
	 * 測試明細頁失敗-oid無資料
	 * 
	 * @throws Exception
	 */
	public void testDetailFail() throws Exception {
		System.out.println("detailFail");

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/detail"))
                        .param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
        		
		System.out.println("detailFail end");
	}
	
	/**
	 * 測試明細頁失敗-OidNull
	 * 
	 * @throws Exception
	 */
	public void testDetailFailOidNull() throws Exception {
		System.out.println("testDetailFailOidNull");

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/detail"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
        		
		System.out.println("testDetailFailOidNull end");
	}
	
	/**
	 * 測試刪除
	 * 
	 * @throws Exception
	 */
	public void testDelete() throws Exception {
		System.out.println("delete");
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/directory_server_schedule/delete"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        );
        
		assertNull(directoryServerScheduleService.findById(1L));
		System.out.println("delete end");
	}
	
	/**
	 * 測試刪除失敗-oid空值
	 * 
	 * @throws Exception
	 */
	public void testDeleteFailOidNull() throws Exception {
		System.out.println("testDeleteFailOidNull");
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (get("/directory_server_schedule/delete"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
        
		System.out.println("testDeleteFailOidNull end");
	}
	
	/**
	 * 測試刪除失敗-oid無資料
	 * 
	 * @throws Exception
	 */
	public void testDeleteFail() throws Exception {
		System.out.println("testDeleteFail");
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (get("/directory_server_schedule/delete"))
                        .param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/directory_server_schedule/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
        
		System.out.println("testDeleteFail end");
	}
	
	/**
	 * testForm
	 * 
	 * @throws Exception
	 */
	public void testForm() throws Exception {
		System.out.println("testForm");

		DirectoryServerScheduleCriteriaForm form = new DirectoryServerScheduleCriteriaForm ();
		form.setCriteriaCardScheme("V");
		form.setPageNumber(1);
		
		assertEquals(form.getCriteriaCardScheme(), "V");
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
