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
        <h4><spring:message text="商戶類別代碼" code="ui.mcc-risk.mcc" /></h4>
        <p>
            <c:if test="${empty dataBefore['mcc']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['mcc']}">
               <c:out value=" ${dataBefore['mcc']}" />
            </c:if>
        </p>
        <hr>
    </div>

    <div class="col-md-12">
        <h4><spring:message text="風險等級" code="ui.mcc-risk.risk-level" /></h4>
        <p>
            <c:if test="${empty dataBefore['riskLevel']}">
                N/A
            </c:if>
            <c:if test="${not empty dataBefore['riskLevel']}">
                <spring:message text="${dataQuery['riskLevel']}"  code="RISK_LEVEL.${dataBefore['riskLevel']}" />
            </c:if>
        </p>
        <hr>
    </div>
</div>


