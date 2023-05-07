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
                        <h4><spring:message text="卡組織" code="ui.card-scheme.name" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['criteriaCardScheme']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['criteriaCardScheme']}">
                                <spring:message text="${dataQuery['criteriaCardScheme']}" 
                                                code="CARD_SCHEME.${dataQuery['criteriaCardScheme']}" />
                            </c:if>
                        </p>
                        <hr>
                    </div>
					<div class="col-md-4">
                        <h4><spring:message text="憑證狀態" code="ui.issuer-cert.credential-status" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['status']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['status']}">
                               <spring:message text="" code="ENABLED.${dataQuery['status']}" />
                            </c:if>
                        </p>
                        <hr>
                    </div>
                    <div class="col-md-4">
                        <h4><spring:message text="有效日期" code="ui.issuer-cert.validity-period" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['notBefore']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['notBefore']}">
                               <c:out value=" ${dataQuery['notBefore']}" />
                            </c:if>
                        </p>
                        <hr>
                    </div>
				</div>
			</div>
		</div>
	</div>
</div>
