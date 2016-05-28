<%-- 
    Document   : edit
    Created on : 27/05/2016, 17:19:43
    Author     : Leonardo
--%>

<%@page language = "java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - Adicionar Livro</title>
    </head>
    <body>
        <h1>Novo Livro</h1>
        <form action="Controller_ref" method="POST">
            <%--<div>
                <label for="RefId">Serial</label> 
                <input type="text" name="RefId"/>
            </div>
            <div>
                <label for="titulo_ref">Titulo</label> 
                <input type="text" name="titulo_ref"/>
            </div>
            <div>
                <label for="autoria_ref">Autoria</label> 
                <input type="text" name="autoria_ref"/>
            </div>--%>

            <div>
                <label for="serialno">Serial</label> 
                <input type="text" name="serialno" value="<c:out value="${ref.serialno}" />"
                readonly="readonly" placeholder="Serial" />
            </div>
            <div>
                <label for="titulo">Titulo</label> 
                <input type="text"name="titulo" value="<c:out value="${ref.titulo}" />"
                placeholder="Titulo" />
            </div>
            <div>
                <label for="autoria">Autoria</label> 
                <input type="text"name="autoria" 
                       value="<c:out value="${ref.autoria}" />" placeholder="Autoria" />
            </div>
            <div>
                <input type="submit" value="Submit" />
            </div>
        </form>
    </body>
</html>
