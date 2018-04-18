/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.mysql.jdbc.StringUtils;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.facade.ClientesFacade;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luck
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
        
    HttpSession session = request.getSession();

    if (session == null || session.getAttribute("user") == null) {

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
        rd.forward(request, response);
        
    }else {
    
        //Início Controller
        
        //Variaveis de controle
        String action = request.getParameter("action");
        RequestDispatcher rd = null;
        int id = 0;
        List<Cliente> lista = null;
        Cliente c = null;
        
        //Identificação da action
        if(!StringUtils.isNullOrEmpty(action)){
            switch(action){

                //Listar clientes do banco de dados
                case "list":
                    lista = ClientesFacade.searchAll();
                    rd = request.getRequestDispatcher("clientesListar.jsp");
                    request.setAttribute("lista", lista);
                    rd.forward(request, response);                
                    break;

                //Visualizar informações de um cliente
                case "show":
                    //Busca id do cliente a ser visualizado no parametro da página
                    id = parseInt((String)request.getParameter("id"));
                    if( id > 0){
                        Cliente cliente = ClientesFacade.search(id);
                        rd = request.getRequestDispatcher("clientesVisualizar.jsp");
                        request.setAttribute("cliente", cliente);
                        rd.forward(request, response);
                    }
                    break;

                //Ir para tela de alteração de informações de um cliente
                case "formUpdate":
                    //Busca id do cliente a ser visualizado no parametro da página
                    id = parseInt((String)request.getParameter("id"));
                    if( id > 0){
                        Cliente cliente = ClientesFacade.search(id);
                        rd = request.getRequestDispatcher("clientesAlterar.jsp");
                        request.setAttribute("cliente", cliente);
                        rd.forward(request, response);
                    }
                    break;

                //Remover um cliente
                case "remove":
                    //Busca id do cliente a ser removido no parametro da página
                    id = parseInt((String)request.getParameter("id"));
                    if( id > 0){
                        ClientesFacade.delete(id);
                        rd = request.getRequestDispatcher("ClientesServlet?action=list");
                        rd.forward(request, response);  
                    }
                    break;

                //Atualizar informações de um cliente
                case "update":
                    id = parseInt(request.getParameter("id"));
                    if( id > 0){
                        //Busca dados do cliente enviados pelo formulário
                        c = new Cliente();
                        c.setIdCliente(id);
                        c.setNomeCliente(request.getParameter("nomeCliente"));
                        c.setCpfCliente(request.getParameter("cpfCliente"));
                        c.setEmailCliente(request.getParameter("emailCliente"));

                        //Converter data de string para data java
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String data = request.getParameter("dataCliente");
                        java.util.Date date = format.parse(data);

                        c.setDataCliente(date);
                        c.setRuaCliente(request.getParameter("ruaCliente"));
                        c.setCepCliente(request.getParameter("cepCliente"));
                        c.setCidadeCliente(request.getParameter("cidadeCliente"));
                        c.setUfCliente(request.getParameter("ufCliente"));
                        c.setNrCliente(parseInt(request.getParameter("nrCliente")));

                        ClientesFacade.update(c);
                        rd = request.getRequestDispatcher("ClientesServlet?action=list");
                        rd.forward(request, response);
                    }
                    break;

                //Ir para tela de inserção de um novo cliente
                case "formNew":
                    rd = request.getRequestDispatcher("clientesNovo.jsp");
                    rd.forward(request, response);
                    break;

                //INserir um novo cliente
                case "new":
                    //Busca dados do cliente enviados pelo formulário
                    c = new Cliente();
                    c.setNomeCliente(request.getParameter("nomeCliente"));
                    c.setCpfCliente(request.getParameter("cpfCliente"));
                    c.setEmailCliente(request.getParameter("emailCliente"));

                    //Converter data de string para data java
                    String data = request.getParameter("dataCliente");
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date = format.parse(data);
                    c.setDataCliente(date);

                    c.setRuaCliente(request.getParameter("ruaCliente"));
                    c.setCepCliente(request.getParameter("cepCliente"));
                    c.setCidadeCliente(request.getParameter("cidadeCliente"));
                    c.setUfCliente(request.getParameter("ufCliente"));
                    c.setNrCliente(parseInt(request.getParameter("nrCliente")));

                    ClientesFacade.insert(c);
                    rd = request.getRequestDispatcher("ClientesServlet?action=list");
                    rd.forward(request, response);
                    break;

                default:
                    break;
            }            
        }else{
            //Por default, a controller lista os clientes
            lista = ClientesFacade.searchAll();
            rd = request.getRequestDispatcher("clientesListar.jsp");
            request.setAttribute("lista", lista);
            rd.forward(request, response);
        }
        
        //Fim Controller
        
        }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ClientesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
