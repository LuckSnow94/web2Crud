/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.utils.CpfUtil;
import com.ufpr.tads.web2.utils.DataUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luck
 */
public class ClienteDAO {
    
	private final String SELECT_ALL = "SELECT * FROM tb_cliente;";
	private final String SELECT = "SELECT * FROM tb_cliente WHERE id_cliente = ?;";
	
	private final String INSERT = "INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, "
                    + "data_cliente, rua_cliente, nr_cliente, cep_cliente, id_cidade) values (?,?,?,?,?,?,?,?);";
    private final String UPDATE = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, "
                    + "data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, id_cidade = ? WHERE id_cliente = ?;";
    private final String DELETE = "DELETE FROM tb_cliente WHERE id_cliente=?;";
    
    private final String VERIFY_CPF = "SELECT cpf_cliente FROM tb_cliente WHERE cpf_cliente = ?;";
    private final String VERIFY_EMAIL = "SELECT email_cliente FROM tb_cliente WHERE email_cliente = ?;";
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        
    public ClienteDAO() {
    }
    
    public List<Cliente> listarClientes() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List<Cliente> lista = new ArrayList<Cliente>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(SELECT_ALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente aux =  new Cliente(rs.getInt(1),CpfUtil.formataCpfTela(rs.getString(2)),rs.getString(3),rs.getString(4));
                lista.add(aux);
            }
            rs.close();
            return lista;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }                 
    }
    
    public Cliente buscarCliente(int id) {
    	Cliente aux = new Cliente();
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(SELECT);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    aux.setIdCliente(rs.getInt(1));
                    aux.setCpfCliente(rs.getString(2));
                    aux.setNomeCliente(rs.getString(3));
                    aux.setEmailCliente(rs.getString(4));
                    aux.setDataCliente(DataUtil.formataDataSqlParaBean(rs.getDate(5)));                    
                    aux.setRuaCliente(rs.getString(6));
                    aux.setNrCliente(rs.getInt(7));
                    aux.setCepCliente(rs.getString(8));
                    aux.setCidadeCliente(rs.getInt(9));
                    }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {con.close();} catch (SQLException e) {}
            }
            return aux;
    }
    
    public void adicionarCliente(Cliente cliente) throws InstantiationException, IllegalAccessException {
            try {
                    con = new ConnectionFactory().getConnection();
                    stmt = con.prepareStatement(INSERT);
                    stmt.setString(1, cliente.getCpfCliente());
                    stmt.setString(2, cliente.getNomeCliente());
                    stmt.setString(3, cliente.getEmailCliente());
                    stmt.setDate(4, DataUtil.formataDataBeanParaSql(cliente.getDateCliente()));
                    stmt.setString(5, cliente.getRuaCliente());
                    stmt.setInt(6, cliente.getNrCliente());
                    stmt.setString(7, cliente.getCepCliente());
                    stmt.setInt(8, cliente.getCidadeCliente());
                    stmt.execute();
                    stmt.close();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
            } finally {
                    try{con.close();}catch(Exception e){}
            }
    }
    
    public void alterarCliente(Cliente cliente) {
            try {
                con = new ConnectionFactory().getConnection();
                stmt = con.prepareStatement(UPDATE);
                stmt.setString(1, cliente.getCpfCliente());
                stmt.setString(2, cliente.getNomeCliente());
                stmt.setString(3, cliente.getEmailCliente());
                stmt.setDate(4, DataUtil.formataDataBeanParaSql(cliente.getDateCliente()));
                stmt.setString(5, cliente.getRuaCliente());
                stmt.setInt(6, cliente.getNrCliente());
                stmt.setString(7, cliente.getCepCliente());
                stmt.setInt(8, cliente.getCidadeCliente());
                stmt.setInt(9, cliente.getIdCliente());
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception e) {
                    throw new RuntimeException(e);
            }finally{
                    try {con.close();} catch (SQLException e) {}
            }
    }

    public void deletarCliente(int idCliente) throws SQLException, InstantiationException, IllegalAccessException {
    	con = new ConnectionFactory().getConnection();
    	stmt = con.prepareStatement(DELETE);
        try {
            stmt.setInt(1, idCliente);
            stmt.executeUpdate();
        } finally{
            stmt.close();
        }
    }
    
    public boolean verificaCpf(String cpf) {
    	try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(VERIFY_CPF);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            if (rs.next())
            	return true;
            else
            	return false;
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}finally{
    		try {con.close();} catch (SQLException e) {}
    	}
    }

    public boolean verificaEmail(String email) {
    	try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement(VERIFY_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next())
            	return true;
            else
            	return false;
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}finally{
    		try {con.close();} catch (SQLException e) {}
    	}
    }

}
