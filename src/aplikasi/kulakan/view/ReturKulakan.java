/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.view;

import datechooser.beans.DateChooserCombo;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class ReturKulakan extends javax.swing.JPanel {

    /**
     * Creates new form ReturKulakan
     */
    public ReturKulakan() {
        initComponents();
    }

    public JButton getChooseBarang() {
        return chooseBarang;
    }

    public void setChooseBarang(JButton chooseBarang) {
        this.chooseBarang = chooseBarang;
    }

    public JTextField getJumlahField() {
        return jumlahField;
    }

    public void setJumlahField(JTextField jumlahField) {
        this.jumlahField = jumlahField;
    }

    public JTextArea getKeteranganField() {
        return keteranganField;
    }

    public void setKeteranganField(JTextArea keteranganField) {
        this.keteranganField = keteranganField;
    }

    public JTextField getNamaBarangReturField() {
        return namaBarangReturField;
    }

    public void setNamaBarangReturField(JTextField namaBarangReturField) {
        this.namaBarangReturField = namaBarangReturField;
    }


    public JTextField getNotaField() {
        return notaField;
    }

    public void setNotaField(JTextField notaField) {
        this.notaField = notaField;
    }

    public JButton getReturButton() {
        return returButton;
    }

    public void setReturButton(JButton returButton) {
        this.returButton = returButton;
    }

    public JComboBox<String> getSatuanReturField() {
        return satuanReturField;
    }

    public void setSatuanReturField(JComboBox<String> satuanReturField) {
        this.satuanReturField = satuanReturField;
    }

    public DateChooserCombo getTanggalRetur() {
        return tanggalRetur;
    }

    public void setTanggalRetur(DateChooserCombo tanggalRetur) {
        this.tanggalRetur = tanggalRetur;
    }

    public JTable getViewTable() {
        return viewTable;
    }

    public void setViewTable(JTable viewTable) {
        this.viewTable = viewTable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tanggalRetur = new datechooser.beans.DateChooserCombo();
        jLabel2 = new javax.swing.JLabel();
        namaBarangReturField = new javax.swing.JTextField();
        chooseBarang = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        notaField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jumlahField = new javax.swing.JTextField();
        satuanReturField = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        keteranganField = new javax.swing.JTextArea();
        returButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 419));

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel1.setText("Tanggal Retur");

        tanggalRetur.setFieldFont(new java.awt.Font("Microsoft New Tai Lue", java.awt.Font.BOLD, 12));

        jLabel2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel2.setText("Barang");

        namaBarangReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        chooseBarang.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        chooseBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1479754122_sign-add.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel3.setText("Nota Pembelian");

        notaField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel4.setText("Jumlah");

        jumlahField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        satuanReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        satuanReturField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel5.setText("Keterangan");

        keteranganField.setColumns(20);
        keteranganField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        keteranganField.setRows(5);
        jScrollPane1.setViewportView(keteranganField);

        returButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        returButton.setText("Retur Pembelian");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(notaField)
                    .addComponent(tanggalRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(namaBarangReturField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chooseBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(returButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jumlahField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(satuanReturField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tanggalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaBarangReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlahField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(satuanReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        viewTable.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        viewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(viewTable);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextArea keteranganField;
    private javax.swing.JTextField namaBarangReturField;
    private javax.swing.JTextField notaField;
    private javax.swing.JButton returButton;
    private javax.swing.JComboBox<String> satuanReturField;
    private datechooser.beans.DateChooserCombo tanggalRetur;
    private javax.swing.JTable viewTable;
    // End of variables declaration//GEN-END:variables
}
