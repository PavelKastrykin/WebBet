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
<form action="webBetController" method="post" style="display:inline;">
    <input type="hidden" name="command" value="SHOW_MY_BETS_COMMAND">
    <fmt:message key="bets.showbets.button" var="buttonValue" />
    <input type="submit" name="submit" value="${buttonValue}" class="btn btn-primary" style="width:120px">
</form>
</body>
</html>
