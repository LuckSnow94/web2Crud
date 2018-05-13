function confirmar(link, id) {
     if (confirm("Deseja remover esse cliente?")) {
          link.href = "ClientesServlet?action=remove&id="+id;
      }
}

function getCidades() {
    var idEstado = $("#estado").val();
    var url = "AjaxServlet";
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

function validaCPF(campocpf) {    
	cpf = campocpf.value.toString().replace(/[^0-9]/g, "");
    var numeros, digitos, soma, i, resultado, digitos_iguais;
    digitos_iguais = 1;
    if (cpf.length < 11){
    	alert("CPF inválido.");
    	campocpf.value = "";
    	return false;
    }
    for (i = 0; i < cpf.length - 1; i++)
          if (cpf.charAt(i) != cpf.charAt(i + 1))
                {
                digitos_iguais = 0;
                break;
                }
    if (!digitos_iguais)
          {
          numeros = cpf.substring(0,9);
          digitos = cpf.substring(9);
          soma = 0;
          for (i = 10; i > 1; i--)
                soma += numeros.charAt(10 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(0)){
          	alert("CPF inválido.");
	      	campocpf.value = "";
	      	return false;
          }
          numeros = cpf.substring(0,10);
          soma = 0;
          for (i = 11; i > 1; i--)
                soma += numeros.charAt(11 - i) * i;
          resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
          if (resultado != digitos.charAt(1)){
          	alert("CPF inválido.");
        	campocpf.value = "";
        	return false;
          }
          return true;
          }
    else    	{
    	alert("CPF inválido.");
    	campocpf.value = "";
    	return false;
    }
  }