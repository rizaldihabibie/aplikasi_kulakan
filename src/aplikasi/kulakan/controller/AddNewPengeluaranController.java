/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Pengeluaran;
import aplikasi.kulakan.service.PengeluaranService;
import aplikasi.kulakan.service.impl.PengeluaranServiceImpl;
import aplikasi.kulakan.view.AddPengeluaran;
import aplikasi.kulakan.view.PengeluaranPage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author guest
 */
public class AddNewPengeluaranController {
    private AddPengeluaran addPengeluaran = null;
    private PengeluaranService pengeluaranService = null;
    private PengeluaranController pengeluaranController = null;
    private DateFormat dateFormat = null;
    private static Logger LOG = LogManager.getLogger(AddNewPengeluaranController.class);
    
    public AddNewPengeluaranController(PengeluaranController pengeluaranController){
        try{
            LOG.info("Preparing add new pengeluaran page");
            this.pengeluaranController = pengeluaranController;
            addPengeluaran = new AddPengeluaran(pengeluaranController.getParent().getParent(), true);
            pengeluaranService = new PengeluaranServiceImpl();
            dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            addPengeluaran.getTanggal().setDateFormat(dateFormat);
            addPengeluaran.getAddButton().addActionListener(this::saveButtonAction);
            addPengeluaran.getJudulField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addPengeluaran.getHargaField().requestFocus();
                }
            });
            addPengeluaran.getHargaField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    addPengeluaran.getDeskripsiField().requestFocus();
                }
            });
            addPengeluaran.getAddButton().setMnemonic(KeyEvent.VK_S);
            LOG.info("Prepare done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
    public void showForm(){
        addPengeluaran.setVisible(true);
    }
    
    public void save(){
        try{
            LOG.info("Save data pengeleuaran");
            if(validation()){
                Pengeluaran pengeluaran = new Pengeluaran();
                pengeluaran.setJudul(addPengeluaran.getJudulField().getText());
                pengeluaran.setJumlah(Integer.valueOf(addPengeluaran.getHargaField().getText()));
                pengeluaran.setDeskripsi(addPengeluaran.getDeskripsiField().getText());
                pengeluaran.setTanggal(addPengeluaran.getTanggal().getSelectedDate().getTime());
                if(pengeluaranService.save(pengeluaran)){
                    addPengeluaran.getJudulField().setText("");
                    addPengeluaran.getDeskripsiField().setText("");
                    addPengeluaran.getHargaField().setText("");
                    pengeluaranController.showAllData();
                    pengeluaranController.viewOnTable();
                    LOG.info("Save success");
                    JOptionPane.showMessageDialog(addPengeluaran, "Data Berhasil Disimpan", "Info", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(addPengeluaran, "Data gagal disimpan", "Warning", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                LOG.info("Saving failed. data not valid");
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public boolean validation(){
        
        if(addPengeluaran.getJudulField().getText() == null || addPengeluaran.getJudulField().getText().equals("")){
            LOG.warn("Empty title");
            JOptionPane.showMessageDialog(addPengeluaran, "Judul tidak boleh kosong", "Warning", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(addPengeluaran.getHargaField().getText() == null || addPengeluaran.getHargaField().getText().equals("")){
            LOG.warn("Empty price");
            JOptionPane.showMessageDialog(addPengeluaran, "Harga tidak boleh kosong", "Warning", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(addPengeluaran.getTanggal().getText() == null || addPengeluaran.getTanggal().getText().equals("")){
            LOG.warn("Empty date");
            JOptionPane.showMessageDialog(addPengeluaran, "Tanggal tidak boleh kosong", "Warning", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void saveButtonAction(java.awt.event.ActionEvent e){
        this.save();
    }
}
