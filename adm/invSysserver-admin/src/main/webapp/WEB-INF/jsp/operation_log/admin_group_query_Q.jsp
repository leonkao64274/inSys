
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld" %>

<div class="row">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading"><spring:message text="" code="ui.search-criteria" /></div>
            <div class="panel-body">
                <div class="row">
                    
                    <%-- <c:if test="${operationLog.category == 'H'}"> --%>
                        <div class="col-md-4">
                            <h4><spring:message text="群組代號" code="ui.admin-group.id" /></h4>
                            <p>
                                <c:if test="${empty dataQuery['criteriaGroupId']}">
                                    N/A
                                </c:if>
                                <c:if test="${not empty dataQuery['criteriaGroupId']}">
                                    <c:out value="${dataQuery['criteriaGroupId']}" />
                                </c:if>        
                            </p>
                            <hr>
                        </div>
                    <%-- </c:if> --%>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="群組名稱" code="ui.admin-group.name" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['criteriaGroupName']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['criteriaGroupName']}">
                                ${dataQuery['criteriaGroupName']}
                            </c:if>
                        </p>
                        <hr>
                    </div>
                        
                </div>
            </div>
        </div>
    </div>
</div>