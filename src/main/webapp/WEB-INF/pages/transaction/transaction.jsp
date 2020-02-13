<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
    <title>
        <c:if test="${operation.ordinal() == 0}">Пополнение</c:if>
        <c:if test="${operation.ordinal() == 1}">Списание</c:if>
        <c:if test="${operation.ordinal() == 2}">Закрытие счёта</c:if>
    </title>
</head>
<body>
<form action="/transaction/${clientId}/${account.id}" method="POST">
    <input type="hidden" name="operation" value="${operation}">
    
    <c:if test="${operation.ordinal() == 0}">
        <div class="header">Пополнение счёта №${account.id}</div>
        <input type="hidden" name="to" value="${account.id}">
        <p><input type="text" name="from" placeholder="Откуда" class="field" maxlength="50" required title="Откуда будут совершен перевод"></p>
        <p><input type="number" name="amount" placeholder="Сумма" class="field" required min="1"></p>
        <p><input type="submit" class="submit" value="Пополнить"></p>
    </c:if>
    
    <c:if test="${operation.ordinal() == 1}">
        <input type="hidden" name="from" value="${account.id}">
        <div class="header">Списание со счета №${account.id}</div>
        <p><input type="text" name="to" placeholder="Куда переводить" class="field" maxlength="50" required></p>
        <p><input type="number" name="amount" id="Сумма" placeholder="Сумма" class="field" required min="1"></p>
        <p><input type="submit" class="submit" value="Списать"></p>
    </c:if>
    
    <c:if test="${operation.ordinal() == 2}">
        <input type="hidden" name="from" value="${account.id}">
        <input type="hidden" name="amount" value="${account.balance}">
        <div class="header">
            Закрытие счёта №${account.id}<br>
            Куда перевести оставшиеся средства:
        </div>
        <p><input type="text" name="to" id="to" class="field" maxlength="50" required></p>
        <p><input type="submit" class="submit" value="Закрыть счёт"></p>
    </c:if>
</form>
<form action="/${client.id}/accounts" ><input type="submit" class="cancel" value="Назад"></form>
</body>
</html>
