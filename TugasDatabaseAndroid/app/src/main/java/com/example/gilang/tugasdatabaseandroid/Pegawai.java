package com.example.gilang.tugasdatabaseandroid;

/**
 * Created by GILANG on 27/05/2016.
 */
public class Pegawai {
    private long id_pegawai;
    private String nama_pegawai;
    private String alamat_pegawai;
    private String jk_pegawai;
    private String status_pegawai;

    public Pegawai()
    {

    }
    public long getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(long id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_pegawai() {
        return nama_pegawai;
    }

    public void setNama_pegawai(String nama_pegawai) {
        this.nama_pegawai = nama_pegawai;
    }

    public String getAlamat_pegawai() {
        return alamat_pegawai;
    }

    public void setAlamat_pegawai(String alamat_pegawai) {
        this.alamat_pegawai = alamat_pegawai;
    }

    public String getJk_pegawai() { return jk_pegawai; }

    public void setJk_pegawai(String jk_pegawai) { this.jk_pegawai = jk_pegawai; }

    public String getStatus_pegawai() {
        return status_pegawai;
    }

    public void setStatus_pegawai(String status_pegawai) {
        this.status_pegawai = status_pegawai;
    }

    @Override
    public String toString()
    {
        return "Pegawai "+ nama_pegawai +" "+ alamat_pegawai +" "+ status_pegawai;
    }
}
