<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>



<!-- page header -->
<div id="sitemesh-header">
	    <spring:message text="" code="ui.system-health"/><!-- 系統健康管理 -->
</div>

<div id="sitemesh-content">
	<div class="row">
		<form:form id="form1" action="#" method="POST" modelAttribute="form">
            <!-- 1.錯誤訊息區 -->
            <c:if test="${not empty errors}">
                <div class="col-md-12">
                    <div class="alert alert-danger">
                        <p class="fa fa-warning">
                        	<c:out value="${errors}"/>
                        </p>
                    </div>
                </div>
            </c:if>	
			<!-- 2.成功訊息區 -->
			<c:if test="${not empty successMsg}">
				<div class="col-md-12">
					<div class="alert alert-success">
						<p class="fa fa-warning">
							<c:out value="${successMsg}"/>
						</p>
					</div>
				</div>
			</c:if>
			            		
			<div class="col-md-12">
				<div class="pull-right">
					<a id="btnAdd" class="btn btn-default" ignore-spin="Y"><spring:message	text="" code="btn.new" /></a>
					<a id="btnEdit" class="btn btn-default" ignore-spin="Y"><spring:message	text="" code="btn.update" /></a>
					<a id="btnCancelEdit" class="btn btn-default hidden" ignore-spin="Y"><spring:message	text="" code="btn.finish" /></a>
				</div>
			</div>
			<div>&nbsp</div>
			<div class="col-md-12 hidden" id="add">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message text="" code="ui.system-health.add" /> <!--查詢條件-->
					</div>

					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<spring:bind path="addSystemName">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">  
											<spring:message text="" code="ui.system-health.system-name"/>${req}<!-- 服務器名稱  --> 
										</label>
										<form:input path="addSystemName" cssClass="form-control" />
										<form:errors path="addSystemName" />
									</div>
								</spring:bind>
							</div>
							<div class="col-md-6">
								<spring:bind path="addSystemUrl">
									<div class="form-group ${status.error ? 'has-error' : ''}">
										<label class="control-label">  
											<spring:message text="" code="ui.system-health.system-url"/>${req}<!-- 服務器位置  -->
										</label>
										<form:input path="addSystemUrl" cssClass="form-control" />
										<form:errors path="addSystemUrl" />
									</div>
								</spring:bind>
							</div>
							<div>&nbsp</div>

							<div class="col-md-12">
								<div class="pull-right">
									<a href="#" id="btnSubmit" class="btn btn-primary"><spring:message
											text="" code="btn.save" /> <!--確認--></a> <a href="#"
										id="btnReset" class="btn btn-default"><spring:message
											text="" code="btn.reset" /> <!--重設--></a> <a href="#"
										id="btnCancel" class="btn btn-default" ignore-spin="Y"><spring:message
											text="" code="btn.cancel" /> <!--回上一頁--></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div>&nbsp;</div>
			<c:forEach var="systemHealth2" items="${form.systemHealth}"
				varStatus="i">
				<div class="col-md-6">
					<c:if test="${systemHealth2.status == 'UP'}">
						<div class="panel panel-success">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-check fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="row">
											<div class="col-md-1 pull-right">
												<form:button id="btn_Delete_${systemHealth2.systemOid}" type="button" class="close hidden"
													aria-label="close" value="${systemHealth2.systemOid}">
													<span aria-hidden="true">&times;</span>
												</form:button>
											</div>

											<%-- 			                        	<form:input path="systemHealth[${i.index}].systemName"/>		                         --%>
										</div>
										<div class="lead">
											<c:out value="${systemHealth2.systemName}" />
										</div>
										<div>Service Avaliable</div>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<p>
											<strong>Memory</strong> <span class="pull-right text-muted">total:
												<fmt:formatNumber pattern="0.##"
													value="${systemHealth2.totalMemory/1024}" /> (MB), free: <fmt:formatNumber
													pattern="0.##" value="${systemHealth2.freeMemory/1024}" />
												(MB), ${systemHealth2.usedMemoryPercentage}% Used
											</span>
										</p>
										<div class="progress progress-striped active">
											<c:if test="${systemHealth2.usedMemoryPercentage < 70}">
												<div class="progress-bar progress-bar-success"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100"
													style="width: ${systemHealth2.usedMemoryPercentage}%">
													<span class="sr-only">&nbsp;</span>
												</div>
											</c:if>
											<c:if test="${systemHealth2.usedMemoryPercentage >= 70}">
												<div class="progress-bar progress-bar-danger"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100"
													style="width: ${systemHealth2.usedMemoryPercentage}%">
													<span class="sr-only">&nbsp;</span>
												</div>
											</c:if>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<p>
											<strong>Disk</strong> <span class="pull-right text-muted">total:
												<fmt:formatNumber pattern="0.##"
													value="${systemHealth2.totalDiskSpace/(1024*1024*1024)}" />
												(GB), free: <fmt:formatNumber pattern="0.##"
													value="${systemHealth2.freeDiskSpace/(1024*1024*1024)}" />
												(GB), ${systemHealth2.usedDiskSpacePercentage}% Used
											</span>
										</p>
										<div class="progress progress-striped active">
											<c:if test="${systemHealth2.usedDiskSpacePercentage < 70}">
												<div class="progress-bar progress-bar-success"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100"
													style="width: ${systemHealth2.usedDiskSpacePercentage}%">
													<span class="sr-only">&nbsp;</span>
												</div>
											</c:if>
											<c:if test="${systemHealth2.usedDiskSpacePercentage >= 70}">
												<div class="progress-bar progress-bar-warning"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100"
													style="width: ${systemHealth2.usedDiskSpacePercentage}%">
													<span class="sr-only">&nbsp;</span>
												</div>
											</c:if>											
										</div>										
									</div>
								</div>								
							</div>
						</div>						
					</c:if>
					<c:if test="${systemHealth2.status != 'UP'}">
						<div class="panel panel-danger">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-ban fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="row">
											<div class="col-md-1 pull-right">
												<form:button id="btn_Delete_${systemHealth2.systemOid}" type="button" class="close hidden"
													aria-label="close" value="${systemHealth2.systemOid}">
													<span aria-hidden="true">&times;</span>
												</form:button>
											</div>

											<%-- 														                        	<form:input path="systemHealth[${i.index}].systemName"/>		                         --%>
										</div>
										<div class="lead">
											<c:out value="${systemHealth2.systemName}" />
											<div>Service Not Avaliable</div>
										</div>
									</div>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<p>
												<strong>Memory</strong> <span class="pull-right text-muted">100%
													used</span>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-danger"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100" style="width: 100%">
													<span class="sr-only">40% Complete (success)</span>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<p>
												<strong>Disk</strong> <span class="pull-right text-muted">100%
													Used</span>
											</p>
											<div class="progress progress-striped active">
												<div class="progress-bar progress-bar-danger"
													role="progressbar" aria-valuenow="40" aria-valuemin="0"
													aria-valuemax="100" style="width: 100%">
													<span class="sr-only">40% Complete (success)</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
				</div>

			</c:forEach>
			<form:hidden id="systemOid" path="systemOid"/>
		</form:form>
	</div>
