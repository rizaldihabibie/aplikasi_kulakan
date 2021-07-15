/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.view.PrintOption;

/**
 *
 * @author USER
 */
public class PrintOptionController {
    
    private PrintOption printOption;
    private MainPageController mainPage;
    private PrintOptionGeneralController printGeneral;
    
    public PrintOptionController(KulakanPageController kulakanPage){
        printOption = new PrintOption(kulakanPage.getParent().getParent(), true);
        printGeneral = new PrintOptionGeneralController(kulakanPage);
        printGeneral.openPrintPage().getTanggalField().setVisible(false);
        printGeneral.openPrintPage().getTanggalLabel().setVisible(false);
        printGeneral.openPrintPage().setSize(printOption.getPrintPage().getSize());
        printGeneral.openPrintPage().setVisible(true);
        printOption.getPrintPage().getContentPane().add(printGeneral.openPrintPage(), java.awt.BorderLayout.CENTER);
        printOption.setTitle("Export Dialog");
        printOption.getPrintPage().setTitle("Kulakan");
        printOption.getPrintPage().validate();        
    }
    
    public PrintOptionController(PengeluaranController pengeluaranController){
        printOption = new PrintOption(pengeluaranController.getParent().getParent(), true);
        printGeneral = new PrintOptionGeneralController(pengeluaranController);
        printGeneral.openPrintPage().getTanggalField().setVisible(false);
        printGeneral.openPrintPage().getTanggalLabel().setVisible(false);
        printGeneral.openPrintPage().setSize(printOption.getPrintPage().getSize());
        printGeneral.openPrintPage().setVisible(true);
        printOption.getPrintPage().getContentPane().add(printGeneral.openPrintPage(), java.awt.BorderLayout.CENTER);
        printOption.setTitle("Export Dialog");
        printOption.getPrintPage().setTitle("Pengeluaran");
        printOption.getPrintPage().validate();        
    }
    
    public PrintOptionController(JournalController journalController){
        printOption = new PrintOption(journalController.getParent().getParent(), true);
        printGeneral = new PrintOptionGeneralController(journalController);
        printGeneral.openPrintPage().setSize(printOption.getPrintPage().getSize());
        printGeneral.openPrintPage().setVisible(true);
        printOption.getPrintPage().getContentPane().add(printGeneral.openPrintPage(), java.awt.BorderLayout.CENTER);
        printOption.setTitle("Export Dialog");
        printOption.getPrintPage().setTitle("Jurnal");
        printOption.getPrintPage().validate();        
    }
    public PrintOption openPrintDialog(){
       return printOption;
   }
}
