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
import java.util.ArrayList;
import java.util.List;
import org.lduboeuf.gestform.model.ECF;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.ResultECF;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
public class ECFDAO {
    
    
    public static List<ResultECF> findAll(Stagiaire s){
        
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
    
    public static List<ECF> findAll(Formation f){
        
        Connection connection = ConnectDB.getConnection();
        
        List<ECF> ecfs = new ArrayList<>();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from ecf WHERE formation_code = ?");
            stm.setString(1, f.getCode());
            
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                
                ECF ecf = new ECF(rs.getInt("id"), rs.getString("nom"), f);
                
                
                ecfs.add(ecf);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return ecfs;
        
    }
    
    public static void save(ResultECF resECF){
        
        
    }
    
    public static void save(ECF ecf) throws Exception{
        
        if (ecf.getId()>0){ //update mode
            update(ecf);
            return;
        }
        
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("INSERT INTO ecf (nom, formation_code) VALUES ( ?, ?);");
            stm.setString(1, ecf.getName());
            stm.setString(2, ecf.getFormation().getCode());

            
            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while creating ecf " + e.getMessage());
        }
    }
    
    private static void update(ECF ecf) throws Exception{
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("UPDATE ecf SET nom = ? WHERE id = ?;");
            stm.setString(1, ecf.getName());
            stm.setInt(2, ecf.getId());
            

            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while updating ecf " + e.getMessage());
        }
    }
    
    public static void update(ResultECF resECF) throws Exception{
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("UPDATE result_ecf SET acquis = ? WHERE stagiaire_code = ? AND ecf_id = ?;");
            stm.setBoolean(1, resECF.isAcquis());
            stm.setString(2, resECF.getStagiaire().getCodeStagiaire());
            stm.setInt(3, resECF.getEcf().getId());
            

            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while creating personne " + e.getMessage());
        }
    }
    
    
    
    
}
