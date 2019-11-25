
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new bank account</title>
</head>
<body>
<form action = "/add-new-account/${client.id}" method="post">
    <input type="hidden" name="balance" value="0">
    <label for="name">Account name</label>
    <input type="text" name="name" id="name">
    <input type="submit" value="Add new account">
</form>
</body>
</html>
