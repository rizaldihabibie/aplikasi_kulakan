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
import aplikasi.kulakan.util.BarangGlazedFilterator;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.AddKulakan;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class AddNewKulakanController {
    
    private AddKulakan addKulakan = null;
    private final KulakanService kulakanService = new KulakanServiceImpl();
    private PilihBarangController barangController;
    private KulakanPageController kulakanPage = null;
    private Barang barang;
    private List<Supplier> listSupplier;
    private List<Kulakan> listKulakan;
    private List<Barang> listBarang;
    private ActionListener addBarangActionListener;
    private DateFormat dateFormat = null;
    private String[] satuan;
    private Double diskon;
    public static Logger LOG = LogManager.getLogger(AddNewKulakanController.class);
    private AutoCompleteSupport autoCompleteSupport;
    private SwingWorker<Void, Void> worker;
    private LoadingController loadingController;

    public AddNewKulakanController(KulakanPageController kulakanPage){
        LOG.info("Preparing add new kulakan page");
        try{
            this.kulakanPage = kulakanPage;
            addBarangActionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addButton(null);
                }
            };
            addKulakan = new AddKulakan(this.kulakanPage.getParent().getParent(), true);
            addKulakan.getSimpanButton().addActionListener(this::saveButtonAction);
            addKulakan.getBatalButton().addActionListener(this::cancelButton);
            addKulakan.getAddButton().addActionListener(this::addButton);
            addKulakan.getDiskonField().addActionListener(this::addButton);
            addKulakan.getSimpanButton().setMnemonic(KeyEvent.VK_S);
//            addKulakan.getSupplierField().addActionListener(this::chooseSupplier);
//            addKulakan.getTanggalKulakan().addCommitListener(new datechooser.events.CommitListener() {
//                @Override
//                public void onCommit(datechooser.events.CommitEvent evt) {
//                    tanggalKulakanOnCommit(evt);
//                }
//            });
//            addKulakan.getHargaNettoField().addKeyListener(new java.awt.event.KeyAdapter() {
//                @Override
//                public void keyReleased(java.awt.event.KeyEvent evt) {
//                    hargaNettoField(evt);
//                }
//            });
            addKulakan.getJumlahBarangField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addKulakan.getHargaNettoField().requestFocus();
                }
            });
            
            addKulakan.getNoNotaField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addKulakan.getSupplierField().requestFocus();
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
            
            addKulakan.getSupplierField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent evt) {
                     if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                         if(addKulakan.getSupplierField().getSelectedItem() != null){
                             chooseSupplier(null);
                         }
                     }
                }
                
            });
            addKulakan.getHargaNettoField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                        if(barang != null && barang.getTipe() == 0){
                            addKulakan.getDiskonField().requestFocus();
                        }
                }
            });
            JTextComponent editor = (JTextComponent) addKulakan.getNamaBarangField().getEditor().getEditorComponent();
            editor.addKeyListener(new KeyAdapter() {
               @Override
               public void keyReleased(KeyEvent evt) {
                    if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if(addKulakan.getNamaBarangField().getSelectedItem() != null){
                            try{
                                setBarang((Barang)addKulakan.getNamaBarangField().getSelectedItem());
                            }catch(Exception e){
                                LOG.error(e);
                            }
                        }
                    }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                        addKulakan.getSupplierField().requestFocus();
                    }
               }
            });
            editor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if(addKulakan.getNamaBarangField().getSelectedItem() != null){
                        setBarang((Barang)addKulakan.getNamaBarangField().getSelectedItem());
                    }
                }
                
            });
            dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            addKulakan.getTanggalKulakan().setDateFormat(dateFormat);
            addKulakan.getJumlahBarangField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        addKulakan.getNamaBarangField().requestFocus();
                    }
                }
            });
            
            addKulakan.getHargaNettoField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        addKulakan.getJumlahBarangField().requestFocus();
                    }
                }
            });
            addKulakan.getDiskonField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        addKulakan.getHargaNettoField().requestFocus();
                    }
                }
            });
            
            addKulakan.getSupplierField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        addKulakan.getNoNotaField().requestFocus();
                    }
                }
            });
            listKulakan = new ArrayList<>();
            LOG.info("Add new kulakan page loaded");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
    public AddKulakan getAddKulakan(){
        LOG.info("Load supplier");
        try{
            listSupplier = kulakanService.getAllSupplierData();
            addKulakan.getSupplierField().setModel(new DefaultComboBoxModel(kulakanService.suplierName()));
            addKulakan.setVisible(true);
        }catch(Exception e){
            LOG.error(e);
        }
        return addKulakan;
    }
    public void chooseSupplier(java.awt.event.ActionEvent e){
        if(addKulakan.getSupplierField().getSelectedIndex()>0){
            addKulakan.getNamaBarangField().requestFocus();
        }
        
    }
