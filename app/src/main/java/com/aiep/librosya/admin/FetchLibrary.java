package com.aiep.librosya.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aiep.librosya.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FetchLibrary extends AppCompatActivity {

    Button btn_actualizar, btn_fetch, btn_delete;
    EditText ed_nombre, ed_direccion, ed_zipcode, ed_imagen, ed_id, ed_telefono;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_library);


        //button
        btn_actualizar = findViewById(R.id.library_update);
        btn_fetch = findViewById(R.id.library_fetch);
        btn_delete = findViewById(R.id.library_delete);

        btn_delete.setVisibility(View.GONE);
        btn_actualizar.setVisibility(View.GONE);

        //edittext

        ed_nombre = findViewById(R.id.library_add_name);
        ed_direccion = findViewById(R.id.library_add_direccion);
        ed_zipcode = findViewById(R.id.library_add_zipcode);
        ed_imagen = findViewById(R.id.library_add_imagen);
        ed_id = findViewById(R.id.library_id);
        ed_telefono = findViewById(R.id.library_add_telefono);


    }

    private void readBiblioteca(){
        String id = ed_id.getText().toString().trim();
        String URL = "http://192.168.1.96/android/fetch.php?biblioteca_id=" + id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String nombre, imagen, address, zipcode, telefono;
                        try {
                            nombre = response.getString("biblioteca_name");
                            imagen = response.getString("biblioteca_image");
                            address = response.getString("address");
                            zipcode = response.getString("address_postalcode");
                            telefono = response.getString("telefono");

                            ed_nombre.setText(nombre);
                            ed_direccion.setText(address);
                            ed_zipcode.setText(zipcode);
                            ed_imagen.setText(imagen);
                            ed_telefono.setText(telefono);
                            btn_delete.setVisibility(View.VISIBLE);
                            btn_actualizar.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


    public void Fetch(View view){
        readBiblioteca();


    }

    public void Delete(View view){
        String id = ed_id.getText().toString().trim();
        removeLibrary(id);
    }

    private void removeLibrary(String idlibrary){

        String URL = "http://192.168.1.96/android/delete.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("biblioteca_id", idlibrary);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void Actualizar(View view){
        String nombre = ed_nombre.getText().toString().trim();
        String direccion = ed_direccion.getText().toString().trim();
        String imagen = ed_imagen.getText().toString().trim();
        String zipcode = ed_zipcode.getText().toString().trim();
        String id = ed_id.getText().toString().trim();
        String telefono = ed_telefono.getText().toString().trim();

        UpdateLibrary(nombre, direccion, imagen, zipcode, id, telefono);


    }

    private void UpdateLibrary(String nombre, String direccion, String imagen, String zipcode, String id, String telefono){
        String URL = "http://192.168.1.96/android/update.php";
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(FetchLibrary.this, "Se actualizo la biblioteca correctamente", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("biblioteca_id", id);
                params.put("biblioteca_name", nombre);
                params.put("biblioteca_image", imagen);
                params.put("address", direccion);
                params.put("address_postalcode", zipcode);
                params.put("telefono", telefono);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



    public void Volver(View v){
        finish();
    }




}