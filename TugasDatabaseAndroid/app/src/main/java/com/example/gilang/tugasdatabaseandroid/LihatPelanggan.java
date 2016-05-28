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
public class LihatPelanggan extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Pelanggan> values;
    private Button editButtonPelanggan;
    private Button delButtonPelanggan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatpelanggan);
        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllPelanggan();

        // masukkan data barang ke array adapter
        ArrayAdapter<Pelanggan> adapter = new ArrayAdapter<Pelanggan>(this,
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
        dialog.setContentView(R.layout.dialog_view_pelanggan);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Pelanggan p = (Pelanggan) getListAdapter().getItem(pos);
        editButtonPelanggan = (Button) dialog.findViewById(R.id.button_dialog_edit_pelanggan);
        delButtonPelanggan = (Button) dialog.findViewById(R.id.button_dialog_hapus_pelanggan);

        //apabila tombol edit diklik
        editButtonPelanggan.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(p.getId_pelanggan());
                        dialog.dismiss();
                    }
                }
        );
        //apabila tombol delete diklik
        delButtonPelanggan.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // Delete Barang
                        dataSource.deletePelanggan(p.getId_pelanggan());
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
        Pelanggan p = dataSource.getPelanggan(id);
        Intent i = new Intent(this, EditPelanggan.class);
        Bundle bun = new Bundle();
        bun.putLong("id", p.getId_pelanggan());
        bun.putString("nama", p.getNama_pelanggan());
        bun.putString("alamat", p.getAlamat_pelanggan());
        bun.putString("jk", p.getJk_pelanggan());
        bun.putString("status", p.getStatus_pelanggan());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale()
    {
        LihatPelanggan.this.finish();
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
