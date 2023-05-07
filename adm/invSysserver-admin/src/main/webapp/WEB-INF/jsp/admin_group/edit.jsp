<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../include/common.jsp"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.admin-group.management" /><!--群組管理-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="entity">
		<div class="row">
			<div class="col-md-12">
				<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.admin-group.data.edit" /><!--群組資料 - 修改-->
					</div>
					<div class="panel-body">
						<!-- 後台：錯誤訊息區 -->
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
						<div class="row">
							<!-- (0)實體主鍵 -->
							<form:hidden path="oid" />
							<!-- (1)群組代碼 -->
							<div class="col-md-6">
								<spring:bind path="groupId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-group.id" />${req}<!-- 群組代號 -->
										</label>
										<form:input path="groupId" class="form-control" maxlength="20" readonly="true" />
										<form:errors path="groupId" />
									</div>
								</spring:bind>
							</div>
							<!-- (2)群組名稱 -->
							<div class="col-md-6">
								<spring:bind path="groupName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-group.name" />${req}<!-- 群組名稱 -->
										</label>
										<form:input path="groupName" class="form-control" maxlength="100" />
										<form:errors path="groupName" />
									</div>
								</spring:bind>
							</div>
							<!-- (3)說明 -->
							<div class="col-md-6">
								<spring:bind path="description">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.admin-group.description" />${req}<!-- 說明 -->
										</label>
										<form:input path="description" class="form-control" maxlength="100" />
										<form:errors path="description" />
									</div>
								</spring:bind>
							</div>
						</div>
						
						<!-- 以下迴圈：一對多的子實體集合。用途：用戶勾選本功能群組設定的"權限"(多選)使用。-->
						<c:forEach items="${adminGroupPrivilegeForm.functions}" var="menuList" varStatus="listStatus">
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-info">
									<!-- (1)模組級功能選單名稱 -->
									<div class="panel-heading">
										
										<spring:message code="${adminGroupPrivilegeForm.modules[listStatus.index].i18nCode}" text=""/>
									<span style="float:right;" >
											<input type="checkbox" data-id="${listStatus.index}" data-checkByGroup="true"/><spring:message code="btn.select.all" text=""/>
										</span>
									</div>
									<!-- (2)功能級功能選單名稱集合 -->
									<div class="panel-body row" id="panelBody_${listStatus.index}">
										<!-- 1.每一個功能選單，用一個CHECK-BOX顯示。-->
										<c:forEach items="${menuList}" var="menuItem">
										<div class="form-group col-md-3">
											<div class="checkbox">
												<label>
													<input type="checkbox" name="privileges" value="${menuItem.accessId}" 
															class="privileges" ${menuItem.mark == true ? "checked" : ""} /> 
													
													<spring:message code="${menuItem.i18nCode}" text=""/>
												</label>
											</div>	
										</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>

						<!-- 空白區 -->
						<div class="row">&nbsp;</div>
						
						<!-- 按鍵區 -->
						<div class="row">
							<!-- button -->
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label> 
									<a href="#" id="btnSubmit" class="btn btn-primary"><spring:message code="btn.save" /><!--確認--></a> 
									<a href="#" id="btnReset" class="btn btn-default"><spring:message code="btn.reset" /><!--重設--></a> 
									<a href="./query" id="btnBackHistory" class="btn btn-default"><spring:message code="btn.cancel" /><!-- 取消 --></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="privileges" value="e" />
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript">
		$(document).ready(function() {
		
			var $privileges = $(".privileges");			// "權限"選項。
			var $error = $("#error_must_choose_one");	// 前台錯誤訊息區。
			
			$("#btnSubmit").click(function() {

				// 2. 成功=提交, 失敗=警告。
					if (!confirm('<spring:message text="" code="ui.confirm.save"/>')) {
		                return false;
		            }
					$("#form1").submit();				
			});
			
			$("#btnReset").click(function() {
				$('#form1')[0].reset();
			});	
			
			$privileges.on("change", function(event){
				if ($error.hasClass("hide") == false) {
					$error.addClass("hide");	
				}
			});
			
			//checkbox:全選,全不選
			$("[data-checkByGroup='true']").on("click",function () {
				 if ( $(this).is(':checked')) {
			            $('#panelBody_'+ $(this).data("id") +' :checkbox').prop('checked', true);
				 }else{
			            $('#panelBody_'+ $(this).data("id") +' :checkbox').prop('checked', false);
				 }
		    });
			
		});
	</script>
</div>