<%-- 
    Document   : transaction_log_Q
    Created on :   /3/16, 下午 07:33:56
    Author     :   LeonKao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">
	<div class="col-md-12">
		<h4>
			<spring:message text="卡組織" code="ui.card-scheme.name" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['cardScheme']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['cardScheme']}">
				<spring:message text="${dataAfter['cardScheme']}"
					code="CARD_SCHEME.${dataAfter['cardScheme']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="主要網址" code="ui.directory-server.main-url" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['areqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['areqUrl']}">
				<c:out value=" ${dataAfter['areqUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="備援網址" code="ui.directory-server.alternate-url" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['backupAreqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['backupAreqUrl']}">
				<c:out value=" ${dataAfter['backupAreqUrl']}" /> bits
            </c:if>
		</p>
		<hr>
	</div>
		<div class="col-md-12">
		<h4>
			<spring:message code="ui.directory-server.preqUrl" text="PReq主要网址" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['preqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['preqUrl']}">
				<c:out value=" ${dataAfter['preqUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message code="ui.directory-server.backPreqUrl"
				text="PReq备用网址" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['backPreqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['backPreqUrl']}">
				<c:out value=" ${dataAfter['backPreqUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="訊息版本號碼"
				code="ui.directory-server.message-version-number" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['messageVersion']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['messageVersion']}">
				<c:out value=" ${dataAfter['messageVersion']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="請求重試次數"
				code="ui.directory-server.number-retries" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['retryLimits']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['retryLimits']}">
				<c:out value=" ${dataAfter['retryLimits']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="請求間隔時間(秒)" code="ui.directory-server.intervals" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['retryInterval']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['retryInterval']}">
				<c:out value=" ${dataAfter['retryInterval']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="連線逾時時間(秒)"
				code="ui.directory-server.readTimeout" />
		</h4>
		<p>
			<c:if test="${empty dataAfter['readTimeout']}">
                N/A
            </c:if>
			<c:if test="${not empty dataAfter['readTimeout']}">
				<c:out value=" ${dataAfter['readTimeout']}" />
			</c:if>
		</p>
		<hr>
	</div>

</div>