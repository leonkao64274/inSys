<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../include/common.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title><spring:message code="application.name" /></title><!--InvCore invSys Server 後台管理系統-->

        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap-datetimepicker.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery-2.1.1.min.js"></script>
        
        <!-- jquery datatables -->
        <script src="${pageContext.request.contextPath}/webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webjars/datatables/1.10.12/css/jquery.dataTables.min.css" />
        
        <link href="${pageContext.request.contextPath}/css/invSetPay.css" rel="stylesheet" type="text/css">
        <script src="${pageContext.request.contextPath}/js/paginator.js"></script>

		<!-- Encryption JavaScript -->
		<script src="${pageContext.request.contextPath}/js/encrypt.min.js"></script>
		
		<!-- Custom JavaScript -->
		<script type="text/javascript">
		$(function() {
	
			// 聲明：控件。
			var $form = $("<form action='#'></form>");
			var $changePassword = $("#change-password");
			var $logout = $("#logout");
			
			// 事件(1): 點擊[修改密碼]。
			$changePassword.on("click", function(event) {
				$form.attr("action", "./../index/password");
				$form.attr("method", "get");
				$form.submit();
			});
			
			// 事件(2): 點擊[管理員登出]。
			$logout.on("click", function(event) {
				$form.attr("action", "./../index/logout");
				$form.attr("method", "post");
				$form.submit();
			});
			
			// 初始程序。
			$("body").append($form);
	        
	        // 關閉 autocomplete
	        $("input").each(function(){
	            $(this).attr("autocomplete", "off");
	        });
	        
	        // 數字欄位輸入檢核
	        $("[data-type='numeric']").keyup(function(){
	            if (isNaN($(this).val())) {
	                $(this).val("");
	            }
	        });
	        
	        // 刪除確認
	        $("[id^='btnDelete_']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.delete"/>')) {
	                return false;
	            }
	        });
	        
	     	// 關閉確認
	        $("[id^='btnClose_']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.close"/>')) {
	                return false;
	            }
	        });
	        
	        // 存檔確認
	        $("[id='btnSave']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.save"/>')) {
	                return false;
	            }
	        });
	
	      //解凍確定
	        $("[id^='btnUnlocked_']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.unlock"/>')) {
	                return false;
	            }
	        });
	      
	      //註銷確定
	        $("[id^='btnDisable_']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.disable"/>')) {
	                return false;
	            }
	        });
	      
	      //開通確定
	        $("[id^='btnEnable_']").click(function(){
	            if (!confirm('<spring:message text="" code="ui.confirm.enable"/>')) {
	                return false;
	            }
	        });
		});
		</script>
	    
	    <style>
	        /* 瀏覽器寬度縮小,登出與修改密碼,不要超過左邊的邊界 */
	        @media (max-width: 691px) { 
	            .dropdown-logout {
	              right: auto;
	              left: 0;
	            }
	        }
	        /* 瀏覽器寬度放大,登出與修改密碼,不要超過右邊的邊界 */
	        @media (min-width: 692px) {
	            .dropdown-logout {
	              right: 0;
	              left: auto;
	            }
	        } 
	
			/* 文字過長,自動換到下一行,避免頁面不美觀*/
	        td{
	            word-wrap: break-word;
	        }
	        .top_bar {
		    background-color: #fff;
		    padding:5px 10px;
		    box-shadow: 0px 0px 5px #999;
		    -webkit-box-shadow: 0px 0px 5px #999;
		    -moz-box-shadow: 0px 0px 5px #2e2e2e;
		}
		body{
    		font-family: Helvetica, 微軟正黑體, Arial, sans-serif;
    	} 
	    </style>
    
    </head>

    <body>
	    
    <c:if test="${not empty requestScope.exception}">
        <sitemesh:write property='div.sitemesh-error'/>
    </c:if>
    
    <c:if test="${empty requestScope.exception}">
        <div id="wrapper">
			<!-- 1. 主畫面-置頂工具列。 -->
			<nav class="navbar navbar-default navbar-static-top top_bar" role="navigation" style="margin-bottom: 0">           
				<!-- 1.1. 工具列左邊LOGO與文字。-->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">invSet</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="" href="/invSysserver-admin/index/health" style="display: block; width: 165px; line-height: 50px; padding: 0 0 0 10px; ">
							<img src="../img/invSetinvSys_logo.svg" alt="brand">
						</a>  
				</div>
	
				<!-- 1.2. 工具列右邊[快捷鍵]區塊。-->
				<ul class="nav navbar-top-links navbar-right">
					<li class="dropdown">
						<!-- 1.2.1. 標題 -->
						<a class="dropdown-toggle" data-toggle="dropdown" href="#">
							<spring:message text="" code="application.name" /><!-- InvCore invSys Server 後台管理系統-->
							<i class="fa fa-user fa-fw"></i><!-- 人形圖標 -->
							<i class="fa fa-caret-down"></i><!-- 倒三角形圖標 -->
						</a>
						<!-- 1.2.2. 下拉選單 -->
						<ul class="dropdown-menu dropdown-logout">
							<li><a href="#" id="change-password">
									<i class="fa fa-gear fa-fw"></i><!-- 車輪圖標 -->
									<spring:message text="" code="ui.main.menu.change-password" /><!-- 修改密碼 -->
								</a>
							</li>
							<li class="divider"></li>
							<li><a href="#" id="logout">
									<i class="fa fa-sign-out fa-fw"></i><!-- 登出圖標 -->
									<spring:message text="" code="ui.main.menu.admin.logout" /><!-- 管理員登出 -->
								</a>
							</li>
						</ul>
					</li>
				</ul>
	
		        <!-- 2. 主畫面-左側功能選單。-->
		        <div class="navbar-default sidebar" role="navigation" style="margin-top: 64px; margin-left: -10px;">
		            <div class="sidebar-nav navbar-collapse">
		                <ul class='nav' id='side-menu'>
		                    <%@include file="../include/profile.jsp" %>
		                    <admin:MenuTagHandler grantedAccessIdList="${grantedAccessIdList}" locale="${locale}"/>
		                </ul>
		            </div>
		        </div>
			
			</nav>
			
			<!-- 3. 主畫面-右側內容頁。-->
            <div id="page-wrapper">
                <div class="container-fluid">
                    <!-- 標題區 -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h3 class="page-header"><sitemesh:write property='div.sitemesh-header'/></h3>
                        </div>
                    </div>
                    
                    <!-- 內容區 -->
                    <sitemesh:write property='div.sitemesh-content'/>
		                    
                </div>
            </div>


        </div>
        <!-- /#wrapper -->

        <!-- jQuery -->
        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery-2.1.1.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/moment-with-locales.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap-datetimepicker.js"></script>


        <!-- Metis Menu Plugin JavaScript -->
        <script src="${pageContext.request.contextPath}/vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="${pageContext.request.contextPath}/dist/js/sb-admin-2.js"></script>

        <!-- Custom Page JavaScript -->
        <sitemesh:write property='div.sitemesh-script'/>
	        
		<script type="text/javascript">
		    $(document).ready(function () {
		        // display spin for processing
		        $(".btn").each(function(){
		            $(this).attr("data-loading-text", "<i class='fa fa-spinner fa-spin '></i> " + $(this).html());
		        });
		
		        $("[data-loading-text]").click(function(event){
		            if (event.isPropagationStopped() ) {
		                return false;
		            }
		            if ($(this).attr("id") == 'btnReset') {
		                return true;
		            }
		            if ($(this).attr("id") == 'btnDownload') {
		                return true;
		            }
		            if ($(this).attr("id") == 'btnShowJson') {
		                return true;
		            }
		            if ($(this).attr("id") == 'btnShowDiv') {
                        return true;
                    }
                    if ($(this).attr("ignore-spin") == 'Y') {
                        return true;
                    }
		            $(this).button('loading');
		        });                
		    });
		</script>
		            
    </c:if>
    </body>

</html>
