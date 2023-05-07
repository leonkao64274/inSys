<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message text="" code="trans.log.query" /><!--交易紀錄查詢 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST"
		modelAttribute="transLogForm">
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

		<!-- 查詢條件 -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message text="" code="ui.search-criteria" /><!--查詢條件-->
						<div class="navbar-right" style="width: 100px; height: 0px; margin-top: -7px;">
							<!-- 1.2.1. 標題 -->
								<a class="btn btn-link" id="btnShowDiv" href="#" role="button">
									<span name="addSelectTitle"> <!--進階查詢  -->
									<spring:message text="進階查詢" code="ui.trans.advanced.select" />
									 </span>
									<i id="astIcon" class="fa fa-plus fa-fw"></i>
								<!-- 加減圖標 -->
								</a>
						</div>
					</div>

                    <div class="panel-body">
                        <div class="row">
							<!--  (1)交易日期起日-->
							<div class="col-md-3">
								<spring:bind path="startDate">
									<div class="form-group">
										<label class="control-label">
												<spring:message text="" code="ui.trans.log.start.date" /> <!--開始時間--> </label>
										<div class='input-group date' id="start_date">
											<form:input path="startDate" class="form-control" id="cds" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>

										</div>
									</div>
								</spring:bind>
							</div>

							<!--  (2)交易日期迄日-->
							<div class="col-md-3">
								<spring:bind path="endDate">
									<div class="form-group">
										<label class="control-label">
												<spring:message text="" code="ui.trans.log.end.date" /> <!--截止時間--> </label>
										<div class='input-group date' id="end_date">
											<form:input path="endDate" class="form-control" id="cde" />
											<span class="input-group-addon"> <span
												class="glyphicon glyphicon-calendar"></span>
											</span>

										</div>
									</div>
								</spring:bind>
							</div>

                            <!-- (3)卡組織 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaCardScheme">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.card-scheme.name"/><!--卡組織-->
									</label>
									<form:select class="form-control" path="criteriaCardScheme">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${cardSchemeConfigModel}"
 											itemValue="codeId" itemLabel="codeDesc" />
 									</form:select>
 									<form:errors path="criteriaCardScheme" /><!-- 錯誤綁定 -->
                                <span>&nbsp;</span>
                                </div>
                                </spring:bind>
                            </div>

                            <!-- (4)訊息版本 -->
                            <div class="col-md-3">
                            	<spring:bind path="messageVersion">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="message.version"/><!--訊息版本-->
									</label>
									<form:select class="form-control" path="messageVersion">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${messageVersionConfigModel}"
 											itemValue="itemValue" itemLabel="itemLabel" />
 									</form:select>
 									<form:errors path="messageVersion" /><!-- 錯誤綁定 -->
                                <span>&nbsp;</span>
                                </div>
                                </spring:bind>
                            </div>

                            <!-- (5)驗證結果 -->
                            <div class="col-md-3  show-div collapse">
                            	<spring:bind path="transStatus">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.trans.log.trans.status"/><!--驗證結果-->
									</label>
                                    <form:select class="form-control" path="transStatus">
                                        <form:option value="">
                                            <spring:message text="" code="ui.option-select" />
                                            <!--請選擇-->
                                        </form:option>
                                        <form:options items="${transStatusConfigModel}"
                                            itemValue="itemValue" itemLabel="itemLabel" />
                                    </form:select>
                                    <form:errors path="transStatus" />
                                <span>&nbsp;</span>
                                </div>
                                </spring:bind>
                            </div>

                            <!-- (6)裝置通道 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="deviceChannel">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
										<spring:message text="" code="device.channel" />
										</label>
										<form:select class="form-control" path="deviceChannel">
											<form:option value="">
												<spring:message text="" code="ui.option-select" />
												<!--請選擇-->
											</form:option>
											<form:options items="${deviceChannelConfigModel}"
												itemValue="itemValue" itemLabel="itemLabel" />
										</form:select>
										<form:errors path="deviceChannel" />
										<!-- 錯誤綁定 -->
										<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>


							<!-- (7)ACS 交易序号 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="acsTransID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
										ACS <spring:message code="acs.trans.id" />
											<!-- ACS 交易序号 -->
										</label>
										<form:input path="acsTransID" class="form-control"
											maxlength="36" />
										<form:errors path="acsTransID" />
										<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>
							<!-- (8)invSys Server 交易序号 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="invSysServerTransID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
										invSys Server <spring:message code="acs.trans.id" />
											<!-- invSys Server 交易序号 -->
										</label>
										<form:input path="invSysServerTransID" class="form-control"
											maxlength="36" />
										<form:errors path="invSysServerTransID" />
										<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>
							<!-- (9)身分證號 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="acctId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message text="" code="ui.trans.log.acct.id" /><!-- 身分證號-->
										</label>
										<form:input path="acctId" class="form-control" maxlength="64" />
										<form:errors path="acctId" />
									<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>

							<!-- (10)卡號前6碼 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="acctNumberPrefix">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message text="" code="ui.trans.log.acct.number.prefix" /><!-- 卡號前6碼-->
										</label>
										<form:input path="acctNumberPrefix" class="form-control" maxlength="6" />
										<form:errors path="acctNumberPrefix" />
									<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>

							<!-- (11)卡號後4碼 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="acctNumberPostfix">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message text="" code="ui.trans.log.acct.number.postfix" /><!-- 卡號後4碼-->
										</label>
										<form:input path="acctNumberPostfix" class="form-control" maxlength="4" />
										<form:errors path="acctNumberPostfix" />
									<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>

							<!-- (12)特店代號 -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="acquirerMerchantID">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message text="" code="ui.trans.log.acquirer.merchant.id" /><!-- 特店代號 -->
										</label>
										<form:input path="acquirerMerchantID" class="form-control" maxlength="35" />
										<form:errors path="acquirerMerchantID" />
									<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>

