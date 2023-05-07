/*
 * @(#)AppBindingInitializer.java
 *
 * Copyright (c)   invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 *      InvCore EMV invSys 系統 - invSys-Server後台管理模組表單提交資料綁定處理器
 *
 * Modify History:
 * v1.00,  /06/19, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * InvCore EMV invSys 系統 - invSys-Server後台管理模組表單提交資料綁定處理器<br>
 * 
 * Spring 控制器針對前端網頁提交的參數資料，如為未填入欄位參數，則參數值預設為空白字串，透過此
 * 資料綁定處理器，將未填入欄位參數值預設為 null，以供後端 Spring Data 透過 QBE (Query By Example)
 * 機制進行過濾查詢時，避免因屬性值為空白字串而列入篩選條件欄位。
 * 
 * @author   LeonKao , Steven Kao
 */
@ControllerAdvice
@Controller
public class AppBindingInitializer {
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
