/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.PenjualanDao;
import aplikasi.kulakan.dao.SupplierDao;
import aplikasi.kulakan.dao.impl.PenjualanDaoImpl;
import aplikasi.kulakan.dao.impl.SupplierDaoImpl;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.service.PenjualanService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class PenjualanServiceImpl implements PenjualanService {
    
    private final PenjualanDao penjualanDao = new PenjualanDaoImpl();
    private final SupplierDao supplierDao = new SupplierDaoImpl();
    
    @Override
    public List<Penjualan> getAllData() {
        return penjualanDao.getAllData();
    }

    @Override
    public boolean saveInBatch(List<Penjualan> listPenjualan) {
        return penjualanDao.saveInBatch(listPenjualan);
    }

    @Override
    public String[] suplierName() {
        return supplierDao.suplierName();
    }

    @Override
    public List<Penjualan> findByDate(Date date) {
        return penjualanDao.findByDate(date);
    }

    @Override
    public List<Penjualan> findByProduct(String productName) {
        return penjualanDao.findByProduct(productName);
    }

    @Override
    public List<Penjualan> findByProductAndDate(String productName, String date) {
        return penjualanDao.findByProductAndDate(productName, date);
    }

    @Override
    public List<Penjualan> findByMonthAndYear(String month, String year) {
        return penjualanDao.findByMonthAndYear(month, year);
    }

    @Override
    public List<Penjualan> findByProductAndMonthAndYear(String productName, String month, String year) {
        return penjualanDao.findByProductAndMonthAndYear(productName, month, year);
    }

    @Override
    public boolean checkNota(String noNota) {
        return penjualanDao.checkNota(noNota);
    }

    @Override
    public boolean saveData(Penjualan penjualan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteData(Penjualan penjualan) {
        return penjualanDao.deleteData(penjualan);
    }

    @Override
    public boolean updateData(Penjualan penjualan) {
        return penjualanDao.updateData(penjualan);
    }
    
}
