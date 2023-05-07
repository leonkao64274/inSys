<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/common.jsp"%>

<!-- (1. 查詢網頁之標題區) -->
<div id="sitemesh-header">
	<!-- (寫這裡:1 - 網頁標題) -->
	<spring:message text="" code="ui.currency" /><!-- 國際貨幣幣別管理 -->
</div>

<!-- (2. 查詢網頁之內容區) -->
<div id="sitemesh-content">
	<!-- (寫這裡:2 - 模型的屬性名) -->
	<form:form modelAttribute="currencyCriteriaForm" id="form1" action="#" method="POST">
		
		<!-- (2.1. 後台訊息) -->
		<c:if test="${not empty errors}">
		<div class="row">
			<div class="col-md-12">
				<div class="alert alert-danger">
					<p class="fa fa-warning">${errors}</p>
				</div>
			</div>
		</div>
		</c:if>		
		
		<!-- (2.2. 前台訊息) -->
		<div class="hide"><!-- (寫這裡:3 - 一堆雜七雜八的前台I18N字) --></div>
		
		<!-- (2.3. 查詢條件) -->
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						<spring:message text="" code="ui.search-criteria" /><!-- (查詢條件) -->
					</div>
					<div class="panel-body">
						<div class="row">
						
							<!-- (寫這裡:4 - 輸入控件) -->
							<!-- 1. entity : 幣別所屬國家或機構 -->
							<spring:bind path="entity">
							<div class="col-md-3">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.entity" /><!-- 幣別所屬國家或機構 -->
									</label>
									<form:input path="entity" maxlength="255" class="form-control" />
									<form:errors path="entity" />
								</div>
							</div>
							</spring:bind>
							
							<!-- 2. currency : 幣別名稱  -->
							<spring:bind path="currency">
							<div class="col-md-3">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.currency" /><!-- 幣別名稱 -->
									</label>
									<form:input path="currency" maxlength="255" class="form-control" />
									<form:errors path="currency" />
								</div>
							</div>
							</spring:bind>
							
							<!-- 3. alphabeticCode : 幣別代碼-字母格式 -->
							<spring:bind path="alphabeticCode">
							<div class="col-md-3">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.alphabeticCode" /><!-- 幣別代碼-字母格式 -->
									</label>
									<form:input path="alphabeticCode" maxlength="3" class="form-control" />
									<form:errors path="alphabeticCode" />
								</div>
							</div>
							</spring:bind>
							
							<!-- 4. numericCode : 幣別代碼-數字格式 -->
							<spring:bind path="numericCode">
							<div class="col-md-3">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.numericCode" /><!-- 幣別代碼-數字格式 -->
									</label>
									<form:input path="numericCode" maxlength="3" class="form-control" />
									<form:errors path="numericCode" />
								</div>
							</div>
							</spring:bind>
							
						</div>
					</div>
				</div>
			</div>
		</div>		
		
		<!-- (2.4. 查詢條件下方的按鍵) -->
		<div class="row">
			<div class="col-md-12">
				<span>
					<label class="bg-success text-success" 
							style="padding: 0.5em 1em; border-radius: 0.25em;">
						<spring:message text="" code="ui.currency.iso.upload" /><!-- ISO 檔案上傳： -->
					</label>
					<input type="file" name="file" accept=".txt" class="bg-success text-success"
							style="display: inline; padding: 0.375em 1em; 
							border-radius: 0.25em; border: 1px solid lightgreen" />	
					<a class="btn btn-default btn-success" href="#" id="uploadBtn">
						<spring:message text="" code="btn.load" /><!-- 上傳 -->
					</a>					
				</span>
				<span class="pull-right">
					<a href="#" id="btnQuery" class="btn btn-primary">
						<spring:message text="" code="btn.query" /><!-- (查詢) -->
					</a>
					<a href="./add" id="btnAdd" class="btn btn-default">
						<spring:message text="" code="btn.new" /><!-- (新增) -->
					</a>
				</span>
			</div>
		</div>		
		
		<!-- (2.5. 美化用空白) -->
		<div class="row" style="height: 40px;">&nbsp;</div>
		
		<!-- (2.6. 查詢結果) -->
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr class="bg-primary">
								
								<!-- (寫這裡:5 - 欄位名稱) -->
								<th class="text-nowrap">
									<spring:message text="" code="ui.currency.entity" /><!-- 幣別所屬國家或機構 -->
								</th>
								<th class="text-nowrap">
									<spring:message text="" code="ui.currency.currency" /><!-- 幣別名稱 -->
								</th>
								<th class="text-nowrap">
									<spring:message text="" code="ui.currency.alphabeticCode" /><!-- 幣別代碼-字母格式 -->
								</th>
								<th class="text-nowrap">
									<spring:message text="" code="ui.currency.numericCode" /><!-- 幣別代碼-數字格式 -->
								</th>
								<th class="text-nowrap">
									<spring:message text="" code="ui.currency.minorUnit" /><!-- 金額小數位數 -->
								</th>

	
								
								<!-- (欄位格：執行選項) -->
								<th class="text-nowrap">
									<spring:message text="" code="ui.operation" /><!-- (執行選項) -->
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${queryResult.content}" var="bean">
							<tr>

								<!-- (寫這裡:6 - 數據的內容) -->							
								<td>${bean.entity}</td>
								<td>${bean.currency}</td>
								<td>${bean.alphabeticCode}</td>
								<td>${bean.numericCode}</td>
								<td>${bean.minorUnit}</td>
								
		
								
								<!-- (按鍵格：執行選項) -->
								<td class="text-nowrap">
									<a href="./edit?oid=${bean.oid}" class="btn btn-sm btn-default">
										<spring:message text="" code="btn.update" /><!-- (修改) -->
									</a>
									<a href="./delete?oid=${bean.oid}"  id="btnDelete_${bean.oid}" class="btn btn-sm btn-default delete">
										<spring:message text="" code="btn.delete" /><!-- (刪除) -->
									</a> 
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<!-- (2.7. 分頁控件) -->
		<c:if test="${not empty queryResult.content}">
			<admin:PaginatorTagHandler formId="form1" pagingUri="./query" page="${queryResult}" />
		</c:if>
	</form:form>
</div>

<!-- 3. 查詢網頁之腳本區 -->
<div id="sitemesh-script">
	<script type="text/javascript">
	$(function() {
		var $form1 = $("#form1");
		var delete_message = '<spring:message text="" code="ui.confirm.delete" />';
		var upload_message = '<spring:message text="" code="ui.confirm.upload" />';
		var nofile_message = '<spring:message text="" code="ui.currency.nofile" />';
		$("#btnQuery").on("click", function(event) {
			$form1.attr("action", "./query");
			$form1.submit();
		});
		$("#uploadBtn").on("click", function(event) {
			event.preventDefault();
			if($('input[type=file]').val()==""){
				alert(nofile_message);	
				return false;
			}
			else if (confirm(upload_message) == true) {
				$form1.attr("action", "./upload");
				$form1.attr("enctype", "multipart/form-data");
				$form1.submit();
			};
		});
	});
	</script>
</div>
