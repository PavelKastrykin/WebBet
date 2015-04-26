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
    <c:set var="pageID" value="jsp/myBets.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <table border="1" cellpadding="5" cellspacing="5">
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
                <td>${bet.footballMatchName}</td>
                <td><fmt:formatDate value="${bet.footballMatchDate}" dateStyle="short"/></td>
                <td>${bet.prediction}</td>
                <td>${bet.sum}</td>
                <td>${bet.currentCoef}</td>
                <td>${bet.won}</td>
                <td>${bet.status}</td>
                <td>${bet.moneyCharge}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a><br/>
</body>
</html>
