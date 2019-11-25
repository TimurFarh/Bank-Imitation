<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 24.11.2019
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>History of transactions</title>
</head>
<body>
<table>
    <tr>
        <td>Operation</td>
        <td>From</td>
        <td>To</td>
        <td>Amount</td>
        <td>Date</td>
    </tr>
    <c:forEach var="transaction" items="${transactions}">
        <tr>
            <td>${transaction.operation}</td>
            <td>${transaction.from}</td>
            <td>${transaction.to}</td>
            <td>${transaction.amount}</td>
            <td><fmt:formatDate value="${transaction.date}" pattern="dd-MM-yyyy hh:mm" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
