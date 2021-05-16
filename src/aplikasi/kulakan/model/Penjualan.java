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
@Table(name="Penjualan")
@XmlRootElement
public class Penjualan implements Serializable {
    
    @Id
    @Column(name="id_penjualan")
    private int idPenjualan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_barang", nullable = false)
    private Barang barang;
    
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
    
    @Column(name="tanggal")
    private Date tanggal;
    
    @Column(name="no_nota")
    private String noNota;

    public Penjualan() {
    }

    public Penjualan(int idPenjualan, Barang barang, double qty, double diskon, String satuan, int totalHarga, int hargaSatuan, Date tanggal, String noNota) {
        this.idPenjualan = idPenjualan;
        this.barang = barang;
        this.qty = qty;
        this.diskon = diskon;
        this.satuan = satuan;
        this.totalHarga = totalHarga;
        this.hargaSatuan = hargaSatuan;
        this.tanggal = tanggal;
        this.noNota = noNota;
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

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

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
