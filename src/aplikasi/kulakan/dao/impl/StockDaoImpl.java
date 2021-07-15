/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.StockDao;
import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.model.Stock;
import aplikasi.kulakan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author USER
 */
public class StockDaoImpl implements StockDao {

    private Session session;
    private double jumlahSebelum, jumlahDitambah, jumlahSesudah, convertSatuanBesar;
    private Stock stockTerakhir, stockTerbaru;
    private static Logger LOG = LogManager.getLogger(BarangDaoImpl.class);

    @Override
    public boolean changeStock(Kulakan kulakan) {
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            if(!kulakan.getBarang().getSatuanTerbesar().isEmpty()){
//                if(kulakan.getBarang().getSatuanTerbesar().equals(kulakan.getSatuan())){
//                    convertSatuanBesar = kulakan.getJumlah()*kulakan.getBarang().getJmlSatuanTerkecil();
//                    jumlahDitambah = convertSatuanBesar;
//                }else{
//                    jumlahDitambah = kulakan.getJumlah();
//                }
//            }else{
            jumlahDitambah = kulakan.getJumlah();
//            }
            stockTerakhir = this.getLastStock(kulakan.getBarang().getIdBarang());
            if (stockTerakhir != null) {
                jumlahSebelum = stockTerakhir.getJumlahStock();
                stockTerakhir.setStatus("STOCK_LAMA");
                session.update(stockTerakhir);
                stockTerbaru = new Stock();
                stockTerbaru.setBarang(stockTerakhir.getBarang());
                stockTerbaru.setStatus("STOCK_BARU");
                stockTerbaru.setTanggal(kulakan.getTanggalKulakan());
                jumlahSesudah = jumlahSebelum + jumlahDitambah;
                stockTerbaru.setJumlahStock(jumlahSesudah);
            } else {
                jumlahSebelum = 0;
                stockTerbaru = new Stock();
                stockTerbaru.setBarang(kulakan.getBarang());
                stockTerbaru.setStatus("STOCK_BARU");
                stockTerbaru.setTanggal(kulakan.getTanggalKulakan());
                jumlahSesudah = jumlahSebelum + jumlahDitambah;
                stockTerbaru.setJumlahStock(jumlahSesudah);
            }

            session.save(stockTerbaru);
            success = true;
            session.flush();
        } catch (HibernateException e) {
            success = false;
            LOG.error(e);
            session.getTransaction().rollback();
        }
        if (success) {
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public boolean changeStock(Penjualan penjualan) {
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            if(!penjualan.getBarang().getSatuanTerbesar().isEmpty()){
//                if(penjualan.getBarang().getSatuanTerbesar().equals(penjualan.getSatuan())){
//                    convertSatuanBesar = penjualan.getQty()*penjualan.getBarang().getJmlSatuanTerkecil();
//                    jumlahDitambah = convertSatuanBesar;
//                }else{
//                    jumlahDitambah = penjualan.getQty();
//                }
//            }else{
            jumlahDitambah = penjualan.getQty();
//            }
            stockTerakhir = this.getLastStock(penjualan.getIdBarang());
            if (stockTerakhir != null) {
                jumlahSebelum = stockTerakhir.getJumlahStock();
                stockTerakhir.setStatus("STOCK_LAMA");
                session.update(stockTerakhir);
                stockTerbaru = new Stock();
                stockTerbaru.setBarang(stockTerakhir.getBarang());
                stockTerbaru.setStatus("STOCK_BARU");
                stockTerbaru.setTanggal(penjualan.getTanggal());
                jumlahSesudah = jumlahSebelum - jumlahDitambah;
                stockTerbaru.setJumlahStock(jumlahSesudah);
                session.save(stockTerbaru);
                success = true;
                session.flush();
            } else {
                success = false;
                JOptionPane.showMessageDialog(null, "Jumlah Stock Kosong", "Warning", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HibernateException e) {
            success = false;
            LOG.error(e);
            session.getTransaction().rollback();
        }
        if (success) {
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public List<Stock> getAllData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Stock getLastStock(int idBarang) {
        Stock stock = new Stock();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            stock = (Stock) session.createCriteria(Stock.class, "stock")
                    .createAlias("stock.barang", "barang")
                    .add(Restrictions.eq("barang.idBarang", idBarang))
                    .add(Restrictions.eq("status", "STOCK_BARU")).uniqueResult();
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return stock;
    }

    @Override
    public List<Stock> getAllLastStock() {
        List<Stock> listStock = new ArrayList<Stock>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listStock = session.createCriteria(Stock.class, "stock")
                    .createAlias("stock.barang", "barang")
                    .add(Restrictions.eq("status", "STOCK_BARU"))
                    .addOrder(Order.asc("barang.namaBarang")).list();

            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return listStock;
    }

    @Override
    public List<Stock> findByName(String name) {
        List<Stock> listStock = new ArrayList<Stock>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listStock = session.createCriteria(Stock.class, "stock")
                    .createAlias("stock.barang", "barang")
                    .add(Restrictions.like("barang.namaBarang", name, MatchMode.ANYWHERE))
                    .add(Restrictions.eq("status", "STOCK_BARU"))
                    .addOrder(Order.asc("barang.namaBarang")).list();

            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        }
        return listStock;
    }

}
