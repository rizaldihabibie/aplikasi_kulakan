/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.model;

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
@Entity
@Table(name="daftar_harga")
public class DaftarHarga {
    
    @Id
    @Column(name="id_harga")
    private long idHarga;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_barang", nullable = false)
    private Barang barang;
    
    @Column(name="harga_a")
    private long hargaA;
    
    @Column(name="harga_b")
    private long hargaB;
    
    @Column(name="harga_c")
    private long hargaC;
    
    @Column(name="harga_d")
    private long hargaD;

    public DaftarHarga(long idHarga, Barang barang, long hargaA, long hargaB, long hargaC, long hargaD) {
        this.idHarga = idHarga;
        this.barang = barang;
        this.hargaA = hargaA;
        this.hargaB = hargaB;
        this.hargaC = hargaC;
        this.hargaD = hargaD;
    }

    public DaftarHarga() {
    }

    public long getIdHarga() {
        return idHarga;
    }

    public void setIdHarga(long idHarga) {
        this.idHarga = idHarga;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public long getHargaA() {
        return hargaA;
    }

    public void setHargaA(long hargaA) {
        this.hargaA = hargaA;
    }

    public long getHargaB() {
        return hargaB;
    }

    public void setHargaB(long hargaB) {
        this.hargaB = hargaB;
    }

    public long getHargaC() {
        return hargaC;
    }

    public void setHargaC(long hargaC) {
        this.hargaC = hargaC;
    }

    public long getHargaD() {
        return hargaD;
    }

    public void setHargaD(long hargaD) {
        this.hargaD = hargaD;
    }
    
    
}
