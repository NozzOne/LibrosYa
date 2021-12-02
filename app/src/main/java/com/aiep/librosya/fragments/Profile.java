package com.aiep.librosya.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiep.librosya.admin.AddBook;
import com.aiep.librosya.DBHelper;
import com.aiep.librosya.HomeActivity;
import com.aiep.librosya.MainActivity;
import com.aiep.librosya.R;
import com.aiep.librosya.admin.ManageBiblioteca;
import com.aiep.librosya.admin.ManageBooks;
import com.aiep.librosya.utils.DbUtility;
import com.aiep.librosya.utils.Utils;
import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment implements View.OnClickListener {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    DBHelper conn;
    ImageButton avatar;


    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        DBHelper conn = new DBHelper(getContext(),"LibrosYa",null,1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // rellena el layout
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView nombre = (TextView) view.findViewById(R.id.nombre_usuario);
        TextView email = (TextView) view.findViewById(R.id.correo_txt);
        avatar = (ImageButton) view.findViewById(R.id.profileAvatar);
        conn = new DBHelper(getContext(),"LibrosYa",null,1);

        HomeActivity activity = (HomeActivity) getActivity();
        int user_id =  activity.getUser_id();



        // SE OBTIENEN LOS DATOS DEL USUARIO DESDE LA BASE DE DATOS SQLITE
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre, email, avatar FROM persona WHERE id = "+ user_id, null);

        if (cursor.moveToFirst()){
            nombre.setText(cursor.getString(0));
            email.setText(cursor.getString(1));

            //VERIFICA SI EL BLOB (TIPO DE DATO) EN LA BASE DE DATOS ESTA LLENO O VACIO

            if (cursor.getBlob(2) == null){
                //SI ESTA VACIO RELLENA LA IMAGEN AVATAR CON UNA IMAGEN GENERICA
                String URL = "https://simulacionymedicina.es/wp-content/uploads/2015/11/default-avatar-300x300-1.jpg";
                Glide.with(view).load(URL).placeholder(null).into(avatar);
            }else{
                //SI ESTA LLENO LO RELLENA CON LA IMAGORMACION DE LA IMAGEN
                byte[] image = cursor.getBlob(2);
                avatar.setImageBitmap(DbUtility.getImage(image));
            }
        }else {
            nombre.setText("No se puedo cargar");
            email.setText("No se puedo cargar");
        }

        //SE ASIGNAN BOTONES
        Button agregarLibro = (Button) view.findViewById(R.id.option_libros);
        agregarLibro.setOnClickListener((View.OnClickListener) this);
        Button agregarCategoria = (Button) view.findViewById(R.id.option_bibliotecas);
        agregarCategoria.setOnClickListener((View.OnClickListener) this);
        Button cerrar = (Button) view.findViewById(R.id.cerrar_sesion);
        cerrar.setOnClickListener((View.OnClickListener) this);

        //SE VERIFICA NIVEL DE USUARIO
        Cursor cursor1 = db.rawQuery("SELECT 1 FROM persona WHERE persona.id = "+ user_id +" AND persona.id = 1", null);
        cursor1.moveToFirst();

        if (cursor1.getCount() != 0){
            // SI ES DISTINTO DE 0 SE MUESTRAN BOTONES DE ADMINISTRACION
            agregarLibro.setVisibility(view.VISIBLE);
            agregarCategoria.setVisibility(view.VISIBLE);

        }else {
            //SI ES 0 SE OCULTAN
            agregarLibro.setVisibility(view.GONE);
            agregarCategoria.setVisibility(view.GONE);

        }
        //SI SE DA CLICK EN LA IMAGEN (EN EL ELEMENTO IMAGEBUTTON) CARGAN LAS OPCIONES PARA CAMBIAR IMAGENES
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargarImagen();
            }
        });

        return view;
    }


    @Override
    public void onClick(View v){
        if (v.getId() == R.id.cerrar_sesion){
            Intent salir = new Intent(v.getContext(), MainActivity.class);
            startActivity( salir);
            getActivity().finish();
        } else if (v.getId() == R.id.option_libros){
            Intent manageBooks = new Intent(v.getContext(), ManageBooks.class);
            startActivity(manageBooks);
        }else if (v.getId() == R.id.option_bibliotecas){
            Intent manageBibliotecas = new Intent(v.getContext(), ManageBiblioteca.class);
            startActivity(manageBibliotecas);
        }
    }

    //MENU QUE SE DESPLIEGA PARA CAMBIAR LA IMAGEN
    private void CargarImagen(){

        //SE DECLARAN OPCIONES
        final CharSequence[] opciones = {"Camara", "Cancelar"};

        // SE CREA EL ELEMENTO ALERDIALOG
        final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(getContext());
        alertOpciones.setTitle("Cambiar Foto de perfil");
        //SE ESPERA A QUE EL USUARIO ELIGA UNA DE LAS OPCIONES MOSTRADAS

        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (opciones[which].equals("Camara")){
                    takePicture();
                } else {
                    dialog.dismiss();
                }

            }
        });

        // SE MUESTRAN OPCIONES
        alertOpciones.show();

    }

    //SE INICIO LA CAMARA PARA TOMAR LA FOTO
    //ESTO INICIA UN INTENT
    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Fragment frag = this;
        frag.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }




    //SE ACTIVA CUANDO LA SE TERMINA LA ACTIVIDAD DE LA CAMARA
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {

                //colocar Imagen de perfil en aplicacion

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                avatar.setImageBitmap(photo);


                //se guarda en la base de datos

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] img = bos.toByteArray();

                conn=new DBHelper(getContext(),"LibrosYa",null,1);
                HomeActivity activity = (HomeActivity) getActivity();
                int user_id =  activity.getUser_id();

                SQLiteDatabase db=conn.getWritableDatabase();

                ContentValues values=new ContentValues();
                values.put("avatar", img);

                db.update(Utils.TABLA_PERSONA, values, "id = "+ user_id, null);
                db.close();

            }
        }
    }
}