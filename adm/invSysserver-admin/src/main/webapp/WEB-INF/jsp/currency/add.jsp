<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../include/common.jsp"%>

<!-- (1. 新增網頁之標題區) -->
<div id="sitemesh-header">
	<!-- (寫這裡:1 - 網頁標題) -->
	<spring:message text="" code="ui.currency" /><!-- 國際貨幣幣別管理 -->
</div>

<!-- (2. 新增網頁之內容區) -->
<div id="sitemesh-content">
	<!-- (寫這裡:2 - 模型的屬性名) -->
	<form:form modelAttribute="form" id="form1" action="#" method="POST">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<!-- (寫這裡:2 - 第二級網頁標題) -->
					<div class="panel-heading">
						<spring:message text="" code="ui.currency-add" /><!-- 幣別資料-新增 -->
					</div>
					<div class="panel-body">
					
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
						
						<!-- (2.3. 用戶輸入) -->
						<div class="row">
						
							<!-- (寫這裡:4 - 輸入控件) -->
														
							<!-- 1. entity : 幣別所屬國家或機構 -->
							<spring:bind path="entity">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.entity" />${req}<!-- 幣別所屬國家或機構 -->
									</label>
									<form:input path="entity" maxlength="255" class="form-control" />
									<form:errors path="entity" />
									<span>&nbsp;</span>
								</div>
							</div>
							</spring:bind>
							
							<!-- 2. currency : 幣別名稱  -->
							<spring:bind path="currency">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.currency" />${req}<!-- 幣別名稱 -->
									</label>
									<form:input path="currency" maxlength="255" class="form-control" />
									<form:errors path="currency" />
									<span>&nbsp;</span>
								</div>
							</div>
							</spring:bind>
							
							<!-- 3. alphabeticCode : 幣別代碼-字母格式 -->
							<spring:bind path="alphabeticCode">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.alphabeticCode" />${req}<!-- 幣別代碼-字母格式 -->
									</label>
									<form:input path="alphabeticCode" maxlength="3" class="form-control" />
									<form:errors path="alphabeticCode" />
									<span>&nbsp;</span>
								</div>
							</div>
							</spring:bind>
							
							<!-- 4. numericCode : 幣別代碼-數字格式 -->
							<spring:bind path="numericCode">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.numericCode" />${req}<!-- 幣別代碼-數字格式 -->
									</label>
									<form:input path="numericCode" maxlength="3" class="form-control" />
									<form:errors path="numericCode" />
									<span>&nbsp;</span>
								</div>
							</div>
							</spring:bind>
							
							<!-- 5. minorUnit : 金額小數位數 -->
							<spring:bind path="minorUnit">
							<div class="col-md-6">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="control-label">
										<spring:message text="" code="ui.currency.minorUnit" /><!-- 金額小數位數 -->
									</label>
									<form:input path="minorUnit" maxlength="1" class="form-control integer" />
									<form:errors path="minorUnit" />
									<span>&nbsp;</span>
								</div>
							</div>
							</spring:bind>


						</div>

						<!-- (2.4. 美化用空白) -->
						<div class="row">&nbsp;</div>

						<!-- (2.5. 表單用按鍵) -->
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<a href="#" id="btnSubmit" class="btn btn-primary">
										<spring:message text="" code="btn.save" /><!-- (確認) -->
									</a> 
									<a href="#" id="btnReset" class="btn btn-default">
										<spring:message text="" code="btn.reset" /><!-- (重設) -->
									</a> 
									<a href="./query?pageNumber=${pageNumber}" class="btn btn-default">
										<spring:message text="" code="btn.cancel" /><!-- (取消)  -->
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</div>

<!-- (3. 新增網頁之腳本區) -->
<div id="sitemesh-script">
	<script type="text/javascript">
	$(function() {
		var $form1 = $("#form1");
		var message = '<spring:message text="" code="ui.confirm.save" />';
		var $rate = $("#rate");
		$("#btnSubmit").on("click", function(event) {
			if (confirm(message) == true) {
				$form1.attr("action", "./add");
				$form1.submit();
        		return true;// display spin for processing
        	}
        	return false;// stop spin for processing
		});
		$("#btnReset").on("click", function(event) {
			$form1[0].reset();
		});
		$("input.integer").on("blur", function(event) {
			var $this = $(this);
			var value = $this.val();
			if (value != "" && (! $.isNumeric(value))) {
				$this.val("-1");
			}
		});
		if ($rate.val() != "") {
			$rate.val(Number($rate.val()));
		}
	});
	</script>
</div>