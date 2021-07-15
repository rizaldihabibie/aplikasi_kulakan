/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Stock;
import aplikasi.kulakan.service.StockService;
import aplikasi.kulakan.service.impl.StockServiceImpl;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.view.StockPage;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class StockController {
    
    private StockService stockService = new StockServiceImpl();
    private StockPage stockPage;
    private List<Stock> listStock;
    private MainPageController mainPage;
    
    public StockController(MainPageController mainPage){
        this.mainPage = mainPage;
        stockPage = new StockPage();
        this.stockPage.getSearchButton().addActionListener(this::find);
    }
    public MainPageController getParent(){
        return mainPage;
    }
    public void viewOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah Stock");
        model.addColumn("Satuan");
        model.addColumn("Update Terakhir");
        for(Stock stock : listStock){
            Object[] obj = new Object[5];
            obj[0] = stock.getBarang().getKodeBarang();
            obj[1] = stock.getBarang().getNamaBarang();
            obj[2] = stock.getJumlahStock();
            obj[3] = stock.getBarang().getSatuanTerkecil();
            obj[4] = FormatDate.format(stock.getTanggal());
            model.addRow(obj);
        }
        stockPage.getViewTable().setModel(model);
    }
    public void showAllData(){
       listStock = stockService.getAllLastStock();
    }
    
    public StockPage stockPage(){
        showAllData();
        viewOnTable();
        return stockPage;
    }
    
    public void find(java.awt.event.ActionEvent e){
        listStock = stockService.findByName(stockPage.getNamaBarangField().getText());
        viewOnTable();
    }
    
}
