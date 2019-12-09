<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>Accounts</title>
</head>
<body>
<c:set var="sum" value="0"/>
<table>
    <caption>${client.firstName} ${client.lastName}</caption>
    <tr>
        <td>Number of account</td>
        <td>Account name</td>
        <td>Balance</td>
        <td colspan="4">Operations</td>
    </tr>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.balance}</td>
            <td>
                <a href="/deposit-account/${client.id}/${account.id}">
                    <img src="<c:url value="/res/pictures/deposit.png"/>" width="35" height="35" title="Deposit">                   <!-- Deposit -->
                </a>
            </td>

            <td>
                <a href="/withdraw-account/${clientId}/${account.id}">
                    <img src="<c:url value="/res/pictures/withdraw.png"/>" width="35" height="35" title="Withdraw/Transfer">        <!-- Withdraw -->
                </a>
            </td>


            <td>
                <a href="/account-transactions/${account.id}/${client.id}">
                    <img src="<c:url value="/res/pictures/transactions.png"/>" width="35" height="35" title="Transactions">         <!-- Account transactions -->
                </a>
            </td>


            <td>
                <a href="/delete-account/${client.id}/${account.id}">
                    <img src="<c:url value="/res/pictures/close.png"/>" width="35" height="35" title="Close account">               <!-- Close -->
                </a>
            </td>     <!-- Close account -->

        </tr>
    </c:forEach>
</table>
<a href="/add-new-account/${client.id}" class="add"><span>▶</span>Add new account<span>◀</span></a>
<form action="/client-transactions/${client.id}" class="date"> <!-- Client transactions -->
    <label for="after" class="date">After</label>
    <input type="date" name="after" id="after" required>
    <label for="before" class="date">Before</label>
    <input type="date" name="before" id="before" required>
    <input type="submit" class="date" value="Transactions">
</form>
<form action="/"><input type="submit" class="cancel" value="List of clients"></form>
</body>
</html>
