<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 網頁：選單標題區。-->
<div id="sitemesh-header">
	<spring:message code="ui.mect-info.data.management" /><!-- 商家資訊管理 -->
</div>

<!-- 網頁：功能內容區。-->
<div id="sitemesh-content">
	<!-- 使用 Spring 資料綁定機制。-->
	<!-- 1. form : 提供 "群組" 訊息。 -->
	<!-- 2. entity : 空實體。 -->
	<form:form id="form1" action="#" method="POST"  modelAttribute="form">
		<div class="row">
			<div class="col-md-12">
				<!-- 這裡使用 "面板" 顯示標題與內容。-->
				<div class="panel panel-default">
				
					<!-- (1) 標題。-->
					<div class="panel-heading">
						<spring:message code="ui.mect-info.data.edit" /><!-- 商家資訊管理-修改 -->
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
							<!-- 1. 商店代號 -->
							<div class="col-md-6">
								<spring:bind path="acquirerMerchantId">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.mect-info.account" />${req}<!-- 商店代號(*) -->
										</label>
										<form:input path="acquirerMerchantId" class="form-control" maxlength="35" readonly="true"/>
										<form:errors path="acquirerMerchantId" />
									</div>
								</spring:bind>
							</div>
							<!-- 2. 商店URL -->
							<div class="col-md-6">
								<spring:bind path="invSysRequestorUrl">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.mect-info.url" />${req}<!-- 商店URL(*) -->
										</label>
										<form:input path="invSysRequestorUrl" class="form-control" maxlength="2048" />
										<form:errors path="invSysRequestorUrl" />
									</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 3. Mcc  -->
							<div class="col-md-6">
								<spring:bind path="mcc">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.mect-info.mcc" />${req}<!-- Mcc -->
									</label>
									<form:input path="mcc" class="form-control" maxlength="4"/>
									<form:errors path="mcc" />
								</div>
								</spring:bind>
							</div>
							<!-- 4.商店名稱 -->
							<div class="col-md-6">
								<spring:bind path="merchantName">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.mect-info.merchantName" />${req}<!--商店名稱 -->
									</label>
									<form:input path="merchantName" class="form-control" maxlength="40"/>
									<form:errors path="merchantName" />
								</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 5. 商店國家代碼 -->
                            <div class="col-md-6">
                                <spring:bind path="merchantCountryCode">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.mect-info.merchantCountryCode" />${req}<!-- 商店國家代碼(*) -->
									</label>
									<form:select class="form-control" path="merchantCountryCode">
										<form:option value="158">158（台灣）</form:option> <!-- 158(Taiwan) -->
										</form:select>
									<form:errors path="merchantCountryCode" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
                            </div>
							<!-- 6. 幣別代碼 -->
							<div class="col-md-6">
                                <spring:bind path="purchaseCurrency">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.mect-info.purchaseCurrency" />${req}<!-- 幣別代碼(*) -->
									</label>
									<form:select class="form-control" path="purchaseCurrency">
										<form:option value="901">901（TWD）</form:option>
										</form:select>
									<form:errors path="purchaseCurrency" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
							</div>
						</div>
						<div class="row">
							<!-- 7. 貨幣指數 -->
							<div class="col-md-6">
                                <spring:bind path="purchaseExponent">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.mect-info.purchaseExponent" />${req}<!-- 貨幣指數(*) -->
									</label>
									
									<form:select class="form-control" path="purchaseExponent">
										<form:option value="2">2</form:option>
										</form:select>
									<form:errors path="purchaseExponent" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
							</div>
							<!-- 8. 商店密碼 -->
							<div class="col-md-6">
								<spring:bind path="vMerchantPwd">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.mect-info.vMerchantPwd" /><!--商店密碼 -->
									</label>
									<form:input path="vMerchantPwd" class="form-control" maxlength="16" />
									<form:errors path="vMerchantPwd" />
								</div>
								</spring:bind>
							</div>
						</div>	
						<div class="row">
							<div class="col-md-6">
								<spring:bind path="mMerchantPwd">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.mect-info.mMerchantPwd" /><!--商店密碼 -->
									</label>
									<form:input path="mMerchantPwd" class="form-control" maxlength="16" />
									<form:errors path="mMerchantPwd" />
								</div>
								</spring:bind>
							</div>
							<div class="col-md-6">
								<spring:bind path="jMerchantPwd">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label"> 
										<spring:message code="ui.mect-info.jMerchantPwd" /><!--商店密碼 -->
									</label>
									<form:input path="jMerchantPwd" class="form-control" maxlength="16" />
									<form:errors path="jMerchantPwd" />
								</div>
								</spring:bind>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<spring:bind path="cMerchantPwd">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label"> 
											<spring:message code="ui.mect-info.cMerchantPwd" /><!--商店密碼 -->
										</label>
										<form:input path="cMerchantPwd" class="form-control" maxlength="16" />
										<form:errors path="cMerchantPwd" />
									</div>
								</spring:bind>
							</div>
						
						
							<!-- 9. 商店狀態 -->
							<div class="col-md-6">
                                <spring:bind path="merStatus">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.mect-info.acquirerStatus" />${req}<!---- 商店狀態-->
									</label>
									<form:select class="form-control" path="merStatus">
										<form:option value="A">啟用（A）</form:option>
										<form:option value="L">停用（L）</form:option>
										</form:select>
									<form:errors path="merStatus" />
									<!-- 錯誤綁定 -->
                                </div>
                                </spring:bind>
							</div>
						</div>
						
						<!-- 空白區 -->
						<div class="row">&nbsp;</div>					
						<!--  按鍵區 -->
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
									<a href="./query" class="btn btn-default">
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


