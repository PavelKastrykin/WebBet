
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
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <c:set var="pageID" value="jsp/register.jsp" scope="request" />
            <c:set var="registerWarning" value="project.empty" scope="application" />
            <br/><jsp:include page="header.jsp"/><br/>
            <form action="webBetController" method="get">
                <input type="hidden" name="command" value="REGISTER_COMMAND"/>
                <table>
                    <tr>
                        <td><label for="registerLogin"><fmt:message key="register.label.login"/> </label> </td>
                        <td><input type="text" id="registerLogin" name="registerLogin" /></td>
                    </tr>
                    <tr>
                        <td><label for="registerPassword"><fmt:message key="register.label.password"/> </label> </td>
                        <td><input type="password" id="registerPassword" name="registerPassword" /></td>
                    </tr>
                    <tr>
                        <td><label for="registerConfirm"><fmt:message key="register.label.confirm"/> </label> </td>
                        <td><input type="password" id="registerConfirm" name="registerConfirm" /></td>
                    </tr>
                    <tr>
                        <td><label for="registerName"><fmt:message key="register.label.name"/> </label> </td>
                        <td><input type="text" id="registerName" name="registerName" /></td>
                    </tr>
                <table/>
                <fmt:message key="register.button.submit" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}" class="btn btn-primary">

            </form><br/>
            <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
            <label><fmt:message key="${registerWarning}"/></label>
        </div>
    </div>
</div>
</body>
</html>
