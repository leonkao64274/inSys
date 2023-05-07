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
            <c:if test="${empty dataBefore['cardScheme']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['cardScheme']}">
                <spring:message text="${dataBefore['cardScheme']}" 
                                code="CARD_SCHEME.${dataBefore['cardScheme']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="密鑰基碼識別" code="ui.certificate-quest.key-alias" /></h4>
        <p>
            <c:if test="${empty dataBefore['keyAlias']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['keyAlias']}">
               <c:out value=" ${dataBefore['keyAlias']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="密鑰長度" code="ui.certificate-quest.key-length" /></h4>
        <p>
            <c:if test="${empty dataBefore['keyLength']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['keyLength']}">
               <c:out value=" ${dataBefore['keyLength']}" /> bits
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="簽章演算法" code="ui.certificate-quest.sign_algorithm" /></h4>
        <p>
            <c:if test="${empty dataBefore['signAlgorithm']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['signAlgorithm']}">
               <c:out value=" ${dataBefore['signAlgorithm']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="網域名稱(CN)" code="ui.certificate-quest.common-name" /></h4>
        <p>
            <c:if test="${empty dataBefore['commonName']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['commonName']}">
               <c:out value=" ${dataBefore['commonName']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="組織(O)" code="ui.certificate-quest.organization" /></h4>
        <p>
            <c:if test="${empty dataBefore['organization']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['organization']}">
               <c:out value=" ${dataBefore['organization']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="組織單位(OU)" code="ui.certificate-quest.organization_unit" /></h4>
        <p>
            <c:if test="${empty dataBefore['organizationUnit']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['organizationUnit']}">
               <c:out value=" ${dataBefore['organizationUnit']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="國家(C)" code="ui.certificate-quest.country" /></h4>
        <p>
            <c:if test="${empty dataBefore['country']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['country']}">
               <c:out value=" ${dataBefore['country']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="省份(ST)" code="ui.certificate-quest.province" /></h4>
        <p>
            <c:if test="${empty dataBefore['province']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['province']}">
               <c:out value=" ${dataBefore['province']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="地區(L)" code="ui.certificate-quest.locality" /></h4>
        <p>
            <c:if test="${empty dataBefore['locality']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['locality']}">
               <c:out value=" ${dataBefore['locality']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="文件名" code="ui.certificate-quest.file-name" /></h4>
        <p>
            <c:if test="${empty dataBefore['fileName']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['fileName']}">
               <c:out value=" ${dataBefore['fileName']}" /> 
            </c:if>
        </p>
        <hr>
    </div>
</div>