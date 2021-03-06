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
            <c:set var="pageID" value="jsp/editBet.jsp" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <form id="editBetForm" action="webBetController" method="post">
                <input type="hidden" name="command" value="CONFIRM_EDIT_BET_COMMAND">
                <input type="hidden" name="betid" value="${betToEdit.betId}">
                <h3><em><mark><fmt:message key="edit.bet.header"/></mark></em></h3>
                <table class="table">
                    <tr>
                        <th>#id</th>
                        <th><fmt:message key="admin.bet.table.login"/></th>
                        <th><fmt:message key="admin.bet.table.match"/></th>
                        <th><fmt:message key="admin.bet.table.date"/></th>
                        <th><fmt:message key="admin.bet.table.score"/></th>
                        <th><fmt:message key="admin.bet.table.prediction"/></th>
                        <th><fmt:message key="admin.bet.table.charge"/></th>
                        <th><fmt:message key="admin.bet.table.sum"/></th>
                        <th><fmt:message key="admin.bet.table.coef"/></th>
                        <th><fmt:message key="admin.bet.table.won"/></th>
                        <th><fmt:message key="admin.bet.table.status"/></th>

                    </tr>
                    <tr>
                        <td>${betToEdit.betId}</td>
                        <td>${betToEdit.user.login}</td>
                        <td>${betToEdit.match.matchName}</td>
                        <td><fmt:formatDate value="${betToEdit.match.startTime}" dateStyle="short"/></td>
                        <td>${betToEdit.match.matchScore}</td>
                        <td>${betToEdit.prediction}</td>
                        <td>
                            <select id="charge" name="charge">
                                <option value="TRUE" ${betToEdit.moneyCharge == 'TRUE' ? 'selected' : ''}><fmt:message key="edit.true" /></option>
                                <option value="FALSE" ${betToEdit.moneyCharge == 'FALSE' ? 'selected' : ''}><fmt:message key="edit.false" /></option>
                            </select>
                        </td>
                        <td>${betToEdit.sum}</td>
                        <td>${betToEdit.currentCoef}</td>
                        <td>
                            <select id="won" name="won">
                                <option value="TRUE" ${betToEdit.won == 'TRUE' ? 'selected' : ''}><fmt:message key="edit.true" /></option>
                                <option value="FALSE" ${betToEdit.won == 'FALSE' ? 'selected' : ''}><fmt:message key="edit.false" /></option>
                            </select>
                        </td>
                        <td>
                            <select id="status" name="status">
                                <option value="OPEN" ${betToEdit.status == 'OPEN' ? 'selected' : ''}><fmt:message key="edit.open" /></option>
                                <option value="CLOSED" ${betToEdit.status == 'CLOSED' ? 'selected' : ''}><fmt:message key="edit.closed" /></option>
                            </select>
                        </td>
                    </tr>
                </table>
                <fmt:message key="editbet.button.edit" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" class="btn btn-primary" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
