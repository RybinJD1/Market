<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Order</title>
</head>
<body>
<form method="POST" action='OrderController' name="formAddOrder">
    <input type="hidden" name="id" value="<c:out value="${orders.id}" />" /> <br />
    registrationDate : <input type="text" name="registrationDate" value="<c:out value="${orders.registrationDate}" />" /> <br />
    closingDate : <input type="text" name="closingDate" value="<c:out value="${orders.closingDate}" />" /> <br />
    status : <select name="status">
    <option value="ORDER_PROCESSING">Обрабатывается</option>
    <option value="EXECUTED">Выполняется</option>
    <option value="CANCELLED">Отменен</option>
    <option value="SENT">Отправлен</option></select> <br />
    buyerId : <select  name="buyerId">
    <option value="buyer.id"></option></select> <br />
    <br/><br/>

    <input type="submit" value="Submit" />

    <p><a href="OrderController?action=home">Home</a></p>

</form>
</body>
</html>
