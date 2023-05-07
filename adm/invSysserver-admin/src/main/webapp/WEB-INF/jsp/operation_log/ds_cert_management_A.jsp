
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
        <h4><spring:message text="金鑰基碼識別" code="ui.issuer-cert.Signature-key-base-code-recognition" /></h4>
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
        <h4><spring:message text="證書格式" code="ui.issuer-cert.Signature-certificate-format" /></h4>
        <p>
            <spring:message code="ui.cert.certEncode.${dataAfter['certEncode']}" text="N/A"/>
        </p>
        <hr>
    </div>
</div>

