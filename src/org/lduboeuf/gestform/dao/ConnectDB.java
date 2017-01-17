/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lionel
 */
public class ConnectDB {

    private static Connection conn = null;
    //TODO dbname user and pwd should come from a property file
    final static String URL = "jdbc:mysql://localhost/gestform?noAccessToProcedureBodies=true";

    /**
     *
     * @return RunTimeException() if any pb
     */
    public static Connection getConnection() {
        if (conn == null) {

            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    
                    
                } catch (InstantiationException ex) {
                    Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //
                conn = DriverManager.getConnection(URL, "gestform_user", "gestform");
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }

        return conn;

    }

}
