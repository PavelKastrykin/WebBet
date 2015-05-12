<!DOCTYPE html>
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
            <c:set var="pageID" value="jsp/addMatch.jsp" scope="request" />
            <c:set var="addMatchWarning" value="project.empty" scope="application" />
            <br/><jsp:include page="header.jsp"/><br/>
            <form action="webBetController" method="post">
                <input type="hidden" name="command" value="ADD_MATCH_COMMAND"/>
                <h3><em><mark><fmt:message key="addmatch.header"/></mark></em></h3>
                <table class="table" style="width: auto !important;">
                    <tr>
                        <td><label for="matchName"><fmt:message key="addmatch.label.match"/> </label> </td>
                        <td><input type="text" id="matchName" maxlength="45" name="matchName" /></td>
                    </tr>
                    <tr>
                        <td><label for="matchDate"><fmt:message key="addmatch.label.date"/> </label> </td>
                        <td><input type="date" id="matchDate" name="matchDate" /></td>
                    </tr>
                </table>
                <fmt:message key="addmatch.button.add" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" class="btn btn-primary" />
            </form><br/>
            <label><fmt:message key="${addMatchWarning}"/></label>
        </div>
    </div>
</div>
</body>
</html>
