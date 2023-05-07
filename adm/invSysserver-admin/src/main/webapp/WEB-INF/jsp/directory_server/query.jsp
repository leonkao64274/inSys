<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message code="ui.directory-server" />
    <!--目錄服務器連線設定-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST"
               modelAttribute="directoryServerCriteriaForm">
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
                            <!--                             (1)卡組織 -->
                            <div class="col-lg-3">
                                <spring:bind path="cardScheme">
                                    <div class="form-group">
                                        <label class="control-label"> <spring:message
                                                code="ui.card-scheme.name" /> <!--卡組織-->
                                        </label>
                                        <form:select class="form-control" path="cardScheme">
                                            <form:option value="">
                                                <spring:message code="ui.option-select" />
                                                <!-- 												請選擇 -->
                                            </form:option>
                                            <form:options items="${cardSchemeConfigModel}"
                                                          itemValue="codeId" itemLabel="codeDesc" /> 
                                        </form:select> 
                                    </div>
                                </spring:bind>
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
            </div>

        </div>
        <div style="height: 40px;"></div>
        <!--         查詢結果 -->
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr class="bg-primary">
                                <th class="text-nowrap">
                                    <spring:message code="ui.card-scheme.name" text="卡組織" />
                                </th>
                                <th class="text-nowrap">
                                    <spring:message code="ui.directory-server.main-url" text="主要網址" />
                                </th>

                                <th class="text-nowrap">
                                    <spring:message code="ui.directory-server.alternate-url" text="備援網址" />
                                </th>
                                <th class="text-nowrap">
                                    <spring:message code="ui.directory-server.message-version-number" text="訊息版本號碼" />
                                </th>
                                <!--
                                <th class="text-nowrap">
                                <spring:message code="ui.directory-server.number-retries" text="請求重試次數" />
                            </th>
                            <th class="text-nowrap">
                                <spring:message code="ui.directory-server.intervals" text="請求間隔時間(秒)" />
                            </th>
                            <th class="text-nowrap">
                                <spring:message code="ui.directory-server.readTimeout" text="連線逾時時間" />
                            </th>
                                -->
                                <th class="text-nowrap">
                                    <spring:message code="ui.operation" text="執行選項" /> 
                                </th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${queryResult.content}" var="queryResult">
                                <tr>
                                    <td><spring:message code="CARD_SCHEME.${queryResult.cardScheme}" /></td>
                                    <td><c:out value="${queryResult.areqUrl}" /></td> 
                                    <td><c:out value="${queryResult.backupAreqUrl}" /></td> 
                                    <td><c:out value="${queryResult.messageVersion}" /></td> 
                                    <td class="text-nowrap">
                                        <a href="./detail?oid=<c:out value="${queryResult.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.detail" /><!--明細--></a>

                                        <a href="./edit?oid=<c:out value="${queryResult.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.update" /><!--修改--></a>
                                        
                                        <a href="./delete?oid=<c:out value="${queryResult.oid}"/>" id="btnDelete_${queryResult.oid}" class="btn btn-sm btn-default">
                                            <spring:message code="btn.delete" /><!--刪除--></a> 
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
            <admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
        </c:if>
    </form:form>

</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnQuery").click(function () {
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });
        });
    </script>
</div>