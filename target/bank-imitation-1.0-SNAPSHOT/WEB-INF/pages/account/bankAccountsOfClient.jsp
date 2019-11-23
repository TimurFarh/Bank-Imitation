
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accounts</title>
</head>
<body>
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
                <td><a href="/delete-account/${client.id}/${account.id}">Close</a> </td>
            </tr>
        </c:forEach>
    </table>

    <a href="/add-new-account/${client.id}">Add new account</a>
    <form action="/"><input type="submit" value="List of clients"></form>
</body>
</html>
