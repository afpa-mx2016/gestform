/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.lduboeuf.gestform.model.Formation;

/**
 *
 * @author lionel
 */
public class FormationTableModel extends AbstractTableModel {

    private final String[] entetes = {"Code", "Nom", "Date Début", "Date Fin"};
    private List<Formation> formations;

    public FormationTableModel(List<Formation> formations) {
        this.formations = formations;
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return formations.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    public Formation getFormation(int rowIndex){
        return formations.get(rowIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return formations.get(rowIndex).getCode();

            case 1:
                return formations.get(rowIndex).getNom();

            case 2:
                return formations.get(rowIndex).getDateDebut();

            case 3:
                return formations.get(rowIndex).getDateFin();


            default:
                throw new IllegalArgumentException();
        }

    }

}
