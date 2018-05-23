package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;

/**
 * Servlet implementation class AtendimentoServlet
 */
@WebServlet("/AtendimentoServlet")
public class AtendimentoServlet extends BeanServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtendimentoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {
        
	    HttpSession session = request.getSession();
	
	    if (session == null || session.getAttribute("user") == null) {
	
	        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	        request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
	        rd.forward(request, response);
	        
	    }else {
			Atendimento at;
			RequestDispatcher rd;
	    	
	    	String action = request.getParameter("action");
	    	
	    	switch (action) {
	    	
	    	//Lista os atendimentos
			case "list":
				request.setAttribute("lista", AtendimentoFacade.searchAll());
				rd = request.getRequestDispatcher("atendimentoListar.jsp");
		        rd.forward(request, response);
				break;
			//Realiza um novo atendimento
			case "form":
				request.setAttribute("form", AtendimentoFacade.form());
				rd = request.getRequestDispatcher("atendimento.jsp");
		        rd.forward(request, response);
		        break;
			//Mostra os detalhes de um atendimento
			case "new":
				AtendimentoFacade.insert(super.fillAtendimento(request));
				response.sendRedirect("portal.jsp");
				break;
			case "show":
				int id = Integer.parseInt(request.getParameter("id"));
				if(id > 0) {
					at = AtendimentoFacade.search(id);
					request.setAttribute("atendimento", at);
					rd = request.getRequestDispatcher("atendimentoDetalhes.jsp");
			        rd.forward(request, response);
				}
				break;
		    //Lista os atendimentos
			default:
				request.setAttribute("lista", AtendimentoFacade.searchAll());
				rd = request.getRequestDispatcher("atendimentoListar.jsp");
		        rd.forward(request, response);
				break;
			}
	    }

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

}
