<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div style="text-align: center">
    <p>
        ${error}
    </p>
    <p>
    <h2>Add New User</h2>
    <form action="/user/add" method="post">
        <label>
            <input type="text" placeholder="Enter Username" name="username" required>
        </label><br>
        <label>
            <input type="password" placeholder="Enter Password" name="password" required>
        </label><br>
        <label>
            <input type="password" placeholder="Confirm Password" name="confirmPassword" required>
        </label><br>
        <label>
            <input type="text" placeholder="Enter name" name="name" required>
        </label><br>
        <button class="btn btn-success btn-sm" type="submit"><i class="fa fa-plus"></i> &nbsp; Create New User</button>
    </form>
    <form action="/" method="get">
        <button class="btn btn-light btn-sm" type="submit">Back</button>
    </form>
    </p>
</div>
</body>
</html>