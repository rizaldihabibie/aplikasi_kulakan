/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan;

import aplikasi.kulakan.controller.MainPageController;
import aplikasi.kulakan.util.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author USER
 */
public class AplikasiKulakan {
    public static Logger log = LogManager.getLogger(AplikasiKulakan.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        System.out.println("log config : "+args[0]);
//        PropertyConfigurator.configure(args[0]);
//        log.trace("ini trace log");
//        log.debug("ini debug log");
//        log.info("ini info log");
//        log.warn("ini warn log");
//        log.error("ini error log");
        log.info("Starting app");
        Constant.init(args[0]);
        MainPageController main = new MainPageController();
        main.init();
        main.start();
        log.info("app started");
    }
    
}
