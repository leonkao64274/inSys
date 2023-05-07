<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- page header -->
<div id="sitemesh-header">
    
</div>

<!-- page content -->
<div id="sitemesh-content">
    
</div>

<!-- Custom Page JavaScript -->
<div id="sitemesh-script">
    
</div>

<div id="sitemesh-error">
<style>
.inner {
	margin-top: 120px;
}

.error-msg {
	font-size: 2em;
}

.error-title {
	font-size: 9em;
}

.text-red {
	color: #e74c3c;
}

.fa {
	display: inline-block;
	font-family: FontAwesome;
	font-style: normal;
	font-weight: normal;
	line-height: 1;
}
</style>

	<div id="wrapper">
		<div class="page-content page-content-ease-in">
			<div class="inner">
				<div class="row">
					<div class="col-lg-6 col-lg-offset-3">
						<h1 class="error-title">500</h1>
						<h4 class="error-msg">
							<i class="fa fa-warning text-red"></i> Internal Server Error
						</h4>
						<p class="lead">
    <%
        Exception ex = (Exception)request.getAttribute("exception");
        if (ex instanceof com.invSet.emv.invSys.invSysserver.admin.exception.InvalidAccessException) {
            out.println(ex.getMessage());
        } else {
            out.println("<spring:message text=\"\" code=\"ui.ERR\"/>");
        }
    %>
                        </p>
						<ul class="list-inline">
                            <li><a class="btn btn-success" href="<c:out value="${btnActionUrl}"/>">
                                    Back to Home</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>