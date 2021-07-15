/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.service.BarangService;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.impl.BarangServiceImpl;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.KulakanPage;
import java.awt.Font;
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
 * @author USER
 */
public class KulakanPageController implements BasicController {
    private KulakanPage kulakanPage;
    private KulakanService kulakanService;
    private BarangService barangService;
    private MainPageController mainPageController;
    private AddNewKulakanController addKulakan;
    private List<Kulakan> listKulakan;
    private String namaBarang;
    private int month, year,  namaSupplier;
    private String tanggalPencarian;
    private Double totalHarga;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger LOG = LogManager.getLogger(KulakanPageController.class);
    
    public KulakanPageController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
    }
    

    @Override
    public void init() {
        try{
            LOG.info("Preparing kulakan page");
            kulakanService = new KulakanServiceImpl();
            barangService = new BarangServiceImpl();
            kulakanPage = new KulakanPage();
            kulakanPage.getAddToCartButton().addActionListener(this::addNewKulakanAction);
            kulakanPage.getPrintButton().addActionListener(this::printButton);
            kulakanPage.getSearchButton().addActionListener(this::findData);
            kulakanPage.getRekapButton().addActionListener(this::openRekap);
            addKulakan = new AddNewKulakanController(this);
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        LOG.info("Loading kulakan page");
        try{
            getData();
            kulakanPage.getMonthOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
            kulakanPage.getTahunParameter().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
            kulakanPage.getSupplierNameOption().setModel(new DefaultComboBoxModel(kulakanService.suplierName()));
            viewOnTable();
        }catch(Exception e){
            LOG.error(e);
        }
    }

    @Override
    public void stop() {
        kulakanService = null;
        barangService = null;
        kulakanPage = null;
    }

    @Override
    public JPanel getPage() {
        return kulakanPage;
    }
    public void viewOnTable(){
        LOG.info("Presenting data to table");
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode Pembelian");
            model.addColumn("Tanggal");
            model.addColumn("Nama Supplier");
            model.addColumn("Nama Barang");
            model.addColumn("Jumlah");
    //        model.addColumn("Satuan");
            model.addColumn("Harga/Pembagian");
            model.addColumn("Diskon");
            model.addColumn("Total Harga");
            model.addColumn("Hapus");
            for(Kulakan kulakan : listKulakan){
                Object[] obj = new Object[10];
                obj[0] = kulakan.getKodePembelian();
                obj[1] = FormatDate.format(kulakan.getTanggalKulakan());
                obj[2] = kulakan.getSupplier().getNamaSupplier();
                obj[3] = kulakan.getBarang().getNamaBarang();
                obj[4] = kulakan.getJumlah();
    //            obj[5] = kulakan.getSatuan();
                if(kulakan.getBarang().getTipe()==0){
                    obj[5] = FormatRupiah.simpleConvert(String.valueOf(kulakan.getHargaNetto()));
                }else{
                    obj[5] = kulakan.getHargaNetto()+"%";
                }
                obj[6] = kulakan.getDiskon();
                if(kulakan.getDiskon()>0){
    //                totalHarga = ((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100));
                    totalHarga = (kulakan.getHargaNetto()*kulakan.getJumlah())-kulakan.getDiskon();
                    obj[7] = FormatRupiah.simpleConvert(String.valueOf(totalHarga.intValue()));
    //                System.out.println((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100));
                }else{
                     totalHarga = kulakan.getHargaNetto()*kulakan.getJumlah();
                     obj[7] = FormatRupiah.simpleConvert((String.valueOf(totalHarga.intValue())));
                }

                obj[8] = "Hapus";
                model.addRow(obj);
            }
            kulakanPage.getViewTable().setModel(model);

             Action delete  = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deleteData(listKulakan.get(modelRow));
                }
            };

            ButtonColumn buttonDelete = new ButtonColumn(kulakanPage.getViewTable(), delete, 8);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            kulakanPage.getViewTable().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            kulakanPage.getViewTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            kulakanPage.getViewTable().validate();
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
        
    }
    
    public void deleteData(Kulakan kulakan){
        LOG.info("Deleting data");
        try{
            List<Kulakan> existing = kulakanService.findByBarang(kulakan.getBarang().getIdBarang());
            if(existing.size() > 0){
                LOG.debug("Previous data exist. checking if it last data..");
                LOG.debug("Current data : "+kulakan.getKodePembelian());
                LOG.debug("Last data : "+existing.get(0).getKodePembelian());
                if(existing.get(0).getKodePembelian().equals(kulakan.getKodePembelian())){
                    LOG.debug("Updating harga barang");
                    kulakan.getBarang().setHarga(kulakan.getBarang().getHargaTerakhir());
                    barangService.updateData(kulakan.getBarang());
                    LOG.debug("Done");
                }
            }
            kulakanService.deleteData(kulakan);
            listKulakan = kulakanService.getAllData();
            getData();
            viewOnTable();
            LOG.info("Delete success");
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public MainPageController getParent(){
        return mainPageController;
    }
    
    public void addNewKulakanAction(java.awt.event.ActionEvent awt){
        addKulakan.getAddKulakan();
    }
    public void setSearchData(){
        addKulakan.setSearchData();
    }
    public KulakanService getService(){
        return kulakanService;
    }
    public void printButton(java.awt.event.ActionEvent e){
        PrintOptionController print = new PrintOptionController(this);
        print.openPrintDialog().setVisible(true);
    }
    public void findData(java.awt.event.ActionEvent e){
        LOG.info("Search data");
        try{
            month = kulakanPage.getMonthOption().getSelectedIndex();
            year = kulakanPage.getTahunParameter().getSelectedIndex();
            namaSupplier = kulakanPage.getSupplierNameOption().getSelectedIndex();
            namaBarang  = kulakanPage.getBarangField().getText();
            tanggalPencarian = kulakanPage.getTanggalField().getText();

            if( month != 0  && year !=0 && !tanggalPencarian.equals("") &&  namaSupplier != 0 && !namaBarang.equals("")){
                LOG.info("By all parameter");
                Date dateString = dateFormat.parse(tanggalPencarian+"/"+kulakanPage.getMonthOption().getSelectedIndex()+
                            "/"+kulakanPage.getTahunParameter().getSelectedItem().toString());
                listKulakan = kulakanService.findByAllParameter(dateString, kulakanPage.getSupplierNameOption().getSelectedItem().toString(), namaBarang);
            }else if( month != 0  && year !=0 && !tanggalPencarian.equals("") &&  namaSupplier == 0 && !namaBarang.equals("")){
                LOG.info("By barang and date");
                Date dateString = dateFormat.parse(tanggalPencarian+"/"+kulakanPage.getMonthOption().getSelectedIndex()+
                            "/"+kulakanPage.getTahunParameter().getSelectedItem().toString());
                listKulakan = kulakanService.findByBarangAndDate(dateString, namaBarang);
            }else if( month != 0  && year !=0 && !tanggalPencarian.equals("") &&  namaSupplier == 0 && namaBarang.equals("")){
                LOG.info("By date");
                Date dateString = dateFormat.parse(tanggalPencarian+"/"+kulakanPage.getMonthOption().getSelectedIndex()+
                            "/"+kulakanPage.getTahunParameter().getSelectedItem().toString());
                listKulakan = kulakanService.findByDate(dateString);
            }else if(month != 0  && year !=0 && tanggalPencarian.equals("") && namaSupplier != 0 && namaBarang.equals("")){
                LOG.info("By month year and supplier");
                listKulakan = kulakanService.findByMonthYearAndSupplier(""+kulakanPage.getMonthOption().getSelectedIndex(),kulakanPage.getTahunParameter().getSelectedItem().toString(), kulakanPage.getSupplierNameOption().getSelectedItem().toString());
            }else if(month != 0  && year !=0 && tanggalPencarian.equals("") && namaSupplier == 0 && namaBarang.equals("")){
                LOG.info("By month and year");
                listKulakan = kulakanService.findByMonthAndYear(""+kulakanPage.getMonthOption().getSelectedIndex(), kulakanPage.getTahunParameter().getSelectedItem().toString());
            }else if(month == 0  && year ==0 &&  tanggalPencarian.equals("") && namaSupplier != 0 && namaBarang.equals("")){
                LOG.info("By supplier");
                listKulakan = kulakanService.findyBySupplier(kulakanPage.getSupplierNameOption().getSelectedItem().toString());
            }else if(month == 0  && year ==0 &&  tanggalPencarian.equals("") && namaSupplier != 0 && !namaBarang.equals("")){
                LOG.info("By barang and supplier");
                listKulakan = kulakanService.findByBarangAndSupplier(namaBarang, kulakanPage.getSupplierNameOption().getSelectedItem().toString());
            }else if(month != 0  && year !=0 &&  tanggalPencarian.equals("") && namaSupplier == 0 && !namaBarang.equals("")){
                LOG.info("By barang and time");
                listKulakan = kulakanService.findByBarangAndTime(""+kulakanPage.getMonthOption().getSelectedIndex(),kulakanPage.getTahunParameter().getSelectedItem().toString(), namaBarang);
            }else if(month == 0  && year ==0 && tanggalPencarian.equals("") && namaSupplier == 0 && !namaBarang.equals("")){
                LOG.info("By barang");
                listKulakan = kulakanService.findByBarang(namaBarang);
            }
            viewOnTable();
        }catch(Exception ex){
            LOG.error(ex);
        }
    }
    public void getData(){
        listKulakan = kulakanService.getAllData();
    }
    
    public void openRekap(java.awt.event.ActionEvent e){
        LOG.info("Open rekap page");
        RekapController rekap = new RekapController(this);
        rekap.getRekap().setVisible(true);
        LOG.info("done");
    }
}
