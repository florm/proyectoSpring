<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <h2 class="mb-3">Lista De Escuelas</h2>
    <div class="col-6">
        <table class="table user-list">
            <thead>
            <tr>
                <th class="d-none"></th>
                <th class="text-center">Nombre</th>
                <th class="text-center">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listaEscuelas}" var="escuela">
                <tr>
                    <td class="d-none">${escuela.id}</td>
                    <td class="text-center">${escuela.nombre}</td>
                    <td class="text-center">
                        <a href="<c:url value="/escuela/${escuela.id}/modificar"/>">
                            <span>Modificar</span>
                        </a>
                        <a href="<c:url value="/escuela/${escuela.id}/eliminar"/>">
                            <span>Eliminar</span>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="crear-escuela">Crear Escuela</a>
    </div>

</div>


</body>
</html>
