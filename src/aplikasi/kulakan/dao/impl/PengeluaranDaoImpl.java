/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.PengeluaranDao;
import aplikasi.kulakan.model.Pengeluaran;
import aplikasi.kulakan.util.FormatDate;
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
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author guest
 */
public class PengeluaranDaoImpl implements PengeluaranDao{
    
    private Session session;
    private HSSFSheet sheet;
    private static Logger LOG = LogManager.getLogger(PengeluaranDaoImpl.class);
    @Override
    public boolean save(Pengeluaran pengeluaran) {
        boolean success = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pengeluaran); 
            success = true;
        }catch(HibernateException e){
            LOG.error(e);
        }
        if(success){
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public List<Pengeluaran> getAll() {
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPengeluaran = session.createCriteria(Pengeluaran.class)
                   .addOrder(Order.desc("tanggal")).list();
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
        return listPengeluaran;
    }

    @Override
    public List<Pengeluaran> findByMonth(String month, String year) {
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Pengeluaran where MONTH(tanggal) = '"+month+"' and YEAR(tanggal) = '"+year+"'");
            listPengeluaran = query.list();
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
        return listPengeluaran;
    }

    @Override
    public List<Pengeluaran> findByTitle(String title) {
        
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Pengeluaran where judul like '%"+title+"%'");
            listPengeluaran = query.list();
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
        return listPengeluaran;
    }

    @Override
    public List<Pengeluaran> findByTitleAndMonth(String title, String month, String year) {
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LOG.debug("Search by : "+title+", "+month+", "+year);
            Query query = session.createQuery("from Pengeluaran where judul like '%"+title+"%' and MONTH(tanggal) = '"+month+"' and YEAR(tanggal) = '"+year+"'");
            listPengeluaran = query.list();
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
        return listPengeluaran;
    }

    @Override
    public void printToExcel(String path, List<Pengeluaran> listPengeluaran) {
        File file = null;
        try{
           HSSFWorkbook workbook = new HSSFWorkbook();
           DataFormat format = workbook.createDataFormat();
           
           sheet = workbook.createSheet("Laporan Pengeluaran");
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
            
            String[] header = new String[5];
            header[0] = "No. ";
            header[1] = "Tanggal";
            header[2] = "Judul";
            header[3] = "Harga";
            header[4] = "Deskripsi";
            
            HSSFCell cell;
            HSSFRow rowHeader, rowData;
            rowHeader = sheet.createRow(3);
            for(int i =0; i<5; i++){
                cell = rowHeader.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellStyle);                
            }
            
            //Fill Data
            int indexData = 4;
            for(Pengeluaran pengeluaran : listPengeluaran){
                rowData = sheet.createRow(indexData);
                cell = rowData.createCell(0);
                cell.setCellValue(pengeluaran.getIdPengeluaran());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(1);
                cell.setCellValue(FormatDate.format(pengeluaran.getTanggal()));
                cell.setCellStyle(styleTanggal);
                
                cell = rowData.createCell(2);
                cell.setCellValue(pengeluaran.getJudul());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(3);
                cell.setCellValue(pengeluaran.getJumlah());
                cell.setCellStyle(styleRupiah);
                
                cell = rowData.createCell(4);
                cell.setCellValue(pengeluaran.getDeskripsi());
                cell.setCellStyle(cellStyle2);
                
                indexData++;
            }
            for(int i = 0; i<5; i++){
                sheet.autoSizeColumn(i);
            }
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            JOptionPane.showMessageDialog(null, "File Berhasil Disimpan di " + path,"Success",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Export Data"+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean delete(Pengeluaran pengeluaran) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(pengeluaran); 
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus "+e, "Warning", JOptionPane.ERROR_MESSAGE);
            return false;
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return true;
    }

    @Override
    public List<Pengeluaran> findByDate(Date date) {
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPengeluaran = session.createCriteria(Pengeluaran.class)
                    .add(Restrictions.eq("tanggal", date)).list();
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
        return listPengeluaran;
    }

    @Override
    public List<Pengeluaran> findByTitleAndDate(String title, Date date) {
        List<Pengeluaran> listPengeluaran = new ArrayList<Pengeluaran>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            LOG.debug("Search by : "+title+", "+date+"");
            
            listPengeluaran = session.createCriteria(Pengeluaran.class)
                    .add(Restrictions.like("judul", title, MatchMode.ANYWHERE))
                    .add(Restrictions.eq("tanggal", date)).list();
//            Query query = session.createQuery("from Pengeluaran where judul like '%"+title+"%' and tanggal = '"+date+"'");
//            listPengeluaran = query.list();
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
        return listPengeluaran;
    }
    
}
