package com.example.tim_vide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText et_correo, et_contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_correo = (EditText) findViewById(R.id.correo);
        et_contrasena = (EditText) findViewById(R.id.contrasena);
    }
    public void IniciarSesion(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "usuarios", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String correo = et_correo.getText().toString();
        String contrasena = et_contrasena.getText().toString();
        if(!correo.isEmpty() && !contrasena.isEmpty()){
            String consulta = "SELECT id, nombre, apellido FROM usuario WHERE correo = '" + correo + "' AND contrasena = '" + contrasena + "'";
            Cursor file = BaseDeDatos.rawQuery(consulta, null);
            if(file.moveToFirst()){
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "Usuario inexistente o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}