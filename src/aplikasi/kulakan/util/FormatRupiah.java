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
        String end="";

        if(format.contains("-")){
            satuan = "(Rp. ";
            end=")";
            format = format.replace("-","");
        }
        if(format.length()== 1){
            format = satuan +format+" ,00"+end;
        }else if(format.length()== 2){
            format = satuan +format+" ,00"+end;
        }else if(format.length()== 3){
            format = satuan +format+" ,00"+end;
        }else if(format.length()== 4){
            format = satuan +format.substring(0,1)+"."+format.substring(1,format.length())+" ,00"+end;
        }else if(format.length()== 5){
            format = satuan +format.substring(0,2)+"."+format.substring(2,format.length())+" ,00"+end;
        }else if(format.length()==6){
            format= satuan +format.substring(0,3)+"."+format.substring(3,format.length())+" ,00"+end;
        }else if(format.length()==7){
            format = satuan +format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,format.length())+" ,00"+end;
        }else if(format.length()==8){
            format = satuan +format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,format.length())+" ,00"+end;
        }else if(format.length()==9){
            format = satuan +format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,format.length())+" ,00"+end;
        }else if(format.length()==10){
            format = satuan +format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,7)+"."+format.substring(7,format.length())+" ,00"+end;
        }else if(format.length()==11){
            format = satuan +format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,8)+"."+format.substring(8,format.length())+" ,00"+end;
        }else if(format.length()==12){
            format = satuan +format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,9)+"."+format.substring(9,format.length())+" ,00"+end;
        }
        return format;
    }
    
    public static String simpleConvert(String number){
        String format = number;
        String satuan = "";
        String end="";

        if(format.contains("-")){
            satuan = "(";
            end=")";
            format = format.replace("-","");
        }
        if(format.length()== 1){
            format = satuan +format+""+end;
        }else if(format.length()== 2){
            format = satuan +format+""+end;
        }else if(format.length()== 3){
            format = satuan +format+""+end;
        }else if(format.length()== 4){
            format = satuan +format.substring(0,1)+"."+format.substring(1,format.length())+""+end;
        }else if(format.length()== 5){
            format = satuan +format.substring(0,2)+"."+format.substring(2,format.length())+""+end;
        }else if(format.length()==6){
            format= satuan +format.substring(0,3)+"."+format.substring(3,format.length())+""+end;
        }else if(format.length()==7){
            format = satuan +format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,format.length())+""+end;
        }else if(format.length()==8){
            format = satuan +format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,format.length())+""+end;
        }else if(format.length()==9){
            format = satuan +format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,format.length())+""+end;
        }else if(format.length()==10){
            format = satuan +format.substring(0,1)+"."+format.substring(1,4)+"."+format.substring(4,7)+"."+format.substring(7,format.length())+""+end;
        }else if(format.length()==11){
            format = satuan +format.substring(0,2)+"."+format.substring(2,5)+"."+format.substring(5,8)+"."+format.substring(8,format.length())+""+end;
        }else if(format.length()==12){
            format = satuan +format.substring(0,3)+"."+format.substring(3,6)+"."+format.substring(6,9)+"."+format.substring(9,format.length())+""+end;
        }
        return format;
    }
}
