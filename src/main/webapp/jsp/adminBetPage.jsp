<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <title></title>
</head>
<body>
    <c:set var="pageID" value="jsp/adminBetPage.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <table border="1" cellpadding="5" cellspacing="5">
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
                <td><a href="webBetController?betId=${bet.betId}&command=EDIT_BET_COMMAND" ><fmt:message key="admin.bet.table.edit"/></a> </td>
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
        <td><a href="webBetController?command=CREATE_BET_LIST&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a></td>
    </c:if>
    <br/>
    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
</body>
</html>
