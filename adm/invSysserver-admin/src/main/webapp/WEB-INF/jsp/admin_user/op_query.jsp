<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.admin-user.query" /><!-- 群組使用者管理 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="adminUserCriteriaForm">
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
							<!-- 查詢條件(1)：群組 -->
							<div class="col-md-3">
								<spring:bind path="criteriaAdminGroupOid">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.admin-user.group" /><!-- 群組 -->
									</label>
									<form:select path="criteriaAdminGroupOid" class="form-control">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${adminUserCriteriaForm.configAdminGroupList}" 
												itemValue="oid" itemLabel="groupName" />
									</form:select>
									<form:errors path="criteriaAdminGroupOid" />
								</div>
								</spring:bind>
							</div>
							<!-- 查詢條件(2): 使用者代號 -->
							<div class="col-md-3">
								<spring:bind path="criteriaAccount">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.admin-user.account" /><!-- 使用者代號 -->
									</label>
									<form:input path="criteriaAccount" class="form-control" maxlength="20" />
									<form:errors path="criteriaAccount" />
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
						<thead>
							<tr class="bg-primary">
								<th class="text-nowrap"><spring:message code="ui.admin-user.account" /><!-- 使用者代號 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.user-name" /><!-- 使用者名稱 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.group" /><!-- 群組 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.department" /><!-- 部門 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.tel" /><!-- 電話 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.ext" /><!-- 分機 --></th>
								<th class="text-nowrap"><spring:message code="ui.admin-user.email" /><!-- 電子郵箱 --></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="entity">
							<tr>
								<td>${entity.account}</td>
								<td>${entity.userName}</td>
								<td>${entity.adminGroup.groupName}</td>
								<td>${entity.department}</td>
								<td>${entity.tel}</td>
								<td>${entity.ext}</td>
								<td>${entity.email}</td>
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
                $("#form1").attr("action", "./op_query");
                $('#form1').submit();
            });
		});
		
	</script>
</div>