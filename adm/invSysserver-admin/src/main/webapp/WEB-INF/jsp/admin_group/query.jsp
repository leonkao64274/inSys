<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.admin-group.management" /><!-- 群組管理 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="adminGroupCriteriaForm">
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

		<!-- 查詢條件 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.search-criteria" /><!--查詢條件-->
					</div>
					<div class="panel-body">
						<div class="row">
							<!-- (1)群組代碼 -->
							<div class="col-md-3">
								<spring:bind path="criteriaGroupId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.admin-group.id" /><!-- 群組代號 -->
										</label>
										<form:input path="criteriaGroupId" class="form-control" maxlength="20" />
										<form:errors path="criteriaGroupId" />
									</div>
								</spring:bind>
							</div>
							<!-- (2)群組名稱 -->
							<div class="col-md-3">
								<spring:bind path="criteriaGroupName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.admin-group.name" /><!-- 群組名稱 -->
										</label>
										<form:input path="criteriaGroupName" class="form-control" maxlength="100" />
										<form:errors path="criteriaGroupName" />
									</div>
								</spring:bind>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="pull-right">
					<a href="#" id="btnQuery" class="btn btn-primary"><spring:message code="btn.query" /> <!--查詢--></a>
					<a href="./add" id="btnAdd" class="btn btn-default"><spring:message code="btn.new" /> <!--新增--></a>
				</div>
			</div>
		</div>

		<div style="height: 40px;"></div>

		<!-- 查詢結果 -->
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr class="bg-primary">
								<th class="text-nowrap"><spring:message code="ui.admin-group.id" /><!-- 群組代號 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-group.name" /><!-- 群組名稱 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-group.description" /><!-- 說明 --></th>
								<th class="text-nowrap">
									<spring:message code="ui.operation" /><!-- 執行選項 -->
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="adminGroup">
							<tr>
								<td><c:out value="${adminGroup.groupId}" /></td>
								<td><c:out value="${adminGroup.groupName}" /></td>
								<td><c:out value="${adminGroup.description}" /></td>
								<td>
									<a href="./detail?oid=<c:out value="${adminGroup.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.detail" /><!--明細--></a>
									<a href="./edit?oid=<c:out value="${adminGroup.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.update" /><!--修改--></a>
                                    <a href="./delete?oid=<c:out value="${adminGroup.oid}"/>" id="btnDelete_${adminGroup.oid}" class="btn btn-sm btn-default"><spring:message code="btn.delete" /><!--刪除--></a> 
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
		<!-- paginator -->
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
		</c:if>
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnQuery").click(function(){
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });
		});
	</script>
</div>