/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.dao.CidadeEstadoDAO;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luck
 */
public class ClientesFacade implements Serializable{
    
    public static List<Estado> listarEstados() throws ClassNotFoundException, SQLException{
        // Vai no BD buscar todas as cidades deste estado, em uma lista
        CidadeEstadoDAO dao = new CidadeEstadoDAO();
        List<Estado> estados = dao.listarEstados();
        return estados;
    }
    
    public static List<Cliente> searchAll(){
        //Busca lista de clientes no banco de dados e retorna um List<Cliente>
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> lista = new ArrayList();
        try {
            lista = dao.listarClientes();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClientesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    public static Cliente search(int id){
        //Busca id do cliente a ser visualizado no parametro da página
        ClienteDAO dao = new ClienteDAO();
        Cliente c = dao.buscarCliente(id);
        return c;
    }
    
    public static void insert(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        dao.adicionarCliente(c);        
    }
    
    public static void update(Cliente c){
        ClienteDAO dao = new ClienteDAO();
        dao.alterarCliente(c);
    }
    
    public static void delete(int id){
        try {
            //Busca cliente no banco de dados e deleta do banco de dados
            ClienteDAO dao = new ClienteDAO();        
            dao.deletarCliente(id);
        } catch (SQLException ex) {
            Logger.getLogger(ClientesFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
