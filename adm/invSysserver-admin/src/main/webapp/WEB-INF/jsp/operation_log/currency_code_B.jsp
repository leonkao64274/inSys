
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別所屬國家或機構" code="ui.currency.entity" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['entity']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['entity']}">
				<c:out value="${dataBefore['entity']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別名稱" code="ui.currency.currency" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['currency']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['currency']}">
				<c:out value="${dataBefore['currency']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別字母代碼" code="ui.currency.alphabeticCode" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['alphabeticCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['alphabeticCode']}">
				<c:out value="${dataBefore['alphabeticCode']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別數字代碼" code="ui.currency.numericCode" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['numericCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['numericCode']}">
				<c:out value="${dataBefore['numericCode']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="金額小數位數" code="ui.currency.minorUnit" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['minorUnit']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['minorUnit']}">
				<c:out value="${dataBefore['minorUnit']}" />
			</c:if>
		</p>
		<hr>
	</div>

	
</div>
