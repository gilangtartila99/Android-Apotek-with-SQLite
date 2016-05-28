package com.example.gilang.tugasdatabaseandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by GILANG on 27/05/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAMA_PELANGGAN = "data_pelanggan";
    public static final String TABLE_NAMA_PEGAWAI = "data_pegawai";
    public static final String TABLE_NAMA_OBAT = "data_obat";

    public static final String COLUMN_ID_PELANGGAN = "_id_pelanggan";
    public static final String COLUMN_NAMA_PELANGGAN = "nama_pelanggan";
    public static final String COLUMN_ALAMAT_PELANGGAN = "alamat_pelanggan";
    public static final String COLUMN_JK_PELANGGAN = "jk_pelanggan";
    public static final String COLUMN_STATUS_PELANGGAN = "status_pelanggan";

    public static final String COLUMN_ID_PEGAWAI = "_id_pegawai";
    public static final String COLUMN_NAMA_PEGAWAI = "nama_pegawai";
    public static final String COLUMN_ALAMAT_PEGAWAI = "alamat_pegawai";
    public static final String COLUMN_JK_PEGAWAI = "jk_pegawai";
    public static final String COLUMN_STATUS_PEGAWAI = "status_pegawai";

    public static final String COLUMN_ID_OBAT = "_id_obat";
    public static final String COLUMN_NAMA_OBAT = "nama_obat";
    public static final String COLUMN_JENIS_OBAT = "jenis_obat";


    public static final String DATABASE_NAME = "apotek.db";
    public static final int db_version=1;

    // Perintah SQL untuk membuat tabel database baru
    private static final String TABLE_PELANGGAN = " create table "
            + TABLE_NAMA_PELANGGAN + "("
            + COLUMN_ID_PELANGGAN + " integer primary key autoincrement, "
            + COLUMN_NAMA_PELANGGAN + " varchar(50) not null, "
            + COLUMN_ALAMAT_PELANGGAN + " varchar(50) not null, "
            + COLUMN_JK_PELANGGAN + " varchar(50) not null, "
            + COLUMN_STATUS_PELANGGAN + " varchar(50) not null);";

    private static final String TABLE_PEGAWAI = " create table "
            + TABLE_NAMA_PEGAWAI + "("
            + COLUMN_ID_PEGAWAI + " integer primary key autoincrement, "
            + COLUMN_NAMA_PEGAWAI + " varchar(50) not null, "
            + COLUMN_ALAMAT_PEGAWAI + " varchar(50) not null, "
            + COLUMN_JK_PEGAWAI + " varchar(50) not null, "
            + COLUMN_STATUS_PEGAWAI + " varchar(50) not null);";

    private static final String TABLE_OBAT = " create table "
            + TABLE_NAMA_OBAT + "("
            + COLUMN_ID_OBAT + " integer primary key autoincrement, "
            + COLUMN_NAMA_OBAT + " varchar(50) not null, "
            + COLUMN_JENIS_OBAT + " varchar(50) not null);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_PELANGGAN);
        db.execSQL(TABLE_PEGAWAI);
        db.execSQL(TABLE_OBAT);
    }

    //dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + "to"
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMA_PELANGGAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMA_PEGAWAI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMA_OBAT);
        onCreate(db);
    }
}
