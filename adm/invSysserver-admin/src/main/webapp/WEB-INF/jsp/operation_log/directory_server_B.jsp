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
			<c:if test="${empty dataBefore['cardScheme']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['cardScheme']}">
				<spring:message text="${dataBefore['cardScheme']}"
					code="CARD_SCHEME.${dataBefore['cardScheme']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="主要網址" code="ui.directory-server.main-url" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['areqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['areqUrl']}">
				<c:out value=" ${dataBefore['areqUrl']}" />
			</c:if>
		</p>
		<hr>
	</div>

	<div class="col-md-12">
		<h4>
			<spring:message text="備援網址" code="ui.directory-server.alternate-url" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['backupAreqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['backupAreqUrl']}">
				<c:out value=" ${dataBefore['backupAreqUrl']}" /> bits
            </c:if>
		</p>
		<hr>
	</div>
		<div class="col-md-12">
		<h4>
			<spring:message code="ui.directory-server.preqUrl" text="PReq主要网址" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['preqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['preqUrl']}">
				<c:out value=" ${dataBefore['preqUrl']}" />
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
			<c:if test="${empty dataBefore['backPreqUrl']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['backPreqUrl']}">
				<c:out value=" ${dataBefore['backPreqUrl']}" />
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
			<c:if test="${empty dataBefore['messageVersion']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['messageVersion']}">
				<c:out value=" ${dataBefore['messageVersion']}" />
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
			<c:if test="${empty dataBefore['retryLimits']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['retryLimits']}">
				<c:out value=" ${dataBefore['retryLimits']}" />
			</c:if>
		</p>
		<hr>
	</div>
	<div class="col-md-12">
		<h4>
			<spring:message text="請求間隔時間(秒)" code="ui.directory-server.intervals" />
		</h4>
		<p>
			<c:if test="${empty dataBefore['retryInterval']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['retryInterval']}">
				<c:out value=" ${dataBefore['retryInterval']}" />
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
			<c:if test="${empty dataBefore['readTimeout']}">
                N/A
            </c:if>
			<c:if test="${not empty dataBefore['readTimeout']}">
				<c:out value=" ${dataBefore['readTimeout']}" />
			</c:if>
		</p>
		<hr>
	</div>


</div>