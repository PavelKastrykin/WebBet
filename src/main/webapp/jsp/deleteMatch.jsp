<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${localeValue == '' || localeValue == null ? 'en_US' : localeValue}"/>
<fmt:setBundle basename="locale.text"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <script type="text/javascript">
        function confirmDeletion(id) {
            if (confirm('Confirm?')) {
                document.forms['matchDelete' + id].submit();
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
    <form action="webBetController" name="matchDelete${matchToDelete}" id="matchDelete${matchToDelete}" method="get">
      <fmt:message key="bookmaker.panel.deleteMatch.reff" var="buttonValue"/>
      <input type="hidden" name="command" value="DELETE_MATCH_COMMAND"/>
      <input type="text" name="matchToDeleteId" value="${matchToDelete}"/>
      <input type="button" value="${buttonValue}" onclick="confirmDeletion('${matchToDelete}')" class="btn btn-primary" />
    </form>
</body>
</html>
