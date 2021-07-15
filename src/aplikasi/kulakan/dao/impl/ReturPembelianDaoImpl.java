/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.dao.ReturDao;
import aplikasi.kulakan.model.ReturPembelian;
import aplikasi.kulakan.model.ReturPenjualan;
import aplikasi.kulakan.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author USER
 */
public class ReturPembelianDaoImpl implements ReturDao {

    private Session session;
    private static Logger LOG = LogManager.getLogger(ReturPembelianDaoImpl.class);
    @Override
    public void addRetur(Object obj) {
        try{
            ReturPembelian returPembelian = (ReturPembelian) obj;
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(returPembelian);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
           JOptionPane.showMessageDialog(null, "Gagal Menyimpan Data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
    }

    @Override
    public List<Object> showAllRetur() {
        List<Object> listRetur = new ArrayList<>();
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            listRetur = session.createCriteria(ReturPembelian.class)
                   .addOrder(Order.desc("tanggalRetur")).list();
            for(Object obj: listRetur){
                ReturPembelian retur = (ReturPembelian)obj;
                if(retur.getBarang() != null){
                   Hibernate.initialize(retur.getBarang());
                }
            }
            session.flush();
        }catch(HibernateException e){
            LOG.error(e);
           JOptionPane.showMessageDialog(null, "Gagal Mengakses data "+e, "Warning", JOptionPane.ERROR_MESSAGE);
        }finally{
            if(session != null){
                if(session.isOpen()){
                    session.close();
                }
            }
        }
        return listRetur;
    }

    @Override
    public List<Object> findByNamaBarang(String namaBarang) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> findByNota(String noNota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
