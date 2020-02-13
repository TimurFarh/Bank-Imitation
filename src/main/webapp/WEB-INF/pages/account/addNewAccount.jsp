<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
    <title>Создание нового счёта</title>
</head>
<body>

<form action="/add-new-account/${client.id}" method="post">
    <div class="header">Создание нового счета</div>
    <input type="hidden" name="balance" value="0">
    <p><input type="text" name="name" placeholder="Название счёта" class="field" maxlength="50" required></p>
    <p><input type="submit" class="submit" value="Добавить новый счёт"></p>
</form>

<form action="/${client.id}/accounts" method="GET"><input type="submit" class="cancel" value="Отмена"></form>
</body>
</html>
