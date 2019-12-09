<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>
        <c:if test="${error.toString() eq 'NotEnoughMoneyException'}">Not enough money</c:if>
        <c:if test="${error.toString() eq 'WrongAccountException'}">Wrong account</c:if>
    </title>
</head>
<body>
    <c:if test="${error.toString() eq 'NotEnoughMoneyException'}">
        <h2>You don't have enough money on your account</h2>
    </c:if>
    <c:if test="${error.toString() eq 'WrongAccountException'}">
        <h2>Account doesn't exists or you entered the same account</h2>
    </c:if>
    <form action="/${client.id}/accounts" ><input type="submit" class="cancel" value="Back"></form>
</body>
</html>
