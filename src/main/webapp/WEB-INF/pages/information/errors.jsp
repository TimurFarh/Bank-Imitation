<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${error.toString() eq 'NotEnoughMoneyerror'}">Not enough money</c:if>
        <c:if test="${error.toString() eq 'WrongAccounterror'}">Wrong account</c:if>
        <c:if test="${error.toString() eq 'WrongAmounterror'}">Wrong amount</c:if>
    </title>
</head>
<body>
    <c:if test="${error.toString() eq 'NotEnoughMoneyerror'}">
        <h2>You don't have enough money on your account</h2>
    </c:if>
    <c:if test="${error.toString() eq 'WrongAccounterror'}">
        <h2>Account doesn't exists</h2>
    </c:if>
    <c:if test="${error.toString() eq 'WrongAmounterror'}">
        <h2>You enter wrong amount for transaction</h2>
    </c:if>
<a href="/${client}/${client.id}/accounts">Return</a>
</body>
</html>
