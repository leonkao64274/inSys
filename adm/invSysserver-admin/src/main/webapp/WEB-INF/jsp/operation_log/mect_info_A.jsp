<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">
	<div class="col-md-12">
		<h4>
			<spring:message text="商店代號" code="ui.mect-info.account" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['acquirerMerchantId']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['acquirerMerchantId']}">
				<c:out value=" ${dataAfter['acquirerMerchantId']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="商店URL" code="ui.mect-info.url" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['invSysRequestorUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['invSysRequestorUrl']}">
				<c:out value=" ${dataAfter['invSysRequestorUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="Mcc" code="ui.mect-info.mcc" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['mcc']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['mcc']}">
				<c:out value=" ${dataAfter['mcc']}" />
            </c:if>
		</p>
		<hr>
	</div>
		<div class="col-md-12">
		<h4>
			<spring:message code="ui.mect-info.merchantName" text="商店名稱" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['merchantName']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['merchantName']}">
				<c:out value=" ${dataAfter['merchantName']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message code="ui.mect-info.merchantCountryCode" text="商店國家代碼" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['merchantCountryCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['merchantCountryCode']}">
				<c:out value=" ${dataAfter['merchantCountryCode']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="幣別代碼" code="ui.mect-info.purchaseCurrency" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['purchaseCurrency']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['purchaseCurrency']}">
				<c:out value=" ${dataAfter['purchaseCurrency']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="貨幣指數"
				code="ui.mect-info.purchaseExponent" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['purchaseExponent']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['purchaseExponent']}">
				<c:out value=" ${dataAfter['purchaseExponent']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="商店密碼" code="ui.mect-info.acquirerMerchantPwd" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['acquiererMerchantPwd']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['acquiererMerchantPwd']}">
				<c:out value=" ${dataAfter['acquiererMerchantPwd']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="商店狀態"
				code="ui.mect-info.acquirerStatus" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['merStatus']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['merStatus']}">
				<c:out value=" ${dataAfter['merStatus']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>