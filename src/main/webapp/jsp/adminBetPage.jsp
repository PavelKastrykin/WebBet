<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
<head>
    <title></title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>#id</th>
            <th>Login</th>
            <th>Match</th>
            <th>Date</th>
            <th>Score</th>
            <th>Prediction</th>
            <th>Charge</th>
            <th>Sum</th>
            <th>Coef</th>
            <th>Won</th>
            <th>Status</th>
        </tr>
        <c:forEach var="bet" items="${betList}">
            <tr>
                <td>${bet.betId}</td>
                <td>${bet.login}</td>
                <td>${bet.footballMatchName}</td>
                <td><fmt:formatDate value="${bet.footballMatchDate}" dateStyle="short"/></td>
                <td>${bet.matchScore}</td>
                <td>${bet.prediction}</td>
                <td>${bet.moneyCharge}</td>
                <td>${bet.sum}</td>
                <td>${bet.currentCoef}</td>
                <td>${bet.won}</td>
                <td>${bet.status}</td>
                <td><a href="webBetController?betId=${bet.betId}&command=EDIT_BET_COMMAND" >Edit</a> </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentPage != 1}">
        <td><a href="webBetController?command=CREATE_BET_LIST&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
    </c:if>
    <table border="1" cellpadding="5" cellspacing="5">
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
        <td><a href="webBetController?command=CREATE_BET_LIST&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a> </td>
    </c:if>

</body>
</html>
