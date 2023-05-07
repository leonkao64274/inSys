<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<h3 class="page-header"><spring:message code="ui.card-scheme-download-setting"/><!--卡段下載設定--></h3>
</div>

<!-- page content -->
			<div id="sitemesh-content">
                <form:form id="form1" action="#" method="POST" modelAttribute="form">
                    <div class="row">
                        <div class="col-md-12">
                            <!-- 資料編輯區外框 -->
                            <div class="panel panel-default">
                                <div class="panel-heading"><spring:message code="ui.card-scheme-download-setting"/>-<spring:message code="btn.update"/></div><!--卡段下載設定-新增  -->
                                <div class="panel-body">
				                    <!-- 1.錯誤訊息區 -->
				                    <c:if test="${not empty errors}">
				                    <div class="row">
				                        <div class="col-md-12">
				                            <div class="alert alert-danger">
				                                <p class="fa fa-warning">
				                                	<c:out value="${errors}"/>
				                                </p>
				                            </div>
				                        </div>
				                    </div>
				                    </c:if>
				                    <!-- 資料編輯區 -->
                      
                             
                   					<div class="row">
		                   				<!--  卡組織 -->
			                            <div class="col-md-6">
			                            	<spring:bind path="cardScheme">
			                                <div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="control-label">
													<spring:message code="ui.card-scheme.name"/><!--卡組織-->
												</label>
												<form:hidden path="cardScheme"/>
												<form:select class="form-control" path="cardScheme" disabled="true"> <!-- 卡組織是唯讀屬性 -->
													<form:option value="">
														<spring:message code="ui.option-select"/><!--請選擇-->
													</form:option>
													<form:options items="${cardSchemeConfigModel}" 
															itemValue="codeId" itemLabel="codeDesc" />
												</form:select>
												<form:errors path="cardScheme" /><!-- 錯誤綁定 -->	
			                                </div>
			                                </spring:bind>
			                            </div>    
		                                
		                       
			                       		<!-- 服務器URL -->
			                            <div class="col-md-6">
			                            	<spring:bind path="serverUrl">
			                                <div class="form-group ${status.error ? 'has-error' : ''}">
			                                 <label class="control-label">
												<spring:message code="ui.serverUrl"/>${req}<!--服務器URL	-->
			                                </label>
												<form:input path="serverUrl" cssClass="form-control"  />
												<form:errors path="serverUrl" /><!-- 錯誤綁定 -->	
			                                </div>
			                                </spring:bind>
			                            </div>    
                       
                       				</div>
                      				<div class="row">
                       
			                       		<!-- 執行類型 -->
			                            <div class="col-md-6">
			                            	<spring:bind path="opRunningType">
			                                <div class="form-group ${status.error ? 'has-error' : ''}">
			                                 <label class="control-label">
												<spring:message code="ui.opRunningType"/>${req}<!--執行類型	-->
			                                </label>
												<form:select class="form-control" path="opRunningType">
													<form:option value="">
														<spring:message code="ui.option-select"/><!--請選擇-->
													</form:option> 
												  	<option value="ALL" <c:if test="${'ALL'==form.opRunningType}">selected</c:if>><spring:message code="ui.opRunningType.ALL"/><!--全部同步	--></option>
												  	<option value="DIF" <c:if test="${'DIF'==form.opRunningType}">selected</c:if>><spring:message code="ui.opRunningType.DIF"/><!--差異同步	--></option> 
												</form:select>
												<form:errors path="opRunningType" /><!-- 錯誤綁定 -->	 
			                                </div>
			                                </spring:bind>
			                            </div>
			                   
			                            
			                       		<!-- 執行週期 -->
			                            <div class="col-md-6">
			                            	<spring:bind path="opDuration">
			                                <div class="form-group ${status.error ? 'has-error' : ''}">
			                                 <label class="control-label">
												<spring:message code="ui.opDuration"/>${req}<!--執行週期	-->
			                                </label>
												<form:input path="opDuration" cssClass="form-control"  maxlength="2" data-type="numeric"/>
												<form:errors path="opDuration" /><!-- 錯誤綁定 -->	
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
                              				  <a href="#" id="btnReset" class="btn btn-default"><spring:message code="btn.reset"/> <!--重設--></a>
                           					  <a href="#" id="btnCancel" class="btn btn-default"><spring:message code="btn.cancel"/> <!--取消--></a>
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
            
            $("#btnCancel").click(function(){
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });
        });
    </script>
</div>