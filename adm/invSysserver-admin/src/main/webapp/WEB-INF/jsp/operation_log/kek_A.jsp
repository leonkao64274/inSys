

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">

	<div class="col-md-12">
		<h4>
			<spring:message text="密鑰基碼識別" code="ui.kek.key-alias" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['keyAlias']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['keyAlias']}">
				<c:out value="${dataAfter['keyAlias']}" />
			</c:if>
		</p>
		<hr>
	</div>


	<div class="col-md-12">
		<h4>
			<spring:message text="演算法" code="ui.kek.alg" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['algorithm']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['algorithm']}">
				<c:out value="${dataAfter['algorithm']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="金鑰長度" code="ui.kek.key-size" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['keySize']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['keySize']}">
				<c:out value="${dataAfter['keySize']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="啟用狀態" code="ui.cek.status" />
		</h4>
		<!--啟用狀態-->
		<p>
			<c:if test="${empty dataAfter['status']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['status']}">
				<spring:message code="ENABLED.${dataAfter['status']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>