<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.invSys-trans" />
<!-- 	交易記錄查詢 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form" action="#" method="POST"
		modelAttribute="invSysTransForm">
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
							
							<!-- (1)商戶代碼 -->
							<div class="col-md-3">
								<spring:bind path="acquirerMerchantID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.invSys-trans.merchant-code" /><!-- 商戶代碼 -->
										</label>
										<form:input path="acquirerMerchantID" class="form-control" maxlength="20" />
										<form:errors path="acquirerMerchantID" />
									</div>
								</spring:bind>
							</div>
							
							<!--                             (2)交易狀態 -->
							<div class="col-lg-3">
								<spring:bind path="transStatus">
									<div class="form-group">
										<label class="control-label"> 
											<spring:message code="ui.invSys-trans.trading-status" /> <!--交易狀態-->
										</label>
										<form:select class="form-control" path="transStatus">
											<form:option value="">
												<spring:message code="ui.option-select" />
<!-- 												請選擇 -->
											</form:option>
											<option value="Y">成功</option>
											<option value="N">失敗</option>
 										</form:select> 
									</div>
								</spring:bind>
							</div>
							
							<!-- (3)流水號 -->
							<div class="col-md-3">
								<spring:bind path="invSysRequestorID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											 <spring:message code="ui.invSys-trans.serial-number" /><!-- 流水號  -->
										</label>
										<form:input path="invSysRequestorID" class="form-control" maxlength="20" />
										<form:errors path="invSysRequestorID" />
									</div>
								</spring:bind>
							</div>
							
							<!-- (4)ECI -->
							<div class="col-md-3">
								<spring:bind path="eci">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											ECI <!-- ECI  -->
										</label>
										<form:input path="eci" class="form-control" maxlength="20" />
										<form:errors path="eci" />
									</div>
								</spring:bind>
							</div>
							
							<!--  (5)開始時間-->
							<div class="col-lg-3">
								<spring:bind path="createDateStart">
									<div class="form-group">
										<label class="control-label">
												<spring:message code="ui.invSys-trans.starting-time" /> <!--開始時間--> </label>
										<div class='input-group date' id="start_date1">
											<form:input path="createDateStart" class="form-control" id="cds" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</spring:bind>
							</div>
							
							<!--  (5)截止時間-->
							<div class="col-lg-3">
								<spring:bind path="createDateEnd">
									<div class="form-group">
										<label class="control-label">
												<spring:message code="ui.invSys-trans.deadline" /> <!--截止時間--> </label>
										<div class='input-group date' id="start_date2">
											<form:input path="createDateEnd" class="form-control" id="cde" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>
										</div>
									</div>
								</spring:bind>
							</div>	
							
							<!-- (7)交易金額 -->
							<div class="col-md-6">
									<div class="form-group ${status.error ? 'has-error' : ''}">
								<spring:bind path="purchaseAmount1">
										<label class="col-sm-12">
											<spring:message code="ui.invSys-trans.amount-transaction" /><!-- 交易金額  -->
										</label>
										<div class="col-md-4">
										<form:input path="purchaseAmount1" class="form-control" maxlength="20" />
										</div>
										<form:errors path="purchaseAmount1" />
								</spring:bind>
								<spring:bind path="purchaseAmount2">
										<div class="col-md-4">
										<form:input path="purchaseAmount2" class="form-control" maxlength="20" />
										</div>
										<form:errors path="purchaseAmount2" />
								</spring:bind>
									</div>
								
							</div>						
						</div>
					</div>
				</div>
			</div>
			<!--         </div> -->

			<div class="row">
				<div class="col-lg-12">
					<div class="pull-right">
						<a href="#" id="btnQuery" class="btn btn-primary"><spring:message code="btn.query" /> <!--查詢--></a>						
					</div>
				</div>
			</div>

			<div style="height: 40px;"></div>

		</div>
		<!--         查詢結果 -->
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr class="bg-primary">								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.verify-service-time" />
								<!-- 驗證服務時間 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.system-tracking" />
								<!-- 系統跟蹤號 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.amount-transaction" />
								<!-- 交易金額 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.currency.code" />
								<!-- 貨幣代碼 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.merchant-code" />
								<!-- 商店代碼 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.trading-status" />
								<!-- 交易狀態 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.invSys-trans.response.code" />
								<!-- 響應碼 --></th>
								
								<th class="text-nowrap">
								<spring:message code="ui.operation" /> 
<!-- 								執行選項 -->
								<!-- 執行選項 --></th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="queryResult">
								<tr>
 									<td><c:out value="${queryResult.createDate}" /><c:out value="${queryResult.createTime}" /></td> 
 									<td><c:out value="${queryResult.invSysRequestorID}" /></td>
 									<td><c:out value="${queryResult.purchaseAmount}" /></td> 
 									<td><c:out value="${queryResult.purchaseCurrency}" /></td> 
 									<td><c:out value="${queryResult.acquirerMerchantID}" /></td> 
									<td><c:out value="${queryResult.transStatus}" /></td> 
  									<td><c:out value="${queryResult.errorCode}" /></td> 
 									
 									<td>
									<a href="./detail?oid=<c:out value="${queryResult.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.detail" /><!--明細--></a>
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
				$("#form").attr("action", "./query");
				$('#form').submit();
			});
		});
		$(function() {
			$('#start_date1').datetimepicker({
				format : 'YYYY-MM-DD 00:00:00',
				defaultDate: new Date().setDate(new Date().getDate()),
				maxDate : new Date().setDate(new Date().getDate())
			});
		});
		$(function() {
			$('#start_date2').datetimepicker({
				format : 'YYYY-MM-DD 23:59:59',
				minDate : new Date().setDate(new Date().getDate()),
				maxDate : new Date().setFullYear(new Date().getFullYear() + 10),
			});
		});
		
		
		
		
	</script>
</div>