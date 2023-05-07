<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>

	<!-- 1. 元數據。 -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- 2. 標題 -->
	<title><spring:message text="" code="application.name" /></title><!--InvCore invSys Server 後台管理系統-->

	<!-- 3. 瀏覽器樣式 -->
	<!-- (1) Bootstrap Core CSS -->
	<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<!-- (2) MetisMenu CSS -->
	<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css" />
	<!-- (3) Custom Fonts -->
	<link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<!-- (4) Custom CSS -->
	<link href="../dist/css/sb-admin-2.min.css" rel="stylesheet" type="text/css" />
	
	<!-- 4. 瀏覽器腳本 -->
	<!-- (1) IE9以下版本 -->
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<!-- (2) jQuery -->
	<script src="../vendor/jquery/jquery.min.js"></script>
	<!-- (3) Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
	<!-- (4) Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.min.js"></script>
	<!-- (5) Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.min.js"></script>
	<!-- (6) Encryption JavaScript -->
	<script src="../js/encrypt.min.js"></script>

	<!-- 5. Custom JavaScript -->
	<script type="text/javascript">
	$(function() {

		// 聲明：控件。
		var $form = $("<form action='#'></form>");
		var $changePassword = $("#change-password");
		var $logout = $("#logout");
		
		// 事件(1): 點擊[修改密碼]。
// 		$changePassword.on("click", function(event) {
// 			$form.attr("action", "./../index/edit_password");
// 			$form.attr("method", "get");
// 			$form.submit();
// 		});
		
		// 事件(2): 點擊[管理員登出]。
		$logout.on("click", function(event) {
			$form.attr("action", "./../index/logout");
			$form.attr("method", "get");
			$form.submit();
		});
		
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
				$form1.attr("action", "./first_login");
				$form1.attr("method", "post");
				$form1.submit();				
			}
		});
    	    	
    	// 方法(1): 前台驗證。
		function validate() {
    		
			var password = "";
			var regExpPassword = new RegExp("^[A-Za-z0-9]+$");

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
				$encryptPassword.val(window.encrypt($random8digits.val(), $account.val(), password));
				$encryptNewPassword.val(window.sha1($account.val() + $newPassword.val())); 
				$oldPassword.val(window.encrypt($random8digits.val(), $account.val(), password));
			}
			return true;
    	}
	
	});
	</script>

	<style>
		/* 瀏覽器寬度縮小,登出與修改密碼,不要超過左邊的邊界 */
		@media (max-width: 691px) { 
			.dropdown-logout {
			  right: auto;
			  left: 0;
			}
		}
		/* 瀏覽器寬度放大,登出與修改密碼,不要超過右邊的邊界 */
		@media (min-width: 692px) {
			.dropdown-logout {
			  right: 0;
			  left: auto;
			}
		}
	</style>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<spring:message text="" code="ui.change-password.first-login" /><!-- 首次登入請先變更密碼 -->
						</h3>
					</div>
					<div class="panel-body">
						<form:form id="form1" action="#" modelAttribute="form">
							<fieldset>
							
								<!-- 錯誤訊息區 -->
								<c:if test="${not empty errors}">
								<div class="row">
									<div class="col-md-12">
										<div class="alert alert-danger">
											<p class="fa fa-warning"><c:out value="${errors}"/></p>
										</div>
									</div>
								</div>
								</c:if>
								<div class="row hidden" id="error">
									<div class="col-md-12 alert alert-danger">
										<p class="fa fa-warning">
											<span id="error-format-style">
												<spring:message text="" code="warn.change-password.format-style" />
												<!-- 密碼請輸入 6 到 12 位英數字！ -->
											</span>
											<span id="error-old-equals-new">
												<spring:message text="" code="warn.change-password.old-equals-new" />
												<!-- 原始密碼與新密碼相同！ -->
											</span>
											<span id="error-new-not-equals-confirm">
												<spring:message text="" code="warn.change-password.new-not-equals-confirm" />
												<!-- 新密碼與確認密碼不相同！ -->
											</span>
			                       		</p>
									</div>
								</div>                                
								<!-- 用戶輸入區 -->
								<!-- (2) 用戶名稱 -->
								<spring:bind path="account">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.change-password.account" /><!-- 登錄帳號 -->
									</label>
									<form:input path="account" cssClass="form-control" maxlength="100"
											id="account" readonly="true" />
									<form:errors path="account" />
								</div>
								</spring:bind>
								
								<!-- (2) 原始密碼 -->
								<spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.change-password.old-password" />${req}<!-- 原始密碼 -->
									</label>						
									<form:password path="password" cssClass="form-control" maxlength="12"
											id="old-password" />
									<form:errors path="password" />
								</div>
								</spring:bind>
								
								<!-- (3) 新密碼 -->
								<div class="form-group">
									<label class="control-label">
										<spring:message text="" code="ui.change-password.new-password" />${req}<!-- 新密碼 -->
									</label>
									<form:password path="password" cssClass="form-control" maxlength="12"
											id="new-password" />
									<form:errors path="password" />
								</div>
			
								<!-- (4) 確認密碼 -->
								<div class="form-group">
									<label class="control-label">
										<spring:message text="" code="ui.change-password.confirm-password" />${req}<!-- 再次確認 -->
									</label>
									<form:password path="password" cssClass="form-control" maxlength="12"
											id="confirm-password" />
									<form:errors path="password" />
								</div>

								<!-- (6) 確認按鍵 -->
								<a id="btnSubmit" class="btn btn-lg btn-success btn-block">
									<spring:message text="" code="ui.login.submit" /><!-- 確定登錄 -->
								</a>
			                   	<!-- (5) 8碼亂碼 -->
			                   	<form:hidden path="random8digits" id="random8digits" />
			                   	<!-- (6) 舊密碼 SHA1 加密 -->
			                   	<form:hidden path="encryptPassword" id="encryptPassword" />
			                   	<!-- (7) 新密碼 SHA1 加密 -->
			                   	<form:hidden path="encryptNewPassword" id="encryptNewPassword" />
							</fieldset>
						</form:form>                        
					</div>
				</div>
			</div>
		</div>
	</div><!-- container 結束 -->
</body>
</html>
