<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>Список счетов</title>
</head>
<body>

<table>
    <caption>${client.firstName} ${client.lastName}</caption>
    <tr>
        <td>Номер счёта</td>
        <td>Название</td>
        <td>Баланс</td>
        <td colspan="4">Операции</td>
    </tr>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.balance}</td>
            <td>
                <a href="/deposit-account/${client.id}/${account.id}">
                    <img src="<c:url value="/res/pictures/deposit.png"/>" width="35" height="35" title="Пополнение">
                </a>
            </td>

            <td>
                <a href="/withdraw-account/${clientId}/${account.id}">
                    <img src="<c:url value="/res/pictures/withdraw.png"/>" width="35" height="35" title="Списание">
                </a>
            </td>


            <td>
                <a href="/account-transactions/${account.id}/${client.id}">
                    <img src="<c:url value="/res/pictures/transactions.png"/>" width="35" height="35" title="Список операций">         <!-- Транзакции по счёту-->
                </a>
            </td>


            <td>
                <a href="/close-account/${client.id}/${account.id}">
                    <img src="<c:url value="/res/pictures/close.png"/>" width="35" height="35" title="Закрытие счета"> 
                </a>
            </td>  
        </tr>
    </c:forEach>
</table>

<a href="/add-new-account/${client.id}" class="add"><span>▶</span>Добавить новый счёт<span>◀</span></a>

<form action="/client-transactions/${client.id}" class="date"> 													
    <label for="after" class="date">От</label>
    <input type="date" name="after" id="after" required>
    <label for="before" class="date">До</label>									<!-- Транзакции клиента-->
    <input type="date" name="before" id="before" required>
    <input type="submit" class="date" value="Список операций">
</form>

<form action="/"><input type="submit" class="cancel" value="К списку клиентов"></form>
</body>
</html>
