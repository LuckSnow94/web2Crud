package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.utils.DataUtil;

public class AtendimentoDAO {

	private final String SELECT_ALL = "SELECT * FROM tb_produto;";
	Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Atendimento> listarAtendimentos() throws InstantiationException, IllegalAccessException, SQLException{
	    List<Atendimento> lista = new ArrayList<Atendimento>();
    	try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
            	Atendimento at = new Atendimento();
                at.setIdAtendimento((rs.getInt(1)));
                at.setDataHoraAtendimento(DataUtil.formataDataHoraSqlParaBean(rs.getTimestamp(2)));
                
                at.setProduto();
                at.setCliente();
                lista.add(at);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
    }

}
