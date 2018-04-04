<%-- 
    Document   : portal
    Created on : 24/03/2018, 01:28:59
    Author     : luck
--%>

<%@page import="com.ufpr.tads.web2.beans.LoginBean"%>
<%@page import="com.mysql.jdbc.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    //Procura se existe um usuário instanciado
    if (pageContext.findAttribute("user") == null) {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
        rd.forward(request, response);

    } else { //se existir ele referencia a instancia e carrega na página
%>
<jsp:useBean id="user" class="com.ufpr.tads.web2.beans.LoginBean" scope="session" />
<%}%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <title>Portal</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col offset-m3 m6">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, <jsp:getProperty name="user" property="nome" /></h2>
                    <div class="divider"></div>
                    <div style="height: 50px;"></div>

                    <div class="collection">
                        <a href="ClientesServlet" class="collection-item">Cadastro de clientes</a>
                        <a href="LogoutServlet" class="collection-item">Sair</a>
                    </div>
                </div>
            </div>
        </div>
        <footer class="page-footer blue-grey footer waves-effect">
            <div class="container">
                <div class="row">
                    <jsp:useBean id="configuracao" class="com.ufpr.tads.web2.beans.ConfigBean" scope="application" />
                    <h5>
                        Em caso de problemas contactar o administrador: <jsp:getProperty name="configuracao" property="email" />
                    </h5>
                </div>
            </div>
        </footer>
    </body>
</html>
