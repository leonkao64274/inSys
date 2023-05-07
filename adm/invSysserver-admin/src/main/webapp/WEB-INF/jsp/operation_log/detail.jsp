<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message text="" code="ui.operation-log"/><!-- 操作記錄查詢 -->
</div>


<!-- page content -->
<div id="sitemesh-content">
    <!-- 操作紀錄 -->
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <div class="panel-heading"><spring:message text="操作記錄" code="ui.operation-log.log"/></div>
                <div class="panel-body">
                    <div class="col-md-4">
                        <h4><spring:message text="操作時間" code="ui.operation-log.op-start-date" /></h4>
                        <p>
                            <c:if test="${empty operationLog.updateDate}">
                                N/A
                            </c:if>
                            <fmt:parseDate var="updateTime" pattern="yyyyMMddHHmmss" value="${operationLog.updateDate}${operationLog.updateTime}" />
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${updateTime}"/>
                        </p>
                        <hr>
                    </div>
<!--                     <div class="col-md-4"> -->
<%--                         <h4><spring:message text="發卡銀行" code="ui.operation-log.issuer" /></h4> --%>
<!--                         <p> -->
<%--                             <c:if test="${empty operationLog.issuer.issuerName}"> --%>
<!--                                 N/A -->
<%--                             </c:if> --%>
<%--                             <c:out value="${operationLog.issuer.issuerName}" /> --%>
<!--                         </p> -->
<!--                         <hr> -->
<!--                     </div> -->
                    <div class="col-md-4">
                        <h4><spring:message text="操作人員" code="ui.operation-log.account" /></h4>
                        <p>
                            <c:if test="${empty operationLog.account}">
                                N/A
                            </c:if>
                            <c:out value="${operationLog.account}" />(<c:out value="${operationLog.userName}" />)
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="來源" code="ui.operation-log.source" /></h4>
                        <p>
                            <c:if test="${empty operationLog.ipAddr}">
                                N/A
                            </c:if>
                            <c:out value="${operationLog.ipAddr}" />
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="功能名稱" code="ui.operation-log.function" /></h4>
                        <p>
                            <c:if test="${empty operationLog.accessId}">
                                N/A
                            </c:if>
                            <spring:message text="${operationLog.accessId}" code="${operationLog.i18nCode}"/>
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="存取檔案/物件名稱" code="ui.operation-log.target-object" /></h4>
                        <p>
                            <c:if test="${empty operationLog.targetObject}">
                                N/A
                            </c:if>
                            <c:out value="${operationLog.targetObject}" />
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="執行動作" code="ui.operation-log.action" /></h4>
                        <p>
                            <c:if test="${empty operationLog.action}">
                                N/A
                            </c:if>
                            <spring:message text="${operationLog.action}" code="ui.operation-log.action.${operationLog.action}"/>
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="執行結果" code="ui.operation-log.result" /></h4>
                        <p>
                            <c:if test="${empty operationLog.result}">
                                N/A
                            </c:if>
                            <spring:message text="${operationLog.result}" code="ui.operation-log.result.${operationLog.result}"/>
                        </p>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 查詢條件 -->
    <c:if test="${not empty dataQuery}">
        <jsp:include page="${operationLog.accessId}_Q.jsp"/>
    </c:if>
    
    <!-- 新增 -->
    <c:if test="${operationLog.action == 'A'}">
        <div class="row">
            <div class="col-md-12">
                <!-- 異動後 -->
                <div class="panel panel-yellow">
                    <div class="panel-heading"><spring:message text="異動後資料" code="ui.operation-log.data-after" /></div>
                    <div class="panel-body">
                        <c:if test="${empty dataAfter}">
                            <div class="row">
                                <div class="col-md-12"><spring:message text="查無資料" code="warn.data-not-found" /></div>
                            </div>
                        </c:if>
                        <c:if test="${not empty dataAfter}">
                            <jsp:include page="${operationLog.accessId}_A.jsp"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    
    <!-- 修改 -->
    <c:if test="${operationLog.action == 'E'}">
        <div class="row">
            <div class="col-md-6">
                <!-- 異動前 -->
                <div class="panel panel-green">
                    <div class="panel-heading"><spring:message text="異動前資料" code="ui.operation-log.data-before" /></div>
                    <div class="panel-body">
                        <c:if test="${empty dataBefore}">
                            <div class="row">
                                <div class="col-md-12"><spring:message text="查無資料" code="warn.data-not-found" /></div>
                            </div>
                        </c:if>
                        <c:if test="${not empty dataBefore}">
                            <jsp:include page="${operationLog.accessId}_B.jsp"/>
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <!-- 異動後 -->
                <div class="panel panel-yellow">
                    <div class="panel-heading"><spring:message text="異動後資料" code="ui.operation-log.data-after" /></div>
                    <div class="panel-body">
                        <c:if test="${empty dataAfter}">
                            <div class="row">
                                <div class="col-md-12"><spring:message text="查無資料" code="warn.data-not-found" /></div>
                            </div>
                        </c:if>
                        <c:if test="${not empty dataAfter}">
                            <jsp:include page="${operationLog.accessId}_A.jsp"/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    
    <!-- 刪除 -->
    <c:if test="${operationLog.action == 'D' || operationLog.action == 'O'}">
        <div class="row">
            <div class="col-md-12">
                <!-- 異動前 -->
                <div class="panel panel-green">
                    <div class="panel-heading"><spring:message text="異動前資料" code="ui.operation-log.data-before" /></div>
                    <div class="panel-body">
                        <c:if test="${empty dataBefore}">
                            <div class="row">
                                <div class="col-md-12"><spring:message text="查無資料" code="warn.data-not-found" /></div>
                            </div>
                        </c:if>
                        <c:if test="${not empty dataBefore}">
                            <jsp:include page="${operationLog.accessId}_B.jsp"/>
                        </c:if>
                    </div>
                </div>
            </div>

        </div>
    </c:if>

    <!-- 查詢結果 -->
    <div class="row">
        <div class="col-md-12">
            <div class="text-center">
                <a href="./query" class="btn btn-default"><spring:message text="" code="btn.back-history"/><!--回上一頁--></a>
            </div>
        </div>
    </div>
    
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">

</div>