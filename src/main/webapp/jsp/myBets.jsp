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
            <c:set var="pageID" value="jsp/myBets.jsp" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <table class="table">
                <tr>
                    <th><fmt:message key="bets.table.matchname" /></th>
                    <th><fmt:message key="bets.table.timestart" /></th>
                    <th><fmt:message key="bets.table.prediction" /></th>
                    <th><fmt:message key="bets.table.sum" /></th>
                    <th><fmt:message key="bets.table.coef" /></th>
                    <th><fmt:message key="bets.table.won" /></th>
                    <th><fmt:message key="bets.table.status" /></th>
                    <th><fmt:message key="bets.table.charge" /></th>
                </tr>
                <c:forEach var="bet" items="${myBets}">
                    <tr>
                        <td>${bet.match.matchName}</td>
                        <td><fmt:formatDate value="${bet.match.startTime}" dateStyle="short"/></td>
                        <td>${bet.prediction}</td>
                        <td>${bet.sum}</td>
                        <td>${bet.currentCoef}</td>
                        <td>${bet.won}</td>
                        <td>${bet.status}</td>
                        <td>${bet.moneyCharge}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
