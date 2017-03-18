<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellspacing="0">
    <caption>Список товаров</caption>
    <thead>
    <th>name</th>
    <th>description</th>
    <th>cost</th>
    <th>remainder</th>
    <th>quantity of goods</th>
    <th>update</th>
    <th>delete</th>

    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:out value="${product.name}" /></td>
            <td><c:out value="${product.description}" /></td>
            <td><c:out value="${product.cost}" /></td>
            <td><c:out value="${product.remainder}" /></td>
            <td><input type="number" name="number" min="0" max="${product.remainder}" value="0" size="10"></td>
            <td><a href="ProductController?action=update&id=<c:out value="${product.id}"/>">Update</a></td>
            <td><a href="ProductController?action=delete&id=<c:out value="${product.id}"/>" onclick="return confirm('Удалить выбранный товар ?')">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form>
<c:forEach items="${products}" var="product">
    <c:if test="${product.remainder !=0 }">
        <%--<input type="hidden" readonly="readonly" name="id" value="${product.id}" />--%>
        <input type="hidden" readonly="readonly" name="id" value="<c:out value="${product.id}"/> "/>
        <input type="hidden" readonly="readonly" name="name" value="<c:out value="${product.name}"/> "/>
        <input type="hidden" readonly="readonly" name="description" value="<c:out value="${product.description}"/> "/>
        <input type="hidden" readonly="readonly" name="cost" value="<c:out value="${product.cost}"/> "/>
        <input type="hidden" readonly="readonly" name="remainder" value="<c:out value="${product.remainder}"/> "/>
    </c:if>
</c:forEach>
<%--<input type="submit" value="submit">--%>
    <p><input type="submit" value="To order" /></p>
</form>
<p><a href="ProductController?action=insert">Add Product</a></p>

<p><a href="ProductController?action=home">Home</a></p>


</body>
</html>
