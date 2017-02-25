<%@ page import="models.pojo.Student" %><%--
  Created by IntelliJ IDEA.
  User: innopolis
  Date: 25.02.17
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Edit</h1>
<% Student student = (Student) request.getAttribute("student");%>
<form action="/students/edit/?id=<%= student.getId() %>" method="post">
    <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name" value="<%= student.getName()%>">
    </div>
    <div>
        <label for="age"></label>
        <input type="text" name="age" id="age" value="<%= student.getAge()%>">
    </div>
    <div>
        <input type="submit" value="Save">
    </div>
</form>


</body>
</html>
