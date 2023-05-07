<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../include/common.jsp"%>

<!-- 網頁：選單標題區。-->
<div id="sitemesh-header">
	<spring:message code="ui.admin-user.management" /><!-- 群組使用者管理 -->
</div>

<!-- 網頁：功能內容區。-->
<div id="sitemesh-content">
	<!-- 使用 Spring 資料綁定機制。-->
	<!-- 1. adminUserCriteriaForm : 提供 "群組" 訊息。 -->
	<!-- 2. entity : 空實體。 -->
	<form:form id="form1" action="#" method="POST" modelAttribute="adminUserForm">
		<div class="row">
			<div class="col-md-12">
				<!-- 這裡使用 "面板" 顯示標題與內容。-->
				<div class="panel panel-default">
				
					<!-- (1) 標題。-->
					<div class="panel-heading">
						<spring:message code="ui.admin-user.data.edit" /><!-- 使用者資料-修改 -->
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
				
						<!-- (2.2) 前台：錯誤訊息區。-->
						<div class="row hidden" id="error">
							<div class="col-md-12">
								<div class="alert alert-danger">
									<p class="fa fa-warning">
										<span id="error-format-style">
											<spring:message text="" code="warn.change-password.format-style" />
											<!-- 密碼請輸入 6 到 12 位英數字！ -->
										</span>
										<span id="error-new-not-equals-confirm">
											<spring:message text="" code="warn.admin-user.password-not-same-confirm" />
											<!-- "密碼"與"確認密碼"不相符，請重新輸入！ -->
										</span>									
		                       		</p>
		                       	</div>
							</div>
						</div>	
												
						<!-- (2.3) 資料編輯區 -->
						<div class="row">	
							<!-- 0. 主鍵 -->
							<form:hidden path="oid"/>
							<!-- 1. 使用者代號 -->
							<div class="col-md-6">
								<spring:bind path="account">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-user.account" />${req}<!-- 使用者代號(*) -->
										</label>
										<form:input path="account" class="form-control" maxlength="20" readonly="true" />
										<form:errors path="account" />
									</div>
								</spring:bind>
							</div>
							<!-- 2. 使用者姓名 -->
							<div class="col-md-6">
								<spring:bind path="userName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-user.user-name" />${req}<!-- 使用者名稱(*) -->
										</label>
										<form:input path="userName" class="form-control" maxlength="100" />
										<form:errors path="userName" />
									</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 3. 群組 -->
                            <div class="col-md-6">
                                <spring:bind path="adminGroup.oid">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.admin-user.group" />${req}<!-- 群組(*) -->
									</label>
									<form:select path="adminGroup.oid" class="form-control">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${adminUserCriteriaForm.configAdminGroupList}" 
												itemValue="oid" itemLabel="groupName" />
									</form:select>
									<form:errors path="adminGroup.oid" />
                                </div>
                                </spring:bind>
                            </div>
							<!-- 4. 部門 -->
							<div class="col-md-6">
								<spring:bind path="department">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.admin-user.department" /><!-- 部門 -->
									</label>
									<form:input path="department" class="form-control" maxlength="100" />
									<form:errors path="department" />
								</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 5. 密碼 -->
							<form:hidden path="encryptPassword" id="encryptPassword"/>
							<div class="col-md-6">
								<spring:bind path="password">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.admin-user.password" /><!-- 密碼(*) -->
									</label>
									<input type="password" class="form-control" maxlength="12" 
											id="password" placeholder='<spring:message code="warn.admin-user.passwoed-size-format" />' />
									<form:errors path="password" />
								</div>
								</spring:bind>
							</div>
							<!-- 6. 確認密碼 -->
							<div class="col-md-6">
								<!-- 注意：無需綁定。-->
								<div class="form-group" id="password-column">
									<label class="control-label"> 
										<spring:message code="ui.admin-user.cnfrpassword" /><!-- 確認密碼(*) -->
									</label>
									<input type="password" class="form-control" maxlength="12" 
											id="cnfrPassword" placeholder='<spring:message code="warn.admin-user.passwoed-size-format" />' />
								</div>
							</div>
						</div>
						<div class="row">
							<!-- 7. 電話 -->
							<div class="col-md-6">
								<spring:bind path="tel">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.admin-user.tel" /><!-- 電話 -->
									</label>
									<form:input path="tel" class="form-control" maxlength="20" />
									<form:errors path="tel" />
								</div>
								</spring:bind>
							</div>
							<!-- 8. 分機 -->
							<div class="col-md-6">
								<spring:bind path="ext">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.admin-user.ext" /><!-- 分機 -->
									</label>
									<form:input path="ext" class="form-control" maxlength="8" data-type="numeric"/>
									<form:errors path="ext" />
								</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 9. 電子郵件 -->
							<div class="col-md-6">
								<spring:bind path="email">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-user.email" /><!-- 電子郵件 -->
										</label>
										<form:input path="email" class="form-control" maxlength="50" />
										<form:errors path="email" />
									</div>
								</spring:bind>
							</div>
						</div>
						
						<!-- (2.4) 空白區。-->
						<div class="row">&nbsp;</div>
						
						<!-- (2.5) 按鍵區。-->
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label> 
									<a href="#" id="btnSubmit" class="btn btn-primary">
										<spring:message code="btn.save" /><!--確認-->
									</a> 
									<a href="#" id="btnReset" class="btn btn-default">
										<spring:message code="btn.reset" /><!--重設-->
									</a> 
									<a href="./query" id="btnBackHistory" class="btn btn-default">
										<spring:message code="btn.cancel" /><!-- 取消 -->
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- 網頁：瀏覽器腳本區。-->
<div id="sitemesh-script">
	<script type="text/javascript">
	var $encryptPassword = $("#encryptPassword");
	var $account = $("#account");
	var $password = $("#password");
	var $cnfrPassword = $("#cnfrPassword");
	//前台錯誤訊息
	var $clean = $("#clean");
	var $error = $("#error");
	var $errorFormatStyle = $("#error-format-style");
	var $errorNewNotEqConfirm = $("#error-new-not-equals-confirm");
	
		$(document).ready(function() {
			$("#btnSubmit").click(function(){
            	var message = '<spring:message text="" code="ui.confirm.save"/>';
            	if( confirm(message) ){
            		if (validate() == true) {
            			$('#form1').submit();
                		return true;// display spin for processing
                	}
            	}
            	return false;// stop spin for processing
            });

			$("#btnReset").click(function() {
				$('#form1')[0].reset();
			});
			$("#password").attr("placeholder","<spring:message text="" code='warn.admin-user.passwoed-size-format'/>")
		});
		
    	// 方法(1): 前台驗證。
		function validate() {
    		
			var password = "";
			var regExpPassword = new RegExp("^[A-Za-z0-9]{6,12}$");
			var i = 0;
			
			
			// 去除空白字符。
			$password.val(String($password.val()).trim());
			$cnfrPassword.val(String($cnfrPassword.val()).trim());

			// 除了舊密碼空白之外，要做驗證。
			password = $password.val();
			length = password.length;
			if (length != 0) {
				
				// 驗證1：新密碼格式
				if (regExpPassword.test($password.val()) == false) {
					$clean.remove();
					$error.removeClass("hidden");
					$error.find("span").addClass("hidden");
					$errorFormatStyle.removeClass("hidden");
					$password.focus();
					return false;
				}
								
				// 驗證2: 密碼 != 驗證密碼password
				if ($password.val() != $cnfrPassword.val()) {
					$clean.remove();
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
			if (length == 0) {
				$encryptPassword.val("");				
			} else {			
				$encryptPassword.val(window.sha1($account.val() + $password.val()));
				password = "";
				for (i = 0; i < length; i++) {
					password += "0";
				}
				$password.val(password);
			}

			return true;
    	}
	
	</script>
</div>