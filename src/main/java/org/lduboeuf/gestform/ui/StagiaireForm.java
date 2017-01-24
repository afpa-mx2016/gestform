/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lduboeuf.gestform.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.lduboeuf.gestform.model.Formation;
import org.lduboeuf.gestform.model.Stagiaire;
import org.lduboeuf.gestform.model.dao.ECFDAO;
import org.lduboeuf.gestform.model.dao.StagiaireDAO;

/**
 *
 * @author lionel
 */
public class StagiaireForm extends javax.swing.JDialog implements FocusListener {

    private Formation currentFormation;
    private Stagiaire currentStagiaire;
    private StagiaireFormEventListener listener;
    
    /**
     * Creates new form StagiaireForm
     */
    public StagiaireForm(java.awt.Frame parent, boolean modal, Formation formation, StagiaireFormEventListener listener ) {
        super(parent, modal);
        initComponents();
        this.listener = listener;
        this.currentFormation = formation;
        

        txtNom.addFocusListener(this);
        txtPrenom.addFocusListener(this);
        txtMatricule.addFocusListener(this);
        
        
        this.setSize(400, 300);
        //centrer par rapport au parent
        this.setLocationRelativeTo(parent);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().getClass()==JTextField.class){
            lblMessage.setVisible(false);
            JTextField txtF = (JTextField) e.getComponent();
            txtF.setBorder(BorderFactory.createEmptyBorder());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
    
    public interface StagiaireFormEventListener{
        public void onNewStagiaire(Stagiaire s);
        public void onUpdatedStagiaire(Stagiaire s);
    }

    public void setStagiaire(Stagiaire stag){
        this.currentStagiaire = stag;
        txtMatricule.setText(stag.getCodeStagiaire());
        txtMatricule.setEnabled(false);
        txtNom.setText(stag.getNom());
        txtPrenom.setText(stag.getPrenom());
        

        
        
    }
    
    
    private void createStagiaire(){
        
        
        
        String matricule = txtMatricule.getText();
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
        
        //TODO ici les controles sur le formulaire
        boolean formComplete = true;
        String msg = "";
        if (matricule.length()!=8){
            formComplete = false;
            msg += "<br> le code du stagiaire doit comporter au maximum 8 caractères";
            txtMatricule.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        if (nom.length()==0){
            formComplete = false;
            msg += "<br>  le nom du stagiaire ne peut être vide";
            txtNom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        if (nom.length()==0){
            formComplete = false;
            msg += "<br>  le prenom du stagiaire ne peut être vide";
            txtPrenom.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        
        if (!formComplete){
            lblMessage.setText("<html>"+msg+"</html>");
            lblMessage.setVisible(true);
            return;
        }
        
        Stagiaire stag = new Stagiaire(nom, prenom, matricule, currentFormation);
        
        try {
            StagiaireDAO.save(stag);
            
            //ok on affiche un message que tout c'est bien passé:
            JOptionPane.showMessageDialog(null, "Le stagiaire " + nom + " a bien été créé et ajouté à la formation + " + currentFormation.getNom(), "creation stagiaire", JOptionPane.INFORMATION_MESSAGE); 
            listener.onNewStagiaire(stag);
            this.dispose();
            
        } catch (Exception ex) {
            //TODO expliquer ce qu'il faut faire dans ce cas là
            JOptionPane.showMessageDialog(null, "Une erreur est survenue à la création d'un nouveau stagiaire, ...", "creation stagiaire", JOptionPane.ERROR_MESSAGE); 

            Logger.getLogger(StagiaireForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
    
    private void updateStagiaire(){
        
        //ici les controles sur le formulaire
        
        currentStagiaire.setNom(txtNom.getText());
        currentStagiaire.setPrenom(txtPrenom.getText());
        
        try {
            //TODO updatestag
            //StagiaireDAO.save(currentStagiaire);
            
            //ok on affiche un message que tout c'est bien passé:
            JOptionPane.showMessageDialog(null, "Le stagiaire " + currentStagiaire.getNom() + " a bien été modifié", "creation stagiaire", JOptionPane.INFORMATION_MESSAGE); 
            listener.onUpdatedStagiaire(currentStagiaire);
            this.dispose();
            
        } catch (Exception ex) {
            Logger.getLogger(StagiaireForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelStagiaireFields = new javax.swing.JPanel();
        lblMatricule = new javax.swing.JLabel();
        txtMatricule = new javax.swing.JTextField();
        lblNom = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        lblPrenom = new javax.swing.JLabel();
        txtPrenom = new javax.swing.JTextField();
        panelStagiaireActions = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        panelCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("nouveau stagiaire");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        panelStagiaireFields.setLayout(new java.awt.GridLayout(3, 2));

        lblMatricule.setText("matricule:");
        panelStagiaireFields.add(lblMatricule);
        panelStagiaireFields.add(txtMatricule);

        lblNom.setText("nom:");
        lblNom.setToolTipText("");
        panelStagiaireFields.add(lblNom);
        panelStagiaireFields.add(txtNom);

        lblPrenom.setText("prenom:");
        lblPrenom.setToolTipText("");
        panelStagiaireFields.add(lblPrenom);
        panelStagiaireFields.add(txtPrenom);

        getContentPane().add(panelStagiaireFields, java.awt.BorderLayout.NORTH);

        btnOK.setText("ok");
        btnOK.setToolTipText("");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        panelStagiaireActions.add(btnOK);

        btnCancel.setText("annuler");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        panelStagiaireActions.add(btnCancel);

        getContentPane().add(panelStagiaireActions, java.awt.BorderLayout.SOUTH);

        panelCenter.setLayout(new javax.swing.BoxLayout(panelCenter, javax.swing.BoxLayout.LINE_AXIS));

        lblMessage.setForeground(new java.awt.Color(255, 0, 0));
        lblMessage.setMaximumSize(null);
        lblMessage.setMinimumSize(null);
        lblMessage.setPreferredSize(null);
        jScrollPane1.setViewportView(lblMessage);

        panelCenter.add(jScrollPane1);

        getContentPane().add(panelCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (currentStagiaire!=null){ //update mode
            updateStagiaire();
        }else{
            createStagiaire(); 
        }
    
            
    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                StagiaireForm stagForm = new StagiaireForm(new javax.swing.JFrame(), true, null, null );
                stagForm.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                stagForm.setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMatricule;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblPrenom;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelStagiaireActions;
    private javax.swing.JPanel panelStagiaireFields;
    private javax.swing.JTextField txtMatricule;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtPrenom;
    // End of variables declaration//GEN-END:variables
}
