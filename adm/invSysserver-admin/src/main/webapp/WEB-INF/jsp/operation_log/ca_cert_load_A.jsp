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
        <h4><spring:message text="識別名稱" code="ui.ca-cert-certificate.cert-alias" /></h4>
        <p>
            <c:if test="${empty dataAfter['certAlias']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['certAlias']}">
               <c:out value=" ${dataAfter['certAlias']}" />
            </c:if>
        </p>
        <hr>
    </div>
    <div class="col-md-12">
        <h4><spring:message text="證書格式" code="ui.ca-cert-certificate-cert-encode" /></h4>
        <p>
            <spring:message code="ui.cert.certEncode.${dataAfter['certEncode']}"/>
        </p>
        <hr>
    </div>
</div>


