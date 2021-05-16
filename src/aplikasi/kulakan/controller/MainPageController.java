/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.view.KulakanPage_new;
import aplikasi.kulakan.view.MainPage;
import aplikasi.kulakan.view.WelcomePage;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class MainPageController {
   private final MainPage main = new MainPage();
   private final ViewSupplierPageController viewSupplier = new ViewSupplierPageController();
   private final BarangController barangController = new BarangController();
   private final KulakanPageController kulakanController;
   private final ReturPembelianController returPembelian;
   private final StockController stockController;
   private final WelcomePage welcome = new WelcomePage();
   private PenjualanPage_newController penjualanPageController;
   private RekapControllerNew rekapController;
   
    public MainPageController(){
        
        main.getAddSupplierButton().addActionListener(this::addSupplierButtonActionPerformed);
        main.getBarangPageButton().addActionListener(this::openBarangPage);
        main.getKulakanPageButton().addActionListener(this::openKulakanPage);
        main.getStockButton().addActionListener(this::openStockPage);
        main.getDataPenjualanButton().addActionListener(this::openDataPenjualan);
        main.getRekapButton().addActionListener(this::openRekapPage);
        main.getMenuLayer().getContentPane().removeAll();
        welcome.setSize(main.getMenuLayer().getSize());
        welcome.setVisible(true);
        main.getMenuLayer().getContentPane().add(welcome, java.awt.BorderLayout.CENTER);
        
        main.getMenuLayer().setTitle("Home Page");
        main.validate();
        main.setVisible(true);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        kulakanController = new KulakanPageController(this);
        stockController = new StockController(this);
        penjualanPageController = new PenjualanPage_newController(this);
        returPembelian = new ReturPembelianController(this);
        rekapController = new RekapControllerNew();
    }
    
    public void addSupplierButtonActionPerformed(java.awt.event.ActionEvent evt){
        
        main.getMenuLayer().getContentPane().removeAll();
        viewSupplier.showSupplierPage().setSize(main.getMenuLayer().getSize());
        viewSupplier.showSupplierPage().setVisible(true);
        main.getMenuLayer().getContentPane().add(viewSupplier.showSupplierPage(), java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("Supplier Page");
        main.validate();
    }
    
    public void openBarangPage(java.awt.event.ActionEvent evt){
        main.getMenuLayer().getContentPane().removeAll();
        barangController.getBarangView().setSize(main.getMenuLayer().getSize());
        barangController.getBarangView().setVisible(true);
        main.getMenuLayer().getContentPane().add(barangController.getBarangView(), java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("Barang Page");
        main.validate();
        barangController.setParent(this);
    }
    
    public void openRekapPage(java.awt.event.ActionEvent evt){
         main.getMenuLayer().getContentPane().removeAll();
        rekapController.getRekap().setSize(main.getMenuLayer().getSize());
        rekapController.getRekap().setVisible(true);
        main.getMenuLayer().getContentPane().add(rekapController.getRekap(), java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("Rekap");
        main.validate();
    }
    public void openStockPage(java.awt.event.ActionEvent evt){
        main.getMenuLayer().getContentPane().removeAll();
        stockController.stockPage().setSize(main.getMenuLayer().getSize());
        stockController.stockPage().setVisible(true);
        main.getMenuLayer().getContentPane().add(stockController.stockPage(), java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("Stock Page");
        main.validate();
    }
   
    public void openDataPenjualan(java.awt.event.ActionEvent evt){
        main.getMenuLayer().getContentPane().removeAll();
        penjualanPageController.getPage().setSize(main.getMenuLayer().getSize());
        penjualanPageController.getPage().setVisible(true);
        main.getMenuLayer().getContentPane().add(penjualanPageController.getPage(), java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("Data Penjualan");
        penjualanPageController.showAllData();
        penjualanPageController.viewOnTable();
        main.validate();
       
    }
    public void openKulakanPage(java.awt.event.ActionEvent evt){
       KulakanPage_new x = new KulakanPage_new();
       x.getWorkspace().setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
       x.getWorkspace().addTab("KULAKAN",kulakanController.getKulakanPage());
       x.getWorkspace().addTab("RETUR KULAKAN",returPembelian.getPage());
       x.validate();
       
       main.getMenuLayer().getContentPane().removeAll();
       x.setSize(main.getMenuLayer().getSize());
       x.setVisible(true);
       main.getMenuLayer().getContentPane().add(x, java.awt.BorderLayout.CENTER);
       main.getMenuLayer().setTitle("Kulakan Page");
       
//         main.getMenuLayer().getContentPane().removeAll();
//         kulakanController.getKulakanPage().setSize(main.getMenuLayer().getSize());
//         kulakanController.getKulakanPage().setVisible(true);
//         main.getMenuLayer().getContentPane().add(kulakanController.getKulakanPage(), java.awt.BorderLayout.CENTER);
//         main.getMenuLayer().setTitle("Kulakan Page");
//         main.validate();
    }
    public MainPage getParent(){
        return main;
    }
}
