/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import controller.Controller;
import domain.Korisnik;
import domain.PrijavaPutovanja;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows HD
 */
public class MainFrm extends javax.swing.JFrame {

    /**
     * Creates new form MainFrm
     */
    public MainFrm() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        Controller.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMain = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuPrijava = new javax.swing.JMenuItem();
        menuPrikaz = new javax.swing.JMenuItem();
        menuLogin = new javax.swing.JMenuItem();
        menuRegister = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMain.setPreferredSize(new java.awt.Dimension(650, 400));
        panelMain.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelMain, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Meni");

        menuPrijava.setText("Prijava putovanja");
        menuPrijava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrijavaActionPerformed(evt);
            }
        });
        jMenu1.add(menuPrijava);

        menuPrikaz.setText("Prikaz putovanja");
        menuPrikaz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPrikazActionPerformed(evt);
            }
        });
        jMenu1.add(menuPrikaz);

        menuLogin.setText("Logovanje");
        menuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoginActionPerformed(evt);
            }
        });
        jMenu1.add(menuLogin);

        menuRegister.setText("Registracija");
        menuRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRegisterActionPerformed(evt);
            }
        });
        jMenu1.add(menuRegister);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoginActionPerformed
        this.dispose();
        new Login(this, true).setVisible(true);
    }//GEN-LAST:event_menuLoginActionPerformed

    private void menuRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRegisterActionPerformed
        new Register(this, true).setVisible(true);
    }//GEN-LAST:event_menuRegisterActionPerformed

    private void menuPrijavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrijavaActionPerformed
        
        panelMain.removeAll();
        PanelPrijava panel1 = new PanelPrijava();
        panelMain.add(panel1);
        
        
        this.pack();
        revalidate();
        repaint();        
    }//GEN-LAST:event_menuPrijavaActionPerformed

    private void menuPrikazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPrikazActionPerformed
        
        panelMain.removeAll();

        JTextField txtJmbg = new JTextField();
        txtJmbg.setPreferredSize(new Dimension(200, 25));
        JButton btnPrikazi = new JButton("Prikazi");

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("JMBG:"));
        inputPanel.add(txtJmbg);
        inputPanel.add(btnPrikazi);
        panelMain.add(inputPanel, BorderLayout.NORTH);
        JTable tabela = new JTable();
        DefaultTableModel dtm =new DefaultTableModel();
        String [] kolone = {"id","Datum prijave","Datum ulaska","Datum izlaska","Trajanje","Nacina prevoza","status"};
        for(int i=0;i<kolone.length;i++){
            dtm.addColumn(kolone[i]);
        }
        dtm.setRowCount(0);
        tabela.setModel(dtm);
        JScrollPane scrollPane = new JScrollPane(tabela);
        panelMain.add(scrollPane);
        this.pack();
        revalidate();
        repaint();
        
        btnPrikazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("prikazi");
                dtm.setRowCount(0);
                try {
                    Korisnik k = new Korisnik();
                    k.setJmbg(txtJmbg.getText());
                    k.setId(Controller.getInstance().getKorisnikId(k));
                    List<PrijavaPutovanja> prijave = Controller.getInstance().getListPrijavaPutovanja(k);
                if(prijave!=null){
                    for(PrijavaPutovanja pp : prijave){
                        String status="zavrsena";
                        if(pp.getDatumUlaska().isBefore(LocalDate.now())){
                            status = "zavrsena";
                        }else if(pp.getDatumUlaska().minusDays(2).isBefore(LocalDate.now())){
                            status="zakljucana";
                        }else{
                            status = "u obradi";
                        }
                        Object[] red = {pp.getId(),pp.getDatumPrijave(),pp.getDatumUlaska(),pp.getDatumIzlaska(),
                        pp.getTrajanje(),pp.getNacinPrevoza().getNaziv(),status};
                        dtm.addRow(red);
                    }
            }
                } catch (Exception ex) {
                    
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Prikaz",JOptionPane.ERROR_MESSAGE);
        
                }

            }
        });
        
    }//GEN-LAST:event_menuPrikazActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuLogin;
    private javax.swing.JMenuItem menuPrijava;
    private javax.swing.JMenuItem menuPrikaz;
    private javax.swing.JMenuItem menuRegister;
    private javax.swing.JPanel panelMain;
    // End of variables declaration//GEN-END:variables
}
