package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.dao.AtendimentoDAO;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;

import java.sql.SQLException;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;

public class AtendimentoFacade {

	public static List<Atendimento> searchAllAtendimentos() {
		AtendimentoDAO dao = new AtendimentoDAO();
		try {
			return dao.listarAtendimentos();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Produto> searchAllProdutos() {
		ProdutoDAO dao = new ProdutoDAO();
		try {
			return dao.listarProdutos();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<TipoAtendimento> searchAllTiposAtendimentos() {
		TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
		try {
			return dao.listarTiposAtendimentos();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Produto buscarProduto(int idProduto) {
		try {
			return new ProdutoDAO().buscarProduto(idProduto);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static TipoAtendimento buscarTipoAtendimento(int idTipoAtendimento) {
		try {
			return new TipoAtendimentoDAO().buscarTipoAtendimento((idTipoAtendimento));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void insertAtendimento(Atendimento t) {
		try {
			new AtendimentoDAO().adicionarAtendimento(t);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Atendimento buscarAtendimento(int id) {
		return new AtendimentoDAO().buscarAtendimento(id);
	}

	public static void insertProduto(String p) {
		try {
			new ProdutoDAO().adicionarProduto(p);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateProduto(Produto p) {
		try {
			new ProdutoDAO().alterarProduto(p);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deleteProduto(int id) {
		try {
			new ProdutoDAO().deletarProduto(id);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void solveAtendimento(int idAtendimento) {
		try {
			new AtendimentoDAO().resolverAtendimento(idAtendimento);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
