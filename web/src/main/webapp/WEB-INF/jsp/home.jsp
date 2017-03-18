
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${param.language}" />
<fmt:setBundle basename="translations" />

<html>
<head>
    <title>Home</title>
</head>
<body>
    <%--<form>--%>
        <%--<input type="submit" value="en_US" name="language">--%>
        <%--<input type="submit" value="ru_RU" name="language">--%>
    <%--</form>--%>
    <%--<fmt:message key="hello"/> <h2>${userName}</h2>--%>
    <%--<br/>--%>
<h2>Добро пожаловать, ${userName}</h2>

<a href="/market/UserController?action=listUser">Users</a>
<br/>
<a href="/market/ProductController?action=listProducts">Products</a>
<br/>
<a href="/market/OrderController?action=listOrders">Orders</a>

<br/><br/>
<a href="LogoutServlet">Logout</a>

</body>
</html>