</div>
<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
    <script type="text/javascript">
        $(document).ready(function () {
        	$("#btnSubmit").click(function(){
        		// 按鈕：新增的存檔
                $("#form1").attr("action", "./health-add");
        		
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
            	$("#add").addClass("hidden");
            	$("#btnEdit").removeClass("hidden");
            	$("#btnAdd").removeClass("hidden");
            });
            
            $("[id^='btn_Delete_']").click(function(){
            	var message = '<spring:message text="" code="ui.confirm.delete"/>';
            	if( confirm(message) ){
                	$("#form1").attr("action", "./health-delete");
            		$("#systemOid").val($(this).val())
            		$('#form1').submit();
            	}
            });
            $("#btnAdd").click(function(){
            	$("#btnAdd").addClass("hidden");
            	$("#add").removeClass("hidden");
            	$("#btnEdit").addClass("hidden");            	
            });
            $("#btnEdit").click(function(){
            	$("#btnAdd").addClass("hidden");
            	$("#btnEdit").addClass("hidden");
            	$("#btnCancelEdit").removeClass("hidden"); 
            	$("[id^='btn_Delete_']").removeClass("hidden");           	
            });            
            $("#btnCancelEdit").click(function(){
            	$("#btnAdd").removeClass("hidden");
            	$("#btnEdit").removeClass("hidden");
            	$("#btnCancelEdit").addClass("hidden"); 
            	$("[id^='btn_Delete_']").addClass("hidden");           	
            });                
        });

    </script>
</div>

