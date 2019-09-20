<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: roman_minevich
  Date: 9/19/19
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Bucket</title>
    </head>
    <body>
        <h1>Your bucket:</h1>
        <form action="bucket" method="post">
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
                        <td><button type="submit" name="-" value="${item.getId()}">-</button></td>
                    </tr>
                </c:forEach>
            </table>
            <button type="submit" name="Complete order" value="${items.size()}">Complete order</button>
        </form>
    </body>
</html>