/**
 * @(#)AdminUserControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的AdminUser JUnit。
 *
 * Modify History:
 * v1.00,   /09/4, Bob 
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;
import com.invSet.emv.invSys.invSysserver.core.service.AdminUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class AdminUserControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	@InjectMocks
	private AdminUserController adminUserController;

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
		//為了取的登入的權限，要使用預設的mockMvc
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		this.session = (MockHttpSession) getLogin(mockMvc);
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); // 在test中重新配置視圖解析器
		resolver.setPrefix("/WEB_INF/jsp");
		resolver.setSuffix(".jsp");
		//為了避免路經死循環，需再配置InternalResourceViewResolver，並指定給全域變數
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminUserController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}

	
	/**
	 * 測試創立使用者GET
	 * @throws Exception
	 */
	public void testAdd() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_user/add"))
                        .session(session)
        ).andExpect(
                view().name("/admin_user/add")
        );
	}
	
	/**
	 * 測試創立使用者
	 * 
	 * @throws Exception
	 */
	public void testAddUser() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_user/add"))
                        .param("account", "t1")
                        .param("userName", "t1")
                        .param("adminGroup.oid", "1")
                        .param("department", "")
                        .param("encryptPassword", "956234596efeeecd8996980dbb55a1a6034361d7")
                        .param("password", "000000")
                        .param("cnfrPassword", "123456")
                        .param("tel", "")
                        .param("ext", "")
                        .param("email", "")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/query")
        );
        
		assertNotNull(adminUserService.findById(3L));

	}
	
	public void testAddUserFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (post("/admin_user/add"))
                		.param("account", "t1")
                		.param("userName", "t1")
                		.param("adminGroup.oid", "1")
                		.param("department", "")
                		.param("encryptPassword", "956234596efeeecd8996980dbb55a1a6034361d7")
                		.param("password", "000000")
                		.param("cnfrPassword", "123456")
                		.param("tel", "")
                		.param("ext", "")
                		.param("email", "")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/add")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));

	}
	
	public void testAddUserFailNull() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_user/add"))
                		.param("account", "")
                		.param("userName", "")
                		.param("adminGroup.oid", "")
                		.param("department", "")
                		.param("tel", "")
                		.param("ext", "")
                		.param("email", "")
                        .session(session)
        ).andExpect(
             view().name("/admin_user/add")
        ).andExpect(
             model().hasErrors()
        ).andReturn();

	}

	
	/**
	 * 測試修改使用者GET
	 * @throws Exception
	 */
	public void testEdit() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_user/edit"))
                		.param("oid", "3")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/edit")
        );
	}
	
	
	/**
	 * 測試修改使用者GET
	 * @throws Exception
	 */
	public void testEditFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (get("/admin_user/edit"))
                		.param("oid", "")
                		.session(session)
        ).andExpect(
                view().name("/admin_user/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	public void testEditFail405() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_user/edit"))
                		.param("oid", "999")
                        .session(session)
        ).andExpect(
                status().isMethodNotAllowed()
        );

		mockMvc.perform(
                (get("/admin_user/edit"))
                		.param("oid", "1")
                        .session(session)
        ).andExpect(
                status().isMethodNotAllowed()
        );
	}
	
	/**
	 * 測試修改使用者
	 * 
	 * @throws Exception
	 */
	public void testEditUser() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_user/edit"))
                		.param("oid", "3")
                		.param("account", "t1")
                		.param("userName", "t2")
                		.param("adminGroup.oid", "1")
                		.param("department", "")
        				.param("encryptPassword", "956234596efeeecd8996980dbb55a1a6034361d7")
        				.param("password", "000000")
        				.param("cnfrPassword", "123456")
        				.param("tel", "")
        				.param("ext", "")
        				.param("email", "")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/query")
        );
        
		assertEquals(adminUserService.findById(3L).getUserName(), "t2");

	}
	
	public void testEditUserPWD() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_user/edit"))
                		.param("oid", "3")
                		.param("account", "t1")
                		.param("userName", "t2")
                		.param("adminGroup.oid", "1")
                		.param("department", "")
        				.param("tel", "")
        				.param("ext", "")
        				.param("email", "")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/query")
        );
        
		assertEquals(adminUserService.findById(3L).getUserName(), "t2");

	}
	
	public void testEditUserFail() throws Exception {
	
		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
	            (post("/admin_user/edit"))
	            		.param("oid", "3")
	            		.param("account", "")
	            		.param("userName", "")
	            		.param("department", "")
	            		.param("adminGroup.name", "t1")
	            		.param("encryptPassword", "956234596efeeecd8996980dbb55a1a6034361d7")
	            		.param("password", "000000")
	            		.param("cnfrPassword", "123456")
	            		.param("tel", "")
	            		.param("ext", "")
	            		.param("email", "")
	                    .session(session)
	    ).andExpect(
	            view().name("/admin_user/edit")
	    ).andExpect(model().hasErrors());
	
	}

	/**
	 * 測試刪除使用者
	 * 
	 * @throws Exception
	 */
	public void testDeleteUser() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_user/delete"))
                        .param("oid", "3")
                        .session(session)
        ).andExpect(
                view().name("/admin_user/query")
        );
        
		assertNull(adminUserService.findById(3L));
	}
	
	/**
	 * 測試刪除使用者
	 * 
	 * @throws Exception
	 */
	public void testDeleteUserFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_user/delete"))
                        .param("oid", "5")
                        .session(session)
        ).andExpect(status().isMethodNotAllowed()
        );
        
	}
	
	public void testDeleteUserFailNull() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (get("/admin_user/delete"))
                        .param("oid", "")
                        .session(session)
        ).andExpect(view().name("/admin_user/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
        
	}

	public void testQueryUser() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_user/query"))
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
	}
	
	public void testQueryUserByID() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_user/query"))
        				.param("criteriaAdminGroupOid", "1")
        				.param("criteriaAccount", "")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 2);
        assertEquals("1",page.getContent().get(0).getAdminGroup().getOid().toString());
	}
	
	public void testQueryUserByName() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_user/query"))
                		.param("criteriaAdminGroupOid", "")
                		.param("criteriaAccount", "t1")
                		.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("t1",page.getContent().get(0).getUserName());
	}
	
	public void testOpQueryUser() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_user/op_query"))
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
	}
	
	public void testOpQueryUserByID() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_user/op_query"))
        				.param("criteriaAdminGroupOid", "1")
        				.param("criteriaAccount", "")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 2);
        assertEquals("1",page.getContent().get(0).getAdminGroup().getOid().toString());
	}
	
	public void testOpQueryUserByName() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_user/op_query"))
                		.param("criteriaAdminGroupOid", "")
                		.param("criteriaAccount", "t1")
                		.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminUser> page = (Page<AdminUser>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("t1",page.getContent().get(0).getUserName());
	}

	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testQueryUser();
		testOpQueryUser();
		testAdd();
		testAddUser();
		testAddUserFail();
		testAddUserFailNull();
		testQueryUserByID();
		testQueryUserByName();
		testOpQueryUserByID();
		testOpQueryUserByName();
		testEdit();
		testEditFail();
		testEditFail405();
		testEditUser();
		testEditUserFail();
		testDeleteUser();
		testDeleteUserFail();
		testDeleteUserFailNull();
		form();
	}
	
	private void form() {
		AdminUserForm form1=new AdminUserForm("12345678");
		form1.getCnfrPassword();
	}

	/**
	 * 登入
	 * 
	 * @return
	 * @throws Exception
	 */
	private HttpSession getLogin(MockMvc mockMvc) throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/index/login")
                        .param("account", "Bob")
                        .param("password", "000000")
                        .param("locale", "zh-CN")
                        .param("random8digits", "14252849")
                        .param("encryptPassword", "f36a45e9539998afc40296c6950d649fbc53934b"))
        ).andReturn();
        
		return result.getRequest().getSession();
	}
}
