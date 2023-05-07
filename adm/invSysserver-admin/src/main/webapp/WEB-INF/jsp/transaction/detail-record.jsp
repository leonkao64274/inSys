<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message text="" code="trans.log.query" /><!--交易紀錄查詢 -->
</div>

<!-- page content -->
<div id="sitemesh-content">

    <!-- 錯誤訊息區 -->
    <c:if test="${not empty errors}">
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-danger">
                    <p class="fa fa-warning">
                        <c:out value="${errors}" />
                    </p>
                </div>
            </div>
        </div>
    </c:if>

    <!-- 查詢結果 -->


    <div id="sitemesh-content">
        <!-- 查詢結果 -->
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-info">
                <!-- 交易记录查询-明细 -->
                <div class="panel-heading">
                    <spring:message code="ui.trans.log-detail" text="" />
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <!-- Nav tabs -->
                    <!-- 交易訊息  報文訊息 tabs-->
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#transaction" data-toggle="tab"><spring:message
                                code="ui.trans.log-trading.message" text="" /></a></li>
                    </ul>

                    <!-- Tab panes -->
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> invSys Server <spring:message code="acs.trans.id" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysTransID}" />
										<c:if test="${empty form.invSysTransID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易狀態" code="ui.trans.log.basic.transactionStatus" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.transStatus}"
											code="ui.trans.log.transStatusMessage.${form.transStatus}" />
										<c:if test="${empty form.transStatus}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"><spring:message text="ECI" code="eci" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.eci}" />
										<c:if test="${empty form.eci}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message
											code="ui.trans.log.acct.number.prefix" />
									</H4>
									<div>
                                        <td><c:out value="${form.acctNumberPrefix}" /></td>
										<c:if test="${empty form.acctNumberPrefix}">N/A</c:if>
									</div>
									<hr>
								</div>
                                <div class="col-md-4">
                                    <H4 class="control-label"> <spring:message
                                            code="ui.trans.log.acct.number.postfix" />
                                    </H4>
                                    <div>
                                        <td><c:out value="${form.acctNumberPostfix}" /></td>
                                        <c:if test="${empty form.acctNumberPostfix}">N/A</c:if>
                                    </div>
                                    <hr>
                                </div>

							 	<div class="col-md-4">
									<H4 class="control-label"> <spring:message code="ui.card-scheme.name" /></H4>
									<div style="word-break: break-all">
										<spring:message text="${form.cardScheme}" code="CARD_SCHEME.${form.cardScheme}" />
										<c:if test="${empty form.cardScheme}">N/A</c:if>
									</div>
									<hr>
								</div>


							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤原因" code="ui.trans.log.basic.errorCode" /> </H4>
									<div style="word-break: break-all">
										<c:if test="${empty form.veResultCode && empty form.paResultCode}">N/A</c:if>
										<c:if test="${!empty form.veResultCode && form.veResultCode!=1100}">veCode=${form.veResultCode}</c:if>
										<c:if test="${!empty form.veResultCode && form.veResultCode==1100 &&!empty form.paResultCode && form.paResultCode==2100}">N/A</c:if>
										<c:if test="${!empty form.paResultCode && form.paResultCode!=2100}">paCode=${form.paResultCode}</c:if>
									</div>
									<hr>
							</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤平台代碼" code="ui.trans.log.details.errorComponent" /> </H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤摘要" code="ui.trans.log.details.errorDescription" /> </H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤明細" code="ui.trans.log.details.errorDetail" /> </H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>

							<div class="col-md-4 ">
								<H4 class="control-label"><spring:message text="錯誤類別" code="ui.trans.log.details.errorMessageType" /></H4>
								<div style="word-break: break-all">
										<c:out value="N/A" />
								</div>
								<hr>
							</div>
							<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易管道" code="ui.trans.log.basic.deviceChannel" /> </H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
							<div class="col-md-4">
									<H4 class="control-label"><spring:message text="MessageCategory" code="ui.trans.messageCategory" /></H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>
							<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="報文版本" code="ui.trans.log.details.messageVersionNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.version}" />
										<c:if test="${empty form.version}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="結果訊息狀態" code="ui.trans.log.details.resultsMessageStatus" /> </H4>
									<div style="word-break: break-all">
										<c:out value="N/A" />
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
                                <div class="col-md-4">
									<H4 class="control-label"> <spring:message text="認證方式" code="ui.trans.log.basic.authenticationMethod" /> </H4>
									<div style="word-break: break-all">
										<c:out value="02 = SMS OTP" />
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="授權類別" code="ui.trans.log.details.authenticationType" /> </H4>
									<div style="word-break: break-all">
										<c:out value="02 = Dynamic" />
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="授權碼" code="ui.trans.log.details.authenticationValue" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.cavv}" />
										<c:if test="${empty form.cavv}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
                            <div class="row">
                                <div class="col-md-4">
                                    <H4 class="control-label"> <spring:message code="ui.trans.log-trading.date.time" /></H4>
                                    <div style="word-break: break-all">
                                        <fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss"
                                                       value="${form.createDate}${form.createTime}" /> <fmt:formatDate
                                            pattern="yyyy-MM-dd HH:mm:ss" value="${createTime}" />
                                        <c:if test="${empty createTime}">N/A</c:if>
                                    </div>
                                    <hr>
                                </div>
                            </div>
						</div>
					</div>
				</div>
                    
                    
                </div>

            </div>

            <div class="text-center">
                <a href="./query" class="btn btn-default"><spring:message text="" code="btn.back-history" /><!-- 回上一頁 --></a>
            </div>
        </div>
    </div>

    </div>
    <!-- /#page-wrapper -->

</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
    <script type="text/javascript">
        $(function() {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</div>
