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
public class EditPelanggan extends Activity implements View.OnClickListener{
    private DBDataSource dataSource;

    private long id;
    private String nama;
    private String alamat;
    private String jk;
    private String status;

    private EditText edNama;
    private EditText edAlamat;
    private EditText edJk;
    private EditText edStatus;

    private TextView txId;

    private Button btnSavePelanggan;
    private Button btnCancelPelanggan;

    private Pelanggan pelanggan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpelanggan);
        //inisialisasi variabel
        edNama = (EditText) findViewById(R.id.editText_nama_pelanggan);
        edAlamat = (EditText) findViewById(R.id.editText_alamat_pelanggan);
        edJk = (EditText) findViewById(R.id.editText_jk_pelanggan);
        edStatus = (EditText) findViewById(R.id.editText_status_pelanggan);
        txId = (TextView) findViewById(R.id.text_id_pelanggan);
        //buat sambungan baru ke database
        dataSource = new DBDataSource(this);

        dataSource.open();
        // ambil data barang dari extras
        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        status = bun.getString("status");
        alamat = bun.getString("alamat");
        jk = bun.getString("jk");
        nama = bun.getString("nama");
        //masukkan data-data barang tersebut ke field editor
        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edAlamat.setText(alamat);
        edJk.setText(jk);
        edStatus.setText(status);

        //set listener pada tombol
        btnSavePelanggan = (Button) findViewById(R.id.button_save_edit_pelanggan);
        btnSavePelanggan.setOnClickListener(this);
        btnCancelPelanggan = (Button) findViewById(R.id.button_cancel_edit_pelanggan);
        btnCancelPelanggan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_save_edit_pelanggan :
                pelanggan = new Pelanggan();
                pelanggan.setNama_pelanggan(edNama.getText().toString());
                pelanggan.setAlamat_pelanggan(edAlamat.getText().toString());
                pelanggan.setJk_pelanggan(edJk.getText().toString());
                pelanggan.setStatus_pelanggan(edStatus.getText().toString());
                pelanggan.setId_pelanggan(id);
                dataSource.updatePelanggan(pelanggan);
                Intent i = new Intent(this, LihatPelanggan.class);
                startActivity(i);
                EditPelanggan.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_edit_pelanggan :
                finish();
                dataSource.close();
                break;
        }
    }
}
