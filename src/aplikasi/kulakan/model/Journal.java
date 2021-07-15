/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.model;

import java.util.Date;

/**
 *
 * @author guest
 */
public class Journal implements Comparable<Journal> {
    private Date tanggal;
    private String keterangan, satuan, type;
    private int volume, hargaSatuan, diskon, total, saldo, margin,totalKulakan, totalPengeluaran;

    public int getTotalKulakan() {
        return totalKulakan;
    }

    public void setTotalKulakan(int totalKulakan) {
        this.totalKulakan = totalKulakan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(int hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public int getTotalPengeluaran() {
        return totalPengeluaran;
    }

    public void setTotalPengeluaran(int totalPengeluaran) {
        this.totalPengeluaran = totalPengeluaran;
    }

    @Override
    public int compareTo(Journal t) {
        return getTanggal().compareTo(t.getTanggal());
    }
    
    
}
