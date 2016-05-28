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
public class LihatObat extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSource dataSource;

    //inisialisasi arraylist
    private ArrayList<Obat> values;
    private Button editButtonObat;
    private Button delButtonObat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatobat);
        dataSource = new DBDataSource(this);
        // buka kontroller
        dataSource.open();

        // ambil semua data barang
        values = dataSource.getAllObat();

        // masukkan data barang ke array adapter
        ArrayAdapter<Obat> adapter = new ArrayAdapter<Obat>(this,
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
        dialog.setContentView(R.layout.dialog_view_obat);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Obat o = (Obat) getListAdapter().getItem(pos);
        editButtonObat = (Button) dialog.findViewById(R.id.button_dialog_edit_obat);
        delButtonObat = (Button) dialog.findViewById(R.id.button_dialog_hapus_obat);

        //apabila tombol edit diklik
        editButtonObat.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(o.getId_obat());
                        dialog.dismiss();
                    }
                }
        );
        //apabila tombol delete diklik
        delButtonObat.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // Delete Barang
                        dataSource.deleteObat(o.getId_obat());
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
        Obat o = dataSource.getObat(id);
        Intent i = new Intent(this, EditObat.class);
        Bundle bun = new Bundle();
        bun.putLong("id", o.getId_obat());
        bun.putString("nama", o.getNama_obat());
        bun.putString("jenis", o.getJenis_obat());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    //method yang dipanggil ketika edit data selesai
    public void finale()
    {
        LihatObat.this.finish();
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
