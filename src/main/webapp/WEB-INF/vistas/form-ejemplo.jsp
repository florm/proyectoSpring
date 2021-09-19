<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="sumar" method="get" >
    <input type="text" name="op1" value="${op1}">
    <input type="text" name="op2" value="${op2}">
    <button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Sumar</button>
    <p>El resultado es: ${resultado}</p>
</form>



</body>
</html>
