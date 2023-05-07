<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
    
</div>

<!-- page content -->
<div id="sitemesh-content">
         <!-- 查詢結果 -->
     <div class="row">
         <div class="col-md-12">
             <div class="table-responsive">
                 <table class="table table-bordered">
                     <tbody>
<!--                          <tr> -->
<%--                              <td class="bg-primary col-md-5"><spring:message code="ui.issuer.name"/><!--發卡銀行--></td> --%>
<%--                              <td><c:out value="${form.issuer.issuerName}" /></td> --%>
<!--                          </tr> -->
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.card-scheme.name"/><!--卡組織--></td>
                             <td><spring:message code="CARD_SCHEME.${form.cardScheme}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.key-alias"/><!--密鑰基碼識別--></td>
                             <td><c:out value="${form.keyAlias}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.key-length"/><!--密鑰長度--></td>
                             <td><c:out value="${formcr.keyLength}" />bits</td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-cert-issuer"/><!--簽發者--></td>
                             <td><c:out value="${form.certAuthorCn}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-subject"/><!--主體--></td>
                             <td><c:out value="${form.subject}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-serial-number"/><!--序號--></td>
                             <td><c:out value="${form.serialNumber}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.sign_algorithm"/><!--簽章演算法--></td>
                             <td><c:out value="${formcr.signAlgorithm}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.ca-cert-certificate-valid-period"/><!--有效期間--></td>
                             <td>                             
                            	 <fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${form.notBefore}" />
							 	 <fmt:formatDate  value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>~
								 <fmt:parseDate var="createTime" pattern="yyyyMMddHHmmss" value="${form.notAfter}" />
								 <fmt:formatDate  value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                             
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

</div>