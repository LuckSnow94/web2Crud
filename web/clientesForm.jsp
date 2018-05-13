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
<style type="text/css">@import url("materialize/css/materialize.css");</style>
<style type="text/css">@import url("materialize/css/materialize.min.css");</style>
<style type="text/css">@import url("materialize/css/web2.css");</style>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
<script type="text/javascript" src="materialize/js/web2.js"></script>
<link rel="icon" href="java.ico">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript">

$(document).ready(function () {
    $("#estado").change(function () {
        getCidades();
    });
});

$( "#cpf" ).mask('000.000.000-00');
$( "#cep" ).mask("00000-000");

</script>
<c:choose>
<c:when test="${visualizar}">
		<title>Visualizar</title>
		</head>
		<body class="bgimg">
			<script type="text/javascript">
            
			window.onload = function() {
				var id = <c:out value="${estado}" />;
				document.getElementById("estado")[id].selected = true;
			}
			
		</script>
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
											disabled value="<c:out value="${cliente.nomeCliente}" />" />
									</div>
									<div class="col m6">
										<label>CPF:</label><input type="text" id="cpf" name="cpfCliente"
											maxlength="11" size="14" disabled
											value="<c:out value="${cliente.cpfCliente}" />" />
									</div>
									<div class="col m8">
										<label>E-Mail:</label><input type="email" name="emailCliente"
											disabled value="<c:out value="${cliente.emailCliente}" />">
									</div>
									<div class="col m4">
										<label>Data de nascimento:</label><input type="date"
											name="dataCliente" disabled
											value="<c:out value="${cliente.dataCliente}"/>" />
									</div>
									<div class="col m8">
										<label>Endereço:</label><input type="text" name="ruaCliente"
											disabled value="<c:out value="${cliente.ruaCliente}"/>" />
									</div>
									<div class="col m4">
										<label>CEP:</label><input type="text" id="cep" name="cepCliente"
											disabled value="<c:out value="${cliente.cepCliente}"/>" 
											size="9" maxlength="8"/>
									</div>
									<div class="col m4">
										<label>Estado:</label> <select name="ufCliente" id="estado"
											class="browser-default" disabled>
											<c:forEach items="${estados}" var="estado">
												<option value="<c:out value="${estado.idEstado}"/>">
													<c:out value="${estado.siglaEstado}" />
												</option>
											</c:forEach>
										</select>
									</div>
									<div class="col m6">
										<label>Cidade:</label> <select name="cidadeCliente"
											id="cidade" class="browser-default" disabled>
											<option value='<c:out value="${cidade.idCidade}"></c:out>'>
												<c:out value="${cidade.nomeCidade}" />
											</option>
										</select>
									</div>
									<div class="col m2">
										<label>N°:</label><input type="number" name="nrCliente"
											disabled value="<c:out value="${cliente.nrCliente}"/>" size="3" max="999" maxlength="3"/>
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
			</div>
	</c:when>
	<c:when test="${alterar}">
		<title>Alterar</title>
</head>
<body class="bgimg">
	<script type="text/javascript">

	function existeCPF(campocpf){
		if(validaCPF(campocpf)){
			cpf = campocpf.value.toString().replace(/[^0-9]/g, "");
			if (cpf == "${cliente.cpfCliente}")
				return true;
	        var url = "AjaxServlet";
	        var res = false;
	        $.ajax({
	            url : url, // URL da sua Servlet
	            data : {
	                cpf: cpf
	            },
	            async: false,
	            dataType : 'json',
	            success : function(data) {
	                res = data.result;
	            },
	            error : function() {
	                res = false;
	            }
	        });
	        if (res){
                alert('CPF já existente');
                campocpf.value = "";
		        return !res;
	        }				
		}
    }
    
	function existeEmail(campoemail){
    	email = campoemail.value;
    	if (email == "${cliente.emailCliente}")
			return true;
    	var url = "AjaxServlet";
	    var res = false;
        $.ajax({
            url : url, // URL da sua Servlet
            data : {
                email: email
            },
            async: false,
            dataType : 'json',
            success : function(data) {
                res = data.result;
            },
            error : function() {
                res = false;
            }
        });
        if (res){
            alert('E-mail já existente');
            campoemail.value = "";
	        return !res;
        }
    }
	
	window.onload = function() {
			var id = <c:out value="${estado}" />;
			document.getElementById("estado")[id].selected = true;
		}
