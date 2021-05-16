/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.StockDao;
import aplikasi.kulakan.dao.impl.StockDaoImpl;
import aplikasi.kulakan.model.Stock;
import aplikasi.kulakan.service.StockService;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author USER
 */
public class StockServiceImpl implements StockService{

    private StockDao stockDao = new StockDaoImpl();
    
    @Override
    public Stock getLastStock(int idBarang) {
        return stockDao.getLastStock(idBarang);
    }

    @Override
    public List<Stock> getAllLastStock() {
        return stockDao.getAllLastStock();
    }

    @Override
    public List<Stock> findByName(String name) {
        return stockDao.findByName(name);
    }
    
}
