<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Bucket</title>
    </head>
    <body>
        <h1>Your bucket:</h1>
        <form action="http://localhost:8080/shop_war_exploded/servlet/bucket" method="post">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Remove from bucket</th>
                </tr>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td><c:out value="${item.getId()}"/></td>
                        <td><c:out value="${item.getName()}"/></td>
                        <td><c:out value="${item.getPrice()}"/></td>
                        <td><button type="submit" name="itemId" value="${item.getId()}">Remove</button></td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" name="itemsSize" value="${items.size()}">Complete order</button>
        </form>
        <div>${errorMessage}</div>
    </body>
</html>
