<%-- 
    Document   : transaction_log_Q
    Created on :   /3/16, 下午 07:33:56
    Author     :   LeonKao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message text="" code="ui.search-criteria" />
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4">
                        <h4><spring:message text="金鑰基碼識別" code="ui.kek.key-alias" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['criteriaKeyAlias']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['criteriaKeyAlias']}">
                                <c:out value="${dataQuery['criteriaKeyAlias']}" />                                  
                            </c:if>
                        </p>
                        <hr>
                    </div>
					<div class="col-md-4">
                        <h4><spring:message text="啟用狀態" code="ui.kek.stat" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['criteriaStatus']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['criteriaStatus']}">
                               <c:out value="${dataQuery['criteriaCommonName']}" />
                               <spring:message text="${dataQuery['criteriaStatus']}" 
                                                code="ENABLED.${dataQuery['criteriaStatus']}" />
                            </c:if>
                        </p>
                        <hr>
                    </div>
				</div>
			</div>
		</div>
	</div>
</div>
