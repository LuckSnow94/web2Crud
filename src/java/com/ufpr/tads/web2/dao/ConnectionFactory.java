/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luck
 */
public class ConnectionFactory {
	//Mysql versão 8.0
    private final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://localhost/web2?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private final String dbUser = "root";
    private final String dbPwd = "luck";
            
    public Connection getConnection() throws InstantiationException, IllegalAccessException {
        try {
            DriverManager.deregisterDriver((Driver) Class.forName(dbDriver).newInstance());
            return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException ex) {
        	System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }    
}
