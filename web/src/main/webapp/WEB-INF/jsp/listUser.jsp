<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" cellspacing="0">
    <caption>Список пользователей</caption>
    <thead>
    <th>role</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>password</th>
    <th>phone</th>
    <th>address</th>
    <th>update</th>
    <th>delete</th>

    </thead>
    <tbody>
    <c:forEach items="${buyers}" var="buyer">
        <tr>
            <td><c:out value="${buyer.role}"/></td>
            <td><c:out value="${buyer.name}" /></td>
            <td><c:out value="${buyer.surname}" /></td>
            <td><c:out value="${buyer.email}" /></td>
            <td><c:out value="${buyer.password}" /></td>
            <td><c:out value="${buyer.phone}" /></td>
            <td><c:out value="${buyer.address}" /></td>
            <td><a href="UserController?action=update&id=<c:out value="${buyer.id}"/>">Update</a></td>
            <td><a href="UserController?action=delete&id=<c:out value="${buyer.id}"/>" onclick="return confirm('Удалить выбранного пользователя ?')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="UserController?action=insert">Add User</a></p>
<p><a href="UserController?action=home">Home</a></p>

</body>
</html>

