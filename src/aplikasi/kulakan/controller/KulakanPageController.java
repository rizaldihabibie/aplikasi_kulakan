/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Kulakan;
import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.impl.KulakanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.KulakanPage;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class KulakanPageController {
    private final KulakanPage kulakanPage;
    private final KulakanService kulakanService = new KulakanServiceImpl();
    private final MainPageController mainPageController;
    private final AddNewKulakanController addKulakan;
    private List<Kulakan> listKulakan;
    private String namaBarang;
    private int month, year,  namaSupplier;
    private Double totalHarga;
    
    
    public KulakanPageController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
        kulakanPage = new KulakanPage();
        kulakanPage.getAddToCartButton().addActionListener(this::addNewKulakanAction);
        kulakanPage.getPrintButton().addActionListener(this::printButton);
        kulakanPage.getSearchButton().addActionListener(this::findData);
         kulakanPage.getRekapButton().addActionListener(this::openRekap);
        addKulakan = new AddNewKulakanController(this);
        
    }
    
    public void viewOnTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Pembelian");
        model.addColumn("Tanggal");
        model.addColumn("Nama Supplier");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Satuan");
        model.addColumn("Harga Netto");
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
            obj[5] = kulakan.getSatuan();
            obj[6] = FormatRupiah.convert(String.valueOf(kulakan.getHargaNetto()));
            obj[7] = kulakan.getDiskon();
            if(kulakan.getDiskon()>0){
                totalHarga = ((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100));
                obj[8] = FormatRupiah.convert(String.valueOf(totalHarga.intValue()));
                System.out.println((kulakan.getHargaNetto()*kulakan.getJumlah())-(kulakan.getHargaNetto()*kulakan.getJumlah())*(kulakan.getDiskon()/100));
            }else{
                 totalHarga = kulakan.getHargaNetto()*kulakan.getJumlah();
                 obj[8] = FormatRupiah.convert((String.valueOf(totalHarga.intValue())));
            }

            obj[9] = "Hapus";
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
                viewOnTable();
            }
        };
        
        ButtonColumn buttonDelete = new ButtonColumn(kulakanPage.getViewTable(), delete, 9);
        buttonDelete.setMnemonic(KeyEvent.VK_D);
        kulakanPage.getViewTable().validate();
        
    }
    
    public KulakanPage getKulakanPage(){
        listKulakan = kulakanService.getAllData();
        kulakanPage.getMonthOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        kulakanPage.getTahunParameter().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
        kulakanPage.getSupplierNameOption().setModel(new DefaultComboBoxModel(kulakanService.supllierName()));
        viewOnTable();
        return kulakanPage;
    }
    
    public void deleteData(Kulakan kulakan){
        kulakanService.deleteData(kulakan);
        listKulakan = kulakanService.getAllData();
    }
    public MainPageController getParent(){
        return mainPageController;
    }
    
    public void addNewKulakanAction(java.awt.event.ActionEvent awt){
        addKulakan.getAddKulakan();
    }
    
    public KulakanService getService(){
        return kulakanService;
    }
    public void printButton(java.awt.event.ActionEvent e){
        PrintOptionController print = new PrintOptionController(this);
        print.openPrintDialog().setVisible(true);
    }
    public void findData(java.awt.event.ActionEvent e){
        month = kulakanPage.getMonthOption().getSelectedIndex();
        year = kulakanPage.getTahunParameter().getSelectedIndex();
        namaSupplier = kulakanPage.getSupplierNameOption().getSelectedIndex();
        namaBarang  = kulakanPage.getBarangField().getText();
        
        if( month != 0  && year !=0 &&  namaSupplier != 0 && !namaBarang.equals("")){
            listKulakan = kulakanService.findByAllParameter(""+kulakanPage.getMonthOption().getSelectedIndex(),kulakanPage.getTahunParameter().getSelectedItem().toString(), kulakanPage.getSupplierNameOption().getSelectedItem().toString(), namaBarang);
        }else if(month != 0  && year !=0 &&  namaSupplier != 0 && namaBarang.equals("")){
            listKulakan = kulakanService.findByMonthYearAndSupplier(""+kulakanPage.getMonthOption().getSelectedIndex(),kulakanPage.getTahunParameter().getSelectedItem().toString(), kulakanPage.getSupplierNameOption().getSelectedItem().toString());
        }else if(month != 0  && year !=0 &&  namaSupplier == 0 && namaBarang.equals("")){
            listKulakan = kulakanService.findByMonthAndYear(""+kulakanPage.getMonthOption().getSelectedIndex(), kulakanPage.getTahunParameter().getSelectedItem().toString());
        }else if(month == 0  && year ==0 &&  namaSupplier != 0 && namaBarang.equals("")){
            listKulakan = kulakanService.findyBySupplier(kulakanPage.getSupplierNameOption().getSelectedItem().toString());
        }else if(month == 0  && year ==0 &&  namaSupplier != 0 && !namaBarang.equals("")){
            listKulakan = kulakanService.findByBarangAndSupplier(namaBarang, kulakanPage.getSupplierNameOption().getSelectedItem().toString());
        }else if(month != 0  && year !=0 &&  namaSupplier == 0 && !namaBarang.equals("")){
            listKulakan = kulakanService.findByBarangAndTime(""+kulakanPage.getMonthOption().getSelectedIndex(),kulakanPage.getTahunParameter().getSelectedItem().toString(), namaBarang);
        }else if(month == 0  && year ==0 &&  namaSupplier == 0 && !namaBarang.equals("")){
            listKulakan = kulakanService.findByBarang(namaBarang);
        }
        viewOnTable();
    }
    public void getData(){
        listKulakan = kulakanService.getAllData();
    }
    
    public void openRekap(java.awt.event.ActionEvent e){
        RekapController rekap = new RekapController(this);
        rekap.getRekap().setVisible(true);
    }
}
