<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Orders</title>
    </head>
    <body>
        <h1>Your orders:</h1>
        <form action="http://localhost:8080/shop_war_exploded/servlet/orders" method="post">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Ordered items</th>
                    <th>Cancel an order</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td><c:out value="${order.getId()}"/></td>
                        <td><table border="1">
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                            </tr>
                            <c:forEach var="item" items="${order.getItems()}">
                                <tr>
                                    <td><c:out value="${item.getId()}"/></td>
                                    <td><c:out value="${item.getName()}"/></td>
                                    <td><c:out value="${item.getPrice()}"/></td>
                                </tr>
                            </c:forEach>
                        </table></td>
                        <td><button type="submit" name="Remove" value="${order.getId()}">Remove</button></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
