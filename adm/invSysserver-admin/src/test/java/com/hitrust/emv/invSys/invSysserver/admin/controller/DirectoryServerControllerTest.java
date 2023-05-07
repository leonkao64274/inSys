/*
 * @(#)DirectoryServerControllerTest.java
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.DirectoryServer;
import com.invSet.emv.invSys.invSysserver.core.service.DirectoryServerService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "目錄服務器設定" 控制器類別測試
 * 
 * @author LeonKao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DirectoryServerControllerTest {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private DirectoryServerService diretoryServerService;

	@Autowired
	@InjectMocks
	private DirectoryServerController directoryServerController;
    
    
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(directoryServerController).setViewResolvers(resolver).build();

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
		testShowEditFormFailOidNull();
		testShowEditFormFail();
		testEdit();
		testEditFail();
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
                (get("/directory_server/query"))
                        .session(session)
        ).andReturn();
        
		Page<DirectoryServer> page = (Page<DirectoryServer>) result.getRequest().getAttribute("queryResult");
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
                (get("/directory_server/add"))
                        .session(session)
        ).andExpect(
                view().name("/directory_server/add")
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
                (post("/directory_server/add"))
                        .param("cardScheme", "V")
                        .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("messageVersion", "2.1.0")
                        .param("retryLimits", "3")
                        .param("retryInterval", "5")
                        .param("readTimeout", "30")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        );
        
		assertNotNull(diretoryServerService.findById(1L));		
		
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
                (post("/directory_server/add"))
                		.param("cardScheme", "V")
		                .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("messageVersion", "2.1.0")
		                .param("retryLimits", "3")
		                .param("retryInterval", "5")
		                .param("readTimeout", "30")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
		                .session(session)
        ).andExpect(
                view().name("/directory_server/add")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
		System.out.println("addfail end");
    }
    
    /**
     * 新增失敗
     * 
     * @throws Exception 
     */
    public void testAddFailHasErrors() throws Exception {
        System.out.println("testAddFailHasErrors");
          
		// MockMvcRequestBuilders.post -> (post("url")
        MvcResult result = mockMvc.perform(
                (post("/directory_server/add"))
                		.param("cardScheme", "V")
		                .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("messageVersion", "2.1.0")
		                .param("retryLimits", "3")
		                .param("retryInterval", "5")
		                .param("readTimeout", "")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
		                .session(session)
        ).andExpect(
                view().name("/directory_server/add")
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
                (get("/directory_server/edit"))
                		.param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/edit")
        );
		
		System.out.println("showEditForm end"); 
    }
    
    /**
     * 顯示修改業面-Oid空值
     *  
     * @throws Exception
     * 
     */
    public void testShowEditFormFailOidNull() throws Exception {
        System.out.println("testShowEditFormFailOidNull");      
            
		// MockMvcRequestBuilders.post -> (post("url")
        MvcResult result = mockMvc.perform(
                (get("/directory_server/edit"))
                		.param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
		System.out.println("testShowEditFormFailOidNull end"); 
    }
    
    /**
     * 顯示修改業面-Oid資料不存在
     *  
     * @throws Exception
     * 
     */
    public void testShowEditFormFail() throws Exception {
        System.out.println("testShowEditFormFail");      
            
		// MockMvcRequestBuilders.post -> (post("url")
        MvcResult result = mockMvc.perform(
                (get("/directory_server/edit"))
                		.param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
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
                (post("/directory_server/edit"))
                		.param("oid", "1")
		                .param("cardScheme", "V")
		                .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("messageVersion", "1.1.0")
		                .param("retryLimits", "3")
		                .param("retryInterval", "5")
		                .param("readTimeout", "30")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
		                .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        );
        
		assertEquals(diretoryServerService.findById(1L).getMessageVersion(), "1.1.0");
		
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
                (post("/directory_server/edit"))
		        		.param("oid", "0")
		                .param("cardScheme", "V")
		                .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("messageVersion", "1.1.0")
		                .param("retryLimits", "3")
		                .param("retryInterval", "5")
		                .param("readTimeout", "30")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
		                .session(session)   
		                
        ).andExpect(
                view().name("/directory_server/query")
        ).andReturn();       
				
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		System.out.println("EditFail end");
	}
	
	/**
	 * 測試修改失敗-hasreeors
	 * 
	 * @throws Exception
	 */
	public void testEditFailHasErrors() throws Exception {
		System.out.println("testEditFailHasErrors");  
		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (post("/directory_server/edit"))
		        		.param("oid", "1")
		                .param("cardScheme", "V")
		                .param("areqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupAreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("preqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("backupPreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("rreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
                        .param("backupRreqUrl", "http://demo.higotw.com.tw:30100/acs-auth/auth/V/2.1.0/1/123/test")
		                .param("messageVersion", "")
		                .param("retryLimits", "3")
		                .param("retryInterval", "5")
		                .param("readTimeout", "30")
                        .param("invSysServerOperatorID", "invSysServerOperatorUL")
                        .param("invSysServerRefNumber", "invSysServerRefNumber")
                        .param("proxyEnabled", "0")
                        .param("proxyHost", "127.0.0.1")
                        .param("proxyHost", "6666")
		                .session(session)   
		                
        ).andExpect(
                view().name("/directory_server/edit")
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
                (get("/directory_server/query"))
                		.param("criteriaCardScheme", "V")
                        .session(session)
        ).andReturn();
        
		Page<DirectoryServer> page = (Page<DirectoryServer>) result.getRequest().getAttribute("queryResult");
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
                (get("/directory_server/detail"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/detail")
        );
        		
		System.out.println("detail end");
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
                (get("/directory_server/detail"))
                        .param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
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
                (get("/directory_server/detail"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
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
                (get("/directory_server/delete"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        );
        
		assertNull(diretoryServerService.findById(1L));
		System.out.println("delete end");
	}
	
	/**
	 * 測試刪除失敗-Oid空值
	 * 
	 * @throws Exception
	 */
	public void testDeleteFailOidNull() throws Exception {
		System.out.println("testDeleteFailOidNull");
		// MockMvcRequestBuilders.post -> (post("url")
		 MvcResult result = mockMvc.perform(
                (get("/directory_server/delete"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));

		
		System.out.println("testDeleteFailOidNull end");
	}
	
	/**
	 * 測試刪除失敗-Oid資料不存在
	 * 
	 * @throws Exception
	 */
	public void testDeleteFail() throws Exception {
		System.out.println("testDeleteFail");
		// MockMvcRequestBuilders.post -> (post("url")
		 MvcResult result = mockMvc.perform(
                (get("/directory_server/delete"))
                        .param("oid", "0")
                        .session(session)
        ).andExpect(
                view().name("/directory_server/query")
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
		
		DirectoryServerCriteriaForm form = new DirectoryServerCriteriaForm();
			
		form.setCardScheme("V");
		form.setPageNumber(1);
		
		assertEquals(form.getCardScheme(), "V");
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
