/*
 * @(#)ExceptionControllerAdvice.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理系統  - 異常錯誤處理類別
 *
 * Modify History:
 * v1.00,  /09/06, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.exception;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理系統 - 異常錯誤處理類別
 * 
 * @author   LeonKao
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
    
    /**
     * Logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    
    /**
     * 預設顯示錯誤訊息的 JSP 路徑
     */
    public static final String DEFAULT_ERROR_VIEW = "/error/error";
    
    /**
     * "異常權限操作" 錯誤處理
     * 
     * @param req http servlet request
     * @param ex  "異常權限操作" 異常類別, 用於取得錯誤說明訊息
     * 
     * @return 顯示錯誤訊息的 JSP 路徑
     */
    @ExceptionHandler(InvalidAccessException.class)
    public ModelAndView invalidAccessExceptionHandler(HttpServletRequest req, InvalidAccessException ex) {

        String btnActionUrl = req.getContextPath() + "/index/login";
        
        // 依據 hosted or issuer 決定錯誤訊息頁 "確認" 按鈕點擊後的 URL
        logger.debug("[request URI]:" + req.getRequestURI());
        logger.debug("[context]:" +  req.getContextPath());
       
        logger.warn("Invalid Access Exception:" + ex.getMessage());
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("btnActionUrl", btnActionUrl);
        
        // 依據各系統，導頁至對應錯誤網頁顯示路徑
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
