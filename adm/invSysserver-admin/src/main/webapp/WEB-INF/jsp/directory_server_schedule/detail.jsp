<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>

<!-- page header -->
<div id="sitemesh-header">
	<h3 class="page-header"><spring:message code="ui.card-scheme-download-setting"/><!--卡段下載設定--></h3>
</div>

<!-- page content -->
                <div id="sitemesh-content">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="table-responsive">
                                <table class="table table-bordered">
                                    <tbody>
                                        <tr>
                                            <td class="bg-primary"><spring:message code="ui.card-scheme.name"/><!--卡組織--></td>
                                            <td>
                   							<c:if test="${not empty cardSchemeConfigModel}">
                                      		<c:forEach items="${cardSchemeConfigModel}" var="cardScheme">
                                      			<%-- ${cardScheme.codeId } [] ${cardScheme.codeDesc }
                                      			<br>  --%>
                                      			<c:if test="${cardScheme.codeId==form.cardScheme}">
                                      				${cardScheme.codeDesc }
                                      			</c:if>
                                            </c:forEach>
                                            </c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-primary"><spring:message code="ui.serverUrl"/><!--服務器URL	--></td>
                                            <td>${form.serverUrl}</td>
                                        </tr>
                                        <tr>
                                            <td class="bg-primary"><spring:message code="ui.opRunningType"/><!--執行類型	--></td>
                                            <%-- <td>${form.opRunningType}</td> --%>
                                            
                          
                                            <td>
                                      			<c:if test="${'ALL'==form.opRunningType}">
                                      				<spring:message code="ui.opRunningType.ALL"/>
                                      			</c:if>
                                      			<c:if test="${'DIF'==form.opRunningType}">
                                      				<spring:message code="ui.opRunningType.DIF"/>
                                      			</c:if>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="bg-primary"><spring:message code="ui.opDuration"/><!--執行週期	--></td>
                                            <td>${form.opDuration}</td>
                                        </tr>
                                    </tbody>    
                                </table>
                            </div>

                            <div class="row">&nbsp;</div>
                            <div class="row">
                                <!-- button -->
                                <div class="col-md-12 text-center">
                                    <div class="form-group">
                                        <label class="control-label"></label>
                                       <a href="./query" class="btn btn-default"><spring:message code="btn.back-history" /><!-- 回上一頁 --></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
         

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btnCancel").click(function(){
                $("#form1").attr("action", "./query");
                $('#form1').submit();
            });
        });
    </script>
</div>