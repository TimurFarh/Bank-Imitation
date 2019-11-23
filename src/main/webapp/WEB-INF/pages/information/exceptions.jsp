<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 23.11.2019
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${exception.toString() eq 'NotEnoughMoneyException'}">Not enough money</c:if>
        <c:if test="${exception.toString() eq 'WrongAccountException'}">Wrong account</c:if>
        <c:if test="${exception.toString() eq 'WrongAmountException'}">Wrong amount</c:if>
    </title>
</head>
<body>
    <c:if test="${exception.toString() eq 'NotEnoughMoneyException'}">
        <h2>You don't have enough money on your account</h2>
    </c:if>
    <c:if test="${exception.toString() eq 'WrongAccountException'}">
        <h2>Account doesn't exists</h2>
    </c:if>
    <c:if test="${exception.toString() eq 'WrongAmountException'}">
        <h2>You enter wrong amount for transaction</h2>
    </c:if>
<a href="/${client}/${client.id}/accounts">Return</a>
</body>
</html>
