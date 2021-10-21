package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.Persona;
import com.aiep.librosya.utils.Utils;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText email, password;
    List<Persona> elements;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);


    }




    public void IniciarSesion(View view){
        DBHelper conn=new DBHelper(this,"LibrosYa",null,1);
        String e = email.getText().toString();
        String pass = password.getText().toString();

        SQLiteDatabase db = conn.getReadableDatabase();


        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utils.TABLA_PERSONA +" WHERE email = '"+ e +"' and password = '"+ pass+"'", null);

        cursor.moveToFirst();
        if (e.equals("") || pass.equals("")){
            Toast.makeText(this, "Uno o mas campos vacios", Toast.LENGTH_LONG).show();
        }else if (cursor.getCount()  > 0){

            Toast.makeText(this, "Bienvenido " + cursor.getString(2).toString(), Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            int user_id = cursor.getInt(0);
            i.putExtra("user_id", user_id);
            startActivity(i);
            this.finish();

        }else {
            Toast.makeText(this, "El inicio de sesion o la contraseña no son validos", Toast.LENGTH_LONG).show();
        }

    }

    public void Registrarse(View view){
        startActivity(new Intent(MainActivity.this, RegistroActivity.class));
    }

}