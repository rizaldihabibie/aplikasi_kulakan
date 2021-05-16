/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service;

import aplikasi.kulakan.model.ReturPenjualan;
import java.util.List;

/**
 *
 * @author USER
 */
public interface ReturService {
    public void addRetur(Object obj);
    public List<Object> showAllRetur();
    public List<ReturPenjualan> findByNamaBarang(String namaBarang);
    public List<ReturPenjualan> findByNota(String noNota);
}
