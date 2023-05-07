<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../include/common.jsp"%>

<!-- 網頁：選單標題區。-->
<div id="sitemesh-header">
	<spring:message code="ui.invSys-trans-config.name" />
	<!-- 交易參數設定 -->
</div>

<!-- 網頁：功能內容區。-->
<div id="sitemesh-content">
	<!-- 使用 Spring 資料綁定機制。-->
	<!-- 1. invSysTransConfigForm : 內含 "卡組織" 與 "實體集合" 兩種訊息。 -->
	<form:form id="form1" action="#" method="POST"
		modelAttribute="invSysTransConfigForm">
		<div class="row">
			<div class="col-md-12">
				<!-- 這裡使用 "面板" 顯示標題與內容。-->
				<div class="panel panel-default">

					<!-- (1) 標題。-->
					<div class="panel-heading">
						<spring:message code="ui.invSys-trans-config.data" />
						<!-- 交易參數資料-設定 -->
					</div>

					<!-- (2) 內容。-->
					<div class="panel-body">

						<!-- (2.1) 後台：錯誤訊息區 -->
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
						<!-- 成功訊息區 -->
						<c:if test="${not empty successMsg}">
							<div class="row">
								<div class="col-md-12">
									<div class="alert alert-success">
										<p class="fa fa-warning">
											<spring:message code="ui.successfully.modified" />
										</p>
									</div>
								</div>
							</div>
						</c:if>

						<!-- (2.2) 資料編輯區 -->
						<div class="row">
							<c:forEach items="${invSysTransConfigForm.entities}"
								var="entity" varStatus="listStatus">
								<div class="col-md-6">

									<!-- 綁定的屬性 -->
									<form:hidden path="entities[${listStatus.index}].oid" />
									<form:hidden path="entities[${listStatus.index}].cardScheme" />
									

									<!-- 1. 交易通道 -->
									<div class="form-group">
										<label class="control-label">
											${invSysTransConfigForm.cardSchemaList[listStatus.index].codeDesc}
										</label>
										<spring:bind
											path="entities[${listStatus.index}].channelEnable">
											<label class="form-control"> &nbsp; &nbsp; <form:radiobutton
													class="channel-value"
													path="entities[${listStatus.index}].channelEnable"
													value="1" /> <spring:message
													code="ui.invSys-trans-config.channel.enable" /> &nbsp; &nbsp;
												<form:radiobutton class="channel-value"
													path="entities[${listStatus.index}].channelEnable"
													value="0" /> <spring:message
													code="ui.invSys-trans-config.channel.disable" />

											</label>
										</spring:bind>
									</div>

								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 按鍵區。-->
		<div class="row">
			<div class="col-md-12">
				<div class="form-group">
					<label class="control-label"></label> <a href="#" id="btnSubmit"
						class="btn btn-primary"> <spring:message code="btn.save" /> <!--確認-->
					</a> <a href="#" id="btnReset" class="btn btn-default"> <spring:message
							code="btn.reset" /> <!--重設-->
					</a>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- 網頁：瀏覽器腳本區。-->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(function() {

			// 屬性(1): 交易通道設定。
			var $channelBind = $(".channel-bind");
			var $channelValue = $(".channel-value");
			var channelSize = $channelBind.length;

			// 事件(1): 表單提交。
			$("#btnSubmit")
					.click(
							function() {
								var message = '<spring:message text="" code="ui.confirm.save"/>';
								if (confirm(message)) {
									$('#form1').submit();
									return true;// display spin for processing
								}
								return false;// stop spin for processing
							});

			// 事件(2): 表單還原。
			$("#btnReset").on("click", function(event) {
				$('#form1')[0].reset();
			});

			// 方法(1): 用戶輸入 => 綁定值。
			function valuesToBind() {
				var i = 0;
				for (i = 0; i < channelSize; i++) {
					$channelBind.eq(i).val($channelValue.eq(i).prop("checked"));
				}
			}

		});
	</script>
</div>