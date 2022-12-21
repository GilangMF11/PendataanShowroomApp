package com.example.pendataanshowroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pendataanshowroom.model.Karyawan;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Activity_UpdateK extends AppCompatActivity implements View.OnClickListener{


    private EditText edtNim, edtNama;
    private Button btnUpdate, btnDelete;
    public static final String EXTRA_KARYAWAN = "extra_karyawan";
    public final int ALERT_DIALOG_CLOSE = 10;
    public final int ALERT_DIALOG_DELETE = 20;

    private Karyawan karyawan;
    private String karyawanId;

    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_k);


        mDatabase = FirebaseDatabase.getInstance().getReference();

        edtNama = findViewById(R.id.edt_edit_nama);
        edtNim = findViewById(R.id.edt_edit_nim);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delate);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        karyawan = getIntent().getParcelableExtra(EXTRA_KARYAWAN);

        if (karyawan != null) {
            karyawanId = karyawan.getId();
        } else {
            karyawan = new Karyawan();
        }

        if (karyawan != null) {
            edtNim.setText(karyawan.getNim());
            edtNama.setText(karyawan.getNama());

        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            updateKaryawan();
        }else if (view.getId() == R.id.btn_delate){
            deleteData();
        }
    }
    private void updateKaryawan() {
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

            Toast.makeText(Activity_UpdateK.this, "Updating Data...", Toast.LENGTH_SHORT).show();

            karyawan.setNim(nim);
            karyawan.setNama(nama);
            karyawan.setPhoto("");

            DatabaseReference dbKaryawan = mDatabase.child("karyawan");

            //update data
            dbKaryawan.child(karyawanId).setValue(karyawan);

            finish();

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_karyawan, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
            case  R.id.btn_delate:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    public void deleteData(){
        //hapus data
        DatabaseReference dbKaryawan =
                mDatabase.child("karyawan").child(karyawanId);

        dbKaryawan.removeValue();

        Toast.makeText(Activity_UpdateK.this, "Deleting data...",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan pada form";
        } else {
            dialogTitle = "Hapus Data";
            dialogMessage = "Apakah anda yakin ingin menghapus item ini";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (isDialogClose) {
                            finish();
                        } else {

                            //hapus data
                            DatabaseReference dbKaryawan =
                                    mDatabase.child("karyawan").child(karyawanId);

                            dbKaryawan.removeValue();

                            Toast.makeText(Activity_UpdateK.this, "Deleting data...",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
