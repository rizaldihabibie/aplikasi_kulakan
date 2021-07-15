/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public interface KulakanDao {
    
    /**
     *
     * @return
     */
    public List<Kulakan> getAllData();
    
    /**
     *
     * @param kulakan
     * @return
     */
    public boolean saveData(Kulakan kulakan);
    
    /**
     *
     * @param tanggalKulakan
     * @param supplierCode
     * @return
     */
    public List<Kulakan> findByDateAndSupplier(Date tanggalKulakan, String supplierCode);
    public List<Kulakan> findByMonth(String month);
    public String generateCode();
    public void printToExcel(String path,List<Kulakan> listKulakan);
    public List<Kulakan> findByMonthAndYear(String month, String year);
    public List<Kulakan> findJournalByMonthAndYear(String month, String year);
    public List<Kulakan> findyBySupplier(String supplierName);
    public List<Kulakan> findByMonthYearAndSupplier(String month, String year, String Supplier);
    public List<Kulakan> findByAllParameter(Date date, String supplier, String namaBarang);
    public List<Kulakan> findByBarangAndSupplier(String barang, String supplier);
    public List<Kulakan> findByBarang(String barang);
    public List<Kulakan> findByBarang(int idBarang);
    public List<Kulakan> findByBarangAndDate(Date date, String barang);
    public List<Kulakan> findByBarangAndTime(String month, String year, String barang);
    public List<Kulakan> findForJournal();
    public void deleteData(Kulakan kulakan);
    public List<Kulakan> findByDate(Date tanggalKulakan);
    public List<Kulakan> findJournalByDate(Date tanggalKulakan);
    public void saveInBatch(List<Kulakan> kulakan);
    public boolean checkNota(String nota);
}
