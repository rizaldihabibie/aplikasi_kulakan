/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.BarangDao;
import aplikasi.kulakan.dao.KulakanDao;
import aplikasi.kulakan.dao.StockDao;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.util.HibernateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormat;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author USER
 */
public class KulakanDaoImpl implements KulakanDao {

    private Session session;
    private HSSFSheet sheet;
    private final StockDao stockDao = new StockDaoImpl();
    private final BarangDao barangDao = new BarangDaoImpl();
    private static final Logger LOG = LogManager.getLogger(KulakanDaoImpl.class);
    @Override
    public List<Kulakan> getAllData() {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class)
                   .addOrder(Order.desc("tanggalKulakan")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByDateAndSupplier(Date tanggalKulakan, String supplierCode) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .add(Restrictions.eq("kulakan.tanggalKulakan", tanggalKulakan))
                    .add(Restrictions.eq("supplier.kodeSupplier", supplierCode)).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }
    @Override
    public List<Kulakan> findByMonth(String month) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("From Kulakan where MONTH(tanggal_kulakan) = '"+month+"'");
            listKulakan =query.list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public String generateCode() {
        String code = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Kulakan a order by a.kodePembelian desc limit 1");
            Kulakan kulakan = (Kulakan)query.uniqueResult();
            if(kulakan != null){
                code = kulakan.getKodePembelian();
                code = code.substring(4, code.length());
                int x = Integer.parseInt(code);
                x = x+1;
                if(String.valueOf(x).length() == 1){
                    code = "PMBL000"+x;
                }else if(String.valueOf(x).length() == 2){
                    code = "PMBL00"+x;
                }else if(String.valueOf(x).length() == 3){
                    code = "PMBL0"+x;
                }else if(String.valueOf(x).length() == 4){
                    code = "PMBL"+x;
                } 
            }else{
                code = "PMBL0001";
            }
//            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return code;
    }

