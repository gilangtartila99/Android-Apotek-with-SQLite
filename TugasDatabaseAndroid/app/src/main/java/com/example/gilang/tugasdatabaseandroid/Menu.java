package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity implements View.OnClickListener {
    private Button bMenuPelanggan;
    private Button bMenuPegawai;
    private Button bMenuObat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bMenuPelanggan = (Button) findViewById(R.id.button_masuk_pelanggan);
        bMenuPelanggan.setOnClickListener(this);
        bMenuPegawai = (Button) findViewById(R.id.button_masuk_pegawai);
        bMenuPegawai.setOnClickListener(this);
        bMenuObat = (Button) findViewById(R.id.button_masuk_obat);
        bMenuObat.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_masuk_pelanggan :
                Intent i = new Intent(this, MenuPelanggan.class);
                startActivity(i);
                break;

            case R.id.button_masuk_pegawai:
                Intent i2 = new Intent(this, MenuPegawai.class);
                startActivity(i2);
                break;

            case R.id.button_masuk_obat :
                Intent i3 = new Intent(this, MenuObat.class);
                startActivity(i3);
                break;
        }
    }
}
