<%-- 
    Document   : index
    Created on : 29/03/2018, 15:08:52
    Author     : luck
--%>

<%@page import="com.mysql.jdbc.StringUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String mensagem = (String) request.getAttribute("msg");
    if (StringUtils.isNullOrEmpty(mensagem)) {
        mensagem = "";
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
        <title>Home</title>
    </head>
    <body class="bgimg">
        <div style="height: 50px;"></div>
        <div class="container">
            <div class="row">
                <div class="col offset-m3 m6">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title center-align"><h3>Formulário de Login</h3></div>
                            <form action="LoginServlet" method="post">
                                Login: <input type="text" name="login" value=""/><br/>
                                Senha: <input type="password" name="senha" value=""/><br/>
                                <input type="submit" value="entrar" class="btn blue-grey">
                            </form>
                            <h5></br><div class="red-text"><%=mensagem%></div></h5>
                        </div>
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
