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
    <c:set var="pageID" value="jsp/adminUserPage.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>#id</th>
            <th><fmt:message key="admin.user.table.login"/></th>
            <th><fmt:message key="admin.user.table.name"/></th>
            <th><fmt:message key="admin.user.table.role"/></th>
        </tr>
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.userID}</td>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.userRole}</td>
                <td><a href="webBetController?userLogin=${user.login}&command=EDIT_USER_COMMAND" ><fmt:message key="admin.bet.table.edit"/></a> </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${currentPage != 1}">
        <td><a href="webBetController?command=CREATE_USER_LIST&page=${currentPage - 1}"><fmt:message key="matches.table.previous" /></a> </td>
    </c:if>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${numberOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="webBetController?command=CREATE_USER_LIST&page=${i}">${i}</a> </td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <c:if test="${currentPage lt numberOfPages}">
        <td><a href="webBetController?command=CREATE_USER_LIST&page=${currentPage + 1}"><fmt:message key="matches.table.next" /></a></td>
    </c:if>
    <br/>
    <a href="home.jsp"><fmt:message key="login.home.reff" /> </a>
</body>
</html>
