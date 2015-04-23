<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="resources.locale.text"/>
<html>
<head>
    <script>
        function setBetNum(betNum, betCoef)
        {
            alert("Я зашёл, ура!");
            document.getElementById("selectedBetNum").value = betNum;
            document.getElementById("selectedBetCoef").value = betCoef;
            document.forms["betForm"].submit();
            return true;
        }
    </script>
    <title></title>
</head>
<body>
    <c:set var="pageID" value="jsp/makeBet.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <form id="betForm" action="webBetController" method="post">
        <input type="hidden" name="command" value="ADD_BET_COMMAND">
        <input type="hidden" id="selectedBetNum" name="selectedBetNum">
        <input type="hidden" id="selectedBetCoef" name="selectedBetCoef">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th>Name</th>
                <th>Date</th>
                <th>Win1</th>
                <th>Draw</th>
                <th>Win2</th>
            </tr>
            <tr>
                <td>${matchToBet.matchId}</td>
                <td>${matchToBet.matchName}</td>
                <td>${matchToBet.startTime}</td>
                <td><input type="button" value="${matchToBet.winCoef}" onclick="setBetNum('first', '${matchToBet.winCoef}');"></td>
                <td><input type="button" value="${matchToBet.drawCoef}" onclick="setBetNum('draw', '${matchToBet.drawCoef}');"></td>
                <td><input type="button" value="${matchToBet.looseCoef}" onclick="setBetNum('second', '${matchToBet.looseCoef}');"></td>
            </tr>
        </table><br/>
        <label for="moneySum">Enter sum of bet:</label>
        <input type="text" id="moneySum" name="moneySum"/>
    </form>
    <br/>
    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
</body>
</html>
