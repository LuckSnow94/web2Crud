/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function cpf_mask(v){
    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
    v=v.replace(/(\d)(\d)/,"$1.$2"); //Coloca ponto entre o terceiro e o quarto dígitos
    v=v.replace(/(\d)(\d)/,"$1.$2");//Coloca ponto entre o setimo e o oitava dígitos
    v=v.replace(/(\d)(\d)/,"$1-$2"); //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
    return v;
}

function confirmar(link, id) {
     if (confirm("Deseja remover esse cliente?")) {
          link.href = "ClientesServlet?action=remove&id="+id;
      }
}