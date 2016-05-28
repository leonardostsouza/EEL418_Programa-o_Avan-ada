<%-- 
    Document   : catalogar
    Created on : 28/05/2016, 10:56:05
    Author     : Leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - CATALOGAR</title>
    </head>
    <body>
        <h1>CATALOGAR</h1>
        <form name="cform" action="controller" method="POST">
            Serial: <input type="text" name="serial" value="<c:out value='${catalogar.serial}'/>" /><br/>
            Titulo: <input type="text" name="titulo" value="" size="100" /><br/>
            Autoria: <input type="text" name="autoria" value="" size="100"/><br/>
            <input type="submit" value="CRIAR ENTRADA" />
        </form>
        <a href="index.jsp">BUSCAR</a>
    </body>
</html>
