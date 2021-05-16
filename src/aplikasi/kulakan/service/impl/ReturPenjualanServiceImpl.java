/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.impl.ReturPenjualanDaoImpl;
import aplikasi.kulakan.model.ReturPenjualan;
import java.util.List;
import aplikasi.kulakan.dao.ReturDao;
import aplikasi.kulakan.service.ReturService;

/**
 *
 * @author USER
 */
public class ReturPenjualanServiceImpl implements ReturService {
    ReturDao returDao = new ReturPenjualanDaoImpl();
    
    @Override
    public void addRetur(Object obj) {
        returDao.addRetur(obj);
    }

    @Override
    public List<Object> showAllRetur() {
        return returDao.showAllRetur();
    }

    @Override
    public List<ReturPenjualan> findByNamaBarang(String namaBarang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReturPenjualan> findByNota(String noNota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
