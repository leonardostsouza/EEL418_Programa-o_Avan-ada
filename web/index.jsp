<%-- 
    Document   : index
    Created on : 26/05/2016, 21:49:22
    Author     : Leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418</title>
    </head>
    <body>
        <h1>BUSCA</h1>
        <%--<form name="name input" action="result.jsp">
            titulo:<input type="text" name="titulo"/>
            autoria:<input type="text" name="autoria"/>
            <input type="submit" value="BUSCAR" name="botao_envio" />
        </form>--%>
        <jsp:forward page="/Controller?action=list"></jsp:forward>
    </body>
</html>
