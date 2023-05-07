/**
 * @(#)DsClientCertificateRequestControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的DsClientCertificateRequestController JUnit。
 *
 * Modify History:
 * v1.00,   /09/04, Steven 
 * 1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;

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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;
import com.invSet.emv.invSys.invSysserver.core.bean.CaCert;
import com.invSet.emv.invSys.invSysserver.core.bean.CertRequest;
import com.invSet.emv.invSys.invSysserver.core.bean.invSysServerCert;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;
import com.invSet.emv.invSys.invSysserver.core.service.CaCertService;
import com.invSet.emv.invSys.invSysserver.core.service.CertRequestService;
import com.invSet.emv.invSys.invSysserver.core.service.invSysServerCertService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CaCertControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	@InjectMocks
	private CaCertController caCertController;

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
		this.mockMvc = MockMvcBuilders.standaloneSetup(caCertController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}

	/**
	 *  查詢作業(直接查詢全部資料)
	 */
	public void testquery() throws Exception{
		mockMvc.perform(
				(get("/ca_certificate/query"))
				.session(session)
				).andExpect(
		                view().name("/ca_certificate/query")
				        );
	}
	
	/**
	 *  查詢作業(直接查詢全部資料)
	 */
	public void testshowLoad() throws Exception{
		mockMvc.perform(
				(get("/ca_certificate/load"))
				.session(session)
				).andExpect(
		                view().name("/ca_certificate/load")
				        );
	}
	
	/**
	 * DS 客戶端證書載入
	 */
	public void testUpload() throws IOException, Exception {

		Resource res = resourceLoader.getResource("classpath:PIT_CA.cer");

		MockMultipartFile multipartFile = new MockMultipartFile("file", "PIT_CA", "application/x-x509-ca-cert", res.getInputStream());

		mockMvc.perform(fileUpload("/ca_certificate/load")
				.file(multipartFile)
				.param("certAlias", "PIT_CA")
				.param("certEncode"  , "2")
				.session(session)).andExpect(status().isOk());
	}
	
	/**
	 * 進入明細頁
	 */
	public void testshowDetail() throws Exception{
		
		//取的頁面回傳資料
		MvcResult result = mockMvc.perform(
                (get("/ca_certificate/detail"))
                .param("oid", "1")
                        .session(session)
        ).andExpect(
                view().name("/ca_certificate/detail")
        ).andReturn();
		CaCert caCertTest = (CaCert)result.getRequest().getAttribute("caCert");

		assertNotNull(caCertTest);
	}
	
	/**
	 * 進入明細頁(反向)
	 */
	public void testshowDetailFail() throws Exception{
		
		//取的頁面回傳資料
		mockMvc.perform(
                (get("/ca_certificate/detail"))
                .param("oid", "3")
                        .session(session)
        ).andExpect(
                view().name("/ca_certificate/query")
        );

	}
	
	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testquery();
		testshowLoad();
		testUpload();
		testshowDetail();
		testshowDetailFail();
		
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
