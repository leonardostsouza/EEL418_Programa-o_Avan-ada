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
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="js/utils.js"></script>
    </head>
    <body>
        <div id="main">
            <div id="pages">
                <div id="align_busca" align="right">
                    <a href="index.jsp">BUSCA</a>
                </div>
                <div id="align_cat" align="center">
                    <h1>CATALOGAR</h1>
                </div>
            </div>
            <div id="form_div" align="center">
                    <form id="form_create" action="controller" method="POST">
                        <input type="hidden" name="clickedButton"/>
                        Titulo
                        <div align="center">
                            <input type="text" name="new_titulo" placeholder="Novo Titulo" size="100"/>
                        </div>
                        <br/>
                        Autoria
                        <div align="center">
                            <input type="text" name="new_autoria" placeholder="Autor" size="100"/>
                        </div>
                        <br/>
                        <a href="#" onClick="Javascript:submitForm('form_create', 'create');">CRIAR</a>
                        <a href="index.jsp">CANCELAR</a>
                    </form>
            </div>

        </div>
    </body>
</html>
