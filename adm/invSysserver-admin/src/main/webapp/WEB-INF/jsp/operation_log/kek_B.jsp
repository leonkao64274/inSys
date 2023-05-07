

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
			<c:if test="${empty dataBefore['keyAlias']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['keyAlias']}">
				<c:out value="${dataBefore['keyAlias']}" />
			</c:if>
		</p>
		<hr>
	</div>


	<div class="col-md-12">
		<h4>
			<spring:message text="演算法" code="ui.kek.alg" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['algorithm']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['algorithm']}">
				<c:out value="${dataBefore['algorithm']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="金鑰長度" code="ui.kek.key-size" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['keySize']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['keySize']}">
				<c:out value="${dataBefore['keySize']}" />
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
			<c:if test="${empty dataBefore['status']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['status']}">
				<spring:message code="ENABLED.${dataBefore['status']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>