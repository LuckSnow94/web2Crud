package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClienteFacade;

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
	    	List<Atendimento> lista;
			List<Cliente> clientes = null;
			Atendimento at;
			RequestDispatcher rd;
	    	
	    	String action = request.getParameter("action");
	    	
	    	switch (action) {
	    	
	    	//Lista os atendimentos
			case "list":
				lista = AtendimentoFacade.searchAllAtendimentos();
				request.setAttribute("lista", lista);
				rd = request.getRequestDispatcher("atendimentoListar.jsp");
		        rd.forward(request, response);
				break;
			//Realiza um novo atendimento
			case "make":
				//Busca clientes
				try {
					clientes = ClienteFacade.selectAll();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Busca ProdutoResource
				List<Produto> produtos = AtendimentoFacade.searchAllProdutos();
				//Busca tipos de atendimentos
				List<TipoAtendimento> tiposAtendimento = AtendimentoFacade.searchAllTiposAtendimentos();
				request.setAttribute("clientes", clientes);
				request.setAttribute("ProdutoResource", produtos);
				request.setAttribute("tiposAtendimento", tiposAtendimento);
				rd = request.getRequestDispatcher("atendimento.jsp");
		        rd.forward(request, response);
		        break;
			//Mostra os detalhes de um atendimento
			case "new":
				at = super.fillAtendimento(request);
				AtendimentoFacade.insertAtendimento(at);
				response.sendRedirect("portal.jsp");
				break;
			case "show":
				int id = Integer.parseInt(request.getParameter("id"));
				if(id > 0) {
					at = AtendimentoFacade.buscarAtendimento(id);
					request.setAttribute("atendimento", at);
					rd = request.getRequestDispatcher("atendimentoDetalhes.jsp");
			        rd.forward(request, response);
				}
				break;
		    //Lista os atendimentos
			default:
				lista = AtendimentoFacade.searchAllAtendimentos();
				rd = request.getRequestDispatcher("atendimentoListar.jsp");
				request.setAttribute("lista", lista);
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
