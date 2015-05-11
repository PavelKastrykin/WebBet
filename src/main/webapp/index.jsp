<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" />
    <script>
        function submitLogin()
        {
            if (document.getElementById("username").value == "" || document.getElementById("password").value == ""){
                alert("Insert all fields!");
                return false;
            }
            document.forms["loginForm"].submit();
            return true;
        }
    </script>
    <title><fmt:message key="title.index" /></title>
</head>
<body>
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <c:set var="pageID" value="index.jsp" scope="request" />
                <jsp:include page="jsp/header.jsp"/><br/>
                <form class="form-horizontal" id="loginForm" action="webBetController" method="post" >
                    <input type="hidden" name="command" value="LOGIN_COMMAND"/>
                    <div class="input-group input-group-sm">
                        <label for="username"><fmt:message key="login.label.username" /></label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>
                    <div class="input-group input-group-sm">
                        <label for="password"><fmt:message key="login.label.password" /></label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div><br/>
                    <fmt:message key="login.button.submit" var="buttonValue"/>
                    <input type="submit" name="submit" value="${buttonValue}" onclick="return submitLogin()" class="btn btn-primary"/>
                    <a href="register.jsp"><fmt:message key="register.button.submit" /> </a>
                </form>
                <a href="webBetController?command=DISPLAY_MATCHES_COMMAND"><fmt:message key="login.home.reff" /> </a>
            </div>
        </div>
    </div>
</body>
</html>
