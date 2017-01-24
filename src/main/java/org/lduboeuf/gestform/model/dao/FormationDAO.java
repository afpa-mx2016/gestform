/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class FormationDAO{


    public static List<Formation> findAll() {
        
        Connection connection = ConnectDB.getConnection();
        
        List<Formation> formations = new ArrayList<>();
        Statement stm;
        try {
            stm = connection.createStatement();

            String sql = "select * from formation";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                Date dateDeb = rs.getTimestamp("date_debut");
                Date dateFin = rs.getTimestamp("date_fin");
                
                Formation f = new Formation(code, nom);
                f.setDateDebut(dateDeb);
                f.setDateFin(dateFin);
                
                formations.add(f);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return formations;
        
    }

    /**
     * 
     * @param formation code
     * @return null if not found
     */
    public static Formation findBy(String code) {
        Formation f = null;
        Connection connection = ConnectDB.getConnection();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from formation WHERE code= ?");
            stm.setString(1, code);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                f = new Formation(rs.getString("code"), rs.getString("nom"));
            }
            
 

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return f;
    }


    public void save(Formation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void update(Formation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void delete(Formation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
