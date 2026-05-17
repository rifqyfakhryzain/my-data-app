package com.rifqy.mydataapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    EditText etNim, etNama, etProdi, etKelas, etAlamat, etEmail;
    Button btnTambah, btnLogout;

    ListView listViewData;

    ArrayList<String> dataMahasiswa;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);


        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);

        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);

        listViewData = findViewById(R.id.listViewData);

        dataMahasiswa = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataMahasiswa
        );

        listViewData.setAdapter(adapter);

        btnTambah.setOnClickListener(v -> {

            String data =
                    "NIM : " + etNim.getText().toString() + "\n" +
                            "Nama : " + etNama.getText().toString() + "\n" +
                            "Prodi : " + etProdi.getText().toString() + "\n" +
                            "Kelas : " + etKelas.getText().toString() + "\n" +
                            "Alamat : " + etAlamat.getText().toString() + "\n" +
                            "Email : " + etEmail.getText().toString();

            dataMahasiswa.add(data);

            adapter.notifyDataSetChanged();

            Toast.makeText(this, "Data Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();

            etNim.setText("");
            etNama.setText("");
            etProdi.setText("");
            etKelas.setText("");
            etAlamat.setText("");
            etEmail.setText("");

        });

        btnLogout.setOnClickListener(v -> {

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.clear();
            editor.apply();

            Toast.makeText(this, "Logout Berhasil", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        });

    }
}