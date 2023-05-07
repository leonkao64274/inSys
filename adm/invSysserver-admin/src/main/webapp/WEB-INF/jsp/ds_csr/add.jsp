<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message code="ui.ds-client-certificate-quest"/><!--DS 客戶端證書請求製作-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="form">
		<div class="row">
			<div class="col-lg-12">
			<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message code="ui.ds-certificate-quest.data"/><!--DS 客戶端證書請求資料-->
		          	</div>
					<div class="panel-body">
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
						<!-- 資料編輯區 -->
						<div class="row">		                  

							<!-- (2)卡組織 -->
							<div class="col-md-6">
								<spring:bind path="cardScheme">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.card-scheme.name"/>${req}<!--卡組織-->
									</label>
									<form:select class="form-control" path="cardScheme">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${cardSchemeConfigModel}" 
												itemValue="codeId" itemLabel="codeDesc" />
									</form:select>
									<form:errors path="cardScheme" /><!-- 錯誤綁定 -->	
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (3)密鑰基碼識別 -->
							<div class="col-md-6">
								<spring:bind path="keyAlias">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
									<spring:message code="ui.certificate-quest.key-alias"/>${req}<!--密鑰基碼識別*-->
									</label>
									<form:input class="form-control" path="keyAlias"/>
									<form:errors path="keyAlias" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (4)密鑰長度 -->
							<div class="col-md-6">
							<spring:bind path="keyLength">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.key-length"/>${req}<!--密鑰長度*-->
									</label>
									<form:select class="form-control" path="keyLength">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
                                        <form:option value="256">256 bits (ECDSA)</form:option>
										<form:option value="1024">1024 bits (RSA)</form:option>
										<form:option value="2048">2048 bits (RSA)</form:option>
									</form:select>
									<form:errors path="keyLength" /><!-- 錯誤綁定 -->	
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (5)簽章演算法* -->
							<div class="col-md-6">
								<spring:bind path="signAlgorithm">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.sign_algorithm"/>${req}<!--簽章演算法*-->
									</label>
									<form:select class="form-control" path="signAlgorithm">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="SHA1withRSA">SHA1withRSA</form:option>
										<form:option value="SHA256withRSA">SHA256withRSA</form:option>
										<form:option value="SHA1withECDSA">SHA1withECDSA</form:option>
										<form:option value="SHA256withECDSA">SHA256withECDSA</form:option>
									</form:select>
									<form:errors path="signAlgorithm" /><!-- 錯誤綁定 -->	
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (6)網域名稱(CN)* -->
							<div class="col-md-6">
							    <spring:bind path="commonName">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.common-name"/>${req}<!--網域名稱(CN)*-->
									</label>		                              
									<form:input class="form-control" path="commonName"/>
									<form:errors path="keyLength" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (7)組織(O)* -->
							<div class="col-md-6">
								<spring:bind path="organization">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.organization"/>${req}<!--組織(O)*-->
									</label>		                              
									<form:input class="form-control" path="organization"/>
									<form:errors path="organization" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (8)組織單位(OU)* -->		                  
							<div class="col-md-6">
								<spring:bind path="organizationUnit">	
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.organization_unit"/>${req}<!--組織單位(OU)*-->
									</label>		                              
									<form:input class="form-control" path="organizationUnit"/>
									<form:errors path="organizationUnit" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>    
							</div>
							<!-- (9)國家(C)* -->	
							<div class="col-md-6 ">
								<spring:bind path="country">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.country"/>${req}<!--國家(C)*-->
									</label>
								<spring:message text="" code="ui.certificate-placeholder.country" var="var_placeholder_country"/>			                              
								<form:input class="form-control" path="country" placeholder="${var_placeholder_country}" />
								<form:errors path="country" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (10)省份(ST) -->	
							<div class="col-md-6">
								<spring:bind path="province">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.province"/><!--省份(ST)-->
									</label>
									<spring:message text="" code="ui.certificate-placeholder.alphabetic" var="var_placeholder_alphabetic"/>		                              
									<form:input class="form-control" path="province" placeholder="${var_placeholder_alphabetic}" />
									<form:errors path="province" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>
							<!-- (11)地區(L) -->	
							<div class="col-md-6">
								<spring:bind path="locality">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.locality"/><!--地區(L)-->
									</label>		                              
									<form:input class="form-control" path="locality" placeholder="${var_placeholder_alphabetic}" />
									<form:errors path="locality" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>    
							</div>
							<!-- (11)文件名* -->
							<div class="col-md-6">
								<spring:bind path="fileName">
								<div class="form-group ${status.error ? 'has-error' : ''}" >
									<label class="control-label">
										<spring:message code="ui.certificate-quest.file-name"/>${req}<!--文件名*-->
									</label>		                              
                                        <form:input class="form-control" path="fileName" placeholder="${var_placeholder_alphabetic}" size="100" />
									<form:errors path="fileName" /><!-- 錯誤綁定 -->
							<span>&nbsp;</span>
								</div>
								</spring:bind>
							</div>		                  
						</div>
					<div class="row">&nbsp;</div>
					<div class="row">
						<!-- button -->
						<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label>
									<a href="#" id="btnSubmit" class="btn btn-primary"><spring:message code="btn.save"/> <!--確認--></a>
									<a href="#" id="btnReset" class="btn btn-default"><spring:message code="btn.reset"/><!--重設--></a>
									<a href="./query"  class="btn btn-default"><spring:message code="btn.cancel"/><!--取消--></a>
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
	    $(document).ready(function () {
		    $("#btnSubmit").click(function(){
		    	if (!confirm('<spring:message text="" code="ui.confirm.save"/>')) {
	                return false;
	            }
		        $('#form1').submit();
	  	    });
            $("#btnReset").click(function(){
            	$('#form1')[0].reset();
            });
            
          //選取签章演算法，篩選密鑰長度
            $("#signAlgorithm").change(function(){
            	var selectedVal = $("#signAlgorithm option:selected").val();
				if(selectedVal == "SHA1withRSA"||selectedVal == "SHA256withRSA"){
					if($("#span2048").size()>0 && $("#span1024").size()>0){
						$("#keyLength option[value='1024']").unwrap();
						$("#keyLength option[value='2048']").unwrap();
					}
					if($("#span256").size()>0){
					}else{
						$("#keyLength option[value='256']").wrap("<span id='span256' style='display:none'></span>");						
					}
					
				}else if(selectedVal == "SHA1withECDSA"||selectedVal == "SHA256withECDSA"){
					
					if($("#span2048").size()>0 && $("#span1024").size()>0){
					}else{
						$("#keyLength option[value='1024']").wrap("<span id='span1024' style='display:none'></span>");
						$("#keyLength option[value='2048']").wrap("<span id='span2048' style='display:none'></span>");
					}
					if($("#span256").size()>0){
						$("#keyLength option[value='256']").unwrap();					
					}
					
				}else{
					if($("#span2048").size()>0 && $("#span1024").size()>0){
						$("#keyLength option[value='1024']").unwrap();
						$("#keyLength option[value='2048']").unwrap();
					}
					if($("#span256").size()>0){
						$("#keyLength option[value='256']").unwrap();					
					}
				}
            });
            
            //選取密鑰長度，篩選簽章演算法
            $("#keyLength").change(function(){
            	var selectedVal = $("#keyLength option:selected").val();

				if(selectedVal == "2048"||selectedVal == "1024"){
					if($("#span1RSA").size()>0 && $("#span256RSA").size()>0){
						$("#signAlgorithm option[value='SHA1withRSA']").unwrap();
						$("#signAlgorithm option[value='SHA256withRSA']").unwrap();
					}
					if($("#span1ECDSA").size()>0 && $("#span256ECDSA").size()>0){
					}else{
						$("#signAlgorithm option[value='SHA1withECDSA']").wrap("<span id='span1ECDSA' style='display:none'></span>");
						$("#signAlgorithm option[value='SHA256withECDSA']").wrap("<span id='span256ECDSA' style='display:none'></span>");
					}
					
				}else if(selectedVal == "256"){
					
					if($("#span1RSA").size()>0 && $("#span256RSA").size()>0){
					}else{
						$("#signAlgorithm option[value='SHA1withRSA']").wrap("<span id='span1RSA' style='display:none'></span>");
						$("#signAlgorithm option[value='SHA256withRSA']").wrap("<span id='span256RSA' style='display:none'></span>");
					}
					if($("#span1ECDSA").size()>0 && $("#span256ECDSA").size()>0){
						$("#signAlgorithm option[value='SHA1withECDSA']").unwrap();					
						$("#signAlgorithm option[value='SHA256withECDSA']").unwrap();					
					}
					
				}else{
					if($("#span1ECDSA").size()>0 && $("#span256ECDSA").size()>0){
						$("#signAlgorithm option[value='SHA1withECDSA']").unwrap();					
						$("#signAlgorithm option[value='SHA256withECDSA']").unwrap();					
					}
					if($("#span1RSA").size()>0 && $("#span256RSA").size()>0){
						$("#signAlgorithm option[value='SHA1withRSA']").unwrap();
						$("#signAlgorithm option[value='SHA256withRSA']").unwrap();
					}
				}
            });
            
	     });
	</script>
</div>