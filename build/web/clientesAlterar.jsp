<%-- 
    Document   : clientesAlterar
    Created on : 01/04/2018, 09:34:12
    Author     : luck
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.beans.Beans"%>
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
<jsp:useBean id="cliente" class="com.ufpr.tads.web2.beans.Cliente" scope="request" />
<%
    //Formatando data do bean
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
    String dataCliente = fmt.format(cliente.getDataCliente());
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <link rel="icon" href="java.ico">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <title>Alterar</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col offset-m2 m8">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, <jsp:getProperty name="user" property="nome" /></h2>
                    <div class="divider"></div>
                    <div style="height: 50px;"></div>
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title center-align"><h3>Dados do cliente</h3></div>
                            <form action="AlterarClienteServlet?id=<jsp:getProperty name="cliente" property="idCliente"/>" method="post">
                                <div class="col m6">
                                    Nome:* <input type="text" name="nomeCliente" required="true" value="<jsp:getProperty name="cliente" property="nomeCliente"/>"/><br/>                                    
                                </div>
                                <div class="col m6">
                                    CPF:* <input type="text" name="cpfCliente" required="true" value="<jsp:getProperty name="cliente" property="cpfCliente"/>"/><br/>                                    
                                </div>
                                <div class="col m8">
                                    E-Mail:* <input type="email" name="emailCliente" required="true" value="<jsp:getProperty name="cliente" property="emailCliente"/>"/><br/>
                                </div>
                                <div class="col m4">
                                    Data de nascimento: <input type="date" name="dataCliente" value="<%=dataCliente%>"/><br/>
                                </div>
                                <div class="col m8">
                                    Endereço: <input type="text" name="ruaCliente" value="<jsp:getProperty name="cliente" property="ruaCliente"/>"/><br/>
                                </div>
                                <div class="col m4">
                                    CEP: <input type="text" name="cepCliente" value="<jsp:getProperty name="cliente" property="cepCliente"/>"/><br/>
                                </div>
                                <div class="col m6">
                                    Cidade: <input type="text" name="cidadeCliente" value="<jsp:getProperty name="cliente" property="cidadeCliente"/>"/><br/>
                                </div>
                                <div class="col m4">
                                    Estado: <input type="text" name="ufCliente" value="<jsp:getProperty name="cliente" property="ufCliente"/>"/><br/>
                                </div>
                                <div class="col m2">
                                    N°: <input type="number" name="nrCliente" value="<jsp:getProperty name="cliente" property="nrCliente"/>"/><br/>
                                </div>
                                <div class="container">
                                    <div class="row">
                                        <div class="col m5">
                                            <button type="submit" class="waves-effect waves-teal btn">salvar<i class="material-icons right">save</i></button>
                                        </div>
                                        <div class="col offset-m2 m5">
                                            <a href="ClientesServlet" class="waves-effect waves-teal btn">Voltar<i class="material-icons right">arrow_back</i></a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div style="height: 100px;"></div>
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
