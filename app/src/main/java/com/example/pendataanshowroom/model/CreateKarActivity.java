package com.example.pendataanshowroom.model;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.pendataanshowroom.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class CreateKarActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNim, edtNama;
    private Button btnSave;

    private Karyawan karyawan;

    DatabaseReference kDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kar);

        kDatabase = FirebaseDatabase.getInstance().getReference();

        edtNama = findViewById(R.id.edt_nama);
        edtNim = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);

        karyawan = new Karyawan();

        DatabaseReference kDatabase;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            saveKaryawan();
        }
    }

    private void saveKaryawan() {
        String nama = edtNama.getText().toString().trim();
        String nim = edtNim.getText().toString().trim();

        boolean isEmptyFields = false;

        if (TextUtils.isEmpty(nama)) {
            isEmptyFields = true;
            edtNama.setError("Field ini tidak boleh kosong");
        }

        if (TextUtils.isEmpty(nim)) {
            isEmptyFields = true;
            edtNim.setError("Field ini tidak boleh kosong");
        }

        if (! isEmptyFields) {

            Toast.makeText(CreateKarActivity.this, "Saving Data...", Toast.LENGTH_SHORT).show();

            DatabaseReference dbKaryawan = kDatabase.child("karyawan");

            String id = dbKaryawan.push().getKey();
            karyawan.setId(id);
            karyawan.setNim(nim);
            karyawan.setNama(nama);
            karyawan.setPhoto("");

            //insert data
            dbKaryawan.child(id).setValue(karyawan);

            finish();
    }
}
}