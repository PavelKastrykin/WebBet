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
    <c:set var="pageID" value="jsp/editMatch.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <form id="editMatchForm" action="webBetController" method="get">
        <input type="hidden" name="command" value="EDIT_MATCH_COMMAND">
        <input type="hidden" name="matchid" value="${matchToEdit.matchId}">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th><fmt:message key="matches.table.match" /></th>
                <th><fmt:message key="matches.table.date" /></th>
                <th><fmt:message key="matches.table.score" /></th>
                <th><fmt:message key="matches.table.winner1" /></th>
                <th><fmt:message key="matches.table.draw" /></th>
                <th><fmt:message key="matches.table.winner2" /></th>
                <th><fmt:message key="matches.table.status" /></th>
            </tr>
            <tr>
                <td>${matchToEdit.matchId}</td>
                <td>${matchToEdit.matchName}</td>
                <td><fmt:formatDate value="${matchToEdit.startTime}" dateStyle="short"/></td>
                <td><input type="text" name="matchScore" value="${matchToEdit.matchScore}"></td>
                <td><input type="text" name="winCoef" value="${matchToEdit.winCoef}"></td>
                <td><input type="text" name="drawCoef" value="${matchToEdit.drawCoef}"></td>
                <td><input type="text" name="looseCoef" value="${matchToEdit.looseCoef}"></td>
                <td>
                    <select id="status" name="status">
                        <option value="ACTIVE" ${matchToEdit.status == 'ACTIVE' ? 'selected' : ''}><fmt:message key="edit.active"/></option>
                        <option value="SOON" ${matchToEdit.status == 'SOON' ? 'selected' : ''}><fmt:message key="edit.soon"/></option>
                        <option value="CLOSED" ${matchToEdit.status == 'CLOSED' ? 'selected' : ''}><fmt:message key="edit.closed"/></option>
                    </select>
                </td>
            </tr>
        </table>
        <fmt:message key="editmatch.button.edit" var="buttonValue"/>
        <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" />
    </form>
</body>
</html>
