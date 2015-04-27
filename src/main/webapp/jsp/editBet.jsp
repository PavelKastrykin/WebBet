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
    <c:set var="pageID" value="jsp/editBet.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <form id="editBetForm" action="webBetController" method="get">
        <input type="hidden" name="command" value="CONFIRM_EDIT_BET_COMMAND">
        <input type="hidden" name="betid" value="${betToEdit.betId}">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th>Login</th>
                <th>Match</th>
                <th>Date</th>
                <th>Score</th>
                <th>Prediction</th>
                <th>Charge</th>
                <th>Sum</th>
                <th>Coef</th>
                <th>Won</th>
                <th>Status</th>

            </tr>
            <tr>
                <td>${betToEdit.betId}</td>
                <td>${betToEdit.login}</td>
                <td>${betToEdit.footballMatchName}</td>
                <td><fmt:formatDate value="${betToEdit.footballMatchDate}" dateStyle="short"/></td>
                <td>${betToEdit.matchScore}</td>
                <td>${betToEdit.prediction}</td>
                <td>
                    <select id="charge" name="charge">
                        <option value="TRUE" ${betToEdit.moneyCharge == 'TRUE' ? 'selected' : ''}>TRUE</option>
                        <option value="FALSE" ${betToEdit.moneyCharge == 'FALSE' ? 'selected' : ''}>FALSE</option>
                    </select>
                </td>
                <td>${betToEdit.sum}</td>
                <td>${betToEdit.currentCoef}</td>
                <td>
                    <select id="won" name="won">
                        <option value="TRUE" ${betToEdit.won == 'TRUE' ? 'selected' : ''}>TRUE</option>
                        <option value="FALSE" ${betToEdit.won == 'FALSE' ? 'selected' : ''}>FALSE</option>
                    </select>
                </td>
                <td>
                    <select id="status" name="status">
                        <option value="OPEN" ${betToEdit.status == 'OPEN' ? 'selected' : ''}>OPEN</option>
                        <option value="CLOSED" ${betToEdit.status == 'CLOSED' ? 'selected' : ''}>CLOSED</option>
                    </select>
                </td>
            </tr>
        </table>
        <fmt:message key="editmatch.button.edit" var="buttonValue"/>
        <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" />
    </form>

</body>
</html>