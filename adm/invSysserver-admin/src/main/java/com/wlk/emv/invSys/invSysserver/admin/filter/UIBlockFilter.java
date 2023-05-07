/*
 * @(#)UIBlockFilter.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - 畫面阻擋攔截控制器
 *
 * Modify History:
 * v1.00,  /09/29, Leon Kao
 *   1) First release
 * v1.01,   /03/09, LeonKao
 *   1) 增加登入後踢前判斷
 * v1.02,    /03/06, Leon Kao
 *   1) 中信弱點掃描問題修正, 加上 Thread.currentThread().interrupt() 處理
 * v1.03,    /04/30, Leon Kao
 *   1) 調整 sleep 時間為 200 毫秒
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;

/**
 * 畫面阻擋攔截控制器 <br>		
 * 搭配前端網頁處理中 spin 效果使用, 避免系統切換過快，導致使用者看不到處理中 spin 動畫效果.
 * 
 * @author   LeonKao
 */
public class UIBlockFilter implements Filter {
    
    /**
     * Logging utility
     */
    private static final Logger logger = LoggerFactory.getLogger(UIBlockFilter.class);
    
    private FilterConfig fc;

    @Override
    public void init(FilterConfig fc) throws ServletException {
        this.fc = fc;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {        
           	
        // 強制執行緒進入睡眠, 以供前端網頁顯示 "處理中 spin" 效果
        //logger.debug("UIBlockFilter..........");       
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            logger.warn("Failed to sleep in UIBlockFilter!", ex);
			Thread.currentThread().interrupt();
        }
        HttpServletResponse response =(HttpServletResponse)servletResponse;
        response.setHeader("Strict-Transport-Security", "max-age-31536000; includeSubDomains; preload");
        response.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");
        
        chain.doFilter(servletRequest, response);
//        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // do nothing
    }
    
}
