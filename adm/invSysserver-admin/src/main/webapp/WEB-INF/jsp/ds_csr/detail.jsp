<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                             <td><c:out value="${form.keyLength}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.sign_algorithm"/><!--簽章演算法*--></td>
                             <td><c:out value="${form.signAlgorithm}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.common-name"/><!--網域名稱(CN)--></td>
                             <td><c:out value="${form.commonName}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.organization"/><!--組織(O)--></td>
                             <td><c:out value="${form.organization}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.organization_unit"/><!--組織單位(OU)--></td>
                             <td><c:out value="${form.organizationUnit}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.country"/><!--國家--></td>
                             <td><c:out value="${form.country}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.province"/><!--省份(ST)--></td>
                             <td><c:out value="${form.province}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.locality"/><!--地區(L)--></td>
                             <td><c:out value="${form.locality}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary"><spring:message code="ui.certificate-quest.file-name"/><!--文件名*--></td>
                             <td><c:out value="${form.fileName}" /></td>
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