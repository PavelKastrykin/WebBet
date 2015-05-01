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
    <form action="webBetController" id="userList" name="userList" method="get">
        <fmt:message key="admin.header.button.users" var="userButtonValue"/>
        <input type="hidden" name="command" value="CREATE_USER_LIST">
        <input type="button" value="${userButtonValue}" onclick="document.forms['userList'].submit()" >
    </form>
    <form action="webBetController" id="betList" name="betList" method="get">
        <fmt:message key="admin.header.button.bets" var="betButtonValue"/>
        <input type="hidden" name="command" value="CREATE_BET_LIST">
        <input type="button" value="${betButtonValue}" onclick="document.forms['betList'].submit()" >
    </form>
</body>
</html>
