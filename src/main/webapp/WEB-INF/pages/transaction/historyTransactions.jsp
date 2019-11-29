<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>History of transactions</title>
</head>
<body>
<table>
    <caption>
        <c:if test="${!empty accountId}">Account №${accountId}</c:if>
        <c:if test="${empty accountId}">Transactions of ${client.firstName} ${client.lastName}</c:if>
    </caption>
    <tr>
        <td>Operation</td>
        <c:if test="${!empty accountId}">
            <td>From(To)</td>
        </c:if>
        <c:if test="${empty accountId}">
        <td>From</td>
        <td>To</td>
        </c:if>
        <td>Amount</td>
        <td>Date</td>
    </tr>
    <c:forEach var="transaction" items="${transactions}">
        <tr>
            <td>${transaction.operation}</td>
            <c:if test="${!empty accountId}">
                <td>
                    <c:if test="${transaction.operation eq 'Deposit'}">${transaction.from}</c:if>
                    <c:if test="${transaction.operation eq 'Withdraw'}">${transaction.to}</c:if>
                </td>
            </c:if>
            <c:if test="${empty accountId}">
            <td>${transaction.from}</td>
            <td>${transaction.to}</td>
            </c:if>
            <td>${transaction.amount}</td>
            <td><fmt:formatDate value="${transaction.date}" pattern="dd.MM.yyyy HH:mm" /></td>
        </tr>
    </c:forEach>
</table>

<form action="/${client}/${client.id}/accounts" method="GET"><input type="submit" class="cancel" value="Back"></form>
</body>
</html>
