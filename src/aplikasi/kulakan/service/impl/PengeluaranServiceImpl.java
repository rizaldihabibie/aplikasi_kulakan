/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.PengeluaranDao;
import aplikasi.kulakan.dao.impl.PengeluaranDaoImpl;
import aplikasi.kulakan.model.Pengeluaran;
import aplikasi.kulakan.service.PengeluaranService;
import java.util.Date;
import java.util.List;

/**
 *
 * @author guest
 */
public class PengeluaranServiceImpl implements PengeluaranService{
    private PengeluaranDao pengeluaranDao = new PengeluaranDaoImpl();
    
    @Override
    public void printToExcel(String path, List<Pengeluaran> listPengeluaran) {
        pengeluaranDao.printToExcel(path, listPengeluaran);
    }

    @Override
    public boolean save(Pengeluaran pengeluaran) {
        return pengeluaranDao.save(pengeluaran);
    }

    @Override
    public List<Pengeluaran> getAll() {
        return pengeluaranDao.getAll();
    }

    @Override
    public List<Pengeluaran> findByMonth(String month, String year) {
        return pengeluaranDao.findByMonth(month, year);
    }

    @Override
    public List<Pengeluaran> findByTitle(String title) {
        return pengeluaranDao.findByTitle(title);
    }

    @Override
    public List<Pengeluaran> findByTitleAndMonth(String title, String month, String year) {
        return pengeluaranDao.findByTitleAndMonth(title, month, year);
    }

    @Override
    public boolean delete(Pengeluaran pengeluaran) {
        return pengeluaranDao.delete(pengeluaran);
    }

    @Override
    public List<Pengeluaran> findByDate(Date date) {
        return pengeluaranDao.findByDate(date);
    }

    @Override
    public List<Pengeluaran> findByTitleAndDate(String title, Date date) {
        return pengeluaranDao.findByTitleAndDate(title, date);
    }
    
}
