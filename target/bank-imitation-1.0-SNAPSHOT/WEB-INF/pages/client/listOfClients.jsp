
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Of Clients</title>
</head>
<body>
<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Age</th>
        <th>Address</th>
    </tr>
    <c:forEach var="client" items="${allClients}">
        <tr>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.age}</td>
            <td>${client.address}</td>
            <td><a href="/${client}/${client.id}/accounts/">Accounts</a></td>
            <td><a href="/edit/${client.id}">Edit information</a></td>
            <td><a href="/delete/${client.id}">Delete client</a></td>
        </tr>
    </c:forEach>
</table>


<a href="/add">Add new client</a>
</body>
</html>
