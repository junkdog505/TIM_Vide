package com.example.tim_vide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Practicar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practicar);
        Button btn1 = findViewById(R.id.btnLetras);
        Button btn2 = findViewById(R.id.btnNumeros);
        Button btn3 = findViewById(R.id.btnPalabras);
        Button btn4 = findViewById(R.id.btnSignos);


        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Practicar.this, Teclado.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Practicar.this, Teclado.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Practicar.this, Teclado.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Practicar.this, Teclado.class);
                startActivity(intent);
            }
        });
    }
}