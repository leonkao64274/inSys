<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- page header -->
<div id="sitemesh-header">
	<spring:message code="ui.ca-cert-certificate-content"/><!-- 證書內容 -->
</div>

<!-- page content -->
<div id="sitemesh-content">
	<!-- 查詢結果 -->
	<div class="row">
	    <div class="col-md-12">
	        <div class="table-responsive">
	            <table class="table table-bordered">
	                <tbody>
	                    <tr>
	                        <td class="bg-primary"><spring:message code="ui.ca-cert-certificate.cert-alias"/><!-- 識別名稱 --></td>
	                        <td>${caCert.certAlias}</td>
	                    </tr>
	                    <tr>
	                        <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-cert-issuer"/><!-- 簽發者 --></td>
	                        <td>${caCert.certAuthor}</td>
	                    </tr>
	                    <tr>
	                        <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-subject"/><!-- 主體 --></td>
	                        <td>${caCert.subject}</td>
	                    </tr>
	                    <tr>
	                        <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-serial-number"/><!-- 序號 --></td>
	                        <td>${caCert.serialNumber}</td>
	                    </tr>
	                    <tr>
	                        <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-valid-period"/><!-- 有效期間 --></td>
	                        <td>
	                        	<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${caCert.notBefore}" />
                                <fmt:formatDate pattern="yyyy/MM/dd" value="${createTime}"/>	                        
	                        	 - 
								<fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${caCert.notAfter}" />
                                <fmt:formatDate pattern="yyyy/MM/dd" value="${createTime}"/>	                                   
	                         </td>
	                    </tr>
	                </tbody>    
	            </table>
	        </div>
	        <div class="text-center">
	            <a href="./query" class="btn btn-default"><spring:message code="btn.back-history"/><!--回上一頁--></a>
	        </div>
	    </div>
	</div>    
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
	<script>
		
	</script>   
</div>