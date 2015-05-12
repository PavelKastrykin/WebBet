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
            <c:set var="pageID" value="webBetController?userLogin=${userToEdit.login}&command=EDIT_USER_COMMAND" scope="request" />
            <br/><jsp:include page="header.jsp"/><br/>
            <form id="editUserForm" action="webBetController" method="post">
                <input type="hidden" name="command" value="CONFIRM_EDIT_USER_COMMAND">
                <input type="hidden" name="userLogin" value="${userToEdit.login}">
                <table class="table">
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
                                <option value="BOOKMAKER" ${userToEdit.userRole == 'BOOKMAKER' ? 'selected' : ''}><fmt:message key="edit.bookmaker" /></option>
                                <option value="USER" ${userToEdit.userRole == 'USER' ? 'selected' : ''}><fmt:message key="edit.user" /></option>
                                <option value="BLOCKED" ${userToEdit.userRole == 'BLOCKED' ? 'selected' : ''}><fmt:message key="edit.blocked" /></option>
                            </select>
                        </td>
                    </tr>
                </table>
                <fmt:message key="edituser.button.edit" var="buttonValue"/>
                <input type="submit" name="submit" value="${buttonValue}" onclick="return confirm('Confirm?')" class="btn btn-primary" />
            </form>
        </div>
    </div>
</div>
</body>
</html>
