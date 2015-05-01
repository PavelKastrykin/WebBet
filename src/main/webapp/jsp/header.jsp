<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<body>
    <form action="webBetController" method="get">
        <label><fmt:message key="header.language" /></label>
        <input type="hidden" name="command" value="LOCALE_COMMAND"/>
        <input type="hidden" name="hiddenPageID" value="${pageID}" />
        <select id="language" name="language" onchange="this.form.submit()">
            <option value="en_US" ${localeValue == 'en_US' ? 'selected' : ''}>English</option>
            <option value="ru_RU" ${localeValue == 'ru_RU' ? 'selected' : ''}>Русский</option>
        </select>
    </form>
</body>
</html>
