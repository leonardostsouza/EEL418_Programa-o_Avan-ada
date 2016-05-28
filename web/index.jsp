<%-- 
    Document   : index
    Created on : 26/05/2016, 21:49:22
    Author     : Leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - BUSCAR</title>
    </head>
    <body>
        <jsp:useBean id="dao" class="com.eel418.trab1.dao.ReferenciasDAO" />
        <h1>BUSCA</h1>
        <form name="form" action="controller" method="POST">
            <select name="tipo_de_busca">
                <option>Titulo</option>
                <option>Autor</option>
                <input type="text" name="parametro" value="" size="100" /><br/>
                <input type="submit" value="OK" />
        </form>
        <a href="catalogar.jsp">CATALOGAR</a>
        <br/><br/>
        <table>
            <thead>
                <tr>
                    <th>Serial</th>
                    <th>Titulo</th>
                    <th>Autoria</th>
                    <th colspan="2">A&ccedil;&otilde;es</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${referencias}" var="ref">
                    <tr>
                        <td><c:out value="${ref.serial}" /></td>
                        <td><c:out value="${ref.titulo}" /></td>
                        <td><c:out value="${ref.autoria}" /></td>
                        <td><a
                                href="controller?action=update&serialno=<c:out value='${ref.serial}'/>">Update</a></td>
                        <td><a
                                href="controller?action=delete&serialno=<c:out value='${ref.serial}'/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
