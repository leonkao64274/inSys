/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.invSet.emv.invSys.invSysserver.admin.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

/**
 *
 * @author  LeonKao
 */
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {
    
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/WEB-INF/jsp/template/main_page.jsp")
                .addExcludedPath("/index/*")
                .addExcludedPath("/500")
                .addExcludedPath("/405")
                .addExcludedPath("/404")
                .addExcludedPath("/400")
                .addExcludedPath("/error")
                .addDecoratorPath("/index/health*", "/WEB-INF/jsp/template/main_page.jsp")
                .addDecoratorPath("/index/password", "/WEB-INF/jsp/template/main_page.jsp");
        
        // 增加 write property 從 <div> 截取資料的支援, ex: <sitemesh:write property='div.content' />
        builder.addTagRuleBundle(new DivExtractingTagRuleBundle());
    }
    
}