<!-- 							 (13)交易金額  -->
							<div class="col-md-3  show-div collapse">
                                <spring:bind path="purchaseAmount">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message text="" code="purchase.amount" />
										</label>
										<form:input path="purchaseAmount" class="form-control" maxlength="10" />
										<form:errors path="purchaseAmount" />
									<span>&nbsp;</span>
									</div>
                                </spring:bind>
							</div>
							<!-- (14)Message Category -->
							<div class="col-md-3  show-div collapse">
								<spring:bind path="messageCategory">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
<%-- 										<spring:message text="" code="device.channel" /> --%>
											<spring:message text="MessageCategory" code="ui.trans.messageCategory" />
										</label>
										<form:select class="form-control" path="messageCategory">
											<form:option value="">
												<spring:message text="" code="ui.option-select" />
												<!--請選擇-->
											</form:option>
											<form:options items="${messagecategoryModel}"
												itemValue="itemValue" itemLabel="itemLabel" />
										</form:select>
										<form:errors path="messageCategory" />
										<!-- 錯誤綁定 -->
										<span>&nbsp;</span>
									</div>
								</spring:bind>
							</div>

                            <!-- (16)Requestor帳號 -->
                            <div class="col-md-3  show-div collapse">
                                <spring:bind path="integratorRequestorId">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <label class="control-label">
                                            <spring:message text="Requestor帳號" code="ui.requestor.requestorId" />
                                        </label>
                                        <form:input path="integratorRequestorId" class="form-control" maxlength="36" />
                                        <form:errors path="integratorRequestorId" />
                                        <span>&nbsp;</span>
                                    </div>
                                </spring:bind>
                            </div>

                            <!-- (17)Requestor訂單序號 -->
                            <div class="col-md-3  show-div collapse">
                                <spring:bind path="invSysRequestorOrderId">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <label class="control-label">
                                            <spring:message text="Requestor訂單序號" code="ui.requestor.invSysRequestorOrderId" />
                                        </label>
                                        <form:input path="invSysRequestorOrderId" class="form-control" maxlength="36" />
                                        <form:errors path="invSysRequestorOrderId" />
                                        <span>&nbsp;</span>
                                    </div>
                                </spring:bind>
                            </div>

                        </div>
                    </div><!-- ./ <div class="panel-body"> -->
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="pull-right">
					<a href="#" id="btnQuery" class="btn btn-primary"><spring:message text="" code="btn.query" /> <!--查詢--></a>
				</div>
			</div>
		</div>

		<div style="height: 40px;"></div>

		<!-- 查詢結果 -->
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr class="bg-primary">
								<th class="text-nowrap">
									<spring:message text="" code="acs.trans.id" /><!-- 交易序號 --><br>
									<spring:message text="" code="message.version" /><!-- 訊息版本 -->/<spring:message text="MessageCategory" code="ui.trans.messageCategory" />
								</th>
								<th>
									<spring:message text="" code="ui.trans.acct.number" /> <!-- 持卡人卡號--><br>
                                   <%-- <spring:message text="" code="ui.trans.acct.number.postfix" /> <!-- 持卡人卡號后4 --><br>--%>
									<%--<spring:message text="" code="device.channel" /><!-- 設備通道 -->--%>

								</th>
								<th>
									<spring:message text="" code="purchase.currency" /> <!-- 幣別 --><br>
									<spring:message text="" code="purchase.amount" /> <!-- 金額 -->
								</th>
								<th>
									<spring:message text="" code="merchant.name" /><!-- 商戶 --><br>
									<spring:message text="" code="acquirer.merchant.id" /><!-- 特店代號-->
								</th>
                                <th>
                                    <spring:message text="" code="integrator.requestor.id" /><!-- Requestor账号 --><br>
                                    <spring:message text="" code="invSys.requestor.order.id" /><!-- Requestor订单序号-->
                                </th>
								<th><spring:message text="" code="trans.time" /></th><!-- 交易時間 -->
								<th><spring:message text="" code="eci" /></th><!-- ECI -->
								<th><spring:message text="" code="trans.status" /></th><!-- 交易結果 -->
								<th><spring:message text="" code="error.description" /></th><!-- 錯誤訊息 -->
							</tr>
						</thead>
						<tbody>
	                        <c:forEach items="${queryResult.content}" var="bean">
	                        	<tr>
	                                <td><a data-toggle="tooltip" data-placement="top" title="<spring:message text="" code="ui.toggle.trans-detail"/>" href="detail?oid=<c:out value="${bean.invSysServerTransId}"/>">
	                               		<c:out value="${bean.invSysServerTransId}"/><!-- 交易序號 --></a><br>
	                               		<c:out value="${bean.messageVersion}"/><!-- 訊息版本 -->&nbsp;/&nbsp;
	                               		<spring:message text="" code="ui.trans.log.messageCategory.${bean.messageCategory}"/>
	                              	</td>
                                    <td>
                                        <c:if test="${bean.messageVersion == '2.1.0'}">
                                            <c:out value="${bean.acctNumberPrefix}XXXXXX${bean.acctNumberPostfix}" />
                                        </c:if>
                                        <c:if test="${bean.messageVersion == '1.0.2'}">
                                            <admin:StringMaskTagHandler prefixLen="6" postfixLen="4" placeholder="X" text="${bean.acctNumber}" />
                                        </c:if>
										<%--<c:out value="${bean.deviceChannel}"/><!-- 設備通道 -->
                                        <c:if test="${not empty bean.deviceChannel}">
                                            <spring:message text="" code="ui.trans.log.deviceChannel.${bean.deviceChannel}"/>
                                        </c:if>--%>
                                   	</td>
	                                <td>
