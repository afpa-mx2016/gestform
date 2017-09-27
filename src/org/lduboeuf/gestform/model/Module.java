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
public class Module {
    
    int id;
    String name;
    Formation formation;

    public Module(int id, String name, Formation formation) {
        this.id = id;
        this.name = name;
        this.formation = formation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    @Override
    public String toString() {
        return "ECF{" + "id=" + id + ", name=" + name + ", formation=" + formation + '}';
    }
    
    
    
}
