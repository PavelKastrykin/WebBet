<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<header>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
</header>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="webBetController?command=DISPLAY_MATCHES_COMMAND">Web Bet</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <c:choose>
            <c:when test="${sessionScope.userValue != null}">
                <form action="webBetController" method="get" class = "navbar-form navbar-left">
                    <input type="hidden" name="command" value="LOGOUT_COMMAND"/>
                    <label for="logout" >${userValue.login}</label>
                    <fmt:message key="loginLogoutHeader.logout.button" var="logoutButton" />
                    <input type="submit" id="logout" name="logout" value="${logoutButton}" class="btn btn-default" style="width:120px">
                </form>
            </c:when>
            <c:otherwise>
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp"><fmt:message key="loginLogoutHeader.login.reff" /></a></li>
                </ul>
            </c:otherwise>
        </c:choose>

        <form action="webBetController" method="get">
            <input type="hidden" name="command" value="LOCALE_COMMAND"/>
            <input type="hidden" name="hiddenPageID" value="${pageID}" />

            <p class="navbar-text navbar-right"><fmt:message key="header.language" />
                <select id="language" name="language" onchange="this.form.submit()" class="form-control"
                        style="display:inline;width: auto !important;">
                    <option value="en_US" ${localeValue == 'en_US' ? 'selected' : ''}>English</option>
                    <option value="ru_RU" ${localeValue == 'ru_RU' ? 'selected' : ''}>Русский</option>
                </select>
            </p>
        </form>
        </div>
    </div>
</nav>
</body>
</html>
