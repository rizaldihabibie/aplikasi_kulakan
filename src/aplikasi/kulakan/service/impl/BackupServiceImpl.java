/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.service.impl;

import aplikasi.kulakan.dao.BackupDao;
import aplikasi.kulakan.dao.impl.BackupDaoImpl;
import aplikasi.kulakan.service.BackupService;

/**
 *
 * @author guest
 */
public class BackupServiceImpl implements BackupService{
    BackupDao backupDao = new BackupDaoImpl();
    @Override
    public void execute(String destinationPath) {
        backupDao.execute(destinationPath);
    }
    
}
