/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service;

import aplikasi.kulakan.dao.KulakanDao;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public interface KulakanService extends KulakanDao {
    
//    public List<Kulakan> getAllData();
//    public boolean saveData(Kulakan kulakan);
//    public List<Kulakan> findByDateAndSupplier(Date tanggalKulakan, String supplierCode);
//    public List<Kulakan> findByMonth(String month);
    public List<Supplier> getAllSupplierData();
//    public String generateCode();
//    public void printToExcel(String path,List<Kulakan> listKulakan);
//    public List<Kulakan> findByMonthAndYear(String month, String year); 
//    public List<Kulakan> findyBySupplier(String supplierName);
//    public List<Kulakan> findByMonthYearAndSupplier(String month, String year, String Supplier);
    public String[] suplierName();
//    public List<Kulakan> findByAllParameter(String month, String year, String supplier, String namaBarang);
//    public List<Kulakan> findByBarangAndSupplier(String barang, String supplier);
//    public List<Kulakan> findByBarang(String barang);
//    public List<Kulakan> findByBarangAndTime(String month, String year, String barang);
//    public void deleteData(Kulakan kulakan);
//    public List<Kulakan> findByDate(Date tanggalKulakan);
    public List<Supplier> getAllDataSuplier();
//    public void saveInBatch(List<Kulakan> kulakan);
//    public boolean checkNota(String nota);
}
