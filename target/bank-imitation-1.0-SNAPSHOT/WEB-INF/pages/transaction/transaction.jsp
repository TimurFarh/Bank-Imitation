<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
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
        <div class="header">Deposit into account №${account.id}</div>
        <input type="hidden" name="to" value="${account.id}">
        <p><input type="text" name="from" placeholder="From" class="field" maxlength="50" required title="Enter external account name"></p>
        <p><input type="number" name="amount" placeholder="Amount" class="field" required min="1"></p>
        <p><input type="submit" class="submit" value="Deposit"></p>
    </c:if>
    <c:if test="${operation.ordinal() == 1}">
        <input type="hidden" name="from" value="${account.id}">
        <div class="header">Withdraw form account №${account.id}</div>
        <p><input type="text" name="to" placeholder="Where to transfer" class="field" maxlength="50" required></p>
        <p><input type="number" name="amount" id="amount" placeholder="Amount" class="field" required min="1"></p>
        <p><input type="submit" class="submit" value="Withdraw"></p>
    </c:if>
    <c:if test="${operation.ordinal() == 2}">
        <input type="hidden" name="from" value="${account.id}">
        <input type="hidden" name="amount" value="${account.balance}">
        <div class="header">
            Close account №${account.id}<br>
            Enter number of account or external account name
        </div>
        <p><input type="text" name="to" id="to" class="field" maxlength="50" required></p>
        <p><input type="submit" class="submit" value="Close account"></p>
    </c:if>
</form>
<form action="/${client}/${client.id}/accounts" ><input type="submit" class="cancel" value="Back"></form>
</body>
</html>
