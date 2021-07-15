/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.service.BarangService;
import aplikasi.kulakan.service.impl.BarangServiceImpl;
import aplikasi.kulakan.view.PilihBarang;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PilihBarangController {
    private final BarangService barangService = new BarangServiceImpl();
    private final PilihBarang pilihBarang;
    private final AddNewKulakanController kulakanPage;
    private final ReturPembelianController returPembelian;
    private final PenjualanController penjualanPage;
    private List<Barang> listBarang;
    
    public PilihBarangController(AddNewKulakanController kulakanPage){
        this.kulakanPage = kulakanPage;
        pilihBarang = new PilihBarang(this.kulakanPage.getKulakanPageController().getParent().getParent(), true);
        pilihBarang.getjButton1().addActionListener(this::searchButton);
        pilihBarang.getPilihButton().addActionListener(this::pilihButtonAction);
        penjualanPage = null;
        returPembelian = null;
    }
    
    public PilihBarangController(PenjualanController penjualanPage){
        this.penjualanPage = penjualanPage;
        pilihBarang = new PilihBarang(this.penjualanPage.getParent().getParent(), true);
        pilihBarang.getjButton1().addActionListener(this::searchButton);
        pilihBarang.getPilihButton().addActionListener(this::pilihButtonAction);
        kulakanPage = null;
        returPembelian = null;
    }
    public PilihBarangController(ReturPembelianController returPembelian){
        this.returPembelian = returPembelian;
        pilihBarang = new PilihBarang(this.returPembelian.getParent().getParent(), true);
        pilihBarang.getjButton1().addActionListener(this::searchButton);
        pilihBarang.getPilihButton().addActionListener(this::pilihButtonAction);
        kulakanPage = null;
        penjualanPage = null;
    }
    
    public PilihBarang chooseProduct(){
        this.getAllDataBarang();
        viewDataOnTable();
        return pilihBarang;
    }
    public List<Barang> getAllDataBarang(){
        listBarang = barangService.getAllData();
        return listBarang;
    }
    public void viewDataOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        for (Barang barang : listBarang) {
            Object[] obj = new Object[2];
            obj[0] = barang.getKodeBarang();
            obj[1] = barang.getNamaBarang();
            model.addRow(obj);
        }
        pilihBarang.getViewTable().setModel(model);
        pilihBarang.getViewTable().validate();
    }
    
    public void pilihButtonAction(java.awt.event.ActionEvent awt){
        if(kulakanPage == null && returPembelian == null){
            if(!penjualanPage.isRetur()){
                penjualanPage.setBarang(listBarang.get(pilihBarang.getViewTable().getSelectedRow()));
                pilihBarang.dispose();
            }else{
               penjualanPage.setBarangRetur(listBarang.get(pilihBarang.getViewTable().getSelectedRow()));
               pilihBarang.dispose(); 
            }
            
        }else if(returPembelian == null && penjualanPage == null){
            kulakanPage.setBarang(listBarang.get(pilihBarang.getViewTable().getSelectedRow()));
            pilihBarang.dispose();
        }else{
            returPembelian.setBarangRetur(listBarang.get(pilihBarang.getViewTable().getSelectedRow()));
            pilihBarang.dispose();
        }
       
    }
    public void searchButton(java.awt.event.ActionEvent e){
       this.searchByName(pilihBarang.getNamaBarangField().getText());
       viewDataOnTable();
    }
    public List<Barang> searchByName(String name){
        listBarang = barangService.findByName(name);
        return listBarang;
    }
}
