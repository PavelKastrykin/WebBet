<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title></title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <c:set var="pageID" value="webBetController?command=CREATE_USER_LIST" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <table class="table">
                <tr>
                    <th>#id</th>
                    <th><fmt:message key="admin.user.table.login"/></th>
                    <th><fmt:message key="admin.user.table.name"/></th>
                    <th><fmt:message key="admin.user.table.role"/></th>
                </tr>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>${user.userRole}</td>
                        <td><a href="webBetController?userLogin=${user.login}&command=EDIT_USER_COMMAND" ><fmt:message key="admin.bet.table.edit"/></a> </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${currentPage != 1}">
                <td><a href="webBetController?command=CREATE_USER_LIST&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
            </c:if>
            <table class="table" style="width: auto !important;">
                <tr>
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="webBetController?command=CREATE_USER_LIST&page=${i}">${i}</a> </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
            <c:if test="${currentPage lt numberOfPages}">
                <td><a href="webBetController?command=CREATE_USER_LIST&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a></td>
            </c:if>
            <br/>
            <a href="webBetController?command=DISPLAY_MATCHES_COMMAND"><fmt:message key="login.home.reff" /> </a>
        </div>
    </div>
</div>
</body>
</html>
