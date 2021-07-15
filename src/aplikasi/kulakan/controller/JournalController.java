/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Journal;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Pengeluaran;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.PengeluaranService;
import aplikasi.kulakan.service.PenjualanService;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.service.impl.PengeluaranServiceImpl;
import aplikasi.kulakan.service.impl.PenjualanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.AddPengeluaran;
import aplikasi.kulakan.view.JournalPage;
import aplikasi.kulakan.view.PengeluaranPage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.PrintSetup;

/**
 *
 * @author guest
 */
public class JournalController implements BasicController {
    private JournalPage journalPage;
    private PengeluaranService pengeluaranService;
    private KulakanService kulakanService;
    private PenjualanService penjualanService;
    private MainPageController mainPageController;
    private HSSFSheet sheet;
    
    private String tanggal;
    private int month, year;        
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<Pengeluaran> listPengeluaran;
    private List<Penjualan> listPenjualan;
//    private List<Kulakan> listKulakan;
    private List<Journal> listJournal;
    private SwingWorker<Void, Void> worker;
    private LoadingController loadingController;
    private static Logger LOG = LogManager.getLogger(JournalController.class);
    
    public JournalController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    

    @Override
    public void init() {
        
        try{
            LOG.info("Preparing journal page");
            this.pengeluaranService = new PengeluaranServiceImpl();
            this.penjualanService = new PenjualanServiceImpl();
            this.kulakanService = new KulakanServiceImpl();

            this.journalPage = new JournalPage();
            this.journalPage.getMonthOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
            this.journalPage.getTahunParameter().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
            this.journalPage.getSearchButton().addActionListener(this::search);
            this.journalPage.getPrintButton().addActionListener(this::printButton);
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        journalPage.setSize(mainPageController.getParent().getMenuLayer().getSize());
        journalPage.setVisible(true);
        this.showAllData();
        this.viewOnTable();
    }

    @Override
    public void stop() {
        journalPage.setVisible(false);
        this.pengeluaranService = null;
        this.penjualanService = null;
        this.kulakanService = null;
    }

    @Override
    public JPanel getPage() {
        return journalPage;
    }
    public void showAllData(){
        listPengeluaran = pengeluaranService.getAll();
        listPenjualan = penjualanService.getAllData();
//        listKulakan = kulakanService.findForJournal();
        processingData();
    }
    public void processingData(){
        LOG.info("Processing data journal");
        try{
            listJournal = new ArrayList<>();
            for(Pengeluaran pengeluaran : listPengeluaran){
                Journal journal = new Journal();
                journal.setTanggal(pengeluaran.getTanggal());
                journal.setKeterangan(pengeluaran.getJudul());
                journal.setTotal(pengeluaran.getJumlah());
                journal.setType("pengeluaran");
                journal.setTotalPengeluaran(pengeluaran.getJumlah());
                listJournal.add(journal);
            }
//            for(Kulakan kulakan : listKulakan){
//                Journal journal = new Journal();
//                journal.setTanggal(kulakan.getTanggalKulakan());
//                journal.setKeterangan(kulakan.getBarang().getNamaBarang());
//                journal.setVolume(((Double)kulakan.getJumlah()).intValue());
//                journal.setSatuan("BH");
//                journal.setDiskon(((Double)kulakan.getDiskon()).intValue());
//                journal.setHargaSatuan(((Long)kulakan.getHargaNetto()).intValue());
//                journal.setTotal(((Long)kulakan.getHargaNetto()).intValue()*((Double)kulakan.getJumlah()).intValue());
//                journal.setType("kulakan");
//                journal.setTotalPengeluaran((((Long)kulakan.getHargaNetto()).intValue()*((Double)kulakan.getJumlah()).intValue())-((Double)kulakan.getDiskon()).intValue());
//                listJournal.add(journal);
//            }
            LOG.debug("Processing "+listPenjualan.size()+" data penjualan");
            for(Penjualan penjualan : listPenjualan){
                Journal journal = new Journal();
                journal.setTanggal(penjualan.getTanggal());
                journal.setKeterangan(penjualan.getNamaBarang());
                journal.setVolume(((Double)penjualan.getQty()).intValue());
                journal.setSatuan("BH");
                journal.setDiskon(((Double)penjualan.getDiskon()).intValue());
                journal.setHargaSatuan(penjualan.getHargaSatuan());
                journal.setTotal(penjualan.getHargaSatuan()*((Double)penjualan.getQty()).intValue());
                journal.setType("penjualan");
                journal.setTotalKulakan(penjualan.getHargaKulak()*((Double)penjualan.getQty()).intValue());
                int margin = (penjualan.getHargaSatuan()*((Double)penjualan.getQty()).intValue())-((Double)penjualan.getDiskon()).intValue();
                margin = margin-(penjualan.getHargaKulak()*((Double)penjualan.getQty()).intValue());
                journal.setMargin(margin);
                listJournal.add(journal);
//                if(penjualan.getBarang().getTipe() == 0){
//                    journal = new Journal();
//                    journal.setTanggal(penjualan.getTanggal());
//                    journal.setKeterangan(penjualan.getBarang().getNamaBarang());
//                    journal.setVolume(((Double)penjualan.getQty()).intValue());
//                    journal.setSatuan("BH");
//                    journal.setDiskon(0);
//                    journal.setHargaSatuan(penjualan.getHargaKulak());
//                    journal.setTotal(penjualan.getHargaKulak()*((Double)penjualan.getQty()).intValue());
//                    journal.setType("kulakan");
//                    journal.setTotalPengeluaran(penjualan.getHargaKulak()*((Double)penjualan.getQty()).intValue());
//                    listJournal.add(journal);
//                }
            }
            Collections.sort(listJournal);
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
        
    }
    public void viewOnTable(){
        try{
            LOG.info("Presenting data on table");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No.");
            model.addColumn("Tanggal");
            model.addColumn("Keterangan");
            model.addColumn("Volume");
            model.addColumn("Satuan");
            model.addColumn("Harga Satuan");
            model.addColumn("Pemasukan");
            model.addColumn("Kulakan");
            model.addColumn("Pengeluaran");
            model.addColumn("Diskon");
            model.addColumn("Saldo");
            model.addColumn("Margin");
            model.addColumn("Total Pengeluaran");
            int no = 1;
            int saldo = 0;
            int totalMargin = 0;
//            int totalSaldo = 0;
            int totalPengeluaran= 0;
            int totalPengeluaranAll= 0;
            int totalPemasukan = 0;
            int totalKulakan = 0;

            for(Journal journal: listJournal){
                Object[] obj = new Object[13];
                obj[0] = no;
                obj[1] = FormatDate.format(journal.getTanggal());
                obj[2] = journal.getKeterangan();
                if(!journal.getType().equals("pengeluaran")){
                    obj[3] = journal.getVolume();
                    obj[4] = journal.getSatuan();
                    obj[5] = FormatRupiah.simpleConvert(String.valueOf(journal.getHargaSatuan()));
//                    if(journal.getType().equals("kulakan")){
//                        saldo = saldo-(journal.getTotal()-journal.getDiskon());
//                        totalKulakan = totalKulakan+journal.getTotalPengeluaran();
//                        totalPengeluaranAll = totalPengeluaranAll+journal.getTotalPengeluaran();
//                        obj[6]="-";
//                        obj[7]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotal()));     
//                        obj[8]="-";
//                        obj[11]="-";
//                        obj[12]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotalPengeluaran())); 
//                    }else{
                        totalPemasukan = totalPemasukan + journal.getTotal();
                        totalMargin = totalMargin+journal.getMargin();
                        saldo = saldo+(journal.getTotal()-journal.getDiskon());
                        obj[6]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotal()));
                        obj[7]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotalKulakan()));;   
                        obj[8]="-";  
                        obj[11]=FormatRupiah.simpleConvert(String.valueOf(journal.getMargin()));
                        obj[12]=0;
//                    }
                    obj[9]=FormatRupiah.simpleConvert(String.valueOf(journal.getDiskon()));
                }else{
                    totalPengeluaranAll = totalPengeluaranAll+journal.getTotalPengeluaran();
                    totalPengeluaran = totalPengeluaran+journal.getTotal();
                    saldo = saldo-(journal.getTotal()-journal.getDiskon());
                    obj[3] = "-";
                    obj[4] = "-";
                    obj[5] = "-";
                    obj[6]="-";
                    obj[7]="-";
                    obj[8]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotal()));
                    obj[9]="-";
                    obj[11]="-";
                    obj[12]=FormatRupiah.simpleConvert(String.valueOf(journal.getTotalPengeluaran()));
                }
