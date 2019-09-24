<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Items</title>
    </head>
    <body>
        <h1>Our product line:</h1>
        <form action="http://localhost:8080/shop_war_exploded/items" method="post">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Add to bucket</th>
                </tr>
                <c:forEach var="item" items="${items}">
                    <tr>
                        <td><c:out value="${item.getId()}"/></td>
                        <td><c:out value="${item.getName()}"/></td>
                        <td><c:out value="${item.getPrice()}"/></td>
                        <td><button type="submit" name="Add" value="${item.getId()}">Add</button></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
