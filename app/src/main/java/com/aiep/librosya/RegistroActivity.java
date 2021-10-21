package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiep.librosya.utils.Utils;
import com.bumptech.glide.util.Util;

public class RegistroActivity extends AppCompatActivity {
    Button registrarse;
    EditText nombre, email, password;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        nombre = (EditText) findViewById(R.id.registro_nombre);
        email = (EditText) findViewById(R.id.registro_email);
        password = (EditText) findViewById(R.id.registro_password);
        registrarse = (Button) findViewById(R.id.registro_registrarse);


    }

    public void RegistrarPersona(View view) {
        DBHelper conn=new DBHelper(this,"LibrosYa",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utils.CAMPO_NOMBRE,nombre.getText().toString());
        values.put(Utils.CAMPO_EMAIL,email.getText().toString());
        values.put(Utils.CAMPO_PASSWORD,password.getText().toString());

        db.insert(Utils.TABLA_PERSONA,null,values);

        Toast.makeText(this,"Registrado Correctamente ",Toast.LENGTH_SHORT).show();
        db.close();
        finish();
    }



    public void Volver(View view){
        finish();
    }

}