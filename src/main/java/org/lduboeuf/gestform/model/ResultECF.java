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
    
    ECF ecf;
    Stagiaire stagiaire;
    boolean acquis;

    public ResultECF(ECF ecf, Stagiaire stagiaire, boolean acquis) {
        this.ecf = ecf;
        this.stagiaire = stagiaire;
        this.acquis = acquis;
    }
    
    
    

    public ECF getEcf() {
        return ecf;
    }

    public void setEcf(ECF ecf) {
        this.ecf = ecf;
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
        return "ResultECF{" + "ecf=" + ecf + ", stagiaire=" + stagiaire + ", acquis=" + acquis + '}';
    }
    
    
    
    
    
    
}
