<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <h1>Users:</h1>
        <form>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Roles</th>
                    <th>Phone number</th>
                    <th>EMail</th>
                    <th>Name</th>
                    <th>Send to</th>
                    <th>Orders</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.getId()}"/></td>
                        <td><c:out value="${user.getRoles()}"/></td>
                        <td><c:out value="${user.getPhoneNumber()}"/></td>
                        <td><c:out value="${user.getEmail()}"/></td>
                        <td><c:out value="${user.getFirstName()} ${user.getLastname()}"/></td>
                        <td><c:out value="${user.shippingAddress()}"/></td>
                    <td><table border="1">
                    <tr>
                    <th>ID</th>
                    <th>Items in order</th>
                    </tr>
                    <c:forEach var="order" items="${user.getOrders()}">
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
                            </c:forEach>
                    </table></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
