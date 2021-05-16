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
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name="kulakan")
@XmlRootElement
public class Kulakan implements Serializable {
    
     @Id
     @Column(name="kode_pembelian")
     private String kodePembelian;
     
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="id_barang", nullable = false)
     private Barang barang;
     
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "kode_supplier", nullable = false)
     private Supplier supplier;
             
     @Column(name="satuan")
     private String satuan;
     
     @Column(name="jumlah")
     private double jumlah;
     
     @Column(name="nota_pembelian")
     private String noNota;
     
     @Column(name="harga_netto")
     private long hargaNetto;
     
     @Column(name="tanggal_kulakan")
     @Temporal(javax.persistence.TemporalType.DATE)
     private Date tanggalKulakan;
     
     @Column(name="diskon")
     private double diskon;
     
    public Kulakan() {
    }

    public Kulakan(String kodePembelian, Barang barang, Supplier supplier, String satuan, double jumlah, String noNota, long hargaNetto, Date tanggalKulakan, double diskon) {
        this.kodePembelian = kodePembelian;
        this.barang = barang;
        this.supplier = supplier;
        this.satuan = satuan;
        this.jumlah = jumlah;
        this.noNota = noNota;
        this.hargaNetto = hargaNetto;
        this.tanggalKulakan = tanggalKulakan;
        this.diskon = diskon;
    }

    public String getNoNota() {
        return noNota;
    }

    public void setNoNota(String noNota) {
        this.noNota = noNota;
    }

    public Date getTanggalKulakan() {
        return tanggalKulakan;
    }

    public void setTanggalKulakan(Date tanggalKulakan) {
        this.tanggalKulakan = tanggalKulakan;
    }

    public String getKodePembelian() {
        return kodePembelian;
    }

    public void setKodePembelian(String kodePembelian) {
        this.kodePembelian = kodePembelian;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public long getHargaNetto() {
        return hargaNetto;
    }

    public void setHargaNetto(long hargaNetto) {
        this.hargaNetto = hargaNetto;
    }    

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon;
    }
     
}
