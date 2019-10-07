<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <h1>Registered users:</h1>
        <form>
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Roles</th>
                    <th>Phone number</th>
                    <th>Name</th>
                    <th>Send to</th>
                    <th>EMail</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.getId()}"/></td>
                        <td><c:out value="${user.getRoles()}"/></td>
                        <td><c:out value="${user.getPhoneNumber()}"/></td>
                        <td><c:out value="${user.getName()}"/></td>
                        <td><c:out value="${user.getAddress()}"/></td>
                        <td><c:out value="${user.getEmail()}"/></td>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
