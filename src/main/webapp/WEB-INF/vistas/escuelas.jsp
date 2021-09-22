<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="buscar-escuela" method="GET">
    <input name="nombre" value="${nombre}">
    <button type="submit">Buscar</button>
</form>

<ul>
    <c:forEach items="${listaEscuelas}" var="escuela">
        <li>Id: ${escuela.id}, Nombre: ${escuela.nombre}</li>
    </c:forEach>
</ul>
<p>${sinResultado}</p>

</body>
</html>
