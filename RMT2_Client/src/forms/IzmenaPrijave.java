/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms;

import controller.Controller;
import domain.DrzavaEu;
import domain.NacinPrevoza;
import domain.PrijavaPutovanja;
import domain.StavkaPrijave;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Windows HD
 */
public class IzmenaPrijave extends javax.swing.JDialog {

    /**
     * Creates new form IzmenaPrijave
     */
    PrijavaPutovanja pp;
    public IzmenaPrijave(java.awt.Frame parent, boolean modal,PrijavaPutovanja pp) throws Exception {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        txtIme.setText(pp.getKorisnik().getIme());
        txtPrezime.setText(pp.getKorisnik().getPrezime());
        txtJmbg.setText(pp.getKorisnik().getJmbg());
        txtBrPasosa.setText(pp.getKorisnik().getBroj_pasosa());
        dtpUlaska.setDate(pp.getDatumUlaska());
        dtpIzlaska.setDate(pp.getDatumIzlaska());
        napuniCmbNacinPrevoza();
        napuniCmbDrzave();
        cmbNacinPrevoza.setSelectedItem(pp.getNacinPrevoza());
        List<StavkaPrijave> stavke = Controller.getInstance().getStavkePrijave(pp);
        TableModel tm = tblDrzave.getModel();
        DefaultTableModel dtm = (DefaultTableModel) tm;
        dtm.setRowCount(0);
        for(StavkaPrijave s:stavke){
            Object[] red={s.getDrzava().getNaziv()};
            dtm.addRow(red);
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tblDrzave.setDefaultRenderer(Object.class, centerRenderer);
        this.pp=pp;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        dtpUlaska = new com.github.lgooddatepicker.components.DatePicker();
        dtpIzlaska = new com.github.lgooddatepicker.components.DatePicker();
        cmbNacinPrevoza = new javax.swing.JComboBox<>();
        cmbDrzava = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIme = new javax.swing.JTextField();
        txtBrPasosa = new javax.swing.JTextField();
        txtJmbg = new javax.swing.JTextField();
        txtPrezime = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDrzave = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setMinimumSize(new java.awt.Dimension(600, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 100));

        jButton3.setText("Dodaj drzavu");
        jButton3.setPreferredSize(new java.awt.Dimension(150, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton1.setText("Izmeni");
        jButton1.setPreferredSize(new java.awt.Dimension(150, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel5.setMinimumSize(new java.awt.Dimension(350, 300));
        jPanel5.setPreferredSize(new java.awt.Dimension(350, 300));
        jPanel5.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 37);
        jPanel5.add(dtpUlaska, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 37);
        jPanel5.add(dtpIzlaska, gridBagConstraints);

        cmbNacinPrevoza.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 71;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 0, 37);
        jPanel5.add(cmbNacinPrevoza, gridBagConstraints);

        cmbDrzava.setMinimumSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 71;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 24, 6, 37);
        jPanel5.add(cmbDrzava, gridBagConstraints);

        jLabel1.setText("Datum ulaska:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        jPanel5.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Datum izlaska:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 0);
        jPanel5.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Nacin prevoza:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 19, 0, 0);
        jPanel5.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Drzava:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 19, 0, 0);
        jPanel5.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Prezime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel5.add(jLabel5, gridBagConstraints);

        jLabel6.setText("JMBG:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 20, 0, 0);
        jPanel5.add(jLabel6, gridBagConstraints);

        jLabel7.setText("Broj pasosa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 0, 0);
        jPanel5.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Ime:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 20, 0, 0);
        jPanel5.add(jLabel8, gridBagConstraints);

        txtIme.setEnabled(false);
        txtIme.setMinimumSize(new java.awt.Dimension(100, 20));
        txtIme.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 24, 0, 0);
        jPanel5.add(txtIme, gridBagConstraints);

        txtBrPasosa.setEnabled(false);
        txtBrPasosa.setMinimumSize(new java.awt.Dimension(100, 20));
        txtBrPasosa.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 24, 0, 0);
        jPanel5.add(txtBrPasosa, gridBagConstraints);

        txtJmbg.setEnabled(false);
        txtJmbg.setMinimumSize(new java.awt.Dimension(100, 20));
        txtJmbg.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 0, 0);
        jPanel5.add(txtJmbg, gridBagConstraints);

