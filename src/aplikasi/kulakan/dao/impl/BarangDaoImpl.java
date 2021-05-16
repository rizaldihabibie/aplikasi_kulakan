/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.BarangDao;
import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.util.HibernateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author USER
 */
public class BarangDaoImpl implements BarangDao{
    private Session session;
    private HSSFSheet sheet;
    
    @Override
    public List<Barang> getAllData() {
        List<Barang> listBarang = new ArrayList<Barang>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listBarang = session.createCriteria(Barang.class,"barang")
                    .addOrder(Order.asc("barang.namaBarang")).list();
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listBarang;
    }

    @Override
    public boolean saveData(Barang barang) {
        boolean success = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(barang);
            session.getTransaction().commit();
            success = true;
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return success;
    }

    @Override
    public Barang findByCode(String code) {
        Barang barang = new Barang();
         try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            barang = (Barang)session.createCriteria(Barang.class).add(Restrictions.eq("kodeBarang", code)).uniqueResult();
            session.flush();
         }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
         return barang;
    }

    @Override
    public List<Barang> findByName(String name) {
        List<Barang> listBarang = new ArrayList<Barang>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Barang a where a.namaBarang like'%"+name+"%'");
            listBarang = query.list();
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listBarang;
    }

    /**
     *
     * @param path
     * @param listBarang
     * @param ListBarang
     */
    @Override
    public void printToExcel(String path, List<Barang> listBarang) {
        File file = null;
        try{
           HSSFWorkbook workbook = new HSSFWorkbook();
           DataFormat format = workbook.createDataFormat();
           
           sheet = workbook.createSheet("Laporan Data Barang");
           sheet.getPrintSetup().setLandscape(true);
           sheet.getPrintSetup().setPaperSize(HSSFPrintSetup.LEGAL_PAPERSIZE); 
                
            HSSFCellStyle cellStyle = workbook.createCellStyle();
	    HSSFCellStyle cellStyle2 = workbook.createCellStyle();      
                
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
            
            String[] header = new String[10];
            header[0] = "Kode Barang";
            header[1] = "Nama Barang";
            header[2] = "Deskripsi Barang";
            
            HSSFCell cell;
            HSSFRow rowHeader, rowData;
            rowHeader = sheet.createRow(3);
            for(int i =0; i<3; i++){
                cell = rowHeader.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(cellStyle);                
            }
            
            //Fill Data
            int indexData = 4;
            for(Barang barang : listBarang){
                rowData = sheet.createRow(indexData);
                cell = rowData.createCell(0);
                cell.setCellValue(barang.getKodeBarang());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(1);
                cell.setCellValue(barang.getNamaBarang());
                cell.setCellStyle(cellStyle2);
                
                cell = rowData.createCell(2);
                cell.setCellValue(barang.getDeskripsiBarang());
                cell.setCellStyle(cellStyle2);
                indexData++;
            }
            for(int i = 0; i<3; i++){
                sheet.autoSizeColumn(i);
            }
            FileOutputStream outputStream = null;
            outputStream = new FileOutputStream(path);
            workbook.write(outputStream);
            outputStream.close();
            JOptionPane.showMessageDialog(null, "File Berhasil Disimpan di " + path,"Success",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Gagal Import Data"+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public List<Barang> findByCodeAndName(String code, String name) {
        List<Barang> listBarang = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listBarang = session.createCriteria(Barang.class).add(Restrictions.or(Restrictions.like("namaBarang", name),Restrictions.like("kodeBarang", code)))
                    .list();
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
         return listBarang;
    }

    @Override
    public void updateData(Barang barang) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(barang);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
    }

    @Override
    public void deleteData(Barang barang) {
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(barang);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
           JOptionPane.showMessageDialog(null, "Gagal Menghapus Data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
    }
    
}
