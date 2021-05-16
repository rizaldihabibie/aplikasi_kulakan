/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.util;

/**
 *
 * @author USER
 */
public class Bulan {
    private static String[] daftarBulan;
    private static String[] daftarTahun;
    
    public static String[] daftarBulan(){
        daftarBulan = new String[13];
        daftarBulan[0] = "-- Pilih Bulan --";
        daftarBulan[1] = "Januari";
        daftarBulan[2] = "Februari";
        daftarBulan[3] = "Maret";
        daftarBulan[4] = "April";
        daftarBulan[5] = "Mei";
        daftarBulan[6] = "Juni";
        daftarBulan[7] = "Juli";
        daftarBulan[8] = "Agustus";
        daftarBulan[9] = "September";
        daftarBulan[10] = "Oktober";
        daftarBulan[11] = "November";
        daftarBulan[12] = "Desember";
        return daftarBulan;
    }
    public static String[] daftarTahun(){
        daftarTahun = new String[15];
        daftarTahun[0] = "-- Pilih Tahun --";
        daftarTahun[1] = "2015";
        daftarTahun[2] = "2016";
        daftarTahun[3] = "2017";
        daftarTahun[4] = "2018";
        daftarTahun[5] = "2019";
        daftarTahun[6] = "2020";
        daftarTahun[7] = "2021";
        daftarTahun[8] = "2022";
        daftarTahun[9] = "2023";
        daftarTahun[10] = "2024";
        daftarTahun[11] = "2025";
        daftarTahun[12] = "2026";
        daftarTahun[13] = "2027";
        daftarTahun[14] = "2028";
        return daftarTahun;
    }
    
}
