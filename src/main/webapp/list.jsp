<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List</h1>
<a href="/students/add/">Add student</a>
<table border="1" cellpadding="5" cellspacing="0">
    <c:forEach items="${studentList}" var="student">
        <tr>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.name}"></c:out></td>
            <td><c:out value="${student.age}"></c:out></td>
            <td><a href="/students/del/?id=<c:out value="${student.id}"></c:out>">delete</a></td>
            <td><a href="/students/edit/?id=<c:out value="${student.id}"></c:out>">edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