</script>
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
									required value="<c:out value="${cliente.nomeCliente}"/>"
									size="100" maxlength="100" />
							</div>
							<div class="col m6">
								<label>CPF:</label><input type="text" id="cpf" name="cpfCliente" required
									maxlength="11" value="<c:out value="${cliente.cpfCliente}" />"
									size="14" onblur="existeCPF(this);" />
							</div>
							<div class="col m8">
								<label>E-Mail:</label><input type="email" name="emailCliente"
									required value="<c:out value="${cliente.emailCliente}" />"
									size="100" maxlength="100" onblur="existeEmail(this);" />
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
								<label>CEP:</label><input type="text" id="cep" name="cepCliente"
									value="<c:out value="${cliente.cepCliente}" />" size="9"
									maxlength="8" />
							</div>
							<div class="col m4">
								<label>Estado:</label> <select name="ufCliente" id="estado"
									class="browser-default">
									<c:forEach items="${estados}" var="estado">
										<option value="<c:out value="${estado.idEstado}"/>">
											<c:out value="${estado.siglaEstado}" />
										</option>
									</c:forEach>
								</select>
							</div>
							<div class="col m6">
								<label>Cidade:</label> <select name="cidadeCliente" id="cidade"
									class="browser-default">
									<option value='<c:out value="${cidade.idCidade }"></c:out>'>
										<c:out value="${cidade.nomeCidade}" />
									</option>
								</select>
							</div>
							<div class="col m2">
								<label>N°:</label><input type="number" name="nrCliente"
									value="<c:out value="${cliente.nrCliente}" />" size="3" max="999" maxlength="3"/>
							</div>
							<div class="container">
								<div class="row">
									<div class="col m5">
										<button type="submit" class="waves-effect waves-teal btn">
											salvar<i class="material-icons right">save</i>
										</button>
									</div>
									<div class="col offset-m2 m5">
										<a href="ClientesServlet" class="waves-effect waves-teal btn">voltar<i
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
	
	<c:otherwise>
		<title>Novo</title>
		</head>
		<body class="bgimg">
		<script type="text/javascript">
		
		function existeCPF(campocpf){
			if(validaCPF(campocpf)){
				cpf = campocpf.value.toString().replace(/[^0-9]/g, "");
		        var url = "AjaxServlet";
		        var res = false;
		        $.ajax({
		            url : url, // URL da sua Servlet
		            data : {
		                cpf: cpf
		            },
		            async: false,
		            dataType : 'json',
		            success : function(data) {
		                res = data.result;
		            },
		            error : function() {
		                res = false;
		            }
		        });
		        if (res){
	                alert('CPF já existente');
	                campocpf.value = "";
			        return !res;
		        }				
			}
	    }
	    
	    function existeEmail(campoemail){
	    	email = campoemail.value;
	        var url = "AjaxServlet";
		    var res = false;
	        $.ajax({
	            url : url, // URL da sua Servlet
	            data : {
	                email: email
	            },
	            async: false,
	            dataType : 'json',
	            success : function(data) {
	                res = data.result;
	            },
	            error : function() {
	                res = false;
	            }
	        });
	        if (res){
                alert('E-mail já existente');
                campocpf.value = "";
		        return !res;
	        }
	    }
	    
		</script>
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
										<label>Nome:</label><input type="text" required
											name="nomeCliente" size="100" maxlength="100" />
									</div>
									<div class="col m6">
										<label>CPF:</label><input type="text" id="cpf" required
											name="cpfCliente" size="14" maxlength="11" onblur="existeCPF(this);" />
									</div>
									<div class="col m8">
										<label>E-Mail:</label><input type="email" required
											name="emailCliente" size="100" maxlength="100" onblur="existeEmail(this);"/>
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
										<label>CEP:</label><input type="text" id="cep" name="cepCliente"
											size="9" maxlength="8" />
									</div>
									<div class="col m3">
										<label>Estado:</label> <select name="ufCliente" id="estado"
											class="browser-default">
											<option><label>Selecione</label></option>
											<c:forEach items="${estados}" var="estado">
												<option value="<c:out value="${estado.idEstado}"/>"><c:out
														value="${estado.siglaEstado}" /></option>
											</c:forEach>
										</select>
									</div>
									<div class="col m6">
										<label>Cidade:</label> <select name="cidadeCliente"
											id="cidade" class="browser-default">
											<option><label>Selecione um estado</label></option>
										</select>
									</div>
									<div class="col m3">
										<label>N°:</label><input type="number" name="nrCliente" size="3" max="999" maxlength="3"/>
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
	</c:otherwise>
	</c:choose>
	<%@include file="footer.jsp"%>
</body>
</html>
