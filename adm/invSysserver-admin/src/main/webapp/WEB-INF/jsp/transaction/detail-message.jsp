<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>

<%--  <spring:message text="" code="ui.issuer.name" />   --%>


	<div class="col-md-12">
	<div id="masonry" class="test">


		<!--基本訊息 -->
		<div class="item col-md-12 ">
			<div class="panel panel-info">

				<div class="panel-heading"><spring:message text="基本訊息" code="ui.trans.log.basic.message" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> invSys Server <spring:message code="acs.trans.id" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysServerTransID}" />
										<c:if test="${empty form.invSysServerTransID}">N/A</c:if>
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
										<spring:message text="${form.errorCode}"
											code="ui.trans.log.errorCode.${form.errorCode}" />
										<c:if test="${empty form.errorCode}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤平台代碼" code="ui.trans.log.details.errorComponent" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.errorComponent}"
											code="ui.trans.log.errorComponent.${form.errorComponent}" />
										<c:if test="${empty form.errorComponent}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤摘要" code="ui.trans.log.details.errorDescription" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.errorDescription}" />
										<c:if test="${empty form.errorDescription}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="錯誤明細" code="ui.trans.log.details.errorDetail" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.errorDetail}" />
										<c:if test="${empty form.errorDetail}">N/A</c:if>
									</div>
									<hr>
								</div>

							<div class="col-md-4 ">
								<H4 class="control-label"><spring:message text="錯誤類別" code="ui.trans.log.details.errorMessageType" /></H4>
								<div style="word-break: break-all">
									<c:out value="${form.errorMessageType}" />
									<c:if test="${empty form.errorMessageType}">N/A</c:if>
								</div>
								<hr>
							</div>
							<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易管道" code="ui.trans.log.basic.deviceChannel" /> </H4>
									<div style="word-break: break-all">
										${form.deviceChannel}-
										<c:if test="${form.deviceChannel=='01'}">
											<spring:message text="" code="ui.trans.log.deviceChannel.01" />
										</c:if>
										<c:if test="${form.deviceChannel=='02'}">
											<spring:message text="" code="ui.trans.log.deviceChannel.02" />
										</c:if>
										<c:if test="${form.deviceChannel=='03'}">
											<spring:message text="" code="ui.trans.log.deviceChannel.03" />
										</c:if>
										<c:if test="${empty form.deviceChannel}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
							<div class="col-md-4">
									<H4 class="control-label"><spring:message text="MessageCategory" code="ui.trans.messageCategory" /></H4>
									<div style="word-break: break-all">
										<spring:message text="" code="ui.trans.log.messageCategory.${form.messageCategory}"/>
										<c:if test="${empty form.messageCategory}">N/A</c:if>
									</div>
									<hr>
								</div>
							<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="報文版本" code="ui.trans.log.details.messageVersionNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.messageVersion}" />
										<c:if test="${empty form.messageVersion}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="結果訊息狀態" code="ui.trans.log.details.resultsMessageStatus" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="" code="ui.trans.log.resultsStatus.${form.resultsStatus}"/>
										<c:if test="${empty form.resultsStatus}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
                                <div class="col-md-4">
									<H4 class="control-label"> <spring:message text="認證方式" code="ui.trans.log.basic.authenticationMethod" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="" code="ui.trans.log.authenticationMethod.${form.authenticationMethod}"/>
										<c:if test="${empty form.authenticationMethod}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="授權類別" code="ui.trans.log.details.authenticationType" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="" code="ui.trans.log.authenticationType.${form.authenticationType}"/>
										<c:if test="${empty form.authenticationType}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="授權碼" code="ui.trans.log.details.authenticationValue" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.authenticationValue}" />
										<c:if test="${empty form.authenticationValue}">N/A</c:if>
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

		<!--Transaction& Checkout Page Info -->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="交易結帳資訊" code="ui.trans.log.details.transactionCheckoutInfo" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="invSys發起平台ID" code="ui.trans.log.details.invSysRequestorId" /> </H4>
									<div>
										<c:out value="${form.invSysRequestorID}" />
										<c:if test="${empty form.invSysRequestorID}">N/A</c:if>
									</div>
									<hr>
								</div>
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="invSys發起平台名稱" code="ui.trans.log.details.invSysRequestorName" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysRequestorName}" />
										<c:if test="${empty form.invSysRequestorName}">N/A</c:if>
									</div>
									<hr>
								</div>
									<div class="col-md-4">
									<H4 class="control-label"><spring:message text="持卡人設備裝置支援介面" code="ui.trans.log.details.deviceUiInterface" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.deviceUiInterface}" />
										<c:if test="${empty form.deviceUiInterface}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="持卡人設備使用者介面類別" code="ui.trans.log.details.deviceUiType" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.deviceUiType}" />
										<c:if test="${empty form.deviceUiType}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易認證狀態" code="ui.trans.log.details.invSysRequestorChallengeInd" />
										Indicator </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.invSysRequestorChallengeInd}"
											code="ui.trans.log.invSysRequestorChallengeInd.${form.invSysRequestorChallengeInd}" />
										<c:if test="${empty form.invSysRequestorChallengeInd}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="invSys發起平台URL" code="ui.trans.log.details.invSysRequestorURL" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysRequestorURL}" />
										<c:if test="${empty form.invSysRequestorURL}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="收單行代號" code="ui.trans.log.basic.acquirerBin" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acquirerBIN}" />
										<c:if test="${empty form.acquirerBIN}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="商店代號" code="ui.trans.log.basic.acquirerMerchantId" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acquirerMerchantID}" />
										<c:if test="${empty form.acquirerMerchantID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="商店名稱" code="ui.trans.log.details.merchantName" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.merchantName}" />
										<c:if test="${empty form.merchantName}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="商店類別代碼" code="ui.mcc-risk.mcc" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.mcc}" />
										<c:if test="${empty form.mcc}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="商店國別" code="ui.trans.log.basic.merchantCountryCode" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.merchantCountryCode}" />
										<c:if test="${empty form.merchantCountryCode}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="帳單與出貨地址是否吻合" code="ui.trans.log.details.addrMatch" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.addrMatch}"
											code="ui.trans.log.addrMatch.${form.addrMatch}" />
										<c:if test="${empty form.addrMatch}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="付款分期資訊" code="ui.trans.log.basic.instalmentPaymentData" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.purchaseInstalData}" />
										<c:if test="${empty form.purchaseInstalData}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="Cres訊息通知URL" code="ui.trans.log.details.notificationURL" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.notificationURL}" />
										<c:if test="${empty form.notificationURL}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="付款金額" code="ui.trans.log.basic.purchaseAmount" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.purchaseAmount}" />
										<c:if test="${empty form.purchaseAmount}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="購買金額小數位數" code="ui.trans.log.details.purchaseExponent" />
									</H4>
									<div style="word-break: break-all">
										<c:out value="${form.purchaseExponent}" />
										<c:if test="${empty form.purchaseExponent}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="付款幣別" code="ui.trans.log.basic.purchaseCurrency" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.purchaseCurrency}" />
										<c:if test="${empty form.purchaseCurrency}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="分期付款到期日" code="ui.trans.log.details.recurringExpiry" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.recurringExpiry}" />
										<c:if test="${empty form.recurringExpiry}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="分期付款週期" code="ui.trans.log.details.recurringFrequency" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.recurringFrequency}" />
										<c:if test="${empty form.recurringFrequency}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="購買日期時間" code="ui.trans.log.details.purchaseDate" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.purchaseDate}" />
										<c:if test="${empty form.purchaseDate}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> Payment Gateway <spring:message code="acs.trans.id" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.pmtTransID}" />
										<c:if test="${empty form.pmtTransID}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Secure Payment -->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="付款安全資訊" code="ui.trans.log.securePayment" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="持卡人姓名" code="ui.trans.log.basic.cardholderName" /> </H4>
									<div>
										<admin:StringMaskTagHandler prefixLen="1" postfixLen="0"
											placeholder="X" text="${form.cardholderName}" />
										<c:if test="${empty form.cardholderName}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="持卡人ID" code="ui.trans.log.basic.cardholderAccountIdentifier" /> </H4>
									<div>
										<admin:StringMaskTagHandler prefixLen="5" postfixLen="0"
											placeholder="X" text="${form.acctID}" />
										<c:if test="${empty form.acctID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="卡號有效期" code="ui.card-data.expiry-date" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.cardExpiryDate}" />
										<c:if test="${empty form.cardExpiryDate}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="卡片類別" code="ui.trans.log.securePayment.acctType" /></H4>
									<div style="word-break: break-all">
										<c:if test="${form.acctType=='01'}">Not Applicable</c:if>
										<c:if test="${form.acctType=='02'}">Credit</c:if>
										<c:if test="${form.acctType=='03'}">Debit</c:if>
										<c:if test="${empty form.acctType}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--invSys  訊息-->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading">invSys <spring:message text="基本訊息" code="ui.trans.log.basic.message" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">

							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="是否為 3RI 交易" code="ui.trans.log.invSysRequestor3RIInd" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.invSysRequestor3RIInd}"
											code="ui.trans.log.invSysRequestor3RIInd.${form.invSysRequestor3RIInd}" />
										<c:if test="${empty form.invSysRequestor3RIInd}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易類別" code="ui.trans.log.invSysRequestorNPAInd" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.invSysRequestorNPAInd}"
											code="ui.trans.log.invSysRequestorNPAInd.${form.invSysRequestorNPAInd}" />
										<c:if test="${empty form.invSysRequestorNPAInd}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> invSys Server <spring:message text="參考號" code="ui.trans.log.referenceNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysServerRefNumber}" />
										<c:if test="${empty form.invSysServerRefNumber}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="卡組織invSys註冊號" code="ui.trans.log.invSysServerOperatorID" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysServerOperatorID}" />
										<c:if test="${empty form.invSysServerOperatorID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> invSys Server URL </H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysServerURL}" />
										<c:if test="${empty form.invSysServerURL}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> invSys Requestor <spring:message text="階段資料" code="ui.trans.log.sessionData" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.invSysSessionData}" />
										<c:if test="${empty form.invSysSessionData}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="invSys Method 完成狀態指標" code="ui.trans.log.invSysCompInd" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.invSysCompInd}"
											code="ui.trans.log.invSysCompInd.${form.invSysCompInd}" />
										<c:if test="${empty form.invSysCompInd}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<!--DS 訊息-->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading">DS <spring:message text="基本訊息" code="ui.trans.log.basic.message" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> DS <spring:message text="參考號" code="ui.trans.log.ReferenceNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.dsReferenceNumber}" />
										<c:if test="${empty form.dsReferenceNumber}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> DS <spring:message text="交易ID" code="ui.trans.log.transID" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.dsTransID}" />
										<c:if test="${empty form.dsTransID}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--ACS 訊息-->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading">ACS <spring:message text="基本訊息" code="ui.trans.log.basic.message" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> ACS  <spring:message code="acs.trans.id" /></H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsTransID}" />
										<c:if test="${empty form.acsTransID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="卡組織ACS註冊號" code="ui.trans.log.acsOperatorId" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsOperatorID}" />
										<c:if test="${empty form.acsOperatorID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="卡組織ACS參考號" code="ui.trans.log.acsReferenceNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsReferenceNumber}" />
										<c:if test="${empty form.acsReferenceNumber}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> ACS <spring:message text="持卡人設備裝置支援介面" code="ui.trans.log.details.deviceUiInterface" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsUiInterface}" />
										<c:if test="${empty form.acsUiInterface}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> ACS <spring:message text="持卡人設備使用者介面類別" code="ui.trans.log.details.deviceUiType" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.acsUiType}"
											code="ui.trans.log.acsUiType.${form.acsUiType}" />
										<c:if test="${empty form.acsUiType}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> ACS URL </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsURL}" />
										<c:if test="${empty form.acsURL}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> ACS <spring:message text="交易是否需要認證" code="ui.trans.log.acschallengeMandated" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.acschallengeMandated}"
											code="ui.trans.log.acschallengeMandated.${form.acschallengeMandated}" />
										<c:if test="${empty form.acschallengeMandated}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!--設備 資訊-->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="瀏覽器資訊" code="ui.trans.log.browserInfoes" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="HTTP標頭" code="ui.trans.log.browserInfoes.browserAcceptHeader" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserAcceptHeader}" />
										<c:if test="${empty form.browserAcceptHeader}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器IP" code="ui.trans.log.browserInfoes.browserIp" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserIP}" />
										<c:if test="${empty form.browserIP}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器是否允許程式碼" code="ui.trans.log.browserInfoes.browserJavaEnabled" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserJavaEnabled}" />
										<c:if test="${empty form.browserJavaEnabled}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器語系" code="ui.trans.log.browserInfoes.browserLanguage" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserLanguage}" />
										<c:if test="${empty form.browserLanguage}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器色深" code="ui.trans.log.browserInfoes.browserColorDepth" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserColorDepth}" />
										<c:if test="${empty form.browserColorDepth}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器屏高" code="ui.trans.log.browserInfoes.browserScreenHeight" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserScreenHeight}" />
										<c:if test="${empty form.browserScreenHeight}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器屏寬" code="ui.trans.log.browserInfoes.browserScreenWidth" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserScreenWidth}" />
										<c:if test="${empty form.browserScreenWidth}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器時區" code="ui.trans.log.browserInfoes.browserTZ" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserTZ}" />
										<c:if test="${empty form.browserTZ}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="瀏覽器UserAgent訊息" code="ui.trans.log.browserInfoes.browserUserAgent" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.browserUserAgent}" />
										<c:if test="${empty form.browserUserAgent}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> SDK App ID </H4>
									<div style="word-break: break-all">
										<c:out value="${form.sdkAppID}" />
										<c:if test="${empty form.sdkAppID}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="SDK參考號" code="ui.trans.log.browserInfoes.sdkReferenceNumber" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.sdkReferenceNumber}" />
										<c:if test="${empty form.sdkReferenceNumber}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> SDK <spring:message code="acs.trans.id" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.sdkTransID}" />
										<c:if test="${empty form.sdkTransID}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="SDK交易最大逾時" code="ui.trans.log.browserInfoes.sdkMaxTimeout" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.sdkMaxTimeout}" />
										<c:if test="${empty form.sdkMaxTimeout}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>


		<!--//Cardholder Billing Address -->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="發票資訊" code="ui.trans.log.billingInfoes" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送城市" code="ui.trans.log.billingInfoes.billAddrCity" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrCity}" />
										<c:if test="${empty form.billAddrCity}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送國家" code="ui.trans.log.billingInfoes.billAddrCountry" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrCountry}" />
										<c:if test="${empty form.billAddrCountry}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送街路地址1" code="ui.trans.log.billingInfoes.billAddrLine1" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrLine1}" />
										<c:if test="${empty form.billAddrLine1}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送街路地址2" code="ui.trans.log.billingInfoes.billAddrLine2" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrLine2}" />
										<c:if test="${empty form.billAddrLine2}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送街路地址3" code="ui.trans.log.billingInfoes.billAddrLine3" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrLine3}" />
										<c:if test="${empty form.billAddrLine3}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="發票寄送洲/省分" code="ui.trans.log.billingInfoes.billAddrState" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.billAddrState}" />
										<c:if test="${empty form.billAddrState}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="電子郵件" code="ui.cardholder-data.email" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.email}" />
										<c:if test="${empty form.email}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>



		<!--//Cardholder Billing Address -->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="出貨資訊" code="ui.trans.log.shippingInfoes" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨城市" code="ui.trans.log.shippingInfoes.AddrCity" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrCity}" />
										<c:if test="${empty form.shipAddrCity}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨國家" code="ui.trans.log.shippingInfoes.AddrCountry" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrCountry}" />
										<c:if test="${empty form.shipAddrCountry}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨街路地址1" code="ui.trans.log.shippingInfoes.AddrLine1" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrLine1}" />
										<c:if test="${empty form.shipAddrLine1}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨街路地址1" code="ui.trans.log.shippingInfoes.AddrLine2" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrLine2}" />
										<c:if test="${empty form.shipAddrLine2}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨街路地址1" code="ui.trans.log.shippingInfoes.AddrLine3" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrLine3}" />
										<c:if test="${empty form.shipAddrLine3}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨郵遞區號" code="ui.trans.log.shippingInfoes.postalCode" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrPostCode}" />
										<c:if test="${empty form.shipAddrPostCode}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>
							<div class="row">

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="出貨洲/省分" code="ui.trans.log.shippingInfoes.AddrState" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.shipAddrState}" />
										<c:if test="${empty form.shipAddrState}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>

		<!--其他訊息-->
		<div class="col-md-12 ">
			<div class="panel panel-info">
				<div class="panel-heading"><spring:message text="其他資訊" code="ui.trans.log.otherMessage" /></div>

				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="ACS簽章內容" code="ui.trans.log.otherMessage.acsSignedContent" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.acsSignedContent}" />
										<c:if test="${empty form.acsSignedContent}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易認證是否被取消" code="ui.trans.log.otherMessage.challengeCancel" /> </H4>
									<div style="word-break: break-all">
										<spring:message text="${form.challengeCancel}"
											code="ui.trans.log.challengeCancel.${form.challengeCancel}" />
										<c:if test="${empty form.challengeCancel}">N/A</c:if>
									</div>
									<hr>
								</div>

								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="交易Token是否已解開" code="ui.trans.log.otherMessage.payTokenInd" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.payTokenInd}" />
										<c:if test="${empty form.payTokenInd}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>

							<div class="row">
								<div class="col-md-4">
									<H4 class="control-label"> <spring:message text="互動計數" code="ui.trans.log.otherMessage.interactionCounter" /> </H4>
									<div style="word-break: break-all">
										<c:out value="${form.interactionCounter}" />
										<c:if test="${empty form.interactionCounter}">N/A</c:if>
									</div>
									<hr>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
</div>

