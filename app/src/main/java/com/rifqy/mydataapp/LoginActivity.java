package com.rifqy.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnCancel;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);

        sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);

        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if(isLoggedIn) {

            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        }

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if(username.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();

            } else if(username.equals("admin") && password.equals("admin123")) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.putString("password", password);

                editor.apply();

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();

            }

        });

        btnCancel.setOnClickListener(v -> {

            etUsername.setText("");
            etPassword.setText("");

        });

    }
}