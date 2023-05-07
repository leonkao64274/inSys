/**
 * @(#)AdminGroupControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的AdminGroup JUnit。
 *
 * Modify History:
 * v1.00,   /08/30, Bob last
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class AdminGroupControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;

	@Autowired
	private AdminGroupService adminGroupService;

	@Autowired
	@InjectMocks
	private AdminGroupController adminGroupController;

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
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminGroupController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}

	/**
	 * 測試創立群組GET
	 * @throws Exception
	 */
	public void testAdd() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_group/add"))
                        .session(session)
        ).andExpect(
                view().name("/admin_group/add")
        );
	}
	/**
	 * 測試創立群組
	 * 
	 * @throws Exception
	 */
	public void testAddGroup() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_group/add"))
                        .param("groupId", "t1")
                        .param("groupName", "t1")
                        .param("description", "t1")
                        .param("privileges", "directory_server")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/query")
        );
        
		assertNotNull(adminGroupService.findById(2L));

	}
	
	/**
	 * 測試創立群組 失敗
	 * @throws Exception
	 */
	public void testAddGroupFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result=mockMvc.perform(
                (post("/admin_group/add"))
                        .param("groupId", "t1")
                        .param("groupName", "t1")
                        .param("description", "t1")
                        .param("privileges", "directory_server")
                        .param("privileges", "sign_csr")
                        .param("privileges", "sign_cert_load")
                        .param("privileges", "sign_cert_activate")
                        .param("privileges", "sign_cert_query")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/add")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));

	}
	
	/**
	 * 測試創立群組 失敗
	 * @throws Exception
	 */
	public void testAddGroupFailErr() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_group/add"))
                        .param("groupId", "t1")
                        .param("groupName", "")
                        .param("description", "t1")
                        .param("privileges", "directory_server")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/add")
        ).andExpect(
                model().hasErrors()
        ).andReturn();


	}
	
	
	/**
	 * 測試創立群組 失敗
	 * @throws Exception
	 */
	public void testAddGroupFailSize0() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_group/add"))
                        .param("groupId", "t1")
                        .param("groupName", "")
                        .param("description", "t1")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/add")
        ).andExpect(
                model().hasErrors()
        ).andReturn();

	}

	/**
	 * 測試修改群組GET
	 * @throws Exception
	 */
	public void testEdit() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_group/edit"))
                		.param("oid", "2")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/edit")
        );
	}
	
	/**
	 * 測試修改群組GET
	 * @throws Exception
	 */
	public void testEditFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_group/edit"))
                		.param("oid", "999")
                        .session(session)
        ).andExpect(
                status().isMethodNotAllowed()
        );
	}

	/**
	 * 測試修改群組
	 * 
	 * @throws Exception
	 */
	public void testEditGroup() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_group/edit"))
                        .param("oid", "2")
                        .param("groupId", "t1")
                        .param("groupName", "t2")
                        .param("description", "t1")
                        .param("privileges", "directory_server")
                        .param("privileges", "sign_csr")
                        .param("privileges", "sign_cert_load")
                        .param("privileges", "sign_cert_activate")
                        .param("privileges", "sign_cert_query")
                        .param("privileges", "ds_cert_management")
                        .param("privileges", "ca_cert_load")
                        .param("privileges", "ca_cert_query")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/query")
        );
        
		assertEquals(adminGroupService.findById(2L).getGroupName(), "t2");

	}
	
	/**
	 * 測試修改群組 失敗
	 * @throws Exception
	 */
	public void testEditGroupFail() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result=mockMvc.perform(
                (post("/admin_group/edit"))
                        .param("oid", "2")
                        .param("groupId", "t1")
                        .param("groupName", "t2")
                        .param("description", "t1")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/edit")
        ).andReturn();
        
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));

	}
	
	public void testEditGroupFailErr() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (post("/admin_group/edit"))
                        .param("oid", "2")
                        .param("groupId", "t1")
                        .param("groupName", "")
                        .param("description", "t1")
                        .param("privileges", "directory_server")
                        .param("privileges", "e")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/edit")
        ).andExpect(
                model().hasErrors()
        ).andReturn();
        
	}

	/**
	 * 測試刪除群組
	 * 
	 * @throws Exception
	 */
	public void testDeleteGroup() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_group/delete"))
                        .param("oid", "2")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/query")
        );
        
		assertNull(adminGroupService.findById(2L));
	}
	
	/**
	 * 測試刪除群組失敗
	 * 
	 * @throws Exception
	 */
	public void testDeleteGroupFailHasUser() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result=mockMvc.perform(
                (get("/admin_group/delete"))
                        .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/query")
        ).andReturn();
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	/**
	 * 測試刪除群組失敗
	 * 
	 * @throws Exception
	 */
	public void testDeleteGroupFailNull() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		mockMvc.perform(
                (get("/admin_group/delete"))
                        .param("oid", "999")
                        .session(session)
        ).andExpect(
        		status().isMethodNotAllowed()
        );
        
	}
	
	public void testDeleteGroupFailIdNull() throws Exception {

		// MockMvcRequestBuilders.post -> (post("url")
		MvcResult result = mockMvc.perform(
                (get("/admin_group/delete"))
                        .param("oid", "")
                        .session(session)
        ).andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}

	/**
	 * 測試群組查詢
	 * @throws Exception
	 */
	public void testQueryGroup() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/query"))
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
	}
	
	/**
	 * 測試群組查詢 BY ID
	 * @throws Exception
	 */
	public void testQueryGroupByID() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_group/query"))
        				.param("criteriaGroupId", "op")
        				.param("criteriaGroupName", "")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("op",page.getContent().get(0).getGroupId());
	}
	
	/**
	 * 測試群組查詢 BY NAME
	 * @throws Exception
	 */
	public void testQueryGroupByName() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_group/query"))
                		.param("criteriaGroupId", "")
                		.param("criteriaGroupName", "t1")
                		.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("t1",page.getContent().get(0).getGroupName());
	}
	
	
	/**
	 * 測試群組查詢
	 * @throws Exception
	 */
	public void testOpQueryGroup() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/op_query"))
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() >= 0);
	}
	
	/**
	 * 測試群組查詢 BY ID
	 * @throws Exception
	 */
	public void testOpQueryGroupByID() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_group/op_query"))
        				.param("criteriaGroupId", "op")
        				.param("criteriaGroupName", "")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("op",page.getContent().get(0).getGroupId());
	}
	
	/**
	 * 測試群組查詢 BY NAME
	 * @throws Exception
	 */
	public void testOpQueryGroupByName() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/admin_group/op_query"))
                		.param("criteriaGroupId", "")
                		.param("criteriaGroupName", "t1")
                		.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
        
		Page<AdminGroup> page = (Page<AdminGroup>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
        assertTrue(page.getContent().size() == 1);
        assertEquals("t1",page.getContent().get(0).getGroupName());
	}
	
	/**
	 * 測試群組明細查詢
	 * @throws Exception
	 */
	public void testDetail() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/detail"))
                		.param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/detail"))
		 .andReturn();
		assertEquals("op",((AdminGroup)result.getRequest().getAttribute("entity")).getGroupId());
	}
	
	/**
	 * 測試群組明細查詢失敗
	 * @throws Exception
	 */
	public void testDetailFail() throws Exception {
		mockMvc.perform(
                (get("/admin_group/detail"))
                		.param("oid", "999")
                        .session(session)
        ).andExpect(
        		status().isMethodNotAllowed());
		 
	}
	
	public void testDetailFailNull() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/detail"))
                		.param("oid", "")
                        .session(session)
        ).andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	
	public void testOpDetail() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/op_detail"))
                		.param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/op_detail"))
         .andReturn();
		assertEquals("op",((AdminGroup)result.getRequest().getAttribute("entity")).getGroupId());
	}
	
	public void testOpDetailFail() throws Exception {
		MvcResult result = mockMvc.perform(
                (get("/admin_group/op_detail"))
                		.param("oid", "")
                        .session(session)
        ).andExpect(
                view().name("/admin_group/op_query"))
         .andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}


	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testQueryGroup();
		testOpQueryGroup();
		testAdd();
		testAddGroup();
		testAddGroupFail();
		testAddGroupFailErr();
		testAddGroupFailSize0();
		testQueryGroupByID();
		testQueryGroupByName();
		testOpQueryGroupByID();
		testOpQueryGroupByName();
		testEdit();
		testEditFail();
		testEditGroup();
		testEditGroupFail();
		testEditGroupFailErr();
		testDeleteGroup();
		testDeleteGroupFailHasUser();
		testDeleteGroupFailNull();
		testDeleteGroupFailIdNull();
		testDetail();
		testDetailFail();
		testDetailFailNull();
		testOpDetail();
		testOpDetailFail();
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
