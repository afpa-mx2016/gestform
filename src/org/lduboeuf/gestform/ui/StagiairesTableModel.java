/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.lduboeuf.gestform.model.Stagiaire;

/**
 *
 * @author lionel
 */
class StagiairesTableModel extends AbstractTableModel {

    private final String[] entetes = {"matricule", "Nom", "Prenom"};
    private List<Stagiaire> stagiaires;

    public StagiairesTableModel(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
    
    public StagiairesTableModel(){
        
    }
    
    public void setModel(List<Stagiaire> stagiaires) {
        this.stagiaires = stagiaires;
    }
    
    public void addStagiaire(Stagiaire stagiaire){
        this.stagiaires.add(stagiaire);
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return stagiaires.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    public Stagiaire getFormation(int rowIndex){
        return stagiaires.get(rowIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return stagiaires.get(rowIndex).getCodeStagiaire();

            case 1:
                return stagiaires.get(rowIndex).getNom();

            case 2:
                return stagiaires.get(rowIndex).getPrenom();

            default:
                throw new IllegalArgumentException();
        }

    }

}

    

