<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Welcome to WebBet!</title>
</head>
<body>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Match</th>
            <th>Starts at</th>
            <th>Score</th>
            <th>Home win</th>
            <th>Draw</th>
            <th>Guest win</th>
            <th>Status</th>
        </tr>

        <c:forEach var="match" items="${matchesList}">
            <tr>
                <td>${match.mathName}</td>
                <td>${match.startTime}</td>
                <td>${match.matchScore}</td>
                <td>${match.winCoef}</td>
                <td>${match.drawCoef}</td>
                <td>${match.looseCoef}</td>
                <td>${match.status}</td>
            </tr>
        </c:forEach>
    </table>

    <c:if test="${currentPage != 1}">
        <td><a href="matches.do?page=${currentPage - 1}">Previous</a> </td>
    </c:if>

    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <a href="matches.do?page=${i}">${i}</a>
                        </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <c:if test="${currentPage lt noOfPages}">
        <td><a href="matches.do?page=${currentPage + 1}">Next</a> </td>
    </c:if>
</body>
</html>
