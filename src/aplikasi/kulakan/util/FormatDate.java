/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author USER
 */
public class FormatDate {
    
    private static String tanggal;
    public static String format(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        tanggal = dateFormat.format(date);
        return tanggal;
    }
}
