/*
 * @(#)ErrorController.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys Server 後台管理  "錯誤網頁 " 控制器類別
 *
 * Modify History:
 * v1.00,  /08/18, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;
 
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping; 

/**
 * InvCore EMV invSys 系統 - invSys Server 後台管理  "錯誤網頁" 控制器類別
 * 
 * @author LeonKao
 */
@Controller
public class ErrorController {
    
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @GetMapping("/400")
    public String badRequest(HttpServletRequest request) {
        return "error/400";
    }
    @GetMapping("/404")
    public String notFound(HttpServletRequest request) {
        return "error/404";
    }
    @GetMapping("/405")
    public String methodNotAllowed(HttpServletRequest request) {
        return "error/405";
    }
    @GetMapping("/500")
    public String serverError(HttpServletRequest request) {
        return "error/500";
    }
}
