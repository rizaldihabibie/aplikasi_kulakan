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

/**
 *
 * @author USER
 */
@Table
@Entity(name="stock_barang")
public class Stock implements Serializable {
    
    @Id
    @Column(name="id_stock")
    private int idStock;
    
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name="id_barang", nullable = false)
     private Barang barang;
     
     @Column(name="jumlah")
     private double jumlahStock;
     
     @Column(name="tanggal")
     private Date tanggal;
     
     @Column(name="status")
     private String status;

    public Stock() {
    }

    public Stock(int idStock, Barang barang, double jumlahStock, Date tanggal, String status) {
        this.idStock = idStock;
        this.barang = barang;
        this.jumlahStock = jumlahStock;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public double getJumlahStock() {
        return jumlahStock;
    }

    public void setJumlahStock(double jumlahStock) {
        this.jumlahStock = jumlahStock;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
     
}
