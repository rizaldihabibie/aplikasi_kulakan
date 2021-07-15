/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.SupplierDao;
import aplikasi.kulakan.dao.impl.SupplierDaoImpl;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.service.SupplierService;
import java.util.List;

/**
 *
 * @author USER
 */
public class SupplierServiceImpl implements SupplierService {
    
    private SupplierDao supplierDao = new SupplierDaoImpl();
    @Override
    public void addSupplier(Supplier supplier) {
        supplierDao.addSupplier(supplier);
    }

    @Override
    public List<Supplier> getAllData() {
        return supplierDao.getAllData();
    }

    @Override
    public List<Supplier> getAllDataByName(String name) {
        return supplierDao.getAllDataByName(name);
    }

    @Override
    public String generateCode() {
        return supplierDao.generateCode();
    }

    @Override
    public String[] suplierName() {
        return supplierDao.suplierName();
    }

    @Override
    public Supplier findBySupplierCode(String code) {
        return supplierDao.findBySupplierCode(code);
    }

    @Override
    public void deleteData(Supplier supplier) {
        supplierDao.deleteData(supplier);
    }
    
}
