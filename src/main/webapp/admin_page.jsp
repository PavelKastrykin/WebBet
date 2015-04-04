<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
<head>
    <title></title>
</head>
<body>
    <c:set var="pageID" value="admin_page.jsp" scope="request"/>
    <jsp:include page="header.jsp"/>
    <h1><fmt:message key="admin_page.greeting"/></h1>
</body>
</html>
