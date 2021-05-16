/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Supplier;
import java.util.List;

/**
 *
 * @author USER
 */
public interface SupplierService {
    public void addSupplier(Supplier supplier);
    public List<Supplier> getAllData();
    public List<Supplier> getAllDataByName(String name);
    public String generateCode();
    public String[] supllierName();
    public Supplier findBySupplierCode(String code);
    public void deleteData(Supplier supplier);
}
