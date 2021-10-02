<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Globons
  Date: 2/10/2021
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form:form action="guardar-escuela" method="post" modelAttribute="datosEscuela">
    <form:input path="nombre"></form:input>
    <form:button>Guardar</form:button>
</form:form>

<a href="listar-escuelas">Ver lista de Escuelas</a>
</body>
</html>
