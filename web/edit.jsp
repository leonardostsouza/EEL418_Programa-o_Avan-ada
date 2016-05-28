<%-- 
    Document   : edit
    Created on : 27/05/2016, 17:19:43
    Author     : Leonardo
--%>

<%@page language = "java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - Adicionar Livro</title>
    </head>
    <body>
        <h1>Novo Livro</h1>
        <form action="controler" method="POST">
            Serial: <input type="text" name="titulo" value="" /><br/>
            Titulo: <input type="text" name="titulo" value="" /><br/>
            Autoria: <input type="text" name="autoria" value="" /><br/>
            <input type="submit" value="OK" />
        </form>
    </body>
</html>
