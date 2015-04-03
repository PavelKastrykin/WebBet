<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<c:set var="language" value="Language"/>
<c:set var="locale" value="en_US"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Welcome to WebBet!</title>
    </head>
    <body>
        <form action="changeLocale" method="post">
            <label>${language}</label>
            <select id="language" name="language" onchange="this.form.submit()">
                <option value="en_US">English</option>
                <option value="ru_RU">Russian</option>
            </select>
        </form>
        <form method="post">
            <label for="username"><fmt:message key="login.label.username" /></label>
            <input type="text" id="username" name="username">
            <br>
            <label for="password"><fmt:message key="login.label.password" /></label>
            <input type="password" id="password" name="password">
            <br>
            <fmt:message key="login.button.submit" var="buttonValue"/>
            <input type="submit" name="submit" value="${buttonValue}">
        </form>
    </body>
</html>