    @Override
    public void printToExcel(String path, List<Kulakan> listKulakan) {
        File file = null;
        try{
           HSSFWorkbook workbook = new HSSFWorkbook();
           DataFormat format = workbook.createDataFormat();
           
           sheet = workbook.createSheet("Laporan Kulakan");
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
            
            String[] header = new String[7];
            header[0] = "Kode Pembelian";
            header[1] = "Nama Supplier";
            header[2] = "Nama Barang";
            header[3] = "Satuan";
            header[4] = "Jumlah";
            header[5] = "Tanggal Kulakan";
            header[6] = "Harga Netto";
            
            HSSFCell cell;
            HSSFRow rowHeader, rowData;
            rowHeader = sheet.createRow(3);
            for(int i =0; i<7; i++){
                cell = rowHeader.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellStyle);                
            }
            
            //Fill Data
            int indexData = 4;
            for(Kulakan kulakan : listKulakan){
                rowData = sheet.createRow(indexData);
                cell = rowData.createCell(0);
                cell.setCellValue(kulakan.getKodePembelian());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(1);
                cell.setCellValue(kulakan.getSupplier().getNamaSupplier());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(2);
                cell.setCellValue(kulakan.getBarang().getNamaBarang());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(3);
                cell.setCellValue(kulakan.getSatuan());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(4);
                cell.setCellValue(kulakan.getJumlah());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(5);
                cell.setCellValue(kulakan.getTanggalKulakan());
                cell.setCellStyle(styleTanggal);
                
                cell = rowData.createCell(6);
                cell.setCellValue(kulakan.getHargaNetto());
                cell.setCellStyle(styleRupiah);
                
                indexData++;
            }
            for(int i = 0; i<7; i++){
                sheet.autoSizeColumn(i);
            }
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            JOptionPane.showMessageDialog(null, "File Berhasil Disimpan di " + path,"Success",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Export Data"+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Kulakan> findByMonthAndYear(String month, String year) {
        List<Kulakan> listKulakan = new ArrayList<Kulakan>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Kulakan where MONTH(tanggal_kulakan) = '"+month+"' and YEAR(tanggal_kulakan) = '"+year+"'");
            listKulakan = query.list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findyBySupplier(String supplierName) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .add(Restrictions.eq("supplier.namaSupplier", supplierName)).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByMonthYearAndSupplier(String month,String year, String supplier) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .add(Restrictions.eq("supplier.namaSupplier", supplier))
                    .add(Restrictions.sqlRestriction("MONTH(tanggal_kulakan) = '"+month+"' && YEAR(tanggal_kulakan) = '"+year+"'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByAllParameter(Date date, String supplier, String namaBarang) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("supplier.namaSupplier", supplier))
                    .add(Restrictions.sqlRestriction("nama_barang like'%"+namaBarang+"%'"))
                    .add(Restrictions.eq("kulakan.tanggalKulakan", date)).list();
//                    .add(Restrictions.sqlRestriction("MONTH(tanggal_kulakan) = '"+month+"' && YEAR(tanggal_kulakan) = '"+year+"'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByBarangAndSupplier(String barang, String supplier) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("supplier.namaSupplier", supplier))
                    .add(Restrictions.sqlRestriction("nama_barang like'%"+barang+"%'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByBarang(String barang) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.sqlRestriction("nama_barang like'%"+barang+"%'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByBarangAndTime(String month, String year, String barang) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.sqlRestriction("nama_barang like'%"+barang+"%'"))
                    .add(Restrictions.sqlRestriction("MONTH(tanggal_kulakan) = '"+month+"' && YEAR(tanggal_kulakan) = '"+year+"'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public void deleteData(Kulakan kulakan) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(kulakan); 
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
    }

    @Override
    public List<Kulakan> findByDate(Date tanggalKulakan) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .add(Restrictions.eq("kulakan.tanggalKulakan", tanggalKulakan)).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public void saveInBatch(List<Kulakan> kulakan) {
        boolean success = true;
        try{
            for(int i = 0; i<kulakan.size(); i++){
                if(this.saveData(kulakan.get(i))){
                    if(stockDao.changeStock(kulakan.get(i))){
                        barangDao.updateData(kulakan.get(i).getBarang());
                        if(i==49){
                            session.flush();
                            session.clear();
                        }
                        success = true;
                    }else{
                        success = false;
                        JOptionPane.showMessageDialog(null, "Stock Gagal Disimpan", "Warning", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Warning", JOptionPane.ERROR_MESSAGE);
                    success = false;
                    break;
                }
            }
            System.out.println("here");
            if(success){
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Rollback error "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
    }
    @Override
    public boolean saveData(Kulakan kulakan) {
        boolean success = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            kulakan.setKodePembelian(this.generateCode());
            session.save(kulakan);
            success = true;
        }catch(HibernateException e){
            LOG.error(e);
            e.printStackTrace();
        }
        if(success){
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public boolean checkNota(String nota) {
        List<Kulakan> listKulakan= new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class)
                    .add(Restrictions.eq("noNota", nota)).list();
            if(!listKulakan.isEmpty()){
                for(Kulakan kulakan : listKulakan){
                    Hibernate.initialize(kulakan.getBarang());
                }
                session.flush();
                return true;
            }
            
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return false;
    }

    @Override
    public List<Kulakan> findForJournal() {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("barang.tipe", 0)).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findJournalByMonthAndYear(String month, String year) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("barang.tipe", 0))
                    .add(Restrictions.sqlRestriction("MONTH(tanggal_kulakan) = '"+month+"' && YEAR(tanggal_kulakan) = '"+year+"'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findJournalByDate(Date tanggalKulakan) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("kulakan.tanggalKulakan", tanggalKulakan))
                    .add(Restrictions.eq("barang.tipe", 0)).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByBarang(int idBarang) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.eq("barang.idBarang", idBarang))
                    .addOrder(Order.desc("kulakan.kodePembelian")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }

    @Override
    public List<Kulakan> findByBarangAndDate(Date date, String barang) {
        List<Kulakan> listKulakan = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            listKulakan = session.createCriteria(Kulakan.class, "kulakan")
                    .createAlias("kulakan.supplier", "supplier")
                    .createAlias("kulakan.barang", "barang")
                    .add(Restrictions.sqlRestriction("nama_barang like'%"+barang+"%'"))
                    .add(Restrictions.eq("kulakan.tanggalKulakan", date)).list();
//                    .add(Restrictions.sqlRestriction("MONTH(tanggal_kulakan) = '"+month+"' && YEAR(tanggal_kulakan) = '"+year+"'")).list();
            for(Kulakan kulakan : listKulakan){
                if(kulakan.getBarang() != null){
                    Hibernate.initialize(kulakan.getBarang());
                }
                if(kulakan.getSupplier() != null){
                    Hibernate.initialize(kulakan.getSupplier());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listKulakan;
    }
}
