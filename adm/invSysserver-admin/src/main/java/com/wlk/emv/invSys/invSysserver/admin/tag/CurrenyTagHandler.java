/*
 * @(#)CurrenyTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 貨幣數字代碼  轉變為  貨幣字母代碼  處理標籤
 *
 * Modify History:
 * v1.00,  /08/18, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.invSet.emv.invSys.invSysserver.core.bean.Currency;
import com.invSet.emv.invSys.invSysserver.core.service.CurrencyService;
import com.invSet.emv.invSys.invSysserver.core.util.SpringContextUtil;

/**
 * InvCore EMV invSys 系統 - 貨幣數字代碼  轉變為  貨幣字母代碼  處理標籤
 * 
 * @author LeonKao
 */
public class CurrenyTagHandler extends SimpleTagSupport {

	private static Logger LOG = Logger.getLogger(CurrenyTagHandler.class);
    
    @Autowired
    private CurrencyService currencyService;
	
    //private String numericCode;
    private String text;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
            
            // autowire bean properties
            if (currencyService == null) {
                SpringContextUtil.autowireBeanProperties(
                        this, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
            }
            
            // 查詢取得功能選單項與個別子功能選項
            Currency currency = currencyService.getByNumericCode(text);
            
            //LOG.debug("[grantedAccessIdList size]: " + currencyList.size());
            //LOG.debug("[menuList size]: " + menuList.size());

            if(currency!=null && currency.getAlphabeticCode()!=null){
            	String alphabeticCode = currency.getAlphabeticCode();
            	out.println(alphabeticCode);
            }
            else out.print("");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in CurrenyTagHandler tag", ex);
        }
    }
 
    public void setText(String text) {
        this.text = text;
    }
}
