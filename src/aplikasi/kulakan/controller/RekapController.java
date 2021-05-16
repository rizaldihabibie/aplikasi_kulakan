/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.RekapKulakan;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class RekapController {
    private RekapKulakan rekapKulakan;
    private KulakanService kulakanService;
    private List<Supplier> listSupplier;
    private List<Kulakan> listKulakan;
    private Double totalHarga = 0.0, jmlHarga;
   
    
    public RekapController(KulakanPageController kulakanPage){
        kulakanService = kulakanPage.getService();
        rekapKulakan = new RekapKulakan(kulakanPage.getParent().getParent(), true);
        rekapKulakan.getTanggalField().setDateFormat( new SimpleDateFormat("dd MMMM yyyy"));
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
        rekapKulakan.getTotalField().setText("Total Belanja :"+FormatRupiah.convert(""+totalHarga.intValue()));
    }
    
    public RekapKulakan getRekap(){
        listSupplier = kulakanService.getAllDataSupplier();
        rekapKulakan.getSupplierField().setModel(new DefaultComboBoxModel(kulakanService.supllierName()));
        rekapKulakan.getSearchButton().addActionListener(this::search);
        return rekapKulakan;
    }
    
    public void search(java.awt.event.ActionEvent e){
        
        if(rekapKulakan.getSupplierField().getSelectedIndex() == 0){
            listKulakan = kulakanService.findByDate(rekapKulakan.getTanggalField().getSelectedDate().getTime());
        }else{
            listKulakan = kulakanService.findByDateAndSupplier(rekapKulakan.getTanggalField().getSelectedDate().getTime(), listSupplier.get(rekapKulakan.getSupplierField().getSelectedIndex()-1).getKodeSupplier());
        }
        viewDataOnTable();
    }
}
