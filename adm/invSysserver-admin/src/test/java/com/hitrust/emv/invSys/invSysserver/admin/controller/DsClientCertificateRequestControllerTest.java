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

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;
import com.invSet.emv.invSys.invSysserver.core.bean.CertRequest;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;
import com.invSet.emv.invSys.invSysserver.core.service.CertRequestService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class DsClientCertificateRequestControllerTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	private MockHttpSession session;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private CertRequestService certRequestService;

	@Autowired
	@InjectMocks
	private DsClientCertificateRequestController dsClientCertificateRequestController;

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
		this.mockMvc = MockMvcBuilders.standaloneSetup(dsClientCertificateRequestController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}

	/**
	 * 顯示新增作業表單
	 * 
	 */
	public void testshowadd() throws Exception {
		mockMvc.perform(
                (get("/ds_csr/add"))
                        .session(session)
        ).andExpect(
                view().name("/ds_csr/add")
        );
	}
	
	/**
	 * 新增客戶端請求資料
	 */
	public void testadddata() throws Exception{
		mockMvc.perform((post("/ds_csr/add"))
				.param("cardScheme", "V")
				.param("keyAlias", "test02")
				.param("keyLength", "2048")
				.param("signAlgorithm", "SHA256withRSA")
				.param("commonName", "test02")
				.param("organization", "WQ")
				.param("organizationUnit", "WQ")
				.param("country", "WQ")
				.param("province", "WQ")
				.param("locality", "WQ")
				.param("fileName", "test02")
				.session(session)
				).andExpect(
						view().name("/ds_csr/query")
						);
		
		mockMvc.perform((post("/ds_csr/add"))
				.param("cardScheme", "M")
				.param("keyAlias", "test2")
				.param("keyLength", "2048")
				.param("signAlgorithm", "SHA256withRSA")
				.param("commonName", "test2")
				.param("organization", "WQ")
				.param("organizationUnit", "WQ")
				.param("country", "WQ")
				.param("province", "WQ")
				.param("locality", "WQ")
				.param("fileName", "test2")
				.session(session)
				).andExpect(
						view().name("/ds_csr/query")
						);
		
		assertNotNull(certRequestService.findBykeyAlias("test02"));
		assertNotNull(certRequestService.findBykeyAlias("test2"));
		
	}
	
	
	/**
	 * 新增客戶端請求資料(反向)
	 * 新增重複值
	 */
	public void testadddataError() throws Exception{
		MvcResult result =mockMvc.perform((post("/ds_csr/add"))
				.param("cardScheme", "V")
				.param("keyAlias", "test")
				.param("keyLength", "2048")
				.param("signAlgorithm", "SHA256withRSA")
				.param("commonName", "test")
				.param("organization", "WQ")
				.param("organizationUnit", "WQ")
				.param("country", "WQ")
				.param("province", "WQ")
				.param("locality", "WQ")
				.param("fileName", "test")
				.session(session)
				).andExpect(
						view().name("/ds_csr/add")
						).andReturn();;
		
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
		
	}
	

	/**
	 * 查詢客戶端頁面(查全部)
	 */
	public void testqueryall() throws Exception{
		MvcResult result =mockMvc.perform(
				(get("/ds_csr/query"))
				.session(session)
				).andReturn();
		Page<CertRequest> page = (Page<CertRequest>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
		assertTrue(page.getSize()!=0);
		page.forEach((certRequest)->System.out.println("testqueryall certRequest.getKeyAlias() = "+certRequest.getOid()));

	}
	
	/**
	 * 查詢客戶端頁面(輸入CardScheme)
	 * 根據卡組織
	 */
	public void  testQuerySelectCriteriaCardScheme() throws Exception{
		MvcResult result = mockMvc.perform(
                (post("/ds_csr/query"))
        				.param("criteriaCardScheme", "M")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
		Page<CertRequest> page = (Page<CertRequest>) result.getRequest().getAttribute("queryResult");
		assertNotNull(page);
		assertTrue(page.getContent().size()!=0);
	}
	
	/**
	 * 查詢客戶端頁面(輸入"网域名称")
	 * 根據網域名稱 查出資料
	 */
	public void  testQuerySelectCriteriaCommonName() throws Exception{
		MvcResult result = mockMvc.perform(
                (post("/ds_csr/query"))
        				.param("criteriaCommonName", "test2")
        				.param("pageNumber", "0")
                        .session(session)
        ).andReturn();
		
		Page<CertRequest> page = (Page<CertRequest>) result.getRequest().getAttribute("queryResult");
		
		assertNotNull(page);
		assertTrue(page.getContent().size()!=0);
	}
	
	/**
	 * 進入明細頁
	 */
	public void testshowDetail() throws Exception{
		
		//取的頁面回傳資料
		MvcResult result = mockMvc.perform(
                (get("/ds_csr/detail"))
                .param("oid", certRequestService.findBykeyAlias("test2").getOid().toString())
                        .session(session)
        ).andExpect(
                view().name("/ds_csr/detail")
        ).andReturn();
		CertRequest certRequestTest = (CertRequest)result.getRequest().getAttribute("form");
		
		//db取oid = 1的資料
		CertRequest certRequest = certRequestService.findBykeyAlias("test2");
		
		assertNotNull(certRequestTest);
		assertEquals(certRequest.getKeyAlias(), certRequestTest.getKeyAlias());
	}
	
	/**
	 * 進入明細頁(反向)
	 */
	public void testshowDetailError() throws Exception{
		
		//取的頁面回傳資料
		MvcResult result = mockMvc.perform(
                (get("/ds_csr/detail"))
                .param("oid", "99")
                        .session(session)
        ).andExpect(
                view().name("/ds_csr/query")
        ).andReturn();
		CertRequest certRequestTest = (CertRequest)result.getRequest().getAttribute("form");
		
		assertNull(certRequestTest);
	}
	
	/**
	 * 刪除证书请求檔
	 */
	public void testDelete() throws Exception {

		mockMvc.perform(
                (get("/ds_csr/delete"))
                        .param("oid", certRequestService.findBykeyAlias("test2").getOid().toString())
                        .session(session)
        ).andExpect(
                view().name("/ds_csr/query")
        );
        
		assertNull(certRequestService.findBykeyAlias("test2"));
	}
	
	public void DownloadFileTest() throws Exception {
		
		
		
		mockMvc.perform((get("/ds_csr/doGet")).param("oid", certRequestService.findBykeyAlias("test02").getOid().toString()).session(session))
				.andExpect(header()
						.string("Content-Disposition"
								, "attachment; filename=\"test02\""));
	}

	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testshowadd();
		testadddata();
		testadddataError();
		testqueryall();
		testQuerySelectCriteriaCardScheme();
		testQuerySelectCriteriaCommonName();
		testshowDetail();
		testshowDetailError();
		DownloadFileTest();
		testDelete();
		testqueryall();
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
