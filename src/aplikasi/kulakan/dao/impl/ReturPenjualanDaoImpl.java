/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.model.ReturPenjualan;
import aplikasi.kulakan.util.HibernateUtil;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import aplikasi.kulakan.dao.ReturDao;
import java.util.ArrayList;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;

/**
 *
 * @author USER
 */
public class ReturPenjualanDaoImpl implements ReturDao {
    
    private Session session;

    @Override
    public void addRetur(Object obj) {
        try{
            ReturPenjualan returPenjualan = (ReturPenjualan) obj;
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(returPenjualan);
            session.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan", "Sukses !", JOptionPane.INFORMATION_MESSAGE);
            session.flush();
        }catch(HibernateException e){
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
            listRetur = session.createCriteria(ReturPenjualan.class)
                   .addOrder(Order.desc("tanggalRetur")).list();
            for(Object obj: listRetur){
                ReturPenjualan retur = (ReturPenjualan)obj;
                if(retur.getBarang() != null){
                   Hibernate.initialize(retur.getBarang());
                }
            }
            session.flush();
        }catch(HibernateException e){
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
