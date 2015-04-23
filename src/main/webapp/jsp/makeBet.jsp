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
    <c:set var="pageID" value="jsp/makeBet.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <form action="webBetController" method="post">
        <input type="hidden" name="command" value="ADD_BET_COMMAND" />
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th>Name</th>
                <th>Date</th>
                <th>Win1</th>
                <th>Draw</th>
                <th>Win2</th>
            </tr>
            <tr>
                <td>${matchToBet.matchId}</td>
                <td>${matchToBet.matchName}</td>
                <td>${matchToBet.startTime}</td>
                <td>${matchToBet.winCoef}</td>
                <td>${matchToBet.drawCoef}</td>
                <td>${matchToBet.looseCoef}</td>
            </tr>
        </table>
    </form>
</body>
</html>