//                totalSaldo = totalSaldo+saldo;
                obj[10]=FormatRupiah.simpleConvert(String.valueOf(saldo));
                model.addRow(obj);
                no++;
            }
            journalPage.getTotalKulakanLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalKulakan)));
            journalPage.getTotalPemasukanLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalPemasukan)));   
            journalPage.getTotalMarginLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalMargin)));         
            journalPage.getTotalPengeluaranLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalPengeluaran))); 
//            journalPage.getTotalSaldoLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalSaldo)));         
            journalPage.getTotalPengeluaranAllLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalPengeluaranAll)));
            int totalLaba = totalMargin-totalPengeluaran;
            journalPage.getTotalLabaLabel().setText(FormatRupiah.simpleConvert(String.valueOf(totalLaba)));
            journalPage.getViewTable().setModel(model);
            journalPage.getViewTable().getColumn("No.").setPreferredWidth(10);
            journalPage.getViewTable().getColumn("Volume").setPreferredWidth(10);
            journalPage.getViewTable().getColumn("Satuan").setPreferredWidth(10);
            journalPage.getViewTable().getColumn("Diskon").setPreferredWidth(35);
            journalPage.getViewTable().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            journalPage.getViewTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            journalPage.getViewTable().validate();
            this.setBoldLabel();
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
//        int totalPengeluaran = 0;
//        for(Pengeluaran p : listPengeluaran){
//            totalPengeluaran = totalPengeluaran +  p.getJumlah();
//        }
//        pengeluaranPage.getTotalPengeluaranLabel().setText(FormatRupiah.convert(String.valueOf(totalPengeluaran)));
    }
    
    public MainPageController getParent(){
        return mainPageController;
    }
    
    public void search(java.awt.event.ActionEvent e){
        try{
            LOG.info("Search data");
            month = journalPage.getMonthOption().getSelectedIndex();
            year = Integer.valueOf(journalPage.getTahunParameter().getSelectedItem().toString());
            tanggal  = journalPage.getTanggalField().getText();
            findData();
            this.processingData();
            viewOnTable();
        }catch(ParseException ex){
            LOG.error(ex);
        }
    }
    public void findData() throws ParseException{
        try{
            if( month != 0  && year !=0 && !tanggal.equals("")){
                String date = tanggal+"/"+month+
                            "/"+year;
                Date dateString = dateFormat.parse(date);
                listPengeluaran = pengeluaranService.findByDate(dateString);
                listPenjualan = penjualanService.findByDate(dateString);
//                listKulakan = kulakanService.findJournalByDate(dateString);
                LOG.info("by date");
            }else if(month != 0  && year !=0 && tanggal.equals("")){
    //            System.out.println("Find by month and year");
                listPengeluaran = pengeluaranService.findByMonth(""+month,""+year);
                listPenjualan = penjualanService.findByMonthAndYear(""+month,""+year);
//                listKulakan = kulakanService.findJournalByMonthAndYear(""+month,""+year);
                LOG.info("by month year");
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public void printButton(java.awt.event.ActionEvent e){
        PrintOptionController print = new PrintOptionController(this);
        print.openPrintDialog().setVisible(true);
    }
    
    public void printToExcel(String path, int year, int month, String date){
//        System.out.println("year : "+year);
//        System.out.println("month : "+month);
//        System.out.println("date : "+date);
        this.month = month;
        this.year = year;
        this.tanggal = date;
        try {
            findData();
            processingData();
//            System.out.println("size : "+listJournal.size());
            loadingController = new LoadingController(this.mainPageController.getParent());
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    generateExcel(path);
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                }
            };
            worker.execute();
            loadingController.showLoading();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    private void generateExcel(String path){
        LOG.info("Exporting journal to excel");
        int saldo = 0;
        int totalMargin = 0;
        int totalSaldo = 0;
        int totalPengeluaran= 0;
        int totalPengeluaranAll= 0;
        int totalPemasukan = 0;
        int totalKulakan = 0;
        File file = null;
        try{
           HSSFWorkbook workbook = new HSSFWorkbook();
           DataFormat format = workbook.createDataFormat();
           
           sheet = workbook.createSheet("JURNAL");
           sheet.getPrintSetup().setLandscape(true);
           sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.LEGAL_PAPERSIZE); 
                
            HSSFCellStyle cellStyle = workbook.createCellStyle();
	    HSSFCellStyle cellStyle2 = workbook.createCellStyle();
	    HSSFCellStyle styleRupiah = workbook.createCellStyle();
	    HSSFCellStyle styleTanggal = workbook.createCellStyle();
            
                
            HSSFFont fontHeader = workbook.createFont();
	    HSSFFont fontValue = workbook.createFont();
            
            fontHeader.setFontHeightInPoints((short)10);
            fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            fontHeader.setFontName("Times New Roman");
            
            fontValue.setFontHeightInPoints((short)10);
            fontValue.setFontName("Times New Roman");
            
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle.setFont(fontHeader);
            
            cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            cellStyle2.setFont(fontValue);
            
            styleRupiah.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleRupiah.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleRupiah.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleRupiah.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleRupiah.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleRupiah.setDataFormat(format.getFormat("_-* #,##0.00_-;-* #,##0.00_-;_-* \"-\"??_-;_-@_-"));
            styleRupiah.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            styleRupiah.setFont(fontValue);
            
            styleTanggal.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            styleTanggal.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleTanggal.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleTanggal.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleTanggal.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleTanggal.setDataFormat(format.getFormat("DD MMMM yyyy"));
            styleTanggal.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            styleTanggal.setFont(fontValue);
            
            String[] header = new String[12];
            header[0] = "TANGGAL";
            header[1] = "KETERANGAN";
            header[2] = "VOLUME";
            header[3] = "SATUAN";
            header[4] = "HARGA SAT";
            header[5] = "PEMASUKAN";
            header[6] = "KULAKAN";
            header[7] = "PENGELUARAN";
            header[8] = "DISKON";
            header[9] = "SALDO";
            header[10] = "MARGIN";
            header[11] = "TOTAL PENGELUARAN";
            
            HSSFCell cell;
            HSSFRow rowHeader, rowData;
            rowHeader = sheet.createRow(3);
            for(int i =0; i<12; i++){
                cell = rowHeader.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellStyle);                
            }
            
            //Fill Data
            int indexData = 4;
            for(Journal journal: listJournal){
                rowData = sheet.createRow(indexData);
                cell = rowData.createCell(0);
                cell.setCellValue(FormatDate.format(journal.getTanggal()));
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(1);
                cell.setCellValue(journal.getKeterangan());
                cell.setCellStyle(styleTanggal);
                if(!journal.getType().equals("pengeluaran")){
                    cell = rowData.createCell(2);
                    cell.setCellValue(journal.getVolume());
                    cell.setCellStyle(cellStyle2);

                    cell = rowData.createCell(3);
                    cell.setCellValue(journal.getSatuan());
                    cell.setCellStyle(styleRupiah);

                    cell = rowData.createCell(4);
                    cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getHargaSatuan())));
                    cell.setCellStyle(cellStyle2);
                    
//                    if(journal.getType().equals("kulakan")){
//                        saldo = saldo-(journal.getTotal()-journal.getDiskon());
//                        totalKulakan = totalKulakan+journal.getTotalPengeluaran();
//                        totalPengeluaranAll = totalPengeluaranAll+journal.getTotalPengeluaran();
//                        cell = rowData.createCell(5);
//                        cell.setCellValue(0);
//                        cell.setCellStyle(cellStyle2);
//                        cell = rowData.createCell(6);
//                        cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotal())));
//                        cell.setCellStyle(cellStyle2);
//                        cell = rowData.createCell(7);
//                        cell.setCellValue(0);
//                        cell.setCellStyle(cellStyle2);
//                        cell = rowData.createCell(10);
//                        cell.setCellValue(0);
//                        cell.setCellStyle(cellStyle2);
//                        cell = rowData.createCell(11);
//                        cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotalPengeluaran())));
//                        cell.setCellStyle(cellStyle2);
//                    }else{
                        totalPemasukan = totalPemasukan + journal.getTotal();
                        totalMargin = totalMargin+journal.getMargin();
                        saldo = saldo+(journal.getTotal()-journal.getDiskon());
                        cell = rowData.createCell(5);
                        cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotal())));
                        cell.setCellStyle(cellStyle2);
                        cell = rowData.createCell(6);
                        cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotalKulakan())));
                        cell.setCellStyle(cellStyle2);
                        cell = rowData.createCell(7);
                        cell.setCellValue(0);
                        cell.setCellStyle(cellStyle2);
                        cell = rowData.createCell(10);
                        cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getMargin())));
                        cell.setCellStyle(cellStyle2);
                        cell = rowData.createCell(11);
                        cell.setCellValue(0);
                        cell.setCellStyle(cellStyle2);
