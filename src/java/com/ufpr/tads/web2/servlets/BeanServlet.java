/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import com.mysql.cj.util.StringUtils;
import com.ufpr.tads.web2.beans.Cliente;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        c.setNomeCliente(pRequest.getParameter("nomeCliente"));
        c.setCpfCliente(pRequest.getParameter("cpfCliente"));
        c.setEmailCliente(pRequest.getParameter("emailCliente"));
        String data = pRequest.getParameter("dataCliente");
        if(!StringUtils.isNullOrEmpty(data)){
            //Converter data de string para data java
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = format.parse(data);
            c.setDataCliente(date);                        
        }
        String ruaCliente = pRequest.getParameter("ruaCliente");
        if(!StringUtils.isNullOrEmpty(ruaCliente)){
            c.setRuaCliente(ruaCliente);                        
        }
        String cepCliente = pRequest.getParameter("cepCliente");
        if(!StringUtils.isNullOrEmpty(cepCliente)){
            c.setCepCliente(cepCliente);                        
        }
        int cidadeCliente = Integer.parseInt(pRequest.getParameter("cidadeCliente"));
        if(cidadeCliente > 0){
            c.setCidadeCliente(cidadeCliente);                        
        }                    
        String ufCliente = pRequest.getParameter("ufCliente");
        if(!StringUtils.isNullOrEmpty(ufCliente)){
            c.setUfCliente(ufCliente);                        
        }
        int nrCliente = parseInt(pRequest.getParameter("nrCliente"));
        if(nrCliente > 0){
            c.setNrCliente(nrCliente);                        
        }
        return c;
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
