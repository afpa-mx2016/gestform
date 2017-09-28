/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestform;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.Personne;
import org.lduboeuf.gestform.model.Stagiaire;
import org.lduboeuf.gestform.model.dao.FormationDAO;

/**
 *
 * @author lionel
 */
public class GestForm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Personne p1 = new Personne(1,"Trubadu", "Jean Claude");
        Personne p3 = new Personne(2,"Gertrude", "Albert");
        
        if (p1==p3){
            System.out.println("kikou");
        }
        
        if (p1.equals(p3)){
            System.out.println("kikou equals");
        }
        
        
        Personne p2 = new Personne();
        p2.setNom("Tardito");
        p2.setPrenom("Balui");
        
        Formation form = new Formation("DL16","Développeur Logiciel");
        Stagiaire p4 = new Stagiaire(8,"Popole", "Marcel", "CA999", form);
      
        form.getStagiaires().add(p4);
        
        
        
        List<Personne> persons = new ArrayList<>();
        persons.add(p1);
        persons.add(p2);
        persons.add(p2);
        persons.add(p4);
      
        
        for (Personne p: persons){
            System.out.println(p);
        }
        
        
        
        Formation f = new Formation("RX111", "dev++");
        
        Calendar caldeb = Calendar.getInstance();
        caldeb.set(2017, 3, 12);
        
        Calendar calfin = Calendar.getInstance();
        calfin.set(2017, 11, 12);
        
                
                //.set(2017, 3, 12);
        f.setDateDebut(caldeb.getTime());
        f.setDateFin(calfin.getTime());
        
        
        try {
            FormationDAO.save(f);
        } catch (Exception ex) {
            Logger.getLogger(GestForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
}
