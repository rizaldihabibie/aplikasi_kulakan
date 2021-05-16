/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.ReturPenjualan;
import java.util.List;

/**
 *
 * @author USER
 */
public interface ReturDao {
    
    public void addRetur(Object obj);
    public List<Object> showAllRetur();
    public List<Object> findByNamaBarang(String namaBarang);
    public List<Object> findByNota(String noNota);
}
