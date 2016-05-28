package com.example.gilang.tugasdatabaseandroid;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by GILANG on 27/05/2016.
 */
public class LihatPegawai extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Pegawai> values;
    private Button editButtonPegawai;
    private Button delButtonPegawai;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatpegawai);
        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllPegawai();

        // masukkan data barang ke array adapter
        ArrayAdapter<Pegawai> adapter = new ArrayAdapter<Pegawai>(this,
                android.R.layout.simple_list_item_1, values);

        // set adapter pada list
        setListAdapter(adapter);

        // mengambil listview untuk diset onItemLongClickListener
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    //apabila ada long click
    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos,
                                   final long id) {

        //tampilkan alert dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view_pegawai);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Pegawai p = (Pegawai) getListAdapter().getItem(pos);
        editButtonPegawai = (Button) dialog.findViewById(R.id.button_dialog_edit_pegawai);
        delButtonPegawai = (Button) dialog.findViewById(R.id.button_dialog_hapus_pegawai);

        //apabila tombol edit diklik
        editButtonPegawai.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(p.getId_pegawai());
                        dialog.dismiss();
                    }
                }
        );
        //apabila tombol delete diklik
        delButtonPegawai.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // Delete Barang
                        dataSource.deletePegawai(p.getId_pegawai());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }
    //method untuk edit data
    public void switchToEdit(long id)
    {
        Pegawai p = dataSource.getPegawai(id);
        Intent i = new Intent(this, EditPegawai.class);
        Bundle bun = new Bundle();
        bun.putLong("id", p.getId_pegawai());
        bun.putString("nama", p.getNama_pegawai());
        bun.putString("alamat", p.getAlamat_pegawai());
        bun.putString("jk", p.getJk_pegawai());
        bun.putString("status", p.getStatus_pegawai());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale()
    {
        LihatPegawai.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
