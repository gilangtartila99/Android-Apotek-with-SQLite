package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GILANG on 27/05/2016.
 */
public class MenuPegawai extends Activity implements View.OnClickListener{
    private Button bTambahPegawai;
    private Button bLihatPegawai;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupegawai);

        bTambahPegawai = (Button) findViewById(R.id.button_tambah_pegawai);
        bTambahPegawai.setOnClickListener(this);
        bLihatPegawai = (Button) findViewById(R.id.button_lihat_pegawai);
        bLihatPegawai.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_tambah_pegawai :
                Intent i = new Intent(this, TambahPegawai.class);
                startActivity(i);
                break;

            case R.id.button_lihat_pegawai:
                Intent i2 = new Intent(this, LihatPegawai.class);
                startActivity(i2);
                break;
        }
    }
}
