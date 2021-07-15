/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.model.ReturPembelian;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.ReturService;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.service.impl.ReturPembelianServiceImpl;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.view.ReturKulakan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class ReturPembelianController {
    private final ReturService returService;
    private ReturKulakan returKulakanPage;
    private Barang barang;
    private MainPageController mainPage;
    private final KulakanService kulakanService;
    private PilihBarangController pilihBarang;
    private List<ReturPembelian> listRetur;
    
    public ReturPembelianController(MainPageController mainPage){
       this.mainPage = mainPage;
       returService = new ReturPembelianServiceImpl();
       kulakanService = new KulakanServiceImpl();
       returKulakanPage = new ReturKulakan();
       returKulakanPage.getReturButton().addActionListener(this::saveRetur);
       returKulakanPage.getChooseBarang().addActionListener(this::chooseProduct);
    }
    
    public ReturKulakan getPage(){
       this.showAllData();
       this.viewDataOnTable();
       return returKulakanPage;
    }
    
    public MainPageController getParent(){
        return mainPage;
    }
    
    //coding untuk retur
     public void saveRetur(java.awt.event.ActionEvent e){
         if(validation()){
             if(kulakanService.checkNota(returKulakanPage.getNotaField().getText())){
                ReturPembelian returPembelian = new ReturPembelian();
                returPembelian.setBarang(barang);
                returPembelian.setKeterangan(returKulakanPage.getKeteranganField().getText());
                returPembelian.setSatuan(returKulakanPage.getSatuanReturField().getSelectedItem().toString());
                returPembelian.setNotaPembelian(returKulakanPage.getNotaField().getText());
                returPembelian.setJumlah(Double.parseDouble(returKulakanPage.getJumlahField().getText()));
                returPembelian.setTanggalRetur(returKulakanPage.getTanggalRetur().getSelectedDate().getTime());
                returService.addRetur(returPembelian);            
             }else{
               JOptionPane.showMessageDialog(null, "Nota Tidak Ditemukan ", "Warning", JOptionPane.ERROR_MESSAGE);
             }
         }
     }
     public void chooseProduct(java.awt.event.ActionEvent e){
        pilihBarang = new PilihBarangController(this);
        pilihBarang.chooseProduct().setVisible(true);
    }
    public void setBarangRetur(Barang barang){
        this.barang = barang;
        returKulakanPage.getNamaBarangReturField().setText(this.barang.getNamaBarang());
        String[] satuan;
        if(this.barang.getSatuanTerbesar().isEmpty()){
            satuan = new String[1];
            satuan[0] = barang.getSatuanTerkecil();
        }else{
            satuan = new String[2];
            satuan[0] = barang.getSatuanTerbesar();
            satuan[1] = barang.getSatuanTerkecil();
        }
        returKulakanPage.getSatuanReturField().setModel(new DefaultComboBoxModel(satuan));
    }
    private boolean validation(){
        if(barang==null){
            JOptionPane.showMessageDialog(returKulakanPage,"Mohon Pilih Barang");
            return false;
        }else if(returKulakanPage.getJumlahField().getText().isEmpty()){
            JOptionPane.showMessageDialog(returKulakanPage,"Mohon Isi Jumlah Barang");
            return false;
        }else if(returKulakanPage.getKeteranganField().getText().isEmpty()){
            JOptionPane.showMessageDialog(returKulakanPage,"Mohon Isi Keterangan");
            return false;
        }else if(returKulakanPage.getNotaField().getText().isEmpty()){
            JOptionPane.showMessageDialog(returKulakanPage,"Mohon Isi Nomor Nota");
            return false;
        }
        
        return true;
    }
    public void showAllData(){
        List<Object> list =  returService.showAllRetur();
        listRetur = new ArrayList<>();
        for(Object obj : list){
            listRetur.add((ReturPembelian)obj);
        }
    }
    public void viewDataOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nota Pembelian");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");
        
        for(ReturPembelian retur: listRetur)
        {
            Object[] obj = new Object[4];
            obj[0] = retur.getNotaPembelian();
            obj[1] = retur.getBarang().getNamaBarang();
            obj[2] = retur.getJumlah()+" "+retur.getSatuan();
            obj[3] = FormatDate.format(retur.getTanggalRetur());
            model.addRow(obj);
        }
        
        returKulakanPage.getViewTable().setModel(model);
    }
}
