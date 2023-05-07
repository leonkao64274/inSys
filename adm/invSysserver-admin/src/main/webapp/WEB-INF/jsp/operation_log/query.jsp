<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message text="" code="ui.operation-log"/><!--操作記錄查詢-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="operationLogCriteriaForm">
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
        <!-- 2.成功訊息區 -->
        <c:if test="${not empty successMsg}">
            <div class="row">
                <div class="col-md-12">
                    <div class="alert alert-success">
                        <p class="fa fa-warning">
                            <c:out value="${successMsg}"/>
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
                        <spring:message text="" code="ui.search-criteria" /> <!--查詢條件-->
                    </div>
                    <div class="panel-body">
                        <div class="row">
                        	<!-- (1)發卡銀行 -->
<!--                             <div class="col-md-3"> -->
<%--                                 <spring:bind path="criteriaIssuerOid"> --%>
<%--                                 <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 									<label class="control-label"> -->
<%-- 										<spring:message text="" code="ui.issuer.name"/><!--發卡銀行--> --%>
<!-- 									</label> -->
<%-- 									<form:select class="form-control" path="criteriaIssuerOid"> --%>
<%-- 										<form:option value=""> --%>
<%-- 											<spring:message text="" code="ui.option-select"/><!--請選擇--> --%>
<%-- 										</form:option> --%>
<%-- 										<form:options items="${issuerConfigModel}"  --%>
<%-- 												itemValue="oid" itemLabel="issuerName" /><!--發卡銀行訊息--> --%>
<%-- 									</form:select> --%>
<%-- 									<form:errors path="criteriaIssuerOid" /><!-- 錯誤綁定 --> --%>
<!-- 								</div> -->
<%-- 								</spring:bind> --%>
<!--                             </div> -->
                            <!-- (2)操作日期起日 -->
							<div class="col-md-3">
								<spring:bind path="criteriaStartDate">
									<div class="form-group">
										<label class="control-label"> <spring:message text=""
												code="ui.operation-log.op-start-date" /> <!--操作日期起日-->
										</label>
										<div class='input-group date' id="start_date">
											<form:input path="criteriaStartDate" class="form-control" id="cds" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span> 
										</div>
                                        <form:errors path="criteriaStartDate" /><!-- 錯誤綁定 -->	
									</div>
								</spring:bind>
							</div>
                            <!-- (3)操作日期迄日 -->
							<div class="col-md-3">
								<spring:bind path="criteriaEndDate">
									<div class="form-group">
										<label class="control-label"> <spring:message text=""
												code="ui.operation-log.op-end-date" /> <!--操作日期迄日-->
										</label>
										<div class='input-group date' id="end_date">
											<form:input path="criteriaEndDate" class="form-control" id="cde" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span> 
                                            
										</div>
                                        <form:errors path="criteriaEndDate" /><!-- 錯誤綁定 -->
									</div>
								</spring:bind>
							</div>
                            <!-- (4)操作人員 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaAccount">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.operation-log.account"/><!--操作人員-->
									</label>
                                    <form:input class="form-control" path="criteriaAccount"/>
									<form:errors path="criteriaAccount" /><!-- 錯誤綁定 -->	
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (5)功能名稱 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaAccessId">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.operation-log.function"/><!--功能名稱-->
									</label>
									<form:select class="form-control" path="criteriaAccessId">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${menuConfigModel}" 
												itemValue="accessId" itemLabel="fnctName" /><!--發卡銀行訊息-->
									</form:select>
									<form:errors path="criteriaAccessId" /><!-- 錯誤綁定 -->	
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (6)執行動作 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaAction">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.operation-log.action"/><!--執行動作-->
									</label>
									<form:select class="form-control" path="criteriaAction">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="Q">
											<spring:message text="" code="ui.operation-log.action.Q"/><!--查詢-->
										</form:option>
										<form:option value="A">
											<spring:message text="" code="ui.operation-log.action.A"/><!--新增-->
										</form:option>
										<form:option value="E">
											<spring:message text="" code="ui.operation-log.action.E"/><!--修改-->
										</form:option>
										<form:option value="D">
											<spring:message text="" code="ui.operation-log.action.D"/><!--刪除-->
										</form:option>
										<form:option value="R">
											<spring:message text="" code="ui.operation-log.action.R"/><!--報表-->
										</form:option>
										<form:option value="O">
											<spring:message text="" code="ui.operation-log.action.O"/><!--匯出-->
										</form:option>
										<form:option value="P">
											<spring:message text="" code="ui.operation-log.action.P"/><!--列印-->
										</form:option>
									</form:select>
									<form:errors path="criteriaAction" /><!-- 錯誤綁定 -->	
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (7)執行結果 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaResult">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.operation-log.result"/><!--執行動作-->
									</label>
									<form:select class="form-control" path="criteriaResult">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="0">
											<spring:message text="" code="ui.operation-log.result.0"/><!--成功-->
										</form:option>
										<form:option value="1">
											<spring:message text="" code="ui.operation-log.result.1"/><!--失敗-->
										</form:option>
									</form:select>
									<form:errors path="criteriaResult" /><!-- 錯誤綁定 -->	
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
                    <a href="#" id="btnQuery" class="btn btn-primary"><spring:message text="" code="btn.query" /> <!--查詢--></a>
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
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.date-time" /><!-- 操作時間 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.account" /><!-- 操作人員 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.function" /><!-- 功能名稱 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.source" /><!-- 來源 -->
                            	</th>
                                
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.action" /><!-- 執行動作 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation-log.result" /><!-- 結果 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation" /><!-- 執行選項 -->
                            	</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${queryResult.content}" var="operationLog">
                            <tr>
                                <td>
                                    <fmt:parseDate var="updateTime" pattern="yyyyMMddHHmmss" value="${operationLog.updateDate}${operationLog.updateTime}" />
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${updateTime}"/>
                                </td>
                                <td>
<%--                                     <c:if test="${not empty operationLog.issuer.issuerName}"> --%>
<%--                                         <c:out value="${operationLog.issuer.issuerName}" /><br> --%>
<%--                                     </c:if> --%>
                                    <c:out value="${operationLog.account}" />(<c:out value="${operationLog.userName}" />)
                                </td>
                                <td><spring:message text="${operationLog.accessId}" code="${operationLog.i18nCode}"/></td>
                                <td><c:out value="${operationLog.ipAddr}" /></td>
                                <td><spring:message text="${operationLog.action}" code="ui.operation-log.action.${operationLog.action}"/></td>
                                <td><spring:message text="${operationLog.result}" code="ui.operation-log.result.${operationLog.result}"/></td>
                                <td class="text-nowrap">
                                    <a href="./detail?oid=${operationLog.oid}" id="btnDetail" class="btn btn-sm btn-default">
                                    <spring:message code="btn.difference" /><!--異動明細--></a>
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
        $(document).ready(function () {
            $("#btnQuery").click(function(){
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });
            
			$('#start_date').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : "${locale}",
				defaultDate : new Date().setDate(new Date().getDate()),
			});

			$('#end_date').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : "${locale}",
				defaultDate : new Date().setDate(new Date().getDate()),
				maxDate : new Date().setDate(new Date().getDate())
			});
            
            $('[data-toggle="tooltip"]').tooltip();
         
        });
    </script>
</div>