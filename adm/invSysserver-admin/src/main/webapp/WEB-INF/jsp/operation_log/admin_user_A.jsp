

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">

	<div class="col-md-12">
		<h4>
			<spring:message text="使用者代號" code="ui.admin-user.account" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['account']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['account']}">
				<c:out value="${dataAfter['account']}" />
			</c:if>
		</p>
		<hr>
	</div>


	<div class="col-md-12">
		<h4>
			<spring:message text="使用者名稱" code="ui.admin-user.user-name" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['userName']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['userName']}">
				<c:out value="${dataAfter['userName']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="群組" code="ui.admin-user.group" />
		</h4>
		<p>
			
			<admin:HostedGroupTagHandler text="${dataAfter['adminGroup']['oid']}"/>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="部門" code="ui.admin-user.department" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['department']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['department']}">
				<c:out value="${dataAfter['department']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="密碼 " code="ui.admin-user.password" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['password']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['password']}">
				<c:out value="${dataAfter['password']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="確認密碼 " code="ui.admin-user.cnfrpassword" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['cnfrPassword']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['cnfrPassword']}">
				<c:out value="${dataAfter['cnfrPassword']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="電話" code="ui.admin-user.tel" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['tel']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['tel']}">
				<c:out value="${dataAfter['tel']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="分機" code="ui.admin-user.ext" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['ext']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['ext']}">
				<c:out value="${dataAfter['ext']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="電子郵件" code="ui.admin-user.email" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['email']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['email']}">
				<c:out value="${dataAfter['email']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>