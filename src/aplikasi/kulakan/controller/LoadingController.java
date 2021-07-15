/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.view.LoadingDialog;
import aplikasi.kulakan.view.MainPage;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author guest
 */
public class LoadingController {
    private LoadingDialog loadingDialog;
    private MainPage mainPage;

    public LoadingController(MainPage mainPage ) {
        this.mainPage = mainPage;
    }
    
    public void init(){
        loadingDialog = new LoadingDialog(mainPage, true);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }
    
    public void showLoading(){
        loadingDialog.setVisible(true);
    }
    public void closeLoading(){
        loadingDialog.setVisible(false);
    }
}
