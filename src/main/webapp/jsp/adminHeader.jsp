<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title></title>
</head>
<body>
    <form action="webBetController" id="userList" name="userList" method="get" style="display:inline;">
        <fmt:message key="admin.header.button.users" var="userButtonValue"/>
        <input type="hidden" name="command" value="CREATE_USER_LIST">
        <input type="button" value="${userButtonValue}" onclick="document.forms['userList'].submit()" class="btn btn-primary"
                style="width:120px">
    </form>
    <form action="webBetController" id="betList" name="betList" method="get" style="display:inline;">
        <fmt:message key="admin.header.button.bets" var="betButtonValue"/>
        <input type="hidden" name="command" value="CREATE_BET_LIST">
        <input type="button" value="${betButtonValue}" onclick="document.forms['betList'].submit()" class="btn btn-primary"
               style="width:120px">
    </form>
</body>
</html>
