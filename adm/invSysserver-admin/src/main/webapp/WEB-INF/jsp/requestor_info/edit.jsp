<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 網頁：選單標題區。-->
<div id="sitemesh-header">
	<spring:message code="ui.requestor-info.data.management" /><!-- 業者資訊管理 -->
</div>

<!-- 網頁：功能內容區。-->
<div id="sitemesh-content">
	<!-- 使用 Spring 資料綁定機制。-->
	<!-- 1. form : 提供 "群組" 訊息。 -->
	<!-- 2. from : 空實體。 -->
	<form:form id="form1" action="#" method="POST" modelAttribute="form">
		<div class="row">
			<div class="col-md-12">
				<!-- 這裡使用 "面板" 顯示標題與內容。-->
				<div class="panel panel-default">
				
					<!-- (1) 標題。-->
					<div class="panel-heading">
						<spring:message code="ui.requsetor-info.data.edit" /><!--業者資訊--新增-->
						          
					</div>
				
					<!-- (2) 內容。-->
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
						<div class="row">	
							<!-- 1. 業者代號 -->
							<div class="col-md-6">
								<spring:bind path="requestorId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.requestor-info.account" />${req} <!-- 業者代號(*) -->
										</label>
										<form:input path="requestorId" class="form-control" maxlength="35" readonly="true" />
										<form:errors path="requestorId" />
									</div>
								</spring:bind>
							</div>
							<!-- 2. 業者名稱 -->
							<div class="col-md-6">
								<spring:bind path="requestorName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.requestor-info.requestorName" />${req}<!-- 業者URL(*) -->
										</label>
										<form:input path="requestorName" class="form-control" maxlength="2048" />
										<form:errors path="requestorName" />
									</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 3. 業者密碼-->
							<div class="col-md-6">
								<spring:bind path="requestorPassword">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.requestor-info.pwd" />${req}<!--業者密碼 -->
									</label>
									<form:input path="requestorPassword" class="form-control" maxlength="128" />
									<form:errors path="requestorPassword" />
								</div>
								</spring:bind>
							</div>
							<!-- 4. Visa啟用 -->
							<div class="col-md-6">
		                 		<spring:bind path="enableVisa">
	                                <div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.enableVisa" />${req}<!---- 業者狀態-->
										</label>
										<form:select class="form-control" path="enableVisa">
											<form:option value="1">啟用</form:option>
											<form:option value="0">停用</form:option>
											</form:select>
										<form:errors path="enableVisa" />
										<!-- 錯誤綁定 -->
	                                </div>
                                </spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 5. master啟用-->
                            <div class="col-md-6">
		                 		<spring:bind path="enableMastercard">
	                                <div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.enableMastercard" />${req}<!---- 業者狀態-->
										</label>
										<form:select class="form-control" path="enableMastercard">
											<form:option value="1">啟用</form:option>
											<form:option value="0">停用</form:option>
											</form:select>
										<form:errors path="enableMastercard" />
										<!-- 錯誤綁定 -->
	                                </div>
                                </spring:bind>
                            </div>
							<!-- 6. 開啟JCB-->
							<div class="col-md-6">
		                 		<spring:bind path="enableJCB">
	                                <div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.enableJCB" />${req}<!---- 業者狀態-->
										</label>
										<form:select class="form-control" path="enableJCB">
											<form:option value="1">啟用</form:option>
											<form:option value="0">停用</form:option>
											</form:select>
										<form:errors path="enableJCB" />
										<!-- 錯誤綁定 -->
	                                </div>
                                </spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 7. 開啟Discover-->
							<div class="col-md-6">
		                 		<spring:bind path="enableDiscover">
	                                <div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.enableDiscover" />${req}<!---- 業者狀態-->
										</label>
										<form:select class="form-control" path="enableDiscover">
											<form:option value="1">啟用</form:option>
											<form:option value="0">停用</form:option>
											</form:select>
										<form:errors path="enableDiscover" />
										<!-- 錯誤綁定 -->
	                                </div>
                                </spring:bind>
							</div>
							<!-- 8. 開啟Cup -->
							<div class="col-md-6">
		                 		<spring:bind path="enableCup">
	                                <div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">
											<spring:message code="ui.requestor-info.enableCup" />${req}<!---- 業者狀態-->
										</label>
										<form:select class="form-control" path="enableCup">
											<form:option value="1">啟用</form:option>
											<form:option value="0">停用</form:option>
											</form:select>
										<form:errors path="enableCup" />
										<!-- 錯誤綁定 -->
	                                </div>
                                </spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 9. 開啟ae -->
                            <div class="col-md-6">
                                <spring:bind path="enable_ae">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.requestor-info.enable_ae" />${req}<!---- 業者狀態-->
									</label>
									<form:select class="form-control" path="enable_ae">
										<form:option value="1">啟用</form:option>
										<form:option value="0">停用</form:option>
										</form:select>
									<form:errors path="enable_ae" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
                            </div>
                            <div class="col-md-6">
                                <spring:bind path="enable">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.requestor-info.enable" />${req}<!---- 業者狀態-->
									</label>
									<form:select class="form-control" path="enable">
										<form:option value="1">啟用</form:option>
										<form:option value="0">停用</form:option>
										</form:select>
									<form:errors path="enable" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
                            </div>
                        </div>

						
						<!-- 按鍵區-->
						<div class="row">
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
		$(document).ready(function() {
			$("#btnSubmit").click(function() {
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