        txtPrezime.setEnabled(false);
        txtPrezime.setMinimumSize(new java.awt.Dimension(100, 20));
        txtPrezime.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 24, 0, 0);
        jPanel5.add(txtPrezime, gridBagConstraints);

        jPanel2.add(jPanel5);

        jPanel4.setMinimumSize(new java.awt.Dimension(350, 300));
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 300));

        jButton2.setText("obrisi");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        tblDrzave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Naziv"
            }
        ));
        tblDrzave.setMaximumSize(new java.awt.Dimension(300, 250));
        tblDrzave.setMinimumSize(new java.awt.Dimension(300, 250));
        tblDrzave.setPreferredSize(new java.awt.Dimension(300, 250));
        jScrollPane1.setViewportView(tblDrzave);

        jPanel4.add(jScrollPane1);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        PrijavaPutovanja izmenjenaPrijava=pp;
        pp.setDatumPrijave(LocalDate.now());
        pp.setDatumIzlaska(dtpIzlaska.getDate());
        pp.setDatumUlaska(dtpUlaska.getDate());
        pp.setNacinPrevoza((NacinPrevoza) cmbNacinPrevoza.getSelectedItem());
        TableModel tm = tblDrzave.getModel();
        DefaultTableModel dtm = (DefaultTableModel) tm;
        List<StavkaPrijave> drzave = new LinkedList<StavkaPrijave>();
        for(int i=0;i<dtm.getRowCount();i++){
            Object value = dtm.getValueAt(i, 0);
            String naziv="";
            if (value instanceof String) {
            naziv = (String) value;
            } else if (value instanceof DrzavaEu) {
            naziv = ((DrzavaEu) value).toString();
            } 
            Long idDrzava=0l;
            try {
                idDrzava = Controller.getInstance().getDrzavaId(naziv);
            } catch (Exception ex) {
                Logger.getLogger(IzmenaPrijave.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            drzave.add(new StavkaPrijave(pp.getId(), Long.valueOf(String.valueOf(i)), 
                    new DrzavaEu(idDrzava, naziv)));
        }
        pp.setDrzave(drzave);
        try {
            Controller.getInstance().updatePrijava(izmenjenaPrijava);
        JOptionPane.showMessageDialog(this, "Uspesna izmena.", "", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

        } catch (Exception ex) {
            Logger.getLogger(IzmenaPrijave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        TableModel tm = tblDrzave.getModel();
        DefaultTableModel dtm = (DefaultTableModel) tm;
        int selektovanRed = tblDrzave.getSelectedRow();
        dtm.removeRow(selektovanRed);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TableModel tm = tblDrzave.getModel();
        DefaultTableModel dtm = (DefaultTableModel) tm;
        Object[] red = {cmbDrzava.getSelectedItem()};
        dtm.addRow(red); 
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<DrzavaEu> cmbDrzava;
    private javax.swing.JComboBox<NacinPrevoza> cmbNacinPrevoza;
    private com.github.lgooddatepicker.components.DatePicker dtpIzlaska;
    private com.github.lgooddatepicker.components.DatePicker dtpUlaska;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDrzave;
    private javax.swing.JTextField txtBrPasosa;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtJmbg;
    private javax.swing.JTextField txtPrezime;
    // End of variables declaration//GEN-END:variables

    private void napuniCmbNacinPrevoza() throws Exception {
        List<NacinPrevoza> prevozi = Controller.getInstance().getListNacinPrevoza();
        for(NacinPrevoza n : prevozi){
            cmbNacinPrevoza.addItem(n);
        }
    }

    private void napuniCmbDrzave() throws Exception {
        List<DrzavaEu> drzave = Controller.getInstance().getListDrzave();
        for(DrzavaEu d:drzave){
            cmbDrzava.addItem(d);
        }
    }
}
