<%-- 
    Document   : erro
    Created on : 24/03/2018, 01:29:10
    Author     : luck
--%>

<%@page import="com.mysql.jdbc.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensagem = (String) request.getAttribute("msg");
    String link = (String) request.getAttribute("page");

    if (StringUtils.isNullOrEmpty(mensagem)) {
        mensagem = "Erro page";
    }

    if (StringUtils.isNullOrEmpty(link)) {
        link = "index.jsp";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <title>Error</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row bordered">
                <h1 class="red-text"><%=mensagem%></h1>
                <h3><p class="white-text">Para voltar à Home <a href="<%=link%>">Clique aqui</a>.</p></h3>
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
