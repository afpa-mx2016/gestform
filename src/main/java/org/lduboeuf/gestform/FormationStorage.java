/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class FormationStorage {
    public static  String fileName = "formation.txt"; // nom par defaut
        
    public static void sauvegarde(Formation form) throws Exception{
        ObjectOutputStream oFW = null;
        try {
                oFW= new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
                throw new Exception("File not found" + e.getMessage());

        } catch (IOException e) {
                throw new Exception("Unable to write to file " +fileName);
        }


        oFW.writeObject(form);


        try {
                oFW.close();
        } catch (IOException e) {
                //do nothing
        }



    }
        
    /**
     * recupère la formation depuis le fichier
     * @return null si rien de trouvé
     */
    public static Formation restoration() throws Exception {

            Formation f = null;

            ObjectInputStream iFW =null;
            try {
                    iFW = new ObjectInputStream(new FileInputStream(fileName));
            } catch (FileNotFoundException e) {
                    //do nothing here
                    return null;
            } catch (IOException e) {
                    throw new Exception("Unable to Read from file " +fileName);
            } 



            try {
                    f = (Formation) iFW.readObject();
            } catch (ClassNotFoundException e) {
                    throw new Exception("Class Formation was not found in " +fileName);
            } catch (IOException e) {
                    throw new Exception("Unable to Read from file " +fileName);
            }finally{
                    if (iFW !=null)
                            try {
                                    iFW.close();
                            } catch (IOException e) {
                                    //do nothing
                                    e.printStackTrace();
                            }
            }

            return f;

    }
}
