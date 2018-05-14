package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Produto;

public class ProdutoDAO {

	private final String SELECT_ALL = "SELECT * FROM tb_produto;";
	private final String SELECT = "SELECT * FROM tb_produto WHERE id_produto = ?;";
	Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<Produto> listarProdutos() throws InstantiationException, IllegalAccessException, SQLException{
	    List<Produto> lista = new ArrayList<Produto>();
    	try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
            	Produto pd = new Produto();
                pd.setIdProduto((rs.getInt(1)));
                pd.setNomeProduto((rs.getString(2)));
                lista.add(pd);
            }
            rs.close();
            return lista;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
    }

	public Produto buscarProduto(int idProduto) {
		
		return null;
	}
    
}
