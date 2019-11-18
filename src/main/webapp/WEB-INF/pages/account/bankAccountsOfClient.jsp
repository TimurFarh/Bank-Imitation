<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 17.11.2019
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
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
            <td>Account type</td>
            <td>Balance</td>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.numberOfAccount}</td>
                <td>${account.typeOfAccount}</td>
                <td>${account.balance}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
