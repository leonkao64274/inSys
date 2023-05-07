<%-- 
    Document   : common
    Created on :  /6/17, 下午 04:14:52
    Author     :   LeonKao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="admin" uri="/WEB-INF/tlds/admin_library.tld" %>
<%
    String root = request.getContextPath();
    // prevent browser cache
	response.setHeader("Cache-Control", "no-store"); // for HTTP 1.1
	response.setHeader("Pragma", "no-cache");        // for HTTP 1.0
	response.setDateHeader("Expires", 0);            // Expired    
%>