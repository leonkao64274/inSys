<%-- 
    Document   : transaction_log_Q
    Created on :   /3/16, 下午 07:33:56
    Author     :   LeonKao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld" %>


<div class="row">

    
        <div class="col-md-12">
        <h4><spring:message text="卡組織" code="ui.card-scheme.name" /></h4>
        <p>
            <c:if test="${empty dataAfter['cardScheme']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['cardScheme']}">
                <spring:message text="${dataAfter['cardScheme']}" 
                                code="CARD_SCHEME.${dataAfter['cardScheme']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="密鑰基碼識別" code="ui.certificate-quest.key-alias" /></h4>
        <p>
            <c:if test="${empty dataAfter['keyAlias']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['keyAlias']}">
               <c:out value=" ${dataAfter['keyAlias']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="密鑰長度" code="ui.certificate-quest.key-length" /></h4>
        <p>
            <c:if test="${empty dataAfter['keyLength']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['keyLength']}">
               <c:out value=" ${dataAfter['keyLength']}" /> bits
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="簽章演算法" code="ui.certificate-quest.sign_algorithm" /></h4>
        <p>
            <c:if test="${empty dataAfter['signAlgorithm']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['signAlgorithm']}">
               <c:out value=" ${dataAfter['signAlgorithm']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="網域名稱(CN)" code="ui.certificate-quest.common-name" /></h4>
        <p>
            <c:if test="${empty dataAfter['commonName']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['commonName']}">
               <c:out value=" ${dataAfter['commonName']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="組織(O)" code="ui.certificate-quest.organization" /></h4>
        <p>
            <c:if test="${empty dataAfter['organization']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['organization']}">
               <c:out value=" ${dataAfter['organization']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="組織單位(OU)" code="ui.certificate-quest.organization_unit" /></h4>
        <p>
            <c:if test="${empty dataAfter['organizationUnit']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['organizationUnit']}">
               <c:out value=" ${dataAfter['organizationUnit']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="國家(C)" code="ui.certificate-quest.country" /></h4>
        <p>
            <c:if test="${empty dataAfter['country']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['country']}">
               <c:out value=" ${dataAfter['country']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="省份(ST)" code="ui.certificate-quest.province" /></h4>
        <p>
            <c:if test="${empty dataAfter['province']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['province']}">
               <c:out value=" ${dataAfter['province']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="地區(L)" code="ui.certificate-quest.locality" /></h4>
        <p>
            <c:if test="${empty dataAfter['locality']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['locality']}">
               <c:out value=" ${dataAfter['locality']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="文件名" code="ui.certificate-quest.file-name" /></h4>
        <p>
            <c:if test="${empty dataAfter['fileName']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['fileName']}">
               <c:out value=" ${dataAfter['fileName']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
</div>