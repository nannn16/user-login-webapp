<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Confirm Page</title>
<body>
<h2>Confirm</h2>
<h4>Do you want to remove user ${removeuser}?</h4>
<form action="/removeuser" method="get">
    <input type="hidden" name="removeuser" value="${removeuser}">
    <button type="submit">Confirm</button>
</form>
<form action="/" method="get">
    <button type="submit">Back</button>
</form>
</body>
</html>