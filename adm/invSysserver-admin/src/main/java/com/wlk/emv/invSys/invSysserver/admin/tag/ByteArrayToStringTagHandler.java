/*
 * @(#)StringMaskTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - ByteArray 轉為 String 處理標籤
 *
 * Modify History:
 * v1.00,  /08/18, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;
 
import java.nio.charset.StandardCharsets;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * InvCore EMV invSys 系統 - ByteArray 轉為 String 處理標籤
 * 
 * @author LeonKao
 */
public class ByteArrayToStringTagHandler extends SimpleTagSupport {

    /**
     * byte array data
     */
    private byte[] text;

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
            if(text != null && text.length > 0){
                String str = new String(text, StandardCharsets.UTF_8);
                out.println(str.trim());
            } else {
                out.println("");
            }
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ByteArrayToStringTagHandler tag", ex);
        }
    }

    public void setText(byte[] text) {
        this.text = text;
    }
    
}
