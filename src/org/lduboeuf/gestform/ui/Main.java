/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import org.lduboeuf.gestform.model.Module;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.Stagiaire;
import org.lduboeuf.gestform.model.dao.ConnectDB;
import org.lduboeuf.gestform.model.dao.ModuleDAO;
import org.lduboeuf.gestform.model.dao.FormationDAO;
import org.lduboeuf.gestform.model.dao.StagiaireDAO;

/**
 *
 * @author lionel
 */
public class Main extends javax.swing.JFrame implements StagiaireForm.StagiaireFormEventListener{

    FormationTableModel tblFormationModel;
    StagiairesTableModel listStagiairesModel;
    ECFTableModel tblECFModel;
    
    Formation selectedFormation;
    /**
     * Creates new form Main
     */
    public Main() {
        
        tblFormationModel = new FormationTableModel(FormationDAO.findAll());
        listStagiairesModel = new StagiairesTableModel(new ArrayList<>()); //pas de stagiaires par défaut
        tblECFModel = new ECFTableModel(new ArrayList<>()); //pas d'ecf par défaut

        
        initComponents();
        
        //ne pas afficher le détails de la formation si rien de selectionné dans la liste
        panelFormationDetails.setVisible(false);
        // par défaut le bouton modifier stagiaire ne doit pas être cliquable
        btnMajStagiaire.setEnabled(false);
        btnAfficheECF.setEnabled(false);
        
        
        tblFormations.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                
                if (tblFormations.getSelectedRow()>-1){
                    selectedFormation = tblFormationModel.getFormation(tblFormations.getSelectedRow());
                    if (selectedFormation!=null){
                        displayFormationDetails(selectedFormation);
                    }
                }
                
               
            }
        });
        
        //on ecoute les évenments sur le clique d'une ligne du tableau, exemple utilisation des Lambdas ( java >= 1.8)
        tblListeStagiaires.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            btnMajStagiaire.setEnabled(true);
            btnAfficheECF.setEnabled(true);
        });
        
    }
    
    /**
     * affiche le détail d'une formation
     * @param formation 
     */
    private void displayFormationDetails(Formation formation){
        
         DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
         
         
        txtCode.setText(formation.getCode());
        txtNom.setText(formation.getNom());
        txtDateDeb.setText(df.format(formation.getDateDebut()));
        txtDateFin.setText(df.format(formation.getDateFin()));

        listStagiairesModel.setModel(StagiaireDAO.findAll(formation));

        
        tblECFModel.setModel(ModuleDAO.findAll(formation));
        tblECFModel.addRow(new Module(-1, "", formation)); //ajout d'un faux ECF pour permettre l'ajout depuis la liste

        btnAjoutStagiaire.setEnabled(true);
        panelFormationDetails.setVisible(true);
        panelFormationDetails.setEnabled(true);
    }
    
    private void saveFormation(){
        
        //TODO controle des champs
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        
        Formation f = new Formation(txtCode.getText(), txtNom.getText());
        
        try {
            Date datedeb = df.parse(txtDateDeb.getText());
            Date datefin = df.parse(txtDateFin.getText());
            
            
            f.setDateDebut(datedeb);
            f.setDateFin(datefin);
            
            FormationDAO.save(f);
            
            //update model
            tblFormationModel.addFormation(f);
            
            
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            
            
            JOptionPane.showMessageDialog(null, "humm pb lors de la creation, doublons, pb de champs, ? pas eu encore le temps de gérer le controle des formulaires, ni l'update... ", "selection stagiaire", JOptionPane.INFORMATION_MESSAGE); 

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
     //TODO
    }
    
    private void clearFormationDetails(){
        txtCode.setText(null);
        txtNom.setText(null);
        txtDateDeb.setText(null);
        txtDateFin.setText(null);
        
        tblFormations.clearSelection();

        
        listStagiairesModel.setModel(new ArrayList<>()); //pas de stagiaires
        btnAjoutStagiaire.setEnabled(false);
        tblECFModel.setModel(new ArrayList<>()); //pas d'ecf par défaut

        
        panelFormationDetails.setVisible(true);
    }
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFooter = new javax.swing.JPanel();
        panelFormationList = new javax.swing.JPanel();
        btnAjoutFormation = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFormations = new javax.swing.JTable();
        panelFormationDetails = new javax.swing.JPanel();
        panelFormDetailsFields = new javax.swing.JPanel();
        lblCode = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        lblNom = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        lblDateDeb = new javax.swing.JLabel();
        txtDateDeb = new javax.swing.JTextField();
        lblDateFin = new javax.swing.JLabel();
        txtDateFin = new javax.swing.JTextField();
        panelFormDetailsActions = new javax.swing.JPanel();
        btnActionFormDetail = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        panelStagiairesList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListeStagiaires = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnAjoutStagiaire = new javax.swing.JButton();
        btnMajStagiaire = new javax.swing.JButton();
        btnAfficheECF = new javax.swing.JButton();
        panelHeader = new javax.swing.JPanel();
        menu = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuFileFermer = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MenuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GestForm");
        setPreferredSize(new java.awt.Dimension(1024, 800));

        panelFooter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        getContentPane().add(panelFooter, java.awt.BorderLayout.SOUTH);

        panelFormationList.setBorder(javax.swing.BorderFactory.createTitledBorder("Formations"));
        panelFormationList.setName(""); // NOI18N
        panelFormationList.setLayout(new javax.swing.BoxLayout(panelFormationList, javax.swing.BoxLayout.Y_AXIS));

        btnAjoutFormation.setText("Créer une formation");
        btnAjoutFormation.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnAjoutFormation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutFormationActionPerformed(evt);
            }
        });
        panelFormationList.add(btnAjoutFormation);

        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(null);

        tblFormations.setModel(tblFormationModel);
        tblFormations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblFormations);

        panelFormationList.add(jScrollPane1);

        getContentPane().add(panelFormationList, java.awt.BorderLayout.WEST);

        panelFormationDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Fiche Formation "));
        panelFormationDetails.setLayout(new javax.swing.BoxLayout(panelFormationDetails, javax.swing.BoxLayout.Y_AXIS));

        panelFormDetailsFields.setLayout(new java.awt.GridLayout(5, 2));

        lblCode.setText("code:");
        panelFormDetailsFields.add(lblCode);

        txtCode.setToolTipText("");
        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });
        panelFormDetailsFields.add(txtCode);

        lblNom.setText("nom:");
        panelFormDetailsFields.add(lblNom);

        txtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomActionPerformed(evt);
            }
        });
        panelFormDetailsFields.add(txtNom);

        lblDateDeb.setText("date début: [jj/mm/aaaa]");
        panelFormDetailsFields.add(lblDateDeb);
        panelFormDetailsFields.add(txtDateDeb);

        lblDateFin.setText("date fin:[jj/mm/aaaa]");
        panelFormDetailsFields.add(lblDateFin);

        txtDateFin.setToolTipText("");
        panelFormDetailsFields.add(txtDateFin);

        panelFormationDetails.add(panelFormDetailsFields);

        panelFormDetailsActions.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnActionFormDetail.setText("Mettre à jour");
        btnActionFormDetail.setToolTipText("");
        btnActionFormDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionFormDetailActionPerformed(evt);
            }
        });
        panelFormDetailsActions.add(btnActionFormDetail);

        panelFormationDetails.add(panelFormDetailsActions);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Modules"));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jScrollPane3.setBorder(null);

        jTable1.setModel(tblECFModel);
        jTable1.setAlignmentX(0.0F);
        jTable1.setAlignmentY(0.0F);
        jTable1.setAutoscrolls(false);
        jScrollPane3.setViewportView(jTable1);

        jPanel2.add(jScrollPane3);

        jButton1.setText("Rapport résultats ECF");
        jButton1.setToolTipText("");
        jButton1.setHideActionText(true);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        panelFormationDetails.add(jPanel2);

        panelStagiairesList.setBorder(javax.swing.BorderFactory.createTitledBorder("Stagiaires"));
        panelStagiairesList.setLayout(new javax.swing.BoxLayout(panelStagiairesList, javax.swing.BoxLayout.Y_AXIS));

        tblListeStagiaires.setModel(listStagiairesModel);
        tblListeStagiaires.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tblListeStagiaires);

        panelStagiairesList.add(jScrollPane2);

        btnAjoutStagiaire.setText("Ajouter un stagiaire");
        btnAjoutStagiaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutStagiaireActionPerformed(evt);
            }
        });
        jPanel1.add(btnAjoutStagiaire);

        btnMajStagiaire.setText("Modifier le stagiaire");
        btnMajStagiaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMajStagiaireActionPerformed(evt);
            }
        });
        jPanel1.add(btnMajStagiaire);

        btnAfficheECF.setText("Resultat ECF");
        btnAfficheECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAfficheECFActionPerformed(evt);
            }
        });
        jPanel1.add(btnAfficheECF);

        panelStagiairesList.add(jPanel1);

        panelFormationDetails.add(panelStagiairesList);

        getContentPane().add(panelFormationDetails, java.awt.BorderLayout.CENTER);

        panelHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        menuFile.setText("Fichier");
        menuFile.setName("FermerItem"); // NOI18N

        menuFileFermer.setText("Fermer");
        menuFileFermer.setToolTipText("");
        menuFileFermer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileFermerActionPerformed(evt);
            }
        });
        menuFile.add(menuFileFermer);

        menu.add(menuFile);

        jMenu3.setText("?");

        MenuItemAbout.setText("A propos");
        MenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemAboutActionPerformed(evt);
            }
        });
        jMenu3.add(MenuItemAbout);

        menu.add(jMenu3);

        setJMenuBar(menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuFileFermerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileFermerActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuFileFermerActionPerformed

    private void MenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemAboutActionPerformed
        About aboutDialog = new About(this, true);
        aboutDialog.setVisible(true);
    }//GEN-LAST:event_MenuItemAboutActionPerformed

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void txtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomActionPerformed

    private void btnActionFormDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionFormDetailActionPerformed
        // TODO add your handling code here:
        //TODO gérer la mise à jour
        
        saveFormation();
        
    }//GEN-LAST:event_btnActionFormDetailActionPerformed

    private void btnAjoutFormationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutFormationActionPerformed
           clearFormationDetails();
           //btnActionFormDetail.setText("OK");
    }//GEN-LAST:event_btnAjoutFormationActionPerformed

    private void btnAjoutStagiaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutStagiaireActionPerformed
        StagiaireForm stagiaireForm = new StagiaireForm(this, true, selectedFormation, this);
        stagiaireForm.setVisible(true);
    }//GEN-LAST:event_btnAjoutStagiaireActionPerformed

    private void btnMajStagiaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMajStagiaireActionPerformed
        
        int selectedRow = tblListeStagiaires.getSelectedRow();
        if (selectedRow==-1){
             JOptionPane.showMessageDialog(null, "Aucun stagiaire de selectionné, ", "selection stagiaire", JOptionPane.INFORMATION_MESSAGE); 
             return;
            
        }
        Stagiaire selectedStagiaire = listStagiairesModel.getStagiaire(tblListeStagiaires.getSelectedRow());
        
        StagiaireForm stagiaireForm = new StagiaireForm(this, true, selectedFormation, this);
        stagiaireForm.setStagiaire(selectedStagiaire);
        stagiaireForm.setVisible(true);
    }//GEN-LAST:event_btnMajStagiaireActionPerformed

    private void btnAfficheECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAfficheECFActionPerformed
        int selectedRow = tblListeStagiaires.getSelectedRow();
        if (selectedRow==-1){
             JOptionPane.showMessageDialog(null, "Aucun stagiaire de selectionné, ", "selection stagiaire", JOptionPane.INFORMATION_MESSAGE); 
             return;
            
        }
        Stagiaire selectedStagiaire = listStagiairesModel.getStagiaire(tblListeStagiaires.getSelectedRow());
        StagiaireECFForm ecfResult = new StagiaireECFForm(this, true, selectedStagiaire);
        ecfResult.setVisible(true);
    }//GEN-LAST:event_btnAfficheECFActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Formation form = tblFormationModel.getFormation(tblFormations.getSelectedRow());
        
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/reports/ecf_result.jasper"));
            //pour éviter l'import de fonts
            report.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "false");
            report.setProperty("net.sf.jasperreports.default.font.name=SansSerif", "true");
            HashMap<String, Object> params = new HashMap<>();
            params.put("formation_code", form.getCode());
            params.put("formation_nom", form.getNom());
            
            JasperPrint jPrint = JasperFillManager.fillReport(report, params, ConnectDB.getConnection());
            
            JFrame pdfFrame = new JFrame("Rapport");
            pdfFrame.getContentPane().add(new JRViewer(jPrint));
            pdfFrame.pack();
            pdfFrame.setSize(this.getSize());
            pdfFrame.setVisible(true);
        
        
        } catch (JRException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuItemAbout;
    private javax.swing.JButton btnActionFormDetail;
    private javax.swing.JButton btnAfficheECF;
    private javax.swing.JButton btnAjoutFormation;
    private javax.swing.JButton btnAjoutStagiaire;
    private javax.swing.JButton btnMajStagiaire;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblDateDeb;
    private javax.swing.JLabel lblDateFin;
    private javax.swing.JLabel lblNom;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileFermer;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelFormDetailsActions;
    private javax.swing.JPanel panelFormDetailsFields;
    private javax.swing.JPanel panelFormationDetails;
    private javax.swing.JPanel panelFormationList;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelStagiairesList;
    private javax.swing.JTable tblFormations;
    private javax.swing.JTable tblListeStagiaires;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtDateDeb;
    private javax.swing.JTextField txtDateFin;
    private javax.swing.JTextField txtNom;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onNewStagiaire(Stagiaire s) {
        listStagiairesModel.addStagiaire(s);
    }

    @Override
    public void onUpdatedStagiaire(Stagiaire s) {
        listStagiairesModel.fireTableDataChanged();
    }
}
