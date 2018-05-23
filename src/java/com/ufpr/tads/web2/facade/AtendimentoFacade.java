package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.dao.AtendimentoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;

public class AtendimentoFacade {
	
	public static ArrayList<List<?>> form() {
		ArrayList<List<?>> form = new ArrayList<List<?>>();
		try {
			//Busca clientes
			List<Cliente> clientes = ClienteFacade.selectAll();
			form.add(clientes);
			//Busca produtos
			List<Produto> produtos = ProdutoFacade.searchAll();
			List<TipoAtendimento> tiposAtendimento = TipoAtendimentoFacade.searchAll();
			form.add(tiposAtendimento);
			form.add(produtos);
			//Busca tipos de atendimentos
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;		
	}

	public static List<Atendimento> searchAll() {
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

	public static Atendimento search(int id) {
		return new AtendimentoDAO().buscarAtendimento(id);
	}

	public static void insert(Atendimento t) {
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

	public static void solve(int idAtendimento) {
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
