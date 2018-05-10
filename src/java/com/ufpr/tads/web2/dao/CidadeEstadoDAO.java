/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Estado;
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
public class CidadeEstadoDAO {
    
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public List<Estado> listarEstados() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List<Estado> estados = new ArrayList<Estado>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement("SELECT * FROM tb_estado;");
            rs = stmt.executeQuery();
            while(rs.next()){
                Estado aux =  new Estado(rs.getInt(1),rs.getString(2),rs.getString(3));
                estados.add(aux);
            }
            rs.close();
            return estados;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }                 
    }
    
    public List<Cidade> listarCidades(int idEstado) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
        
        List<Cidade> cidades = new ArrayList<Cidade>();

        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.prepareStatement("SELECT * FROM tb_cidade WHERE id_estado = "+idEstado+";");
            rs = stmt.executeQuery();
            while(rs.next()){
                Cidade aux =  new Cidade(rs.getInt(1),rs.getInt(2),rs.getString(3));
                cidades.add(aux);
            }
            rs.close();
            return cidades;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            con.close();
        }                 
    }
    
}
