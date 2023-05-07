<%-- 
    Document   : transaction_log_Q
    Created on :   /3/16, 下午 07:33:56
    Author     :   LeonKao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">
	<div class="col-md-12">
		<h4>
			<spring:message text="卡組織" code="ui.card-scheme.name" />
		</h4>
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
		<h4>
			<spring:message text="服務器URL" code="ui.serverUrl" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['serverUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['serverUrl']}">
				<c:out value=" ${dataBefore['serverUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="執行類型" code="ui.opRunningType" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['opRunningType']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['opRunningType']}">
				<spring:message code="ui.opRunningType.${dataBefore['opRunningType']}" /> 
            </c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="執行週期"
				code="ui.opDuration" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['opDuration']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['opDuration']}">
				<c:out value=" ${dataBefore['opDuration']}" />
			</c:if>
		</p>
		<hr>
	</div>
	
</div>