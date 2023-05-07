/*
 * @(#)PaginatorTagHandler.java
 *
 * Copyright (c)    invSet Incorporated.
 * All rights reserved.
 * 
 * Description:
 * 		InvCore EMV invSys 系統 - 後台管理系統分頁處理標籤
 *
 * Modify History:
 * v1.00,  /06/18, Leon Kao
 *   1) First release
 *
 */
package com.invSet.emv.invSys.invSysserver.admin.tag;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;

/**
 * InvCore EMV invSys 系統 - 後台管理系統分頁處理標籤<br>
 * 
 * 依據前端網頁傳入的分頁查詢資料，組成 bootstrap 的分頁器，處理規則說明如下：<br>
 * 1. 如果分頁頁數 <= 5 頁，則依實際頁數組成分頁器, ex: << 1 2 3 >> <br>
 * 2. 如果分頁頁數 > 5 頁，則分頁器最多顯示 5 頁選項, 當前頁次為中間頁次 ex: << 6 7 (8) 9 10 >>
 * 
 * @author   LeonKao
 */
public class PaginatorTagHandler extends SimpleTagSupport {

    private Page page;
    private String pagingUri;
    private String formId;

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
            if (page == null) {
                return;
            }
            
            int pageNumber = page.getNumber() + 1;
            long rowCount = page.getTotalElements();
            int totalPage = page.getTotalPages();
            
                    
            int prePage = pageNumber-1;
            if (prePage <= 0) {
                prePage = 1;
            }
            int nextPage = pageNumber + 1;
            if (nextPage > totalPage) {
                nextPage = totalPage;
            }
            
            // 計算份頁顯示頁數
            ArrayList<Integer> list = new ArrayList<Integer>();
            // 5頁以內
            if (totalPage <= 5) {
                for (int i=1; i<=totalPage; i++) {
                    list.add(Integer.valueOf(i));
                }
            } else {
                list.add(pageNumber);
                // 往前後遞迴兩組
                for (int i=1; i<=2; i++) {
                    // 往後頁數
                    if ((pageNumber + i) <= totalPage) {
                        list.add(Integer.valueOf(pageNumber+i));
                    }
                    // 往前頁數
                    if ((pageNumber - i) >= 1) {
                        list.add(0, Integer.valueOf(pageNumber-i));
                    }
                }
                if (list.size()<5) {
                    int diff = 5-list.size();
                    if ((list.get(0)).intValue() == 1)  {
                        for (int i=1; i<=diff; i++) {
                            list.add(Integer.valueOf(list.get(list.size()-1).intValue() + 1));
                        }
                    } else {
                        for (int i=1; i<=diff; i++) {
                            list.add(0, Integer.valueOf((list.get(0)).intValue() - 1));
                        }
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("<div class='col-md-6'>");
            sb.append("</br>"+getI18nName("ui.confirm.total.number") + rowCount+ " / "+ getI18nName("ui.confirm.total.pages") + totalPage);
            sb.append("</div>");
            
            //舊有分頁
            sb.append("<div class='text-right col-md-6'>");
            sb.append("   <nav aria-label='Page navigation'>");
            sb.append("       <ul class='pagination'>");
            sb.append("           <li>");
            sb.append("               <a href='javascript:pagingClick(" + prePage + ");' aria-label='Previous'>");
            sb.append("                   <span aria-hidden='true'>&laquo;</span>");
            sb.append("               </a>");
            sb.append("           </li>");
            
            for (int i=0; i<list.size(); i++) {
                // 如為當前頁面，需high light顯示
                if (list.get(i).intValue() == pageNumber) {
                    sb.append("           <li class='active'><a href='javascript:pagingClick("+ list.get(i) +");'>" + list.get(i) + "</a></li>");
                } else {
                    sb.append("           <li><a href='javascript:pagingClick("+ list.get(i) +");'>" + list.get(i) + "</a></li>");
                }
            }
            sb.append("           <li>");
            sb.append("               <a href='javascript:pagingClick(" + nextPage + ");' aria-label='Next'>");
            sb.append("                   <span aria-hidden='true'>&raquo;</span>");
            sb.append("               </a>");
            sb.append("           </li>");
            sb.append("       </ul>");
            sb.append("   </nav>");
            //sb.append("   <input id='pageNumber' name='pageNumber' type='hidden' value='" + (pageNumber-1) +"'/>");
            sb.append("   <input id='pageNumber' name='pageNumber' type='hidden' value='0'/>");
            sb.append("   <script type='text/javascript'>");
            sb.append("       function pagingClick(pagenumber) {");
            sb.append("           $('#" + formId + "').attr('action', './" + pagingUri + "');");
            sb.append("           if (pagenumber != null && pagenumber != '') {");
            sb.append("               $('#pageNumber').val(pagenumber-1);");
            sb.append("           }");
            sb.append("           $('#" + formId + "').submit();");
            sb.append("       }");
            sb.append("   </script>");
            sb.append("</div>");
            
            out.println(sb.toString());


        } catch (java.io.IOException ex) {
            throw new JspException("Error in PaginatorTagHandler tag", ex);
        }
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void setPagingUri(String pagingUri) {
        this.pagingUri = pagingUri;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
    
    /**
     * 取得多國語系的文字
     * 
     * @author Steven    /03/12
     * 
     * @param String i18nCode 
     * @return 多國語系的文字
     */
	private String getI18nName(String i18nCode) {

        // i18n檔案名稱開頭 - messages
		ResourceBundle res = ResourceBundle.getBundle("messages",LocaleContextHolder.getLocale());
		
		String I18nName = null;
		try {
			if (i18nCode == null)
				return "";
			I18nName = res.getString(i18nCode);
		} catch (java.util.MissingResourceException ex) { 
			I18nName = "";
		}
		if (I18nName == null) {
			I18nName = "";
		}
		return I18nName;
	}
    
}
