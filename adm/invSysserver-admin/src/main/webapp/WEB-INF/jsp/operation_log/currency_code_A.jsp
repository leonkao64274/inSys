
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
			<c:if test="${empty dataAfter['entity']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['entity']}">
				<c:out value="${dataAfter['entity']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別名稱" code="ui.currency.currency" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['currency']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['currency']}">
				<c:out value="${dataAfter['currency']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別字母代碼" code="ui.currency.alphabeticCode" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['alphabeticCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['alphabeticCode']}">
				<c:out value="${dataAfter['alphabeticCode']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="幣別數字代碼" code="ui.currency.numericCode" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['numericCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['numericCode']}">
				<c:out value="${dataAfter['numericCode']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="金額小數位數" code="ui.currency.minorUnit" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['minorUnit']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['minorUnit']}">
				<c:out value="${dataAfter['minorUnit']}" />
			</c:if>
		</p>
		<hr>
	</div>

</div>
