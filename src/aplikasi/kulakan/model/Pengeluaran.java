/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi.kulakan.model;

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
 * @author guest
 */
@Entity
@Table(name="pengeluaran")
@XmlRootElement
public class Pengeluaran {
    
    @Id
    @Column(name="id_pengeluaran")
    private int idPengeluaran;
    
    @Column(name="judul")
    private String judul;
    
    @Column(name="deskripsi")
    private String deskripsi;
    
    @Column(name="jumlah")
    private int jumlah;
    
    @Column(name="tanggal")
    private Date tanggal;

    public Pengeluaran() {
    }

    public Pengeluaran(int idPengeluaran, String judul, String deskripsi, int jumlah, Date tanggal) {
        this.idPengeluaran = idPengeluaran;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.jumlah = jumlah;
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }
    
}
