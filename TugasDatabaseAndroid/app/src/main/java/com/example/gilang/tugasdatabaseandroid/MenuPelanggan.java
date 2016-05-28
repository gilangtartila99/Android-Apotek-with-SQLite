package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GILANG on 27/05/2016.
 */
public class MenuPelanggan extends Activity implements View.OnClickListener{
    private Button bTambahPelanggan;
    private Button bLihatPelanggan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupelanggan);

        bTambahPelanggan = (Button) findViewById(R.id.button_tambah_pelanggan);
        bTambahPelanggan.setOnClickListener(this);
        bLihatPelanggan = (Button) findViewById(R.id.button_lihat_pelanggan);
        bLihatPelanggan.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_tambah_pelanggan :
                Intent i = new Intent(this, TambahPelanggan.class);
                startActivity(i);
                break;

            case R.id.button_lihat_pelanggan:
                Intent i2 = new Intent(this, LihatPelanggan.class);
                startActivity(i2);
                break;
        }
    }
}
