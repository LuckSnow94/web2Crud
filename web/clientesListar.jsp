<%-- 
    Document   : clientesListar
    Created on : 29/03/2018, 20:05:12
    Author     : luck
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="com.sun.faces.application.WebPrintWriter"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente"%>
<%@page import="java.util.List"%>
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
<jsp:useBean id="lista" class="List<Cliente>" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">@import url("materialize/css/materialize.css");</style>
        <style type="text/css">@import url("materialize/css/materialize.min.css");</style>
        <style type="text/css">@import url("materialize/css/web2.css");</style>
        <script type="text/javascript" src="materialize/js/web2.js"></script>
        <link rel="icon" href="java.ico">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script type="text/javascript">
            var elem = document.querySelector('.tooltipped');
            var instance = M.Tooltip.init(elem, options);
        </script>
        <title>Portal</title>
    </head>
    <body class="bgimg">
        <div class="container">
            <div class="row">
                <div class="col offset-m2 m8">
                    <h1 class="white-text">@Portal</h1>
                    <h2 class="white-text">Olá, <jsp:getProperty name="user" property="nome" /></h2>
                    <div class="divider"></div>
                    <div class="card white">
                        <div class="card-content">
                            <span class="card-title center-align"><h3>Cadastro de Clientes</h3></span>
                        </div>
                    </div>
                    <table class="responsive-table highlight centered">
                        <thead>
                            <tr class="card blue-grey lighten-5">
                                <th>CPF</th>
                                <th>Nome</th>
                                <th>E-mail</th>
                                <th>Visualizar/Alterar/Remover</th>
                            </tr>
                        </thead>

                        <tbody>
                            <%
                                //Returna o tamanho da lista de clientes
                                int count = lista.size();

                                for (int i = 0; i < count; i++) {
                                    Cliente aux = lista.get(i);

                                    //Printando as linhas da table com os campos dos clientes preenchidos
                                    try {

                                        out.println("<tr class='card'>");
                                        out.println("<td>" + aux.getCpfCliente().substring(0, 3) + "." + aux.getCpfCliente().substring(3, 6) + "." + aux.getCpfCliente().substring(6, 9) + "-" + aux.getCpfCliente().substring(9, 11) + "</td>");
                                        out.println("<td>" + aux.getNomeCliente() + "</td>");
                                        out.println("<td>" + aux.getEmailCliente() + "</td>");
                                        out.println("<td class=\"center-align\" style=\"width: 200px;\">");
                                        out.print("<a href=\"VisualizarClienteServlet?id=" + aux.getIdCliente() + "\" class=\"btn-floating pulse green opt\"><i class=\"small material-icons white-text\">visibility</i></a>");
                                        out.print("<a href=\"FormAlterarClienteServlet?id=" + aux.getIdCliente() + "\" class=\"btn-floating pulse yellow opt\"><i class=\"small material-icons white-text\">edit</i></a>");
                                        out.print("<a href=\"RemoverClienteServlet?id=" + aux.getIdCliente() + "\" class=\"btn-floating pulse red opt\"><i class=\"small material-icons white-text\">delete</i></a>");
                                        out.println("</td></tr>");

                                    } catch (IOException e) {
                                        throw new IOException(e);
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                    <div style="height: 10px;"></div>
                    <div class="container">
                        <div class="row">
                            <div class="col left-align">
                                <a href="FormNovoClienteServlet" class="waves-effect waves-teal btn-large">Novo<i class="material-icons right">add</i></a>                                
                            </div>
                            <div class="col offset-s3">
                                <a href="portal.jsp" class="waves-effect waves-teal btn-large">Voltar<i class="material-icons right">arrow_back</i></a>                                
                            </div>
                        </div>
                    </div>
                    <div style="height: 100px;"></div>
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

