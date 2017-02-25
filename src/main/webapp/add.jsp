<%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 24.02.17
  Time: 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add</h1>
<form action="/students/add/" method="post">
    <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name">
    </div>
    <div>
        <label for="age">age</label>
        <input type="text" name="age" id="age">
    </div>
    <div>
        <input type="submit" value="Save">
    </div>
</form>
</body>
</html>