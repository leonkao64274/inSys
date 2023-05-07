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
		<h4>
			<spring:message text="服務器URL" code="ui.serverUrl" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['serverUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['serverUrl']}">
				<c:out value=" ${dataAfter['serverUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="執行類型" code="ui.opRunningType" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['opRunningType']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['opRunningType']}">
				<spring:message code="ui.opRunningType.${dataAfter['opRunningType']}" /> 
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
			<c:if test="${empty dataAfter['opDuration']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['opDuration']}">
				<c:out value=" ${dataAfter['opDuration']}" />
			</c:if>
		</p>
		<hr>
	</div>
	
</div>