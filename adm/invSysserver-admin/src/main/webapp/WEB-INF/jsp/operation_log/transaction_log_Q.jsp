<%-- 
    Document   : transaction_log_Q
    Created on :   /3/16, 下午 07:33:56
    Author     :   LeonKao
--%>

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
                    <div class="col-md-4">
                        <h4><spring:message text="發卡銀行" code="ui.issuer.name" /></h4>
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
                        
                    <div class="col-md-4">
                        <h4><spring:message text="開始時間" code="ui.trans.log.start.date" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['startDate']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['startDate']}">
                                <c:out value="${dataQuery['startDate']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="截止時間" code="ui.trans.log.end.date" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['endDate']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['endDate']}">
                                <c:out value="${dataQuery['endDate']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
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
                        <h4><spring:message text="訊息版本" code="message.version" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['messageVersion']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['messageVersion']}">
                                <c:out value="${dataQuery['messageVersion']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="裝置通道" code="device.channel" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['deviceChannel']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['deviceChannel']}">
                                <spring:message text="${dataQuery['deviceChannel']}" 
                                                code="ui.trans.log.deviceChannel.${dataQuery['deviceChannel']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4>ACS <spring:message text="交易序号" code="acs.trans.id" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['acsTransID']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['acsTransID']}">
                                <c:out value="${dataQuery['acsTransID']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4>invSys Server <spring:message text="交易序号" code="acs.trans.id" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['invSysServerTransID']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['invSysServerTransID']}">
                                <c:out value="${dataQuery['invSysServerTransID']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>

                    <div class="col-md-4">
                        <h4><spring:message text="驗證結果" code="ui.trans.log.trans.status" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['transStatus']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['transStatus']}">
                                <spring:message text="${dataQuery['transStatus']}" 
                                                code="ui.trans.log.transStatus.${dataQuery['transStatus']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="身分證號" code="ui.trans.log.acct.id" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['acctId']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['acctId']}">
                                <c:out value="${dataQuery['acctId']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="卡號前6碼" code="ui.trans.log.acct.number.prefix" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['acctNumberPrefix']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['acctNumberPrefix']}">
                                <c:out value="${dataQuery['acctNumberPrefix']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="卡號後4碼" code="ui.trans.log.acct.number.postfix" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['acctNumberPostfix']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['acctNumberPostfix']}">
                                <c:out value="${dataQuery['acctNumberPostfix']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                    <div class="col-md-4">
                        <h4><spring:message text="特店代號" code="ui.trans.log.acquirer.merchant.id" /></h4>
                        <p>
                            <c:if test="${empty dataQuery['acquirerMerchantID']}">
                                N/A
                            </c:if>
                            <c:if test="${not empty dataQuery['acquirerMerchantID']}">
                                <c:out value="${dataQuery['acquirerMerchantID']}" />
                            </c:if>        
                        </p>
                        <hr>
                    </div>
                        
                </div>
            </div>
        </div>
    </div>
</div>
