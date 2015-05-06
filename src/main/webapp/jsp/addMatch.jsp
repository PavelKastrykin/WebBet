<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <c:set var="pageID" value="jsp/addMatch.jsp" scope="request" />
            <c:set var="addMatchWarning" value="project.empty" scope="application" />
            <jsp:include page="header.jsp"/>
            <jsp:include page="loginLogoutHeader.jsp" />
            <form action="webBetController" method="get">
                <input type="hidden" name="command" value="ADD_MATCH_COMMAND"/>
                <table>
                    <tr>
                        <td><label for="matchName"><fmt:message key="addmatch.label.match"/> </label> </td>
                        <td><input type="text" id="matchName" name="matchName" /></td>
                    </tr>
                    <tr>
                        <td><label for="matchDate"><fmt:message key="addmatch.label.date"/> </label> </td>
                        <td><input type="date" id="matchDate" name="matchDate" /></td>
                    </tr>
                <table/>
                <fmt:message key="addmatch.button.add" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" class="btn btn-primary" />
            </form><br/>
            <a href="home.jsp"><fmt:message key="login.home.reff" /> </a><br/>
            <label><fmt:message key="${addMatchWarning}"/></label>
        </div>
    </div>
</div>
</body>
</html>
