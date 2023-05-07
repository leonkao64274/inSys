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

import com.invSet.emv.invSys.invSysserver.core.bean.invSysServerCert;
import com.invSet.emv.invSys.invSysserver.core.service.invSysServerCertService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DsIssuerCertManagementControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * invSys-Server憑證資訊
	 */
	@Autowired
	private invSysServerCertService invSysServerCertService;

	@Autowired
	@InjectMocks
	private DsIssuerCertManagementController dsIssuerCertManagementController;

//	@Autowired
//	private CertificateRequestService certificateRequestService;

	
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
		this.mockMvc = MockMvcBuilders.standaloneSetup(dsIssuerCertManagementController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}

	/**
	 * 查詢客戶端證書(查全部)
	 */
	public void testqueryall() throws Exception{
		MvcResult result =mockMvc.perform(
				(get("/ds_certificate_management/activate"))
				.session(session)
				).andReturn();
		Page<invSysServerCert> page = (Page<invSysServerCert>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
		assertTrue(page.getSize()!=0);
	}
	
	/**
	 * 查詢客戶端證書
	 * 根據卡組織、證書狀態、有效期限
	 */
	public void  testQuerySelectCriteria() throws Exception{
		MvcResult result = mockMvc.perform(
                (post("/ds_certificate_management/activate"))
        				.param("criteriaCardScheme", "V")
        				.param("status", "0")
        				.param("notBefore", "   /09/12")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
		Page<invSysServerCert> page = (Page<invSysServerCert>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
		assertTrue(page.getContent().size()!=0);
	}

	/**
	 * 進入明細頁
	 */
	public void testshowDetail() throws Exception{
		
		//取的頁面回傳資料
		MvcResult result = mockMvc.perform(
                (get("/ds_certificate_management/detail"))
                .param("oid", invSysServerCertService.findByKeyAlias("test").getOid().toString())
                        .session(session)
        ).andExpect(
                view().name("/ds_certificate_management/detail")
        ).andReturn();
		invSysServerCert invSysServerCertTest = (invSysServerCert)result.getRequest().getAttribute("form");
		
		//db取oid = 1的資料
		invSysServerCert invSysServerCert = invSysServerCertService.findByKeyAlias("test");
		
		assertNotNull(invSysServerCertTest);
		assertEquals(invSysServerCert.getKeyAlias(), invSysServerCert.getKeyAlias());
	}
	
	/**
	 * 進入明細頁(反向)
	 */
	public void testshowDetailError() throws Exception{
		
		//取的頁面回傳資料
		MvcResult result = mockMvc.perform(
                (get("/ds_certificate_management/detail"))
                .param("oid", "3")
                        .session(session)
        ).andExpect(
                view().name("/ds_certificate_management/activate")
        ).andReturn();
		invSysServerCert invSysServerCert = invSysServerCertService.findById(3L);
		
		assertNull(invSysServerCert);
	}
	
	/**
	 * DS 客戶端證書載入
	 */
	public void testUpload() throws IOException, Exception {

		Resource res = resourceLoader.getResource("classpath:ACSsigning_certificate.der");

		MockMultipartFile multipartFile = new MockMultipartFile("file", "ACSsigning_certificate", "application/x-x509-ca-cert", res.getInputStream());

		mockMvc.perform(fileUpload("/ds_certificate_management/upload")
				.file(multipartFile)
				.param("cardScheme", "V")
				.param("keyAlias"  , "test")
				.param("certEncode", "2")
				.session(session)).andExpect(status().isOk());
	}
	
	/**
	 * 顯示DS 客戶端證書載入作業表單
	 */
	public void testshowUpload() throws Exception{
		mockMvc.perform(
				(get("/ds_certificate_management/upload"))
				.session(session)
				).andExpect(
		                view().name("/ds_certificate_management/upload")
				        );
	}
	
	/**
	 * 顯示DS 客戶端證書開啟作業表單
	 */
	public void testshowEnabledForm() throws Exception{
		mockMvc.perform(
				(get("/ds_certificate_management/activate"))
				.session(session)
				).andExpect(
		                view().name("/ds_certificate_management/activate")
				        );
	}
	
	/**
	 * 顯示DS 客戶端證書開啟作業表單
	 */
	public void testenableCredentialsForm() throws Exception{
		mockMvc.perform(
				(get("/ds_certificate_management/enable")
						.param("oid", invSysServerCertService.findByKeyAlias("test").getOid().toString()))
				.session(session)
				).andExpect(
		                view().name("/ds_certificate_management/activate")
				        );
	}
	
	public void DownloadFileTest() throws Exception {
		mockMvc.perform((get("/ds_certificate_management/export"))
				.param("oid", invSysServerCertService.findByKeyAlias("test").getOid().toString())
				.param("recipient", "123123")
				.param("checkRecipient", "123123")
				.session(session))
				.andExpect(header()
						.string("Content-Disposition"
								, "attachment; filename=\"test.p12\""));
	}

	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testshowUpload();
		testUpload();
		testqueryall();
		testQuerySelectCriteria();
		testshowDetail();
		testshowDetailError();
		testshowEnabledForm();
		testenableCredentialsForm();
		DownloadFileTest();
		
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
