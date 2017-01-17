/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.lduboeuf.gestform.model.ECF;
import org.lduboeuf.gestform.model.ResultECF;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
public class ECFDAO {
    
    
    public List<ResultECF> findAll(Stagiaire s){
        
        Connection connection = ConnectDB.getConnection();
        
        List<ResultECF> res_ecf = new ArrayList<>();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from result_ecf INNER JOIN ecf ON result_ecf.ecf_id = ecf.id WHERE stagiaire_code = ?");
            stm.setString(1, s.getCodeStagiaire());
            
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                
                ECF ecf = new ECF(rs.getInt("id"), rs.getString("nom"), s.getFormation());
                ResultECF ecfRes = new ResultECF(ecf, s, rs.getBoolean("acquis"));
                
                res_ecf.add(ecfRes);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return res_ecf;
        
    }
    
    
    
    
}
