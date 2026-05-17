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

        sharedPreferences = getSharedPreferences("DATA_MHS", MODE_PRIVATE);


        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);

        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);

        listViewData = findViewById(R.id.listViewData);

        String nim = sharedPreferences.getString("nim", "");
        String nama = sharedPreferences.getString("nama", "");
        String prodi = sharedPreferences.getString("prodi", "");
        String kelas = sharedPreferences.getString("kelas", "");
        String alamat = sharedPreferences.getString("alamat", "");
        String email = sharedPreferences.getString("email", "");


        dataMahasiswa = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dataMahasiswa
        );

        listViewData.setAdapter(adapter);

        if(!nim.isEmpty()) {

            String data =
                    "NIM : " + nim + "\n" +
                            "Nama : " + nama + "\n" +
                            "Prodi : " + prodi + "\n" +
                            "Kelas : " + kelas + "\n" +
                            "Alamat : " + alamat + "\n" +
                            "Email : " + email;

            dataMahasiswa.add(data);

            adapter.notifyDataSetChanged();

        }

        btnTambah.setOnClickListener(v -> {

            String data =
                    "NIM : " + etNim.getText().toString() + "\n" +
                            "Nama : " + etNama.getText().toString() + "\n" +
                            "Prodi : " + etProdi.getText().toString() + "\n" +
                            "Kelas : " + etKelas.getText().toString() + "\n" +
                            "Alamat : " + etAlamat.getText().toString() + "\n" +
                            "Email : " + etEmail.getText().toString();

            dataMahasiswa.add(data);

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("nim", etNim.getText().toString());
            editor.putString("nama", etNama.getText().toString());
            editor.putString("prodi", etProdi.getText().toString());
            editor.putString("kelas", etKelas.getText().toString());
            editor.putString("alamat", etAlamat.getText().toString());
            editor.putString("email", etEmail.getText().toString());

            editor.apply();

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

            SharedPreferences loginPref =
                    getSharedPreferences("LOGIN", MODE_PRIVATE);

            SharedPreferences.Editor editor = loginPref.edit();

            editor.clear();
            editor.apply();

            Toast.makeText(this,
                    "Logout Berhasil",
                    Toast.LENGTH_SHORT).show();

            Intent intent =
                    new Intent(DashboardActivity.this,
                            LoginActivity.class);

            startActivity(intent);
            finish();

        });
    }
}