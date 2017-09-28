/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import org.lduboeuf.gestform.model.Module;
import org.lduboeuf.gestform.model.dao.ModuleDAO;

/**
 *
 * @author lionel
 */
public class ModuleTableModel extends AbstractTableModel {

    private final String[] entetes = {"Nom"};
    private List<Module> modules;

    public ModuleTableModel(List<Module> modules) {
        this.modules = modules;
    }

    public void setModel(List<Module> modules) {
        this.modules = modules;
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return entetes[column];
    }

    @Override
    public int getRowCount() {
        return modules.size();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void addRow(Module module) {
        modules.add(module);
        fireTableRowsInserted(modules.size() - 1, modules.size() - 1);
    }

    //methode appellée chaque fois qu'une modification a lieu dans le tableau
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Module module = modules.get(rowIndex);
        if (columnIndex == 0) {
            String nom = (String) aValue;
            if (nom.length() > 0) {
                module.setName(nom);
                try {
                    ModuleDAO.save(module);
                    fireTableCellUpdated(rowIndex, columnIndex);// notify listeners

                } catch (Exception ex) {
                    Logger.getLogger(ModuleTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

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

    public Module getECF(int rowIndex) {
        return modules.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return modules.get(rowIndex).getName();

            default:
                throw new IllegalArgumentException();
        }

    }

}
