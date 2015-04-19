<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <c:choose>
            <c:when test="${userValue.login} == null || ${userValue.login} == '' ">
                <form>
                    <a href="index.jsp"><fmt:message key="loginLogoutHeader.login.reff" /></a>
                    <br/>
                </form>
            </c:when>
            <c:otherwise>
                <form action="webBetController" method="get">
                    <input type="hidden" name="command" value="LOGOUT_COMMAND"/>
                    <label for="logout" >${userValue.login}</label>
                    <fmt:message key="loginLogoutHeader.logout.button" var="logoutButton" />
                    <input type="submit" id="logout" name="logout" value="${logoutButton}">
                </form>

            </c:otherwise>
        </c:choose>
    </body>
</html>
