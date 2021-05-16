/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.service.KulakanService;
import aplikasi.kulakan.util.Bulan;
import aplikasi.kulakan.view.PrintOptionKulakan;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class PrintOptionKulakanController {
    
    private PrintOptionKulakan print;
    private KulakanService kulakanService;
    private KulakanPageController kulakanPage;
    
    public PrintOptionKulakanController(KulakanPageController kulakanPage){
        this.kulakanPage = kulakanPage;
        this.kulakanService = kulakanPage.getService();
        print = new PrintOptionKulakan();
        print.getBulanOption().setModel(new DefaultComboBoxModel(Bulan.daftarBulan()));
        print.getPrintButton().addActionListener(this::print);
    }
    
   
   public void print(java.awt.event.ActionEvent e){
       String month = null;
       if((print.getBulanOption().getSelectedIndex()+1)!= 0){
           month = ""+print.getBulanOption().getSelectedIndex();
       }
       JFileChooser saveFile = new JFileChooser();
       int result = saveFile.showSaveDialog(null);
       if(result == JFileChooser.APPROVE_OPTION){
            File file = saveFile.getSelectedFile();
            String path = String.valueOf(file+".xls");
            kulakanService.printToExcel(path, kulakanService.findByMonthAndYear(month, print.getTahunOption().getSelectedItem().toString()));
       }else{
                return;
       }
    }
   
   public PrintOptionKulakan openPrintPage(){
       return print;
   }
}
