package com.example.gilang.tugasdatabaseandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by GILANG on 28/05/2016.
 */
public class SignUpActivity extends Activity {
    EditText edUsername, edPassword, edConfirmPassword;
    Button bSignup;
    Context context = this;
    LoginDatabaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        loginDataBaseAdapter = new LoginDatabaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        edUsername = (EditText) findViewById(R.id.editText_username);
        edPassword = (EditText) findViewById(R.id.editText_password);
        edConfirmPassword = (EditText) findViewById(R.id.editText_ConfirmPassword);

        bSignup = (Button) findViewById(R.id.button_signup);
        bSignup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPassword = edConfirmPassword.getText().toString();
                if (username.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Kosong",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password tidak sesuai", Toast.LENGTH_LONG)
                            .show();
                    return;
                } else {

                    loginDataBaseAdapter.insertEntry(username, password);
                    Toast.makeText(getApplicationContext(),
                            "Akun Berhasil Dibuat", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(SignUpActivity.this,
                            MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
