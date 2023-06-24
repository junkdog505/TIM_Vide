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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button btn_register;
    EditText name, last_name, email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.name);
        last_name = findViewById(R.id.lastname);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btn_register = findViewById(R.id.btnRegistrar);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = name.getText().toString().trim();
                String lastNameUser = last_name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if (nameUser.isEmpty() || lastNameUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty()) {
                    Toast.makeText(Register.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                } else if (!isEmailValid(emailUser)) {
                    Toast.makeText(Register.this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordValid(passUser)) {
                    Toast.makeText(Register.this, "La contraseña debe contener al menos 8 caracteres y incluir números y letras", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(nameUser, lastNameUser, emailUser, passUser);
                }
            }

            private boolean isEmailValid(String email) {
                // Expresión regular para validar el correo electrónico
                String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
                return email.matches(emailRegex);
            }

            private boolean isPasswordValid(String password) {
                // Expresión regular para validar la contraseña (al menos 8 caracteres, incluyendo números y letras)
                String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
                return password.matches(passwordRegex);
            }

            private void registerUser(String nameUser, String lastNameUser, String emailUser, String passUser) {
                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = mAuth.getCurrentUser().getUid();

                            // Crear un mapa con los datos del usuario
                            Map<String, Object> map = new HashMap<>();
                            map.put("id", id);
                            map.put("name", nameUser);
                            map.put("last_name", lastNameUser);
                            map.put("email", emailUser);
                            map.put("password", passUser);

                            // Configurar las opciones de Firestore para habilitar el almacenamiento en caché
                            FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                                    .setPersistenceEnabled(true)
                                    .build();
                            mFirestore.setFirestoreSettings(settings);

                            // Guardar los datos del usuario en Firestore
                            mFirestore.collection("user").document(id).set(map)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            // Datos guardados exitosamente

                                            // Redirigir al usuario a la actividad principal
                                            finish();
                                            startActivity(new Intent(Register.this, Login.class));
                                            Toast.makeText(Register.this, "Registro completado", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Error al guardar los datos en Firestore
                                            Toast.makeText(Register.this, "Error al registrar (Firestore)", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            // Error al registrar el usuario
                            Toast.makeText(Register.this, "Error al registrar (Usuario)", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
