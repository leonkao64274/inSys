<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header"></div>

<!-- page content -->
<div id="sitemesh-content">
	<!-- 查詢結果 -->
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table class="table table-bordered">
					<tbody>

						<tr>
							<td class="bg-primary"><spring:message
									code="ui.card-scheme.name" />
								<!--卡組織--></td>
							<td><spring:message code="CARD_SCHEME.${form.cardScheme}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.main-url" />
								<!--主要網址--></td>
							<td><c:out value="${form.areqUrl}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.alternate-url" />
								<!--備援網址--></td>
							<td><c:out value="${form.backupAreqUrl}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.preqUrl" text="PReq备用网址" /></td>
							<td><c:out value="${form.preqUrl}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.backPreqUrl" text="PReq备用网址" /></td>
							<td><c:out value="${form.backupPreqUrl}" /></td>
						</tr>
												<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.rreqUrl" text="RReq备用网址" /></td>
							<td><c:out value="${form.rreqUrl}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.backRreqUrl" text="RReq备用网址" /></td>
							<td><c:out value="${form.backupRreqUrl}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.message-version-number" />
								<!--訊息版本號碼--></td>
							<td><c:out value="${form.messageVersion}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.number-retries" />
								<!--請求重試次數--></td>
							<td><c:out value="${form.retryLimits}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.intervals" />
								<!--請求間隔時間(秒)--></td>
							<td><c:out value="${form.retryInterval}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.readTimeout" />
								<!--連線逾時時間(秒)--></td>
							<td><c:out value="${form.readTimeout}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.invSyssvr-operator-id" />
								<!--invSys Server Operator ID--></td>
							<td><c:out value="${form.invSysServerOperatorID}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.invSyssvr-ref-num" />
								<!-- invSys Server Reference Number--></td>
							<td><c:out value="${form.invSysServerRefNumber}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.proxy-enabled" />
								<!-- 是否啟用代理服務器 --></td>
							<td>
                                <c:if test="${form.proxyEnabled == '0'}">
                                    <spring:message code="ui.directory-server.disabled" />
                                </c:if>
                                <c:if test="${form.proxyEnabled == '1'}">
                                    <spring:message code="ui.directory-server.enabled" />
                                </c:if>
                                (<c:out value="${form.proxyEnabled}" />)
                            </td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.proxy-host" />
								<!-- 代理服務器 --></td>
							<td><c:out value="${form.proxyHost}" /></td>
						</tr>
						<tr>
							<td class="bg-primary"><spring:message
									code="ui.directory-server.proxy-port" />
								<!-- 代理服務器埠號 --></td>
							<td><c:out value="${form.proxyPort}" /></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="text-center">
				<a href="./query" class="btn btn-default"><spring:message
						code="btn.back-history" />
					<!--回上一頁--></a>
			</div>
		</div>
	</div>

</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script"></div>