/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service;

import aplikasi.kulakan.model.Stock;
import java.util.List;

/**
 *
 * @author USER
 */
public interface StockService {
     public Stock getLastStock(int idBarang);
    public List<Stock> getAllLastStock();
    public List<Stock> findByName(String name);
}
