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
        <h4><spring:message text="發卡銀行" code="ui.issuer.name" /></h4>
        <p>
            <c:if test="${empty dataAfter['issuer']['oid']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['issuer']['oid']}">
                <c:out value="${dataAfter['issuer']['oid']}" />
            </c:if>        
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="有效時間(分鐘)" code="ui.issuerAttempt.expiry(min)" /></h4>
        <p>
            <c:if test="${empty dataAfter['expiry']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['expiry']}">
               <c:out value=" ${dataAfter['expiry']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="有效次數" code="ui.issuerAttempt.frequency"/></h4>
        <p>
            <c:if test="${empty dataAfter['frequency']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['frequency']}">
                <c:out value="${dataAfter['frequency']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="單筆最大金額" code="ui.issuerAttempt.maxAmount"/></h4>
        <p>
            <c:if test="${empty dataAfter['maxAmount']}">
                N/A
            </c:if>
            <c:if test="${not empty dataAfter['maxAmount']}">
                <c:out value="${dataAfter['maxAmount']}" />
            </c:if>
        </p>
        <hr>
    </div>

</div>

