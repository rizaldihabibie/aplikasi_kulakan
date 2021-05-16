/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service;

import aplikasi.kulakan.model.Barang;
import java.util.List;

/**
 *
 * @author USER
 */
public interface BarangService {
    public List<Barang> getAllData();
    public boolean saveData(Barang barang);
    public Barang findByCode(String code);
    public List<Barang> findByName(String name);
    public void printToExcel(String path, List<Barang> listBarang);
    public List<Barang> findByCodeAndName(String code, String name);
    public void updateBarang(Barang barang);
    public void deleteData(Barang barang);
}
