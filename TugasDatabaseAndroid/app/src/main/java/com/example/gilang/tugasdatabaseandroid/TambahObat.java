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
public class TambahObat extends Activity implements View.OnClickListener{
    private Button buttonSubmitObat;
    private EditText edNama;
    private EditText edJenis;
    //inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahobat);

        buttonSubmitObat = (Button) findViewById(R.id.button_submit_obat);
        buttonSubmitObat.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.editText_nama_obat);
        edJenis = (EditText) findViewById(R.id.editText_jenis_obat);

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
        String jenis = null;
        @SuppressWarnings("unused")

        //inisialisasi pelanggan baru (masih kosong)
                Obat obat = null;
        if(edNama.getText()!=null && edJenis.getText()!=null)
        {
			/* jika field nama, merk, dan harga tidak kosong
			 * maka masukkan ke dalam data barang*/
            nama = edNama.getText().toString();
            jenis = edJenis.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.button_submit_obat:
                // insert data barang baru
                obat = dataSource.createObat(nama, jenis);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Obat\n" +
                        "nama" + obat.getNama_obat() +
                        "jenis" + obat.getJenis_obat(), Toast.LENGTH_LONG).show();
                break;
        }

    }
}
