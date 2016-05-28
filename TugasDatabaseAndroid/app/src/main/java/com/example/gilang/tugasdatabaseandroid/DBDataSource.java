package com.example.gilang.tugasdatabaseandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GILANG on 27/05/2016.
 */
public class DBDataSource {
    private SQLiteDatabase database;
    private SQLiteDatabase db;

    //inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //ambil semua nama kolom
    private String[] allPelanggan = { DBHelper.COLUMN_ID_PELANGGAN,
            DBHelper.COLUMN_NAMA_PELANGGAN,
            DBHelper.COLUMN_ALAMAT_PELANGGAN,
            DBHelper.COLUMN_JK_PELANGGAN,
            DBHelper.COLUMN_STATUS_PELANGGAN};

    private String[] allPegawai = {
            DBHelper.COLUMN_ID_PEGAWAI,
            DBHelper.COLUMN_NAMA_PEGAWAI,
            DBHelper.COLUMN_ALAMAT_PEGAWAI,
            DBHelper.COLUMN_JK_PEGAWAI,
            DBHelper.COLUMN_STATUS_PEGAWAI};

    private String[] allObat = {
            DBHelper.COLUMN_ID_OBAT,
            DBHelper.COLUMN_NAMA_OBAT,
            DBHelper.COLUMN_JENIS_OBAT};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //membuka/membuat sambungan baru ke database
    public DBDataSource open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();
        return null;
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    public Pelanggan createPelanggan(String nama, String alamat, String jk, String status) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA_PELANGGAN, nama);
        values.put(DBHelper.COLUMN_ALAMAT_PELANGGAN, alamat);
        values.put(DBHelper.COLUMN_JK_PELANGGAN, jk);
        values.put(DBHelper.COLUMN_STATUS_PELANGGAN, status);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAMA_PELANGGAN, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PELANGGAN,
                allPelanggan, DBHelper.COLUMN_ID_PELANGGAN + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek pelanggan
        Pelanggan newPelanggan = cursorToPelanggan(cursor);

        // close cursor
        cursor.close();

        // mengembalikan pelanggan baru
        return newPelanggan;
    }

    private Pelanggan cursorToPelanggan(Cursor cursor)
    {
        // buat objek pelanggan baru
        Pelanggan pelanggan = new Pelanggan();
        // debug LOGCAT
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));

		/* Set atribut pada objek pelanggan dengan
		 * data kursor yang diambil dari database*/
        pelanggan.setId_pelanggan(cursor.getLong(0));
        pelanggan.setNama_pelanggan(cursor.getString(1));
        pelanggan.setAlamat_pelanggan(cursor.getString(2));
        pelanggan.setJk_pelanggan(cursor.getString(3));
        pelanggan.setStatus_pelanggan(cursor.getString(4));

        //kembalikan sebagai objek pelanggan
        return pelanggan;
    }

    public ArrayList<Pelanggan> getAllPelanggan() {
        ArrayList<Pelanggan> daftarPelanggan = new ArrayList<Pelanggan>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PELANGGAN,
                allPelanggan, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data pelanggan ke
        // daftar pelanggan
        while (!cursor.isAfterLast()) {
            Pelanggan pelanggan = cursorToPelanggan(cursor);
            daftarPelanggan.add(pelanggan);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarPelanggan;
    }

    public Pelanggan getPelanggan(long id_pelanggan)
    {
        Pelanggan pelanggan = new Pelanggan(); //inisialisasi pelanggan
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PELANGGAN, allPelanggan, "_id_pelanggan ="+id_pelanggan, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek pelanggan
        pelanggan = cursorToPelanggan(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return pelanggan;
    }
    //update pelanggan yang diedit
    public void updatePelanggan(Pelanggan p) {
        //ambil id barang
        String strFilter = "_id_pelanggan=" + p.getId_pelanggan();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA_PELANGGAN, p.getNama_pelanggan());
        args.put(DBHelper.COLUMN_ALAMAT_PELANGGAN, p.getAlamat_pelanggan());
        args.put(DBHelper.COLUMN_JK_PELANGGAN, p.getJk_pelanggan());
        args.put(DBHelper.COLUMN_STATUS_PELANGGAN, p.getStatus_pelanggan());
        //update query
        database.update(DBHelper.TABLE_NAMA_PELANGGAN, args, strFilter, null);
    }

    //delete barang sesuai ID
    public void deletePelanggan(long id_pelanggan)
    {
        String strFilter = "_id_pelanggan=" + id_pelanggan;
        database.delete(DBHelper.TABLE_NAMA_PELANGGAN, strFilter, null);
    }

    public Pegawai createPegawai(String nama, String alamat, String jk, String status) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA_PEGAWAI, nama);
        values.put(DBHelper.COLUMN_ALAMAT_PEGAWAI, alamat);
        values.put(DBHelper.COLUMN_JK_PEGAWAI, jk);
        values.put(DBHelper.COLUMN_STATUS_PEGAWAI, status);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAMA_PEGAWAI, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PEGAWAI,
                allPegawai, DBHelper.COLUMN_ID_PEGAWAI + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek pelanggan
        Pegawai newPegawai = cursorToPegawai(cursor);

        // close cursor
        cursor.close();

        // mengembalikan pelanggan baru
        return newPegawai;
    }

    private Pegawai cursorToPegawai(Cursor cursor)
    {
        // buat objek pelanggan baru
        Pegawai pegawai = new Pegawai();
        // debug LOGCAT
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng " + cursor.getString(1) + "," + cursor.getString(2));

		/* Set atribut pada objek pelanggan dengan
		 * data kursor yang diambil dari database*/
        pegawai.setId_pegawai(cursor.getLong(0));
        pegawai.setNama_pegawai(cursor.getString(1));
        pegawai.setAlamat_pegawai(cursor.getString(2));
        pegawai.setJk_pegawai(cursor.getString(3));
        pegawai.setStatus_pegawai(cursor.getString(4));

        //kembalikan sebagai objek pelanggan
        return pegawai;
    }

    public ArrayList<Pegawai> getAllPegawai() {
        ArrayList<Pegawai> daftarPegawai = new ArrayList<Pegawai>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PEGAWAI,
                allPegawai, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data pelanggan ke
        // daftar pelanggan
        while (!cursor.isAfterLast()) {
            Pegawai pegawai = cursorToPegawai(cursor);
            daftarPegawai.add(pegawai);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarPegawai;
    }

    public Pegawai getPegawai(long id_pegawai)
    {
        Pegawai pegawai = new Pegawai(); //inisialisasi pelanggan
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_PEGAWAI, allPegawai, "_id_pegawai ="+id_pegawai, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek pelanggan
        pegawai = cursorToPegawai(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return pegawai;
    }
    //update pelanggan yang diedit
    public void updatePegawai(Pegawai p) {
        //ambil id barang
        String strFilter = "_id_pegawai=" + p.getId_pegawai();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA_PEGAWAI, p.getNama_pegawai());
        args.put(DBHelper.COLUMN_ALAMAT_PEGAWAI, p.getAlamat_pegawai());
        args.put(DBHelper.COLUMN_JK_PEGAWAI, p.getJk_pegawai());
        args.put(DBHelper.COLUMN_STATUS_PEGAWAI, p.getStatus_pegawai());
        //update query
        database.update(DBHelper.TABLE_NAMA_PEGAWAI, args, strFilter, null);
    }

    //delete barang sesuai ID
    public void deletePegawai(long id_pegawai)
    {
        String strFilter = "_id_pegawai=" + id_pegawai;
        database.delete(DBHelper.TABLE_NAMA_PEGAWAI, strFilter, null);
    }

    public Obat createObat(String nama, String jenis) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAMA_OBAT, nama);
        values.put(DBHelper.COLUMN_JENIS_OBAT, jenis);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAMA_OBAT, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_OBAT,
                allObat, DBHelper.COLUMN_ID_OBAT + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek pelanggan
        Obat newObat = cursorToObat(cursor);

        // close cursor
        cursor.close();

        // mengembalikan pelanggan baru
        return newObat;
    }

    private Obat cursorToObat(Cursor cursor)
    {
        // buat objek pelanggan baru
        Obat obat = new Obat();
        // debug LOGCAT
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng " + cursor.getString(1) + "," + cursor.getString(2));

		/* Set atribut pada objek pelanggan dengan
		 * data kursor yang diambil dari database*/
        obat.setId_obat(cursor.getLong(0));
        obat.setNama_obat(cursor.getString(1));
        obat.setJenis_obat(cursor.getString(2));

        //kembalikan sebagai objek pelanggan
        return obat;
    }

    public ArrayList<Obat> getAllObat() {
        ArrayList<Obat> daftarObat = new ArrayList<Obat>();

        // select all SQL query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_OBAT,
                allObat, null, null, null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();
        // jika masih ada data, masukkan data pelanggan ke
        // daftar pelanggan
        while (!cursor.isAfterLast()) {
            Obat obat = cursorToObat(cursor);
            daftarObat.add(obat);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return daftarObat;
    }

    public Obat getObat(long id_obat)
    {
        Obat obat = new Obat(); //inisialisasi pelanggan
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAMA_OBAT, allObat, "_id_obat ="+id_obat, null, null, null, null);
        //ambil data yang pertama
        cursor.moveToFirst();
        //masukkan data cursor ke objek pelanggan
        obat = cursorToObat(cursor);
        //tutup sambungan
        cursor.close();
        //return barang
        return obat;
    }
    //update pelanggan yang diedit
    public void updateObat(Obat o) {
        //ambil id barang
        String strFilter = "_id_obat=" + o.getId_obat();
        //memasukkan ke content values
        ContentValues args = new ContentValues();
        //masukkan data sesuai dengan kolom pada database
        args.put(DBHelper.COLUMN_NAMA_OBAT, o.getNama_obat());
        args.put(DBHelper.COLUMN_JENIS_OBAT, o.getJenis_obat());
        //update query
        database.update(DBHelper.TABLE_NAMA_OBAT, args, strFilter, null);
    }

    //delete barang sesuai ID
    public void deleteObat(long id_obat)
    {
        String strFilter = "_id_obat=" + id_obat;
        database.delete(DBHelper.TABLE_NAMA_OBAT, strFilter, null);
    }
}
