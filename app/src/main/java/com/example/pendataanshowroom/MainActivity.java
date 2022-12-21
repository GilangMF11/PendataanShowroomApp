package com.example.pendataanshowroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseUser firebaseUser;
    private TextView textName;
    private Button btnLogout;
    private CardView card1, card2, card3, card4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.name);
        btnLogout = findViewById(R.id.btn_Logout);
        card1 = (CardView) findViewById(R.id.showroomCard);
        card2 = (CardView) findViewById(R.id.pmkCard);
        card3 = (CardView) findViewById(R.id.karyCard);
        card4 = (CardView) findViewById(R.id.motorCard);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null){
            textName.setText(firebaseUser.getDisplayName());
        }else {
            textName.setText("Login Gagal!");
        }
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()) {
            case  R.id.showroomCard:
                i = new Intent(this, ShowroomActivity.class);
                startActivity(i);
                break;

            case  R.id.pmkCard:
                i = new Intent(this, PemilikActivity.class);
                startActivity(i);
                break;

            case  R.id.karyCard:
                i = new Intent(this, KaryawanActivity.class);
                startActivity(i);
                break;

            case  R.id.motorCard:
                i = new Intent(this, MotorActivity.class);
                startActivity(i);
                break;
        }
    }
}