<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title><fmt:message key="title.home" />${userValue.name}</title>
</head>
<body>
    <c:set var="pageID" value="jsp/home.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <c:if test="${sessionScope.userValue.userRole == 'ADMIN' || sessionScope.userValue.userRole == 'BOOK'}" >
        <jsp:include page="bookmakerPanel.jsp" />
    </c:if>
    <form action="webBetController" method="get">
        <input type="hidden" name="command" value="DISPLAY_MATCHES_COMMAND" />
        <fmt:message key="matches.button.show" var="buttonShow" />
        <input type="submit" name="submit" value="${buttonShow}"/>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th><fmt:message key="matches.table.match" /></th>
                <th><fmt:message key="matches.table.date" /></th>
                <th><fmt:message key="matches.table.score" /></th>
                <th><fmt:message key="matches.table.winner1" /></th>
                <th><fmt:message key="matches.table.draw" /></th>
                <th><fmt:message key="matches.table.winner2" /></th>
                <th><fmt:message key="matches.table.status" /></th>
            </tr>
            <c:forEach var="match" items="${matchesList}">
                <tr>
                    <td>${match.matchId}</td>
                    <td>${match.matchName}</td>
                    <td><fmt:formatDate value="${match.startTime}" type="both" dateStyle="short" timeStyle="short"/></td>
                    <td>${match.matchScore}</td>
                    <td>${match.winCoef}</td>
                    <td>${match.drawCoef}</td>
                    <td>${match.looseCoef}</td>
                    <td>${match.status}</td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${currentPage != 1}">
            <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
        </c:if>
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <c:forEach begin="1" end="${numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${i}">${i}</a> </td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>
        <c:if test="${currentPage lt numberOfPages}">
            <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a> </td>
        </c:if>
    </form>

</body>
</html>
