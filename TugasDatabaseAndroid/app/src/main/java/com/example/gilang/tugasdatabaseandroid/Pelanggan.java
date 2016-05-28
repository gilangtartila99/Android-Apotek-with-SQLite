package com.example.gilang.tugasdatabaseandroid;

/**
 * Created by GILANG on 27/05/2016.
 */
public class Pelanggan {
    private long id_pelanggan;
    private String nama_pelanggan;
    private String alamat_pelanggan;
    private String jk_pelanggan;
    private String status_pelanggan;

    public Pelanggan() {

    }

    public long getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(long id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public void setNama_pelanggan(String nama_pelanggan) {
        this.nama_pelanggan = nama_pelanggan;
    }

    public String getAlamat_pelanggan() {
        return alamat_pelanggan;
    }

    public void setAlamat_pelanggan(String alamat_pelanggan) {
        this.alamat_pelanggan = alamat_pelanggan;
    }

    public String getJk_pelanggan() {
        return jk_pelanggan;
    }

    public void setJk_pelanggan(String jk_pelanggan) {
        this.jk_pelanggan = jk_pelanggan;
    }

    public String getStatus_pelanggan() {
        return status_pelanggan;
    }

    public void setStatus_pelanggan(String status_pelanggan) {
        this.status_pelanggan = status_pelanggan;
    }

    @Override
    public String toString() {
        return "Pelanggan " + nama_pelanggan + " " + alamat_pelanggan + " " + jk_pelanggan + " " + status_pelanggan;
    }
}