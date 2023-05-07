<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message text="卡 BIN 範圍" code="ui.card-range-data"/>
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="cardRangeDataCriteriaForm">
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
                            <!-- (1)卡組織 -->
                            <div class="col-lg-3">
                                <spring:bind path="criteriaCardScheme">
                                    <div class="form-group">
                                        <label class="control-label"> 
                                            <spring:message text="卡組織" code="ui.card-range-data.cardScheme" />
                                        </label>
                                        <form:select class="form-control" path="criteriaCardScheme">
                                            <form:option value="">
                                                <spring:message text="請選擇" code="ui.option-select" />
                                            </form:option>
                                            <form:options items="${cardSchemeConfigModel}" itemValue="codeId" itemLabel="codeDesc" /> 
                                        </form:select> 
                                    </div>
                                </spring:bind>
                            </div>
                            
                            <!-- (2)卡號 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaAcctNumber">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="卡號" code="ui.card-range-data.acctNumber"/>
									</label>
                                    <form:input class="form-control" path="criteriaAcctNumber"/>
									<form:errors path="criteriaAcctNumber" /><!-- 錯誤綁定 -->	
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
                            		<spring:message text="卡組織" code="ui.card-range-data.cardScheme" />
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="卡 BIN 範圍" code="ui.card-range-data.cardRange" />
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="invSys Method URL" code="ui.card-range-data.invSysMethodURL" />
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="ACS Protocol Version" code="ui.card-range-data.acsProtocolVersion" />
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="DS Protocol Version" code="ui.card-range-data.dsProtocolVersion" />
                            	</th>
                            	<th class="text-nowrap">
                            		<spring:message text="ACS Information Ind" code="ui.card-range-data.acsInfoInd" />
                            	</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${queryResult.content}" var="cardRangeData">
                            <tr>
                                <td><spring:message code="CARD_SCHEME.${cardRangeData.cardScheme}" /></td>
                                <td><c:out value="${cardRangeData.startRange}" />~<c:out value="${cardRangeData.endRange}" /></td>
                                <td><c:out value="${cardRangeData.invSysMethodURL}" /></td>
                                <td><c:out value="${cardRangeData.acsStartProtocolVersion}" />~<c:out value="${cardRangeData.acsEndProtocolVersion}" /></td>
                                <td><c:out value="${cardRangeData.dsStartProtocolVersion}" />~<c:out value="${cardRangeData.dsEndProtocolVersion}" /></td>
                                <td><c:out value="${cardRangeData.acsInfoInd}" /></td>
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