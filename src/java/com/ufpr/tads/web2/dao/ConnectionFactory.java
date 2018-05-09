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
    private final String dbDriver = "com.mysql.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://localhost/web2";
    private final String dbUser = "root";
    private final String dbPwd = "luck";
            
    public Connection getConnection() {
        try {
            Class.forName(dbDriver);
            DriverManager.registerDriver(new dbDriver());
            return DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
