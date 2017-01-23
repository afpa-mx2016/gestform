/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.lduboeuf.gestform.model.ResultECF;
import org.lduboeuf.gestform.model.dao.ECFDAO;

/**
 *
 * @author lionel
 */
public class ECFResultTableModel extends AbstractTableModel{

    private final String[] entetes = {"Nom", "Date", "Résultat"};
    private List<ResultECF> ecfs;

    public ECFResultTableModel(List<ResultECF> ecfs) {
        this.ecfs = ecfs;
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return ecfs.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }
    
    //
      // This method is used by the JTable to define the default
      // renderer or editor for each cell. For example if you have
      // a boolean data it will be rendered as a check box. A
      // number value is right aligned.
      //
      @Override
      public Class<?> getColumnClass(int columnIndex) {
          return getValueAt(0, columnIndex).getClass();
      }
    

    public ResultECF getECF(int rowIndex){
        return ecfs.get(rowIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return ecfs.get(rowIndex).getEcf().getName();

            case 1:
                return new Date(); //TODO

            case 2:
                return ecfs.get(rowIndex).isAcquis();

            default:
                throw new IllegalArgumentException();
        }

    }

    //methode appellée chaque fois qu'une modification a lieu dans le tableau
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ResultECF ecf = ecfs.get(rowIndex);
        if (columnIndex==2){
            ecf.setAcquis(((Boolean)aValue).booleanValue());
            try {
                //update database
                ECFDAO.update(ecf);
                fireTableCellUpdated(rowIndex, columnIndex);// notify listeners
                
            } catch (Exception ex) {
                Logger.getLogger(ECFResultTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
        @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return (columnIndex==2);
    }
    
}
