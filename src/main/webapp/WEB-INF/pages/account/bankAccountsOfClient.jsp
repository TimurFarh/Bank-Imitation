<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
<c:set var="sum" value="0"/>
    <table>
        <tr>
            <td>Number of account</td>
            <td>Account name</td>
            <td>Balance</td>
            <td>Operations</td>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.balance}</td>
                <td><a href="/deposit-account/${client.id}/${account.id}">Deposit</a></td>
                <td><a href="/withdraw-account/${clientId}/${account.id}">Withdraw/Transfer</a></td>
                <td><a href = "/transactions/${account.id}">Transactions</a></td>
                <td><a href="/delete-account/${client.id}/${account.id}">Close</a> </td>
            </tr>
        </c:forEach>
        <tr>
            <td>Sum</td>
            <td>${sum}</td>
        </tr>
    </table>
    <form action="/transactions/${client}/${client.id}">
        <%java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());%>
        <label for="after">After</label>
        <input type="date" name="after" id="after" value="<%=date%>">
        <label for="before">Before</label>
        <input type="date" name="before" id="before" value="<%=date%>">
        <input type="submit" value="Transactions">
    </form>
    <a href="/add-new-account/${client.id}">Add new account</a>
    <form action="/"><input type="submit" value="List of clients"></form>
</body>
</html>
