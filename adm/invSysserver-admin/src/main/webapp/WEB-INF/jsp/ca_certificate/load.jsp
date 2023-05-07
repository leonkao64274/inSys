<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ca-cert-certificate"/><!-- 信任 CA 證書載入 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="form" enctype="multipart/form-data">
		<div class="row">
			<div class="col-md-12">
				<!-- 資料編輯區外框 -->
				<div class="panel panel-default">
					<div class="panel-heading"><spring:message text="" code="ui.ca-cert-certificate"/><!-- 信任 CA 證書載入 --></div>
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
			            <!-- 資料編輯區 -->
			            <div class="row">
			                <form role="form">	
			                	<div class="col-md-12">
				                	<div class="row">			                		
										<!--(1)識別名稱-->	                    
					                    <div class="col-md-6">
						                    <spring:bind path="certAlias">
						                        <div class="form-group ${status.error ? 'has-error' : ''}">
						                            <label class="control-label">
														<spring:message text="" code="ui.ca-cert-certificate.cert-alias"/>${req}<!--識別名稱-->
						                            </label>
						                            <form:input class="form-control" path="certAlias"/>
						                            <span>&nbsp;</span>
						                        	<form:errors path="certAlias" /><!-- 錯誤綁定 -->            	
						                        </div>
						                    </spring:bind>
					                    </div>
					                    <!--(2)證書格式-->
					                    <div class="col-md-6">
											<spring:bind path="certAlias">
						                        <div class="form-group ${not empty certEncodeError ? 'has-error' : ''}">
						                            <label class="control-label">
						                            <spring:message text="" code="ui.ca-cert-certificate-cert-encode"/>${req}<!--證書格式-->
						                            </label>
						                            <select class="form-control" id="certEncode" name="certEncode">
						                            	<option value="0"><spring:message text="" code="ui.option-select" /><!--請選擇--></option>
						                                <option value="1" ${certEncode == 1 ? 'selected' : ''}>PEM</option>
						                                <option value="2" ${certEncode == 2 ? 'selected' : ''}>DER</option>
						                            </select>
													 <span><c:out value="${certEncodeError}"/>&nbsp;</span>
						                            
						                        </div>
						                    </spring:bind>
					                    </div>
				                    </div>			                   
				                    <div class="row">
					                    <!--(3)開啟證書檔-->
					                    <div class="col-md-6">
					                        <div class="form-group ${not empty fileError ? 'has-error' : ''}">
					                            <label class="control-label">
					                            	<spring:message text="" code="ui.ca-cert-certificate-cert-content"/>${req}<!--開啟證書檔-->
					                            </label>
					                            <input type="file" name="file" class="form-control">		                            
									 			<span><c:out value="${fileError}"/>&nbsp;</span>
					                        </div>
					                    </div>
					                </div>
				                </div>
			                </form>
			            </div>
						<div class="row">&nbsp;</div>
						<div class="row">
							<!-- button -->
							<div class="col-md-12">
								<div class="form-group">
									<label class="control-label"></label>
									<a href="#" id="btnSubmit" class="btn btn-primary"><spring:message text="" code="btn.save"/> <!--確認--></a>
									<a href="#" id="btnReset" class="btn btn-default"><spring:message text="" code="btn.reset"/><!--重設--></a>
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

    });
	</script>   
</div>