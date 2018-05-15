/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.mysql.cj.util.StringUtils;
import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClienteFacade;
import com.ufpr.tads.web2.utils.DataUtil;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luck
 */
@WebServlet(name = "BeanServlet", urlPatterns = {"/BeanServlet"})
public class BeanServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
    }

    protected Cliente fillCliente(HttpServletRequest pRequest) throws ParseException{
        //Busca dados do cliente enviados pelo formulário
        Cliente c = new Cliente();
        if(!(pRequest.getParameter("id") == null)) {
        	int idCliente = Integer.parseInt((String) pRequest.getParameter("id"));
        	c.setIdCliente(idCliente);
        }
        c.setNomeCliente(pRequest.getParameter("nomeCliente"));
        c.setCpfCliente(pRequest.getParameter("cpfCliente").toString().replaceAll("[^0-9]", ""));
        c.setEmailCliente(pRequest.getParameter("emailCliente"));
        String data = pRequest.getParameter("dataCliente");
        if(!StringUtils.isNullOrEmpty(data)){
            c.setDataCliente(DataUtil.formataDataTelaParaBean(data));                        
        }
        String ruaCliente = pRequest.getParameter("ruaCliente");
        if(!StringUtils.isNullOrEmpty(ruaCliente)){
            c.setRuaCliente(ruaCliente);                        
        }
        String cepCliente = pRequest.getParameter("cepCliente").toString().replaceAll("[^0-9]", "");
        if(!StringUtils.isNullOrEmpty(cepCliente)){
            c.setCepCliente(cepCliente);                        
        }
        int cidadeCliente = Integer.parseInt(pRequest.getParameter("cidadeCliente"));
        if(cidadeCliente > 0){
            c.setCidadeCliente(cidadeCliente);                        
        }
        int nrCliente = parseInt(pRequest.getParameter("nrCliente"));
        if(nrCliente > 0){
            c.setNrCliente(nrCliente);                        
        }
        return c;
    }
    
    protected Atendimento fillAtendimento(HttpServletRequest pRequest) throws ParseException{
		Atendimento at = new Atendimento();
		if(!StringUtils.isNullOrEmpty((String) pRequest.getParameter("dataAtendimento")))
			at.setDataHoraAtendimento(DataUtil.formataDataHoraTelaParaBean(pRequest.getParameter("dataAtendimento")));
		if(!StringUtils.isNullOrEmpty(pRequest.getParameter("descricaoAtendimento")))
			at.setDescricaoAtendimento(pRequest.getParameter("descricaoAtendimento"));
		if(!StringUtils.isNullOrEmpty(pRequest.getParameter("produto")))
			at.setProduto(AtendimentoFacade.buscarProduto((Integer.parseInt(pRequest.getParameter("produto")))));
		if(!StringUtils.isNullOrEmpty(pRequest.getParameter("tipoAtendimento")))
			at.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(pRequest.getParameter("tipoAtendimento"))));
		at.setUsuario((LoginBean) pRequest.getSession().getAttribute("user"));
		if(!StringUtils.isNullOrEmpty(pRequest.getParameter("cliente")))
			at.setCliente(ClienteFacade.select(Integer.parseInt(pRequest.getParameter("cliente"))));
		if(!StringUtils.isNullOrEmpty(pRequest.getParameter("resultadoAtendimento")))
			at.setResultadoAtendimento(pRequest.getParameter("resultadoAtendimento"));
    	return at;
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
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(BeanServlet.class.getName()).log(Level.SEVERE, null, ex);
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
