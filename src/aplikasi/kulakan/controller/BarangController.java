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
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.BarangView;
import aplikasi.kulakan.view.PreviewImportBarang;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author USER
 */
public class BarangController implements BasicController {
    
    private BarangView barangView;
    private PreviewImportBarang previewImportBarang;
    private BarangService barangService;
    private List<Barang> listBarang, listImportBarang, listSearchImportBarang;
    private final MainPageController mainPageController;
    private String searchImportedNamaBarang;
    private SwingWorker<Void, Void> worker;
    private LoadingController loadingController;
    private boolean success;
    private static Logger LOG = LogManager.getLogger(BarangController.class);
    
    public BarangController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
    }
    

    @Override
    public void init() {
        try{
            LOG.info("Preparing barang page");
            barangView = new BarangView();
            barangService = new BarangServiceImpl();
            barangView.getSaveButton().addActionListener(this::saveData);
            barangView.getExportButton().addActionListener(this::printButtonAction);
            barangView.getSearchButton().addActionListener(this::findData);
            barangView.getKodeParamField().addActionListener(this::findData);
            barangView.getImportButton().addActionListener(this::chooseFile);
            
            barangView.getTipeBarang().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode() == KeyEvent.VK_ENTER){
                        if(barangView.getTipeBarang().getSelectedIndex()>0){
                            selectedType(null);
                        }
                    }
                }
                
            });
            barangView.getKodeBarangField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    barangView.getNamaBarangField().requestFocus();
                }
            });
            barangView.getPersenPembagian().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    barangView.getSatuanTerbesarField().requestFocus();
                }
            });
            barangView.getNamaBarangField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    barangView.getTipeBarang().requestFocus();
                }
            });
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
            LOG.info("Done");
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        barangView.setSize(mainPageController.getParent().getMenuLayer().getSize());
        barangView.setVisible(true);
        listBarang = barangService.getAllData();
        viewOnTable();
    }

    @Override
    public void stop() {
        barangView.setVisible(false);
        barangService = null;
        barangView = null;
    }

    @Override
    public JPanel getPage() {
        return barangView;
    }
    
    public void saveData(java.awt.event.ActionEvent awt){
        try{
            LOG.info("Save barang");
            Barang barang = new Barang();
            barang.setKodeBarang(barangView.getKodeBarangField().getText());
            barang.setNamaBarang(barangView.getNamaBarangField().getText());
            barang.setDeskripsiBarang(barangView.getDeskripsiField().getText());
            barang.setSatuanTerbesar(barangView.getSatuanTerbesarField().getText());
            int selectedType=barangView.getTipeBarang().getSelectedIndex()-1;
            barang.setTipe(selectedType);
            if(selectedType == 1 && barangView.getPersenPembagian().getText() == null){
                JOptionPane.showMessageDialog(barangView, "Mohon isi jumlah pembagian dalam persen !", "Warning", JOptionPane.INFORMATION_MESSAGE);
            }else{
                if(selectedType == 1){
                    barang.setJumlahPembagian(Integer.valueOf(barangView.getPersenPembagian().getText().replace("%", "")));
                }
    //        barang.setSatuanTerkecil(barangView.getSatuanTerkecilField().getText());

    //        if(barangView.getSatuanTerbesarField().getText().isEmpty()){
    //            barang.setJmlSatuanTerkecil(0);
    //        }else{
    //            barang.setJmlSatuanTerkecil(Integer.valueOf(barangView.getJmlSatuanKecilField().getText()));
    //
    //        }
    //        if(validation(barang)){
                if(barangService.saveData(barang)){
                    empty();
                    listBarang = barangService.getAllData();
                    viewOnTable();
                    barangView.getKodeBarangField().requestFocus();
                    LOG.info("Save success");
                }else{
                    LOG.info("Save failed");
                }
    //        }  
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public boolean validation(Barang barang){
        
        boolean passed = false;
        if(barangService.findByCode(barang.getKodeBarang()) != null){
            LOG.info("Same code");
            JOptionPane.showMessageDialog(barangView, "Kode Barang Tidak Boleh Sama", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }else{
            passed = true;
        }
        return passed;
    }
    public void viewOnTable(){
        LOG.info("Preparing data barang");
        try{
            if(barangView.getViewTable().getModel() != null){
               ((DefaultTableModel) barangView.getViewTable().getModel()).setNumRows(0);
               barangView.getViewTable().revalidate();
            }
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Kode");
            tableModel.addColumn("Nama Barang");
            tableModel.addColumn("Harga");
            tableModel.addColumn("Bagi Untung");
            tableModel.addColumn("Satuan");
            tableModel.addColumn("Tipe");
    //        tableModel.addColumn("Satuan Terkecil");
    //        tableModel.addColumn("Jumlah Satuan Terkecil");
            tableModel.addColumn("Edit");
            tableModel.addColumn("Hapus");
            int index = 0;
            for(Barang barang : listBarang){
                Object[] obj = new Object[8];
                obj[0] = barang.getKodeBarang();
                obj[1] = barang.getNamaBarang();
                obj[2] = FormatRupiah.simpleConvert(String.valueOf(barang.getHarga()));
                obj[3] = barang.getJumlahPembagian()+"%";
                obj[4] = barang.getSatuanTerbesar();
                if(barang.getTipe()==0){
                     obj[5] = "S";
                }else{
                     obj[5] = "T";
                }
    //            obj[4] = barang.getSatuanTerkecil();
    //            obj[5] = barang.getJmlSatuanTerkecil();
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
                }
            };

            ButtonColumn buttonUpdate = new ButtonColumn(barangView.getViewTable(), update, 6);
            buttonUpdate.setMnemonic(KeyEvent.VK_D);

            ButtonColumn buttonDelete = new ButtonColumn(barangView.getViewTable(), delete, 7);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            barangView.getViewTable().getColumn("Tipe").setPreferredWidth(2);
            barangView.getViewTable().getColumn("Satuan").setPreferredWidth(8);
            barangView.getViewTable().getColumn("Kode").setPreferredWidth(30);
            barangView.getViewTable().getColumn("Bagi Untung").setPreferredWidth(40);
            barangView.getViewTable().getColumn("Nama Barang").setPreferredWidth(100);
            barangView.getViewTable().getColumn("Ubah").setPreferredWidth(2);
            barangView.getViewTable().getColumn("Hapus").setPreferredWidth(2);
            barangView.getViewTable().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            barangView.getViewTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            barangView.getViewTable().validate();
            LOG.info("Data ready");
        }catch(Exception e){
            LOG.error(e);
        }
        
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
        LOG.info("Exporting data to excel");
        try{
            JFileChooser saveFile = new JFileChooser();
            int result = saveFile.showSaveDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
              File file = saveFile.getSelectedFile();
              String path = String.valueOf(file+".xls");
              barangService.printToExcel(path,barangService.getAllData());
              LOG.info("Exporting done");
            }else{
              return;
            }
        }catch(Exception ex){
            LOG.error(ex);
        }
    }
    public void selectedType(ActionEvent e) {
        if(barangView.getTipeBarang().getSelectedIndex()-1 == 1){
            barangView.getPersenPembagian().setEnabled(true);
            barangView.getPersenPembagian().requestFocus();
        }else{
            barangView.getPersenPembagian().setEnabled(false);
            barangView.getDeskripsiField().requestFocus();

        }
    }
    public void findData(java.awt.event.ActionEvent e){
        search();
    }
    public void search(){
        LOG.info("Search data");
        try{
            if(!barangView.getKodeParamField().getText().equals("")){
                if(!barangView.getNamaParamField().getText().equals("")){
                    LOG.info("by code and name");
                    listBarang = barangService.findByCodeAndName(barangView.getKodeParamField().getText(), barangView.getNamaParamField().getText());
                }else{
                    LOG.info("by code");
                    listBarang = barangService.findByCode(barangView.getKodeParamField().getText());
                }
            }else{
                if(!barangView.getNamaParamField().getText().equals("")){
                    LOG.info("by name");
                    listBarang = barangService.findByName(barangView.getNamaParamField().getText());  
                }else{
                    LOG.info("all data");
                    listBarang = barangService.getAllData();
                }
            }
            viewOnTable();
        }catch(Exception e){
            LOG.error(e);
        }
    }
    private void namaParamFieldKeyReleased(java.awt.event.KeyEvent evt) {                                           
        search();
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
        try{
            LOG.info("Retrieve all data");
            listBarang = barangService.getAllData();
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public void deleteData(Barang barang){
        loadingController = new LoadingController(this.getParentController().getParent());
        loadingController.init();
        worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws InterruptedException {
            /** Execute some operation */
                try{
                    LOG.info("Deleting : "+barang.getNamaBarang());
                    barangService.deleteData(barang);
                    listBarang = barangService.getAllData();
                    viewOnTable();
                }catch(Exception e){
                    LOG.error(e);
                }
                return null;
            }
            @Override
            protected void done() {
                loadingController.closeLoading();
                loadingController = null;
                worker=null;
            }
        };
        worker.execute();
        loadingController.showLoading();
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
    public void chooseFile(java.awt.event.ActionEvent e){
        LOG.info("Preparing to import data");
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this.mainPageController.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            LOG.debug("Processing file "+selectedFile.getName());
            
            loadingController = new LoadingController(this.getParentController().getParent());
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    try {
                        FileInputStream excelFile = new FileInputStream(selectedFile);
                        Workbook workbook = new XSSFWorkbook(excelFile);
                        Sheet datatypeSheet = workbook.getSheetAt(0);
                        Iterator<Row> iterator = datatypeSheet.iterator();
                        listImportBarang = new ArrayList<>();
                        int columnIndex = 0, harga;
                        String namaBarang, kodeBarang,tipeAtauSatuan;
                        boolean isValid;
                        while (iterator.hasNext()) {
                            Row currentRow = iterator.next();
                            Iterator<Cell> cellIterator = currentRow.iterator();
                            Barang barang = new Barang();
                            isValid = true;
                            harga = 0;
                            while (cellIterator.hasNext()) {
                                Cell currentCell = cellIterator.next();
                                columnIndex = currentCell.getColumnIndex();
                                if(columnIndex==1){
                                    kodeBarang = currentCell.getStringCellValue();
                                    if(null == kodeBarang || "".equals(kodeBarang)){
                                        isValid = false;
                                        LOG.info("Empty code");
                                        break;
                                    }
                                    barang.setKodeBarang(kodeBarang);
                                }else if(columnIndex==2){
                                    namaBarang = currentCell.getStringCellValue();
                                    if(null == namaBarang || "".equals(namaBarang)){
                                        isValid = false;
                                        LOG.info("Empty name");
                                        break;
                                    }
                                    barang.setNamaBarang(namaBarang);                  
                                }else if(columnIndex==3){
                                    tipeAtauSatuan = currentCell.getStringCellValue();
                                    if(null == tipeAtauSatuan || "".equals(tipeAtauSatuan)){
                                        isValid = false;
                                        LOG.info("Empty price");
                                        break;
                                    }
                                    if(tipeAtauSatuan.equals("%")){
                                        barang.setTipe(1);
                                    }else{
                                        barang.setTipe(0);
                                        barang.setSatuanTerbesar(tipeAtauSatuan);
                                    }
                                }else if(columnIndex==4){

                                    if(currentCell.getCellType() == Cell.CELL_TYPE_STRING){
                                        try{
                                            harga = Integer.valueOf(currentCell.getStringCellValue());
                                        }catch(NumberFormatException ex){
                                            ex.getMessage();
                                            isValid = false;
                                            LOG.info(ex);
                                            break;
                                        }
                                    }else{
                                        harga = (int) currentCell.getNumericCellValue();
                                    }
                                    if(harga == 0){
                                        LOG.info("Empty price");
                                        isValid = false;
                                        break;
                                    }
                                    if(barang.getTipe() == 0){
                                        barang.setHarga(harga);
                                    }else{
                                        barang.setJumlahPembagian(harga);
                                    }
                                }
                            }
                            if(isValid){
                                listImportBarang.add(barang);
                            }
                        }

                    }catch (FileNotFoundException ex) {
                        LOG.error(ex);
                    }catch(IOException ex){
                        LOG.error(ex);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker=null;
                    if(listImportBarang.size() >0){
                        previewDataOnTable(listImportBarang);
                        previewImportBarang.show();
                    }else{
                        LOG.info("Empty file");
                    }
                }
            };
            worker.execute();
            loadingController.showLoading();
        }
    }
    public void previewDataOnTable(List<Barang> source){
        try{
            LOG.info("Preparing data barang");
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Kode");
            tableModel.addColumn("Nama Barang");
            tableModel.addColumn("Harga");
            tableModel.addColumn("Bagi Untung");
            tableModel.addColumn("Satuan");
            tableModel.addColumn("Tipe");
            tableModel.addColumn("Hapus");
            for(Barang barang : source){
                Object[] obj = new Object[7];
                obj[0] = barang.getKodeBarang();
                obj[1] = barang.getNamaBarang();
                obj[2] = FormatRupiah.simpleConvert(String.valueOf(barang.getHarga()));
                obj[3] = barang.getJumlahPembagian()+"%";
                obj[4] = barang.getSatuanTerbesar();
                if(barang.getTipe()==0){
                     obj[5] = "S";
                }else{
                     obj[5] = "T";
                }
    //            obj[4] = barang.getSatuanTerkecil();
    //            obj[5] = barang.getJmlSatuanTerkecil();
                obj[6] = "Hapus";
                tableModel.addRow(obj);
            }
            if(previewImportBarang == null){
                previewImportBarang = new PreviewImportBarang(mainPageController.getParent(), true);
                previewImportBarang.getSearchButton().addActionListener(this::searchInPreviewData);
                previewImportBarang.getShowAllButton().addActionListener(this::showAllImportedbarang);
                previewImportBarang.getSaveButton().addActionListener(this::saveImportedBarang);
            }
            previewImportBarang.getPreviewDataTable().setModel(tableModel);

            Action delete  = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deletePreviewData(source.get(modelRow));
                }
            };

            ButtonColumn buttonDelete = new ButtonColumn(previewImportBarang.getPreviewDataTable(), delete, 6);
            buttonDelete.setMnemonic(KeyEvent.VK_D);

            previewImportBarang.getPreviewDataTable().getColumn("Tipe").setPreferredWidth(2);
            previewImportBarang.getPreviewDataTable().getColumn("Satuan").setPreferredWidth(8);
            previewImportBarang.getPreviewDataTable().getColumn("Harga").setPreferredWidth(20);
            previewImportBarang.getPreviewDataTable().getColumn("Kode").setPreferredWidth(20);
            previewImportBarang.getPreviewDataTable().getColumn("Bagi Untung").setPreferredWidth(40);
            previewImportBarang.getPreviewDataTable().getColumn("Nama Barang").setPreferredWidth(130);
            previewImportBarang.getPreviewDataTable().getColumn("Hapus").setPreferredWidth(2);
            previewImportBarang.getPreviewDataTable().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            previewImportBarang.getPreviewDataTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            previewImportBarang.getPreviewDataTable().validate();
            LOG.info("Data ready");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    private void deletePreviewData(Barang barang){
        try{
            LOG.info("Delete data preview : "+barang.toString());
            listImportBarang.remove(barang);
            if(null != searchImportedNamaBarang && !"".equals(searchImportedNamaBarang)){
                listSearchImportBarang.remove(barang);
                LOG.info("Delete success");
                previewDataOnTable(listSearchImportBarang);
            }else{
                LOG.info("Delete success");
                previewDataOnTable(listImportBarang);            
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    private void showAllImportedbarang(ActionEvent e){
        previewDataOnTable(listImportBarang);
    }
    private void searchInPreviewData(ActionEvent event){
        try{
            LOG.info("Search data in preview");
            searchImportedNamaBarang = previewImportBarang.getSearchParamField().getText().trim();
            listSearchImportBarang = new ArrayList<>();
            for(Barang barang : listImportBarang){
                if(barang.getNamaBarang().toLowerCase().contains(searchImportedNamaBarang.toLowerCase())){
                    listSearchImportBarang.add(barang);
                }
            }
            LOG.info("Search success with result "+listSearchImportBarang.size()+" data");
            previewDataOnTable(listSearchImportBarang);
        }catch(Exception e){
            LOG.error(e);
        }
    }
    private void saveImportedBarang(ActionEvent event){
//        for(Barang barang : listImportBarang){
//            success = barangService.saveData(barang);
//            if(!success){
//                break;
//            }
//        }
        try{
            LOG.info("Importing data to database");
            loadingController = new LoadingController(this.getParentController().getParent());
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    if(barangService.saveData(listImportBarang)){
                        LOG.info("Import succes. "+listImportBarang.size()+" saved");
                        success = true;
                    }else{
                        LOG.info("Import failed");
                        success = false;
                    }
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                    loadingController = null;
                    worker=null;
                    if(success){
                        previewImportBarang.dispose();
                        listBarang = barangService.getAllData();
                        viewOnTable();
                        JOptionPane.showMessageDialog(barangView, "Import berhasil !", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(barangView, "Import gagal", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            };
            worker.execute();
            loadingController.showLoading();
        }catch(Exception e){
            LOG.error(e);
        }
    }
}
