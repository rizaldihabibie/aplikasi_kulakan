/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

/**
 *
 * @author Rizaldi Habibie
 */
public class FormatRupiah {
    
    public static String convert(String number){
        String format = number;
        String satuan = "Rp. ";
        if(number.length()== 1){
            format = "Rp. "+format+" ,00";
        }else if(format.length()== 2){
            format = "Rp. "+format+" ,00";
        }else if(format.length()== 3){
            format = "Rp. "+format+" ,00";
        }else if(format.length()== 4){
            format = "Rp. "+format.substring(0,1)+"."+format.substring(1,format.length())+" ,00";
        }else if(format.length()== 5){
            format = "Rp. "+format.substring(0,2)+"."+format.substring(2,format.length())+" ,00";
        }else if(format.length()==6){
            format= "Rp. "+format.substring(0,3)+"."+format.substring(3,format.length())+" ,00";
        }else if(format.length()==7){
            format = "Rp. "+format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,format.length())+" ,00";
        }else if(format.length()==8){
            format = "Rp. "+format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,format.length())+" ,00";
        }else if(format.length()==9){
            format = "Rp. "+format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,format.length())+" ,00";
        }else if(format.length()==10){
            format = "Rp. "+format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,7)+"."+format.substring(7,format.length())+" ,00";
        }else if(format.length()==11){
            format = "Rp. "+format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,8)+"."+format.substring(8,format.length())+" ,00";
        }else if(format.length()==12){
            format = "Rp. "+format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,9)+"."+format.substring(9,format.length())+" ,00";
        }
        return format;
    }
}
