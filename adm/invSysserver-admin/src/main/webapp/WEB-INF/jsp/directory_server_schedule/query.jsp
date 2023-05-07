<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>

<!-- page header -->
<div id="sitemesh-header">
	<h3 class="page-header"><spring:message code="ui.card-scheme-download-setting"/><!--卡段下載設定--></h3>
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="directoryServerScheduleCriteriaForm">
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
					<div class="panel-heading"><spring:message code="ui.search-criteria" /> <!--查詢條件--></div>
					<div class="panel-body">
						<div class="row"> 
							<!--(1)卡組織 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaCardScheme">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.card-scheme.name"/><!--卡組織-->
									</label>
									<form:select class="form-control" path="criteriaCardScheme">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${cardSchemeConfigModel}" 
 											itemValue="codeId" itemLabel="codeDesc" /> 
 									</form:select> 
 									<form:errors path="criteriaCardScheme" /><!-- 錯誤綁定 -->	 
                                </div>
                                </spring:bind>
                            </div>
						</div>
					</div>
				</div>
			</div>
		</div>

        <div class="row">
            <div class="col-lg-12">
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
								<th class="text-nowrap"><spring:message code="ui.card-scheme.name"/><!--卡組織	--></th>
								<th class="text-nowrap"><spring:message code="ui.serverUrl"/><!--服務器URL	--></th>
								<th class="text-nowrap"><spring:message code="ui.opRunningType"/><!--執行類型	--></th>
								<th class="text-nowrap"><spring:message code="ui.opDuration"/><!--執行週期	--></th>
								<th class="text-nowrap"><spring:message code="ui.operation"/><!--執行選項	--></th>				
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${queryResult.content}" var="result">
							<tr>
							    <td><!-- 卡組織 -->
           							<c:if test="${not empty result.cardScheme}">
                              		<c:forEach items="${cardSchemeConfigModel}" var="cardScheme">
                              			<c:if test="${cardScheme.codeId==result.cardScheme}">
                              				${cardScheme.codeDesc }<!-- 卡組織 -->
                              			</c:if>
                                    </c:forEach>
                                    </c:if>
							    </td>
							    <td><c:out value="${result.serverUrl}"/></td><!-- 服務器URL -->
							    <td><!-- 執行類型 -->	    
	                       			<c:if test="${'ALL'==result.opRunningType}">
	                       				<spring:message code="ui.opRunningType.ALL"/>
	                       			</c:if>
	                       			<c:if test="${'DIF'==result.opRunningType}">
	                       				<spring:message code="ui.opRunningType.DIF"/>
	                       			</c:if>
							    </td>
							    <td><c:out value="${result.opDuration}"/></td><!-- 執行週期 --> 
							     <td>
							        <a href="./detail?oid=<c:out value="${result.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.detail" /><!--明細--></a>
                                    <a href="./edit?oid=<c:out value="${result.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.update" /><!--修改--></a>
                                    <a href="./delete?oid=<c:out value="${result.oid}"/>" id="btnDelete_${result.oid}" class="btn btn-sm btn-default"><spring:message code="btn.delete" /><!--刪除--></a>
                                </td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
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
			$("#btnQuery").click(function() {
				$("#form1").attr("action", "./query");
				$('#form1').submit();
			});
		});
	</script>
</div>