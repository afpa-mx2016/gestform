/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class FormationDAO implements DAO<Formation>{

    @Override
    public List<Formation> findAll() {
        
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
                
                Formation f = new Formation(code, nom);
                
                formations.add(f);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return formations;
        
    }

    
}
