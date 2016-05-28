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
        <form name="Search Parameters Form" action="/controler">
            <select name="tipo_de_busca">
                <option>Titulo</option>
                <option>Autor</option>
            <input type="text" name="parametro" value="" size="200" /><br/>
            <input type="submit" value="OK" />
            
        </form>
    </body>
</html>
