/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.view;

import datechooser.beans.DateChooserCombo;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class PenjualanPage_new extends javax.swing.JPanel {

    /**
     * Creates new form PenjualanPage_new
     */
    public PenjualanPage_new() {
        initComponents();
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

    public JButton getAddBarang() {
        return addBarang;
    }

    public void setAddBarang(JButton addBarang) {
        this.addBarang = addBarang;
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

    public JTextField getNamaBarangField() {
        return namaBarangField;
    }

    public void setNamaBarangField(JTextField namaBarangField) {
        this.namaBarangField = namaBarangField;
    }

    public JComboBox<String> getSatuanField() {
        return satuanField;
    }

    public void setSatuanField(JComboBox<String> satuanField) {
        this.satuanField = satuanField;
    }

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
        return tanggalRetur;
    }

    public void setTanggalRetur(DateChooserCombo tanggalRetur) {
        this.tanggalRetur = tanggalRetur;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        workspace = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        namaBarangField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jumlahField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        HargaSatuanField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        diskonField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        totalHargaField = new javax.swing.JTextField();
        addBarang = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewTablePenjualan = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        satuanField = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        tanggalField = new datechooser.beans.DateChooserCombo();
        jLabel17 = new javax.swing.JLabel();
        notaField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
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
        tanggalRetur = new datechooser.beans.DateChooserCombo();
        saveReturButton = new javax.swing.JButton();
        noNotaReturField = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        viewTableRetur = new javax.swing.JTable();

        workspace.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 102)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel5.setText("Nama Barang");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 10, 0, 0);
        jPanel2.add(jLabel5, gridBagConstraints);

        namaBarangField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 174;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 0, 0);
        jPanel2.add(namaBarangField, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel6.setText("QTY");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 4, 0, 0);
        jPanel2.add(jLabel6, gridBagConstraints);

        jumlahField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 174;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 0, 0);
        jPanel2.add(jumlahField, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel7.setText("Harga Satuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 11;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 4, 0, 0);
        jPanel2.add(jLabel7, gridBagConstraints);

        HargaSatuanField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 101;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 0, 0);
        jPanel2.add(HargaSatuanField, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel8.setText("Diskon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 4, 0, 0);
        jPanel2.add(jLabel8, gridBagConstraints);

        diskonField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 47;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 4, 0, 0);
        jPanel2.add(diskonField, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel9.setText("Total Harga");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 15;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 10, 0, 0);
        jPanel2.add(jLabel9, gridBagConstraints);

        totalHargaField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 130;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 4, 0, 0);
        jPanel2.add(totalHargaField, gridBagConstraints);

        addBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1479754122_sign-add.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = -18;
        gridBagConstraints.ipady = -2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 6, 0, 0);
        jPanel2.add(addBarang, gridBagConstraints);

        viewTablePenjualan.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        viewTablePenjualan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No.", "Nama Barang", "Jumlah", "Satuan", "Harga Satuan", "Diskon", "Total Harga"
            }
        ));
        jScrollPane2.setViewportView(viewTablePenjualan);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 1187;
        gridBagConstraints.ipady = 301;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 30);
        jPanel2.add(jScrollPane2, gridBagConstraints);

        saveButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        saveButton.setText("SAVE");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 26, 11, 30);
        jPanel2.add(saveButton, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel10.setText("Satuan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 4, 0, 0);
        jPanel2.add(jLabel10, gridBagConstraints);

        satuanField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        satuanField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        jPanel2.add(satuanField, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel11.setText("Tanggal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 41, 0, 0);
        jPanel2.add(jLabel11, gridBagConstraints);

        addButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        addButton.setText("Tambah");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 10, 0, 30);
        jPanel2.add(addButton, gridBagConstraints);

        tanggalField.setFieldFont(new java.awt.Font("Microsoft New Tai Lue", java.awt.Font.BOLD, 12));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 125;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 0, 0);
        jPanel2.add(tanggalField, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel17.setText("Nomor Nota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipady = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 0, 0);
        jPanel2.add(jLabel17, gridBagConstraints);

        notaField.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 174;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 4, 0, 0);
        jPanel2.add(notaField, gridBagConstraints);

        workspace.addTab("PENJUALAN", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(800, 380));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setMinimumSize(new java.awt.Dimension(400, 100));
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 380));
        jPanel4.setLayout(new java.awt.GridBagLayout());

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
        jScrollPane1.setViewportView(viewTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 855;
        gridBagConstraints.ipady = 336;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(8, 1, 9, 0);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMinimumSize(new java.awt.Dimension(300, 0));

        findButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        findButton.setText("Cari Data");

        namaBarangParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        tanggalParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel4.setText("Nama Barang");

        jLabel2.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel2.setText("Tahun");

        tahunParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        tahunParam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        bulanParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        bulanParam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel1.setText("Bulan");

        jLabel18.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        jLabel18.setText("Nota");

        noNotaParam.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44)
                                .addComponent(tahunParam, 0, 200, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(47, 47, 47)
                                .addComponent(bulanParam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(35, 35, 35)
                                .addComponent(tanggalParam))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(namaBarangParam))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addGap(51, 51, 51)
                        .addComponent(noNotaParam))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(findButton)))
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(tanggalParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(namaBarangParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noNotaParam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findButton)
                .addContainerGap(281, Short.MAX_VALUE))
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

        tanggalRetur.setLocale(new java.util.Locale("in", "", ""));

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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jumlahReturField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(satuanReturField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(barangReturField)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(tanggalRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveReturButton))
                    .addComponent(noNotaReturField))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tanggalRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addContainerGap(63, Short.MAX_VALUE))
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
            .addComponent(workspace, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HargaSatuanField;
    private javax.swing.JButton addBarang;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jumlahField;
    private javax.swing.JTextField jumlahReturField;
    private javax.swing.JTextArea keteranganField;
    private javax.swing.JTextField namaBarangField;
    private javax.swing.JTextField namaBarangParam;
    private javax.swing.JTextField noNotaParam;
    private javax.swing.JTextField noNotaReturField;
    private javax.swing.JTextField notaField;
    private javax.swing.JComboBox<String> satuanField;
    private javax.swing.JComboBox<String> satuanReturField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveReturButton;
    private javax.swing.JComboBox<String> tahunParam;
    private datechooser.beans.DateChooserCombo tanggalField;
    private javax.swing.JTextField tanggalParam;
    private datechooser.beans.DateChooserCombo tanggalRetur;
    private javax.swing.JTextField totalHargaField;
    private javax.swing.JTable viewTable;
    private javax.swing.JTable viewTablePenjualan;
    private javax.swing.JTable viewTableRetur;
    private javax.swing.JTabbedPane workspace;
    // End of variables declaration//GEN-END:variables
}
