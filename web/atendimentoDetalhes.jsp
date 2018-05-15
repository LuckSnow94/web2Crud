<%-- 
    Document   : atendimentoDetalhes
    Created on : 15/05/2018, 15:44:52
    Author     : luck
--%>

<%@page import="java.util.Date"%>
<%@page import="java.beans.Beans"%>
<%@page import="com.mysql.cj.util.StringUtils"%>
<%@page errorPage="erro.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${empty sessionScope.user}">
	<jsp:forward page="index.jsp">
		<jsp:param name="msg"
			value="Usuário deve se autenticar para acessar o sistema." />
	</jsp:forward>
</c:if>
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

<title>Visualizar</title>
		</head>
		<body class="bgimg">
			<div class="container">
				<div class="row">
					<div class="col offset-m2 m8">
						<h1 class="white-text">@Portal</h1>
						<h2 class="white-text">Olá, ${user.nome}</h2>
						<div class="divider"></div>
						<div class="card">
							<div class="card-content">
								<div class="card-title center-align">
									<h3>Dados do Atendimento</h3>
								</div>
								<form>
									<div class="col m4">
										<jsp:useBean id="dataAtendimento" class="java.util.Date"/>
										<label>Data/Hora do atendimento:</label><br/>
										<span class="black-text" ><fmt:formatDate value="${atendimento.dataHoraAtendimento}" type="both" pattern="dd/MM/yyyy HH:mm" /></span>
									</div>
									<div class="col m8">
										<label>Cliente:</label> 
										<select name="cliente" class="browser-default" disabled>
											<option><c:out value="${atendimento.cliente.nomeCliente}" /></option>
										</select>
									</div>
									<div class="col m4">
										<label>Tipo do atendimento:</label> 
										<select name="tipoAtendimento" class="browser-default" disabled>
											<option><c:out value="${atendimento.tipoAtendimento.nomeTipoAtendimento}" /></option>
										</select>
									</div>
									<div class="col m8">
										<label>Produto:</label> 
										<select name="produto" class="browser-default" disabled>
											<option><c:out value="${atendimento.produto.nomeProduto}" /></option>
										</select>
									</div>
									<div class="col m12">
									<label>Descrição do atendimento:</label>
										<textarea rows="3" cols="85" name="descricaoAtendimento" disabled><c:out value="${atendimento.descricaoAtendimento}" /></textarea>
									</div>
							<div class="col m10">
								<div style="height: 20px;"></div>
								<label>Resultado do atendimento:</label>
								<div class="row">
									<div class="col m5">
										<label class="black-text bold">
										<c:out value="${atendimento.resultadoAtendimento == \"S\" ? \"Resolvido\": \"Em Aberto\"}" />
										</label>
									</div>
								</div>
							</div>
								<div style="height: 20px;"></div>
							<a href="AtendimentoServlet?action=list"
								class="waves-effect waves-teal btn">voltar<i
								class="material-icons right">arrow_back</i></a>
						</form>
							</div>
						</div>
					</div>
				</div>
				<div style="height: 100px;"></div>
			</div>
	<%@include file="footer.jsp"%>
</body>
</html>