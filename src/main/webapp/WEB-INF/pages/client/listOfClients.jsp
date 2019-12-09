<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/style.css"/>" rel="stylesheet" type="text/css">
    <title>List Of Clients</title>
</head>
<body>
<table>
    <caption>List of clients</caption>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Address</th>
        <th colspan="2">Operations</th>
    </tr>
    <c:forEach var="client" items="${allClients}">
        <tr>
            <td><a href="/${client.id}/accounts" title="Accounts">${client.firstName}</a></td>
            <td>${client.lastName}</td>
            <td>${client.age}</td>
            <td>${client.address}</td>
            <td><a href="/edit/${client.id}">
                    <img src="<c:url value="/res/pictures/editIcon.png"/>" title="Edit client" width="25" height="25">
                </a>
            </td>
            <td>
                <a href="/delete/${client.id}">
                    <img src="<c:url value="/res/pictures/deleteIcon.png"/>" title="Delete client" width="25" height="25">
                </a>
            </td>
        </tr>
    </c:forEach>
</table>


<a href="/add" class="add"><span>▶</span>Add new client<span>◀</span></a>
</body>
</html>
