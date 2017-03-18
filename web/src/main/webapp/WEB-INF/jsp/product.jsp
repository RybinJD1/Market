<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<form method="POST" action='ProductController' name="formAddProduct">
    <input type="hidden" name="id" value="<c:out value="${product.id}" />" /> <br />
    name : <input type="text" name="name" value="<c:out value="${product.name}" />" /> <br />
    description : <input type="text" name="description" value="<c:out value="${product.description}" />" /> <br />
    cost : <input type="text" name="cost" value="<c:out value="${product.cost}" />" /> <br />
    remainder : <input type="text" name="remainder" value="<c:out value="${product.remainder}" />" /> <br />


    <br/><br/>

    <input type="submit" value="Submit" />

    <p><a href="ProductController?action=home">Home</a></p>
</form>
</body>
</html>
