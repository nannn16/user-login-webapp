<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<title>Edit User Page</title>
<body>
<p>
    ${error}
</p>
<p>
<h2>Edit User</h2>
<form action="/edituser" method="post">
    <label>
        <input type="text" name="username" value="${username}">
    </label><br>
    <label>
        <input type="text" name="name" value="${name}">
    </label><br>
    <button type="submit">Submit</button>
</form>
<form action="/" method="get">
    <button type="submit">Back</button>
</form>
</p>
</body>
</html>