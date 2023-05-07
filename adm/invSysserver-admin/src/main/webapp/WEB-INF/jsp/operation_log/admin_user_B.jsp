

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
			<c:if test="${empty dataBefore['account']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['account']}">
				<c:out value="${dataBefore['account']}" />
			</c:if>
		</p>
		<hr>
	</div>


	<div class="col-md-12">
		<h4>
			<spring:message text="使用者名稱" code="ui.admin-user.user-name" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['userName']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['userName']}">
				<c:out value="${dataBefore['userName']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="群組" code="ui.admin-user.group" />
		</h4>
		<p>
			<admin:HostedGroupTagHandler text="${dataBefore['adminGroup']['oid']}"/>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="部門" code="ui.admin-user.department" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['department']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['department']}">
				<c:out value="${dataBefore['department']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="密碼 " code="ui.admin-user.password" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['password']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['password']}">
				<c:out value="${dataBefore['password']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="確認密碼 " code="ui.admin-user.cnfrpassword" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['cnfrPassword']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['cnfrPassword']}">
				<c:out value="${dataBefore['cnfrPassword']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="電話" code="ui.admin-user.tel" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['tel']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['tel']}">
				<c:out value="${dataBefore['tel']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="分機" code="ui.admin-user.ext" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['ext']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['ext']}">
				<c:out value="${dataBefore['ext']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="電子郵件" code="ui.admin-user.email" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['email']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['email']}">
				<c:out value="${dataBefore['email']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>