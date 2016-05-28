package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by GILANG on 27/05/2016.
 */
public class TambahPegawai extends Activity implements View.OnClickListener{
    private Button buttonSubmitPegawai;
    private EditText edNama;
    private EditText edAlamat;
    private EditText edJk;
    private EditText edStatus;
    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahpegawai);

        buttonSubmitPegawai = (Button) findViewById(R.id.button_submit_pegawai);
        buttonSubmitPegawai.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.editText_nama_pegawai);
        edAlamat = (EditText) findViewById(R.id.editText_alamat_pegawai);
        edJk = (EditText) findViewById(R.id.editText_jk_pegawai);
        edStatus = (EditText) findViewById(R.id.editText_status_pegawai);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    //KETIKA Tombol Submit Diklik
    @Override
    public void onClick(View v) {
        // Inisialisasi data pelanggan
        String nama = null;
        String alamat = null;
        String jk = null;
        String status = null;
        @SuppressWarnings("unused")

        //inisialisasi pelanggan baru (masih kosong)
                Pegawai pegawai = null;
        if(edNama.getText()!=null && edAlamat.getText()!=null && edJk.getText()!=null && edStatus.getText()!=null)
        {
			/* jika field nama, merk, dan harga tidak kosong
			 * maka masukkan ke dalam data barang*/
            nama = edNama.getText().toString();
            alamat = edAlamat.getText().toString();
            jk = edJk.getText().toString();
            status = edStatus.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.button_submit_pegawai:
                // insert data barang baru
                pegawai = dataSource.createPegawai(nama, alamat, jk, status);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Pegawai\n" +
                        "nama" + pegawai.getNama_pegawai() +
                        "alamat" + pegawai.getAlamat_pegawai() +
                        "jk" + pegawai.getJk_pegawai() +
                        "status" + pegawai.getStatus_pegawai(), Toast.LENGTH_LONG).show();
                break;
        }

    }
}
