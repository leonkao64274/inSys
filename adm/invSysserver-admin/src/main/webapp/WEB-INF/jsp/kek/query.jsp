<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message text="" code="ui.kek"/><!--金鑰加密金鑰設定-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="kekCriteriaForm">
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
                            <!-- (2)金鑰識別碼 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaKeyAlias">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.kek.key-alias"/><!--金鑰基碼識別-->
									</label>
                                    <form:input class="form-control" path="criteriaKeyAlias"/>
									<form:errors path="criteriaKeyAlias" /><!-- 錯誤綁定 -->	
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (3)啟用狀態 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaStatus">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.kek.status"/><!--啟用狀態-->
									</label>
									<form:select class="form-control" path="criteriaStatus">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="1">
											<spring:message text="" code="ui.kek.status-enabled"/><!--啟用-->
										</form:option>
										<form:option value="0">
											<spring:message text="" code="ui.kek.status-disabled"/><!--未啟用-->
										</form:option>
									</form:select>
									<form:errors path="criteriaStatus" /><!-- 錯誤綁定 -->	
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
                    <a href="./add" id="btnAdd" class="btn btn-default"><spring:message text="" code="btn.new" /> <!--新增--></a>
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
                            		<spring:message text="" code="ui.kek.key-alias" /><!-- 金鑰基碼識別 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.kek.alg" /><!-- 演算法 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.kek.key-size" /><!-- 金鑰長度 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.kek.modify-time" /><!-- 異動時間 -->
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="" code="ui.operation" /><!-- 執行選項 -->
                            	</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${queryResult.content}" var="kmsInfo">
                            <tr>
                                <td><c:out value="${kmsInfo.keyAlias}" /></td>
                                <td><c:out value="${kmsInfo.algorithm}" /></td>
                                <td><c:out value="${kmsInfo.keySize}" /></td>
                                <td>
                                    <fmt:parseDate var="updateTime" pattern="yyyyMMddHHmmss" value="${kmsInfo.updateDate}${kmsInfo.updateTime}" />
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${updateTime}"/>
                                </td>
                                <td class="text-nowrap">
                                    <c:if test="${kmsInfo.status != 1}">
											<a href="./enable?oid=${kmsInfo.oid}" id="btnEnabled" class="btn btn-sm btn-default">
											<spring:message code="btn.setup" /><!--啟用--></a>
									</c:if>
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
            
         
        });
    </script>
</div>