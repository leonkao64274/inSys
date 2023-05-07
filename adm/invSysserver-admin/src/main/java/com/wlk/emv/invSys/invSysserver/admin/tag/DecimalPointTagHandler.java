/*
 * @(#)StringMaskTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 小數點處理標籤
 *
 * Modify History:
 * v1.00,  /08/18, LeonKao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * InvCore EMV invSys 系統 - 小數點處理標籤
 * 
 * @author LeonKao
 */
public class DecimalPointTagHandler extends SimpleTagSupport {

    private int pointLocation; 
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
            
            out.println(DecimalPointTagHandler.addDecimalPoint(pointLocation, text));
        } catch (java.io.IOException ex) {
            throw new JspException("Error in DecimalPointTagHandler tag", ex);
        }
    }
 
    public void setText(String text) {
        this.text = text;
    }

	public void setPointLocation(int pointLocation) {
		this.pointLocation = pointLocation;
	}
    
	
	private static String addDecimalPoint(int pointLocation, String text) { 
		 
		if(pointLocation<=0)return text;
		if(text.length()<=pointLocation){ 
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<pointLocation-text.length();i++){
				sb.append("0");
			}
			text = "0." + sb.toString() + text; 
		}else{
			text = text.substring(0, (text.length()-pointLocation)) 
					+ "."
					+ text.substring((text.length()-pointLocation),text.length());
		}
		
		return text; 
	}
	
}
