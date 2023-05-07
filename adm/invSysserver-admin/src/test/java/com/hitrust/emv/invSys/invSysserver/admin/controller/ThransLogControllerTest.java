/**
 * @(#)ThransLogControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的ThransLog JUnit。
 *
 * Modify History:
 * v1.00,   /09/05, Bob 
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import com.invSet.emv.invSys.invSysserver.core.bean.invSysTrans;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ThransLogControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;

	@Autowired
	private TransLogController transLogController;

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
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		this.session = (MockHttpSession) getLogin(mockMvc);
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); // 在test中重新配置視圖解析器
		resolver.setPrefix("/WEB_INF/jsp");
		resolver.setSuffix(".jsp");
		// 為了避免路經死循環，需再配置InternalResourceViewResolver，並指定給全域變數
		this.mockMvc = MockMvcBuilders.standaloneSetup(transLogController).setViewResolvers(resolver).build();
	}

	@After
	public void tearDown() {
	}

	/**
	 * test query log
	 * 
	 * @throws Exception
	 */
	public void testQuery() throws Exception {
		MvcResult result = mockMvc
				.perform((get("/invSys_trans/query")).param("startDate", "  -07-05")
						.param("invSysServerTransID", "38f3ecbb-3611-48e6-91a0-fcab21d3c89a")
						.param("endDate", "  -09-05").session(session))
				.andExpect(view().name("/transaction/query")).andReturn();
		Page<invSysTrans> page = (Page<invSysTrans>) result.getRequest().getAttribute(WebKeyConst.QUERY_RESULT);
		assertNotNull(page);
		assertTrue(page.getContent().size() == 1);
		assertEquals(page.getContent().get(0).getinvSysServerTransID(), "38f3ecbb-3611-48e6-91a0-fcab21d3c89a");

	}

	/**
	 * test query trans Detail
	 * 
	 * @throws Exception
	 */
	public void testDetail() throws Exception {
		MvcResult result = mockMvc.perform((get("/invSys_trans/detail")).param("oid", "1").session(session))
				.andExpect(view().name("/transaction/detail")).andReturn();

		assertEquals(((invSysTrans) (result.getRequest().getAttribute("form"))).getinvSysServerTransID(),
				"38f3ecbb-3611-48e6-91a0-fcab21d3c89a");

	}

	public void testDetailNull() throws Exception {

		MvcResult result = mockMvc.perform((get("/invSys_trans/detail")).param("oid", "").session(session))
				.andExpect(view().name("/transaction/query")).andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}

	public void testDetailRecall() throws Exception {
		MvcResult result = mockMvc.perform((get("/invSys_trans/detail")).param("oid", "9999").session(session))
				.andExpect(view().name("/transaction/query")).andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}

	private void form() {
		TransLogCriteriaForm form = new TransLogCriteriaForm();
		form.setAcctId("");
		form.setAcctNumberPostfix("");
		form.setAcctNumberPrefix("");
		form.setAcquirerMerchantID("");
		form.setAcsTransID("");
		form.setCriteriaCardScheme("M");
		form.setDeviceChannel("01");
		form.setMessageVersion("2.1.0");
		form.setTransStatus("Y");
		form.setPageNumber(1);
		form.setPurchaseAmount("1");
		form.setMessageCategory("01");
	}

	@Test
	public void test() throws Exception {
		testQuery();
		testDetail();
		testDetailRecall();
		testDetailNull();
		form();
	}

	/**
	 * 登入
	 * 
	 * @return
	 * @throws Exception
	 */
	private HttpSession getLogin(MockMvc mockMvc) throws Exception {
		MvcResult result = mockMvc.perform((post("/index/login").param("account", "Bob").param("password", "000000")
				.param("locale", "zh-CN").param("random8digits", "14252849")
				.param("encryptPassword", "f36a45e9539998afc40296c6950d649fbc53934b"))).andReturn();

		return result.getRequest().getSession();
	}
}
