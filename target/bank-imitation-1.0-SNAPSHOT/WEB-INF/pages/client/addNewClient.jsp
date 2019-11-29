
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
    <c:if test="${empty client.firstName}">
        <title>Add new client</title>
    </c:if>
    <c:if test="${!empty client.firstName}">
        <title>Edit client</title>
    </c:if>
</head>
<body>
<c:if test="${empty client.firstName}">
    <c:url value="/add" var="method"/>
    <div class="header">Add new client</div>
</c:if>
<c:if test="${!empty client.firstName}">
    <div class="header">Edit information</div>
    <c:url value="/edit" var="method"/>
</c:if>
<form action="${method}" method="POST">
    <c:if test="${!empty client.firstName}">
        <input type="hidden" name="id" value="${client.id}">
    </c:if>
    <p>
        <input type="text" name="firstName" value="${client.firstName}" class="field" placeholder="First name" maxlength="55" required>
    </p>
    <p>
        <input type="text" name="lastName" value="${client.lastName}" class="field" placeholder="Last name" maxlength="55" required>
    </p>
    <p>
        <input type="text" name="age" value="${client.age}" class="field" placeholder="Age" required>
    </p>
    <p>
        <input type="text" name="address" value="${client.address}" class="field" placeholder="Address" maxlength="255" required>
    </p>
    <p>
        <c:if test="${empty client.firstName}">
            <input type="submit" class="submit" value="Add new client">
        </c:if>
        <c:if test="${!empty client.firstName}">
            <input type="submit" class="submit" value="Edit client">
        </c:if>
    </p>
</form>
<form action="/"><input type="submit" class="cancel" value="Cancel"></form>
</body>
</html>