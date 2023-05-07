<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<jsp:useBean id="now" class="java.util.Date" />
<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ds-issuer-cert.management" />
	<!--DS 客戶端證書管理-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST"
		modelAttribute="IssuerCertManagementCriteriaForm">
		<!-- 錯誤訊息區 -->
        <c:if test="${not empty errors}">
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-danger">
                    <p class="fa fa-warning"><c:out value="${errors}"/></p>
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
							
							<!--  (1)卡組織 -->
							<div class="col-lg-3">
								<spring:bind path="criteriaCardScheme">
									<div class="form-group">
										<label class="control-label"> <spring:message
												code="ui.card-scheme.name" /> <!--卡組織-->
										</label>
										<form:select class="form-control" path="criteriaCardScheme">
											<form:option value="">
												<spring:message code="ui.option-select" />
												<!--請選擇-->
											</form:option>
											<form:options items="${cardSchemeConfigModel}"
												itemValue="codeId" itemLabel="codeDesc" />
										</form:select>
									</div>
								</spring:bind>
							</div>
							<!--  (2)憑證狀態 -->
							<div class="col-lg-3">
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
							<!--  (3)有效日期 -->
							<div class="col-lg-3">
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
				<div class="col-lg-12">
					<div class="pull-right">
						<a href="#" id="btnQuery" class="btn btn-primary"><spring:message
								code="btn.query" /> <!--查詢--></a>
						<a href="./upload" id="btnAdd" class="btn btn-default" ignore-spin="Y"><spring:message text="載入" code="btn.upload" /> <!--載入--></a>
					</div>
				</div>
			</div>

			<div style="height: 40px;"></div>

		</div>
		<!-- <h3>近來了</h3> -->
		<!--         查詢結果 -->
		<div class="row">
			<div class="col-lg-12">
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
								<th class="text-nowrap"><spring:message
										code="ui.ca-cert-certificate-cert-issuer-cn" /> <!-- 頒發者 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.issuer-cert.validity-period" /> <!-- 有效期限 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.issuer-cert.credential-status" /> <!-- 憑證狀態 --></th>
								<th class="text-nowrap"><spring:message code="ui.operation" />
									<!-- 執行選項 --></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="issuerCert">
								<tr>
									<%-- 									<td><c:out value="${issuerCert.issuer.issuerName}" /></td> --%>
									<td><spring:message
											code="CARD_SCHEME.${issuerCert.cardScheme}" /></td>
									<td><a
										href="./detail?oid=<c:out value="${issuerCert.oid}"/>"> <c:out
												value="${issuerCert.keyAlias}" /></a></td>
									<td><c:out value="${issuerCert.subjectCn}" /></td>
									<td><c:out value="${issuerCert.certAuthorCn}" /></td>
									<td><fmt:parseDate var="createTime"
											pattern="yyyyMMddHHmmss" value="${issuerCert.notBefore}" />
										<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd" />~
										<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${issuerCert.notAfter}" /> 
										<fmt:formatDate var="notAfter" value="${createTime}" pattern="yyyy-MM-dd " />
										<c:out value="${notAfter}" /></td>
									<td><spring:message code="ENABLED.${issuerCert.status}" />
									</td>
									<fmt:formatDate var="nowDate" value="${now}" pattern="yyyy-MM-dd " />
									<td><c:if
											test="${issuerCert.status != 1 && nowDate < notAfter}">
											<a href="./enable?oid=${issuerCert.oid}"
												id="btnEnable_${issuerCert.oid}"
												class="btn btn-sm btn-default"> <spring:message
													code="btn.setup" /> <!--啟用-->
											</a>
										</c:if>
										<button type="button" class="btn btn-sm btn-default" data-toggle="modal" data-target="#exampleModal" data-whatever="${issuerCert.oid}" ignore-spin="Y"><spring:message code="btn.export" /><!--匯出--></button>
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
			<admin:PaginatorTagHandler formId="form1" pagingUri="./activate"
				page="${queryResult}" />
		</c:if>
	</form:form>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"><spring:message text="" code="ui.ds-issuer-cert.private-key-management" /></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" ignore-spin="Y">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="form" action="#">
						<div class="form-group">
							<label for="recipient" class="col-form-label"><spring:message text="" code="ui.ds-issuer-cert.set-password" />:</label>
							<input type="text" class="form-control" id="recipient" name="recipient">					
							<input type="hidden" class="form-control" id="oid" name="oid">
						</div>
					</form>
				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal" ignore-spin="Y">Close</button>
				<button id="btnExport" type="button" class="btn btn-secondary" data-dismiss="modal" ignore-spin="Y"><spring:message code="btn.export" /><!--匯出--></button>
				</div>
			</div>
		</div>
	</div>

</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnQuery").click(function() {
				$("#form1").attr("action", "./activate");
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
		$('#exampleModal').on('show.bs.modal', function (event) {
      	  var button = $(event.relatedTarget) // Button that triggered the modal       	 
      	  var recipient = button.data('whatever') // Extract info from data-* attributes
      	  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
      	  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
      	  var modal = $(this)

      	  $("#btnExport").click(function() {
      		$("#oid").val(recipient);
      		$("#form").attr("action", "./export");
				$('#form').submit();
			});
     	 })
	</script>
</div>