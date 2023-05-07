<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../include/common.jsp"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message text="" code="ui.main.menu.change-password" />
	<!-- 密碼變更 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="form">
		<div class="row">
			<div class="col-md-12">
				<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message text="" code="ui.main.menu.change-password" />
						<!--使用者資料-->
					</div>
					<div class="panel-body">
						<!-- 錯誤訊息區 -->
						<c:if test="${not empty errors}">
							<div class="row" id="clean">
								<div class="col-md-12">
									<div class="alert alert-danger">
										<p class="fa fa-warning">
											<c:out value="${errors}" />
										</p>
									</div>
								</div>
							</div>
						</c:if>
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
						<!-- 2. 前台訊息 -->
						<div class="row hidden" id="error">
							<div class="col-md-12">
								<div class="alert alert-danger">
									<p class="fa fa-warning">
										<span id="error-format-style"> <spring:message text=""
												code="warn.change-password.format-style" /> <!-- 密碼請輸入 6 到 12 位英數字！ -->
										</span> <span id="error-old-equals-new"> <spring:message
												text="" code="warn.change-password.old-equals-new" /> <!-- 原始密碼與新密碼相同！ -->
										</span> <span id="error-new-not-equals-confirm"> <spring:message
												text="" code="warn.change-password.new-not-equals-confirm" />
											<!-- 新密碼與確認密碼不相同！ -->
										</span>
									</p>
								</div>
							</div>
						</div>
						<!-- 資料編輯區 -->
						<div class="row">
							<!-- (1)使用者代號 -->
							<div class="col-md-6">
								<spring:bind path="account">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> <spring:message text=""
												code="ui.change-password.account" /> <!-- 登錄帳號 -->
										</label>
										<form:input path="account" cssClass="form-control"
											readonly="true" id="account" value="${adminUser.account}" />
										<form:errors path="account" />
									</div>
								</spring:bind>
							</div>
							<!-- (2)原始密碼 -->
							<div class="col-md-6">
								<spring:bind path="password">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> <spring:message text=""
												code="ui.change-password.old-password" />${req}<!-- 原始密碼 -->
										</label>
										<form:password path="password" cssClass="form-control"
											maxlength="12" id="old-password" />
										<form:errors path="password" />
									</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- (3)新密碼 -->
							<div class="col-md-6">

								<div class="form-group">
									<label class="control-label"> <spring:message text=""
											code="ui.change-password.new-password" />${req}<!-- 新密碼 -->
									</label> 	
									<form:password path="password" cssClass="form-control" maxlength="12"
											id="new-password" />
									<form:errors path="password" />
								</div>

							</div>
							<!-- (4)再次確認  -->
							<div class="col-md-6">

								<div class="form-group">
									<label class="control-label"> <spring:message text=""
											code="ui.change-password.confirm-password" />${req}<!-- 再次確認 -->
									</label> 
									<form:password path="password" cssClass="form-control" maxlength="12"
											id="confirm-password" />
									<form:errors path="password" />
								</div>

							</div>
						</div>
						<!-- (5) 8碼亂碼 -->
						<form:hidden path="random8digits" id="random8digits" />
						<!-- (6) 舊密碼 SHA1 加密 -->
						<form:hidden path="encryptPassword" id="encryptPassword" />
						<!-- (7) 新密碼 SHA1 加密 -->
						<form:hidden path="encryptNewPassword" id="encryptNewPassword" />
						<!-- (8) locale-->


						<div class="row">&nbsp;</div>
						<div class="row">
							<!-- button -->
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label> 
									<a href="#"	id="btnSubmit" class="btn btn-primary"> 
										<spring:message text="" code="ui.change-password.submit" /> <!-- 變更 --></a>
									<a href="#" id="btnReset" class="btn btn-default">
										<spring:message text="" code="btn.reset" /> <!--重設--></a>
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
		$(function() {

			// 聲明：控件。
			var $form = $("<form action='#'></form>");
			var $changePassword = $("#change-password");
			var $logout = $("#logout");

			// 初始程序。
			$("body").append($form);

			//--------------------------------------------
			// 聲明：控件。
			var $form1 = $("#form1");
			var $account = $("#account");
			var $random8digits = $("#random8digits");
			var $oldPassword = $("#old-password");
			var $newPassword = $("#new-password");
			var $confirmPassword = $("#confirm-password");
			var $encryptPassword = $("#encryptPassword");
			var $encryptNewPassword = $("#encryptNewPassword");
			// 前台訊息
			var $error = $("#error");
			var $errorFormatStyle = $("#error-format-style");
			var $errorOldEqNew = $("#error-old-equals-new");
			var $errorNewNotEqConfirm = $("#error-new-not-equals-confirm");

			// 事件(1): 點擊[提交]
			$("#btnSubmit").click(function() {
				if (validate() == true) {

					$form1.attr("method", "post");
					$form1.submit();
				} else
					return false;
			});

			// 方法(1): 前台驗證。
			function validate() {

				var password = "";
				var regExpPassword = new RegExp("^[A-Za-z0-9]{6,12}$");

				// 去除空白字符。
				$oldPassword.val(String($oldPassword.val()).trim());
				$newPassword.val(String($newPassword.val()).trim());
				$confirmPassword.val(String($confirmPassword.val()).trim());

				// 除了舊密碼空白之外，要做驗證。
				password = $oldPassword.val();
				if (password.length != 0) {

					// 驗證1：新密碼格式
					if (regExpPassword.test($newPassword.val()) == false) {
						$error.removeClass("hidden");
						$error.find("span").addClass("hidden");
						$errorFormatStyle.removeClass("hidden");
						$newPassword.focus();
						return false;
					}

					// 驗證2: 舊密碼 == 新密碼
					if ($oldPassword.val() == $newPassword.val()) {
						$error.removeClass("hidden");
						$error.find("span").addClass("hidden");
						$errorOldEqNew.removeClass("hidden");
						$newPassword.focus();
						return false;
					}

					// 驗證3: 新密碼 != 驗證密碼
					if ($newPassword.val() != $confirmPassword.val()) {
						$error.removeClass("hidden");
						$error.find("span").addClass("hidden");
						$errorNewNotEqConfirm.removeClass("hidden");
						$confirmPassword.focus();
						return false;
					}
				} else {
					$error.addClass("hidden");
				}

				// 前台驗證成功：如果沒有輸入密碼，不做加密處理；如果有輸入密碼，則加密。
				if (password.length == 0) {
					$encryptPassword.val("");
					$encryptNewPassword.val("");
					$oldPassword.val("");
				} else {
					$encryptPassword.val(window.encrypt($random8digits.val(),
							$account.val(), password));
					$encryptNewPassword.val(window.sha1($account.val()
							+ $newPassword.val()));
					$oldPassword.val(window.encrypt($random8digits.val(),
							$account.val(), password));
				}
				return true;
			}

		});
	</script>
</div>

