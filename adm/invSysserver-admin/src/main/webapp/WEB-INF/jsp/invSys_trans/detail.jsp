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

                         <tr>
                             <td class="bg-primary">
									<spring:message code="ui.invSys-trans.verify-service-time" />
                             		<!--驗證服務時間--></td>
                             <td><c:out value="${form.createDate}" /><c:out value="${form.createTime}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.system-tracking" />
                             		<!--系統跟踪號--></td>
                             <td><c:out value="${form.invSysRequestorID}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.amount-transaction" />
                             		<!--交易金額--></td>
                             <td><c:out value="${formcr.purchaseAmount}" />bits</td>
                         </tr>
                         <tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.currency.code" />
                             		<!--貨幣代碼--></td>
                             <td><c:out value="${form.purchaseCurrency}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.merchant-code" />
                             		<!--商店代碼--></td>
                             <td><c:out value="${form.acquirerMerchantID}" /></td>
                         </tr>
                         <tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.trading-status" />
                             		<!--交易狀態--></td>
                             <td><c:out value="${form.transStatus}" /></td>
                         </tr>
						<tr>
                             <td class="bg-primary">
                             <spring:message code="ui.invSys-trans.response.code" />
                            		 <!--響應碼--></td>
                             <td><c:out value="${form.errorCode}" /></td>
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