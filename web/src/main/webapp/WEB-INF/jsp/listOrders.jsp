<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" cellspacing="0">
    <caption>Список заказов</caption>
    <thead>
    <th>registrationDate</th>
    <th>closingDate</th>
    <th>status</th>
    <th>buyerId</th>
    <th>update</th>
    <th>delete</th>

    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td><c:out value="${order.registrationDate}" /></td>
            <td><c:out value="${order.closingDate}" /></td>
            <td><c:out value="${order.status}" /></td>
            <td><c:out value="${order.buyerId}" /></td>
            <td><a href="OrderController?action=update&id=<c:out value="${order.id}"/>">Update</a></td>
            <td><a href="OrderController?action=delete&id=<c:out value="${order.id}"/>" onclick="return confirm('Удалить выбранный заказ ?')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="OrderController?action=insert">Add Orders</a></p>
<p><a href="OrderController?action=home">Home</a></p>

</body>
</html>
