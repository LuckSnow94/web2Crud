/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.facade.ClienteFacade;
import com.ufpr.tads.web2.facade.EnderecoFacade;

/**
 *
 * @author luck
 */
@WebServlet(name = "AjaxServlet", urlPatterns = {"/AjaxServlet"})
public class AjaxServlet extends HttpServlet {

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
        throws ServletException, IOException, ClassNotFoundException, SQLException {
        
            String idEstado = request.getParameter("idEstado");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String json = "{}";
            boolean existe = true;
            
            // Vai no BD buscar todas as cidades deste estado, em uma lista
            if (!StringUtils.isNullOrEmpty(idEstado)) {
            	int estado = Integer.parseInt(idEstado);
            	try {
            		List<Cidade> cidades  = EnderecoFacade.listarCidades(estado);
            		json = new Gson().toJson(cidades);
            	} catch (InstantiationException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	} catch (IllegalAccessException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}            	
            }
            // Verifica email duplicado
            else if (!StringUtils.isNullOrEmpty(email)) {
                existe = ClienteFacade.verifyEmail(email);
                json = "{\"result\": " + existe +  "}";
            }
            // Verifica cpf duplicado
            else if (!StringUtils.isNullOrEmpty(cpf)) {
                existe = ClienteFacade.verifyCpf(cpf);
                json = "{\"result\": " + existe +  "}";            	
            }
            
			// retorna o JSON
            // transforma o MAP em JSON
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
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
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AjaxServlet.class.getName()).log(Level.SEVERE, null, ex);
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
