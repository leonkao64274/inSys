<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

		<!--InvCore invSys Server 後台管理系統-->
        <title><spring:message text="" code="application.name" /></title>

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!--分頁-->
<!--         http://cdn.bootcss.com/jquery/1.11.3/ -->
        <script src="${pageContext.request.contextPath}/vernder/jquery/jquery.min.js"></script>
        
        <!-- jquery datatables -->
        <script src="${pageContext.request.contextPath}/webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/datatables/1.10.12/css/jquery.dataTables.min.css" />
        
        <link href="${pageContext.request.contextPath}/css/invSetPay.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/js/paginator.js"></script>

    </head>



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

.lead {
	font-size: 21px;
}
</style>

<body>
	<div id="wrapper">
		<div class="page-content page-content-ease-in">

			<!-- end PAGE TITLE ROW -->

			<div class="inner">
				<div class="row">
					<div class="col-lg-6 col-lg-offset-3">
						<h1 class="error-title">400</h1>
						<h4 class="error-msg">
							<i class="fa fa-warning text-red"></i> Bad Request
						</h4>
						<p class="lead"><spring:message text="" code="ui.ERR_400"/></p>
						
						<ul class="list-inline">
							<li><a class="btn btn-success"
								href="/invSysserver-admin/index/login">Back to Home</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>