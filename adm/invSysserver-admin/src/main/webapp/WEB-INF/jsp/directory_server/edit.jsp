<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.directory-server" />
	<!--目錄服務器連線設定-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="form">
		<div class="row">
			<div class="col-lg-12">
				<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.directory-server.edit" text="" />
					</div>
					<div class="panel-body">
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
						<!-- 資料編輯區 -->
						<!-- 資料編輯區 -->
						<div class="row">
							<div class="row">
								<div class="col-md-12">
									<!-- (1)卡組織 -->
									<div class="col-md-6">
										<spring:bind path="cardScheme">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.card-scheme.name" />${req}<!--卡組織-->
												</label>
												<form:select class="form-control" path="cardScheme">
													<form:option value="">
														<spring:message code="ui.option-select" />
														<!--請選擇-->
													</form:option>
													<form:options items="${cardSchemeConfigModel}"
														itemValue="codeId" itemLabel="codeDesc" />
												</form:select>
												<form:errors path="cardScheme" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- (2)主要網址-->
									<div class="col-md-6">
										<spring:bind path="areqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.main-url" />${req}<!--主要網址*-->
												</label>
												<form:input class="form-control" path="areqUrl" />
												<form:errors path="areqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<!-- (3)備援網址-->
									<div class="col-md-6">
										<spring:bind path="backupAreqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.alternate-url" />${req}<!--備援網址-->
												</label>
												<form:input class="form-control" path="backupAreqUrl" />
												<form:errors path="backupAreqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<div class="col-md-6">
										<spring:bind path="preqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.preqUrl" text="PReq主要网址" />${req}
												</label>
												<form:input class="form-control" path="preqUrl" />
												<form:errors path="preqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>

								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<spring:bind path="backupPreqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.backPreqUrl" text="PReq备用网址" />${req}
												</label>
												<form:input class="form-control" path="backupPreqUrl" />
												<form:errors path="backupPreqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- RReq主要網址-->
									<div class="col-md-6">
										<spring:bind path="rreqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.rreqUrl" text="RReq主要网址" />${req}
												</label>
												<form:input class="form-control" path="rreqUrl" />
												<form:errors path="rreqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>

								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<!-- (3)備援網址-->
									<div class="col-md-6">
										<spring:bind path="backupRreqUrl">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.backRreqUrl" />${req}<!--備援網址-->
												</label>
												<form:input class="form-control" path="backupRreqUrl" />
												<form:errors path="backupRreqUrl" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<div class="col-md-6">
										<spring:bind path="messageVersion">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.message-version-number" />${req}<!--訊息版本號碼-->
												</label>
												<form:input class="form-control" path="messageVersion"
													maxlength="5" />
												<form:errors path="messageVersion" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-12">
									<!-- 請求重試次數-->
									<div class="col-md-6">
										<spring:bind path="retryLimits">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.number-retries" />${req}<!--請求重試次數-->
												</label>
												<form:input class="form-control" path="retryLimits"
													maxlength="11" />
												<form:errors path="retryLimits" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- (6)請求間隔時間(秒)-->
									<div class="col-md-6">
										<spring:bind path="retryInterval">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.intervals" />${req}<!--請求間隔時間(秒)-->
												</label>
												<form:input class="form-control" path="retryInterval"
													maxlength="11" />
												<form:errors path="retryInterval" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<!-- 連線逾時時間(秒)-->
									<div class="col-md-6">
										<spring:bind path="readTimeout">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.readTimeout" />${req}<!--連線逾時時間(秒)-->
												</label>
												<form:input class="form-control" path="readTimeout" />
												<form:errors path="readTimeout" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- invSys Server Operator ID-->
									<div class="col-md-6">
										<spring:bind path="invSysServerOperatorID">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.invSyssvr-operator-id" />${req}<!--invSys Server Operator ID-->
												</label>
												<form:input class="form-control" path="invSysServerOperatorID" />
												<form:errors path="invSysServerOperatorID" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
								</div>
							</div>
                            <div class="row">
                                <div class="col-md-12">
									<!-- invSys Server Reference Number-->
									<div class="col-md-6">
										<spring:bind path="invSysServerRefNumber">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
														code="ui.directory-server.invSyssvr-ref-num" />${req}<!--invSys Server Reference Number-->
												</label>
												<form:input class="form-control" path="invSysServerRefNumber" />
												<form:errors path="invSysServerRefNumber" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- 是否啟用代理服務器 -->
									<div class="col-md-6">
										<spring:bind path="proxyEnabled">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
                                                        text="是否啟用代理服務器"
														code="ui.directory-server.proxy-enabled" />${req}<!--是否啟用代理服務器-->
												</label>
                                                <div>
                                                    <label class="radio-inline"><form:radiobutton path="proxyEnabled" value="1"/><spring:message text="啟用" code="ui.directory-server.enabled"/><!-- 啟用 --></label>
                                                    <label class="radio-inline"><form:radiobutton path="proxyEnabled" value="0"/><spring:message text="不啟用" code="ui.directory-server.disabled"/><!-- 不啟用 --></label>
                                                </div>
												<form:errors path="proxyEnabled" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
									<!-- 代理伺服器 -->
									<div class="col-md-6">
										<spring:bind path="proxyHost">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
                                                        text="代理伺服器"
														code="ui.directory-server.proxy-host" /><!--代理伺服器-->
												</label>
                                                <form:input class="form-control" path="proxyHost" maxlength="50"/>
												<form:errors path="proxyHost" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
									<!-- 代理伺服器埠號 -->
									<div class="col-md-6">
										<spring:bind path="proxyPort">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label"> <spring:message
                                                        text="代理伺服器埠號"
														code="ui.directory-server.proxy-port" /><!--代理伺服器埠號-->
												</label>
                                                <form:input class="form-control" path="proxyPort" maxlength="5" />
												<form:errors path="proxyPort" />
												<!-- 錯誤綁定 -->
											</div>
										</spring:bind>
									</div>
                                </div>
                            </div>
						</div>
						<div class="row">&nbsp;</div>
						<div class="row">
							<!-- button -->
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label> 
									<a href="#"
										id="btnSubmit" class="btn btn-primary"><spring:message
											code="btn.save" /> <!--確認--></a> 
									<a href="#" id="btnReset"
										class="btn btn-default"><spring:message code="btn.reset" />
										<!--重設--></a> 
									<a href="./query" class="btn btn-default"><spring:message
											code="btn.cancel" /> <!--取消--></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#btnSubmit")
									.click(
											function() {
												if (!confirm('<spring:message text="" code="ui.confirm.save"/>')) {
													return false;
												}
												$('#form1').submit();
											});
							$("#btnReset").click(function() {
								$('#form1')[0].reset();
							});

						});
	</script>
</div>