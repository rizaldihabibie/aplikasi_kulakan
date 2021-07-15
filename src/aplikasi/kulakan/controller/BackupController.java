/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.controller;

import aplikasi.kulakan.service.BackupService;
import aplikasi.kulakan.service.impl.BackupServiceImpl;
import aplikasi.kulakan.util.Constant;
import aplikasi.kulakan.view.BackupView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author guest
 */
public class BackupController {
    private final BackupService backupService = new BackupServiceImpl();
    private String destinationPath;
    private BackupView backupView;
    private MainPageController mainPageController;
    private static org.apache.logging.log4j.Logger LOG = LogManager.getLogger(BackupController.class);
    
    public BackupController(MainPageController mainPageController){
        this.mainPageController = mainPageController;
        backupView = new BackupView(mainPageController.getParent(), true);
        backupView.getPilihButton().addActionListener((ActionEvent ae) -> {
            chooseFileDestination();
        });
        backupView.getMulaiButton().addActionListener((ActionEvent ae) -> {
            execute();
        });
    }
    public void show(){
        backupView.getBinField().setText(Constant.BIN_PATH);
        backupView.getBinField().setEnabled(false);
        backupView.show();
    }
    private void chooseFileDestination(){
        JFileChooser saveFile = new JFileChooser();
        int result = saveFile.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = saveFile.getSelectedFile();
            if(String.valueOf(file).contains(".sql")){
                destinationPath = String.valueOf(file);
            }else{
                destinationPath = String.valueOf(file + ".sql");
            }
            LOG.info("back up to : "+destinationPath);
            backupView.getBackupField().setText(destinationPath);
            backupView.getBackupField().setEnabled(false);
        }
    }
    public void execute(){
        if(!"".equals(destinationPath) && null != destinationPath){
            backupService.execute(destinationPath);
        }else{
            JOptionPane.showMessageDialog(backupView, "Mohon pilih file destinasi", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
