<%-- 
    Document   : result
    Created on : 26/05/2016, 22:31:49
    Author     : Leonardo
--%>

<%--
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabalho 1 - EEL 418</title>
    </head>
    <body>
        <jsp:useBean id="resp_bean" scope="session" class="br.ufrj.eel418.trab1.NameHandler" />
        <jsp:setProperty name="resp_bean" property="name"/>
        <h1>Olá, <jsp:getProperty name="resp_bean" property="name" /></h1>
    </body>
</html>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - Resultados</title>
    </head>
    <body>
        <table border="0">
            <thead>
                <tr>
                    <th>Serial</th>
                    <th>Titulo</th>
                    <th>Autoria</th>
                    <th colspan=2>A&ccedil;&otilde;es</th>
                </tr>
            </thead>
            <tbody>
                <%--<jsp:useBean id="result_bean" scope="session" class="com.eel418.trab1.model.Referencias"/>
                <jsp:setProperty name="result_bean" property="serialno"/>
                <jsp:setProperty name="result_bean" property="titulo"/>
                <jsp:setProperty name="result_bean" property="autoria"/>--%>
                <c:forEach items="${referencias}" var="ref">
                    <tr>
                        <%--<td><jsp:getProperty name="result_bean" property="serialno" /></td>
                        <td><jsp:getProperty name="result_bean" property="titulo" /></td>
                        <td><jsp:getProperty name="result_bean" property="autoria" /></td>--%>
                        <td><c:out value="${ref.serialno}"/></td>
                        <td><c:out value="${ref.titulo}"/></td>
                        <td><c:out value="${ref.autoria}"/></td>
                        <td><a href="Controller_ref?action=update&serialno=<c:out value="${ref.serialno}"/>">Update</a></td>
                        <td><a href="Controller_ref?action=delete&serialno=<c:out value="${ref.serialno}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p>
            <a href="Controller_ref?action=create">Adicionar novo livro</a>
        </p>
    </body>
</html>