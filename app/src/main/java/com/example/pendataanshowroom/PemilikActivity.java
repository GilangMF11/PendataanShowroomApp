package com.example.pendataanshowroom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PemilikActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private Button btnAddPemilik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilik);

        listView = findViewById(R.id.lvp_list);
        btnAddPemilik = findViewById(R.id.btn_addP);
        btnAddPemilik.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
