/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.AddKulakan;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class AddNewKulakanController {
    
    private final AddKulakan addKulakan;
    private final KulakanService kulakanService = new KulakanServiceImpl();
    private PilihBarangController barangController;
    private final KulakanPageController kulakanPage;
    private Barang barang;
    private List<Supplier> listSupplier;
    private List<Kulakan> listKulakan;
    private final DateFormat dateFormat;
    private String[] satuan;
    private Double diskon;
    
    public AddNewKulakanController(KulakanPageController kulakanPage){
        this.kulakanPage = kulakanPage;
        addKulakan = new AddKulakan(this.kulakanPage.getParent().getParent(), true);
        addKulakan.getChooseProductButton().addActionListener(this::addProductActionButton);
        addKulakan.getSimpanButton().addActionListener(this::saveButtonAction);
        addKulakan.getBatalButton().addActionListener(this::cancelButton);
        addKulakan.getAddButton().addActionListener(this::addButton);
        addKulakan.getSupplierField().addActionListener(this::chooseSupplier);
        addKulakan.getTanggalKulakan().addCommitListener(new datechooser.events.CommitListener() {
            @Override
            public void onCommit(datechooser.events.CommitEvent evt) {
                tanggalKulakanOnCommit(evt);
            }
        });
        addKulakan.getHargaNettoField().addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hargaNettoField(evt);
            }
        });
        addKulakan.getDiskonField().addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                diskonFieldFocusGained(evt);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                diskonFieldFocusLost(evt);
            }
        });
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        addKulakan.getTanggalKulakan().setDateFormat(dateFormat);
        addKulakan.getTanggalBeli().setText(FormatDate.format(addKulakan.getTanggalKulakan().getSelectedDate().getTime()));
        listKulakan = new ArrayList<>();
        addKulakan.getBarangField().setEditable(false);
    }
    
    public AddKulakan getAddKulakan(){
        listSupplier = kulakanService.getAllSupplierData();
        addKulakan.getSupplierField().setModel(new DefaultComboBoxModel(kulakanService.supllierName()));
        addKulakan.setVisible(true);
        return addKulakan;
    }
    public void chooseSupplier(java.awt.event.ActionEvent e){
        if(addKulakan.getSupplierField().getSelectedIndex()==0){
            addKulakan.getSupplierLabel().setText("-");
        }else{
            addKulakan.getSupplierLabel().setText(addKulakan.getSupplierField().getSelectedItem().toString());
        }
    }
    private void tanggalKulakanOnCommit(datechooser.events.CommitEvent evt) {                                        
        addKulakan.getTanggalBeli().setText(FormatDate.format(addKulakan.getTanggalKulakan().getSelectedDate().getTime()));
    }
    private void addProductActionButton(java.awt.event.ActionEvent awt){
        barangController = new PilihBarangController(this);
        barangController.chooseProduct().setVisible(true);
    }
    public void setBarang(Barang barang){
        this.barang = barang;
        addKulakan.getBarangField().setText(this.barang.getNamaBarang());
        
        if(this.barang.getSatuanTerbesar().isEmpty() || this.barang.getSatuanTerbesar().equals("")){
            satuan = new String[1];
            satuan[0] = barang.getSatuanTerkecil();
        }else{
            satuan = new String[2];
            satuan[0] = barang.getSatuanTerkecil();
            satuan[1] = barang.getSatuanTerbesar();
        }
        addKulakan.getSatuanField().setModel(new DefaultComboBoxModel(satuan));
    }
    public KulakanPageController getKulakanPageController(){
        return kulakanPage;
    }
    
    public void addButton(java.awt.event.ActionEvent e){
        if(validation()){
            Kulakan kulakan = new Kulakan();
            kulakan.setBarang(barang);
            kulakan.setNoNota(addKulakan.getNoNotaField().getText());
            kulakan.setHargaNetto(Long.parseLong(addKulakan.getHargaNettoField().getText()));
            kulakan.setSatuan(addKulakan.getSatuanField().getSelectedItem().toString());
            kulakan.setJumlah(Double.parseDouble(addKulakan.getJumlahBarangField().getText()));
            if(diskon == null ){
                kulakan.setDiskon(0);
            }else{
                kulakan.setDiskon(diskon);
            }
            
            kulakan.setSupplier(listSupplier.get((addKulakan.getSupplierField().getSelectedIndex())-1));
            kulakan.setTanggalKulakan(addKulakan.getTanggalKulakan().getSelectedDate().getTime());
            listKulakan.add(kulakan);
            viewOnTable();
            barang = null;
            addKulakan.getBarangField().setText("");
            addKulakan.getHargaNettoField().setText("");
            addKulakan.getSatuanField().setModel(new DefaultComboBoxModel());
            addKulakan.getFormatHargaNetto().setText("Rp. ");
            addKulakan.getJumlahBarangField().setText("");
            addKulakan.getDiskonField().setText("");
        }
    }
    public void saveButtonAction(java.awt.event.ActionEvent e){
        if(listKulakan.size() != 0 ){
            kulakanService.saveInBatch(listKulakan);
            kulakanPage.getData();
            kulakanPage.viewOnTable();
            empty();
            viewOnTable();
        }
    }
    public void viewOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Supplier");
        model.addColumn("Tanggal");
        model.addColumn("Barang");
        model.addColumn("Jumlah");
        model.addColumn("Satuan");
        model.addColumn("Harga Netto");
        model.addColumn("diskon");
        model.addColumn("Total");
        model.addColumn("Hapus");
        for(Kulakan kulakan : listKulakan){
            Object[] obj = new Object[9];
            obj[0] = kulakan.getSupplier().getNamaSupplier();
            obj[2] = kulakan.getBarang().getNamaBarang();
            obj[3] = kulakan.getJumlah();
            obj[4] = kulakan.getSatuan();
            obj[5] = FormatRupiah.convert(String.valueOf(kulakan.getHargaNetto()));
            obj[1] = FormatDate.format(kulakan.getTanggalKulakan());
            obj[6] = kulakan.getDiskon();
            if(kulakan.getDiskon()>0){
                obj[7] = FormatRupiah.convert(String.valueOf((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100)));
            }else{
                 obj[7] = FormatRupiah.convert((String.valueOf(kulakan.getHargaNetto()*kulakan.getJumlah())));
            }
            obj[8] = "Hapus";
            model.addRow(obj);
        }
        addKulakan.getViewTable().setModel(model);
        Action delete  = new AbstractAction()
        {
        @Override
        public void actionPerformed(ActionEvent e)
            {
                int modelRow = Integer.valueOf(e.getActionCommand());
                viewOnTable();
            }
        };
        
        ButtonColumn buttonDelete = new ButtonColumn(addKulakan.getViewTable(), delete, 6);
        buttonDelete.setMnemonic(KeyEvent.VK_D);
        
    }
    public void empty(){
        barang = null;
        addKulakan.getFormatHargaNetto().setText("");
        addKulakan.getBarangField().setText("");
        addKulakan.getHargaNettoField().setText("");
        addKulakan.getSatuanField().setModel(new DefaultComboBoxModel());
        addKulakan.getJumlahBarangField().setText("");
        addKulakan.getNoNotaField().setText("");
        listKulakan = new ArrayList<>();
    }
    public void cancelButton(java.awt.event.ActionEvent e){
        addKulakan.dispose();
    }
    
    public void formatRupiah(JTextField textField, JLabel rupiahFormat){
        rupiahFormat.setText(FormatRupiah.convert(textField.getText()));
    }
    private void hargaNettoField(java.awt.event.KeyEvent evt) {
        formatRupiah(addKulakan.getHargaNettoField(), addKulakan.getFormatHargaNetto());
    }
    private boolean validation(){
        if(addKulakan.getSupplierField().getSelectedIndex()==0){
            JOptionPane.showMessageDialog(addKulakan,"Mohon Pilih Supplier");
            return false;
        }else if(barang==null){
            JOptionPane.showMessageDialog(addKulakan,"Mohon Pilih Barang");
            return false;
        }else if(addKulakan.getJumlahBarangField().getText().isEmpty()){
            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Jumlah Barang");
            return false;
        }else if(addKulakan.getHargaNettoField().getText().isEmpty()){
            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Harga Barang");
            return false;
        }else if(addKulakan.getNoNotaField().getText().isEmpty()){
            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Nomor Nota");
            return false;
        }
        
        return true;
    }
    private void diskonFieldFocusGained(java.awt.event.FocusEvent evt) {                                             
        if(!addKulakan.getDiskonField().getText().isEmpty() || !addKulakan.getDiskonField().getText().equals("")){
            double numberOnly;
            numberOnly=  Double.valueOf(addKulakan.getDiskonField().getText().replaceAll("%", ""));
            addKulakan.getDiskonField().setText(""+numberOnly);
        }
    }
    private void diskonFieldFocusLost(java.awt.event.FocusEvent evt) {                                           
       if(!addKulakan.getDiskonField().getText().isEmpty() || !addKulakan.getDiskonField().getText().equals("")){
            diskon = Double.valueOf(addKulakan.getDiskonField().getText());
            addKulakan.getDiskonField().setText(diskon+"%");
            
        }else{
           diskon = 0.0;
           addKulakan.getDiskonField().setText(diskon+"%");
       }
    }
    
}
