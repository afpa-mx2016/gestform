/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.model;



/**
 *
 * @author lionel
 */
public class ResultECF {
    
    Module module;
    Stagiaire stagiaire;
    boolean acquis;

    public ResultECF(Module module, Stagiaire stagiaire, boolean acquis) {
        this.module = module;
        this.stagiaire = stagiaire;
        this.acquis = acquis;
    }
    
    
    

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Stagiaire getStagiaire() {
        return stagiaire;
    }

    public void setStagiaire(Stagiaire stagiaire) {
        this.stagiaire = stagiaire;
    }

    

    public boolean isAcquis() {
        return acquis;
    }

    public void setAcquis(boolean acquis) {
        this.acquis = acquis;
    }

    @Override
    public String toString() {
        return "ResultECF{" + "ecf=" + module + ", stagiaire=" + stagiaire + ", acquis=" + acquis + '}';
    }
    
    
    
    
    
    
}
