/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.KulakanDao;
import aplikasi.kulakan.dao.SupplierDao;
import aplikasi.kulakan.dao.impl.KulakanDaoImpl;
import aplikasi.kulakan.dao.impl.SupplierDaoImpl;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.KulakanService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class KulakanServiceImpl implements KulakanService {

    private final KulakanDao kulakanDao = new KulakanDaoImpl();
    private final SupplierDao supplierDao = new SupplierDaoImpl();
    
    @Override
    public List<Kulakan> getAllData() {
        return kulakanDao.getAllData();
    }

    @Override
    public boolean saveData(Kulakan kulakan) {
        return kulakanDao.saveData(kulakan);
    }

    @Override
    public List<Kulakan> findByDateAndSupplier(Date tanggalKulakan, String codeSupplier) {
        return kulakanDao.findByDateAndSupplier(tanggalKulakan, codeSupplier);
    }

    @Override
    public List<Kulakan> findByMonth(String month) {
        return kulakanDao.findByMonth(month);
    }

    @Override
    public List<Supplier> getAllSupplierData() {
        return supplierDao.getAllData();
    }

    @Override
    public String generateCode() {
        return kulakanDao.generateCode();
    }

    @Override
    public void printToExcel(String path,List<Kulakan> listKulakan) {
        kulakanDao.printToExcel(path,listKulakan);
    }

    @Override
    public List<Kulakan> findByMonthAndYear(String month, String year) {
        return kulakanDao.findByMonthAndYear(month, year);
    }

    @Override
    public List<Kulakan> findyBySupplier(String supplierName) {
        return kulakanDao.findyBySupplier(supplierName);
    }

    @Override
    public List<Kulakan> findByMonthYearAndSupplier(String month, String year, String Supplier){
        return kulakanDao.findByMonthYearAndSupplier(month, year, Supplier);
    }

    @Override
    public String[] supllierName() {
        return supplierDao.supllierName();
    }

    @Override
    public List<Kulakan> findByAllParameter(String month, String year, String supplier, String namaBarang) {
        return kulakanDao.findByAllParameter(month, year, supplier, namaBarang);
    }

    @Override
    public List<Kulakan> findByBarangAndSupplier(String barang, String supplier) {
        return kulakanDao.findByBarangAndSupplier(barang, supplier);
    }

    @Override
    public List<Kulakan> findByBarang(String barang) {
        return kulakanDao.findByBarang(barang);
    }

    @Override
    public List<Kulakan> findByBarangAndTime(String month, String year, String barang) {
        return kulakanDao.findByBarangAndTime(month, year, barang);
    }

    @Override
    public void deleteData(Kulakan kulakan) {
        kulakanDao.deleteData(kulakan);
    }

    @Override
    public List<Supplier> getAllDataSupplier() {
        return supplierDao.getAllData();
    }

    @Override
    public List<Kulakan> findByDate(Date tanggalKulakan) {
        return kulakanDao.findByDate(tanggalKulakan);
    }

    @Override
    public void saveInBatch(List<Kulakan> kulakan) {
        kulakanDao.saveInBatch(kulakan);
    }   

    @Override
    public boolean checkNota(String nota) {
        return kulakanDao.checkNota(nota);
    }
    
}
