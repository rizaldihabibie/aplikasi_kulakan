/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.service.BarangService;
import aplikasi.kulakan.service.impl.BarangServiceImpl;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.view.BarangView;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class BarangController {
    
    private BarangView barangView;
    private BarangService barangService = new BarangServiceImpl();
    private List<Barang> listBarang;
    private MainPageController mainPageController;
    
    public BarangView getBarangView(){
        barangView = new BarangView();
        barangView.getSaveButton().addActionListener(this::saveData);
        barangView.getExportButton().addActionListener(this::printButtonAction);
        barangView.getSearchButton().addActionListener(this::findData);
        barangView.getNamaParamField().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaParamFieldKeyReleased(evt);
            }
        });
        barangView.getSatuanTerkecilField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                satuanTerkecilFocusLost(evt);
            }
        });
        listBarang = barangService.getAllData();
        viewOnTable();
        return barangView;
    }
    
    public void saveData(java.awt.event.ActionEvent awt){
        Barang barang = new Barang();
        barang.setKodeBarang(barangView.getKodeBarangField().getText());
        barang.setNamaBarang(barangView.getNamaBarangField().getText());
        barang.setDeskripsiBarang(barangView.getDeskripsiField().getText());
        barang.setSatuanTerbesar(barangView.getSatuanTerbesarField().getText());
        barang.setSatuanTerkecil(barangView.getSatuanTerkecilField().getText());

        if(barangView.getSatuanTerbesarField().getText().isEmpty()){
            barang.setJmlSatuanTerkecil(0);
        }else{
            barang.setJmlSatuanTerkecil(Integer.valueOf(barangView.getJmlSatuanKecilField().getText()));

        }
        if(validation(barang)){
            if(barangService.saveData(barang)){
            empty();
            listBarang = barangService.getAllData();
            viewOnTable();
            barangView.getKodeBarangField().requestFocus();
            }
        }        
    }
    public boolean validation(Barang barang){
        boolean passed = false;
        if(barangService.findByCode(barang.getKodeBarang()) != null){
            JOptionPane.showMessageDialog(barangView, "Kode Barang Tidak Boleh Sama", "Warning", JOptionPane.INFORMATION_MESSAGE);
       
        }else{
            passed = true;
        }
        return passed;
    }
    public void viewOnTable(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Kode Barang");
        tableModel.addColumn("Nama Barang");
        tableModel.addColumn("Deskripsi Barang");
        tableModel.addColumn("Satuan Terbesar");
        tableModel.addColumn("Satuan Terkecil");
        tableModel.addColumn("Jumlah Satuan Terkecil");
        tableModel.addColumn("Ubah");
        tableModel.addColumn("Hapus");
        int index = 0;
        for(Barang barang : listBarang){
            Object[] obj = new Object[8];
            obj[0] = barang.getKodeBarang();
            obj[1] = barang.getNamaBarang();
            obj[2] = barang.getDeskripsiBarang();
            obj[3] = barang.getSatuanTerbesar();
            obj[4] = barang.getSatuanTerkecil();
            obj[5] = barang.getJmlSatuanTerkecil();
            obj[6] = "Edit";
            obj[7] = "Hapus";
            tableModel.addRow(obj);
        }
        barangView.getViewTable().setModel(tableModel);
        Action update = new AbstractAction()
        {
        @Override
        public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf(e.getActionCommand());
                openEditPage(listBarang.get(modelRow));
            }
        };
        
        Action delete  = new AbstractAction()
        {
        @Override
        public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf(e.getActionCommand());
                deleteData(listBarang.get(modelRow));
                viewOnTable();
            }
        };
        
        ButtonColumn buttonUpdate = new ButtonColumn(barangView.getViewTable(), update, 6);
        buttonUpdate.setMnemonic(KeyEvent.VK_D);
        
        ButtonColumn buttonDelete = new ButtonColumn(barangView.getViewTable(), delete, 7);
        buttonDelete.setMnemonic(KeyEvent.VK_D);
        
        barangView.getViewTable().validate();
    }
    
    public void empty(){
        barangView.getKodeBarangField().setText("");
        barangView.getNamaBarangField().setText("");
        barangView.getDeskripsiField().setText("");
        barangView.getSatuanTerbesarField().setText("");
        barangView.getSatuanTerkecilField().setText("");
        barangView.getJmlSatuanKecilField().setText("");
    }
    
    public void printButtonAction(java.awt.event.ActionEvent e){
        JFileChooser saveFile = new JFileChooser();
       int result = saveFile.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION){
         File file = saveFile.getSelectedFile();
         String path = String.valueOf(file+".xls");
         barangService.printToExcel(path,barangService.getAllData());
       }else{
         return;
       }
    }
    public void findData(java.awt.event.ActionEvent e){
        search();
    }
    public void search(){
         if(!barangView.getKodeParamField().getText().equals("")){
            if(!barangView.getNamaParamField().getText().equals("")){
                listBarang = barangService.findByCodeAndName(barangView.getKodeParamField().getText(), barangView.getNamaParamField().getText());
            }else{
                listBarang = new ArrayList<>();
                listBarang.add(barangService.findByCode(barangView.getKodeParamField().getText()));
            }
        }else{
            if(!barangView.getNamaParamField().getText().equals("")){
              listBarang = barangService.findByName(barangView.getNamaParamField().getText());  
            }else{
                listBarang = barangService.getAllData();
            }
        }
        viewOnTable();
    }
    private void namaParamFieldKeyReleased(java.awt.event.KeyEvent evt) {                                           
        search();
    } 
    public void setParent(MainPageController mainPage){
        this.mainPageController = mainPage;
    }
    public MainPageController getParentController(){
        return mainPageController;
    }
    public BarangService getService(){
        return barangService;
    }
    public void openEditPage(Barang barang){
        EditBarangController edit = new EditBarangController(this);
        edit.setData(barang);
        edit.editBarang().setVisible(true);
    }
    public void getData(){
        listBarang = barangService.getAllData();
    }
    public void deleteData(Barang barang){
        barangService.deleteData(barang);
        listBarang = barangService.getAllData();
    }
    
    private void satuanTerkecilFocusLost(java.awt.event.FocusEvent evt) {                                              
        if(barangView.getSatuanTerbesarField().getText().isEmpty()){
            barangView.getLabelSatuanBesar().setText("--");
            barangView.getJmlSatuanKecilField().setEditable(false);
            barangView.getJmlSatuanKecilField().setText("");
            barangView.getLabelSatuanKecil().setText("--");
        }else{
            barangView.getJmlSatuanKecilField().setEditable(true);
            barangView.getLabelSatuanBesar().setText("1 "+barangView.getSatuanTerbesarField().getText()
                    +" terdiri dari ");
            barangView.getLabelSatuanKecil().setText(barangView.getSatuanTerkecilField().getText());
        }
    }
}
