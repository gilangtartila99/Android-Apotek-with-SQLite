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
public class EditPegawai extends Activity implements View.OnClickListener{
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

    private Button btnSavePegawai;
    private Button btnCancelPegawai;

    private Pegawai pegawai;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpegawai);
        //inisialisasi variabel
        edNama = (EditText) findViewById(R.id.editText_nama_pegawai);
        edAlamat = (EditText) findViewById(R.id.editText_alamat_pegawai);
        edJk = (EditText) findViewById(R.id.editText_jk_pegawai);
        edStatus = (EditText) findViewById(R.id.editText_status_pegawai);
        txId = (TextView) findViewById(R.id.text_id_pegawai);
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
        btnSavePegawai = (Button) findViewById(R.id.button_save_edit_pegawai);
        btnSavePegawai.setOnClickListener(this);
        btnCancelPegawai = (Button) findViewById(R.id.button_cancel_edit_pegawai);
        btnCancelPegawai.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            // apabila tombol save diklik (update barang)
            case R.id.button_save_edit_pegawai :
                pegawai = new Pegawai();
                pegawai.setNama_pegawai(edNama.getText().toString());
                pegawai.setAlamat_pegawai(edAlamat.getText().toString());
                pegawai.setJk_pegawai(edJk.getText().toString());
                pegawai.setStatus_pegawai(edStatus.getText().toString());
                pegawai.setId_pegawai(id);
                dataSource.updatePegawai(pegawai);
                Intent i = new Intent(this, LihatPegawai.class);
                startActivity(i);
                EditPegawai.this.finish();
                dataSource.close();
                break;
            // apabila tombol cancel diklik, finish activity
            case R.id.button_cancel_edit_pegawai :
                finish();
                dataSource.close();
                break;
        }
    }
}
