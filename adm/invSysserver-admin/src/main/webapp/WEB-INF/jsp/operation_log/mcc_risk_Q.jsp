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

					<%-- <c:if test="${operationLog.category == 'H'}"> --%>
						<div class="col-md-4">
							<h4>
								<spring:message text="發卡銀行" code="ui.issuer.name" />
							</h4>
							<p>
								<c:if test="${empty dataQuery['criteriaIssuerOid']}">
                                    N/A
                                </c:if>
								<c:if test="${not empty dataQuery['criteriaIssuerOid']}">
									<c:out value="${dataQuery['criteriaIssuerOid']}" />
								</c:if>
							</p>
							<hr>
						</div>
					<%-- </c:if> --%>

					<div class="col-md-4">
						<h4>
							<spring:message text="商戶類別代碼" code="ui.mcc-risk.mcc" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['criteriaMcc']}">
                                N/A
                            </c:if>
							<c:if test="${not empty dataQuery['criteriaMcc']}">
								<c:out value="${dataQuery['criteriaMcc']}" />	
							</c:if>
						</p>
						<hr>
					</div>
					<div class="col-md-4">
                        <h4><spring:message text="風險等級" code="ui.mcc-risk.risk-level" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['criteriaRiskLevel']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['criteriaRiskLevel']}">
                                <spring:message text="${dataQuery['criteriaRiskLevel']}" 
                                                code="RISK_LEVEL.${dataQuery['criteriaRiskLevel']}" />
                            </c:if>
                        </p>
                        <hr>
                    </div>
				</div>
			</div>
		</div>
	</div>
</div>
