<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form method="POST" action='UserController' name="formAddUser">
    <input type="hidden" name="id" value="<c:out value="${buyer.id}" />" /> <br />
    First Name : <input type="text" name="name" value="<c:out value="${buyer.name}" />" /> <br />
    Last Name : <input type="text" name="surname" value="<c:out value="${buyer.surname}" />" /> <br />
    Email : <input type="text" name="email" value="<c:out value="${buyer.email}" />" /> <br />
    Password : <input type="text" name="password" value="<c:out value="${buyer.password}" />" /> <br />
    Phone : <input type="text" name="phone" value="<c:out value="${buyer.phone}" />" /> <br />
    Address : <input type="text" name="address" value="<c:out value="${buyer.address}" />" /> <br />

    <br/><br/>

    <input type="submit" value="Submit" />

    <p><a href="UserController?action=home">Home</a></p>
</form>
</body>
</html>
