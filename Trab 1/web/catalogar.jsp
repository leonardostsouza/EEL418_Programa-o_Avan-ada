<%-- 
    Document   : catalogar
    Created on : 28/05/2016, 10:56:05
    Author     : Leonardo Souza
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
                    <a href="controller?clickedButton=busca">BUSCA</a>
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
                        <input type="text" name="titulo" placeholder="Novo Titulo" size="100"/>
                    </div>
                    <br/>
                    Autoria
                    <div align="center">
                        <input type="text" name="autoria" placeholder="Autor" size="100"/>
                    </div>
                    <br/>
                    <a href="#" onClick="Javascript:submitForm('form_create', 'create');">CRIAR</a>
                    <a href="#" onClick="Javascript:limparCampos('form_create');">LIMPAR</a>
                </form>
            </div>
        </div>
    </body>
    <div id="alert_div" msg="${sessionScope.msg}"/>
    <script type="text/javascript"> window.onload = function () {
            alertMsg('alert_div');
        };</script>
</html>
