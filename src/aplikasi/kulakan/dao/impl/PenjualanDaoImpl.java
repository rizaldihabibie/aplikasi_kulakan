/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.PenjualanDao;
import aplikasi.kulakan.dao.StockDao;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.util.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author USER
 */
public class PenjualanDaoImpl implements PenjualanDao {

    private Session session;
    private final StockDao stockDao = new StockDaoImpl();
    private static Logger LOG = LogManager.getLogger(PenjualanDaoImpl.class);

    @Override
    public List<Penjualan> getAllData() {
        List<Penjualan> listPenjualan = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public boolean saveData(Penjualan penjualan) {
        boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(penjualan);
            success = true;
        } catch (HibernateException e) {
            LOG.error(e);
        }
        if (success) {
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public boolean saveInBatch(List<Penjualan> listPenjualan) {
        boolean success = false;
        try {
            for (int i = 0; i < listPenjualan.size(); i++) {
                if (this.saveData(listPenjualan.get(i))) {
//                    if (stockDao.changeStock(listPenjualan.get(i))) {
                        if (i == 49) {
                            session.flush();
                            session.clear();
                        }
                        success = true;
//                    } else {
//                        success = false;
//                        JOptionPane.showMessageDialog(null, "Stock Gagal Disimpan", "Warning", JOptionPane.ERROR_MESSAGE);
//                        break;
//                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Data Gagal Disimpan", "Warning", JOptionPane.ERROR_MESSAGE);
                    success = false;
                    break;
                }

            }
            if (success) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (HibernateException e) {
            LOG.error(e);
        }
        return success;
    }

    @Override
    public List<Penjualan> findByDate(Date date) {
        List<Penjualan> listPenjualan = new ArrayList<Penjualan>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class)
                    .add(Restrictions.eq("tanggal", date)).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public List<Penjualan> findByProduct(String productName) {
        List<Penjualan> listPenjualan = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class, "penjualan")
                    .createAlias("penjualan.barang", "barang")
                    .add(Restrictions.like("barang.namaBarang", productName, MatchMode.ANYWHERE)).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public List<Penjualan> findByProductAndDate(String productName, String date) {
        List<Penjualan> listPenjualan = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date dateString = dateFormat.parse(date);
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class, "penjualan")
                    .createAlias("penjualan.barang", "barang")
                    .add(Restrictions.like("barang.namaBarang", productName, MatchMode.ANYWHERE))
                    .add(Restrictions.eq("tanggal", dateString)).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            LOG.error(ex);
            JOptionPane.showMessageDialog(null, "Gagal Convert Tanggal " + ex, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public List<Penjualan> findByMonthAndYear(String month, String year) {
        List<Penjualan> listPenjualan = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class)
                    .add(Restrictions.sqlRestriction("MONTH(tanggal) = '" + month + "' && YEAR(tanggal) = '" + year + "'")).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public List<Penjualan> findByProductAndMonthAndYear(String productName, String month, String year) {
        List<Penjualan> listPenjualan = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class, "penjualan")
                    .createAlias("penjualan.barang", "barang")
                    .add(Restrictions.eq("barang.namaBarang", productName))
                    .add(Restrictions.sqlRestriction("MONTH(tanggal) = '" + month + "' && YEAR(tanggal) = '" + year + "'")).list();
//            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
//            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listPenjualan;
    }

    @Override
    public boolean checkNota(String noNota) {
        List<Penjualan> listPenjualan = new ArrayList<Penjualan>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listPenjualan = session.createCriteria(Penjualan.class)
                    .add(Restrictions.eq("noNota", noNota)).list();
            if (!listPenjualan.isEmpty()) {
//                for (Penjualan penjualan : listPenjualan) {
//                    Hibernate.initialize(penjualan.getBarang());
//                }
                session.flush();
                return true;
            }

        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Mengakses data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteData(Penjualan penjualan) {
        boolean success = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(penjualan); 
            success = true;
        }catch(HibernateException e){
            LOG.error(e);
            e.printStackTrace();
        }
        if(success){
            session.getTransaction().commit();
        }
        return success;
    }

    @Override
    public boolean updateData(Penjualan penjualan) {
        boolean success = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(penjualan); 
            success = true;
        }catch(HibernateException e){
            LOG.error(e);
            e.printStackTrace();
        }
        if(success){
            session.getTransaction().commit();
        }
        return success;
    }

}
