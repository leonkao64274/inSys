<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<spring:message text="" code="trans.log.query" /><!--交易紀錄查詢 -->
</div>

<!-- page content -->
<div id="sitemesh-content">

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

		<!-- 查詢結果 -->
		<div class="row">
			<div class="col-md-12">
			<div class="panel panel-default">
                     <div class="panel-heading">
							<spring:message text="" code="ui.trans.log-detail" /><!--交易紀錄查詢-明細 -->
                     </div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<!-- Nav tabs -->
						<ul class="nav nav-tabs">
							<li class="active"><a href="#transaction"
								data-toggle="tab"><spring:message text="" code="ui.trans.log-trading.message" /></a></li>
							<li><a href="#Message" data-toggle="tab"><spring:message text="" code="ui.trans.log-message.message" /></a></li> 
						</ul>
						<!-- Tab panes -->
						<div class="tab-content">
							<div class="tab-pane fade in active" id="transaction">
								<div class="panel-heading"></div>
								<div class="masonry ">
								
								<jsp:include page="detail-message.jsp"></jsp:include>
								
								
								</div>
							</div>
							<div class="tab-pane fade" id="Message">
								<!-- .panel-heading -->
								<div class="panel-body"> 
									<div class="row">
		                            <div class="col-lg-12">
		                                <div class="table-responsive">
		                                    <table class="table table-striped table-hover">
		                                        <thead>
		                                            <tr class="bg-primary">
		                                                <th class="text-nowrap"><spring:message
													code="ui.trans.log-type.message" /></th>
		                                                <th class="text-nowrap"><spring:message
													code="ui.trans.log-direction.message" /></th>
		                                                <th class="text-nowrap"><spring:message
													code="ui.trans.log-time.message" /></th> 
		                                                <th class="text-nowrap"><spring:message
													code="ui.operation" /></th>
		                                            </tr>
		                                        </thead>
		                                        <tbody>													 
													<c:forEach items="${form.messageLogs}" var="messageLog">
			                                            <tr>
			                                                <td><c:out value="${messageLog.messageType}"/></td>
			                                                <td>
			                                                	<c:if test="${messageLog.direction=='0'}">Incoming</c:if>
	                              								<c:if test="${messageLog.direction=='1'}">Outgoing</c:if>
			                                                </td>
			                                                <td>
																<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${messageLog.createDate}${messageLog.createTime}" />
								                                <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${createTime}"/>
							                                </td> 
			                                                <td>
			                                                    <button class="btn btn-default" onclick="showJSON(${messageLog.oid});" ignore-spin="Y"><spring:message text="" code="ui.trans.log-content.message" /></button>
			                                                </td>
			                                            </tr> 
													</c:forEach>
		                                        </tbody>
		                                    </table>
		                                </div> 
			                        </div>
			                        </div>			 
								</div>
								<!-- .panel-body -->
							</div>
						</div>
					</div> 
				</div> 
				<div class="text-center">
					<a href="./query" class="btn btn-default"><spring:message text="" code="btn.back-history" /><!-- 回上一頁 --></a>
				</div>
			</div>
		</div> 
	<!-- /#page-wrapper -->
	
	<!-- 報文訊息明細 -->
	<c:forEach items="${form.messageLogs}" var="messageLog">
		<div id="myModal_${messageLog.oid}" class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog  modal-lg" role="document"> 
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle"><spring:message
													code="ui.trans.log-detail.message" /></h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body"> 
		     	  <div class="row">
			      <div class="col-md-12">
	                <div class="table-responsive">
	                    <table class="table table-bordered">
	                        <tbody>
	                            <tr>
	                                <td>
	                                    <div class="form-group">
	                                      <textarea rows="20" class="form-control" id="messageData_${messageLog.oid}">
	                                      <admin:ByteArrayToStringTagHandler text="${messageLog.messageData}"/>
	                                      </textarea>  
	                                    </div>
	                                </td>
	                            </tr>
	                        </tbody>    
	                    </table>
	                </div> 
			      </div>
			  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" ignore-spin="Y">Close</button> 
                <button type="button" class="btn btn-sm btn-default" id="btnFormat_${messageLog.oid}" ignore-spin="Y">Format JOSN</button> 
		      </div>
		    </div>
		  </div>
		</div>	
	</c:forEach>
	<!-- /#報文訊息明細 -->
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script type="text/javascript" >	
		$(function(){
 
		});
		
		function showJSON(messageLogOid){
			var modalId = "#myModal_"+messageLogOid;
			$(modalId).modal();	
	        
	        $("#btnFormat_" + messageLogOid).click(function(){
	            $("#messageData_" + messageLogOid).text(
	                    JSON.stringify(JSON.parse($("#messageData_" + messageLogOid).text()),null,2));
	        });
		}
	</script>
</div>