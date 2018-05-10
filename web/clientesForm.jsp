<%-- 
    Document   : clientesForm
    Created on : 25/04/2018, 20:44:52
    Author     : luck
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
@import url("materialize/css/materialize.css");
</style>
<style type="text/css">
@import url("materialize/css/materialize.min.css");
</style>
<style type="text/css">
@import url("materialize/css/web2.css");
</style>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/materialize/js/web2.js"></script>
<script type="text/javascript">

            $(document).ready(function () {
                $("#estado").change(function () {
                    getCidades();
                });
            });

            function getCidades() {
                var idEstado = $("#estado").val();
                var url = "AJAXServlet";
                $.ajax({
                    url: url, // URL da sua Servlet
                    data: {
                        idEstado: idEstado
                    }, // Parâmetro passado para a Servlet
                    dataType: 'json',
                    success: function (data) {
                        // Se sucesso, limpa e preenche a combo de cidade
                        // alert(JSON.stringify(data));
                        $("#cidade").empty();
                        $.each(data, function (i, obj) {
                            $("#cidade").append('<option value=' + obj.idCidade + '>' + obj.nomeCidade +
                                    '</option>');
                        });
                    },
                    error: function (request, textStatus, errorThrown) {
                        alert(request.status + ', Error: ' + request.statusText);// Erro
                    }
                });
            }
        </script>
<script>
            $(document).ready(function () {
                $('select').material_select();
            });
        </script>
