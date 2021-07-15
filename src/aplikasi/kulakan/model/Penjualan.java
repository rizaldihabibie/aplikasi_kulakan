/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name="penjualan")
@XmlRootElement
public class Penjualan implements Serializable {
    
    @Id
    @Column(name="id_penjualan")
    private int idPenjualan;
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="id_barang", nullable = false)
//    private Barang barang;
    
    @Column(name="nama_barang")
    private String namaBarang;
    
    @Column(name="id_barang")
    private int idBarang;
    
    @Column(name="tipe_barang")
    private int tipeBarang;
    
    @Column(name="kuantitas")
    private double qty;
    
    @Column(name="diskon")
    private double diskon;
    
    @Column(name="satuan")
    private String satuan;
    
    @Column(name="total_harga")
    private int totalHarga;
    
    @Column(name="harga_satuan")
    private int hargaSatuan;
    
    @Column(name="harga_kulak")
    private int hargaKulak;
    
    @Column(name="margin")
    private int margin;
    
    @Column(name="tanggal")
    private Date tanggal;
    
    @Column(name="no_nota")
    private String noNota;

    public Penjualan() {
    }

    public Penjualan(int idPenjualan, double qty, double diskon,
            String satuan,
            int totalHarga,
            int hargaSatuan,
            Date tanggal,
            String noNota,
            int hargaKulak,
            int margin,
            String namaBarang,
            String idBarang,
            int tipeBarang) {
        this.idPenjualan = idPenjualan;
        this.idBarang = this.idBarang;
        this.namaBarang = this.namaBarang;
        this.tipeBarang = tipeBarang;
//        this.barang = barang;
        this.qty = qty;
        this.diskon = diskon;
        this.satuan = satuan;
        this.totalHarga = totalHarga;
        this.hargaSatuan = hargaSatuan;
        this.tanggal = tanggal;
        this.noNota = noNota;
        this.hargaKulak = hargaKulak;
        this.margin = margin;
    }

    public int getTipeBarang() {
        return tipeBarang;
    }

    public void setTipeBarang(int tipeBarang) {
        this.tipeBarang = tipeBarang;
    }

    public int getHargaKulak() {
        return hargaKulak;
    }

    public void setHargaKulak(int hargaKulak) {
        this.hargaKulak = hargaKulak;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public int getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(int hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public int getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(int idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

//    public Barang getBarang() {
//        return barang;
//    }
//
//    public void setBarang(Barang barang) {
//        this.barang = barang;
//    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public int getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
    
}
