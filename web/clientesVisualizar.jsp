<%-- 
    Document   : clientesVisualizar
    Created on : 01/04/2018, 09:33:06
    Author     : luck
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.beans.Beans"%>
<%@page import="com.mysql.jdbc.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="erro.jsp" %>
<%    /*Procura se existe um usuário instanciado*/  if (pageContext.findAttribute("user") == null) { %>
<jsp:forward page="index.jsp">
    <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
</jsp:forward>
<% } %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Home</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col offset-m2 m8">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, ${user.nome}</h2>
                    <div class="divider"></div>
                    <div style="height: 50px;"></div>
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title center-align"><h3>Dados do cliente</h3></div>
                            <form>
                                <div class="col m6">
                                Nome: <input type="text" name="nomeCliente" disabled="true" value="${cliente.nomeCliente}"/><br/>                                    
                                </div>
                                <div class="col m6">
                                    CPF: <input type="text" name="cpfCliente" disabled="true" value="${cliente.cpfCliente}"/><br/>                                    
                                </div>
                                <div class="col m8">
                                    E-Mail: <input type="email" name="emailCliente" disabled="true" value="${cliente.emailCliente}"/><br/>
                                </div>
                                <div class="col m4">
                                    Data de nascimento: <input type="date" name="dataCliente" disabled="true" value="${cliente.dataCliente}"/><br/>
                                </div>
                                <div class="col m8">
                                    Endereço: <input type="text" name="ruaCliente" disabled="true" value="${cliente.ruaCliente}"/><br/>
                                </div>
                                <div class="col m4">
                                    CEP: <input type="text" name="cepCliente" disabled="true" value="${cliente.cepCliente}"/><br/>
                                </div>
                                <div class="col m6">
                                    Cidade: <input type="text" name="cidadeCliente" disabled="true" value="${cliente.cidadeCliente}"/><br/>
                                </div>
                                <div class="col m4">
                                    Estado: <input type="text" name="ufCliente" disabled="true" value="${cliente.ufCliente}"/><br/>
                                </div>
                                <div class="col m2">
                                    N°: <input type="number" name="nrCliente" disabled="true" value="${cliente.nrCliente}"/><br/>
                                </div>
                                    <a href="ClientesServlet?action=list" class="waves-effect waves-teal btn">Voltar<i class="material-icons right">arrow_back</i></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 100px;"></div>
        </div>
<%@include file="footer.jsp" %>
    </body>
</html>
