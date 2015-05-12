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
    <script>
        function validate(){
            var win = document.getElementsByName("winCoef")[0].value;
            var draw = document.getElementsByName("drawCoef")[0].value;
            var loose = document.getElementsByName("looseCoef")[0].value;
            if(isNaN(win) || isNaN(draw) || isNaN(loose)){
                document.getElementById("validationWarning").innerText = '<fmt:message key="editmatch.validation.notnumeric"/>';
                return false;
            }
            if(win > 1000 || draw > 1000 || loose > 1000){
                document.getElementById("validationWarning").innerText = '<fmt:message key="editmatch.validation.toobig"/>';
                return false;
            }
            if(win <= 0 || draw <= 0 || loose <= 0){
                document.getElementById("validationWarning").innerText = '<fmt:message key="editmatch.validation.lesszero"/>';
                return false;
            }
            return true;

        }
    </script>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <c:set var="pageID" value="webBetController?matchId=${matchToEdit.matchId}&command=CREATE_MATCH_EDIT_FORM_COMMAND" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <form id="editMatchForm" action="webBetController" method="post" onsubmit="return validate()">
                <input type="hidden" name="command" value="EDIT_MATCH_COMMAND">
                <input type="hidden" name="matchId" value="${matchToEdit.matchId}">
                <table class="table">
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
                <input type="submit" name="submit" id="submitSave" value="${buttonValue}" onclick="return confirm('Confirm?')" class="btn btn-primary"/>
                <label id="validationWarning" > </label>
            </form>
        </div>
    </div>
</div>
</body>
</html>
