<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <form action="http://localhost:8080/shop_war_exploded/login" method="post">
            <h1>Login</h1>
            <p>To continue fill in:</p>
            <label><b>Phone number</b></label>
            <input type="number" placeholder="" name="Phone number" required>
            <label><b>Password</b></label>
            <input type="password" placeholder="" name="Password" required>
            <button type="submit">Enter</button>
        </form>
        <div>${errorMessage}</div>
    </body>
</html>
