<%-- 
    Document   : common
    Created on :  /10/18, 下午 04:14:52
    Author     : LeonKao
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- profile -->
    <li class='sidebar-search'>
        <div class='input-group custom-search-form'>
            <div class="text-center" style="margin-bottom:10px;">
                <c:if test="${not empty adminUser}">
                    <img src="../img/HiLogo.jpg" class="img-responsive img-thumbnail">
                </c:if>
            </div>
            <div class="text-left text-muted"><spring:message code="ui.profile.account" /><!--<!-- 登錄帳號 --></div>
            <H5 class="text-left">
            <c:if test="${not empty adminUser}">
                <c:out value="${adminUser.account}" /> / <c:out value="${adminUser.userName}" />
            </c:if>
            </H5>
        </div>
    </li>
