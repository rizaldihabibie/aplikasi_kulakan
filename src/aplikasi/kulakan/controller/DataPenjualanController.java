/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.service.PenjualanService;
import aplikasi.kulakan.service.impl.PenjualanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.DataPenjualanPage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class DataPenjualanController {
    
    private DataPenjualanPage dataPenjualan;
    private List<Penjualan> listPenjualan;
    private MainPageController mainPage;
    private PenjualanService penjualanService;
    
    public DataPenjualanController(MainPageController mainPage){
        this.mainPage = mainPage;
        this.listPenjualan = new ArrayList<>();
        penjualanService = new PenjualanServiceImpl();
        this.dataPenjualan = new DataPenjualanPage();
        this.dataPenjualan.getFindButton().addActionListener(this::search);

    }
    
    public MainPageController getParent(){
        return mainPage;
    }
    
    public void showAllData(){
        listPenjualan = penjualanService.getAllData();
    }
    
    public void viewOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Penjualan");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Satuan");
        model.addColumn("Harga Satuan");
        model.addColumn("Diskon");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        
        for(Penjualan penjualan : listPenjualan){
            Object[] obj = new Object[8];
            obj[0] = penjualan.getIdPenjualan();
            obj[1] = penjualan.getBarang().getNamaBarang();
            obj[2] = penjualan.getQty();
            obj[3] = penjualan.getSatuan();
            obj[4] = FormatRupiah.convert(String.valueOf(penjualan.getHargaSatuan()));
            obj[5] = penjualan.getDiskon();
            obj[6] = FormatRupiah.convert(String.valueOf(penjualan.getTotalHarga()));
            obj[7] = FormatDate.format(penjualan.getTanggal());
            model.addRow(obj);
        }
        dataPenjualan.getViewTable().setModel(model);
    }
    
    public DataPenjualanPage getPage(){
        dataPenjualan.getBulanParam().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        dataPenjualan.getTahunParam().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
        return dataPenjualan;
    }
    
    public void search(java.awt.event.ActionEvent e){
        
        boolean searchByDate = false;
        if(dataPenjualan.getBulanParam().getSelectedIndex()!=0 && dataPenjualan.getTahunParam().getSelectedIndex()!=0){
            searchByDate = true;
        }
        
        if(searchByDate && !dataPenjualan.getNamaBarangParam().getText().equals("")){
            if(!dataPenjualan.getTanggalParam().getText().equals("")){
                String date = dataPenjualan.getTanggalParam().getText()+"/"+dataPenjualan.getBulanParam().getSelectedIndex()+
                        "/"+dataPenjualan.getTahunParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByProductAndDate(dataPenjualan.getNamaBarangParam().getText(), date);
                viewOnTable();
            }else{
                String month = String.valueOf(dataPenjualan.getBulanParam().getSelectedIndex());
                String year =  dataPenjualan.getTahunParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByProductAndMonthAndYear(dataPenjualan.getNamaBarangParam().getText(), month, year);
                viewOnTable();
            }
            
        }else if(searchByDate && dataPenjualan.getNamaBarangParam().getText().equals("")){
            if(!dataPenjualan.getTanggalParam().getText().equals("")){
                String date = dataPenjualan.getTanggalParam().getText()+"/"+dataPenjualan.getBulanParam().getSelectedIndex()+
                        "/"+dataPenjualan.getTahunParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByDate(date);
                viewOnTable();
            }else{
                String month = String.valueOf(dataPenjualan.getBulanParam().getSelectedIndex());
                String year =  dataPenjualan.getTahunParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByMonthAndYear(month, year);
                viewOnTable();
            }
        }else if(!searchByDate && !dataPenjualan.getNamaBarangParam().getText().equals("")){
            listPenjualan = penjualanService.findByProduct(dataPenjualan.getNamaBarangParam().getText());
            viewOnTable();
        }
    }
}
