/*
 * @(#)KekControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的金鑰加密金鑰設定 JUnit。
 *
 * Modify History:
 * v1.00,   /08/31,   LeonKao 
 *  1) First release
 * v1.01,   /09/04, LeonKao 
 *  1) 修改做法 
 *  
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.invSet.emv.invSys.invSysserver.core.bean.KmsInfo;
import com.invSet.emv.invSys.invSysserver.core.service.KeyInfoService;
import com.invSet.emv.invSys.invSysserver.core.service.KmsInfoService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 "金鑰加密金鑰設定" 控制器類別測試
 * 
 * @author   LeonKao,LeonKao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class KekControllerTest {
    
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private KmsInfoService kmsInfoService;
	
	@Autowired
	private KeyInfoService keyInfoService;

	@Autowired
	@InjectMocks
	private KekController kekController;
    
    public KekControllerTest() {
    }
    
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(kekController).setViewResolvers(resolver).build();

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
		testEnable();
		testEnableFail();
		testQueryByAlias();
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
                (get("/kek/query"))
                        .session(session)
        ).andReturn();
        
		Page<KmsInfo> page = (Page<KmsInfo>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
        
        System.out.println("query end");
        
    }
    
    
    /**
     * 根據查詢條件查詢資料
     * 
     * @throws Exception 
     */
    public void testQueryByAlias() throws Exception {
        System.out.println("queryByAlias");
		MvcResult result = mockMvc.perform(
                (get("/kek/query"))
                		.param("criteriaKeyAlias", "testkek")
                        .session(session)
        ).andReturn();
        
		Page<KmsInfo> page = (Page<KmsInfo>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        
        System.out.println("queryByAlias end");
        
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
                (get("/kek/add"))
                        .session(session)
        ).andExpect(
                view().name("/kek/add")
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
                (post("/kek/add"))
                        .param("keyAlias", "testkek")
                        .param("algorithm", "DESede")
                        .param("keySize", "192")
                        .session(session)
        ).andExpect(
                view().name("/kek/query")
        );
        
		assertTrue(keyInfoService.findByKeyAlias("testkek").size() != 0 );
		assertNotNull(kmsInfoService.findByKeyAlias("testkek"));
		
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
                (post("/kek/add"))
                        .param("keyAlias", "testkek")
                        .param("algorithm", "DESede")
                        .param("keySize", "192")
                        .session(session)
        ).andExpect(
                view().name("/kek/add")
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
                (post("/kek/add"))
                        .param("keyAlias", "")
                        .param("algorithm", "DESede")
                        .param("keySize", "192")
                        .session(session)
        ).andExpect(
                view().name("/kek/add")
	    ).andExpect(
	            model().hasErrors()                
        ).andReturn();
        		
		System.out.println("testAddFailHasErrors end");
    }

    /**
     * 啟用
     * 
     * @throws Exception 
     */
    public void testEnable() throws Exception {
        System.out.println("enable");
        
        Long oid = kmsInfoService.findByKeyAlias("testkek").getOid();
        
		mockMvc.perform(
                (get("/kek/enable"))
                		.param("oid", oid.toString())
                        .session(session)
        ).andExpect(
                view().name("/kek/query")
        );
		
		KmsInfo kmsinfo =  kmsInfoService.findByKeyAlias("testkek");
		assertEquals(kmsinfo.getStatus(), "1");
		
		System.out.println("enable end");
    }
    
    /**
     * 啟用失敗
     * 
     * @throws Exception 
     */
    public void testEnableFail() throws Exception {
        System.out.println("enable");
        
        MvcResult result = mockMvc.perform(
                (get("/kek/enable")) 
                		.param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/kek/query")
        ).andReturn();
		
        assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
		System.out.println("enable end");
    }
    
    /**
     * 啟用失敗
     * 
     * @throws Exception 
     */
    public void testForm() throws Exception {
        System.out.println("enable");
        
        KekCriteriaForm form = new KekCriteriaForm();
        form.setCriteriaKeyAlias("test");
        form.setCriteriaStatus("1");
        form.setPageNumber(1);
        
        assertEquals(form.getCriteriaKeyAlias(), "test");
        assertEquals(form.getCriteriaStatus(), "1");
        assertEquals(form.getPageNumber().toString(), "1");

		
		System.out.println("enable end");
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
