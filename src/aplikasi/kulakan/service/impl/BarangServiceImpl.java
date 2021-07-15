/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.BarangDao;
import aplikasi.kulakan.dao.impl.BarangDaoImpl;
import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.service.BarangService;
import java.util.List;

/**
 *
 * @author USER
 */
public class BarangServiceImpl implements BarangService{

    private BarangDao barangDao = new BarangDaoImpl();
    
    @Override
    public List<Barang> getAllData() {
        return barangDao.getAllData();
    }

    @Override
    public boolean saveData(Barang barang) {
       return barangDao.saveData(barang);
    }

    @Override
    public List<Barang> findByCode(String code) {
        return barangDao.findByCode(code);
    }

    @Override
    public List<Barang> findByName(String name) {
        return barangDao.findByName(name);
    }

    @Override
    public void printToExcel(String path, List<Barang> listBarang) {
        barangDao.printToExcel(path,listBarang);
    }

    @Override
    public List<Barang> findByCodeAndName(String code, String name) {
       return barangDao.findByCodeAndName(code, name);
    }

    @Override
    public boolean updateData(Barang barang) {
        return barangDao.updateData(barang);
    }

    @Override
    public void deleteData(Barang barang) {
        barangDao.deleteData(barang);
    }

    @Override
    public boolean saveData(List<Barang> listBarang) {
        return barangDao.saveData(listBarang);
    }
    
}
