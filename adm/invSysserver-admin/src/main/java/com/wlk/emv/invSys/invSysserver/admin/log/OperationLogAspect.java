/*
 * @(#)OperationLogAspect.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 切面類別
 *
 * Modify History:
 * v1.00,   /03/19, Leon Kao
 *   1) First release
 */
package com.invSet.emv.invSys.invSysserver.admin.log;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.invSet.emv.invSys.invSysserver.admin.controller.IndexLoginSession;
import com.invSet.emv.invSys.invSysserver.core.bean.AdminMenu;
import com.invSet.emv.invSys.invSysserver.core.bean.OperationLog;
import com.invSet.emv.invSys.invSysserver.core.exception.ResourceAlreadyExistException;
import com.invSet.emv.invSys.invSysserver.core.service.AdminMenuService;
import com.invSet.emv.invSys.invSysserver.core.service.OperationLogService;

/**
 * InvCore EMV invSys 系統 - invSys-Server 後台管理 "操作記錄" 切面類別
 * 
 * @author   LeonKao
 */
@Component
@Aspect
public class OperationLogAspect {
    
    /**
     * Logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);
    
    @Autowired
    private IndexLoginSession indexLoginSession;
    
    @Autowired
    private OperationLogService operationLogService;
    
    @Autowired
    private AdminMenuService adminMenuService;
    
    /**
     * 切面點：使用 @AcessRightCheck 的全部方法。
     */
    @Pointcut("@annotation(com.invSet.emv.invSys.invSysserver.admin.log.DoOperationLog))")
    public void pointCut() {}
    
    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        
        // 取得操作結果
        boolean opResult = false;
        if (LogContextHolder.getLogAttribute(LogContextHolder.ATTR_RESULT) != null) {
            opResult = (boolean) LogContextHolder.getLogAttribute(LogContextHolder.ATTR_RESULT);
        }
        
        // do Log with operation log
        this.doLog(joinPoint, opResult);
        
        // clear LogContextHolder
        LogContextHolder.clearLogAttributes();
        
    }
    
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        // do Log with operation result to false
        this.doLog(joinPoint, false);
        
        // clear LogContextHolder
        LogContextHolder.clearLogAttributes();
    }
    
    private void doLog(JoinPoint joinPoint, boolean opResult) {
        
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();        
        logger.debug("Begin to operation log - pointcut!(" + method.getDeclaringClass().getName() + "." +method.getName() +")");
        
        DoOperationLog annotation = method.getAnnotation(DoOperationLog.class);
        logger.debug("[accessId]:" + annotation.accessId() + ",[operation]:" + annotation.operation());
        
        OperationLog opLog = new OperationLog();
        opLog.setAccessId(annotation.accessId());
        opLog.setAction(annotation.operation());
        //opLog.setTargetObject(method.getDeclaringClass().getName() + "." +method.getName());
        opLog.setTargetObject(annotation.targetObject());
                
        // 查詢條件
        Object dataQuery = LogContextHolder.getLogAttribute(LogContextHolder.ATTR_DATA_QUERY);
        if (dataQuery != null) {
            opLog.setDataQuery((byte[])dataQuery);
        }
       
        
        // 異動前資料
        Object dataBefore = LogContextHolder.getLogAttribute(LogContextHolder.ATTR_DATA_BEFORE);
		if (dataBefore != null) {
		    opLog.setDataBefore((byte[])dataBefore);
		}
        
        // 異動後資料

        Object dataAfter = LogContextHolder.getLogAttribute(LogContextHolder.ATTR_DATA_AFTER);
        if (dataAfter != null) {
            opLog.setDataAfter((byte[])dataAfter);
        }
        // 操作結果
        opLog.setResult(opResult == true ? "0" : "1");
        
        
        // 因應反向代理機制, 調整抓取 IP 方式
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        
        String deviceIp = null;
        if (!StringUtils.isEmpty(request.getHeader("HTTP_CLIENT_IP"))) {
            logger.debug("HTTP_CLIENT_IP found!");
            deviceIp = request.getHeader("HTTP_CLIENT_IP");
        } else if (!StringUtils.isEmpty(request.getHeader("X-FORWARDED-FOR"))) {
            logger.debug("X-FORWARDED-FOR found!");
            deviceIp = request.getHeader("X-FORWARDED-FOR");
        } else {
            deviceIp = request.getRemoteAddr();
        }
        //String remoteAddress = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        opLog.setIpAddr(deviceIp);
        
        // 功能選單 i18 
        List<AdminMenu> menuList = adminMenuService.findByAccessId(opLog.getAccessId());
        if (!menuList.isEmpty()) {
            opLog.setI18nCode(menuList.get(0).getI18nCode());
        }
        
        // 操作人員 - Hosted
        if (indexLoginSession.getAdminUser() != null) {
            opLog.setAccount(indexLoginSession.getAdminUser().getAccount());
            opLog.setUserName(indexLoginSession.getAdminUser().getUserName());
        }
        
        if (StringUtils.isEmpty(opLog.getAccount())) {
            logger.debug("User account is empty, skip to do operation log!");
            return;
        }
        
        // 儲存操作記錄
        try {
            operationLogService.save(opLog);
        } catch (ResourceAlreadyExistException ex) {
            logger.warn("Failed to save operationLog!", ex);
        }
    }
    
}
