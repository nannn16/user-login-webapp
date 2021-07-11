<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div style="text-align: center">
    <h2>Confirm</h2>
    <h4>Do you want to remove user ${removeUser}?</h4>
    <form action="/user/remove" method="get">
        <input type="hidden" name="removeUser" value="${removeUser}">
        <button class="btn btn-success btn-sm" type="submit"><i class="fa fa-check"></i> &nbsp; Confirm</button>
    </form>
    <form action="/" method="get">
        <button class="btn btn-light btn-sm" type="submit">Back</button>
    </form>
</div>
</body>
</html>