package com.aiep.librosya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import com.aiep.librosya.adapters.BibliotecaAdapter;
import com.aiep.librosya.adapters.LibraryAdapter;
import com.aiep.librosya.entidades.Biblioteca;
import com.aiep.librosya.entidades.Libro;
import com.aiep.librosya.entidades.PersonaLibros;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SelecionBiblioteca extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Biblioteca> bibliotecas;
    private static String json_data = "http://192.168.1.96/android/fetchall.php";
    int user_id;
    int libro_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion_biblioteca);

        recyclerView = findViewById(R.id.bibliotecas_recycler);
        bibliotecas = new ArrayList<>();

        ExtractBiblioteca();

        Bundle extras = getIntent().getExtras();
        user_id = extras.getInt("user_id");
        libro_id = extras.getInt("libro_id");

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        BibliotecaAdapter bibliotecaAdapter = new BibliotecaAdapter(bibliotecas, getLayoutInflater(), this, new BibliotecaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Biblioteca item) {
                MoveToBook(item);

            }
        });
        recyclerView.setAdapter(bibliotecaAdapter);


    }

    public void MoveToBook(Biblioteca item){
        Intent intent = new Intent(this, BibliotecaMasInfo.class);
        intent.putExtra("Biblioteca", item);
        startActivity(intent);
    }

    private void ExtractBiblioteca() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, json_data, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject dataObjetc = response.getJSONObject(i);

                        Biblioteca biblioteca = new Biblioteca();
                        biblioteca.setId(dataObjetc.getInt("biblioteca_id"));
                        biblioteca.setNombre(dataObjetc.getString("biblioteca_name").toString());
                        biblioteca.setImagen(dataObjetc.getString("biblioteca_image").toString());
                        biblioteca.setDireccion(dataObjetc.getString("address").toString());
                        biblioteca.setZipcode(dataObjetc.getString("address_postalcode").toString());
                        biblioteca.setTelefono(dataObjetc.getString("telefono").toString());
                        bibliotecas.add(biblioteca);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }



}