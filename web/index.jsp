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
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <script type="text/javascript" src="js/utils.js"></script>
    </head>
    <body>
        <%-- Formulario para busca --%>
        <div id="main">
            <div id="pages">
                <div id="align_cat" align="right">
                    <a href="catalogar.jsp">CATALOGAR</a>
                </div>
                <div id="align_busca" align="center">
                    <h1>BUSCA</h1>
                </div>
            </div>
            <div id="form_div" align="center">
                <form id="form" name="form" action="controller" method="POST">
                    <input type="hidden" name="parser" value="com.eel418.trab1.controller.Parser"/>
                    <input type="hidden" name="clickedButton"/>
                    <select name="tipo_de_busca">
                        <option name="opcao1" value="titulo" selected>Titulo</option>
                        <option name="opcao2" value="autoria">Autor</option>
                    </select>
                    <input type="text" name="parametro" value="${sessionScope.parametro}" 
                           size="100" placeholder="Digite o valor que deseja buscar"/><br/>
                    <%--<input type="submit" value="BUSCAR" onclick="submitForm('form', 'read')"/>--%>
                    <a href="#" onClick="Javascript:submitForm('form','READ');">
                    BUSCAR
                    </a>
                </form>
            </div>
            <br/><br/>
            
            <%-- Resultados --%>
            <%--<c:if test="${not empty sessionScope.qtd_resultados}">--%>
                <div id="qtd_result" align="left">
                    <p>Sua busca retornou ${sessionScope.qtd_resultados} resultados</p>
                </div>
                <div id="result" align="center">
                    <%--<table>
                        <thead>
                            <tr>
                                <th>Serial</th>
                                <th>Titulo</th>
                                <th>Autoria</th>
                                <th colspan="2">A&ccedil;&otilde;es</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.resultados}" var="ref">
                                <tr>
                                    <td><c:out value="${ref.serialno}" /></td>
                                    <td><c:out value="${ref.titulo}" /></td>
                                    <td><c:out value="${ref.autoria}" /></td>
                                    <td><a
                                            href="controller?action=update&serialno=<c:out value='${ref.serial}'/>">Update</a></td>
                                    <td><a
                                            href="controller?action=delete&serialno=<c:out value='${ref.serial}'/>">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>--%>
                    <c:forEach var="ref" items="${sessionScope.resultados}">
                            <fieldset>
                                <legend align="center">
                                    <span id="tag">Titulo:${ref.getTitulo()}</span> 
                                </legend>
                                   <span id="tag">Autoria:${ref.getAutoria()}</span>  <br>
                                <input type="hidden" name="parser" value="Trabalho1.TratadorResultado"/>
                                <input type="hidden" name="clickedButton"/>
                                <input type="button" value="Update" />
                            </fieldset>
                    </c:forEach>  
                </div>
            <%--</c:if>--%>

        </div>
    </body>
</html>
