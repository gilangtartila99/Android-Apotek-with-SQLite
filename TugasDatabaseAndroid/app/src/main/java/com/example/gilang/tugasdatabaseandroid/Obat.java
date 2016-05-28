package com.example.gilang.tugasdatabaseandroid;

/**
 * Created by GILANG on 27/05/2016.
 */
public class Obat {
    private long id_obat;
    private String nama_obat;
    private String jenis_obat;

    public Obat()
    {

    }
    public long getId_obat() {
        return id_obat;
    }

    public void setId_obat(long id_obat) {
        this.id_obat = id_obat;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }

    public String getJenis_obat() {
        return jenis_obat;
    }

    public void setJenis_obat(String jenis_obat) {
        this.jenis_obat = jenis_obat;
    }

    @Override
    public String toString()
    {
        return "Obat "+ nama_obat +" "+ jenis_obat;
    }
}
