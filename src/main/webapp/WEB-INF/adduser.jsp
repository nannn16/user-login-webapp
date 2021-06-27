<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Add User Page</title>
<body>
<p>
    ${error}
</p>
<p>
<form action="/adduser" method="post">
    <label>
        <input type="text" placeholder="Enter Username" name="username" required>
    </label><br>
    <label>
        <input type="password" placeholder="Enter Password" name="password" required>
    </label><br>
    <label>
        <input type="text" placeholder="Enter name" name="name" required>
    </label><br>
    <button type="submit">Submit</button>
</form>
</p>
</body>
</html>