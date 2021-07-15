/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.Supplier;
import java.util.List;

/**
 *
 * @author USER
 */
public interface SupplierDao {
    
    public void addSupplier(Supplier supplier);
    public List<Supplier> getAllData();
    public List<Supplier> getAllDataByName(String name);
    public String generateCode();
    public String[] suplierName();
    public Supplier findBySupplierCode(String code);
    public void deleteData(Supplier supplier);
}
