

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld"%>


<div class="row">

	<c:forEach items="${ dataAfter['entities']}" var="entity"
		varStatus="listStatus">
		<div class="col-md-6">
			<h4><spring:message code="CARD_SCHEME.${entity.cardScheme}"/></h4>
				<c:if test="${entity.channelEnable == '1'}"><spring:message code="ui.invSys-trans-config.channel.enable" text="啟動"/></c:if>
				<c:if test="${entity.channelEnable == '0'}"><spring:message code="ui.invSys-trans-config.channel.disable" text="關閉" /></c:if>
		</div>
	</c:forEach>

	
	

</div>