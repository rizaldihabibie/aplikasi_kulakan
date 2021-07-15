/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.SupplierDao;
import aplikasi.kulakan.model.Supplier;
import aplikasi.kulakan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author USER
 */
public class SupplierDaoImpl implements SupplierDao {

    private Session session;
    private static Logger LOG = LogManager.getLogger(SupplierDaoImpl.class);

    @Override
    public void addSupplier(Supplier supplier) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(supplier);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan \n" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
    }

    @Override
    public List<Supplier> getAllData() {
        List<Supplier> listSupplier = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("From Supplier order by nama_supplier asc");
            listSupplier = q.list();
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Diakses \n" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listSupplier;
    }

    @Override
    public List<Supplier> getAllDataByName(String name) {
        List<Supplier> listSupplier = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listSupplier = session.createCriteria(Supplier.class).add(Restrictions.like("namaSupplier", name, MatchMode.ANYWHERE)).list();
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Diakses \n" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return listSupplier;
    }

    @Override
    public String generateCode() {
        String code = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from Supplier  a order by a.kodeSupplier desc limit 1");
            Supplier supplier = (Supplier) query.uniqueResult();
            if (supplier != null) {
                code = supplier.getKodeSupplier();
            }
            code = code.substring(3, code.length());
            int x = Integer.parseInt(code);
            x = x + 1;
            if (String.valueOf(x).length() == 1) {
                code = "SPL00" + x;
            } else if (String.valueOf(x).length() == 2) {
                code = "SPL0" + x;
            } else if (String.valueOf(x).length() == 3) {
                code = "SPL" + x;
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
        return code;
    }

    @Override
    public String[] suplierName() {
        String[] supplierName = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("From Supplier order by nama_supplier asc");
            List<Supplier> listSupplier = q.list();
            if (listSupplier != null) {
                supplierName = new String[(listSupplier.size()) + 1];
                supplierName[0] = "-- Pilih Supplier --";
                for (int i = 0; i < listSupplier.size(); i++) {
                    supplierName[i + 1] = listSupplier.get(i).getNamaSupplier();
                }
            }
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Diakses \n" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return supplierName;
    }

    @Override
    public Supplier findBySupplierCode(String code) {
        Supplier supplier = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            supplier = (Supplier) session.createCriteria(Supplier.class).add(Restrictions.eq("kodeSupplier", code)).uniqueResult();
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Diakses \n" + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
        return supplier;
    }

    @Override
    public void deleteData(Supplier supplier) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(supplier);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        } catch (HibernateException e) {
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Gagal Menghapus Data " + e, "Warning", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null) {
                if (session.isOpen()) {
                    session.close();
                }
            }
        }
    }
}
