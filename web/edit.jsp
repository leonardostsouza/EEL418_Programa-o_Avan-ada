<%-- 
    Document   : edit
    Created on : 27/05/2016, 17:19:43
    Author     : Leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - EDITAR</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="js/utils.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="header" align="center"><h1>Editar</h1></div>
            <div id="form_div" align="center">
                <c:forEach items="${sessionScope.referencia}" var="ref">
                    <form id="form_update" action="controler" method="POST">
                        Titulo<div align="center"><input type="text" name="titulo" value="${ref.getTitulo()}" size="100"/></div>
                        <br/>
                        Autoria<div align="center"><input type="text" name="autoria" value="${ref.getAutoria()}" size="100"/></div>
                        <br/>
                        <input type="submit" value="OK" />
                    </form>
                </c:forEach>
            </div>

        </div>
    </body>
</html>
