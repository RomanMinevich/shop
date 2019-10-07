<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <form action="http://localhost:8080/shop_war_exploded/registration" method="post">
            <h1>Registration</h1>
            <p>Please fill in the form:</p>
            <label><b>Phone number</b></label>
            <input type="number" placeholder="" name="Phone number" required>
            <label><b>Password</b></label>
            <input type="password" placeholder="" name="Password" required>
            <label><b>First name</b></label>
            <input type="text" placeholder="" name="First name">
            <label><b>Last name</b></label>
            <input type="text" placeholder="" name="Last name">
            <label><b>Shipping address</b></label>
            <input type="text" placeholder="" name="Shipping address">
            <label><b>Email</b></label>
            <input type="text" placeholder="" name="Email">
            <button type="submit">Register</button>
        </form>
    </body>
</html>
