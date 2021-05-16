/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.model.ReturPenjualan;
import aplikasi.kulakan.service.PenjualanService;
import aplikasi.kulakan.service.impl.PenjualanServiceImpl;
import aplikasi.kulakan.service.impl.ReturPenjualanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.PenjualanPage_new;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import aplikasi.kulakan.service.ReturService;

/**
 *
 * @author USER
 */
public class PenjualanPage_newController {
    private final PenjualanPage_new penjualanPage;
    private List<Penjualan> listDataPenjualan;
    private final MainPageController mainPage;
    private final PenjualanService penjualanService;
    private final ReturService returService;
    private List<Penjualan> listPenjualan;
    private PilihBarangController pilihBarang;
    private Barang barang;
    private int hargaSatuan;
    private Double diskon, totalHarga;
    private final DateFormat dateFormat;
    private boolean retur;
    private List<ReturPenjualan> listRetur;
    
    public PenjualanPage_newController(MainPageController mainPage){
        this.mainPage = mainPage;
        //ini untuk data penjualan
        this.listDataPenjualan = new ArrayList<>();
        penjualanService = new PenjualanServiceImpl();
        this.returService = new ReturPenjualanServiceImpl();        
        this.penjualanPage = new PenjualanPage_new();
        this.penjualanPage.getFindButton().addActionListener(this::search);
        
        //ini untuk penjualan
        listPenjualan = new ArrayList<>();
        penjualanPage.getAddBarang().addActionListener(this::chooseProductAdd);
        penjualanPage.getAddBarangRetur().addActionListener(this::chooseProductRetur);
        penjualanPage.getTotalHargaField().addActionListener(this::addData);
        penjualanPage.getSaveButton().addActionListener(this::save);
        penjualanPage.getSaveReturButton().addActionListener(this::saveRetur);
        penjualanPage.getHargaSatuanField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                HargaSatuanFieldFocusGained(evt);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                HargaSatuanFieldFocusLost(evt);
            }
        });
        
        penjualanPage.getDiskonField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                diskonFieldFocusGained(evt);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                diskonFieldFocusLost(evt);
            }
        });
        penjualanPage.getTotalHargaField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                totalFieldFocusGained(evt);
            }
            
        });        
        penjualanPage.getAddButton().addActionListener(this::addData);
        diskon = null;
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        penjualanPage.getTanggalField().setDateFormat(dateFormat);
        penjualanPage.getTanggalRetur().setDateFormat(dateFormat);
        penjualanPage.getNamaBarangField().setEditable(false);
        
    }
    
    public MainPageController getParent(){
        return mainPage;
    }
    
    public void showAllData(){
        listDataPenjualan = penjualanService.getAllData();
    }
    
    public void showAllDataRetur(){
        List<Object> list =  returService.showAllRetur();
        listRetur = new ArrayList<>();
        for(Object obj : list){
            listRetur.add((ReturPenjualan)obj);
        }
    }
    
    public void viewOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nota");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Satuan");
        model.addColumn("Harga Satuan");
        model.addColumn("Diskon");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        
        listDataPenjualan.stream().map((penjualan) -> {
            Object[] obj = new Object[8];
            obj[0] = penjualan.getNoNota();
            obj[1] = penjualan.getBarang().getNamaBarang();
            obj[2] = penjualan.getQty();
            obj[3] = penjualan.getSatuan();
            obj[4] = FormatRupiah.convert(String.valueOf(penjualan.getHargaSatuan()));
            obj[5] = penjualan.getDiskon();
            obj[6] = FormatRupiah.convert(String.valueOf(penjualan.getTotalHarga()));
            obj[7] = FormatDate.format(penjualan.getTanggal());
            return obj;
        }).forEach((obj) -> {
            model.addRow(obj);
        });
        penjualanPage.getViewTable().setModel(model);
    }
    
    public PenjualanPage_new getPage(){
        this.showAllDataRetur();
        this.viewDataReturOnTable();
        penjualanPage.getBulanParam().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        penjualanPage.getTahunParam().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
        return penjualanPage;
    }
    
    public void search(java.awt.event.ActionEvent e){
        
        boolean searchByDate = false;
        if(penjualanPage.getBulanParam().getSelectedIndex()!=0 && penjualanPage.getTahunParam().getSelectedIndex()!=0){
            searchByDate = true;
        }
        
        if(searchByDate && !penjualanPage.getNamaBarangParam().getText().equals("")){
            if(!penjualanPage.getTanggalParam().getText().equals("")){
                String date = penjualanPage.getTanggalParam().getText()+"/"+penjualanPage.getBulanParam().getSelectedIndex()+
                        "/"+penjualanPage.getTahunParam().getSelectedItem().toString();
                listDataPenjualan = penjualanService.findByProductAndDate(penjualanPage.getNamaBarangParam().getText(), date);
                viewOnTable();
            }else{
                String month = String.valueOf(penjualanPage.getBulanParam().getSelectedIndex());
                String year =  penjualanPage.getTahunParam().getSelectedItem().toString();
                listDataPenjualan = penjualanService.findByProductAndMonthAndYear(penjualanPage.getNamaBarangParam().getText(), month, year);
                viewOnTable();
            }
            
        }else if(searchByDate && penjualanPage.getNamaBarangParam().getText().equals("")){
            if(!penjualanPage.getTanggalParam().getText().equals("")){
                String date = penjualanPage.getTanggalParam().getText()+"/"+penjualanPage.getBulanParam().getSelectedIndex()+
                        "/"+penjualanPage.getTahunParam().getSelectedItem().toString();
                listDataPenjualan = penjualanService.findByDate(date);
                viewOnTable();
            }else{
                String month = String.valueOf(penjualanPage.getBulanParam().getSelectedIndex());
                String year =  penjualanPage.getTahunParam().getSelectedItem().toString();
                listDataPenjualan = penjualanService.findByMonthAndYear(month, year);
                viewOnTable();
            }
        }else if(!searchByDate && !penjualanPage.getNamaBarangParam().getText().equals("")){
            listDataPenjualan = penjualanService.findByProduct(penjualanPage.getNamaBarangParam().getText());
            viewOnTable();
        }
    }
    
    //ini penjualan page
    public void viewDataOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Satuan");
        model.addColumn("Harga Satuan");
        model.addColumn("Diskon");
        model.addColumn("Total Harga");
        int number = 0;
        for(Penjualan jual : listPenjualan){
            number++;
            Object[] obj = new Object[7];
            obj[0] = number;
            obj[1] = jual.getBarang().getNamaBarang();
            obj[2] = jual.getQty();
            obj[3] = jual.getSatuan();
            obj[4] = FormatRupiah.convert(String.valueOf(jual.getHargaSatuan()));
            obj[5] = jual.getDiskon();
            obj[6] = FormatRupiah.convert(String.valueOf(jual.getTotalHarga()));
            model.addRow(obj);
        }
        penjualanPage.getViewTablePenjualan().setModel(model);
    }
    
    public void setBarang(Barang barang){
        this.barang = barang;
        penjualanPage.getNamaBarangField().setText(barang.getNamaBarang());
        String[] satuan;
        if(this.barang.getSatuanTerbesar().isEmpty()){
            satuan = new String[1];
            satuan[0] = barang.getSatuanTerkecil();
        }else{
            satuan = new String[2];
            satuan[0] = barang.getSatuanTerbesar();
            satuan[1] = barang.getSatuanTerkecil();
        }
        penjualanPage.getSatuanField().setModel(new DefaultComboBoxModel(satuan));
    }
    
    public void setBarangRetur(Barang barang){
        this.barang = barang;
        penjualanPage.getBarangReturField().setText(barang.getNamaBarang());
        String[] satuan;
        if(this.barang.getSatuanTerbesar().isEmpty()){
            satuan = new String[1];
            satuan[0] = barang.getSatuanTerkecil();
        }else{
            satuan = new String[2];
            satuan[0] = barang.getSatuanTerbesar();
            satuan[1] = barang.getSatuanTerkecil();
        }
        penjualanPage.getSatuanReturField().setModel(new DefaultComboBoxModel(satuan));
    }
    public void addData(java.awt.event.ActionEvent e){
        Penjualan penjualan = new Penjualan();
        penjualan.setBarang(barang);
        penjualan.setQty(Double.valueOf(penjualanPage.getJumlahField().getText()));
        penjualan.setSatuan(penjualanPage.getSatuanField().getSelectedItem().toString());
        penjualan.setTotalHarga(totalHarga.intValue());
        penjualan.setNoNota(penjualanPage.getNotaField().getText());
        if(diskon != null){
            if(diskon>0.0){
                penjualan.setDiskon(diskon);
            }
            
        }
        penjualan.setTanggal(penjualanPage.getTanggalField().getSelectedDate().getTime());
        penjualan.setHargaSatuan(hargaSatuan);
        listPenjualan.add(penjualan);
        viewDataOnTable();
        empty();
    }

    public void chooseProduct(){
        pilihBarang = new PilihBarangController(this);
        pilihBarang.chooseProduct().setVisible(true);
    }
    
    public void chooseProductAdd(java.awt.event.ActionEvent e){
        barang = new Barang();
        retur = false;
        chooseProduct();
    }
    public void chooseProductRetur(java.awt.event.ActionEvent e){
        barang = new Barang();
        retur = true;
        chooseProduct();
    }
    
    public boolean isRetur(){
        return retur;
    }

    public void save(java.awt.event.ActionEvent e){
        if(penjualanService.saveInBatch(listPenjualan)){
            listPenjualan = new ArrayList<>();
            viewDataOnTable();
            this.showAllData();
            viewOnTable();
        }
    }
    
    private void HargaSatuanFieldFocusGained(java.awt.event.FocusEvent evt) {                                             
        if(!penjualanPage.getHargaSatuanField().getText().isEmpty() || !penjualanPage.getHargaSatuanField().getText().equals("")){
            int numberOnly;
            numberOnly=  Integer.valueOf(penjualanPage.getHargaSatuanField().getText().replaceAll("\\D+", ""))/100;
            penjualanPage.getHargaSatuanField().setText(""+numberOnly);
        }
    }                                            

    private void HargaSatuanFieldFocusLost(java.awt.event.FocusEvent evt) {                                           
       if(!penjualanPage.getHargaSatuanField().getText().isEmpty() || !penjualanPage.getHargaSatuanField().getText().equals("")){
            hargaSatuan = Integer.valueOf(penjualanPage.getHargaSatuanField().getText());

            penjualanPage.getHargaSatuanField().setText(FormatRupiah.convert(penjualanPage.getHargaSatuanField().getText()));
        }
    }
    
    private void diskonFieldFocusLost(java.awt.event.FocusEvent evt) {                                           
       if(!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")){
            diskon = Double.valueOf(penjualanPage.getDiskonField().getText());
            penjualanPage.getDiskonField().setText(diskon+"%");
            double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
            double jumlahDiskon = (diskon/100)*(hargaSatuan*jumlahBarang);
            totalHarga = (hargaSatuan*jumlahBarang)-jumlahDiskon;
            int convert = totalHarga.intValue();
            penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
//            System.out.println(jumlahDiskon);
//            System.out.println(totalHarga);
            
        }else{
           double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
           totalHarga = hargaSatuan * jumlahBarang;
           int convert = totalHarga.intValue();
           penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
       }
    } 
     private void diskonFieldFocusGained(java.awt.event.FocusEvent evt) {                                             
        if(!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")){
            double numberOnly;
            numberOnly=  Double.valueOf(penjualanPage.getDiskonField().getText().replaceAll("%", ""));
            penjualanPage.getDiskonField().setText(""+numberOnly);
        }
    }
     private void totalFieldFocusGained(java.awt.event.FocusEvent evt) {                                             
        if(!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")){
          diskon = Double.valueOf(penjualanPage.getDiskonField().getText());
            penjualanPage.getDiskonField().setText(diskon+"%");
            double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
            double jumlahDiskon = (diskon/100)*(hargaSatuan*jumlahBarang);
            totalHarga = (hargaSatuan*jumlahBarang)-jumlahDiskon;
            int convert = totalHarga.intValue();
            penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));

            
        }else{
           double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
           totalHarga = hargaSatuan * jumlahBarang;
           int convert = totalHarga.intValue();
           penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
       }
    } 
     
     public void empty(){
        barang = new Barang();
        penjualanPage.getNamaBarangField().setText("");
        penjualanPage.getJumlahField().setText("");
        penjualanPage.getSatuanField().setSelectedIndex(0);
        penjualanPage.getTotalHargaField().setText("");
        totalHarga = 0.0;
        penjualanPage.getDiskonField().setText("");
        diskon = 0.0;
        penjualanPage.getHargaSatuanField().setText("");
        hargaSatuan = 0;
        penjualanPage.getBarangReturField().setText("");
        penjualanPage.getKeteranganField().setText("");
        penjualanPage.getJumlahReturField().setText("");
        penjualanPage.getNoNotaReturField().setText("");
       
     }
     
     //coding untuk retur
     public void saveRetur(java.awt.event.ActionEvent e){
         if(validationRetur()){
             if(penjualanService.checkNota(penjualanPage.getNoNotaReturField().getText())){
                ReturPenjualan returPenjualan = new ReturPenjualan();
                returPenjualan.setBarang(barang);
                returPenjualan.setKeterangan(penjualanPage.getKeteranganField().getText());
                returPenjualan.setSatuan(penjualanPage.getSatuanReturField().getSelectedItem().toString());
                returPenjualan.setNotaPenjualan(penjualanPage.getNoNotaReturField().getText());
                returPenjualan.setJumlah(Double.parseDouble(penjualanPage.getJumlahReturField().getText()));
                returPenjualan.setTanggalRetur(penjualanPage.getTanggalRetur().getSelectedDate().getTime());
                returService.addRetur(returPenjualan);  
                this.showAllDataRetur();
                this.viewDataReturOnTable();
                this.empty();
             }else{
               JOptionPane.showMessageDialog(null, "Nota Tidak Ditemukan ", "Warning", JOptionPane.ERROR_MESSAGE);
             }
         }
     }
     private boolean validationRetur(){
        if(this.barang==null){
            JOptionPane.showMessageDialog(penjualanPage,"Mohon Pilih Barang");
            return false;
        }else if(penjualanPage.getJumlahReturField().getText().isEmpty()){
            JOptionPane.showMessageDialog(penjualanPage,"Mohon Isi Jumlah Barang");
            return false;
        }else if(penjualanPage.getKeteranganField().getText().isEmpty()){
            JOptionPane.showMessageDialog(penjualanPage,"Mohon Isi Keterangan");
            return false;
        }else if(penjualanPage.getNoNotaReturField().getText().isEmpty()){
            JOptionPane.showMessageDialog(penjualanPage,"Mohon Isi Nomor Nota");
            return false;
        }
        return true;
    }
     
      public void viewDataReturOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nota Pembelian");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");
        
        for(ReturPenjualan retur: listRetur)
        {
            Object[] obj = new Object[4];
            obj[0] = retur.getNotaPenjualan();
            obj[1] = retur.getBarang().getNamaBarang();
            obj[2] = retur.getJumlah()+" "+retur.getSatuan();
            obj[3] = FormatDate.format(retur.getTanggalRetur());
            model.addRow(obj);
        }
        
        penjualanPage.getViewTableRetur().setModel(model);
    }
}
