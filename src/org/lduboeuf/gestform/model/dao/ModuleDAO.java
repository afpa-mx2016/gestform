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
import org.lduboeuf.gestform.model.Module;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.ResultECF;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
public class ModuleDAO {
    
    
    public static List<ResultECF> findAll(Stagiaire s){
        
        Connection connection = ConnectDB.getConnection();
        
        List<ResultECF> res_ecf = new ArrayList<>();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from result_ecf INNER JOIN module ON result_ecf.module_id = module.id WHERE stagiaire_code = ?");
            stm.setString(1, s.getCodeStagiaire());
            
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                
                Module ecf = new Module(rs.getInt("id"), rs.getString("nom"), s.getFormation());
                ResultECF ecfRes = new ResultECF(ecf, s, rs.getBoolean("acquis"));
                
                res_ecf.add(ecfRes);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res_ecf;
        
    }
    
    public static List<Module> findAll(Formation f){
        
        Connection connection = ConnectDB.getConnection();
        
        List<Module> modules = new ArrayList<>();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from module WHERE formation_code = ?");
            stm.setString(1, f.getCode());
            
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                
                Module module = new Module(rs.getInt("id"), rs.getString("nom"), f);
                
                
                modules.add(module);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return modules;
        
    }
    
    public static void save(ResultECF resECF){
        
        
    }
    
    public static void save(Module module) throws Exception{
        
        if (module.getId()>0){ //update mode
            update(module);
            return;
        }
        
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("INSERT INTO module (nom, formation_code) VALUES ( ?, ?);");
            stm.setString(1, module.getName());
            stm.setString(2, module.getFormation().getCode());

            
            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while creating module " + e.getMessage());
        }
    }
    
    private static void update(Module module) throws Exception{
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("UPDATE module SET nom = ? WHERE id = ?;");
            stm.setString(1, module.getName());
            stm.setInt(2, module.getId());
            

            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while updating module " + e.getMessage());
        }
    }
    
    public static void update(ResultECF resECF) throws Exception{
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stm;
        try {

            stm = connection.prepareStatement("UPDATE result_ecf SET acquis = ? WHERE stagiaire_code = ? AND module_id = ?;");
            stm.setBoolean(1, resECF.isAcquis());
            stm.setString(2, resECF.getStagiaire().getCodeStagiaire());
            stm.setInt(3, resECF.getModule().getId());
            

            stm.execute();

            stm.close();

        } catch (SQLException e) {
            
            
            throw new Exception("error while creating personne " + e.getMessage());
        }
    }
    
    
    
    
}
