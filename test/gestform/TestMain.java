/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestform;

import gestform.model.ECF;
import java.util.List;
import org.lduboeuf.gestform.dao.FormationDAO;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class TestMain {
    
    public static void main(String[] args) {
        
        ECF ecf = new ECF("ECF1","Developpement Web");
       
        
        FormationDAO fDAO = new FormationDAO();
        List<Formation> formations = fDAO.findAll();
        for (Formation f: formations){
            System.out.println(f.getNom());
        }
    }
    
}
