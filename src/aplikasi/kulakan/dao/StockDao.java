/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.model.Stock;
import java.util.List;

/**
 *
 * @author USER
 */
public interface StockDao {
    
    public boolean changeStock(Kulakan kulakan);
    public boolean changeStock(Penjualan Penjualan);
    public List<Stock> getAllData();
    public Stock getLastStock(int idBarang);
    public List<Stock> getAllLastStock();
    public List<Stock> findByName(String name);
    
}
