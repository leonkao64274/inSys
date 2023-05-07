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
            <c:if test="${empty dataBefore['issuer']['oid']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['issuer']['oid']}">
                <c:out value="${dataBefore['issuer']['oid']}" />
            </c:if>        
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="有效時間(分鐘)" code="ui.issuerAttempt.expiry(min)" /></h4>
        <p>
            <c:if test="${empty dataBefore['expiry']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['expiry']}">
               <c:out value=" ${dataBefore['expiry']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="有效次數" code="ui.issuerAttempt.frequency"/></h4>
        <p>
            <c:if test="${empty dataBefore['frequency']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['frequency']}">
                <c:out value="${dataBefore['frequency']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="單筆最大金額" code="ui.issuerAttempt.maxAmount"/></h4>
        <p>
            <c:if test="${empty dataBefore['maxAmount']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['maxAmount']}">
                <c:out value="${dataBefore['maxAmount']}" />
            </c:if>
        </p>
        <hr>
    </div>

</div>


