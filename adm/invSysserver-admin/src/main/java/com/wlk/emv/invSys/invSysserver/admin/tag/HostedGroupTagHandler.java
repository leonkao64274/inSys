/*
 * @(#)HostedGroupTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 群組顯示處理標籤
 *
 * Modify History:
 * v1.00,   /04/17, BOB
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.invSet.emv.invSys.invSysserver.core.bean.AdminGroup;
import com.invSet.emv.invSys.invSysserver.core.service.AdminGroupService;
import com.invSet.emv.invSys.invSysserver.core.util.SpringContextUtil;
/**
 * InvCore EMV invSys 系統 - 群組顯示處理標籤
 * 
 * @author BOB
 */
public class HostedGroupTagHandler extends SimpleTagSupport{

	private static final Logger log = LoggerFactory.getLogger(HostedGroupTagHandler.class);
	private String text;

	@Override
	public void doTag() throws JspException {
		JspWriter out = getJspContext().getOut();

		try {

			JspFragment f = getJspBody();
			if (f != null) {
				f.invoke(out);
			}

			// 查詢取得issuer
			AdminGroupService adminGroupService = SpringContextUtil.getApplicationContext().getBean("AdminGroupService",
					AdminGroupService.class);

			long oid = 0L;
			try {
				oid = Long.parseLong(text);
				AdminGroup adminGroup = adminGroupService.findById(oid);
				if (adminGroup != null)
					text = adminGroup.getGroupName();
				else
					text = "N/A";
			} catch (NumberFormatException e) {
				text = "N/A";
				log.error("oid not number");
			} catch(Exception e2){
				text = "N/A";
				log.error(e2.getMessage());
			}

			out.println(text);

		} catch (java.io.IOException ex) {
			throw new JspException("Error in CurrenyTagHandler tag", ex);
		}
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
