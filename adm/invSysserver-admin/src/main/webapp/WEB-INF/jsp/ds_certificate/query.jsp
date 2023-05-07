<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ds-issuer-cert.query" />
	<!--DS 客戶端證書查詢-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST"
		modelAttribute="IssuerCertCriteriaForm">
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
		<!--         查詢條件 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.search-criteria" />
						<!--查詢條件-->
					</div>
					<div class="panel-body">
						<div class="row">
							<!--                             (2)卡組織 -->
							<div class="col-md-3">
								<spring:bind path="criteriaCardScheme">
									<div class="form-group">
										<label class="control-label"> <spring:message
												code="ui.card-scheme.name" /> <!--卡組織-->
										</label>
										<form:select class="form-control" path="criteriaCardScheme">
											<form:option value="">
												<spring:message code="ui.option-select" />
												<!-- 												請選擇 -->
											</form:option>
											<form:options items="${cardSchemeConfigModel}"
												itemValue="codeId" itemLabel="codeDesc" />
										</form:select>
									</div>
								</spring:bind>
							</div>
							<!--                             (3)憑證狀態 -->
							<div class="col-md-3">
								<spring:bind path="status">
									<div class="form-group">
										<label class="control-label"><spring:message
												code="ui.issuer-cert.credential-status" /> <!--憑證狀態--> </label>
										<form:select class="form-control" path="status">
											<c:if test="${empty status.value}">
												<form:option value="">
													<spring:message code="ui.option-select" />
													<!--請選擇-->
												</form:option>
												<option value="1"><spring:message code="ENABLED.1" /></option>
												<option value="0"><spring:message code="ENABLED.0" /></option>
											</c:if>
											<c:if test="${not empty status.value}">
												<form:option value="">
													<spring:message code="ui.option-select" />
													<!--請選擇-->
												</form:option>
												<option value="1" ${status.value  == 1 ? 'selected' : ''}><spring:message
														code="ENABLED.1" /></option>
												<option value="0" ${status.value  == 0 ? 'selected' : ''}><spring:message
														code="ENABLED.0" /></option>
											</c:if>
										</form:select>
									</div>
								</spring:bind>
							</div>
							<!--                             (4)有效日期 -->
							<div class="col-md-3">
								<spring:bind path="notBefore">
									<div class="form-group">
										<label class="control-label"><spring:message
												code="ui.issuer-cert.validity-period" /> <!--有效日期--> </label>
										<div class='input-group date' id="start_date">
											<form:input path="notBefore" class="form-control" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</spring:bind>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div>
			<div class="row">
				<div class="col-md-12">
					<div class="pull-right">
						<a href="#" id="btnQuery" class="btn btn-primary"><spring:message
								code="btn.query" /> <!--查詢--></a>
					</div>
				</div>
			</div>

			<div style="height: 40px;"></div>

		</div>
		<!-- <h3>近來了</h3> -->
		<!--         查詢結果 -->
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr class="bg-primary">
								<%-- 								<th class="text-nowrap"><spring:message --%>
								<%-- 										code="ui.issuer.issuer-name" /> <!-- 銀行 --></th> --%>
								<th class="text-nowrap"><spring:message
										code="ui.card-scheme.name" /> <!-- 卡組織 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.issuer-cert.Signature-key-base-code-recognition" />
									<!-- 密鑰基碼識別 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.certificate-quest.common-name" /> <!-- 網域名稱(CN) --></th>
								<%-- 								<th class="text-nowrap"><spring:message --%>
								<%-- 										code="ui.ca-cert-certificate-cert-issuer-cn" /> <!-- 頒發者 --></th> --%>
								<th class="text-nowrap"><spring:message
										code="ui.issuer-cert.validity-period" /> <!-- 有效期限 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.issuer-cert.enabled-state" /> <!-- 啟用狀態 --></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="issuerCert">
								<tr>
									<%-- 									<td><c:out value="${issuerCert.issuer.issuerName}" /></td> --%>
									<td><spring:message
											code="CARD_SCHEME.${issuerCert.cardScheme}" /></td>
									<td><a
										href="./detail?oid=<c:out value="${issuerCert.oid}"/>"><c:out
												value="${issuerCert.keyAlias}" /></a></td>
									<td><c:out value="${issuerCert.subjectCn}" /></td>
									<%-- 									<td><c:out value="${issuerCert.certIssuerCn}" /></td> --%>
									<td><fmt:parseDate var="createTime"
											pattern="yyyyMMddHHmmss" value="${issuerCert.notBefore}" />
										<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" />~
										<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss"
											value="${issuerCert.notAfter}" /> <fmt:formatDate
											value="${createTime}" pattern="yyyy-MM-dd" /></td>
									<td><spring:message code="ENABLED.${issuerCert.status}" />
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--         /#page-wrapper -->
		<!--         paginator -->
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query"
				page="${queryResult}" />
		</c:if>
	</form:form>

</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnQuery").click(function() {
				$("#form1").attr("action", "./query");
				$('#form1').submit();
			});
		});
		$(function() {
			$('#start_date').datetimepicker({
				format : 'YYYY/MM/DD',
				defaultDate : new Date(),
				maxDate : new Date().setFullYear(new Date().getFullYear() + 10)
			});
		});
	</script>
</div>