<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
    <spring:message code="ui.ds-client-certificate-quest"/><!--簽名證書請求製作-->
</div>

<!-- page content -->
<div id="sitemesh-content">
    <form:form id="form1" action="#" method="POST" modelAttribute="certificateRequestCriteriaForm">
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
		
	    <div class="row">
		    <div class="col-lg-12">
		        <div class="panel panel-default">
		            <div class="panel-heading">
		                <spring:message code="ui.search-criteria" /><!-- 查詢條件 -->
		            </div>
		            <div class="panel-body">
		                <div class="row">
<!--                         	(1)發卡銀行 -->
<!--                             <div class="col-md-3"> -->
<%--                                 <spring:bind path="criteriaIssuerOid"> --%>
<%--                                 <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 									<label class="control-label"> -->
<%-- <%-- 										<spring:message code="ui.issuer.name"/><!--發卡銀行--> --%>
<!-- 									</label> -->
<%-- 									<form:select class="form-control" path="criteriaIssuerOid"> --%>
<%-- 										<form:option value=""> --%>
<%-- 											<spring:message code="ui.option-select"/><!--請選擇--> --%>
<%-- 										</form:option> --%>
<%-- 										<form:options items="${issuerConfigModel}"  --%>
<%-- 												itemValue="oid" itemLabel="issuerName" /><!--發卡銀行訊息--> --%>
<%-- 									</form:select> --%>
<%-- 									<form:errors path="criteriaIssuerOid" /><!-- 錯誤綁定 --> --%>
<!-- 								</div> -->
<%-- 								</spring:bind> --%>
<!--                             </div> -->
                            <!-- (2)卡組織 -->
                            <div class="col-md-3">
                            	<spring:bind path="criteriaCardScheme">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message code="ui.card-scheme.name"/><!--卡組織-->
									</label>
									<form:select class="form-control" path="criteriaCardScheme">
										<form:option value="">
											<spring:message code="ui.option-select"/><!--請選擇-->
										</form:option>
										<form:options items="${cardSchemeConfigModel}" 
												itemValue="codeId" itemLabel="codeDesc" />
									</form:select>
									<form:errors path="criteriaCardScheme" /><!-- 錯誤綁定 -->	
                                </div>
                                </spring:bind>
                            </div>
                            <!-- (3)網域名稱(CN)-->
		                    <div class="col-md-3">
                            	<spring:bind path="criteriaCommonName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
		                            <label>
										<spring:message code="ui.certificate-quest.common-name"/><!--網域名稱(CN)-->
		                            </label>		                            
		                            <form:input class="form-control" path="criteriaCommonName"/>
		                            <form:errors path="criteriaCommonName" /><!-- 錯誤綁定 -->			                            
		                        </div>
		                        </spring:bind>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="row">
		    <div class="col-lg-12">
		        <div class="pull-right">
                    <a href="#" id="btnQuery" class="btn btn-primary"><spring:message code="btn.query" /> <!--查詢--></a>
                    <a href="./add" id="btnAdd" class="btn btn-default"><spring:message code="btn.new" /> <!--新增--></a>
		        </div>
		    </div>
		</div>
		
		<div style="height: 40px;"></div>
		
		<!-- 查詢結果 -->
		<div class="row">
		    <div class="col-lg-12">
		        <div class="table-responsive">
		            <table class="table table-striped table-hover">
		                <thead>
		                    <tr class="bg-primary">
<%-- 		                        <th class="text-nowrap"><spring:message code="ui.issuer.bank"/><!--銀行--></th> --%>
		                        <th class="text-nowrap"><spring:message code="ui.card-scheme.name"/><!--卡組織--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.key-alias"/><!--密鑰基碼識別--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.key-length"/><!--密鑰長度--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.common-name"/><!--網域名稱(CN)--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.organization"/><!--組織(O)--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.organization_unit"/><!--組織單位(OU)--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.country"/><!--國家--></th>
		                        <th class="text-nowrap"><spring:message code="ui.certificate-quest.enroll-status"/><!--憑證申請狀態--></th>
		                        <th class="text-nowrap"><spring:message code="ui.operation"/><!--執行選項--></th>
		                    </tr>
		                </thead>
		                <tbody>
                        	<c:forEach items="${queryResult.content}" var="certificateRequest">		                
		                    <tr>
<%-- 		                        <td>${certificateRequest.issuer.issuerName}</td> --%>
		                        <td><spring:message code="CARD_SCHEME.${certificateRequest.cardScheme}"/><!-- 卡組織 --></td>
		                        <td><a href="./detail?oid=<c:out value="${certificateRequest.oid}"/>" >${certificateRequest.keyAlias}</a></td>
		                        <td>${certificateRequest.keyLength}</td>
		                        <td>${certificateRequest.commonName}</td>
		                        <td>${certificateRequest.organization}</td>
		                        <td>${certificateRequest.organizationUnit}</td>
		                        <td>${certificateRequest.country}</td>
		                        <td><spring:message code="ENROLL.${certificateRequest.enrollStatus}"/><!-- 憑證申請狀態 --></td>		                        
		                        <td>
		                        	<!-- 下載按鈕 生成時判斷申請狀態  未申請時disabled=false 已申請時按鍵disabled=true 並且移除href屬性  -->
									<c:if test="${certificateRequest.enrollStatus == '0'}">
		                            	<a id="btnDownload" href="./doGet?oid=<c:out value="${certificateRequest.oid}"/>"  class="btn btn-sm btn-default" ><spring:message code="btn.download"/><!--下載--></a>		                            
									</c:if>
									<c:if test="${certificateRequest.enrollStatus == '1'}">
		                            	<a disabled="true" id="btnDownload" class="btn btn-sm btn-default" ><spring:message code="btn.download"/><!--下載--></a>		                            
									</c:if>
									<!-- 刪除按鈕 生成時判斷申請狀態 未申請時disabled=false  已申請時按鍵disabled=true 並且移除href屬性  -->									
									<c:if test="${certificateRequest.enrollStatus == '0'}">
		                            	<a id="btnDelete_${certificateRequest.oid}" href="./delete?oid=<c:out value="${certificateRequest.oid}"/>" class="btn btn-sm btn-default"><spring:message code="btn.delete"/><!--刪除--></a>
		                        	</c:if>
		                        	<c:if test="${certificateRequest.enrollStatus == '1'}">
		                            	<a disabled="true" id="btn-delete" class="btn btn-sm btn-default"><spring:message code="btn.delete"/><!--刪除--></a>		                        	
		                        	</c:if>
		                        </td>
		                    </tr>
		                    </c:forEach>
	                </tbody>
		            </table>
		        </div>
		
		    </div>
		</div>
				<!--         /#page-wrapper -->
		<!--         paginator -->
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
		</c:if>
		
	</form:form>
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
   <script type="text/javascript">
		//讀取完畢 設置查詢按鈕點擊觸發方法  form1表單指定對應對應Controller方法並送出 
		$(document).ready(function () {
		    $("#btnQuery").click(function(){
		        $("#form1").attr("action", "./query");
		        $('#form1').submit();
		    });
		});
	</script>
</div>