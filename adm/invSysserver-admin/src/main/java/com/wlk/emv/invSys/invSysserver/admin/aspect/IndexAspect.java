/*
 * @(#)IndexAspect.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *     InvCore EMV invSys 系統 - invSys Server 後台管理系統 "首頁" 切面類別
 *
 * Modify History:
 * v1.00,  /08/24, Milo Gao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invSet.emv.invSys.invSysserver.admin.controller.IndexLoginSession;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統   "首頁" 切面類別
 * 
 * @author MiloGao
 */
@Aspect
@Component
public class IndexAspect {

	private static Logger logger = LoggerFactory.getLogger(IndexAspect.class);
	
    /**
     * Session Scope 的管理員登錄訊息。
     */
    @Autowired
    private IndexLoginSession indexLoginSession;

    /**
     * 切面點：管理員包的全部方法。
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) " + 
    		"&& execution(public String com.invSet.emv.invSys.invSysserver.admin.controller.*Controller.*(..))")
    public void hosted() {}
    
    /**
     * 切面方法：管理員包的全部方法。
     */
//    @After()
    @Around("hosted()")
    public Object hostedAround(ProceedingJoinPoint point) throws Throwable {
    	
    	String signature = point.getSignature().toString();
    	logger.info("signature = " + signature);
    	
		if ((signature.indexOf("IndexController") < 0) ||
			(signature.indexOf("IndexController") >= 0 && signature.indexOf("changePassword") >= 0)) {
			if (indexLoginSession.getAdminUser() == null) {
				logger.info("redirect:/index/login");
				return "redirect:/index/login"; 
			}
		}
    	return point.proceed();
    }
 
}
