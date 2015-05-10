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
    <title><fmt:message key="title.home" />${userValue.name}</title>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <c:set var="pageID" value="webBetController?command=DISPLAY_MATCHES_COMMAND" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <jsp:include page="loginLogoutHeader.jsp" />
            <br/><br/>

            <c:if test="${sessionScope.userValue.userRole == 'ADMIN' || sessionScope.userValue.userRole == 'BOOKMAKER'}" >
                <jsp:include page="bookmakerPanel.jsp" />
            </c:if>
            <c:if test="${sessionScope.userValue.userRole == 'ADMIN' || sessionScope.userValue.userRole == 'USER'}" >
                <jsp:include page="myBetsHeader.jsp"/>
            </c:if>
            <c:if test="${sessionScope.userValue.userRole == 'ADMIN'}" >
                <jsp:include page="adminHeader.jsp"/>
            </c:if>
            <form action="webBetController" name="allmatches" id="allmatches" method="get">
                <input type="hidden" name="command" value="DISPLAY_MATCHES_COMMAND" />
                <fmt:message key="matches.button.show" var="buttonShow" />
                <input type="submit" name="submit" value="${buttonShow}" class="btn btn-primary"/>
            </form>
                <br/><br/>
                <table class="table">
                    <thead>
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
                    </thead>
                    <tbody>
                    <c:forEach var="match" items="${matchesList}">
                        <tr>
                            <td>${match.matchId}</td>
                            <td>${match.matchName}</td>
                            <td><fmt:formatDate value="${match.startTime}" dateStyle="short"/></td>
                            <td>${match.matchScore}</td>
                            <td>${match.winCoef}</td>
                            <td>${match.drawCoef}</td>
                            <td>${match.looseCoef}</td>
                            <td>${match.status}</td>
                            <c:if test="${match.status == 'ACTIVE' && sessionScope.userValue != null && sessionScope.userValue.userRole != 'BOOKMAKER'}">
                                <td>
                                    <a href="webBetController?matchId=${match.matchId}&command=CREATE_BET_FORM_COMMAND" >Bet!</a>
                                </td>
                            </c:if>
                            <c:if test="${sessionScope.userValue.userRole == 'BOOKMAKER'}">
                                <td>
                                    <a href="webBetController?matchId=${match.matchId}&command=CREATE_MATCH_EDIT_FORM_COMMAND">Edit</a>
                                </td>
                                <td>
                                    <c:set var="matchToDelete" value="${match.matchId}" scope="request"/>
                                    <jsp:include page="deleteMatch.jsp"/>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${currentPage != 1}">
                    <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
                </c:if>
                <table class="table">
                    <tr>
                        <c:forEach begin="1" end="${numberOfPages}" var="i">
                            <c:choose>
                                <c:when test="${currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${i}">${i}</a> </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </table>
                <c:if test="${currentPage lt numberOfPages}">
                    <td><a href="webBetController?command=DISPLAY_MATCHES_COMMAND&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a> </td>
                </c:if>
        </div>
    </div>
</div>
</body>
</html>
