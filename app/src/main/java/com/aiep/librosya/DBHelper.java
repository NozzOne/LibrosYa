package com.aiep.librosya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.aiep.librosya.utils.Utils;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utils.CREAR_TABLA_PERSONA);
        db.execSQL(Utils.CREAR_TABLA_LIBROS);
        db.execSQL(Utils.CREAR_TABLA_PERSONALIBROS);
        db.execSQL(Utils.CREAR_TABLA_CATEGORIAS);
        db.execSQL(Utils.CREAR_TABLA_LIBROCATEGORIA);

        //Insertando registros
        //registros de usuarios
        db.execSQL(Utils.INSERTAR_ADMIN);
        db.execSQL(Utils.INSERTAR_USUARIO);

        //registros de libro
        db.execSQL(Utils.INSERTAR_LIBRO1);
        db.execSQL(Utils.INSERTAR_LIBRO2);


        //registro de personality
        db.execSQL(Utils.INSERTAR_PERSONALIBRO1);


        //registro categoria
        db.execSQL(Utils.INSERT_CATEGORIA1);
        db.execSQL(Utils.INSERT_CATEGORIA2);
        db.execSQL(Utils.INSERT_CATEGORIA3);
        db.execSQL(Utils.INSERT_CATEGORIA4);
        db.execSQL(Utils.INSERT_CATEGORIA5);
        db.execSQL(Utils.INSERT_CATEGORIA6);
        db.execSQL(Utils.INSERT_CATEGORIA7);
        db.execSQL(Utils.INSERT_CATEGORIA8);
        db.execSQL(Utils.INSERT_CATEGORIA9);
        db.execSQL(Utils.INSERT_CATEGORIA10);
        db.execSQL(Utils.INSERT_CATEGORIA11);
        db.execSQL(Utils.INSERT_CATEGORIA12);
        db.execSQL(Utils.INSERT_CATEGORIA13);
        db.execSQL(Utils.INSERT_CATEGORIA14);
        db.execSQL(Utils.INSERT_CATEGORIA15);
        db.execSQL(Utils.INSERT_CATEGORIA16);
        db.execSQL(Utils.INSERT_CATEGORIA18);
        db.execSQL(Utils.INSERT_CATEGORIA17);
        db.execSQL(Utils.INSERT_CATEGORIA19);
        db.execSQL(Utils.INSERT_CATEGORIA20);
        db.execSQL(Utils.INSERT_CATEGORIA21);

        db.execSQL(Utils.INSERT_LIBROCATEGORIA1);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA2);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA3);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA4);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA5);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA6);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA7);
        db.execSQL(Utils.INSERT_LIBROCATEGORIA8);


        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLA_PERSONA);
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLA_PERSONASLIBROS);
        db.execSQL("DROP TABLE IF EXISTS "+Utils.TABLA_LIBROS);
        onCreate(db);
    }




}
