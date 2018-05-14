package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.dao.AtendimentoDAO;

import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;

public class AtendimentoFacade {

	public List<Atendimento> searchAll(){
		AtendimentoDAO dao = new AtendimentoDAO();
		return dao.listarAtendimentos();
	}
}
