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
@Table(name="retur_pembelian")
@XmlRootElement
public class ReturPembelian implements Serializable {
    
    @Id
    @Column(name="id")
    private int id;
    
    @Column(name="nota_pembelian")
    private String notaPembelian;
    
    @Column(name="tanggal_retur")
    private Date tanggalRetur;
    
    @Column(name="keterangan")
    private String keterangan;
    
    @Column(name="jumlah")
    private double jumlah;
    
    @Column(name="satuan")
    private String satuan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_barang", nullable = false)
    private Barang barang;

    public ReturPembelian() {
    }

    public ReturPembelian(int id, String notaPembelian, Date tanggalRetur, String keterangan, double jumlah, String satuan, Barang barang) {
        this.id = id;
        this.notaPembelian = notaPembelian;
        this.tanggalRetur = tanggalRetur;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
        this.satuan = satuan;
        this.barang = barang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotaPembelian() {
        return notaPembelian;
    }

    public void setNotaPembelian(String notaPembelian) {
        this.notaPembelian = notaPembelian;
    }

    public Date getTanggalRetur() {
        return tanggalRetur;
    }

    public void setTanggalRetur(Date tanggalRetur) {
        this.tanggalRetur = tanggalRetur;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }
    
    
    
}
