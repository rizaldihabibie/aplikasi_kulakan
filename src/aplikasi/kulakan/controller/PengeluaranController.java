/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Pengeluaran;
import aplikasi.kulakan.service.PengeluaranService;
import aplikasi.kulakan.service.impl.PengeluaranServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.PengeluaranPage;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author guest
 */
public class PengeluaranController implements BasicController {
    private PengeluaranPage pengeluaranPage;
    private PengeluaranService pengeluaranService;
    private MainPageController mainPageController;
    private AddNewPengeluaranController addNewPengeluaranController;
    private String title;
    private int month, year;
    private String tanggalPencarian;
    private List<Pengeluaran> listPengeluaran;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger LOG = LogManager.getLogger(PengeluaranController.class);
    
    public PengeluaranController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    @Override
    public void init() {
        try{
            LOG.info("Preparing pengeluaran page");
            this.pengeluaranService = new PengeluaranServiceImpl();
            this.pengeluaranPage = new PengeluaranPage();
            this.pengeluaranPage.getMonthOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
            this.pengeluaranPage.getTahunParameter().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
            this.pengeluaranPage.getAddButton().addActionListener(this::addNewPengeluaranAction);
            this.pengeluaranPage.getSearchButton().addActionListener(this::search);
            this.addNewPengeluaranController = new AddNewPengeluaranController(this);
            this.pengeluaranPage.getPrintButton().addActionListener(this::printButton);
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        pengeluaranPage.setSize(mainPageController.getParent().getMenuLayer().getSize());
        pengeluaranPage.setVisible(true);
        this.showAllData();
        this.viewOnTable();
    }

    @Override
    public void stop() {
        pengeluaranPage.setVisible(false);
        pengeluaranService = null;
        pengeluaranPage = null;
        addNewPengeluaranController = null;
    }

    @Override
    public JPanel getPage() {
        return pengeluaranPage;
    }
    
    public void showAllData(){
        try{
            LOG.info("Retrieve all data pengeluaran");
            listPengeluaran = pengeluaranService.getAll();
        }catch(Exception e){
            LOG.error(e);
        }
        
    }
    public void viewOnTable(){
        LOG.info("Presenting data pengeluarn to table");
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No. ");
            model.addColumn("Tanggal");
            model.addColumn("Nama");
            model.addColumn("deskripsi");
            model.addColumn("Harga");
            model.addColumn("Hapus");
            for(Pengeluaran pengeluaran : listPengeluaran){
                Object[] obj = new Object[6];
                obj[0] = pengeluaran.getIdPengeluaran();
                obj[1] = FormatDate.format(pengeluaran.getTanggal());
                obj[2] = pengeluaran.getJudul();
                obj[3] = pengeluaran.getDeskripsi();
                obj[4] = FormatRupiah.simpleConvert(String.valueOf(pengeluaran.getJumlah()));
                obj[5] = "Hapus";
                model.addRow(obj);
            }
            pengeluaranPage.getViewTable().setModel(model);
             Action delete  = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deleteData(listPengeluaran.get(modelRow));
                }
            };

            ButtonColumn buttonDelete = new ButtonColumn(pengeluaranPage.getViewTable(), delete, 5);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            pengeluaranPage.getViewTable().validate();

            int totalPengeluaran = 0;
            for(Pengeluaran p : listPengeluaran){
                totalPengeluaran = totalPengeluaran + p.getJumlah();
            }
            pengeluaranPage.getTotalPengeluaranLabel().setText(FormatRupiah.convert(String.valueOf(totalPengeluaran)));
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
    public MainPageController getParent(){
        return mainPageController;
    }
    
    public void addNewPengeluaranAction(java.awt.event.ActionEvent awt){
        addNewPengeluaranController.showForm();
    }
    
    public void search(java.awt.event.ActionEvent e){
        try{
            LOG.info("Search data");
            month = pengeluaranPage.getMonthOption().getSelectedIndex();
            year = pengeluaranPage.getTahunParameter().getSelectedIndex();
            tanggalPencarian  = pengeluaranPage.getTanggalField().getText();
            title  = pengeluaranPage.getJudulField().getText();
            if( (month != 0  && year !=0) && (!tanggalPencarian.equals("") && !title.equals(""))){
                LOG.info("By title and date");
                Date dateString = dateFormat.parse(tanggalPencarian+"/"+month+
                            "/"+pengeluaranPage.getTahunParameter().getSelectedItem().toString());
                listPengeluaran = pengeluaranService.findByTitleAndDate(title,dateString);
            }else if((month != 0  && year !=0) && (!tanggalPencarian.equals("") && title.equals(""))){
                LOG.info("By date");
                Date dateString = dateFormat.parse(tanggalPencarian+"/"+month+
                            "/"+pengeluaranPage.getTahunParameter().getSelectedItem().toString());
                listPengeluaran = pengeluaranService.findByDate(dateString);
            }else if((month != 0  && year !=0) && (tanggalPencarian.equals("") && !title.equals(""))){
                LOG.info("By title and month");
                listPengeluaran = pengeluaranService.findByTitleAndMonth(title,""+month,pengeluaranPage.getTahunParameter().getSelectedItem().toString());
            }else if(month != 0  && year !=0 && title.equals("")){
                LOG.info("By month");
                listPengeluaran = pengeluaranService.findByMonth(""+month,pengeluaranPage.getTahunParameter().getSelectedItem().toString());
            }else if(month == 0  && year ==0 && !title.equals("")){
                LOG.info("By title");
                listPengeluaran = pengeluaranService.findByTitle(title);
            }
            viewOnTable();
        }catch(Exception ex){
            LOG.error(ex);
        }
    }
    
    public void deleteData(Pengeluaran pengeluaran){
        try{
            LOG.info("Deleting data");
            pengeluaranService.delete(pengeluaran);
            listPengeluaran = pengeluaranService.getAll();
            viewOnTable();
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    
    public void printButton(java.awt.event.ActionEvent e){
        try{
            LOG.info("Print data pengeluaran");
            PrintOptionController print = new PrintOptionController(this);
            print.openPrintDialog().setVisible(true);
        }catch(Exception ex){
            LOG.error(ex);
        }
    }

    public PengeluaranService getPengeluaranService() {
        return pengeluaranService;
    }
}
