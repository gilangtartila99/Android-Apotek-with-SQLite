package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by GILANG on 27/05/2016.
 */
public class EditObat extends Activity implements View.OnClickListener{
    private DBDataSource dataSource;

    private long id;
    private String nama;
    private String jenis;

    private EditText edNama;
    private EditText edJenis;

    private TextView txId;

    private Button btnSaveObat;
    private Button btnCancelObat;

    private Obat obat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editobat);
        //inisialisasi variabel
        edNama = (EditText) findViewById(R.id.editText_nama_obat);
        edJenis = (EditText) findViewById(R.id.editText_jenis_obat);
        txId = (TextView) findViewById(R.id.text_id_obat);
        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);

        dataSource.open();
        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        jenis = bun.getString("jenis");
        nama = bun.getString("nama");
        //masukkan data-data barang tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edJenis.setText(jenis);

        //set listener pada tombol
        btnSaveObat = (Button) findViewById(R.id.button_save_edit_obat);
        btnSaveObat.setOnClickListener(this);
        btnCancelObat = (Button) findViewById(R.id.button_cancel_edit_obat);
        btnCancelObat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_save_edit_obat :
                obat = new Obat();
                obat.setNama_obat(edNama.getText().toString());
                obat.setJenis_obat(edJenis.getText().toString());
                obat.setId_obat(id);
                dataSource.updateObat(obat);
                Intent i = new Intent(this, LihatObat.class);
                startActivity(i);
                EditObat.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_edit_obat :
                finish();
                dataSource.close();
                break;
        }
    }
}
