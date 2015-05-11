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
    <meta http-equiv="refresh" content="20">
    <script>
        function setBetNum(betNum, betCoef)
        {
            if (document.getElementById("moneySum").value == ""){
                <fmt:message key='make.bet.empty' var="alertField" />
                alert("${alertField}");
                return false;
            }
            document.getElementById("selectedBetNum").value = betNum;
            document.getElementById("selectedBetCoef").value = betCoef;
            document.forms["betForm"].submit();
            return true;
        }
        function checkNumberInput(ob) {
            var key = event.keyCode || event.which;
            key = String.fromCharCode(key);
            if(key.length == 0) return;
            var validChars = /^[0-9]*\.?[0-9]*$/;
            if(!validChars.test(key)){
                event.returnValue = false;
                if(event.preventDefault)
                    event.preventDefault();
            }
        }
    </script>
    <title></title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <c:set var="pageID" value="webBetController?matchId=${matchToBet.matchId}&command=CREATE_BET_FORM_COMMAND" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <jsp:include page="loginLogoutHeader.jsp" />
            <form id="betForm" action="webBetController" method="post">
                <input type="hidden" name="command" value="ADD_BET_COMMAND">
                <input type="hidden" id="selectedBetNum" name="selectedBetNum">
                <input type="hidden" id="selectedBetCoef" name="selectedBetCoef">
                <input type="hidden" id="idOfMatch" name="idOfMatch" value="${matchToBet.matchId}">
                <table class="table">
                    <tr>
                        <th><fmt:message key="matches.table.match" /></th>
                        <th><fmt:message key="matches.table.date" /></th>
                        <th><fmt:message key="matches.table.winner1" /></th>
                        <th><fmt:message key="matches.table.draw" /></th>
                        <th><fmt:message key="matches.table.winner2" /></th>
                    </tr>
                    <tr>
                        <td>${matchToBet.matchName}</td>
                        <td>${matchToBet.startTime}</td>
                        <td><input type="button" value="${matchToBet.winCoef}" onclick="setBetNum('first', '${matchToBet.winCoef}');" class="btn btn-primary" style="width: 50px"></td>
                        <td><input type="button" value="${matchToBet.drawCoef}" onclick="setBetNum('draw', '${matchToBet.drawCoef}');" class="btn btn-primary" style="width: 50px"></td>
                        <td><input type="button" value="${matchToBet.looseCoef}" onclick="setBetNum('second', '${matchToBet.looseCoef}');" class="btn btn-primary" style="width: 50px"></td>
                    </tr>
                </table><br/>
                <label for="moneySum"><fmt:message key="make.bet.entersum" /></label>
                <input type="text" id="moneySum" name="moneySum" maxlength="9" onkeypress="return checkNumberInput(this)"/>BYR
            </form>
            <br/>
            <fmt:message key="make.bet.count.begin" /><span id="seconds">20</span><fmt:message key="make.bet.count.end" />
            <script>
                var seconds = 20;
                setInterval(
                        function(){
                            document.getElementById('seconds').innerHTML = --seconds;
                        }, 1000
                );
            </script><br/><br/>
            <a href="webBetController?command=DISPLAY_MATCHES_COMMAND"><fmt:message key="login.home.reff" /> </a>
        </div>
    </div>
</div>
</body>
</html>
