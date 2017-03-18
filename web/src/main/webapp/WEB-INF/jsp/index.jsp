
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>index</title>
    </head>
    <body>

    <h2>Вход в систему</h2>

    <form action='LoginController' method="POST" name="loginForm">
        <p><strong>Email: </strong>
            <input type="text" name="username" placeholder="email">
        <p><p><strong>Password: </strong>
        <input type="password" name="password" placeholder="password">
        <p></p>
        <input type="submit" value="Submit">
    </form>

    ${messages}

    </body>
</html>
