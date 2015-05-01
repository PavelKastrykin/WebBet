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
<form action="webBetController" method="post">
    <input type="hidden" name="command" value="SHOW_MY_BETS_COMMAND">
    <fmt:message key="bets.showbets.button" var="buttonValue" />
    <input type="submit" name="submit" value="${buttonValue}">
</form>
</body>
</html>
