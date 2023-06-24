package com.example.tim_vide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);

        Button btn_login = findViewById(R.id.login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (emailUser.isEmpty() && passUser.isEmpty()) {
                    Toast.makeText(Login.this, "Ingrese los datos correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(emailUser, passUser);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verificar si el usuario ya ha iniciado sesión
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // El usuario ya ha iniciado sesión, redirigir a la pantalla de inicio directamente
            startActivity(new Intent(Login.this, Home.class));
            finish();
        }
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(Login.this, Home.class));
                    Toast.makeText(Login.this, "Bienvenido :)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "Ingreso no Autorizado", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
