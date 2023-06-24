package com.example.tim_vide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuAprender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aprender);
        ImageButton btn1 = findViewById(R.id.btnLetras);
        ImageButton btn2 = findViewById(R.id.btnNumeros);
        ImageButton btn3 = findViewById(R.id.btnSignos);


        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MenuAprender.this,  AprenderView.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAprender.this, AprenderNums.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAprender.this, AprenderSign.class);
                startActivity(intent);
            }
        });
    }
}