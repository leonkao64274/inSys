<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message text="" code="ui.kek"/><!--金鑰加密金鑰(Key Encryption Key)設定-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="form">
    <div class="row">
        <div class="col-md-12">
            <!-- 資料編輯區外框 -->
            <div class="panel panel-default">
                <div class="panel-heading">
                	<spring:message text="" code="ui.kek.editor-title"/><!--金鑰加密金鑰設定-->
                </div>
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
                    <!-- 2.資料編輯區 -->
                    <div class="row">
                        <form role="form">
                            <!-- (2)密鑰基碼識別 -->
                            <div class="col-md-6">
                                <spring:bind path="keyAlias">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="control-label">
                                    	<spring:message text="" code="ui.kek.key-alias"/><!-- 密鑰基碼識別 -->
                                    </label>
                                    <form:input path="keyAlias" cssClass="form-control" maxlength="64" />
                                    <form:errors path="keyAlias" />
                                    <span>&nbsp;</span>
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (3)演算法 -->
                            <div class="col-md-6">
                            	<spring:bind path="algorithm">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.kek.alg"/><!--卡組織-->
									</label>
									<form:select class="form-control" path="algorithm">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="DESede">DESede</form:option>
									</form:select>
									<form:errors path="algorithm" /><!-- 錯誤綁定 -->	
									<span>&nbsp;</span>
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (3)金鑰長度 -->
                            <div class="col-md-6">
                                <spring:bind path="keySize">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label class="control-label">
                                    	<spring:message text="" code="ui.kek.key-size"/><!-- 金鑰長度 -->
                                    </label>
									<form:select class="form-control" path="keySize">
										<form:option value="">
											<spring:message text="" code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:option value="192">192</form:option>
									</form:select>
                                    <form:errors path="keySize" />
                                    <span>&nbsp;</span>
                                </div>
                                </spring:bind>
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
                                <a href="#" id="btnReset" class="btn btn-default"><spring:message text="" code="btn.reset"/> <!--重設--></a>
                                <a href="#" id="btnCancel" class="btn btn-default"><spring:message text="" code="btn.cancel"/> <!--回上一頁--></a>
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
            	var message = '<spring:message text="" code="ui.confirm.save"/>';
            	if( confirm(message) ){
            		$('#form1').submit();
            		return true;// display spin for processing
            	}
            	return false;// stop spin for processing
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