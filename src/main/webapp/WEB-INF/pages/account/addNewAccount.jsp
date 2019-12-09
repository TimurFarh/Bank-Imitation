<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/res/styles/formStyle.css"/>" rel="stylesheet" type="text/css">
    <title>Create new account</title>
</head>
<body>
<form action="/add-new-account/${client.id}" method="post">
    <div class="header">Create new account</div>
    <input type="hidden" name="balance" value="0">
    <p><input type="text" name="name" placeholder="Account name" class="field" maxlength="50" required></p>
    <p><input type="submit" class="submit" value="Add new account"></p>
</form>
<form action="/${client.id}/accounts" method="GET"><input type="submit" class="cancel" value="Cancel"></form>
</body>
</html>
