/*
 * @(#)invSysserverAdminApplication.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 Spring Boot 主應用程式
 *
 * Modify History:
 * v1.00,  /07/10, Leon Kao
 *   1) First release
 * v1.01,    /04/30, Leon Kao
 *   1) add @EnableEncryptableProperties for encryption properties with Jasypt 
 *
 */
package com.invSet.emv.invSys.invSysserver.admin;

//import com.cathaybk.encypt.Decrypter;
import com.invSet.emv.invSys.invSysserver.admin.filter.SiteMeshFilter;
import com.invSet.emv.invSys.invSysserver.admin.filter.UIBlockFilter;
import com.invSet.emv.invSys.invSysserver.core.invSysserverCoreConfiguration;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import net.sf.ehcache.CacheManager;
import java.io.IOException;
import org.springframework.core.io.ResourceLoader;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 Spring boot 主應用程式
 * 
 * @author   LeonKao
 */
//@EnableCaching
@SpringBootApplication
@Import(invSysserverCoreConfiguration.class)
@EnableEncryptableProperties
public class invSysserverAdminApplication {

	@Autowired
	private ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(invSysserverAdminApplication.class);
		application.run(args);
	}

	@Bean(name = "sitemesh3")
	SiteMeshFilter siteMeshFilter() {
		return new SiteMeshFilter();
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(@Qualifier("sitemesh3") SiteMeshFilter siteMeshFilter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(siteMeshFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				// container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,
				// "/500"));
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
				container.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405"));
				container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/400"));
			}
		};
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	@Bean
	public FilterRegistrationBean uiblockFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new UIBlockFilter());
		filterRegistrationBean.setEnabled(true);
		// filterRegistrationBean.addUrlPatterns("/hosted/*", "/issuer/*");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(3);
		return filterRegistrationBean;
	}

	@Bean(name = "echacheManager")
	public CacheManager ehCacheManagerFactoryBean() {
		CacheManager cacheManager = CacheManager.getCacheManager(CacheManager.DEFAULT_NAME);

		if (cacheManager == null) {
			try {
				cacheManager = CacheManager
						.create(resourceLoader.getResource("classpath:ehcache.xml").getInputStream());

			} catch (IOException e) {
				throw new RuntimeException("initialize cacheManager failed");
			}
		}
		return cacheManager;
	}
}
