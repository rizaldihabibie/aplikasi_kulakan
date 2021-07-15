/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.dao.impl;

import aplikasi.kulakan.controller.BackupController;
import aplikasi.kulakan.dao.BackupDao;
import aplikasi.kulakan.util.Constant;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author guest
 */
public class BackupDaoImpl implements BackupDao {
    private static org.apache.logging.log4j.Logger LOG = LogManager.getLogger(BackupDaoImpl.class);
    @Override
    public void execute(String destinationPath) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(Constant.BIN_PATH+"mysqldump -u root database_kelontong -r \""+destinationPath+"\"");
            JOptionPane.showMessageDialog(null, "Backup success !", "INFO", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            LOG.error(ex);
            JOptionPane.showMessageDialog(null, "Gagal backup datae !", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
