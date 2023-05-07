<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>

<div class="row">
	<div class="col-md-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message text="" code="ui.search-criteria" />
			</div>
			<div class="panel-body">
				<div class="row">
	
	                    <div class="col-md-4">
							<h4>
								<spring:message text="商店代號" code="ui.mect-info.account" />
							</h4>
							<p>
								<c:if test="${empty dataQuery['acquirerMerchantId']}">
					                N/A
					            </c:if>
								<c:if test="${not empty dataQuery['acquirerMerchantId']}">
									<c:out value=" ${dataQuery['acquirerMerchantId']}" />
								</c:if>
							</p>
	                    </div>
	                        
	                    <div class="col-md-4">
							<h4>
								<spring:message text="商店名稱" code="ui.mect-info.merchantName" />
							</h4>
							<p>
								<c:if test="${empty dataQuery['merchantName']}">
					                N/A
					            </c:if>
								<c:if test="${not empty dataQuery['merchantName']}">
									<c:out value=" ${dataQuery['merchantName']}" />
								</c:if>
							</p>
	                    </div>

				</div>
			</div>
		</div>
	</div>
</div>
