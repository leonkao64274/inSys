
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
			<spring:message text="金鑰基碼識別" code="ui.kek.key-alias" />
		</h4>
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
		<h4>
			<spring:message code="ui.certificate-quest.common-name"
				text="網域名稱(CN)" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['subjectCn']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['subjectCn']}">
				<c:out value=" ${dataBefore['subjectCn']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message code="ui.ca-cert-certificate-cert-issuer-cn"
				text="頒發者" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['certAuthorCn']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['certAuthorCn']}">
				<c:out value=" ${dataBefore['certAuthorCn']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message code="ui.issuer-cert.validity-period" text="有效期限" />
		</h4>
		<p>
			
			<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss"
				value="${dataBefore['notBefore']}" />
			<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" />
			~
			<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss"
				value="${dataBefore['notAfter']}" />
			<fmt:formatDate var="notAfter" value="${createTime}"
				pattern="yyyy-MM-dd " />
			<c:out value="${notAfter}" />
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="憑證狀態" code="ui.kek.key-alias" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['status']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['status']}">
				<spring:message text="" code="ENABLED.${dataBefore['status']}" />
			</c:if>
		</p>
		<hr>
	</div>

</div>