<link rel="icon" href="java.ico">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<c:choose>
	<c:when test="${alterar}">
		<title>Alterar</title>
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
						<div class="card-title center-align">
							<h3>Dados do cliente</h3>
						</div>
						<form
							action="ClientesServlet?action=update&id=${cliente.idCliente}"
							method="post">
							<div class="col m6">
								<label>Nome:</label><input type="text" name="nomeCliente"
									required="true" value="<c:out value="${cliente.nomeCliente}"/>"
									size="100" maxlength="100" />
							</div>
							<div class="col m6">
								<label>CPF:</label><input type="text" name="cpfCliente"
									required="true" maxlength="11" minlength="11"
									value="<c:out value="${cliente.cpfCliente}" />" size="11"
									maxlength="11" />
							</div>
							<div class="col m8">
								<label>E-Mail:</label><input type="email" name="emailCliente"
									required="true"
									value="<c:out value="${cliente.emailCliente}" />" size="100"
									maxlength="100" />
							</div>
							<div class="col m4">
								<label>Data de nascimento:</label><input type="date"
									name="dataCliente"
									value="<c:out value="${cliente.dataCliente}"/>" />
							</div>
							<div class="col m8">
								<label>Endereço:</label><input type="text" name="ruaCliente"
									value="<c:out value="${cliente.ruaCliente}" />" size="100"
									maxlength="100" />
							</div>
							<div class="col m4">
								<label>CEP:</label><input type="text" name="cepCliente"
									value="<c:out value="${cliente.cepCliente}" />" size="11"
									maxlength="11" />
							</div>
							<div class="col m4">
								<label>Estado:</label> <select name="ufCliente" id="estado"
									class="browser-default">
									<option><c:out value="${cliente.ufCliente}" /></option>
									<c:forEach items="${estados}" var="estado">
										<option value="<c:out value="${estado.idEstado}"/>"><c:out
												value="${estado.siglaEstado}" /></option>
									</c:forEach>
								</select>
							</div>
							<div class="col m6">
								<label>Cidade:</label> <select name="cidadeCliente" id="cidade"
									class="browser-default">
									<option><c:out value="${cliente.cidadeCliente}" /></option>
								</select>
							</div>
							<div class="col m2">
								<label>N°:</label><input type="number" name="nrCliente"
									value="<c:out value="${cliente.nrCliente}" />" size="11"
									maxlength="11" />
							</div>
							<div class="container">
								<div class="row">
									<div class="col m5">
										<button type="submit" class="waves-effect waves-teal btn">
											salvar<i class="material-icons right">save</i>
										</button>
									</div>
									<div class="col offset-m2 m5">
										<a href="ClientesServlet" class="waves-effect waves-teal btn">cancelar<i
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
	</c:when>
	<c:when test="${visualizar}">
		<title>Visualizar</title>
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
								<div class="card-title center-align">
									<h3>Dados do cliente</h3>
								</div>
								<form>
									<div class="col m6">
										<label>Nome:</label><input type="text" name="nomeCliente"
											disabled="true"
											value="<c:out value="${cliente.nomeCliente}" />" />
									</div>
									<div class="col m6">
										<label>CPF:</label><input type="text" name="cpfCliente"
											disabled="true"
											value="<c:out value="${cliente.cpfCliente}" />" />
									</div>
									<div class="col m8">
										<label>E-Mail:</label><input type="email" name="emailCliente"
											disabled="true"
											value="<c:out value="${cliente.emailCliente}" />">
									</div>
									<div class="col m4">
										<label>Data de nascimento:</label><input type="date"
											name="dataCliente" disabled="true"
											value="<c:out value="${cliente.dataCliente}"/>" />
									</div>
									<div class="col m8">
										<label>Endereço:</label><input type="text" name="ruaCliente"
											disabled="true"
											value="<c:out value="${cliente.ruaCliente}"/>" />
									</div>
									<div class="col m4">
										<label>CEP:</label><input type="text" name="cepCliente"
											disabled="true"
											value="<c:out value="${cliente.cepCliente}"/>" />
									</div>
									<div class="col m4">
										<label>Estado:</label> 
										<select name="ufCliente" id="estado"
											class="browser-default" disabled="true">
											<option><c:out value="${cliente.ufCliente}" /></option>
											<c:forEach items="${estados}" var="estado">
												<option value="<c:out value="${estado.idEstado}"/>"><c:out
														value="${estado.siglaEstado}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m6">
										<label>Cidade:</label> <select name="cidadeCliente"
											id="cidade" class="browser-default" disabled="true">
											<option><c:out value="${cliente.cidadeCliente}" /></option>
										</select>
									</div>
									<div class="col m2">
										<label>N°:</label><input type="number" name="nrCliente"
											disabled="true" value="<c:out value="${cliente.nrCliente}"/>" />
									</div>
									<a href="ClientesServlet?action=list"
										class="waves-effect waves-teal btn">Voltar<i
										class="material-icons right">arrow_back</i></a>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div style="height: 100px;"></div>
			</div>]
			<script type="text/javascript">
				
				$(document).ready(function () {
	                $("#cidade").change(function () {
	                    getEstadoCliente();
	                });
	            });
				
			</script>
	</c:when>
	<c:otherwise>
		<title>Novo</title>
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
								<div class="card-title center-align">
									<h3>Dados do cliente</h3>
								</div>
								<form action="ClientesServlet?action=new" method="post">
									<div class="col m6">
										<label>Nome:</label><input type="text" required="true"
											name="nomeCliente" size="100" maxlength="100" />
									</div>
									<div class="col m6">
										<label>CPF:</label><input type="text" required="true"
											maxlength="11" minlength="11" name="cpfCliente" size="11"
											maxlength="11" />
									</div>
									<div class="col m8">
										<label>E-Mail:</label><input type="email" required="true"
											name="emailCliente" size="100" maxlength="100" />
									</div>
									<div class="col m4">
										<jsp:useBean id="data" class="java.util.Date" />
										<label>Data de nascimento:</label><input type="date"
											name="dataCliente"
											value="<fmt:formatDate value="${data}" pattern="yyyy-MM-dd" />" />
									</div>
									<div class="col m8">
										<label>Endereço:</label><input type="text" name="ruaCliente"
											size="100" maxlength="100" />
									</div>
									<div class="col m4">
										<label>CEP:</label><input type="text" name="cepCliente"
											size="11" maxlength="11" />
									</div>
									<div class="col m3">
										<label>Estado:</label> <select name="ufCliente" id="estado"
											class="browser-default">
											<option value=""></option>
											<c:forEach items="${estados}" var="estado">
												<option value="<c:out value="${estado.idEstado}"/>"><c:out
														value="${estado.siglaEstado}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m6">
										<label>Cidade:</label> <select name="cidadeCliente"
											id="cidade" class="browser-default">
											<option value=""></option>
										</select>
									</div>
									<div class="col m3">
										<label>N°:</label><input type="number" name="nrCliente" />
									</div>
									<div class="container">
										<div class="row">
											<div class="col m5">
												<button type="submit" class="waves-effect waves-teal btn">
													salvar<i class="material-icons right">save</i>
												</button>
											</div>
											<div class="col offset-m2 m5">
												<a href="ClientesServlet"
													class="waves-effect waves-teal btn">cancelar<i
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
	</c:otherwise>
	</c:choose>
	<%@include file="footer.jsp"%>
</body>
</html>
