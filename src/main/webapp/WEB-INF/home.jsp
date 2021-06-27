<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Home Page</title>
<body>
<h2>
    Hello ${username}
</h2>
<form action="/adduser" method="get">
    <button type="submit">add user</button>
</form>
<form action="/logout" method="get">
    <button type="submit">Logout</button>
</form>
</body>
</html>