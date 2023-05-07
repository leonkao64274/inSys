<%@	page contentType="text/html" pageEncoding="UTF-8"%>
<%@	include file="../include/common.jsp"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.admin-group.management" /><!-- 群組管理 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="entity">
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
		<!-- 資料明細區 -->
		<div class="row">
			<div class="col-md-12">
				<!-- (1)主表。 -->
				<div class="table-responsive">
					<table class="table table-bordered">
						<tbody>
							<!-- (1)群組代號 -->
							<tr><td class="bg-primary col-md-6">
									<spring:message code="ui.admin-group.id" />
								</td>
								<td class="col-md-6">${entity.groupId}</td>
							</tr>
							<!-- (2)群組名稱 -->
							<tr><td class="bg-primary col-md-6">
									<spring:message code="ui.admin-group.name" />
								</td>
								<td class="col-md-6">${entity.groupName}</td>
							</tr>
							<!-- (3)說明 -->
							<tr><td class="bg-primary col-md-6">
									<spring:message code="ui.admin-group.description" /><!-- 說明 -->
								</td>
								<td class="col-md-6">${entity.description}</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<!-- (2)從表 -->
				<div class="row">
					<c:forEach items="${key}" var="key" varStatus="index">
						<div class="col-md-12">
							<div class="panel panel-info">
								<div class="panel-heading">
									<spring:message code="${key}" text="" />
								</div>
								<div class="panel-body">
									<div class="form-group">
										<c:forEach items="${permissionMap.get(key)}" var="i18nCode">
											<div class="col-md-3">
												<span style="font-weight: bold"> <spring:message
														code="${i18nCode}" text="" /></span>
											</div>
										</c:forEach>
									</div>

								</div>
							</div>
						</div>
					</c:forEach>

				</div>
				<!-- (3)空白區 -->
				<div class="row">&nbsp;</div>
				<!-- (4)按鍵區 -->
				<div class="row">
					<div class="col-md-12 text-center">
						<div class="form-group">
							<label class="control-label"></label> 
							<a href="./op_query" class="btn btn-default"><spring:message code="btn.back-history" /></a><!-- 回上一頁 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>
</div>