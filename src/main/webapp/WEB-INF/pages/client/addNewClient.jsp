<%--
  Created by IntelliJ IDEA.
  User: timur
  Date: 13.11.2019
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty client.firstName}">
        <title>Add new client</title>
    </c:if>
    <c:if test="${!empty client.firstName}">
        <title>Edit client</title>
    </c:if>
</head>
<body>
<c:if test="${empty client.firstName}">
    <c:url value="/add" var="var"/>
</c:if>
<c:if test="${!empty client.firstName}">
    <c:url value="/edit" var="var"/>
</c:if>
<form action="${var}" method="POST">
    <c:if test="${!empty client.firstName}">
        <input type="hidden" name="id" value="${client.id}">
    </c:if>
    <label for="firstName">First name</label>
    <input type="text" name="firstName" id="firstName" value="${client.firstName}">
    <label for="lastName">Last Name</label>
    <input type="text" name="lastName" id="lastName" value="${client.lastName}">
    <label for="age">Age</label>
    <input type="text" name="age" id="age" value="${client.age}">
    <label for="address">Address</label>
    <input type="text" name="address" id="address" value="${client.address}">
    <c:if test="${empty client.firstName}">
        <input type="submit" value="Add new client">
    </c:if>
    <c:if test="${!empty client.firstName}">
        <input type="submit" value="Edit client">
    </c:if>
</form>
</body>
</html>
