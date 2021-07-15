/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.model.Barang;
import aplikasi.kulakan.model.Penjualan;
import aplikasi.kulakan.model.ReturPenjualan;
import aplikasi.kulakan.service.PenjualanService;
import aplikasi.kulakan.service.impl.PenjualanServiceImpl;
import aplikasi.kulakan.service.impl.ReturPenjualanServiceImpl;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.util.FormatDate;
import aplikasi.kulakan.util.FormatRupiah;
import aplikasi.kulakan.view.PenjualanPage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import aplikasi.kulakan.service.ReturService;
import aplikasi.kulakan.util.BarangGlazedFilterator;
import aplikasi.kulakan.util.ButtonColumn;
import aplikasi.kulakan.view.EditPenjualan;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.text.JTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class PenjualanController implements BasicController {

    private PenjualanPage penjualanPage;
    private EditPenjualan editPenjualan;
    private List<Penjualan> listDataPenjualan;
    private MainPageController mainPageController;
    private PenjualanService penjualanService;
    private ReturService returService;
    private List<Penjualan> listPenjualan;
    private List<Barang> listBarang;
    private PilihBarangController pilihBarang;
    private Barang barang;
    private int hargaSatuan;
    private Double diskon, totalHarga;
    private DateFormat dateFormat;
    private SimpleDateFormat dateFormatDatabase = new SimpleDateFormat("dd/MM/yyyy");
    private boolean retur;
    private AutoCompleteSupport autoCompleteSupport;
    private Double totalBelanja = 0.0;
    private List<ReturPenjualan> listRetur;
    private SwingWorker<Void, Void> worker;
    private LoadingController loadingController;
    private static Logger LOG = LogManager.getLogger(PenjualanController.class);

    public PenjualanController(MainPageController mainPage) {
        this.mainPageController = mainPage;
    }
    
    @Override
    public void init() {
        try {
            LOG.info("Preparing penjualan page");
            //ini untuk data penjualan
            this.listDataPenjualan = new ArrayList<>();
            this.penjualanService = new PenjualanServiceImpl();
            this.returService = new ReturPenjualanServiceImpl();
            this.penjualanPage = new PenjualanPage();
            this.penjualanPage.getFindButton().addActionListener(this::search);

            //ini untuk penjualan
            listPenjualan = new ArrayList<>();
            penjualanPage.getAddBarangRetur().addActionListener(this::chooseProductRetur);
            penjualanPage.getTotalHargaField().addActionListener(this::addData);
            penjualanPage.getSaveButton().addActionListener(this::save);
            penjualanPage.getSaveReturButton().addActionListener(this::saveRetur);
            penjualanPage.getAddButton().addActionListener(this::addData);

            JTextComponent editor = (JTextComponent) penjualanPage.getNamaBarangField().getEditor().getEditorComponent();
            editor.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (penjualanPage.getNamaBarangField().getSelectedItem() != null) {
                            getSelectedBarang(null);
                        }
                    }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
                        penjualanPage.getNotaField().requestFocus();
                    }
                }
            });
            editor.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    if (penjualanPage.getNamaBarangField().getSelectedItem() != null) {
                        getSelectedBarang(null);
                    }
                }
                
            });
            penjualanPage.getNotaField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    penjualanPage.getNamaBarangField().requestFocus();
                }
            });
            penjualanPage.getJumlahField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    penjualanPage.getHargaSatuanField().requestFocus();
                }
            });
            penjualanPage.getHargaSatuanField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    penjualanPage.getDiskonField().requestFocus();
                }
            });
            penjualanPage.getDiskonField().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    penjualanPage.getTotalHargaField().requestFocus();
                }
            });
            penjualanPage.getHargaSatuanField().addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    HargaSatuanFieldFocusGained(evt);
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent evt) {
                    HargaSatuanFieldFocusLost(evt);
                }
            });

            penjualanPage.getDiskonField().addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    diskonFieldFocusGained(evt);
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent evt) {
                    diskonFieldFocusLost(evt);
                }
            });
            penjualanPage.getTotalHargaField().addFocusListener(new java.awt.event.FocusAdapter() {
                @Override
                public void focusGained(java.awt.event.FocusEvent evt) {
                    totalFieldFocusGained(evt);
                }

            });
            penjualanPage.getTotalHargaField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        penjualanPage.getDiskonField().requestFocus();
                    }
                }
            });
            penjualanPage.getDiskonField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        penjualanPage.getHargaSatuanField().requestFocus();
                    }
                }
            });
            penjualanPage.getHargaSatuanField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        penjualanPage.getJumlahField().requestFocus();
                    }
                }
            });
            penjualanPage.getJumlahField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        penjualanPage.getNamaBarangField().requestFocus();
                    }
                }
            });
            penjualanPage.getNamaBarangField().addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent ke) {
                    if(ke.getKeyCode()== KeyEvent.VK_ESCAPE){
                        penjualanPage.getNoNotaReturField().requestFocus();
                    }
                }
            });
            diskon = null;
            dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            penjualanPage.getTanggalField().setDateFormat(dateFormat);
            penjualanPage.getTanggalRetur().setDateFormat(dateFormat);
            penjualanPage.getNotaField().requestFocus();
            LOG.info("done");
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void start() {
        this.penjualanPage.setSize(mainPageController.getParent().getMenuLayer().getSize());
        this.penjualanPage.setVisible(true);
        this.showAllDataRetur();
        this.viewDataReturOnTable();
        penjualanPage.getBulanParam().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        penjualanPage.getTahunParam().setModel(new DefaultComboBoxModel(Bulan.daftarTahun()));
    }
    
    @Override
    public JPanel getPage() {
        return this.penjualanPage;
    }

    @Override
    public void stop() {
        this.listDataPenjualan = null;
        this.penjualanService = null;
        this.returService = null;
        this.penjualanPage = null;
    }
    
    public void getSelectedBarang(ActionEvent e) {
        try{
            this.setBarang((Barang) penjualanPage.getNamaBarangField().getSelectedItem());
            penjualanPage.getJumlahField().requestFocus();
        }catch(Exception ex){
            LOG.error(ex);
        }
    }

    public void setSearchData() {
        try {
            LOG.info("Preparing data field");
            if (pilihBarang == null) {
                pilihBarang = new PilihBarangController(this);
            }
            listBarang = pilihBarang.getAllDataBarang();
//        penjualanPage.getNamaBarangField().setModel(new DefaultComboBoxModel(listBarang.toArray()));
//        AutoCompleteDecorator.decorate(penjualanPage.getNamaBarangField());
            LOG.debug("insert data to Glazed list");
            EventList<Barang> barangs = new BasicEventList<Barang>();
            for (Barang barang : listBarang) {
                barangs.add(barang);
            }
            LOG.debug("Preparing autocomplete");
            try {
                if (autoCompleteSupport != null && autoCompleteSupport.isInstalled()) {
                    autoCompleteSupport.uninstall();
                }
                autoCompleteSupport = AutoCompleteSupport.install(penjualanPage.getNamaBarangField(), barangs, new BarangGlazedFilterator());
            } catch (IllegalArgumentException e) {
                LOG.error(e);
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public MainPageController getParent() {
        return mainPageController;
    }

    public void showAllData() {
        listDataPenjualan = penjualanService.getAllData();
    }

    public void showAllDataRetur() {
        List<Object> list = returService.showAllRetur();
        listRetur = new ArrayList<>();
        for (Object obj : list) {
            listRetur.add((ReturPenjualan) obj);
        }
    }

    public void viewOnTable() {
        try {
            LOG.info("Presenting "+listDataPenjualan.size()+" data on table");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Nota");
            model.addColumn("Tanggal");
            model.addColumn("Nama Barang");
            model.addColumn("Jumlah");
            //        model.addColumn("Satuan");
            model.addColumn("Harga Satuan");
            model.addColumn("Diskon");
            model.addColumn("Kulak/Pembagian");
            model.addColumn("Total Harga");
            model.addColumn("margin");
            model.addColumn("Edit");
            model.addColumn("Hapus");
            
            int totalMargin = 0;
            for (Penjualan penjualan : listDataPenjualan) {
                totalMargin = totalMargin + penjualan.getMargin();
            }
            penjualanPage.getTotalMarginLabel().setText(FormatRupiah.convert(String.valueOf(totalMargin)));
            listDataPenjualan.stream().map((penjualan) -> {
                Object[] obj = new Object[11];
                obj[0] = penjualan.getNoNota();
                obj[1] = FormatDate.format(penjualan.getTanggal());
                obj[2] = penjualan.getNamaBarang();
                obj[3] = penjualan.getQty();
                //            obj[3] = penjualan.getSatuan();
                obj[4] = FormatRupiah.simpleConvert(String.valueOf(penjualan.getHargaSatuan()));
                obj[5] = penjualan.getDiskon();
                if(penjualan.getTipeBarang() == 0){
                    obj[6] = FormatRupiah.simpleConvert(String.valueOf(penjualan.getHargaKulak()));
                }else{
                    obj[6] = penjualan.getHargaKulak()+"%";
                }
                obj[7] = FormatRupiah.simpleConvert(String.valueOf(penjualan.getTotalHarga()));
                obj[8] = FormatRupiah.simpleConvert(String.valueOf(penjualan.getMargin()));
                obj[9] = "Edit";
                obj[10] = "Hapus";
                return obj;
            }).forEach((obj) -> {
                model.addRow(obj);
            });
            penjualanPage.getViewTable().setModel(model);
            Action update = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    openEditPage(listDataPenjualan.get(modelRow));
                }
            };

            Action delete  = new AbstractAction()
            {
            @Override
            public void actionPerformed(ActionEvent e)
                {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deleteData(listDataPenjualan.get(modelRow));
                }
            };
            ButtonColumn buttonUpdate = new ButtonColumn(penjualanPage.getViewTable(), update, 9);
            buttonUpdate.setMnemonic(KeyEvent.VK_D);

            ButtonColumn buttonDelete = new ButtonColumn(penjualanPage.getViewTable(), delete, 10);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            penjualanPage.getViewTable().getColumn("Nota").setPreferredWidth(5);
            penjualanPage.getViewTable().getColumn("Tanggal").setPreferredWidth(10);
            penjualanPage.getViewTable().getColumn("margin").setPreferredWidth(10);
            penjualanPage.getViewTable().getColumn("Jumlah").setPreferredWidth(8);
            penjualanPage.getViewTable().getColumn("Edit").setPreferredWidth(8);
            penjualanPage.getViewTable().getColumn("Hapus").setPreferredWidth(9);
            penjualanPage.getViewTable().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
            penjualanPage.getViewTable().getTableHeader().setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            LOG.info("done");
        } catch (Exception e) {
            LOG.error(e);
            e.printStackTrace();
        }
    }
    public void openEditPage(Penjualan penjualan){
        editPenjualan =  new EditPenjualan(mainPageController.getParent(), true);
        editPenjualan.getNamaBarangField().setText(penjualan.getNamaBarang());
        editPenjualan.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateData(penjualan);
            }
        });
        editPenjualan.getDiskonField().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateData(penjualan);
            }
        });
        editPenjualan.show();
    }
    public void updateData(Penjualan penjualan){
        try{
            String hargaSatuanString = editPenjualan.getHargaSatuanField().getText();
            String jumlahString = editPenjualan.getJumlahField().getText();
            String diskonString = editPenjualan.getDiskonField().getText();
            if((hargaSatuanString.equals("") || jumlahString.equals(""))){
                JOptionPane.showMessageDialog(null, "Mohon isi data harga dan jumlah", "Warning", JOptionPane.ERROR_MESSAGE);
            }else{
                int hargaSatuanUpdate = Integer.valueOf(hargaSatuanString);
                double jumlah = Double.parseDouble(jumlahString);
                int diskon = diskonString.equals("") ? 0 : Integer.valueOf(diskonString);
                penjualan.setHargaSatuan(hargaSatuanUpdate);
                penjualan.setQty(jumlah);
                penjualan.setDiskon(diskon);
                penjualan.setTotalHarga((int)(hargaSatuanUpdate*jumlah)-diskon);
                int margin = 0;
                if (penjualan.getTipeBarang() == 0) {
                    margin = (int) ((penjualan.getHargaSatuan()*penjualan.getQty()) - (penjualan.getHargaKulak()*penjualan.getQty()));
                } else {
                    //                System.out.println("Pembagian : "+(barang.getJumlahPembagian()/100));
                    //                System.out.println("total harga : "+totalHarga.intValue());
                    double marginInPercent = penjualan.getHargaKulak();
                    marginInPercent = (marginInPercent / 100) * (penjualan.getHargaSatuan()*penjualan.getQty());
                    //                System.out.println("Margin : "+marginInPercent);
                    margin = (int) marginInPercent;
                    //                System.out.println("margin : "+margin);
                }
                penjualan.setMargin(margin);
                LOG.info("Updating : "+penjualan.getIdPenjualan());
                if(penjualanService.updateData(penjualan)){
                    listDataPenjualan = penjualanService.getAllData();
                    viewOnTable();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "INFO", JOptionPane.ERROR_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Data gagal dihapus", "Warning", JOptionPane.ERROR_MESSAGE);
                }                
            }
        }catch(Exception e){
            LOG.error(e);
            JOptionPane.showMessageDialog(null, "Maaf ada kesalahan", "Warning", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteData(Penjualan penjualan){
        try{
            LOG.info("Deleting : "+penjualan.getIdPenjualan());
            if(penjualanService.deleteData(penjualan)){
                listDataPenjualan = penjualanService.getAllData();
                viewOnTable();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus", "INFO", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Data gagal dihapus", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            LOG.error(e);
        }
    }
    public void search(java.awt.event.ActionEvent e) {
        LOG.info("Search data");
        try {
            boolean searchByDate = false;
            if (penjualanPage.getBulanParam().getSelectedIndex() != 0 && penjualanPage.getTahunParam().getSelectedIndex() != 0) {
                searchByDate = true;
            }

            if (searchByDate && !penjualanPage.getNamaBarangParam().getText().equals("")) {
                if (!penjualanPage.getTanggalParam().getText().equals("")) {
                    String date = penjualanPage.getTanggalParam().getText() + "/" + penjualanPage.getBulanParam().getSelectedIndex()
                            + "/" + penjualanPage.getTahunParam().getSelectedItem().toString();
                    LOG.info("By date and product");
                    listDataPenjualan = penjualanService.findByProductAndDate(penjualanPage.getNamaBarangParam().getText(), date);
                    viewOnTable();
                } else {
                    String month = String.valueOf(penjualanPage.getBulanParam().getSelectedIndex());
                    String year = penjualanPage.getTahunParam().getSelectedItem().toString();
                    LOG.info("By month and product");
                    listDataPenjualan = penjualanService.findByProductAndMonthAndYear(penjualanPage.getNamaBarangParam().getText(), month, year);
                    viewOnTable();
                }

            } else if (searchByDate && penjualanPage.getNamaBarangParam().getText().equals("")) {
                if (!penjualanPage.getTanggalParam().getText().equals("")) {
                    try {
                        String date = penjualanPage.getTanggalParam().getText() + "/" + penjualanPage.getBulanParam().getSelectedIndex()
                                + "/" + penjualanPage.getTahunParam().getSelectedItem().toString();
                        Date dateString = dateFormatDatabase.parse(date);
                        LOG.info("By date");
                        listDataPenjualan = penjualanService.findByDate(dateString);
                        viewOnTable();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    String month = String.valueOf(penjualanPage.getBulanParam().getSelectedIndex());
                    String year = penjualanPage.getTahunParam().getSelectedItem().toString();
                    LOG.info("By month");
                    listDataPenjualan = penjualanService.findByMonthAndYear(month, year);
                    viewOnTable();
                }
            } else if (!searchByDate && !penjualanPage.getNamaBarangParam().getText().equals("")) {
                LOG.info("By nama barang");
                listDataPenjualan = penjualanService.findByProduct(penjualanPage.getNamaBarangParam().getText());
                viewOnTable();
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    //ini penjualan page
    public void viewDataOnTable() {
        try {
            LOG.info("Presenting data penjualan on table");
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("No");
            model.addColumn("Nama Barang");
            model.addColumn("Jumlah");
            //        model.addColumn("Satuan");
            model.addColumn("Harga Satuan");
            model.addColumn("Diskon");
            model.addColumn("Total Harga");
            model.addColumn("Margin");
            model.addColumn("#");
            int number = 0;
            for (Penjualan jual : listPenjualan) {
                number++;
                Object[] obj = new Object[8];
                obj[0] = number;
                obj[1] = jual.getNamaBarang();
                obj[2] = jual.getQty();
                //            obj[3] = jual.getSatuan();
                obj[3] = FormatRupiah.convert(String.valueOf(jual.getHargaSatuan()));
                obj[4] = jual.getDiskon();
                obj[5] = FormatRupiah.convert(String.valueOf(jual.getTotalHarga()));
                obj[6] = FormatRupiah.convert(String.valueOf(jual.getMargin()));
                obj[7] = "Hapus";
                model.addRow(obj);
            }
            penjualanPage.getViewTablePenjualan().setModel(model);
            penjualanPage.getViewTablePenjualan().getColumn("No").setPreferredWidth(3);
            penjualanPage.getViewTablePenjualan().getColumn("Jumlah").setPreferredWidth(8);
            Action delete = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int modelRow = Integer.valueOf(e.getActionCommand());
                    deleteDataPenjualan(listPenjualan.get(modelRow));
                    viewOnTable();
                }
            };
            ButtonColumn buttonDelete = new ButtonColumn(penjualanPage.getViewTablePenjualan(), delete, 7);
            buttonDelete.setMnemonic(KeyEvent.VK_D);
            LOG.info("done");
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void deleteDataPenjualan(Penjualan penjualan) {
        try {
            LOG.info("Deleting data input penjualan");
            listPenjualan.remove(penjualan);
            totalBelanja = totalBelanja - penjualan.getTotalHarga();
            if (totalBelanja > 0) {
                penjualanPage.getTotalPenjualanLabel().setText("Rp. " + FormatRupiah.simpleConvert("" + totalBelanja.intValue()));
            } else {
                penjualanPage.getTotalPenjualanLabel().setText("");
            }
            viewDataOnTable();
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
//        penjualanPage.getNamaBarangField().setText(barang.getNamaBarang());
//        String[] satuan;
//        if(this.barang.getSatuanTerbesar().isEmpty()){
//            satuan = new String[1];
//            satuan[0] = barang.getSatuanTerkecil();
//        }else{
//            satuan = new String[2];
//            satuan[0] = barang.getSatuanTerbesar();
//            satuan[1] = barang.getSatuanTerkecil();
//        }
//        penjualanPage.getSatuanField().setModel(new DefaultComboBoxModel(satuan));
    }

    public void setBarangRetur(Barang barang) {
        this.barang = barang;
        penjualanPage.getBarangReturField().setText(barang.getNamaBarang());
        String[] satuan;
        if (this.barang.getSatuanTerbesar().isEmpty()) {
            satuan = new String[1];
            satuan[0] = barang.getSatuanTerkecil();
        } else {
            satuan = new String[2];
            satuan[0] = barang.getSatuanTerbesar();
            satuan[1] = barang.getSatuanTerkecil();
        }
        penjualanPage.getSatuanReturField().setModel(new DefaultComboBoxModel(satuan));
    }

    public void addData(java.awt.event.ActionEvent e) {
        try {
            LOG.info("Add data to invoice");
            if (barang != null) {
                Penjualan penjualan = new Penjualan();
                penjualan.setNamaBarang(barang.getNamaBarang());
                penjualan.setIdBarang(barang.getIdBarang());
                penjualan.setTipeBarang(barang.getTipe());
                penjualan.setQty(Double.parseDouble(penjualanPage.getJumlahField().getText()));
                //        penjualan.setSatuan(penjualanPage.getSatuanField().getSelectedItem().toString());
                penjualan.setTotalHarga(totalHarga.intValue());
                penjualan.setNoNota(penjualanPage.getNotaField().getText());
                if (diskon != null) {
                    if (diskon > 0.0) {
                        penjualan.setDiskon(diskon);
                    }
                }
                penjualan.setHargaKulak(barang.getHarga());
                int margin = 0;
                if (barang.getTipe() == 0) {
                    margin = totalHarga.intValue() - (int)(barang.getHarga() * Double.parseDouble(penjualanPage.getJumlahField().getText()));
                } else {
                    //                System.out.println("Pembagian : "+(barang.getJumlahPembagian()/100));
                    //                System.out.println("total harga : "+totalHarga.intValue());
                    double marginInPercent = barang.getJumlahPembagian();
                    marginInPercent = (marginInPercent / 100) * totalHarga;
                    //                System.out.println("Margin : "+marginInPercent);
                    margin = (int) marginInPercent;
                    penjualan.setHargaKulak(barang.getJumlahPembagian());
                    //                System.out.println("margin : "+margin);
                }
                penjualan.setMargin(margin);
                penjualan.setTanggal(penjualanPage.getTanggalField().getSelectedDate().getTime());
                penjualan.setHargaSatuan(hargaSatuan);
                listPenjualan.add(penjualan);
                totalBelanja = totalBelanja + totalHarga;
                penjualanPage.getTotalPenjualanLabel().setText("Rp. " + FormatRupiah.simpleConvert("" + totalBelanja.intValue()));
                viewDataOnTable();
                empty();
            } else {
                JOptionPane.showMessageDialog(penjualanPage, "Mohon Pilih Barang");
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public void chooseProduct() {
        pilihBarang = new PilihBarangController(this);
        listBarang = pilihBarang.searchByName("hiro");
        System.out.print("Data " + listBarang.size());
//        pilihBarang.chooseProduct().setVisible(true);
    }

    public void chooseProductAdd(java.awt.event.ActionEvent e) {
        barang = new Barang();
        retur = false;
        chooseProduct();
    }

    public void chooseProductRetur(java.awt.event.ActionEvent e) {
        barang = new Barang();
        retur = true;
        chooseProduct();
    }

    public boolean isRetur() {
        return retur;
    }

    public void save(java.awt.event.ActionEvent e) {
        try {
            LOG.info("Saving data invoice");
            loadingController = new LoadingController(this.mainPageController.getParent());
            loadingController.init();
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws InterruptedException {
                    /** Execute some operation */
                    if (penjualanService.saveInBatch(listPenjualan)) {
                        listPenjualan = new ArrayList<>();
                        viewDataOnTable();
                        showAllData();
                        viewOnTable();
                        penjualanPage.getTotalPenjualanLabel().setText("");
                        penjualanPage.getNotaField().requestFocus();
                    } else {
                        LOG.error("Saving failed");
                    }
                    return null;
                }
                @Override
                protected void done() {
                    loadingController.closeLoading();
                }
            };
            worker.execute();
            loadingController.showLoading();
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    private void HargaSatuanFieldFocusGained(java.awt.event.FocusEvent evt) {
        if (!penjualanPage.getHargaSatuanField().getText().isEmpty() || !penjualanPage.getHargaSatuanField().getText().equals("")) {
            int numberOnly;
            numberOnly = Integer.valueOf(penjualanPage.getHargaSatuanField().getText().replaceAll("\\D+", "")) / 100;
            penjualanPage.getHargaSatuanField().setText("" + numberOnly);
        }
    }

    private void HargaSatuanFieldFocusLost(java.awt.event.FocusEvent evt) {
        if (!penjualanPage.getHargaSatuanField().getText().isEmpty() || !penjualanPage.getHargaSatuanField().getText().equals("")) {
            hargaSatuan = Integer.valueOf(penjualanPage.getHargaSatuanField().getText());
            penjualanPage.getHargaSatuanField().setText(FormatRupiah.convert(penjualanPage.getHargaSatuanField().getText()));
        }
    }

    private void diskonFieldFocusLost(java.awt.event.FocusEvent evt) {
        try {
            if (!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")) {
                diskon = Double.valueOf(penjualanPage.getDiskonField().getText());
                penjualanPage.getDiskonField().setText("Rp. " + diskon);
                double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
                //            double jumlahDiskon = (diskon/100)*(hargaSatuan*jumlahBarang);
                totalHarga = (hargaSatuan * jumlahBarang) - diskon;
                int convert = totalHarga.intValue();
                penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
                //            System.out.println(jumlahDiskon);
                //            System.out.println(totalHarga);
            } else {
                double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
                totalHarga = hargaSatuan * jumlahBarang;
                int convert = totalHarga.intValue();
                penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    private void diskonFieldFocusGained(java.awt.event.FocusEvent evt) {
        if (!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")) {
            double numberOnly;
            numberOnly = Double.valueOf(penjualanPage.getDiskonField().getText().replaceAll("%", ""));
            penjualanPage.getDiskonField().setText("" + numberOnly);
        }
    }

    private void totalFieldFocusGained(java.awt.event.FocusEvent evt) {
        try {
            if (!penjualanPage.getDiskonField().getText().isEmpty() || !penjualanPage.getDiskonField().getText().equals("")) {
//            diskon = Double.valueOf(penjualanPage.getDiskonField().getText());
                penjualanPage.getDiskonField().setText("Rp. " + diskon);
                double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
                //            double jumlahDiskon = (diskon/100)*(hargaSatuan*jumlahBarang);
                totalHarga = (hargaSatuan * jumlahBarang) - diskon;
                int convert = totalHarga.intValue();
                penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
            } else {
                double jumlahBarang = Double.valueOf(penjualanPage.getJumlahField().getText());
                totalHarga = hargaSatuan * jumlahBarang;
                int convert = totalHarga.intValue();
                penjualanPage.getTotalHargaField().setText(FormatRupiah.convert(String.valueOf(convert)));
            }
        } catch (Exception e) {
            LOG.error(e);
        }
    }

    public void empty() {
        barang = new Barang();
        penjualanPage.getNamaBarangField().setSelectedIndex(-1);
        penjualanPage.getJumlahField().setText("");
//        penjualanPage.getSatuanField().setSelectedIndex(0);
        penjualanPage.getTotalHargaField().setText("");
        totalHarga = 0.0;
        penjualanPage.getDiskonField().setText("");
        diskon = 0.0;
        penjualanPage.getHargaSatuanField().setText("");
        hargaSatuan = 0;
        penjualanPage.getBarangReturField().setText("");
        penjualanPage.getKeteranganField().setText("");
        penjualanPage.getJumlahReturField().setText("");
        penjualanPage.getNoNotaReturField().setText("");
        penjualanPage.getNamaBarangField().requestFocus();
//        penjualanPage.getTotalPenjualanLabel().setText("");
    }

    //coding untuk retur
    public void saveRetur(java.awt.event.ActionEvent e) {
        if (validationRetur()) {
            if (penjualanService.checkNota(penjualanPage.getNoNotaReturField().getText())) {
                ReturPenjualan returPenjualan = new ReturPenjualan();
                returPenjualan.setBarang(barang);
                returPenjualan.setKeterangan(penjualanPage.getKeteranganField().getText());
                returPenjualan.setSatuan(penjualanPage.getSatuanReturField().getSelectedItem().toString());
                returPenjualan.setNotaPenjualan(penjualanPage.getNoNotaReturField().getText());
                returPenjualan.setJumlah(Double.parseDouble(penjualanPage.getJumlahReturField().getText()));
                returPenjualan.setTanggalRetur(penjualanPage.getTanggalRetur().getSelectedDate().getTime());
                returService.addRetur(returPenjualan);
                this.showAllDataRetur();
                this.viewDataReturOnTable();
                this.empty();
            } else {
                JOptionPane.showMessageDialog(null, "Nota Tidak Ditemukan ", "Warning", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validationRetur() {
        if (this.barang == null) {
            JOptionPane.showMessageDialog(penjualanPage, "Mohon Pilih Barang");
            return false;
        } else if (penjualanPage.getJumlahReturField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(penjualanPage, "Mohon Isi Jumlah Barang");
            return false;
        } else if (penjualanPage.getKeteranganField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(penjualanPage, "Mohon Isi Keterangan");
            return false;
        } else if (penjualanPage.getNoNotaReturField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(penjualanPage, "Mohon Isi Nomor Nota");
            return false;
        }
        return true;
    }

    public void viewDataReturOnTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nota Pembelian");
        model.addColumn("Nama Barang");
        model.addColumn("Jumlah");
        model.addColumn("Tanggal");

        for (ReturPenjualan retur : listRetur) {
            Object[] obj = new Object[4];
            obj[0] = retur.getNotaPenjualan();
            obj[1] = retur.getBarang().getNamaBarang();
            obj[2] = retur.getJumlah() + " " + retur.getSatuan();
            obj[3] = FormatDate.format(retur.getTanggalRetur());
            model.addRow(obj);
        }

        penjualanPage.getViewTableRetur().setModel(model);
    }
}
