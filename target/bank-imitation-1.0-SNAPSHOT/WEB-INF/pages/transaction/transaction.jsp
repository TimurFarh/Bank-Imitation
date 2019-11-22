<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 20.11.2019
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${operation.ordinal() == 0}">Deposit</c:if>
        <%--<c:if test="${operation == 'Close'}">Close</c:if>
        <c:if test="${operation == 'Withdraw'}">Withdraw</c:if>
        --%>
    </title>
</head>
<body>
    <form action="/transaction/${clientId}/${account.id}/${operation.ordinal()}" method="POST">
        <input type="hidden" name="operation" value="${operation}">
        <c:if test="${operation.ordinal() == 0}">
            <label for="from">Where to transfer money from</label>
            <input type="text" name="from" id="from">
            <input type="hidden" name="to" value="${account.id}">
            <label for="amount">Enter amount</label>
            <input type="number" name="amount" id="amount">
            <input type="submit" value="Transaction">
        </c:if>
        <%--
        <c:if test="${operation == 'Withdraw'}">
            <label for="from">Where to transfer money from</label>
            <input type="text" name="from" id="from">
            <input type="hidden" name="to" value="${account.id}">
            <label for="amount">Enter amount</label>
            <input type="number" name="amount" id="amount">
        </c:if>
        --%>
    </form>
</body>
</html>
