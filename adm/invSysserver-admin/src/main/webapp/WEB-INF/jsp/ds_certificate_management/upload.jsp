<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ds-issuer-cert.Signature" />
	<!--DS 客戶端證書載入-->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<form:form id="form1" action="#" method="POST" modelAttribute="form" enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-12">
				<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.issuer-cert.Signature" />
						<!--簽名證書載入-->
					</div>
					<div class="panel-body">
						<!-- 1.錯誤訊息區 -->
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
						<!-- 2.成功訊息區 -->
						<c:if test="${not empty successMsg}">
							<div class="row">
								<div class="col-md-12">
									<div class="alert alert-success">
										<p class="fa fa-warning">
											<spring:message code="ui.issuer-cert.Signature-certificate-loaded-success" />
										</p>
									</div>
								</div>
							</div>
						</c:if>
						<!-- 2.資料編輯區 -->
						<div class="row">
							<form role="form">
								<!-- (2)卡組織 -->
								<div class="col-md-6">
									<spring:bind path="cardScheme">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="control-label"> <spring:message
													code="ui.card-scheme.name" />${req} <!--卡組織-->
											</label>
											<form:select class="form-control" path="cardScheme">
												<form:option value="">
													<spring:message code="ui.option-select" />
													<!--請選擇-->
												</form:option>
												<form:options items="${cardSchemeConfigModel}"
													itemValue="codeId" itemLabel="codeDesc" />
											</form:select>
											<form:errors path="cardScheme" />
											<!-- 錯誤綁定 -->
										</div>
									</spring:bind>
								<span>&nbsp;</span>
								</div>

								<!-- (3)對應私鑰基碼識別 -->
								<div class="col-md-6">
									<spring:bind path="keyAlias">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="control-label"> <spring:message
													code="ui.issuer-cert.Signature-key-base-code-recognition" />${req}
												<!-- 金鑰基碼識別 -->
											</label>
											<form:input path="keyAlias" cssClass="form-control"
												maxlength="64" />
											<form:errors path="keyAlias" />
										</div>
									</spring:bind>
								<span>&nbsp;</span>
								</div>

								<!-- (4)證書格式 -->
								<div class="col-md-6">
									<div class="form-group ${not empty certEncodeError ? 'has-error' : ''}">
										
										<label class="control-label"> <spring:message
												code="ui.issuer-cert.Signature-certificate-format" />${req} <!--證書格式-->
										</label> <select class="form-control" id="certEncode"
											name="certEncode">
											<option value="0"><spring:message
													code="ui.option-select" /><!--請選擇--></option>
											<option value="1" ${certEncode == 1 ? 'selected' : ''}>PEM</option>
											<option value="2" ${certEncode == 2 ? 'selected' : ''}>DER</option>
										</select>
										<span><c:out value="${certEncodeError}"/>&nbsp;</span>
										
									</div>
								<span>&nbsp;</span>
								</div>

								<!-- (5)開啟證書黨 -->
								<div class="col-md-6">
									<div class="form-group ${not empty fileError ? 'has-error' : ''}">
										<label class="control-label">
										<spring:message code="ui.issuer-cert.Signature-open-the-certificate-file" />${req}
											<!-- 開啟證書檔 -->
										</label> <input type="file" name="file" class="form-control"/>
										<span><c:out value="${fileError}"/>&nbsp;</span>
									</div>
								<span>&nbsp;</span>
								</div>
							</form>
						</div>
						<div class="row">&nbsp;</div>
						<div class="row">
							<!-- button -->
							<div class="col-md-12">
								<div class="form-group ">
									<label class="control-label"></label> <a href="#"
										id="btnSubmit" class="btn btn-primary"><spring:message code="btn.save" /> <!--確認--></a> <a href="./upload" id="btnReset"
										class="btn btn-default"><spring:message code="btn.reset" />
										<!--重設--></a>
										<a href="#" id="btnCancel" class="btn btn-default"><spring:message text="" code="btn.cancel"/> <!--取消--></a>
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
			$("#btnReset").click(function(){
                $('#form1')[0].reset();
            });         
            $("#btnCancel").click(function(){
                $("#form1").attr("action", "./activate");
                $('#form1').submit();
            });
		});
	</script>
</div>