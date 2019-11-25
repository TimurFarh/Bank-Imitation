<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>
        <c:if test="${operation.ordinal() == 0}">Deposit</c:if>
        <c:if test="${operation.ordinal() == 1}">Withdraw</c:if>
        <c:if test="${operation.ordinal() == 2}">Close account</c:if>
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
            <input type="submit" value="Deposit">
        </c:if>
        <c:if test="${operation.ordinal() == 1}">
            <input type="hidden" name="from" value="${account.id}">
            <label for="to">Where to transfer money</label>
            <input type="text" name="to" id="to">
            <label for="amount">Enter amount</label>
            <input type="number" name="amount" id="amount">
            <input type="submit" value="Withdraw">
        </c:if>
        <c:if test="${operation.ordinal() == 2}">
            <input type="hidden" name="from" value="${account.id}">
            <input type="hidden" name="amount" value="${account.balance}">
            <label for="to">Enter number of account or external account name</label>
            <input type="text" name="to" id="to">
            <input type="submit" value="Close account">
        </c:if>
    </form>
</body>
</html>
