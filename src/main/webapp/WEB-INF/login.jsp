<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div style="text-align: center">
    <h2>Login</h2>
    <p>
        ${error}
    </p>
    <p>
    <form action="/login" method="post">
        <input type="text" placeholder="Enter Username" name="username" required><br>
        <input type="password" placeholder="Enter Password" name="password" required><br>
        <button type="submit" class="btn btn-primary btn-sm"><i class="fa fa-sign-in"></i> &nbsp;Login</button>
    </form>
    </p>
</div>
</body>
</html>