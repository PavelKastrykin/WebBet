<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Welcome to WebBet!</title>
    </head>
    <body>
        <c:set var="pageID" value="login.jsp" scope="request" />
        <jsp:include page="header.jsp"/>
        <form action="loginWebBet" method="post">
            <table>
                <tr>
                    <td><label for="username"><fmt:message key="login.label.username" /></label></td>
                    <td><input type="text" id="username" name="username"></td>
                </tr>
                <tr>
                    <td><label for="password"><fmt:message key="login.label.password" /></label></td>
                    <td><input type="password" id="password" name="password"></td>
                </tr>
            </table>
            <br>
            <fmt:message key="login.button.submit" var="buttonValue"/>
            <input type="submit" name="submit" value="${buttonValue}">
        </form>
    </body>
</html>