<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>
        <c:if test="${error.toString() eq 'NotEnoughMoneyException'}">Не достаточно средств</c:if>
        <c:if test="${error.toString() eq 'WrongAccountException'}">Wrong account</c:if>
        <c:if test="${error.toString() eq 'AccountAlreadyExistsException'}">Неверное название</c:if>
    </title>
</head>
<body>
    <c:if test="${error.toString() eq 'NotEnoughMoneyException'}">
        <h2>На вашем счету недостаточно средств</h2>
    </c:if>
    
    <c:if test="${error.toString() eq 'WrongAccountException'}">
        <h2>Такого счёта не существует, либо вы ввели тот же самый счёт</h2>
    </c:if>
    
    <c:if test="${error.toString() eq 'AccountAlreadyExistsException'}">
    	<h2>Счёт с таким названием уже существует.</h2>
    </c:if>
    <form action="/${client.id}/accounts" ><input type="submit" class="cancel" value="Назад"></form>
</body>
</html>
