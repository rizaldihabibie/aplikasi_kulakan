/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.service.BarangService;
import aplikasi.kulakan.view.EditBarang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class EditBarangController {
    private EditBarang editBarang;
    private Barang barang;
    private BarangService barangService;
    private BarangController barangController;
    private static Logger LOG = LogManager.getLogger(EditBarangController.class);
    
    public EditBarangController(BarangController barangController){
        try{
            LOG.info("Preparing edit barang page");
            this.barangController = barangController;
            editBarang = new EditBarang(barangController.getParentController().getParent(), true);
            this.barangService = barangController.getService();
            editBarang.getTipeBarang().addActionListener(this::selectedType);

            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public void selectedType(ActionEvent e) {
        if(editBarang.getTipeBarang().getSelectedIndex() == 1){
            editBarang.getPersenPembagian().setEnabled(true);
            editBarang.getPersenPembagian().requestFocus();
            editBarang.getPersenPembagian().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    editBarang.getDeskripsiField().requestFocus();
                }
            });
        }else{
            editBarang.getPersenPembagian().setEnabled(false);
            editBarang.getDeskripsiField().requestFocus();
        }
    }
    public void setData(Barang barang){
        LOG.info("Preparing data barang");
        try{
            this.barang = barang;
            editBarang.getKodeBarangField().setText(this.barang.getKodeBarang());
            editBarang.getNamaBarangField().setText(this.barang.getNamaBarang());
            editBarang.getDeskripsiField().setText(this.barang.getDeskripsiBarang());
//        if(barang.getSatuanTerbesar().isEmpty()){
//            editBarang.getLabelSatuanBesar().setText("--");
//            editBarang.getJmlSatuanKecilField().setEditable(true);
//            editBarang.getJmlSatuanKecilField().setText("");
//            editBarang.getLabelSatuanKecil().setText("--");
//            editBarang.getSatuanTerkecilField().setText(barang.getSatuanTerkecil());
//        }else{
//            editBarang.getJmlSatuanKecilField().setEditable(true);
            editBarang.getSatuanTerbesarField().setText(barang.getSatuanTerbesar());
//            editBarang.getSatuanTerkecilField().setText(barang.getSatuanTerkecil());
//            editBarang.getLabelSatuanBesar().setText("1 "+barang.getSatuanTerbesar()
//                    +" terdiri dari ");
//            editBarang.getLabelSatuanKecil().setText(barang.getSatuanTerkecil());
//            editBarang.getJmlSatuanKecilField().setText(""+barang.getJmlSatuanTerkecil());
//
//        }
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
        
    }
    public EditBarang editBarang(){
        editBarang.getSaveButton().addActionListener(this::saveButton);
//        editBarang.getSatuanTerkecilField().addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                satuanTerkecilFocusLost(evt);
//            }
//        });
//        editBarang.getSatuanTerbesarField().addFocusListener(new java.awt.event.FocusAdapter() {
//            @Override
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                satuanTerkecilFocusLost(evt);
//            }
//        });
        return editBarang;
    }
    public void saveButton(java.awt.event.ActionEvent e){
        LOG.info("Updating barang");
        try{
            this.barang.setDeskripsiBarang(editBarang.getDeskripsiField().getText());
            this.barang.setKodeBarang(editBarang.getKodeBarangField().getText());
            this.barang.setNamaBarang(editBarang.getNamaBarangField().getText());
            this.barang.setSatuanTerbesar(editBarang.getSatuanTerbesarField().getText());
            int selectedType= editBarang.getTipeBarang().getSelectedIndex();
            barang.setTipe(selectedType);
            if(selectedType == 1 && editBarang.getPersenPembagian().getText() == null){
                JOptionPane.showMessageDialog(editBarang, "Mohon isi jumlah pembagian dalam persen !", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(selectedType == 1){
                    barang.setJumlahPembagian(Integer.valueOf(editBarang.getPersenPembagian().getText().replace("%", "")));
                }
                if(barangService.updateData(barang)){
                    JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Data Gagal Dihapus", "Error !", JOptionPane.INFORMATION_MESSAGE);
                }
                barangController.getData();
                barangController.viewOnTable();
                editBarang.dispose();
                LOG.info("Update success");
            }
//        this.barang.setSatuanTerkecil(editBarang.getSatuanTerkecilField().getText());

//        if(editBarang.getSatuanTerbesarField().getText().isEmpty()){
//            barang.setJmlSatuanTerkecil(0);
//        }else{
//            barang.setJmlSatuanTerkecil(Integer.valueOf(editBarang.getJmlSatuanKecilField().getText()));
//
//        }
        }catch(Exception ex){
            LOG.error(ex);
        }
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
