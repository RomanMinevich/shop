<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman_minevich
  Date: 9/20/19
  Time: 3:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Orders</title>
    </head>
    <body>
        <h1>Your orders:</h1>
        <form action="orders" method="post">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Items in order</th>
                    <th>Remove from list</th>
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
                        <td><button type="submit" name="-" value="${order.getId()}">-</button></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
