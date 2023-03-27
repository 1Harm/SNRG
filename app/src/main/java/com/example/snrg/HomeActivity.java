package com.example.snrg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {
    private CardView dec, jan,cre;
    LinearLayout lay;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        lay=findViewById(R.id.lay);
        dec = findViewById(R.id.drinks_menu);
        cre = findViewById(R.id.snacks_menu);
        jan = findViewById(R.id.menu_jan);
        dec.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), DecemberActivity.class);
            String value = "December";
            intent.putExtra("choice", value);
            startActivity(intent);
        });
        jan.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JanuaryActivity.class);
            String value = "January";
            intent.putExtra("choice", value);
            startActivity(intent);
        });
        cre.setOnClickListener(v -> {Intent intent = new Intent(getApplicationContext(), MainActivity.class);startActivity(intent); Toast.makeText(getApplicationContext(), "Create new data", Toast.LENGTH_SHORT).show();});
    }


}