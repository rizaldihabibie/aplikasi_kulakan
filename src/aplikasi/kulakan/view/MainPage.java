/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.view;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;

/**
 *
 * @author USER
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        try 
        { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
            UIManager.put("InternalFrame.activeTitleBackground", Color.GRAY);
            initComponents();
            rekapButton.setVisible(false);
            stockButton.setVisible(false);
            ImageIcon img = new ImageIcon(getClass().getResource("/aplikasi/kulakan/images/samoedra_aquatic_24.png"));
            this.setIconImage(img.getImage());
        } 
        catch(Exception e){
            e.printStackTrace();
        }
       
    }

    public JButton getBackupButton() {
        return backupButton;
    }

    public void setBackupButton(JButton backupButton) {
        this.backupButton = backupButton;
    }

    public JButton getJournalButton() {
        return journalButton;
    }

    public void setJournalButton(JButton journalButton) {
        this.journalButton = journalButton;
    }

    public JButton getPengeluaranButton() {
        return pengeluaranButton;
    }

    public void setPengeluaranButton(JButton pengeluaranButton) {
        this.pengeluaranButton = pengeluaranButton;
    }

    public JButton getRekapButton() {
        return rekapButton;
    }

    public void setRekapButton(JButton rekapButton) {
        this.rekapButton = rekapButton;
    }

    public JButton getStockButton() {
        return stockButton;
    }

    public void setStockButton(JButton stockButton) {
        this.stockButton = stockButton;
    }

    public JButton getAddSupplierButton() {
        return addSupplierButton;
    }

    public void setAddSupplierButton(JButton addSupplierButton) {
        this.addSupplierButton = addSupplierButton;
    }

    public JInternalFrame getMenuLayer() {
        return menuLayer;
    }

    public void setMenuLayer(JInternalFrame menuLayer) {
        this.menuLayer = menuLayer;
    }

    public JButton getBarangPageButton() {
        return barangPageButton;
    }

    public void setBarangPageButton(JButton barangPageButton) {
        this.barangPageButton = barangPageButton;
    }

    public JButton getKulakanPageButton() {
        return kulakanPageButton;
    }

    public void setKulakanPageButton(JButton kulakanPageButton) {
        this.kulakanPageButton = kulakanPageButton;
    }

    public JButton getDataPenjualanButton() {
        return dataPenjualanButton;
    }

    public void setDataPenjualanButton(JButton dataPenjualanButton) {
        this.dataPenjualanButton = dataPenjualanButton;
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
        addSupplierButton = new javax.swing.JButton();
        barangPageButton = new javax.swing.JButton();
        kulakanPageButton = new javax.swing.JButton();
        stockButton = new javax.swing.JButton();
        dataPenjualanButton = new javax.swing.JButton();
        rekapButton = new javax.swing.JButton();
        pengeluaranButton = new javax.swing.JButton();
        backupButton = new javax.swing.JButton();
        journalButton = new javax.swing.JButton();
        menuLayer = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Management Toko");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        addSupplierButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        addSupplierButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/suppliers.png"))); // NOI18N
        addSupplierButton.setText("Supplier");
        addSupplierButton.setToolTipText("Supplier");
        addSupplierButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addSupplierButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        barangPageButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        barangPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1479150022_Package-Download.png"))); // NOI18N
        barangPageButton.setText("Barang");
        barangPageButton.setToolTipText("Barang");
        barangPageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        barangPageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        kulakanPageButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        kulakanPageButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/iconfinder_3440920_bag_basket_cart_ecommerce_shop_icon_32px.png"))); // NOI18N
        kulakanPageButton.setText("Kulakan");
        kulakanPageButton.setToolTipText("Kulakan");
        kulakanPageButton.setFocusPainted(false);
        kulakanPageButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        kulakanPageButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        stockButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        stockButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1485292261_stock-market.png"))); // NOI18N
        stockButton.setText("Stock");
        stockButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stockButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        dataPenjualanButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        dataPenjualanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1485292562_Sales-by-Payment-Method-rep.png"))); // NOI18N
        dataPenjualanButton.setText("Penjualan");
        dataPenjualanButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dataPenjualanButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        rekapButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        rekapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/1485292431_electronic_billing_machine.png"))); // NOI18N
        rekapButton.setText("Rekap");
        rekapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rekapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        pengeluaranButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        pengeluaranButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/iconfinder_money_transaction_transfer_send_3668846(2).png"))); // NOI18N
        pengeluaranButton.setText("Pengeluaran");
        pengeluaranButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pengeluaranButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        backupButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        backupButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/Time-Machine-Drive-icon.png"))); // NOI18N
        backupButton.setText("Backup Data");
        backupButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        backupButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        journalButton.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 12)); // NOI18N
        journalButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/iconfinder_kontact_journal_7744.png"))); // NOI18N
        journalButton.setText("Jurnal");
        journalButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        journalButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addSupplierButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barangPageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kulakanPageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataPenjualanButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rekapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pengeluaranButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(journalButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backupButton)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rekapButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dataPenjualanButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stockButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kulakanPageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barangPageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addSupplierButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pengeluaranButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backupButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(journalButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        menuLayer.setPreferredSize(new java.awt.Dimension(900, 480));
        menuLayer.setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/aplikasi/kulakan/images/samoedra_aquatic_24.png")));
        menuLayer.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuLayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuLayer, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSupplierButton;
    private javax.swing.JButton backupButton;
    private javax.swing.JButton barangPageButton;
    private javax.swing.JButton dataPenjualanButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton journalButton;
    private javax.swing.JButton kulakanPageButton;
    private javax.swing.JInternalFrame menuLayer;
    private javax.swing.JButton pengeluaranButton;
    private javax.swing.JButton rekapButton;
    private javax.swing.JButton stockButton;
    // End of variables declaration//GEN-END:variables
}
