/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestform;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lduboeuf.gestform.dao.ECFDAO;
import org.lduboeuf.gestform.dao.FormationDAO;
import org.lduboeuf.gestform.dao.StagiaireDAO;
import org.lduboeuf.gestform.model.ECF;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.ResultECF;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
public class TestMain {
    
    public static void main(String[] args) {
        

       
        
        FormationDAO fDAO = new FormationDAO();
        List<Formation> formations = fDAO.findAll();
        for (Formation f: formations){
            System.out.println(f.getNom());
        }
        
        Formation formation = fDAO.findBy("DL16");
        
        Stagiaire s = new Stagiaire("duclou", "alfred", "001C0997",formation);
        StagiaireDAO sDAO = new StagiaireDAO();
        try {
            sDAO.save(s);
            Logger.getLogger(TestMain.class.getName()).log(Level.INFO, "generated id:" + s.getId());
        } catch (Exception ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Stagiaire> stagiaires = sDAO.findAll(formation);
        for (Stagiaire stag: stagiaires){
            System.out.println(stag);
        }        
                
                
       // ECF ecf = new ECF(1,"Developpement Web", formation); 
        
        ECFDAO ecfDAO = new ECFDAO();
        List<ResultECF> ecfs = ecfDAO.findAll(s);
        for (ResultECF res: ecfs){
            System.out.println(res);
        }
    }
    
}
