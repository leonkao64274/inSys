
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
							<spring:message text="幣別所屬國家或機構" code="ui.currency.entity" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['entity']}">
                                    N/A
                                </c:if>
							<c:if test="${not empty dataQuery['entity']}">
								<c:out value="${dataQuery['entity']}" />
							</c:if>
						</p>
						<hr>
					</div>


					<div class="col-md-4">
						<h4>
							<spring:message text="幣別名稱" code="ui.currency.currency" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['currency']}">
                                N/A
                            </c:if>
							<c:if test="${not empty dataQuery['currency']}">
								<c:out value="${dataQuery['currency']}" />
							</c:if>
						</p>
						<hr>
					</div>

					<div class="col-md-4">
						<h4>
							<spring:message text="幣別代碼-字母格式"
								code="ui.currency.alphabeticCode" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['alphabeticCode']}">
                                N/A
                            </c:if>
							<c:if test="${not empty dataQuery['alphabeticCode']}">
								<c:out value="${dataQuery['alphabeticCode']}" />
							</c:if>
						</p>
						<hr>
					</div>

					<div class="col-md-4">
						<h4>
							<spring:message text="幣別代碼-數字格式" code="ui.currency.numericCode" />
						</h4>
						<p>
							<c:if test="${empty dataQuery['numericCode']}">
                                N/A
                            </c:if>
							<c:if test="${not empty dataQuery['numericCode']}">
								<c:out value="${dataQuery['numericCode']}" />
							</c:if>
						</p>
						<hr>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
