/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao;

import aplikasi.kulakan.model.Pengeluaran;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guest
 */
public interface PengeluaranDao {
    
    public void printToExcel(String path,List<Pengeluaran> listPengeluaran);
    public boolean save(Pengeluaran pengeluaran);
    public boolean delete(Pengeluaran pengeluaran);
    public List<Pengeluaran> getAll();
    public List<Pengeluaran> findByMonth(String month, String year);
    public List<Pengeluaran> findByDate(Date date);
    public List<Pengeluaran> findByTitle(String title);
    public List<Pengeluaran> findByTitleAndMonth(String title, String month, String year);
    public List<Pengeluaran> findByTitleAndDate(String title, Date date);
}
