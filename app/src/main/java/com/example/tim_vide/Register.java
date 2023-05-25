package com.example.tim_vide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {
    Button btn_register;
    EditText name, last_name, email, password;
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
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
                String lastNameUser = last_name.getText().toString().toString();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(nameUser.isEmpty() && emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(Register.this, "Rellene los datos", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(nameUser, lastNameUser, emailUser, passUser);
                }
            }

            private void registerUser(String nameUser, String lastNameUser, String emailUser, String passUser) {
                mAuth.createUserWithEmailAndPassword(emailUser, passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Map<String, Object> map = new HashMap<>();
                        String id = mAuth.getCurrentUser().getUid();
                        map.put("id", id);
                        map.put("name", nameUser);
                        map.put("last_name", lastNameUser);
                        map.put("email", emailUser);
                        map.put("password", passUser);

                        mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                finish();
                                startActivity(new Intent(Register.this, MainActivity.class));
                                Toast.makeText(Register.this, "Registro completado", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                            }
                        });


                        Toast.makeText(Register.this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
    /*private EditText et_nombre, et_apellido, et_correo, et_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_nombre = (EditText) findViewById(R.id.name);
        et_apellido = (EditText) findViewById(R.id.lastname);
        et_correo = (EditText) findViewById(R.id.username);
        et_contrasena = (EditText) findViewById(R.id.password);
    }

    public void RegistrarUsuario(View view){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String apellido = et_apellido.getText().toString();
        String correo = et_correo.getText().toString();
        String contrasena = et_contrasena.getText().toString();

        if(!nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !contrasena.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("nombre", nombre);
            registro.put("apellido", apellido);
            registro.put("correo", correo);
            registro.put("contrasena", contrasena);

            BaseDeDatos.insert("usuario", null, registro);
            BaseDeDatos.close();
            et_nombre.setText("");
            et_apellido.setText("");
            et_correo.setText("");
            et_contrasena.setText("");

            Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();

        }
    }*/