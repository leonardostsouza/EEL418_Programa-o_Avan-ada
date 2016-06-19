<%-- 
    Document   : index
    Created on : 26/05/2016, 21:49:22
    Author     : Leonardo Souza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="0; url=controller?clickedButton=busca" />
        <title>Trabalho 1 - EEL 418 - BUSCAR</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="js/utils.js"></script>
    </head>
    <body>
    </body>
    <div id="alert_div" msg="${sessionScope.msg}"/>
    <c:if test="${not empty sessionScope.msg}">
    <script type="text/javascript"> window.onload = function () {
                alertMsg('alert_div');
                resetMsg();
            };</script>
    </c:if>
</html>