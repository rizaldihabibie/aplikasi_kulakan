/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.view.KulakanPageContainer;
import aplikasi.kulakan.view.MainPage;
import aplikasi.kulakan.view.PenjualanPage;
import aplikasi.kulakan.view.WelcomePage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class MainPageController implements BasicController{
    
   private final MainPage main = new MainPage();
   private ViewSupplierPageController viewSupplier;
   private BarangController barangController;
   private PengeluaranController pengeluaranController;
   private KulakanPageController kulakanController;
   private ReturPembelianController returPembelian;
   private StockController stockController;
   private WelcomePage welcome = new WelcomePage();
   private PenjualanController penjualanController;
   private RekapControllerNew rekapController;
   private JournalController journalController;
   private LoadingController loadingController;
   private BackupController backupController;
   private KulakanPageContainer kulakanPageContainer;
   private SwingWorker<Void, Void> worker;
   private static Logger LOG = LogManager.getLogger(MainPageController.class);
   private boolean isSupplierPageOpen = false;
   private boolean isBarangPageOpen = false;
   private boolean isPengeluaranPageOpen = false;
   private boolean isKulakanPageOpen = false;
   private boolean isPenjualanPageOpen = false;
   private boolean isJournalPageOpen = false;
   
    public MainPage getParent(){
        return main;
    }

    @Override
    public void init() {
        try{
            LOG.info("Preparing all menu");
            main.getAddSupplierButton().addActionListener(this::openSupplierPage);
            main.getBarangPageButton().addActionListener(this::openBarangPage);
            main.getKulakanPageButton().addActionListener(this::openKulakanPage);
            main.getStockButton().addActionListener(this::openStockPage);
            main.getDataPenjualanButton().addActionListener(this::openPenjualanPage);
            main.getPengeluaranButton().addActionListener(this::openPengeluaranPage);
            main.getRekapButton().addActionListener(this::openRekapPage);
            main.getJournalButton().addActionListener(this::openJournalPage);
            main.getBackupButton().addActionListener(this::showBackupDialog);
            main.getMenuLayer().getContentPane().removeAll();
            kulakanController = new KulakanPageController(this);
            stockController = new StockController(this);
            penjualanController = new PenjualanController(this);
            returPembelian = new ReturPembelianController(this);
            rekapController = new RekapControllerNew();
            pengeluaranController = new PengeluaranController(this);
            journalController = new JournalController(this);
            barangController = new BarangController(this);
            viewSupplier = new ViewSupplierPageController(this);
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        welcome.setSize(main.getMenuLayer().getSize());
        welcome.setVisible(true);
        main.getMenuLayer().getContentPane().add(welcome, java.awt.BorderLayout.CENTER);
        main.getMenuLayer().setTitle("HOME");
        main.validate();
        main.setVisible(true);
        main.setExtendedState(JFrame.MAXIMIZED_BOTH);
        welcome.requestFocus();
    }

    @Override
    public void stop() {
        if(isSupplierPageOpen){
            viewSupplier.stop();
            isSupplierPageOpen = false;
        }else if(isBarangPageOpen){
            barangController.stop();
            isBarangPageOpen = false;
        }else if(isPengeluaranPageOpen){
            pengeluaranController.stop();
            isPengeluaranPageOpen = false;
        }else if(isKulakanPageOpen){
            kulakanController.stop();
            isKulakanPageOpen = false;
        }else if(isPenjualanPageOpen){
            penjualanController.stop();
            isPenjualanPageOpen = false;
        }else if(isJournalPageOpen){
            journalController.stop();
            isJournalPageOpen = false;
        }
    }

    @Override
    public JPanel getPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void showBackupDialog(java.awt.event.ActionEvent evt){
        backupController = new BackupController(this);
        backupController.show();
    }
    public void openSupplierPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening menu supplier");
            this.stop();
            main.getMenuLayer().getContentPane().removeAll();
//            viewSupplier.getPage().setSize(main.getMenuLayer().getSize());
//            viewSupplier.getPage().setVisible(true);
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    viewSupplier.init();
                    viewSupplier.start();
                    main.getMenuLayer().getContentPane().add(viewSupplier.getPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("SUPPLIER");
                    main.validate();
                    isSupplierPageOpen = true;
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);
        }
    }
    
    public void openBarangPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening menu barang");
            this.stop();
            main.getMenuLayer().getContentPane().removeAll();
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    barangController.init();
                    barangController.start();
                    main.getMenuLayer().getContentPane().add(barangController.getPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("BARANG");
                    main.validate();
                    isBarangPageOpen = true;
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);            
        }
    }
    
    public void openKulakanPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening kulalakan page");
            this.stop();
            
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    kulakanController.init();
                    kulakanController.start();
                    kulakanPageContainer = new KulakanPageContainer();
                    kulakanPageContainer.getWorkspace().setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
                    kulakanPageContainer.getWorkspace().addTab("KULAKAN",kulakanController.getPage());
        //            x.getWorkspace().addTab("RETUR KULAKAN",returPembelian.getPage());
                    kulakanPageContainer.validate();

                    main.getMenuLayer().getContentPane().removeAll();
                    kulakanPageContainer.setSize(main.getMenuLayer().getSize());
                    kulakanPageContainer.setVisible(true);
                    main.getMenuLayer().getContentPane().add(kulakanPageContainer, java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("KULAKAN");
                    isKulakanPageOpen = true;
                    return null;
                }
                @Override
                protected void done() {
                    kulakanController.setSearchData();
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);          
        }
    }
    public void openPenjualanPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening penjualan page");
            main.getMenuLayer().getContentPane().removeAll();
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    penjualanController.init();
                    penjualanController.start();
                    main.getMenuLayer().getContentPane().add(penjualanController.getPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("PENJUALAN");
                    main.validate();
                    ((PenjualanPage)penjualanController.getPage()).getNotaField().requestFocus();
                    return null;
                }
                @Override
                protected void done() {
                    penjualanController.setSearchData();
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);    
        }
    }
    public void openPengeluaranPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening menu pengeluaran");
            this.stop();
            main.getMenuLayer().getContentPane().removeAll();
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    pengeluaranController.init();
                    pengeluaranController.start();
                    main.getMenuLayer().getContentPane().add(pengeluaranController.getPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("PENGELUARAN");
                    main.validate();
                    isPengeluaranPageOpen = true;
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();           
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);                  
        }
    }
    
    public void openJournalPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening journal page");
            this.stop();
            main.getMenuLayer().getContentPane().removeAll();
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    journalController.init();
                    journalController.start();
                    main.getMenuLayer().getContentPane().add(journalController.getPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("JURNAL");
                    main.validate();
                    isJournalPageOpen = true;
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker = null;
                }
            };
            worker.execute();
            loadingController.showLoading();            
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);            
        }
    }
    public void openRekapPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening rekap page");
            main.getMenuLayer().getContentPane().removeAll();
            rekapController.getRekap().setSize(main.getMenuLayer().getSize());
            rekapController.getRekap().setVisible(true);
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    main.getMenuLayer().getContentPane().add(rekapController.getRekap(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("REKAP");
                    main.validate();
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);            
        }
    }
    public void openStockPage(java.awt.event.ActionEvent evt){
        try{
            LOG.info("Opening stock page");
            main.getMenuLayer().getContentPane().removeAll();
            stockController.stockPage().setSize(main.getMenuLayer().getSize());
            stockController.stockPage().setVisible(true);
            loadingController = new LoadingController(main);
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    main.getMenuLayer().getContentPane().add(stockController.stockPage(), java.awt.BorderLayout.CENTER);
                    main.getMenuLayer().setTitle("STOCK");
                    main.validate();
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                }
            };
            worker.execute();
            loadingController.showLoading();            
        }catch(Exception e){
            JOptionPane.showMessageDialog(main, "Terjadi kesalahan!! Mohon hubungi developer atau matikan lalu nyalakan kembali aplikasi", "Error", JOptionPane.ERROR_MESSAGE);
            LOG.error(e);            
        }
    }
}
