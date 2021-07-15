/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.view;

import datechooser.beans.DateChooserCombo;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class PenjualanPage extends javax.swing.JPanel {

    /**
     * Creates new form PenjualanPage_new
     */
    public PenjualanPage(){
        initComponents();
        workspace.removeTabAt(2);
        saveButton.setMnemonic(KeyEvent.VK_S);
    }

    public JTextField getNoNotaParam() {
        return noNotaParam;
    }

    public void setNoNotaParam(JTextField noNotaParam) {
        this.noNotaParam = noNotaParam;
    }

    public JTable getViewTableRetur() {
        return viewTableRetur;
    }

    public void setViewTableRetur(JTable viewTableRetur) {
        this.viewTableRetur = viewTableRetur;
    }

    public JTabbedPane getWorkspace() {
        return workspace;
    }

    public void setWorkspace(JTabbedPane workspace) {
        this.workspace = workspace;
    }

    public JComboBox<String> getBulanParam() {
        return bulanParam;
    }

    public void setBulanParam(JComboBox<String> bulanParam) {
        this.bulanParam = bulanParam;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public void setFindButton(JButton findButton) {
        this.findButton = findButton;
    }

    public JTextField getNamaBarangParam() {
        return namaBarangParam;
    }

    public void setNamaBarangParam(JTextField namaBarangParam) {
        this.namaBarangParam = namaBarangParam;
    }

    public JComboBox<String> getTahunParam() {
        return tahunParam;
    }

    public void setTahunParam(JComboBox<String> tahunParam) {
        this.tahunParam = tahunParam;
    }

    public JTextField getTanggalParam() {
        return tanggalParam;
    }

    public void setTanggalParam(JTextField tanggalParam) {
        this.tanggalParam = tanggalParam;
    }

    public JTable getViewTable() {
        return viewTable;
    }

    public void setViewTable(JTable viewTable) {
        this.viewTable = viewTable;
    }

    public JTextField getHargaSatuanField() {
        return HargaSatuanField;
    }

    public void setHargaSatuanField(JTextField HargaSatuanField) {
        this.HargaSatuanField = HargaSatuanField;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    public JTextField getDiskonField() {
        return diskonField;
    }

    public void setDiskonField(JTextField diskonField) {
        this.diskonField = diskonField;
    }

    public JTextField getJumlahField() {
        return jumlahField;
    }

    public void setJumlahField(JTextField jumlahField) {
        this.jumlahField = jumlahField;
    }

    public JComboBox<String> getNamaBarangField() {
        return namaBarangField;
    }

    public void setNamaBarangField(JComboBox<String> namaBarangField) {
        this.namaBarangField = namaBarangField;
    }
    
//    public JComboBox<String> getSatuanField() {
//        return satuanField;
//    }
//
//    public void setSatuanField(JComboBox<String> satuanField) {
//        this.satuanField = satuanField;
//    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public DateChooserCombo getTanggalField() {
        return tanggalField;
    }

    public void setTanggalField(DateChooserCombo tanggalField) {
        this.tanggalField = tanggalField;
    }

    public JTextField getTotalHargaField() {
        return totalHargaField;
    }

    public void setTotalHargaField(JTextField totalHargaField) {
        this.totalHargaField = totalHargaField;
    }

    public JTable getViewTablePenjualan() {
        return viewTablePenjualan;
    }

    public void setViewTablePenjualan(JTable viewTablePenjualan) {
        this.viewTablePenjualan = viewTablePenjualan;
    }

    public JButton getAddBarangRetur() {
        return addBarangRetur;
    }

    public void setAddBarangRetur(JButton addBarangRetur) {
        this.addBarangRetur = addBarangRetur;
    }

    public JTextField getBarangReturField() {
        return barangReturField;
    }

    public void setBarangReturField(JTextField barangReturField) {
        this.barangReturField = barangReturField;
    }

    public JTextField getJumlahReturField() {
        return jumlahReturField;
    }

    public void setJumlahReturField(JTextField jumlahReturField) {
        this.jumlahReturField = jumlahReturField;
    }

    public JTextArea getKeteranganField() {
        return keteranganField;
    }

    public void setKeteranganField(JTextArea keteranganField) {
        this.keteranganField = keteranganField;
    }

    public JButton getSaveReturButton() {
        return saveReturButton;
    }

    public void setSaveReturButton(JButton saveReturButton) {
        this.saveReturButton = saveReturButton;
    }

    public JTextField getNoNotaReturField() {
        return noNotaReturField;
    }

    public void setNoNotaReturField(JTextField noNotaReturField) {
        this.noNotaReturField = noNotaReturField;
    }

    public DateChooserCombo getTanggalRetur() {
        return tanggalField;
    }

    public void setTanggalRetur(DateChooserCombo tanggalRetur) {
        this.tanggalField = tanggalRetur;
    }

    public JTextField getNotaField() {
        return notaField;
    }

    public void setNotaField(JTextField notaField) {
        this.notaField = notaField;
    }

    public JComboBox<String> getSatuanReturField() {
        return satuanReturField;
    }

    public void setSatuanReturField(JComboBox<String> satuanReturField) {
        this.satuanReturField = satuanReturField;
    }

    public JLabel getTotalMarginLabel() {
        return totalMarginLabel;
    }

    public void setTotalMarginLabel(JLabel totalMarginLabel) {
        this.totalMarginLabel = totalMarginLabel;
    }

    public JLabel getTotalPenjualanLabel() {
        return totalPenjualanLabel;
    }

    public void setTotalPenjualanLabel(JLabel totalPenjualanLabel) {
        this.totalPenjualanLabel = totalPenjualanLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workspace = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewTablePenjualan = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        totalPenjualanLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        tanggalField = new datechooser.beans.DateChooserCombo();
        jLabel8 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        notaField = new javax.swing.JTextField();
        jumlahField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        namaBarangField = new javax.swing.JComboBox<>();
        HargaSatuanField = new javax.swing.JTextField();
        diskonField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        totalHargaField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        totalMarginLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        viewTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        findButton = new javax.swing.JButton();
        namaBarangParam = new javax.swing.JTextField();
        tanggalParam = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tahunParam = new javax.swing.JComboBox<>();
        bulanParam = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        noNotaParam = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        addBarangRetur = new javax.swing.JButton();
        barangReturField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        satuanReturField = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jumlahReturField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        keteranganField = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        saveReturButton = new javax.swing.JButton();
        noNotaReturField = new javax.swing.JTextField();
        tanggalRetur = new datechooser.beans.DateChooserCombo();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        viewTableRetur = new javax.swing.JTable();

        workspace.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 102)));

        viewTablePenjualan.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        viewTablePenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Nama Barang", "Jumlah", "Harga Satuan", "Diskon", "Total Harga", "Margin"
            }
        ));
        jScrollPane2.setViewportView(viewTablePenjualan);

        saveButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        saveButton.setText("SIMPAN");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel19.setText("Total :");

        totalPenjualanLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel9.add(tanggalField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 2, 187, 32));

        jLabel8.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Diskon");
        jPanel9.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 202, 100, 32));

        addButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        addButton.setText("Tambah");
        jPanel9.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 241, -1, 32));

        jLabel7.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Harga Satuan");
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 160, 100, 32));

        jLabel17.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Nomor Nota");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 42, 100, 32));

        jLabel5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nama Barang");
        jPanel9.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 81, 100, 32));

        notaField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jPanel9.add(notaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 43, 187, 32));

        jumlahField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jPanel9.add(jumlahField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 120, 187, 32));

        jLabel11.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tanggal");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 2, 100, 32));

        namaBarangField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        namaBarangField.setMaximumSize(new java.awt.Dimension(73, 25));
        jPanel9.add(namaBarangField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 81, 187, 32));

        HargaSatuanField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jPanel9.add(HargaSatuanField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 161, 187, 32));

        diskonField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jPanel9.add(diskonField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 203, 187, 32));

        jLabel6.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("QTY");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 119, 100, 32));

        jLabel9.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Total Harga");
        jPanel9.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 241, 100, 32));

        totalHargaField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jPanel9.add(totalHargaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 242, 187, 32));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPenjualanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(8, 8, 8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1301, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(1226, 1226, 1226)
                            .addComponent(saveButton))))
                .addGap(14, 31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(totalPenjualanLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addContainerGap())
        );

        workspace.addTab("KASIR", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(800, 380));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 380));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Total Margin :");

        totalMarginLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        totalMarginLabel.setText("Rp...");

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
        jScrollPane5.setViewportView(viewTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalMarginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(totalMarginLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMaximumSize(new java.awt.Dimension(306, 499));
        jPanel5.setMinimumSize(new java.awt.Dimension(306, 499));

        findButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        findButton.setText("Cari Data");

        namaBarangParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        namaBarangParam.setMaximumSize(new java.awt.Dimension(14, 23));

        tanggalParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        tanggalParam.setMaximumSize(new java.awt.Dimension(14, 23));

        jLabel3.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel4.setText("Nama Barang");

        jLabel2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel2.setText("Tahun");

        tahunParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        tahunParam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tahunParam.setMaximumSize(new java.awt.Dimension(14, 23));
        tahunParam.setMinimumSize(new java.awt.Dimension(14, 23));
        tahunParam.setPreferredSize(new java.awt.Dimension(14, 23));

        bulanParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        bulanParam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bulanParam.setMaximumSize(new java.awt.Dimension(14, 23));
        bulanParam.setMinimumSize(new java.awt.Dimension(14, 23));
        bulanParam.setPreferredSize(new java.awt.Dimension(14, 23));

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel1.setText("Bulan");

        jLabel18.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel18.setText("Nota");

        noNotaParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        noNotaParam.setMaximumSize(new java.awt.Dimension(14, 23));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(findButton))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(noNotaParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bulanParam, javax.swing.GroupLayout.Alignment.TRAILING, 0, 181, Short.MAX_VALUE)
                            .addComponent(tanggalParam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(namaBarangParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tahunParam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bulanParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tahunParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tanggalParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(namaBarangParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noNotaParam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findButton)
                .addContainerGap(363, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel5, java.awt.BorderLayout.WEST);

        workspace.addTab("DATA PENJUALAN", jPanel3);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 456));

        addBarangRetur.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        addBarangRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1479754122_sign-add.png"))); // NOI18N

        barangReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel12.setText("Barang");

        satuanReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        satuanReturField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel13.setText("Satuan");

        jumlahReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel14.setText("Nomor Nota");

        jLabel15.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel15.setText("Keterangan");

        keteranganField.setColumns(20);
        keteranganField.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 12)); // NOI18N
        keteranganField.setRows(5);
        jScrollPane3.setViewportView(keteranganField);

        jLabel16.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel16.setText("Tanggal Retur");

        saveReturButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        saveReturButton.setText("RETUR BARANG");

        noNotaReturField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jumlahReturField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(satuanReturField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(saveReturButton))
                            .addComponent(noNotaReturField))
                        .addContainerGap())
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(barangReturField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tanggalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(tanggalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barangReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jumlahReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(satuanReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(noNotaReturField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveReturButton)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel6, java.awt.BorderLayout.WEST);

        jPanel7.setLayout(new java.awt.BorderLayout());

        viewTableRetur.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(viewTableRetur);

        jPanel7.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.CENTER);

        workspace.addTab("RETUR PENJUALAN", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workspace)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workspace)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HargaSatuanField;
    private javax.swing.JButton addBarangRetur;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField barangReturField;
    private javax.swing.JComboBox<String> bulanParam;
    private javax.swing.JTextField diskonField;
    private javax.swing.JButton findButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField jumlahReturField;
    private javax.swing.JTextArea keteranganField;
    private javax.swing.JComboBox<String> namaBarangField;
    private javax.swing.JTextField namaBarangParam;
    private javax.swing.JTextField noNotaParam;
    private javax.swing.JTextField noNotaReturField;
    private javax.swing.JTextField notaField;
    private javax.swing.JComboBox<String> satuanReturField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveReturButton;
    private javax.swing.JComboBox<String> tahunParam;
    private datechooser.beans.DateChooserCombo tanggalField;
    private javax.swing.JTextField tanggalParam;
    private datechooser.beans.DateChooserCombo tanggalRetur;
    private javax.swing.JTextField totalHargaField;
    private javax.swing.JLabel totalMarginLabel;
    private javax.swing.JLabel totalPenjualanLabel;
    private javax.swing.JTable viewTable;
    private javax.swing.JTable viewTablePenjualan;
    private javax.swing.JTable viewTableRetur;
    private javax.swing.JTabbedPane workspace;
    // End of variables declaration//GEN-END:variables
}
