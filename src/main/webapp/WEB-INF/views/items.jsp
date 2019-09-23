<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman_minevich
  Date: 9/17/19
  Time: 7:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Items</title>
    </head>
    <body>
        <h1>Our product line:</h1>
        <form action="items" method="post">
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
