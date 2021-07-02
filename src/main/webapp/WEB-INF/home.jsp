<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<title>Home Page</title>
<body>
<h2>
    Hello ${username}
    <form action="/logout" method="get">
        <button type="submit">Logout</button>
    </form>
</h2>
<form action="/adduser" method="get">
    <button type="submit">add user</button>
</form>
<table>
    <tr>
        <th>Username</th>
        <th>Name</th>
    </tr>

    <c:forEach items="${list}" var="usr" varStatus="idx">
        <tr>
            <td><c:out value="${usr.username}"/></td>
            <td><c:out value="${usr.name}"/></td>
            <td>
                <c:if test="${username!=usr.username}">
                    <form action="/edituser" method="get">
                        <input type="hidden" name="id" value="${usr.id}">
                        <input type="hidden" name="username" value="${usr.username}">
                        <input type="hidden" name="name" value="${usr.name}">
                        <button type="submit">edit user</button>
                    </form>
                </c:if>
            </td>
            <td>
                <c:if test="${username!=usr.username}">
                    <form action="/confirmremove" method="get">
                        <input type="hidden" name="removeuser" value="${usr.username}">
                        <button type="submit">remove user</button>
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>