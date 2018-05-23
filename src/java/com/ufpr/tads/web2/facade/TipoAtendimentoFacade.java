package com.ufpr.tads.web2.facade;

import java.sql.SQLException;
import java.util.List;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;

public class TipoAtendimentoFacade {

	public static List<TipoAtendimento> searchAll() {
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
	
	public static TipoAtendimento search(int idTipoAtendimento) {
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

}
