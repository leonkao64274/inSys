<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../include/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.requestor-info.data.management" />
	<!-- 業者資料管理 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST"
		modelAttribute="requestorInfoCriteriaForm">

		<!-- 查詢條件 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.search-criteria" />
						<!--查詢條件-->
					</div>
					<div class="panel-body">
						<div class="row">
							<!-- 查詢條件(1): 業者代號 -->
							<div class="col-md-3">
								<spring:bind path="requestorId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.requestor-info.account" /> <!-- 商店代號 -->
										</label>
										<form:input path="requestorId" class="form-control" maxlength="35" />
										<form:errors path="requestorId" />
									</div>
								</spring:bind>
							</div>
							<!-- 查詢條件(2): 業者名稱 -->
							<div class="col-md-3">
								<spring:bind path="requestorName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.requestorName" /> <!-- 商店名稱 -->
										</label>
										<form:input path="requestorName" class="form-control" maxlength="40" />
										<form:errors path="requestorName" />
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
					<a href="#" id="btnQuery" class="btn btn-primary"><spring:message
							code="btn.query" /> <!--查詢--></a> 
					<a href="./add" id="btnAdd"
						class="btn btn-default"><spring:message code="btn.new" /> <!--新增--></a>
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
							<tr class="bg-primary" style=" width:100% ">
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.account" /> <!-- 商店代號 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.requestorName" /> <!-- 商店URL--></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enableVisa" /> <!-- Mcc --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enableMastercard" /> <!-- 商店名稱 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enableJCB" /> <!-- 商店國家代碼 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enableDiscover" /> <!-- 幣別代碼 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enableCup" /> <!-- 貨幣指數 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enable_ae" /> <!-- 商店密碼 --></th>
								<th class="text-nowrap"><spring:message
										code="ui.requestor-info.enable" /> <!-- 商店狀態 --></th>
								<th class="text-nowrap"><spring:message code="ui.operation" />
									<!-- 執行選項 --></th>
							</tr>
						</thead>
						<tbody>		
	                            <c:forEach items="${queryResult.content}" var="queryResult">
	                                <tr>
<!-- 	                                <td>1</td> -->
<!-- 	                                <td>2</td> -->
<!-- 	                                <td>3</td> -->
<!-- 	                                <td>1</td> -->
<!-- 	                                <td>1</td> -->
<!-- 	                                <td>1</td> -->
	                                
<!-- 	                                <td>1</td> -->
<!-- 	                                <td>1</td> -->
<!-- 	                                <td>1</td> -->
	                                	
                                    	<td><c:out value="${queryResult.requestorId}" /></td>  	
	                                    <td><c:out value="${queryResult.requestorName}" /></td>

	                                    <td><spring:message text="" code="requestor.${queryResult.enableVisa}"/></td>
										<td><spring:message text="" code="requestor.${queryResult.enableMastercard}"/></td>
										<td><spring:message text="" code="requestor.${queryResult.enableJCB}"/></td>
										<td><spring:message text="" code="requestor.${queryResult.enableDiscover}"/></td>
										<td><spring:message text="" code="requestor.${queryResult.enableCup}"/></td>
										<td><spring:message text="" code="requestor.${queryResult.enable_ae}"/></td>
	                             		<td><spring:message text="" code="requestor.${queryResult.enable}"/></td>
	                                    <td class="text-nowrap">
	                                        <a href="./edit?oid=<c:out value="${queryResult.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.update" /><!--修改--></a>
<!-- 	                                    </td>  -->
	
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
            <admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
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
	</script>
</div>