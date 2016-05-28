package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by GILANG on 28/05/2016.
 */
public class MainActivity extends Activity {
    Button bLogin, bSignup;
    LoginDatabaseAdapter loginDatabaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDatabaseAdapter = new LoginDatabaseAdapter(this);
        loginDatabaseAdapter = loginDatabaseAdapter.open();

        bLogin = (Button) findViewById(R.id.button_masuk_login);
        bSignup = (Button) findViewById(R.id.button_masuk_signup);

        bSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(getApplicationContext(),
                        SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    public void signIn(View V) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");
        final EditText edUsername = (EditText) dialog
                .findViewById(R.id.editText_username_login);
        final EditText edPassword = (EditText) dialog
                .findViewById(R.id.editText_password_login);

        Button bLogin = (Button) dialog.findViewById(R.id.button_login);

        bLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String storedPassword = loginDatabaseAdapter.getSinlgeEntry(username);
                if (password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this,
                            "Login Berhasil", Toast.LENGTH_LONG)
                            .show();
                    dialog.dismiss();
                    Intent main = new Intent(MainActivity.this, Menu.class);
                    startActivity(main);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Username dan Password tidak sesuai",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDatabaseAdapter.close();
    }
}
