/*
 * @(#)StringMaskTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 字串遮罩處理標籤
 *
 * Modify History:
 * v1.00,  /06/18, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import com.invSet.emv.invSys.invSysserver.core.util.StringUtil;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * InvCore EMV invSys 系統 - 字串遮罩處理標籤
 * 
 * @author   LeonKao
 */
public class StringMaskTagHandler extends SimpleTagSupport {

    private int prefixLen;
    private int postfixLen;
    private char placeholder;
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
            out.println(StringUtil.mask(prefixLen, postfixLen, placeholder, text));
        } catch (java.io.IOException ex) {
            throw new JspException("Error in StringMaskTagHandler tag", ex);
        }
    }

    public void setPrefixLen(int prefixLen) {
        this.prefixLen = prefixLen;
    }

    public void setPostfixLen(int postfixLen) {
        this.postfixLen = postfixLen;
    }

    public void setPlaceholder(char placeholder) {
        this.placeholder = placeholder;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
