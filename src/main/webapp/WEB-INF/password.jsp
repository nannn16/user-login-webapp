<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Change Password Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div style="text-align: center">
    <p>
    <h2>Change Password</h2>
    ${error}
    <form action="/user/password" method="post">
        <input type="hidden" name="username" value="${username}">
        <label>
            <input type="password" placeholder="Enter New Password" name="password" required>
        </label><br>
        <label>
            <input type="password" placeholder="Confirm Password" name="confirmPassword" required>
        </label><br>
        <button class="btn btn-success" type="submit"><i class="fa fa-save"></i> &nbsp; Save</button>
    </form>
    <form action="/" method="get">
        <button class="btn btn-light btn-sm" type="submit">Back</button>
    </form>
    </p>
</div>
</body>
</html>