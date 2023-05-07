
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message text="" code="ui.search-criteria" />
			</div>
			<div class="panel-body">
				<div class="row">


					<div class="col-md-12">
						<h4>
							<spring:message text="群組" code="ui.admin-user.group" />
						</h4>
						<p>

							<admin:HostedGroupTagHandler
								text="${dataQuery['criteriaIssuerOid']}" />
						</p>
						<hr>
					</div>


					<div class="col-md-12">
						<h4>
							<spring:message text="使用者代號" code="ui.admin-user.account" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['criteriaAccount']}">
                                N/A
                            </c:if>
							<c:if test="${not empty dataQuery['criteriaAccount']}">
                                ${dataQuery['criteriaAccount']}
                            </c:if>
						</p>
						<hr>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>