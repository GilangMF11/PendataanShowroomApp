package com.example.pendataanshowroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pendataanshowroom.model.CreateKarActivity;
import com.example.pendataanshowroom.model.Karyawan;
import com.example.pendataanshowroom.Activity_UpdateK;
import com.example.pendataanshowroom.adapter.KaryawanAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;


public class KaryawanActivity extends AppCompatActivity implements  View.OnClickListener{

    private ListView listView;
    private Button btnAdd;

    //tambahkan kode ini
    private KaryawanAdapter adapter;
    private ArrayList<Karyawan> karyawanList;
    DatabaseReference dbKaryawan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan);

        dbKaryawan = FirebaseDatabase.getInstance().getReference("karyawan");

        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);



        listView = findViewById(R.id.lv_list);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        //list Karyawan
       karyawanList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(KaryawanActivity.this, Activity_UpdateK.class);
                intent.putExtra(Activity_UpdateK.EXTRA_KARYAWAN, karyawanList.get(i));

                startActivity(intent);
            }
        });

    }

    protected void onStart(){
        super.onStart();

        dbKaryawan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                karyawanList.clear();

                for (DataSnapshot karyawanSnapshot : dataSnapshot.getChildren()) {
                    Karyawan karyawan = karyawanSnapshot.getValue(Karyawan.class);
                    karyawanList.add(karyawan);
                }

                KaryawanAdapter adapter = new KaryawanAdapter(KaryawanActivity.this);
                adapter.setKaryawanList(karyawanList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(KaryawanActivity.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add) {
            Intent intent = new Intent(KaryawanActivity.this, CreateKarActivity.class);
            startActivity(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                //menampilkan dialog

                break;
            case android.R.id.home:

                break;
        }

        return super.onOptionsItemSelected(item);
    }


}