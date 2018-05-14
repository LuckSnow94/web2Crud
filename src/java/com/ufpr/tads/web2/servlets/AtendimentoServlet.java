package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AtendimentoServlet
 */
@WebServlet("/AtendimentoServlet")
public class AtendimentoServlet extends HttpServlet {
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
	    	String action = request.getParameter("action");
	    	
	    	switch (action) {
	    	
	    	//Lista os atendimentos
			case "list":
				List<Atendimento> lista = AtendimentoFacade.searchAll();
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		        rd.forward(request, response);
				break;
			//Realiza um novo atendimento
			case "make":
				
				break;
			//Mostra os detalhes de um atendimento
			case "show":
				
				break;
		    //Lista os atendimentos
			default:
				break;
			}
	    }

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
