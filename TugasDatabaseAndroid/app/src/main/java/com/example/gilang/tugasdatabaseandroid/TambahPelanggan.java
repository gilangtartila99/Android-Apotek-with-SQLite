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
public class TambahPelanggan extends Activity implements View.OnClickListener{
    private Button buttonSubmitPelanggan;
    private EditText edNama;
    private EditText edAlamat;
    private EditText edJk;
    private EditText edStatus;
    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahpelanggan);

        buttonSubmitPelanggan = (Button) findViewById(R.id.button_submit_pelanggan);
        buttonSubmitPelanggan.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.editText_nama_pelanggan);
        edAlamat = (EditText) findViewById(R.id.editText_alamat_pelanggan);
        edJk = (EditText) findViewById(R.id.editText_jk_pelanggan);
        edStatus = (EditText) findViewById(R.id.editText_status_pelanggan);

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
                Pelanggan pelanggan = null;
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
            case R.id.button_submit_pelanggan:
                // insert data barang baru
                pelanggan = dataSource.createPelanggan(nama, alamat, jk, status);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Pelanggan\n" +
                        "nama" + pelanggan.getNama_pelanggan() +
                        "alamat" + pelanggan.getAlamat_pelanggan() +
                        "jk" + pelanggan.getJk_pelanggan() +
                        "status" + pelanggan.getStatus_pelanggan(), Toast.LENGTH_LONG).show();
                break;
        }

    }
}
