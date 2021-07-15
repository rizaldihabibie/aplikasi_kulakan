/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.service.PengeluaranService;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.view.PrintOptionGeneral;
import java.io.File;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class PrintOptionGeneralController {

    private PrintOptionGeneral print;
    private KulakanService kulakanService;
    private KulakanPageController kulakanPage;
    private PengeluaranController pengeluaranController;
    private JournalController journalController;
    private PengeluaranService pengeluaranService;
    private static Logger LOG = LogManager.getLogger(PrintOptionGeneralController.class);
    
    public PrintOptionGeneralController(KulakanPageController kulakanPage) {
        this.kulakanPage = kulakanPage;
        this.kulakanService = kulakanPage.getService();
        print = new PrintOptionGeneral();
        print.getBulanOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        print.getPrintButton().addActionListener(this::print);
    }

    public PrintOptionGeneralController(PengeluaranController pengeluaranController) {
        this.pengeluaranController = pengeluaranController;
        this.pengeluaranService = pengeluaranController.getPengeluaranService();
        print = new PrintOptionGeneral();
        print.getBulanOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        print.getPrintButton().addActionListener(this::print);
    }

    public PrintOptionGeneralController(JournalController journalController) {
        this.journalController = journalController;
        print = new PrintOptionGeneral();
        print.getBulanOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        print.getPrintButton().addActionListener(this::print);
    }

    public void print(java.awt.event.ActionEvent e) {
        try {
            LOG.info("Printing");
            String month = null;
            if ((print.getBulanOption().getSelectedIndex() + 1) != 0) {
                month = "" + print.getBulanOption().getSelectedIndex();
            }
            JFileChooser saveFile = new JFileChooser();
            int result = saveFile.showSaveDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = saveFile.getSelectedFile();
                
                String path;
                if(String.valueOf(file).contains(".xls")){
                    path = String.valueOf(file);
                }else{
                    path = String.valueOf(file + ".xls");
                }
                if (kulakanService != null) {
                    LOG.info("data kulakan by month and year");
                    kulakanService.printToExcel(path, kulakanService.findByMonthAndYear(month, print.getTahunOption().getSelectedItem().toString()));
                } else if (pengeluaranService != null) {
                    LOG.info("data pengeluaran by month");
                    pengeluaranService.printToExcel(path, pengeluaranService.findByMonth(month, print.getTahunOption().getSelectedItem().toString()));
                } else {
                    LOG.info("data journal by date");
                    journalController.printToExcel(path, Integer.valueOf(print.getTahunOption().getSelectedItem().toString()), Integer.valueOf(month), print.getTanggalField().getText());
                }
            } else {
                return;
            }
        } catch (Exception ex) {
            LOG.error(ex);
        }
    }

    public PrintOptionGeneral openPrintPage() {
        return print;
    }
}
