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
			<c:if test="${empty dataBefore['acquirerMerchantId']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['acquirerMerchantId']}">
				<c:out value=" ${dataBefore['acquirerMerchantId']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="商店URL" code="ui.mect-info.url" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['invSysRequestorUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['invSysRequestorUrl']}">
				<c:out value=" ${dataBefore['invSysRequestorUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="Mcc" code="ui.mect-info.mcc" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['mcc']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['mcc']}">
				<c:out value=" ${dataBefore['mcc']}" />
            </c:if>
		</p>
		<hr>
	</div>
		<div class="col-md-12">
		<h4>
			<spring:message code="ui.mect-info.merchantName" text="商店名稱" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['merchantName']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['merchantName']}">
				<c:out value=" ${dataBefore['merchantName']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message code="ui.mect-info.merchantCountryCode" text="商店國家代碼" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['merchantCountryCode']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['merchantCountryCode']}">
				<c:out value=" ${dataBefore['merchantCountryCode']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="幣別代碼" code="ui.mect-info.purchaseCurrency" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['purchaseCurrency']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['purchaseCurrency']}">
				<c:out value=" ${dataBefore['purchaseCurrency']}" />
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
			<c:if test="${empty dataBefore['purchaseExponent']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['purchaseExponent']}">
				<c:out value=" ${dataBefore['purchaseExponent']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="商店密碼" code="ui.mect-info.acquirerMerchantPwd" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['acquiererMerchantPwd']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['acquiererMerchantPwd']}">
				<c:out value=" ${dataBefore['acquiererMerchantPwd']}" />
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
			<c:if test="${empty dataBefore['merStatus']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['merStatus']}">
				<c:out value=" ${dataBefore['merStatus']}" />
			</c:if>
		</p>
		<hr>
	</div>
</div>