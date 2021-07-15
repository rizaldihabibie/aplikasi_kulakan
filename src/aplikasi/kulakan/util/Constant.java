/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author guest
 */
public class Constant {
    public static String BIN_PATH;
    private static Logger LOG = LogManager.getLogger(Constant.class);
    public static void init(String configFile){
        try{
            LOG.info("initializing properties file : "+configFile);
            Properties loader = new Properties();
            FileInputStream file = new FileInputStream(configFile);
            loader.load(file);
            file.close();
            BIN_PATH = loader.getProperty("mysql_bin_path").trim();
            LOG.info("done");
        }catch(Exception e){
            LOG.error(e);
        }
    }
}
