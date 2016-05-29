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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabalho 1 - EEL 418 - BUSCAR</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="js/utils.js"></script>
    </head>
    <body>
        <%-- Cabecalho --%>
        <div id="main">
            <div id="pages">
                <div id="align_cat" align="right">
                    <a href="catalogar.jsp">CATALOGAR</a>
                </div>
                <div id="align_busca" align="center">
                    <h1>BUSCA</h1>
                </div>
            </div>

            <%-- Formulario para busca --%>
            <div id="form_div" align="center">
                <form id="form_busca" name="form_busca" action="controller" method="POST">
                    <%--input type="hidden" name="parser" value="com.eel418.trab1.controller.Parser"/--%>
                    <input type="hidden" name="clickedButton"/>
                    <select name="tipo_de_busca">
                        <option name="opcao1" value="titulo" selected>Titulo</option>
                        <option name="opcao2" value="autoria">Autor</option>
                    </select>
                    <input type="text" name="parametro" value="${sessionScope.parametro}" 
                           size="100" placeholder="Digite o valor que deseja buscar"/><br/><br/>
                    <a href="#" onClick="Javascript:submitForm('form_busca', 'read');">BUSCAR</a>
                </form>
            </div>
            <br/><br/>

            <%-- Resultados --%>
            <c:if test="${not empty sessionScope.qtd_resultados}">
                <div id="qtd_result" align="center">
                    <p>Sua busca retornou ${sessionScope.qtd_resultados} resultados</p>
                </div>
                <div id="result" align="center">
                    <%--form id="form_return" align="center" name="form_return"  method="post"--%>
                    <table border="0" cellspacing="3" cellpadding="2">
                        <thead>
                            <tr>
                                <th>Serial</th>
                                <th>Titulo</th>
                                <th>Autoria</th>
                                <th colspan="2">  A&ccedil;&otilde;es  </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.resultados}" var="ref">
                                <tr>
                                    <td>${ref.getSerialno()}</td>
                                    <td>${ref.getTitulo()}</td>
                                    <td>${ref.getAutoria()}</td>
                                    <td><a href="controller?clickedButton=update&serialno=<c:out value='${ref.getSerialno()}'/>">Editar</a></td>
                                    <td><a href="controller?clickedButton=delete&serialno=<c:out value='${ref.getSerialno()}'/>">Apagar</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            
        </div>
    </body>
</html>