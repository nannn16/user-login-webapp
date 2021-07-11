<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Home Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<div class="container mt-4">
    <body>
    <h2 class="my-4" style="display: inline;">
        Hello ${username}
    </h2>
    <form style="display: inline;" action="/user/password" method="get">
        <input type="hidden" name="username" value="${username}">
        <button class="btn btn-primary btn-sm" type="submit"><i class="fa fa-key"></i> &nbsp; Change Password</button>
    </form>
    <form style="display: inline;" action="/logout" method="get">
        <button class="btn btn-light btn-sm" type="submit"><i class="fa fa-sign-out"></i> &nbsp; Logout</button>
    </form>

    <p>
        ${error}
    </p>
    <form action="/user/add" method="get">
        <button class="btn btn-success btn-sm" type="submit"><i class="fa fa-user-plus"></i> &nbsp; Add user</button>
    </form>

    <table class="table table-stripped table-bordered">
        <thead>
        <tr>
            <th class="py-3">Username</th>
            <th class="py-3">Name</th>
            <th class="py-3">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="usr" varStatus="idx">
            <tr>
                <td class="py-3"><c:out value="${usr.username}"/></td>
                <td class="py-3"><c:out value="${usr.name}"/></td>
                <td class="py-3">
                    <div>
                        <form style="display: inline;" action="/user/edit" method="get">
                            <input type="hidden" name="username" value="${usr.username}">
                            <button class="btn btn-warning btn-sm" type="submit"><i class="fa fa-pencil"></i></button>
                        </form>
                        <c:if test="${username!=usr.username}">
                            <form style="display: inline;" action="/user/confirmremove" method="get">
                                <input type="hidden" name="removeUser" value="${usr.username}">
                                <button class="btn btn-danger btn-sm" type="submit"><i class="fa fa-trash"></i></button>
                            </form>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </body>
</div>
</html>