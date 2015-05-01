<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <script>
        function submitLogin()
        {
            if (document.getElementById("username").value == "" || document.getElementById("password").value == ""){
                alert("Insert all fields!")
                return false;
            }
            document.forms["loginForm"].submit();
            return true;
        }
    </script>
    <title><fmt:message key="title.index" /></title>
</head>
<body>
    <c:set var="pageID" value="index.jsp" scope="request" />
    <jsp:include page="jsp/header.jsp"/>
    <form id="loginForm" action="webBetController" method="post" >
        <input type="hidden" name="command" value="LOGIN_COMMAND"/>
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
        <input type="submit" name="submit" value="${buttonValue}" onclick="return submitLogin()"/>
        <a href="register.jsp"><fmt:message key="register.button.submit" /> </a>
    </form>
    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
</body>
</html>
