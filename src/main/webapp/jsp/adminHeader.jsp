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
    <form action="webBetController" id="userList" name="userList" method="get">
        <input type="hidden" name="command" value="CREATE_USER_LIST">
        <input type="button" value="Users" onclick="document.forms['userList'].submit()" >
    </form>
    <form action="webBetController" id="betList" name="betList" method="get">
        <input type="hidden" name="command" value="CREATE_BET_LIST">
        <input type="button" value="Bets" onclick="document.forms['betList'].submit()" >
    </form>
</body>
</html>
