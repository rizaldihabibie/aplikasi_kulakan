/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.PenjualanService;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.service.impl.PenjualanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.DataPenjualanPage;
import aplikasi.kulakan.view.Rekap;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class RekapControllerNew {
    private Rekap rekapKulakan;
    private KulakanService kulakanService;
    private List<Supplier> listSupplier;
    private List<Kulakan> listKulakan;
    private Double totalHarga = 0.0, jmlHarga;
    private int month, year, namaSupplier;
    private List<Penjualan> listPenjualan;
    private PenjualanService penjualanService;
    
    public RekapControllerNew(){
        kulakanService = new KulakanServiceImpl();
         penjualanService = new PenjualanServiceImpl();
    }
    
    public void viewDataOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Supplier");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Harga Netto");
        model.addColumn("Disc");
        model.addColumn("Total");
        
        for(Kulakan kulakan : listKulakan)
        {
            Object[] obj = new Object[6];
            obj[0] = kulakan.getSupplier().getNamaSupplier();
            obj[1] = kulakan.getBarang().getNamaBarang();
            obj[2] = kulakan.getJumlah()+" "+kulakan.getSatuan();
            obj[3] = kulakan.getHargaNetto();
            obj[4] = kulakan.getDiskon();
            if(kulakan.getDiskon()>0){
                jmlHarga = (kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100);
                totalHarga = totalHarga+jmlHarga;
                obj[5] = FormatRupiah.convert(String.valueOf(jmlHarga.intValue()));
            }else{
                jmlHarga = (kulakan.getHargaNetto()*kulakan.getJumlah());
                totalHarga = totalHarga+jmlHarga;
                 obj[5] = FormatRupiah.convert((String.valueOf(jmlHarga.intValue())));
            }
            model.addRow(obj);
        }
        
        rekapKulakan.getViewTable().setModel(model);
        rekapKulakan.getTotalBelanja().setText(FormatRupiah.convert(""+totalHarga.intValue()));
    }
    
    public Rekap getRekap(){
        rekapKulakan = new Rekap();
        listSupplier = kulakanService.getAllDataSupplier();
        showAllDataPenjualan();
        rekapKulakan.getSupplierParam().setModel(new DefaultComboBoxModel(kulakanService.supllierName()));
        rekapKulakan.getBulanParam().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        rekapKulakan.getTahunParam().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
        rekapKulakan.getBulanPenjualanParam().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        rekapKulakan.getTahunPenjualanParam().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
        rekapKulakan.getFindButton().addActionListener(this::search);
        this.rekapKulakan.getFindButtonPenjualan().addActionListener(this::searchPenjualan);
        viewOnTablePenjualan();
        
        return rekapKulakan;
    }
    
    public void search(java.awt.event.ActionEvent e){
        month = rekapKulakan.getBulanParam().getSelectedIndex();
        year = rekapKulakan.getTahunParam().getSelectedIndex();
        namaSupplier = rekapKulakan.getSupplierParam().getSelectedIndex();
        
        if(month != 0  && year !=0 &&  namaSupplier != 0){
            listKulakan = kulakanService.findByMonthYearAndSupplier(""+rekapKulakan.getBulanParam().getSelectedIndex(),rekapKulakan.getTahunParam().getSelectedItem().toString(), rekapKulakan.getSupplierParam().getSelectedItem().toString());
        }else if(month != 0  && year !=0 &&  namaSupplier == 0){
            listKulakan = kulakanService.findByMonthAndYear(""+rekapKulakan.getBulanParam().getSelectedIndex(), rekapKulakan.getTahunParam().getSelectedItem().toString());
        }else if(month == 0  && year ==0 &&  namaSupplier != 0){
            listKulakan = kulakanService.findyBySupplier(rekapKulakan.getSupplierParam().getSelectedItem().toString());
        }
        viewDataOnTable();
        totalHarga = 0.0;
    }
    
    public void searchPenjualan(java.awt.event.ActionEvent e){
        
        boolean searchByDate = false;
        if(rekapKulakan.getBulanPenjualanParam().getSelectedIndex()!=0 && rekapKulakan.getTahunPenjualanParam().getSelectedIndex()!=0){
            searchByDate = true;
        }
        
        if(searchByDate && !rekapKulakan.getNamaBarangParam().getText().equals("")){
                String month = String.valueOf(rekapKulakan.getBulanPenjualanParam().getSelectedIndex());
                String year =  rekapKulakan.getTahunPenjualanParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByProductAndMonthAndYear(rekapKulakan.getNamaBarangParam().getText(), month, year);
                viewOnTablePenjualan();
        }else if(searchByDate && rekapKulakan.getNamaBarangParam().getText().equals("")){
            
                String month = String.valueOf(rekapKulakan.getBulanPenjualanParam().getSelectedIndex());
                String year =  rekapKulakan.getTahunPenjualanParam().getSelectedItem().toString();
                listPenjualan = penjualanService.findByMonthAndYear(month, year);
                viewOnTablePenjualan();
        }else if(!searchByDate && !rekapKulakan.getNamaBarangParam().getText().equals("")){
            listPenjualan = penjualanService.findByProduct(rekapKulakan.getNamaBarangParam().getText());
            viewOnTablePenjualan();
        }
    }
    
    public void viewOnTablePenjualan(){
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
        rekapKulakan.getViewTablePenjualan().setModel(model);
    }
    public void showAllDataPenjualan(){
        listPenjualan = penjualanService.getAllData();
    }
}
