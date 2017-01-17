/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.model;


import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author lionel
 */
public class Stagiaire extends Personne implements Serializable{
    
    String codeStagiaire;
    Formation formation;
    
    public Stagiaire(String nom, String prenom, String codeStagiaire, Formation f) {
        super(nom, prenom);
        this.codeStagiaire = codeStagiaire;
        this.formation = f;
    }
    

    public Stagiaire(int id, String nom, String prenom, String codeStagiaire, Formation f) {
        super(id, nom, prenom);
        this.codeStagiaire = codeStagiaire;
        this.formation = f;
    }

    public String getCodeStagiaire() {
        return codeStagiaire;
    }

    public void setCodeStagiaire(String codeStagiaire) {
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
        hash = 97 * hash + Objects.hashCode(this.codeStagiaire);
        hash = 97 * hash + Objects.hashCode(this.formation);
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
        if (!Objects.equals(this.codeStagiaire, other.codeStagiaire)) {
            return false;
        }
        if (!Objects.equals(this.formation, other.formation)) {
            return false;
        }
        return true;
    }
    
    




    @Override
    public String toString() {
        return super.toString() + " Stagiaire{" + "codeStagiaire=" + codeStagiaire + ", formation=" + formation + '}';
    }
    
    
    
    
    
    
}
