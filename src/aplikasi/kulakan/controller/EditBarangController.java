/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.service.BarangService;
import aplikasi.kulakan.view.EditBarang;

/**
 *
 * @author USER
 */
public class EditBarangController {
    private final EditBarang editBarang;
    private Barang barang;
    private final BarangService barangService;
    private final BarangController barangController;
    
    public EditBarangController(BarangController barangController){
        this.barangController = barangController;
        editBarang = new EditBarang(barangController.getParentController().getParent(), true);
        this.barangService = barangController.getService();
    }
    public void setData(Barang barang){
        this.barang = barang;
        editBarang.getKodeBarangField().setText(this.barang.getKodeBarang());
        editBarang.getNamaBarangField().setText(this.barang.getNamaBarang());
        editBarang.getDeskripsiField().setText(this.barang.getDeskripsiBarang());
        if(barang.getSatuanTerbesar().isEmpty()){
            editBarang.getLabelSatuanBesar().setText("--");
            editBarang.getJmlSatuanKecilField().setEditable(true);
            editBarang.getJmlSatuanKecilField().setText("");
            editBarang.getLabelSatuanKecil().setText("--");
            editBarang.getSatuanTerkecilField().setText(barang.getSatuanTerkecil());
        }else{
            editBarang.getJmlSatuanKecilField().setEditable(true);
            editBarang.getSatuanTerbesarField().setText(barang.getSatuanTerbesar());
            editBarang.getSatuanTerkecilField().setText(barang.getSatuanTerkecil());
            editBarang.getLabelSatuanBesar().setText("1 "+barang.getSatuanTerbesar()
                    +" terdiri dari ");
            editBarang.getLabelSatuanKecil().setText(barang.getSatuanTerkecil());
            editBarang.getJmlSatuanKecilField().setText(""+barang.getJmlSatuanTerkecil());

        }
        
    }
    public EditBarang editBarang(){
        editBarang.getSaveButton().addActionListener(this::saveButton);
        editBarang.getSatuanTerkecilField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                satuanTerkecilFocusLost(evt);
            }
        });
        editBarang.getSatuanTerbesarField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                satuanTerkecilFocusLost(evt);
            }
        });
        return editBarang;
    }
    public void saveButton(java.awt.event.ActionEvent e){
        this.barang.setDeskripsiBarang(editBarang.getDeskripsiField().getText());
        this.barang.setKodeBarang(editBarang.getKodeBarangField().getText());
        this.barang.setNamaBarang(editBarang.getNamaBarangField().getText());
        this.barang.setSatuanTerbesar(editBarang.getSatuanTerbesarField().getText());
        this.barang.setSatuanTerkecil(editBarang.getSatuanTerkecilField().getText());

        if(editBarang.getSatuanTerbesarField().getText().isEmpty()){
            barang.setJmlSatuanTerkecil(0);
        }else{
            barang.setJmlSatuanTerkecil(Integer.valueOf(editBarang.getJmlSatuanKecilField().getText()));

        }
        barangService.updateBarang(barang);
        barangController.getData();
        barangController.viewOnTable();
        editBarang.dispose();
    }
    private void satuanTerkecilFocusLost(java.awt.event.FocusEvent evt) {                                              
        if(editBarang.getSatuanTerbesarField().getText().isEmpty()){
            editBarang.getLabelSatuanBesar().setText("--");
            editBarang.getJmlSatuanKecilField().setEditable(false);
            editBarang.getJmlSatuanKecilField().setText("");
            editBarang.getLabelSatuanKecil().setText("--");
        }else{
            editBarang.getJmlSatuanKecilField().setEditable(true);
            editBarang.getLabelSatuanBesar().setText("1 "+editBarang.getSatuanTerbesarField().getText()
                    +" terdiri dari ");
            editBarang.getLabelSatuanKecil().setText(editBarang.getSatuanTerkecilField().getText());
        }
    }
}
