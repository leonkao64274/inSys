/*
 * @(#)AccessControlAspect.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統 "功能權限檢核" 切面類別
 *
 * Modify History:
 * v1.00,  /09/06, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.aspect;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.invSet.emv.invSys.invSysserver.admin.controller.IndexLoginSession;
import com.invSet.emv.invSys.invSysserver.admin.exception.InvalidAccessException;
import com.invSet.emv.invSys.invSysserver.core.bean.LoginSessionKey;
import com.invSet.emv.invSys.invSysserver.core.service.LoginSessionKeyService;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統  "功能權限檢核" 切面類別
 * 
 * @author Leon Kao
 */
@Component
@Aspect
public class AccessControlAspect {
    
    /**
     * Logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(AccessControlAspect.class);
    
	@Autowired
	private MessageSource messageSource;
    
    @Autowired
    private IndexLoginSession indexLoginSession;
    
    @Autowired
    private LoginSessionKeyService loginSessionKeyService;
    
    /**
     * 切面點：使用 @AcessRightCheck 的全部方法。
     */
    @Pointcut("@annotation(com.invSet.emv.invSys.invSysserver.admin.aspect.AcessRightCheck)")
    public void pointCut() {}
    
    /**
     * 切面方法：使用 @AcessRightCheck 的全部方法。
     */    
    @Before("pointCut())")
    public void before(JoinPoint joinPoint) {
        // 取得檢核權限代碼
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.debug("into before pointcut!(" + method.getDeclaringClass().getName() + "." +method.getName() +")");
        
        AcessRightCheck rightCheck = method.getAnnotation(AcessRightCheck.class);
  
        //換頁判斷loginSessionkey是否正確 不正確則將使用者踢出系統
  		LoginSessionKey ils = indexLoginSession.getLoginSessionKey();
//  		logger.debug("sessionkey1 = "+ils.getSessionKey());
  		if (ils != null) {
  			LoginSessionKey loginSessionKey2 = loginSessionKeyService.findByid(ils.getOid());
//  			logger.debug("sessionkey2 = "+loginSessionKey2.getSessionKey());
  			
  			if (loginSessionKey2 != null) {

  				if (!ils.getSessionKey().equals(loginSessionKey2.getSessionKey())) {
  					throw new InvalidAccessException(
  		                    messageSource.getMessage("warn.login.session-timeout", 
  		                            null, LocaleContextHolder.getLocale()));
  				}
  			}
  		}
        
        // 檢核使用者登入狀態
        if (indexLoginSession.getAdminUser() == null) {

            logger.info("您尚未登錄系統或已操作逾時自動被登出，請重新執行[登錄]作業。");
            throw new InvalidAccessException(
                    messageSource.getMessage("warn.login.session-timeout", 
                            null, LocaleContextHolder.getLocale()));
        }

        // 檢核是否授權使用該功能
        if (!isAccessAllowed(rightCheck.accessId(), rightCheck.operation(), 
                indexLoginSession.getGrantedAccessIdList())) {
            logger.info("您無此功能的操作權限，請洽系統管理員");
            throw new InvalidAccessException(
                    messageSource.getMessage("warn.login.invalid-access", 
                            null, LocaleContextHolder.getLocale()));
        }
            
    }
    
    private boolean isAccessAllowed(String accessId, String operation, List<String> grantedAccessIdList) {
        
        if (StringUtils.isEmpty(accessId)) {
            return true;
        }
        
        return grantedAccessIdList.contains(accessId);
    }
}
