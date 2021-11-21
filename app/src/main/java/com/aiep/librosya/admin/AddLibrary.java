package com.aiep.librosya.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aiep.librosya.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddLibrary extends AppCompatActivity {

    EditText nombre, direccion, zipcode, imagen;


    RequestQueue requestQueue;

    private static final String URL_SAVED = "http://192.168.1.96/android/save.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_library);


        requestQueue = Volley.newRequestQueue(this);
        //EdutText

        nombre  = findViewById(R.id.library_add_name);
        direccion  = findViewById(R.id.library_add_direccion);
        zipcode  = findViewById(R.id.library_add_zipcode);
        imagen  = findViewById(R.id.library_add_imagen);
    }


    public void AgregarBiblioteca(View view){
        String str_nombre = nombre.getText().toString().trim();
        String str_direccion = direccion.getText().toString().trim();
        String str_zipcode = zipcode.getText().toString().trim();
        String str_imagen = imagen.getText().toString().trim();

        Createlibrary(str_nombre, str_direccion, str_zipcode, str_imagen);

        nombre.setText("");
        direccion.setText("");
        zipcode.setText("");
        imagen.setText("");

    }


    private void Createlibrary(final String str_nombre, final String str_direccion, final String str_zipcode, final String str_imagen) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVED, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Biblioteca agregada correctamente", Toast.LENGTH_SHORT).show();
                nombre.getText().clear();
                direccion.getText().clear();
                zipcode.getText().clear();
                imagen.getText().clear();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("biblioteca_name", str_nombre);
                parametros.put("biblioteca_image", str_imagen);
                parametros.put("address", str_direccion);
                parametros.put("address_postalcode", str_zipcode);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void Volver(View v){
        finish();
    }

}