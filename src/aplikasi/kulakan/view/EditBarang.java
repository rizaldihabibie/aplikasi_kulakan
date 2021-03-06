/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class EditBarang extends javax.swing.JDialog {

    /**
     * Creates new form EditBarang
     */
    public EditBarang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
        (screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2);
    }

    public JTextArea getDeskripsiField() {
        return deskripsiField;
    }

    public void setDeskripsiField(JTextArea deskripsiField) {
        this.deskripsiField = deskripsiField;
    }

    public JTextField getKodeBarangField() {
        return kodeBarangField;
    }

    public void setKodeBarangField(JTextField kodeBarangField) {
        this.kodeBarangField = kodeBarangField;
    }

    public JTextField getNamaBarangField() {
        return namaBarangField;
    }

    public void setNamaBarangField(JTextField namaBarangField) {
        this.namaBarangField = namaBarangField;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JTextField getJmlSatuanKecilField() {
        return jmlSatuanKecilField;
    }

    public void setJmlSatuanKecilField(JTextField jmlSatuanKecilField) {
        this.jmlSatuanKecilField = jmlSatuanKecilField;
    }

    public JLabel getLabelSatuanBesar() {
        return labelSatuanBesar;
    }

    public void setLabelSatuanBesar(JLabel labelSatuanBesar) {
        this.labelSatuanBesar = labelSatuanBesar;
    }

    public JLabel getLabelSatuanKecil() {
        return labelSatuanKecil;
    }

    public void setLabelSatuanKecil(JLabel labelSatuanKecil) {
        this.labelSatuanKecil = labelSatuanKecil;
    }

    public JTextField getSatuanTerbesarField() {
        return satuanTerbesarField;
    }

    public void setSatuanTerbesarField(JTextField satuanTerbesarField) {
        this.satuanTerbesarField = satuanTerbesarField;
    }

    public JTextField getSatuanTerkecilField() {
        return satuanTerkecilField;
    }

    public void setSatuanTerkecilField(JTextField satuanTerkecilField) {
        this.satuanTerkecilField = satuanTerkecilField;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        deskripsiField = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        namaBarangField = new javax.swing.JTextField();
        kodeBarangField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        satuanTerbesarField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        satuanTerkecilField = new javax.swing.JTextField();
        labelSatuanBesar = new javax.swing.JLabel();
        jmlSatuanKecilField = new javax.swing.JTextField();
        labelSatuanKecil = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        deskripsiField.setColumns(20);
        deskripsiField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        deskripsiField.setRows(5);
        jScrollPane1.setViewportView(deskripsiField);

        saveButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        saveButton.setText("SIMPAN");

        namaBarangField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        kodeBarangField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel1.setText("Kode Barang");

        jLabel5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel5.setText("Nama Barang");

        jLabel3.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel3.setText("Deskripsi Barang");

        satuanTerbesarField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel6.setText("Satuan Terbesar");

        jLabel7.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel7.setText("Satuan Terkecil");

        satuanTerkecilField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        satuanTerkecilField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                satuanTerkecilFieldFocusLost(evt);
            }
        });

        labelSatuanBesar.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        labelSatuanBesar.setText("1 Satuan Besar Terdiri Dari");

        jmlSatuanKecilField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        labelSatuanKecil.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        labelSatuanKecil.setText("Satuan Kecil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaBarangField)
                    .addComponent(kodeBarangField)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton))
                    .addComponent(satuanTerbesarField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(satuanTerkecilField)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSatuanBesar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jmlSatuanKecilField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSatuanKecil)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kodeBarangField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(namaBarangField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(satuanTerbesarField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(4, 4, 4)
                .addComponent(satuanTerkecilField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSatuanBesar)
                    .addComponent(jmlSatuanKecilField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSatuanKecil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveButton)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void satuanTerkecilFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_satuanTerkecilFieldFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_satuanTerkecilFieldFocusLost

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
            java.util.logging.Logger.getLogger(EditBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditBarang dialog = new EditBarang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea deskripsiField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jmlSatuanKecilField;
    private javax.swing.JTextField kodeBarangField;
    private javax.swing.JLabel labelSatuanBesar;
    private javax.swing.JLabel labelSatuanKecil;
    private javax.swing.JTextField namaBarangField;
    private javax.swing.JTextField satuanTerbesarField;
    private javax.swing.JTextField satuanTerkecilField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
