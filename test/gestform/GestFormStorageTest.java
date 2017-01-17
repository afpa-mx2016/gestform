/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestform;


import org.lduboeuf.gestform.model.Stagiaire;
import java.sql.Connection;
import org.junit.Assert;
import org.junit.Test;
import org.lduboeuf.gestform.FormationStorage;
import org.lduboeuf.gestform.dao.ConnectDB;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class GestFormStorageTest {
    
    public GestFormStorageTest() {
    }



    @Test
    public void serializeTest() throws Exception{
        Formation f = new Formation("DL16","Developpeur Logiciel");
        Stagiaire s = new Stagiaire(1, "kikou", "zonote", "CA01",f);
        s.setFormation(f); //test cyclic dependancy
        f.addStagiaire(s);
        
        FormationStorage.sauvegarde(f);
        
        
        Formation fres = FormationStorage.restoration();
        Assert.assertEquals(f.getNom(), fres.getNom());
        Assert.assertEquals(f.getStagiaires().get(0), fres.getStagiaires().get(0));
        
    }
    
    @Test
    public void connectTest(){
        Connection connection = ConnectDB.getConnection();
        Assert.assertNotNull(connection);
    }
    
    
}
