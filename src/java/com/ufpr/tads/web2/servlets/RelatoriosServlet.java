package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.util.StringUtils;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.facade.TipoAtendimentoFacade;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class RelatoriosServlet
 */
@WebServlet("/RelatoriosServlet")
public class RelatoriosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelatoriosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		HttpSession session = request.getSession();

		if (session == null || session.getAttribute("user") == null) {

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema.");
			rd.forward(request, response);

		} else {

			// InÃ­cio Controller

			// Variaveis de controle
			String action = request.getParameter("action");
			RequestDispatcher rd = null;
			List<TipoAtendimento> tiposAtendimento;

			// IdentificaÃ§Ã£o da action
			if (!StringUtils.isNullOrEmpty(action)) {
				switch (action) {
				case "form":
					tiposAtendimento = TipoAtendimentoFacade.searchAll();
					request.setAttribute("tiposAtendimento", tiposAtendimento);
                    rd = request.getRequestDispatcher("relatorios.jsp");
                    rd.forward(request, response);
					break;
				case "clientes":
					gerarRelatorioClientes(request,response);
					break;
				case "atendimentos":
					gerarRelatorioAtendimentos(request,response);					
					break;
				case "atendimentosResolvidos":
					gerarRelatorioAtendimentosResolvidos(request,response);
					break;
				case "atendimentosTipo":
					gerarRelatorioTiposAtendimento(request,response);
					break;
				default:
					tiposAtendimento = TipoAtendimentoFacade.searchAll();
					request.setAttribute("tiposAtendimento", tiposAtendimento);
                    rd = request.getRequestDispatcher("relatorios.jsp");
                    rd.forward(request, response);
					break;
				}
			}
		}
	}
    
	public void gerarRelatorioClientes(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		Connection con = null;
		try {
			// Conexão com o banco
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/web2?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "luck");
			// Caminho contextualizado do relatório compilado
			String jasper = pRequest.getContextPath() + "/Teste.jasper";
			// Host onde o servlet esta executando
			String host = "http://" + pRequest.getServerName() + ":" + pRequest.getServerPort();
			// URL para acesso ao relatório
			URL jasperURL = new URL(host + jasper);
			// Parâmetros do relatório
			HashMap<String, Object> params = new HashMap<String, Object>();
			// Geração do relatório
			byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
			if (bytes != null) {
				// A página será mostrada em PDF
				pResponse.setContentType("application/pdf");
				// Envia o PDF para o Cliente
				OutputStream ops = pResponse.getOutputStream();
				ops.write(bytes);
			}
		} catch (ClassNotFoundException e) {
			pRequest.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (SQLException e) {
			pRequest.setAttribute("mensagem", "Erro de conexão ou query: " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (JRException e) {
			pRequest.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void gerarRelatorioAtendimentos(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		Connection con = null;
		try {
			// Conexão com o banco
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/web2?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "luck");
			// Caminho contextualizado do relatório compilado
			String jasper = pRequest.getContextPath() + "/Teste.jasper";
			// Host onde o servlet esta executando
			String host = "http://" + pRequest.getServerName() + ":" + pRequest.getServerPort();
			// URL para acesso ao relatório
			URL jasperURL = new URL(host + jasper);
			// Parâmetros do relatório
			HashMap<String, Object> params = new HashMap<String, Object>();
			// Geração do relatório
			byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
			if (bytes != null) {
				// A página será mostrada em PDF
				pResponse.setContentType("application/pdf");
				// Envia o PDF para o Cliente
				OutputStream ops = pResponse.getOutputStream();
				ops.write(bytes);
			}
		} catch (ClassNotFoundException e) {
			pRequest.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (SQLException e) {
			pRequest.setAttribute("mensagem", "Erro de conexão ou query: " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (JRException e) {
			pRequest.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void gerarRelatorioAtendimentosResolvidos(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		Connection con = null;
		try {
			// Conexão com o banco
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/web2?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "luck");
			// Caminho contextualizado do relatório compilado
			String jasper = pRequest.getContextPath() + "relatorios/relatorioClientes.jasper";
			// Host onde o servlet esta executando
			String host = "http://" + pRequest.getServerName() + ":" + pRequest.getServerPort();
			// URL para acesso ao relatório
			URL jasperURL = new URL(host + jasper);
			// Parâmetros do relatório
			HashMap<String, Object> params = new HashMap<String, Object>();
			// Geração do relatório
			byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
			if (bytes != null) {
				// A página será mostrada em PDF
				pResponse.setContentType("application/pdf");
				// Envia o PDF para o Cliente
				OutputStream ops = pResponse.getOutputStream();
				ops.write(bytes);
			}
		} catch (ClassNotFoundException e) {
			pRequest.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (SQLException e) {
			pRequest.setAttribute("mensagem", "Erro de conexão ou query: " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (JRException e) {
			pRequest.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void gerarRelatorioTiposAtendimento(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		Connection con = null;
		try {
			// Conexão com o banco
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/web2?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "luck");
			// Caminho contextualizado do relatório compilado
			String jasper = pRequest.getContextPath() + "/Teste.jasper";
			// Host onde o servlet esta executando
			String host = "http://" + pRequest.getServerName() + ":" + pRequest.getServerPort();
			// URL para acesso ao relatório
			URL jasperURL = new URL(host + jasper);
			// Parâmetros do relatório
			HashMap<String, Object> params = new HashMap<String, Object>();
			// Geração do relatório
			byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
			if (bytes != null) {
				// A página será mostrada em PDF
				pResponse.setContentType("application/pdf");
				// Envia o PDF para o Cliente
				OutputStream ops = pResponse.getOutputStream();
				ops.write(bytes);
			}
		} catch (ClassNotFoundException e) {
			pRequest.setAttribute("mensagem", "Driver BD não encontrado : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (SQLException e) {
			pRequest.setAttribute("mensagem", "Erro de conexão ou query: " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} catch (JRException e) {
			pRequest.setAttribute("mensagem", "Erro no Jasper : " + e.getMessage());
			pRequest.getRequestDispatcher("erro.jsp").forward(pRequest, pResponse);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        processRequest(request, response);
	}

}