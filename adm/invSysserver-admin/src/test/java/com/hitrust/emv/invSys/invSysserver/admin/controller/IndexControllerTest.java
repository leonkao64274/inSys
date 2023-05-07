/**
 * @(#)IndexControllerTest.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 -invSys Server 後台管理 的登入與健康  JUnit。
 *
 * Modify History:
 * v1.00,   /9/7, Bob last
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.invSet.emv.invSys.invSysserver.admin.util.WebKeyConst;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminUser;
import com.invSet.emv.invSys.invSysserver.core.service.AdminUserService;
import com.invSet.emv.invSys.invSysserver.core.util.CodingUtil;
import com.invSet.emv.invSys.invSysserver.core.util.DateUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class IndexControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private AdminUserService adminUserService;

	@Autowired
	@InjectMocks
	private IndexController indexController;

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
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver(); // 在test中重新配置視圖解析器
		resolver.setPrefix("/WEB_INF/jsp");
		resolver.setSuffix(".jsp");
		//為了避免路經死循環，需再配置InternalResourceViewResolver，並指定給全域變數
		this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).setViewResolvers(resolver).build();

	}

	@After
	public void tearDown() {
	}
	/**
	 * 測試轉址
	 * @throws Exception
	 */
	public void testIndexRoot() throws Exception {
		mockMvc.perform(
                (get("/"))
        ).andExpect(
                view().name("redirect:/index/login"));
	}
	/**
	 * 測試轉址
	 * @throws Exception
	 */
	public void testIndex() throws Exception {
		mockMvc.perform(
                (get("/index"))
        ).andExpect(
                view().name("redirect:/index/login"));
	}
	/**
	 * 測試轉址
	 * @throws Exception
	 */
	public void testIndexlogin() throws Exception {
		mockMvc.perform(
                (get("/index/login"))
        ).andExpect(
                view().name("/index/login"));
	}
	/**
	 * 測試登入
	 * @throws Exception
	 */
	public void testLogin() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/index/login")
                        .param("account", "Bob")
                        .param("password", "000000")
                        .param("locale", "zh-CN")
                        .param("random8digits", "14252849")
                        .param("encryptPassword", "f36a45e9539998afc40296c6950d649fbc53934b"))
                		
        ).andExpect(view().name("redirect:/index/health")).andReturn();
		assertEquals(((AdminUser)result.getRequest().getSession().getAttribute("adminUser")).getAccount(), "Bob");
	}
	/**
	 * 測試登入失敗
	 * @throws Exception
	 */
	public void testLoginFail() throws Exception {
		MvcResult result = mockMvc.perform(
                (post("/index/login")
                        .param("account", "Fail")
                        .param("password", "000000")
                        .param("locale", "zh-CN")
                        .param("random8digits", "44702844")
                        .param("encryptPassword", "Fail"))
                		
        ).andExpect(view().name("/index/login")).andReturn();
		assertNotNull(result.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	/**
	 * 測試首次登入更改失敗(未登入)
	 * @throws Exception
	 */
	public void testAdmFistLogin() throws Exception {
		AdminUser adminUser = adminUserService.findById(1L);
		adminUser.setLastPswdDttm(null);
        adminUserService.update(adminUser);
        HttpSession session;
        MvcResult result = mockMvc.perform(
                (post("/index/login"))
                .param("account", "SYSADMIN")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "24471354")
                .param("encryptPassword", "d66895f7e9a88b2be0e764b154aa7abd82d115c1")
        ).andExpect(
                view().name("/index/sysadm_first_login")
        ).andReturn();
        session=result.getRequest().getSession();
        
		mockMvc.perform(
                (post("/index/sysadm_first_login"))
                .param("account", "SYSADMIN")
                .param("newAccount", "SYSADMIN")
                .param("password", "30e002fbc5bca3f789003c8462a9dd6da255a5d5")
                .param("random8digits", "36323490")
                .param("encryptPassword", "30e002fbc5bca3f789003c8462a9dd6da255a5d5")
                .param("encryptNewPassword", "6fffa68b79fcc5c5dfcf6ccfe7b95c30ae4f1466")
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("/index/login")
        ).andReturn();
		
		
		MvcResult result2 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "SYSADMIN")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "13072763")
                .param("encryptPassword", "fb07b8c63f78bfa5a205b0c44f17086a6bf6a0e6")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
		assertEquals(((AdminUser)result2.getRequest().getSession().getAttribute("adminUser")).getAccount(), "SYSADMIN");
		
	}
	/**
	 * 測試系統管理員首次登入與更改密碼
	 * @throws Exception
	 */
	public void testFirstUser() throws Exception {
		AdminUser adminUser = adminUserService.findById(2L);
		adminUser.setLastPswdDttm(null);
        adminUserService.update(adminUser);
        
        HttpSession session;
        
        MvcResult result3 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("/index/first_login")
        ).andReturn();
        
        session=result3.getRequest().getSession();
        
        mockMvc.perform(
                (post("/index/first_login"))
                .param("account", "Bob")
                .param("password", "d6ac37376cbde5439aeb0ef9a48bdf878559b536")
                .param("random8digits", "28377369")
                .param("encryptPassword", "d6ac37376cbde5439aeb0ef9a48bdf878559b536")
                .param("encryptNewPassword", "e6856cadc5114fc11f56eac8cbe0d9bff9da24c8")
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("/index/login")
        ).andReturn();
        
        MvcResult result4 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "32465564")
                .param("encryptPassword", "3b494601505ac9fd3e1bdcd7aa1a014f2f1c1da2")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
		assertEquals(((AdminUser)result4.getRequest().getSession().getAttribute("adminUser")).getAccount(), "Bob");
	}
	
	/**
	 * 測試更改密碼
	 * @throws Exception
	 */
	public void testChangePassword() throws Exception {
		MvcResult result5 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "32465564")
                .param("encryptPassword", "3b494601505ac9fd3e1bdcd7aa1a014f2f1c1da2")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
		
		HttpSession session;
		session=result5.getRequest().getSession();
		
		mockMvc.perform(
                (get("/index/password"))
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("/index/password"));
	}
	/**
	 * 測試登入
	 * @throws Exception
	 */
	public void testSubmitPassword() throws Exception {
		MvcResult result6 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "32465564")
                .param("encryptPassword", "3b494601505ac9fd3e1bdcd7aa1a014f2f1c1da2")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
		
		HttpSession session;
		session=result6.getRequest().getSession();
		
		MvcResult result7 = mockMvc.perform(
                (post("/index/password"))
                .param("account", "Bob")
                .param("password", "5d1f5080b3131264f034b197295db690b22bcbdc")
                .param("random8digits", "361  3")
                .param("encryptPassword", "5d1f5080b3131264f034b197295db690b22bcbdc")
                .param("encryptNewPassword", "9035ea3f0f857d0209ad86046d7119a999dbd63d")
                .param("locale", "zh_CN")
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("/index/password")
        ).andReturn();
		assertNotNull(result7.getRequest().getAttribute(WebKeyConst.SUCCESS_MSG));
	}
	/**
	 * 測試登入失敗
	 * @throws Exception
	 */
	public void testSubmitPasswordFail() throws Exception {
		MvcResult result6 = mockMvc.perform(
                (post("/index/login")
                        .param("account", "Bob")
                        .param("password", "000000")
                        .param("locale", "zh-CN")
                        .param("random8digits", "14252849")
                        .param("encryptPassword", "f36a45e9539998afc40296c6950d649fbc53934b"))
                		
        ).andExpect(view().name("redirect:/index/health")).andReturn();
		
		HttpSession session;
		session=result6.getRequest().getSession();
		
		MvcResult result7 = mockMvc.perform(
                (post("/index/password"))
                .param("account", "Bob")
                .param("password", "5d1f5080b3131264f034b197295db690b22bcbdc")
                .param("random8digits", "361  3")
                .param("encryptPassword", "5d1f5080b3131264f034b197295db690b22bcbdc")
                .param("encryptNewPassword", "1234567892312345678912345678912345678912")
                .param("locale", "zh_CN")
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("/index/password")
        ).andReturn();
		assertNotNull(result7.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	/**
	 * 測試登出
	 * @throws Exception
	 */
	public void testLogout() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
        assertNotNull(session.getAttribute("adminUser"));
        
        mockMvc.perform(
                (get("/index/logout"))
                .session((MockHttpSession) session)
        ).andExpect(
                view().name("redirect:/index/login")
        );
	}
	/**
	 * 測試增加健康管理
	 * @throws Exception
	 */
	public void systemHealthaAdd() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		MvcResult result9 = mockMvc.perform(
                (post("/index/health-add")
                .param("addSystemName", "Test")
                .param("addSystemUrl", "http://demo.higotw.com.tw:8060/invSysserver-admin"))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
		assertNotNull(result9.getRequest().getAttribute(WebKeyConst.SUCCESS_MSG));
	}
	
	/**
	 * 測試增加健康管理fail
	 * @throws Exception
	 */
	public void systemHealthaAddFailSystemNull() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		mockMvc.perform(
                (post("/index/health-add")
                .param("addSystemUrl", "demo.higotw.com.tw:8060/invSysserver-admin"))
                .session((MockHttpSession) session)
        ).andExpect(model().hasErrors());
		
		mockMvc.perform(
                (post("/index/health-add")
                .param("addSystemName", "test"))
                .session((MockHttpSession) session)
        ).andExpect(model().hasErrors());

	}
	
	public void systemHealthaAddFailRepeat() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		MvcResult result9 = mockMvc.perform(
                (post("/index/health-add")
                .param("addSystemName", "Test")
                .param("addSystemUrl", "demo.higotw.com.tw:8060/invSysserver-admin"))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
		assertNotNull(result9.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	/**
	 * 測試健康管理刪除
	 * @throws Exception
	 */
	public void systemHealthaDelete() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		MvcResult result9 = mockMvc.perform(
                (post("/index/health-delete")
                .param("systemOid", "1"))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
		assertNotNull(result9.getRequest().getAttribute(WebKeyConst.SUCCESS_MSG));
	}
	
	public void systemHealthaDeleteFailOidEmpty() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		MvcResult result9 = mockMvc.perform(
                (post("/index/health-delete")
                .param("systemOid", ""))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
		assertNotNull(result9.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	public void systemHealthaDeleteFailDataNull() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
		MvcResult result9 = mockMvc.perform(
                (post("/index/health-delete")
                .param("systemOid", "9999"))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
		assertNotNull(result9.getRequest().getAttribute(WebKeyConst.ERRORS));
	}
	
	/**
	 * 測試能取得健康管理
	 * @throws Exception
	 */
	public void checkSystemHealth() throws Exception {
		HttpSession session;
        
        MvcResult result8 = mockMvc.perform(
                (post("/index/login"))
                .param("account", "Bob")
                .param("password", "000000")
                .param("locale", "zh-CN")
                .param("random8digits", "75528704")
                .param("encryptPassword", "551c870ef796627fd96bd29ee917da0e26795c8b")
        ).andExpect(
                view().name("redirect:/index/health")
        ).andReturn();
        
        session=result8.getRequest().getSession();
        
        MvcResult result9 = mockMvc.perform(
                (get("/index/health"))
                .session((MockHttpSession) session)
        ).andExpect(view().name("/index/main")
        ).andReturn();
        session=result8.getRequest().getSession();
  /*      SystemHealthForm form=(SystemHealthForm) result9.getRequest().getAttribute("form");
        assertNotNull(form);
        assertEquals(form.getSystemHealth().get(0).getSystemOid().toString(),"1");
        */
        
	}
	
	
	private void form() {
		/*
		SystemHealthForm form=new SystemHealthForm("", "", 1.0, 1.0, 1.0, 1.0);
		form.getUsedDiskSpace();
		form.getUsedDiskSpacePercentage();
		form.getUsedMemoryPercentage();
		
		*/
	}

	/**
	 * 確定執行順序
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		testIndexRoot();
		testIndex();
		testIndexlogin();
		testLogin();
		testLoginFail();
		testFirstUser();
		testAdmFistLogin();
		testChangePassword();
		testSubmitPassword();
		testSubmitPasswordFail();
		testLogout();
		systemHealthaAdd();
		systemHealthaAddFailRepeat();
		systemHealthaAddFailSystemNull();
		checkSystemHealth();
		systemHealthaDelete();
		systemHealthaDeleteFailOidEmpty();
		systemHealthaDeleteFailDataNull();
		form();
	}

}
