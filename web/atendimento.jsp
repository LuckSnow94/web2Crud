<%-- 
    Document   : atendimento
    Created on : 14/05/2018, 20:44:52
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

<title>Novo</title>
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
								<form action="AtendimentoServlet?action=new" method="post">
									<div class="col m4">
										<jsp:useBean id="dataAtendimento" class="java.util.Date"/>
										<label>Data/Hora do atendimento:</label><br/>
										<input type="hidden" name="dataAtendimento"	value="<fmt:formatDate value="${dataAtendimento}" type="both" pattern="dd/MM/yyyy HH:mm" />" />
										<span class="black-text" ><fmt:formatDate value="${dataAtendimento}" type="both" pattern="dd/MM/yyyy HH:mm" /></span>
									</div>
									<div class="col m8">
										<label>Cliente:</label> 
										<select name="cliente" class="browser-default">
											<option>Selecione um cliente</option>
											<c:forEach items="${form[0]}" var="cliente">
												<option value="<c:out value="${cliente.idCliente}"/>">
												<c:out value="${cliente.nomeCliente}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m4">
										<label>Tipo do atendimento:</label> 
										<select name="tipoAtendimento" class="browser-default">
											<option>Selecione</option>
											<c:forEach items="${form[1]}" var="tipoAtendimento">
												<option value="<c:out value="${tipoAtendimento.idTipoAtendimento}"/>">
												<c:out value="${tipoAtendimento.nomeTipoAtendimento}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m8">
										<label>Produto:</label> 
										<select name="produto" class="browser-default">
											<option>Selecione um produto</option>
											<c:forEach items="${form[2]}" var="produto">
												<option value="<c:out value="${produto.idProduto}"/>">
												<c:out value="${produto.nomeProduto}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m12">
									<label>Descrição do atendimento:</label>
										<textarea rows="3" cols="85" name="descricaoAtendimento" ></textarea>
									</div>
							<div class="col m10">
								<div style="height: 20px;"></div>
								<label>Resultado do atendimento:</label><br />
								<div class="row">
									<div class="col m5">
										<input type="radio" value="S" id="S"
											name="resultadoAtendimento" /><label class="black-text"
											for="S">Resolvido</label>
									</div>
									<div class="col offset-m2 m5">
										<input type="radio" value="N" id="N"
											name="resultadoAtendimento" /><label class="black-text"
											for="N">Em aberto</label>
									</div>
								</div>
							</div>
								<div style="height: 20px;"></div>
							<div class="container">
										<div class="row">
											<div class="col m5">
												<button type="submit" class="waves-effect waves-teal btn">
													salvar<i class="material-icons right">save</i>
												</button>
											</div>
											<div class="col offset-m2 m5">
												<a href="portal.jsp"
													class="waves-effect waves-teal btn">voltar<i
													class="material-icons right">arrow_back</i></a>
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
	<%@include file="footer.jsp"%>
</body>
</html>