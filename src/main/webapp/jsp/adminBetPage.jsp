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
            <c:set var="pageID" value="webBetController?command=CREATE_BET_LIST" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <jsp:include page="loginLogoutHeader.jsp" />
            <table class="table">
                <tr>
                    <th>#id</th>
                    <th><fmt:message key="admin.bet.table.login"/></th>
                    <th><fmt:message key="admin.bet.table.match"/></th>
                    <th><fmt:message key="admin.bet.table.date"/></th>
                    <th><fmt:message key="admin.bet.table.score"/></th>
                    <th><fmt:message key="admin.bet.table.prediction"/></th>
                    <th><fmt:message key="admin.bet.table.charge"/></th>
                    <th><fmt:message key="admin.bet.table.sum"/></th>
                    <th><fmt:message key="admin.bet.table.coef"/></th>
                    <th><fmt:message key="admin.bet.table.won"/></th>
                    <th><fmt:message key="admin.bet.table.status"/></th>
                </tr>
                <c:forEach var="bet" items="${betList}">
                    <tr>
                        <td>${bet.betId}</td>
                        <td>${bet.user.login}</td>
                        <td>${bet.match.matchName}</td>
                        <td><fmt:formatDate value="${bet.match.startTime}" dateStyle="short"/></td>
                        <td>${bet.match.matchScore}</td>
                        <td>${bet.prediction}</td>
                        <td>${bet.moneyCharge}</td>
                        <td>${bet.sum}</td>
                        <td>${bet.currentCoef}</td>
                        <td>${bet.won}</td>
                        <td>${bet.status}</td>
                        <td><a href="webBetController?betId=${bet.betId}&command=EDIT_BET_COMMAND" ><fmt:message key="admin.bet.table.edit"/></a> </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${currentPage != 1}">
                <td><a href="webBetController?command=CREATE_BET_LIST&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
            </c:if>
            <table class="table">
                <tr>
                    <c:forEach begin="1" end="${numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="webBetController?command=CREATE_BET_LIST&page=${i}">${i}</a> </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
            <c:if test="${currentPage lt numberOfPages}">
                <td><a href="webBetController?command=CREATE_BET_LIST&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a></td>
            </c:if>
            <br/>
            <a href="webBetController?command=DISPLAY_MATCHES_COMMAND"><fmt:message key="login.home.reff" /> </a>
        </div>
    </div>
</div>
</body>
</html>
