<%-- 
    Document   : atendimentosListar
    Created on : 12/05/2018, 20:05:12
    Author     : luck
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="com.sun.faces.application.WebPrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@page import="com.mysql.cj.util.StringUtils"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page errorPage="erro.jsp" %>
<%-- Procura se existe um usuário instanciado --%>
<c:choose>
<c:when test="${empty sessionScope.user}">
<jsp:forward page="index.jsp">
    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
</jsp:forward>
</c:when>
    <c:otherwise>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <script type="text/javascript" src="materialize/js/web2.js"></script>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" href="java.ico">
        <title>Portal</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col m12">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, ${user.nome}</h2>
                    <div class="divider"></div>
                    <div class="card white">
                        <div class="card-content">
                            <span class="card-title center-align"><h3>Lista de Atendimentos</h3></span>
                        </div>
                    </div>
                    <table class="responsive-table highlight centered">
                        <thead>
                            <tr class="card blue-grey lighten-5">
                                <th>Data/Hora</th>
                                <th>Produto</th>
                                <th>Cliente</th>
                                <th>Visualizar</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${lista}" var="aux">
                                <tr class='card'>
                                <td><fmt:formatDate value="${aux.dataHoraAtendimento}" type="both" pattern="dd/MM/yyyy HH:mm" /></td>
                                <td>${aux.produto.nomeProduto}</td>
                                <td>${aux.cliente.nomeCliente}</td>
                                <td class="center-align" style="width: 220px;">
                                <a href="AtendimentoServlet?action=show&id=${aux.idAtendimento}" class="btn-floating pulse green opt"><i class="small material-icons white-text">visibility</i></a>
                                </td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div style="height: 10px;"></div>
                    <div class="container">
                        <div class="row">
                            <div class="col offset-s8">
                                <a href="portal.jsp" class="waves-effect waves-teal btn">voltar<i class="material-icons right">arrow_back</i></a>                                
                            </div>
                        </div>
                    </div>
                    <div style="height: 100px;"></div>
                </div>
            </div>
        </div>
                    <%@include file="footer.jsp" %>
    </body>
</html>
    </c:otherwise>    
</c:choose>