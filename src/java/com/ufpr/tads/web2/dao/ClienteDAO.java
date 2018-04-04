/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cliente;
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
    private final String insert = "INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, "
                    + "data_cliente, rua_cliente, nr_cliente, cep_cliente, cidade_cliente, uf_cliente) values (?,?,?,?,?,?,?,?,?);";
    private final String update = "UPDATE tb_cliente SET cpf_cliente = ?, nome_cliente = ?, email_cliente = ?, "
                    + "data_cliente = ?, rua_cliente = ?, nr_cliente = ?, cep_cliente = ?, cidade_cliente = ?, uf_cliente = ? WHERE id_cliente = ?;";
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
        
    public ClienteDAO() {
    }
    
    public List<Cliente> listarClientes() throws ClassNotFoundException, SQLException{
        
        List<Cliente> lista;
        lista = new ArrayList();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement("SELECT * FROM tb_cliente;");
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente aux =  new Cliente();
                aux.setIdCliente(rs.getInt(1));
                aux.setCpfCliente(rs.getString(2));
                aux.setNomeCliente(rs.getString(3));
                aux.setEmailCliente(rs.getString(4));
                
                //converter sql date para java date 
//                java.sql.Date sqlDate = rs.getDate(5);
//                java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
//                
//                aux.setDataCliente(utilDate);
//                aux.setRuaCliente(rs.getString(6));
//                aux.setNrCliente(rs.getInt(7));
//                aux.setCepCliente(rs.getString(8));
//                aux.setCidadeCliente(rs.getString(9));
//                aux.setUfCliente(rs.getString(10));
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
                stmt = con.prepareStatement("SELECT * FROM tb_cliente WHERE id_cliente = ?;");
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    aux.setIdCliente(rs.getInt(1));
                    aux.setCpfCliente(rs.getString(2));
                    aux.setNomeCliente(rs.getString(3));
                    aux.setEmailCliente(rs.getString(4));
                    
                    //converter sql date para java date 
                    java.sql.Date sqlDate = rs.getDate(5);
                    java.util.Date utilDate = new java.util.Date(sqlDate.getTime());

                    aux.setDataCliente(utilDate);                    
                    aux.setRuaCliente(rs.getString(6));
                    aux.setNrCliente(rs.getInt(7));
                    aux.setCepCliente(rs.getString(8));
                    aux.setCidadeCliente(rs.getString(9));
                    aux.setUfCliente(rs.getString(10));
                    }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {con.close();} catch (SQLException e) {}
            }
            return aux;
    }
    
    public void adicionarCliente(Cliente cliente) {
            try {
                    con = new ConnectionFactory().getConnection();
                    stmt = con.prepareStatement(insert);
                    stmt.setString(1, cliente.getCpfCliente());
                    stmt.setString(2, cliente.getNomeCliente());
                    stmt.setString(3, cliente.getEmailCliente());
                    
                    //converter java date para sql date
                    java.sql.Date sqlDate = new java.sql.Date(cliente.getDataCliente().getTime());
                    
                    stmt.setDate(4, sqlDate);
                    stmt.setString(5, cliente.getRuaCliente());
                    stmt.setInt(6, cliente.getNrCliente());
                    stmt.setString(7, cliente.getCepCliente());
                    stmt.setString(8, cliente.getCidadeCliente());
                    stmt.setString(9, cliente.getUfCliente());
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
                stmt = con.prepareStatement(update);
                stmt.setString(1, cliente.getCpfCliente());
                stmt.setString(2, cliente.getNomeCliente());
                stmt.setString(3, cliente.getEmailCliente());
                    
                //converter java date para sql date
                java.sql.Date sqlDate = new java.sql.Date(cliente.getDataCliente().getTime());

                stmt.setDate(4, sqlDate);
                stmt.setString(5, cliente.getRuaCliente());
                stmt.setInt(6, cliente.getNrCliente());
                stmt.setString(7, cliente.getCepCliente());
                stmt.setString(8, cliente.getCidadeCliente());
                stmt.setString(9, cliente.getUfCliente());
                stmt.setInt(10, cliente.getIdCliente());
                stmt.executeUpdate();
                stmt.close();
            } catch (Exception e) {
                    throw new RuntimeException(e);
            }finally{
                    try {con.close();} catch (SQLException e) {}
            }
    }

    public void deletarCliente(Cliente cliente) throws SQLException {
    	con = new ConnectionFactory().getConnection();
    	stmt = con.prepareStatement("DELETE FROM tb_cliente WHERE id_cliente=?;");
        try {
            stmt.setInt(1, cliente.getIdCliente());
            stmt.executeUpdate();
        } finally{
            stmt.close();
        }
    }
    
}