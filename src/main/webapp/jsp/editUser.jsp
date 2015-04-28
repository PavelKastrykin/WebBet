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
    <c:set var="pageID" value="jsp/editUser.jsp" scope="request" />
    <jsp:include page="header.jsp"/>
    <jsp:include page="loginLogoutHeader.jsp" />
    <form id="editUserForm" action="webBetController" method="get">
        <input type="hidden" name="command" value="CONFIRM_EDIT_USER_COMMAND">
        <input type="hidden" name="userLogin" value="${userToEdit.login}">
        <table border="1" cellpadding="5" cellspacing="5">
            <tr>
                <th>#id</th>
                <th><fmt:message key="admin.user.table.login" /></th>
                <th><fmt:message key="admin.user.table.name" /></th>
                <th><fmt:message key="admin.user.table.role" /></th>
            </tr>
            <tr>
                <td>${userToEdit.userID}</td>
                <td>${userToEdit.login}</td>
                <td>${userToEdit.name}</td>
                <td>
                    <select id="role" name="role">
                        <option value="ADMIN" ${userToEdit.userRole == 'ADMIN' ? 'selected' : ''}><fmt:message key="edit.admin" /></option>
                        <option value="BOOK" ${userToEdit.userRole == 'BOOK' ? 'selected' : ''}><fmt:message key="edit.book" /></option>
                        <option value="USER" ${userToEdit.userRole == 'USER' ? 'selected' : ''}><fmt:message key="edit.user" /></option>
                        <option value="BLOCKED" ${userToEdit.userRole == 'BLOCKED' ? 'selected' : ''}><fmt:message key="edit.blocked" /></option>
                    </select>
                </td>
            </tr>
        </table>
        <fmt:message key="editmatch.button.edit" var="buttonValue"/>
        <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" />
    </form>
</body>
</html>
