/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.model;


import java.io.Serializable;

/**
 *
 * @author lionel
 */
public class Stagiaire extends Personne implements Serializable{
    
    int codeStagiaire;
    Formation formation;

    public Stagiaire(String nom, String prenom, int codeStagiaire) {
        super(nom, prenom);
        this.codeStagiaire = codeStagiaire;
    }

    public int getCodeStagiaire() {
        return codeStagiaire;
    }

    public void setCodeStagiaire(int codeStagiaire) {
        this.codeStagiaire = codeStagiaire;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.codeStagiaire;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stagiaire other = (Stagiaire) obj;
        if (this.codeStagiaire != other.codeStagiaire) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
