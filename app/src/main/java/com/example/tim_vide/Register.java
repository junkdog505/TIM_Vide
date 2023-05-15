package com.example.tim_vide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText et_nombre, et_apellido, et_correo, et_contrasena;

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
    }
}