<!-- 	                                	<-- 幣別字母代號(使用"幣別數字代號"  到   "invSys_acs.t_currency"  table中查詢  -->
	                                	<admin:CurrenyTagHandler text="${bean.purchaseCurrency}"/><br>
	                               		(<c:out value="${bean.purchaseCurrency}"/>)<br><!-- 幣別數字代號 -->
<!-- 	                               		<-- purchaseExponent:決定金額的小數點的位置 -->
	                               		<admin:DecimalPointTagHandler pointLocation="${bean.purchaseExponent}" text="${bean.purchaseAmount}"/><!-- 金額  -->
	                              	</td>
	                                <td>
	                               		<c:out value="${bean.merchantName}"/><br><!-- 商戶 -->
                                        <c:out value="${bean.acquirerMerchantId}"/><!-- 特店代號  -->
	                              	</td>
                                    <td>
                                        <c:out value="${bean.integratorRequestorId}"/><br><!-- Requestor账号 -->
                                        <c:out value="${bean.requestorOrderId}"/><!-- Requestor订单序号  -->
                                    </td>
	                              	<td>
										<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${bean.createDate}${bean.createTime}" />
		                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${createTime}"/>
		                            </td>
	                              	<td><c:out value="${bean.eci}"/></td><!-- ECI -->
	                              	<td>
<!--                                      <--    交易結果 -->
                                        <c:if test="${not empty bean.transStatus}">
                                            <spring:message text="" code="ui.trans.log.transStatus.${bean.transStatus}" />
                                        </c:if>
	                              	</td>
	                              	<td>
<!--                                       <--  錯誤訊息  -->
                                        <c:if test="${not empty bean.errorCode}">
                                            (<c:out value="${bean.errorCode}"/>) <c:out value="${bean.errorDescription}"/>
                                        </c:if>
                                       <c:if test="${not empty bean.transStatusReason}">
                                            (<c:out value="${bean.transStatusReason}"/>) <spring:message text="" code="ui.trans-status-reason.${bean.transStatusReason}" />
                                        </c:if>
                                    </td>
                            	</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
		<!-- paginator -->
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
		</c:if>
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(function() {
			$("#btnQuery").click(function(){
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });

			$('#start_date').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : "${locale}",
			    defaultDate: new Date().setMonth(new Date().getMonth()-2),
			});

			$('#end_date').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : "${locale}",
				defaultDate: new Date().setDate(new Date().getDate()),
				maxDate : new Date().setDate(new Date().getDate())
			});

			$('[data-toggle="tooltip"]').tooltip();

			$("#btnShowDiv").click(function() {
				if ($("#astIcon").hasClass("fa fa-plus fa-fw")) {
					$(".show-div").slideToggle();
					$("#astIcon").removeClass("fa fa-plus fa-fw");
					$("#astIcon").addClass("fa fa-minus fa-fw");
				} else {
					$(".show-div").slideToggle();
					$("#astIcon").removeClass("fa fa-minus fa-fw");
					$("#astIcon").addClass("fa fa-plus fa-fw");

				}

			})
		});
	</script>
</div>
