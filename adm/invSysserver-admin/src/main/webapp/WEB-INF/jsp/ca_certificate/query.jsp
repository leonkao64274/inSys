<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ca-cert-certificate-find" />
	<!--信任 CA 證書查詢-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<!-- 錯誤訊息區 -->
	<c:if test="${not empty errors}">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger">
					<p class="fa fa-warning">
						<c:out value="${errors}" />
					</p>
				</div>
			</div>
		</div>
	</c:if>
	<!-- 查詢結果 -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-striped table-hover">
					<thead>
						<tr class="bg-primary">
							<th class="text-nowrap"><spring:message
									code="ui.ca-cert-certificate.cert-alias" />
								<!--識別名稱--></th>
							<th class="text-nowrap"><spring:message
									code="ui.ca-cert-certificate-subject-cn" />
								<!--網域名稱(CN)--></th>
							<th class="text-nowrap"><spring:message
									code="ui.ca-cert-certificate-cert-issuer-cn" />
								<!--頒發者--></th>
							<th class="text-nowrap"><spring:message
									code="ui.ca-cert-certificate-valid-period" />
								<!--有效期限--></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${queryResult.content}" var="caCertificate">
							<tr>
								<td><a
									href="./detail?oid=<c:out value="${caCertificate.oid}"/>">${caCertificate.certAlias}</a></td>
								<td>${caCertificate.subjectCn}</td>
								<td>${caCertificate.certAuthorCn}</td>
								<td><fmt:parseDate var="createTime"
										pattern="yyyyMMddHHmmss" value="${caCertificate.notBefore}" />
									<fmt:formatDate pattern="yyyy/MM/dd" value="${createTime}" /> ~
									<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss"
										value="${caCertificate.notAfter}" /> <fmt:formatDate
										pattern="yyyy/MM/dd" value="${createTime}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<form action="./query" id="form1" method="post">
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query"
				page="${queryResult}" />
		</c:if>
	</form>
</div>
<!-- Custom Page JavaScript -->
<div id="sitemesh-script"></div>