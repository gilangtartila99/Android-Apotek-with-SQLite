package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by GILANG on 27/05/2016.
 */
public class MenuObat extends Activity implements View.OnClickListener{
    private Button bTambahObat;
    private Button bLihatObat;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuobat);

        bTambahObat = (Button) findViewById(R.id.button_tambah_obat);
        bTambahObat.setOnClickListener(this);
        bLihatObat = (Button) findViewById(R.id.button_lihat_obat);
        bLihatObat.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_tambah_obat :
                Intent i = new Intent(this, TambahObat.class);
                startActivity(i);
                break;

            case R.id.button_lihat_obat:
                Intent i2 = new Intent(this, LihatObat.class);
                startActivity(i2);
                break;
        }
    }
}
