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
	private final String INSERT = "INSERT INTO tb_produto(nome_produto) VALUES (?);";
	private final String UPDATE = "UPDATE tb_produto SET nome_produto = ? WHERE id_produto = ?;";
	private final String DELETE = "DELETE FROM tb_produto WHERE id_produto = ?;";
	
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

	public Produto buscarProduto(int idProduto) throws InstantiationException, IllegalAccessException, SQLException {
		Produto pd = new Produto();
		try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(SELECT);
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            while(rs.next()){
                pd.setIdProduto((rs.getInt(1)));
                pd.setNomeProduto((rs.getString(2)));
            }
            rs.close();
            return pd;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
	}

	public void adicionarProduto(String p) throws InstantiationException, IllegalAccessException, SQLException {
		try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(INSERT);
            stmt.setString(1, p);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
	}

	public void alterarProduto(Produto p) throws InstantiationException, IllegalAccessException, SQLException {
		try {
	        con = new ConnectionFactory().getConnection();
	        stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, p.getNomeProduto());
            stmt.setInt(2, p.getIdProduto());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }
	}

	public void deletarProduto(int id) throws InstantiationException, IllegalAccessException, SQLException {
        try {
        	con = new ConnectionFactory().getConnection();
        	stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            stmt.close();
        }
	}
    
}
