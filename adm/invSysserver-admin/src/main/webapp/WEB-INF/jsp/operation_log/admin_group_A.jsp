

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">

	<div class="col-md-12">
		<h4>
			<spring:message text="群組代號" code="ui.admin-group.id" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['groupId']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['groupId']}">
				<c:out value="${dataAfter['groupId']}" />
			</c:if>
		</p>
		<hr>
	</div>


	<div class="col-md-12">
		<h4>
			<spring:message text="群組名稱" code="ui.admin-group.name" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['groupName']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['groupName']}">
				<c:out value="${dataAfter['groupName']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="說明" code="ui.admin-group.description" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['description']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['description']}">
				<c:out value="${dataAfter['description']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<c:if test="${not empty dataAfter['adminGroupPrivileges']}">
				<c:forEach items="${dataAfter['adminGroupPrivileges']}" var="permission" varStatus="index">
					<div class="col-md-4">
					<c:set var="keyString">${permission['accessId']}</c:set>
					<spring:message code="${accessIdMap[keyString]}" text="${permission['accessId']}"/>
					</div>
				</c:forEach>

		</c:if>
	</div>
	
</div>