//                    }
                    cell = rowData.createCell(8);
                    cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getDiskon())));
                    cell.setCellStyle(cellStyle2);
                }else{
                    totalPengeluaranAll = totalPengeluaranAll+journal.getTotalPengeluaran();
                    totalPengeluaran = totalPengeluaran+journal.getTotal();
                    saldo = saldo-(journal.getTotal()-journal.getDiskon());
                    cell = rowData.createCell(2);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(3);
                    cell.setCellValue("-");
                    cell.setCellStyle(styleRupiah);
                    cell = rowData.createCell(4);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(5);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(6);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(7);
                    cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotal())));
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(8);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(10);
                    cell.setCellValue(0);
                    cell.setCellStyle(cellStyle2);
                    cell = rowData.createCell(11);
                    cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(journal.getTotalPengeluaran())));
                    cell.setCellStyle(cellStyle2);
                }
//                totalSaldo = totalSaldo+saldo;
                cell = rowData.createCell(9);
                cell.setCellValue(FormatRupiah.simpleConvert(String.valueOf(saldo)));
                cell.setCellStyle(cellStyle2);
                indexData++;
            }
            rowData = sheet.createRow(indexData);
            cell = rowData.createCell(5);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalPemasukan));
            cell.setCellStyle(cellStyle2);
            rowData = sheet.createRow(indexData);
            cell = rowData.createCell(6);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalKulakan));
            cell.setCellStyle(cellStyle2);
            cell = rowData.createCell(7);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalPengeluaran));
            cell.setCellStyle(cellStyle2);
            cell = rowData.createCell(9);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalSaldo));
            cell.setCellStyle(cellStyle2);
            cell = rowData.createCell(10);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalMargin));
            cell.setCellStyle(cellStyle2);
            cell = rowData.createCell(11);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalPengeluaranAll));
            cell.setCellStyle(cellStyle2);
            int totalLaba = totalMargin-totalPengeluaran;
            indexData++;
            rowData = sheet.createRow(indexData);
            cell = rowData.createCell(10);
            cell.setCellValue(FormatRupiah.simpleConvert(""+totalLaba));
            cell.setCellStyle(cellStyle2);
            
            cell = rowData.createCell(11);
            cell.setCellStyle(cellStyle2);
            sheet.addMergedRegion(new CellRangeAddress(indexData,indexData,10,11));
            workbook.setPrintArea(
                0, //sheet index
                0, //start column
                11, //end column
                3, //start row
                indexData //end row
            );
            sheet.setAutobreaks(true); 
            sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
            sheet.getPrintSetup().setLandscape(true);
            sheet.setFitToPage(true);
            sheet.getPrintSetup().setFitWidth((short)1);
            sheet.getPrintSetup().setFitHeight((short)0);
            for(int i = 0; i<12; i++){
                sheet.autoSizeColumn(i);
            }
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            LOG.info("done");
            JOptionPane.showMessageDialog(null, "File Berhasil Disimpan di " + path,"Success",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Export Data"+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setBoldLabel(){
        journalPage.getTotalKulakanLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        journalPage.getTotalPemasukanLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));   
        journalPage.getTotalMarginLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));       
        journalPage.getTotalPengeluaranLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12)); 
        journalPage.getTotalPengeluaranAllLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        journalPage.getTotalLabaLabel().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
    }
}
