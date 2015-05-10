<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<header>
    <link href="css/bootstrap.css" rel="stylesheet" />
</header>
<body>
    <c:choose>
        <c:when test="${sessionScope.userValue != null}">
            <form action="webBetController" method="get" style="display:inline;">
                <input type="hidden" name="command" value="LOGOUT_COMMAND"/>
                <label for="logout" >${userValue.login}</label>
                <fmt:message key="loginLogoutHeader.logout.button" var="logoutButton" />
                <input type="submit" id="logout" name="logout" value="${logoutButton}" class="btn btn-primary" style="width:120px">
            </form>
        </c:when>
        <c:otherwise>
            <a href="index.jsp"><fmt:message key="loginLogoutHeader.login.reff" /></a>
            <br/>
        </c:otherwise>
    </c:choose>
</body>
</html>
