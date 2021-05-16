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
    private PrintOptionKulakanController printKulakan;
    
    public PrintOptionController(KulakanPageController kulakanPage){
        printOption = new PrintOption(kulakanPage.getParent().getParent(), true);
        printKulakan = new PrintOptionKulakanController(kulakanPage);
        printKulakan.openPrintPage().setSize(printOption.getPrintPage().getSize());
        printKulakan.openPrintPage().setVisible(true);
        printOption.getPrintPage().getContentPane().add(printKulakan.openPrintPage(), java.awt.BorderLayout.CENTER);
        printOption.setTitle("Export Dialog");
        printOption.getPrintPage().setTitle("Kulakan");
        printOption.getPrintPage().validate();        
    }
    public PrintOption openPrintDialog(){
       return printOption;
   }
}
