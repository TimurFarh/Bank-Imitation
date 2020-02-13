<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>Список клиентов</title>
</head>
<body>
<table>
    <caption>Список клиентов</caption>
    <tr>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Возраст</th>
        <th>Адрес</th>
        <th colspan="2">Операции</th>
    </tr>
    <c:forEach var="client" items="${allClients}">
        <tr>
            <td><a href="/${client.id}/accounts" title="Accounts">${client.firstName}</a></td>
            <td>${client.lastName}</td>
            <td>${client.age}</td>
            <td>${client.address}</td>
            <td><a href="/edit/${client.id}">
                    <img src="<c:url value="/res/pictures/editIcon.png"/>" title="Редактирование информации" width="25" height="25">
                </a>
            </td>
            <td>
                <a href="/delete/${client.id}">
                    <img src="<c:url value="/res/pictures/deleteIcon.png"/>" title="Удалить клиента" width="25" height="25">
                </a>
            </td>
        </tr>
    </c:forEach>
</table>


<a href="/add" class="add"><span>▶</span>Добавить нового клиента<span>◀</span></a>
</body>
</html>
