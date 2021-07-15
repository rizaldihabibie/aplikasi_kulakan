/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.Penjualan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public interface PenjualanDao {
    public List<Penjualan> getAllData();
    public boolean deleteData(Penjualan penjualan);
    public boolean updateData(Penjualan penjualan);
    public boolean saveData(Penjualan penjualan);
    public boolean saveInBatch(List<Penjualan> listPenjualan);
    public List<Penjualan> findByDate(Date date);
    public List<Penjualan> findByProduct(String productName);
    public List<Penjualan> findByProductAndDate(String productName, String date);
    public List<Penjualan> findByMonthAndYear(String month, String year);
    public List<Penjualan> findByProductAndMonthAndYear(String productName, String month, String year);
    public boolean checkNota(String noNota);
}