//    private void addProductActionButton(java.awt.event.ActionEvent awt){
//        barangController = new PilihBarangController(this);
//        barangController.chooseProduct().setVisible(true);
//    }
    public void setBarang(Barang barang){
        this.barang = barang;
        if(barang.getTipe() == 1){
            addKulakan.getLabelHarga().setText("Pembagian");
            addKulakan.getDiskonField().setEnabled(false);
            addKulakan.getHargaNettoField().addActionListener(addBarangActionListener);
        }else{
            addKulakan.getLabelHarga().setText("Harga Satuan");
            addKulakan.getDiskonField().setEnabled(true);
            addKulakan.getHargaNettoField().removeActionListener(addBarangActionListener);
            
        }
        addKulakan.getJumlahBarangField().requestFocus();
//        addKulakan.getBarangField().setText(this.barang.getNamaBarang());
        
//        if(this.barang.getSatuanTerbesar().isEmpty() || this.barang.getSatuanTerbesar().equals("")){
//            satuan = new String[1];
//            satuan[0] = barang.getSatuanTerkecil();
//        }else{
//            satuan = new String[2];
//            satuan[0] = barang.getSatuanTerkecil();
//            satuan[1] = barang.getSatuanTerbesar();
//        }
//        addKulakan.getSatuanField().setModel(new DefaultComboBoxModel(satuan));
    }
    public KulakanPageController getKulakanPageController(){
        return kulakanPage;
    }
    
    public void addButton(java.awt.event.ActionEvent e){
        LOG.info("Add data to list");
        if(validation()){
            Kulakan kulakan = new Kulakan();
            kulakan.setHargaNetto(Long.parseLong(addKulakan.getHargaNettoField().getText()));
            if(barang.getTipe() == 0){
                barang.setHargaTerakhir(barang.getHarga());
                barang.setHarga(Integer.parseInt(addKulakan.getHargaNettoField().getText()));
            }else{
                barang.setHargaTerakhir(barang.getJumlahPembagian());
                barang.setJumlahPembagian(Integer.parseInt(addKulakan.getHargaNettoField().getText()));                
            }
            kulakan.setBarang(barang);
            kulakan.setNoNota(addKulakan.getNoNotaField().getText());
//            kulakan.setSatuan(addKulakan.getSatuanField().getSelectedItem().toString());
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
            addKulakan.getNamaBarangField().setSelectedItem(null);
            addKulakan.getSupplierField().setSelectedIndex(-1);
            addKulakan.getHargaNettoField().setText("");
//            addKulakan.getSatuanField().setModel(new DefaultComboBoxModel());
            addKulakan.getJumlahBarangField().setText("");
            addKulakan.getDiskonField().setText("");
            addKulakan.getNoNotaField().requestFocus();
            LOG.info("Data added");
        }else{
            LOG.error("Data not valid");
        }
    }
    public void saveButtonAction(java.awt.event.ActionEvent e){
        
        if(listKulakan.size() != 0 ){
            LOG.info("Saving data kulakan");
            loadingController = new LoadingController(this.getKulakanPageController().getParent().getParent());
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    try{
                        kulakanService.saveInBatch(listKulakan);
                        kulakanPage.getData();
                        kulakanPage.viewOnTable();
                        empty();
                        viewOnTable();
                        addKulakan.dispose();
                    }catch(Exception ex){
                        LOG.error(ex);
                    }
                    LOG.info("Data saved");
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                }
            };
            worker.execute();
            loadingController.showLoading();
        }else{
            LOG.info("Empty list. do nothing");
        }
    }
    public void viewOnTable(){
        LOG.info("Load data kulakan to table");
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Supplier");
            model.addColumn("Tanggal");
            model.addColumn("Barang");
            model.addColumn("Jumlah");
    //        model.addColumn("Satuan");
            model.addColumn("Harga");
            model.addColumn("diskon");
            model.addColumn("Total");
            model.addColumn("Hapus");
            for(Kulakan kulakan : listKulakan){
                Object[] obj = new Object[8];
                obj[0] = kulakan.getSupplier().getNamaSupplier();
                obj[1] = FormatDate.format(kulakan.getTanggalKulakan());
                obj[2] = kulakan.getBarang().getNamaBarang();
                obj[3] = kulakan.getJumlah();
    //            obj[4] = kulakan.getSatuan();
                if(kulakan.getBarang().getTipe()==0){
                    obj[4] = FormatRupiah.simpleConvert(String.valueOf(kulakan.getHargaNetto()));
                }else{
                    obj[4] = kulakan.getHargaNetto()+"%";                    
                }
                obj[5] = kulakan.getDiskon();
                if(kulakan.getDiskon()>0){
                    obj[6] = FormatRupiah.simpleConvert(String.valueOf((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100)));
                }else{
                    obj[6] = FormatRupiah.simpleConvert(String.valueOf(kulakan.getHargaNetto()*((Double)kulakan.getJumlah()).intValue()));
                }
                obj[7] = "Hapus";
                model.addRow(obj);
            }
            addKulakan.getViewTable().setModel(model);
            Action delete  = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deleteData(listKulakan.get(modelRow));
                    viewOnTable();
                }
            };
            ButtonColumn buttonDelete = new ButtonColumn(addKulakan.getViewTable(), delete, 7);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
        }catch(Exception e){
            LOG.error(e);
        }
        
    }
    private void deleteData(Kulakan kulakan){
        LOG.info("delete data kulakan : "+kulakan.getBarang().getNamaBarang());
        try{
            listKulakan.remove(kulakan);
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public void setSearchData(){
        if(barangController == null){
            barangController = new PilihBarangController(this);
        }
        listBarang = barangController.getAllDataBarang();
//        penjualanPage.getNamaBarangField().setModel(new DefaultComboBoxModel(listBarang.toArray()));
//        AutoCompleteDecorator.decorate(penjualanPage.getNamaBarangField());
        EventList<Barang> barangs = new BasicEventList<Barang>();
        for (Barang barang : listBarang) {
            barangs.add(barang);
        }
        try{
            if(autoCompleteSupport != null && autoCompleteSupport.isInstalled()){
                autoCompleteSupport.uninstall();
            }
            autoCompleteSupport = AutoCompleteSupport.install(addKulakan.getNamaBarangField(), barangs, new BarangGlazedFilterator());
        }catch(IllegalArgumentException e){
            e.getMessage();
        }
    }
    public void empty(){
        barang = null;
        addKulakan.getNamaBarangField().setSelectedIndex(-1);
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
//    private void hargaNettoField(java.awt.event.KeyEvent evt) {
//        if(barang != null && barang.getTipe() == 0){
//            formatRupiah(addKulakan.getHargaNettoField(), addKulakan.getFormatHargaNetto());
//        }
//    }
    private boolean validation(){
        LOG.info("Validating data");
        if(addKulakan.getSupplierField().getSelectedIndex()==0){
            LOG.info("User not select supplier");
            JOptionPane.showMessageDialog(addKulakan,"Mohon Pilih Supplier");
            return false;
        }else if(barang==null){
            LOG.info("User not inputing product");
            JOptionPane.showMessageDialog(addKulakan,"Mohon Pilih Barang");
            return false;
        }else if(addKulakan.getJumlahBarangField().getText().isEmpty()){
            LOG.info("User not providing quantity");
            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Jumlah Barang");
            return false;
        }else if(addKulakan.getHargaNettoField().getText().isEmpty()){
            LOG.info("User not providing price");
            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Harga Barang");
            return false;
        }
//        else if(addKulakan.getNoNotaField().getText().isEmpty()){
//            JOptionPane.showMessageDialog(addKulakan,"Mohon Isi Nomor Nota");
//            return false;
//        }
        return true;
    }
    private void diskonFieldFocusGained(java.awt.event.FocusEvent evt) {
        try{
            if(!addKulakan.getDiskonField().getText().isEmpty() || !addKulakan.getDiskonField().getText().equals("")){
                double numberOnly;
                numberOnly=  Double.valueOf(addKulakan.getDiskonField().getText().replaceAll("%", ""));
                addKulakan.getDiskonField().setText(""+numberOnly);
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    private void diskonFieldFocusLost(java.awt.event.FocusEvent evt) {
        try{
            if(!addKulakan.getDiskonField().getText().isEmpty() || !addKulakan.getDiskonField().getText().equals("")){
                 diskon = Double.valueOf(addKulakan.getDiskonField().getText());
                 addKulakan.getDiskonField().setText(diskon+"%");
             }else{
                diskon = 0.0;
                addKulakan.getDiskonField().setText(diskon+"%");
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
}
