
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
    <c:if test="${empty client.firstName}">
        <title>Добавить нового клиента</title>
    </c:if>
    <c:if test="${!empty client.firstName}">
        <title>Редактирование информации</title>
    </c:if>
</head>
<body>
<c:if test="${empty client.firstName}">
    <c:url value="/add" var="method"/>
    <div class="header">Добавить нового клиента</div>
</c:if>
<c:if test="${!empty client.firstName}">
    <div class="header">Редактирование информации</div>
    <c:url value="/edit" var="method"/>
</c:if>
<form action="${method}" method="POST">
    <c:if test="${!empty client.firstName}">
        <input type="hidden" name="id" value="${client.id}">
    </c:if>
    <p>
        <input type="text" name="firstName" value="${client.firstName}" class="field" placeholder="Имя" maxlength="55" required>
    </p>
    <p>
        <input type="text" name="lastName" value="${client.lastName}" class="field" placeholder="Фамилия" maxlength="55" required>
    </p>
    <p>
        <input type="number" name="age" value="${client.age}" class="field" placeholder="Возраст" required min="18">
    </p>
    <p>
        <input type="text" name="address" value="${client.address}" class="field" placeholder="Адрес" maxlength="255" required>
    </p>
    <p>
        <c:if test="${empty client.firstName}">
            <input type="submit" class="submit" value="Добавить клиента">
        </c:if>
        <c:if test="${!empty client.firstName}">
            <input type="submit" class="submit" value="Изменить">
        </c:if>
    </p>
</form>
<form action="/"><input type="submit" class="cancel" value="Отмена"></form>
</body>
</html>