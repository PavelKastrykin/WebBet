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
    <c:set var="pageID" value="jsp/errorPage.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />

    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
</body>
</html>
