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
import java.util.List;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
public class StagiaireDAO {


    public static List<Stagiaire> findAll(Formation formation) {
       Connection connection = ConnectDB.getConnection();
        
        List<Stagiaire> stagiaires = new ArrayList<>();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from stagiaire INNER JOIN personne ON stagiaire.personne_id = personne.id WHERE formation_code= ?");
            stm.setString(1, formation.getCode());
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                
                String code = rs.getString("code");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                int id = rs.getInt("id");
                
                Stagiaire s = new Stagiaire(id, nom, prenom, code,formation);
                
                stagiaires.add(s);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stagiaires;
    }


    public static  Stagiaire findBy(int personId) {
        
        Stagiaire stag = null;
        Connection connection = ConnectDB.getConnection();
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement("select * from stagiaire inner join personne on stagiaire.personne_id = personne.id WHERE personne_id= ?");
            stm.setInt(1, personId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Formation f = FormationDAO.findBy(rs.getString("formation_code"));
        
                stag = new Stagiaire(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("code"), f);
            }
            
 

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stag;

    }


    public static void save(Stagiaire s) throws Exception {
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stmCreatePersonne;
        PreparedStatement stmCreateStagiaire;
        try {
            connection.setAutoCommit(false);

            stmCreatePersonne = connection.prepareStatement("INSERT INTO personne (nom, prenom) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmCreatePersonne.setString(1, s.getNom());
            stmCreatePersonne.setString(2, s.getPrenom());

            stmCreatePersonne.execute();

            // get autoincrement
            ResultSet rs = stmCreatePersonne.getGeneratedKeys();
            if (!rs.next()) {
                throw new Exception("humm cannot get generated personne id");
            }
            s.setId(rs.getInt(1));

            stmCreateStagiaire = connection.prepareStatement("INSERT INTO stagiaire (personne_id, code, formation_code) VALUES(?, ?, ?);");
            stmCreateStagiaire.setInt(1, s.getId());
            stmCreateStagiaire.setString(2, s.getCodeStagiaire());
            stmCreateStagiaire.setString(3, s.getFormation().getCode());
            stmCreateStagiaire.execute();

            connection.commit();
            stmCreatePersonne.close();
            stmCreateStagiaire.close();

        } catch (SQLException e) {
            
            //pb if here
            connection.rollback();
            
            
            throw new Exception("error while creating personne " + e.getMessage());
        }
    }


    public void update(Stagiaire s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void delete(Stagiaire s) {
    }

}
