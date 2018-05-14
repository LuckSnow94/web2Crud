<%--
    Document   : portal
    Created on : 24/03/2018, 01:28:59
    Author     : luck
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@page import="com.mysql.cj.util.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="erro.jsp" %>
<%-- Procura se existe um usuário instanciado --%>
<c:choose>
<c:when test="${empty sessionScope.user}">
<jsp:forward page="index.jsp">
    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
</jsp:forward>
</c:when>
    <c:otherwise>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="icon" href="java.ico">
        <title>Portal</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col offset-m3 m6">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, ${user.nome}</h2>
                    <div class="divider"></div>
                    <div style="height: 50px;"></div>
                    <div class="collection">
                        <a href="ClientesServlet?action=list" class="collection-item">Cadastro de clientes</a>
                        <a href="AtendimentoServlet?action=make" class="collection-item">Efetuar atendimento</a>
                        <a href="AtendimentoServlet?action=list" class="collection-item">Mostrar atendimentos</a>
                        <a href="LogoutServlet" class="collection-item">Sair</a>
                    </div>
                </div>
            </div>
        </div>
<%@include file="footer.jsp" %>
    </body>
</html>        
    </c:otherwise>    
</c:choose>