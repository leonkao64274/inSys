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
	<link href="../css/login_style.css" rel="stylesheet">
	
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
        
        // 關閉 autocomplete
        $("input").each(function(){
            $(this).attr("autocomplete", "off");
        });

		// 聲明：控件。
		var $account = $("#account");
		var $password = $("#password");
		var $locale = $("#locale");
		var $random8digits = $("#random8digits");
		var $encrypt = $("#encrypt");
		var $remember = $("#remember");
		// 聲明: 常數。
		var STORAGE_LOGIN = "acs@admin";
		// 聲明: 實例。
		var loginItem = null;		// 放置: 本地儲存的實例。
		var loginValue = null;		// 放置: 儲存值(JSON)。
		// 聲明: local 變數。
		var hint = "";
		
		// 事件(1)：點擊[登錄]。
		$("#btnLogin").click(function() {
			
			var password = "";
			var length = 0, i = 0;
			
			// 1. 如果沒有輸入密碼，不做加密處理；如果有輸入密碼，則加密。
			password = String($password.val()).trim();
			length = password.length;
			if (length == 0) {
				$encrypt.val("");
				$password.val("");
			} else {
				$encrypt.val(window.encrypt($random8digits.val(), $account.val(), password));
				password = "";
				for (i = 0; i < length; i++) {
					password += "0";
				}
				$password.val(password);
			}
			
			// 2. 有沒有"記住我"？
			rememberMe();
			
			// 3. 提交表單。
			$("#form1").attr("action", "./login");
			$("#form1").submit();
		});
		
		// 事件(2): 記住我的控件觸發。
		$account.on("blur", function(event) {
			rememberMe();
		});
		$remember.on("click", function(event) {
			rememberMe();
		});

		// 方法(1): 記住我！
		function rememberMe() {
			var jsonValue = null;
			
			if (typeof(window.localStorage) != "undefined") {
				if ($remember.prop("checked")) {
					jsonValue = { 
							"account" : $account.val(),
							"locale" : $locale.val()
					};
					window.localStorage.setItem(STORAGE_LOGIN, JSON.stringify(jsonValue));
				} else {
					window.localStorage.removeItem(STORAGE_LOGIN)
				}
			}			
		}
		
		// 初始化程序。
		// (1) 顯示控件的提示文字。
		hint = $account.next("span").text();
		$account.attr("placeholder", hint);
		$account.attr("title", hint);
		hint = $password.next("span").text();
		$password.attr("placeholder", hint);
		$password.attr("title", hint);
		// (2) 檢查本地儲存，有沒有“記住我”？
		if (typeof(window.localStorage) != "undefined") {
			loginItem = window.localStorage.getItem(STORAGE_LOGIN);
			if (loginItem != null) {
				loginValue = JSON.parse(String(loginItem));
				if (loginValue.account != "undefined") {
					$account.val(loginValue.account);
					$locale.val(loginValue.locale);
					$remember.prop("checked", true);
					$password.focus();
				}
			}
		}
    });
    </script>	
</head>
<body>
	
	
	<div class="top_bar">
        <a class="" href="#"><img src="../img/invSetinvSys_logo.svg" alt="" width="150" height="" class=""></a>
    </div>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-4 col-md-offset-4 align-self-center">
                <div class="login-panel">
                    <h3 class="login_title panel-title">
                       <spring:message text="" code="ui.login.admin.header" />
                    </h3>
                    <div class="panel panel-default">                

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
								<!-- 2.成功訊息區 -->
								<c:if test="${not empty successMsg}">
									<div class="row">
										<div class="col-md-12">
											<div class="alert alert-success">
												<p class="fa fa-warning"><c:out value="${successMsg}"/></p>
											</div>
										</div>
									</div>
								</c:if>  
								                              
								<!-- 用戶輸入區 -->
								<!-- (1) 用戶名稱 -->
								<spring:bind path="account">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.login.account" /><!-- 登錄帳號: -->
									</label>
									<form:input path="account" cssClass="form-control" autofocus="true" autocomplete="false"
											id="account" />
									<span class="hidden">
										<spring:message text="" code="ui.login.account.placeholder" /><!-- 請輸入登錄帳號 -->
									</span>
									<form:errors path="account" cssClass="text-danger" />
								</div>
                                </spring:bind>
                                <!-- (2) 用戶密碼 -->
                                <spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.login.password" /><!-- 登錄密碼: -->
									</label>
									<form:password path="password" cssClass="form-control" 
											id="password" />
									<span class="hidden">
										<spring:message text="" code="ui.login.password.placeholder" /><!-- 請輸入登錄密碼 -->
									</span>
									<form:errors path="password" cssClass="text-danger" />
								</div>
								</spring:bind>
								<!-- (4) 記住我 -->
								<div class="checkbox hide">
									<label class="control-label">
										<input name="remember" type="checkbox" id="remember" />
										<spring:message text="" code="ui.login.remember-me" /><!-- 記住我！ -->
									</label>
								</div>
								<!-- (5) 確認按鍵 -->
								<a id="btnLogin" class="btn btn-lg btn-success btn-block">
									<spring:message text="" code="ui.login.submit" /><!-- 確定登錄 -->
								</a>
								<!-- (6) 亂碼8碼 -->
								<form:hidden path="random8digits" id="random8digits" />
								<!-- (7) 加密後的密碼 -->
								<form:hidden path="encryptPassword" id="encrypt" />
							</fieldset>
						</form:form>                       
                        </div>
                    </div>
                    <div style="text-align: center;"><spring:message text="技術提供：" code="ui.main.technology.provides" /><br><img src="../img/invSet_logo.svg" alt="" width="80%"></div>
                </div>
            </div>
        </div>
    </div><!-- container 結束 -->
	
</body>
</html>