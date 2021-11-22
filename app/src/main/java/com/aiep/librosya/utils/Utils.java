package com.aiep.librosya.utils;

public class Utils {
    //PERONAS p
    public static final String TABLA_PERSONA = "persona";
    public static final String CAMPO_ID_PERSONA = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_PASSWORD = "password";
    public static final String IS_ADMIN = "isadmin";
    public static final String avatar = "avatar";

    public static final String CREAR_TABLA_PERSONA = "CREATE TABLE IF NOT EXISTS " +
            TABLA_PERSONA+"("+CAMPO_ID_PERSONA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_EMAIL +" TEXT, "+CAMPO_NOMBRE+" TEXT, "+ CAMPO_PASSWORD + " TEXT, "+IS_ADMIN+" INTEGER, "+ avatar +" BLOB)";


    //libros l
    public static final String TABLA_LIBROS = "libro";
    public static final String CAMPO_ID_LIBRO = "id";
    public static final String CAMPO_LIBRO_NOMBRE = "nombre";
    public static final String CAMPO_AUTHOR = "author";
    public static final String CAMPO_SINOPSIS = "sinopsis";
    public static final String CAMPO_PAGINAS = "paginas";
    public static final String CAMPO_VALORACION = "valoracion";
    public static final String CAMPO_IMAGEN_URL = "imagen";

    public static final String CREAR_TABLA_LIBROS = "CREATE TABLE IF NOT EXISTS " +
            TABLA_LIBROS+"("+CAMPO_ID_LIBRO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CAMPO_LIBRO_NOMBRE +" TEXT, "+ CAMPO_AUTHOR +" TEXT, "+ CAMPO_SINOPSIS + " TEXT, "+ CAMPO_PAGINAS + " INTEGER, "+CAMPO_VALORACION+" REAL,"+
            CAMPO_IMAGEN_URL+" TEXT )";

    //PersonaLibros pl
    public static final String TABLA_PERSONASLIBROS = "personaslibros";
    public static final String CAMPO_PERSONASLIBROS_ID = "id";
    public static final String CAMPO_IDPERSONA = "idPersona";
    public static final String CAMPO_IDLIBRO = "idLibro";
    public static final String CAMPO_PORCENTAJE = "porcentaje";

    public static final String CREAR_TABLA_PERSONALIBROS = "CREATE TABLE IF NOT EXISTS " +
            TABLA_PERSONASLIBROS+"("+CAMPO_IDPERSONA+" INTEGER, "
            + CAMPO_IDLIBRO +" INTEGER, "+ CAMPO_PORCENTAJE + " INTEGER )";


    //Categorias ct
    public static final String TABLA_CATEGORIA = "categorias";
    public static final String CAMPO_CATEGORIA_ID = "id";
    public static final String CAMPO_CATEGORIA_NOMBRE = "nombre";

    public static final String CREAR_TABLA_CATEGORIAS = "CREATE TABLE IF NOT EXISTS "+
            TABLA_CATEGORIA+ "("+CAMPO_CATEGORIA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_CATEGORIA_NOMBRE+" TEXT UNIQUE)";

    //LibroCategoria

    public static final String TABLA_LIBROCATEGORIA = "librocategoria";
    public static final String CAMPO_LIBROCATEGORIA_NOMBRE = "idCategoria";
    public static final String CAMPO_LIBROCATEGORIA_IDLIBRO = "idLibro";
    public static final String CAMPO_LIBROCATEGORIA_CATEGORIA_NOMBRE = "nombreCategoria";

    public static final String CREAR_TABLA_LIBROCATEGORIA = "CREATE TABLE IF NOT EXISTS "+
            TABLA_LIBROCATEGORIA+ "("+CAMPO_LIBROCATEGORIA_NOMBRE+" INTEGER,"+CAMPO_LIBROCATEGORIA_IDLIBRO+" TEXT,"+
            CAMPO_LIBROCATEGORIA_CATEGORIA_NOMBRE+" TEXT)";


    //INSERT USUARIO ADMIN Y NORMAL

    public static final String INSERTAR_ADMIN = "INSERT INTO persona (email, nombre, password, isadmin) values ('admin@test.com', 'valentin', '123', 1)";
    public static final String INSERTAR_USUARIO = "INSERT INTO persona (email, nombre, password, isadmin) values ('pepe@test.com', 'pepe', '123', 0)";


    // INSERT LIBROS
    public static final String INSERTAR_LIBRO1 = "INSERT INTO libro (nombre, author, sinopsis, paginas, valoracion, imagen) VALUES ('Harry Potter y las Reliquias de la Muerte', 'J. K. Rowling', 'Harry, Ron y Hermione se marchan de Hogwarts para iniciar sumisión más importante: tienen que destruir los horrocruxes el secreto del poder y la inmortalidad de Voldemort, en los que el temido mago oscuro guarda los fragmentos de su alma.', 607, 4.8, 'https://i.imgur.com/dGkvk4O.jpg')";
    public static final String INSERTAR_LIBRO2 = "INSERT INTO libro (nombre, author, sinopsis, paginas, valoracion, imagen) VALUES ('Maze runner', 'James Dashner', 'La historia sigue a Thomas, un adolescente de 16 años que despierta dentro de un elevador en ascenso hasta llegar a un área rodeada por un laberinto. Mientras avanza la historia, irá desentrañando los secretos de porqué él y otros jóvenes fueron introducidos allí.', 398, 4.5, 'https://i.imgur.com/Y0jtZA6.jpg')";

    //INSERT LIBROS PERSONAS
    public static final String INSERTAR_PERSONALIBRO1 = "INSERT INTO personaslibros VALUES (1, 1, 20)";


    //INSERT CATEGORIAs
    public static final String INSERT_CATEGORIA1 = "INSERT INTO categorias (nombre) VALUES ('Novela')";
    public static final String INSERT_CATEGORIA2 = "INSERT INTO categorias (nombre) VALUES ('Thriller')";
    public static final String INSERT_CATEGORIA3 = "INSERT INTO categorias (nombre) VALUES ('Infantil')";
    public static final String INSERT_CATEGORIA4 = "INSERT INTO categorias (nombre) VALUES ('Magia')";
    public static final String INSERT_CATEGORIA5 = "INSERT INTO categorias (nombre) VALUES ('Acción')";
    public static final String INSERT_CATEGORIA6 = "INSERT INTO categorias (nombre) VALUES ('Ficción')";
    public static final String INSERT_CATEGORIA7 = "INSERT INTO categorias (nombre) VALUES ('Misterio')";
    public static final String INSERT_CATEGORIA8 = "INSERT INTO categorias (nombre) VALUES ('Aventuras')";
    public static final String INSERT_CATEGORIA9 = "INSERT INTO categorias (nombre) VALUES ('Hadas')";
    public static final String INSERT_CATEGORIA10 = "INSERT INTO categorias (nombre) VALUES ('Gótica')";
    public static final String INSERT_CATEGORIA11 = "INSERT INTO categorias (nombre) VALUES ('Paranormal')";
    public static final String INSERT_CATEGORIA12 = "INSERT INTO categorias (nombre) VALUES ('Distópica')";
    public static final String INSERT_CATEGORIA13 = "INSERT INTO categorias (nombre) VALUES ('Fantástica')";
    public static final String INSERT_CATEGORIA14 = "INSERT INTO categorias (nombre) VALUES ('Cuento')";
    public static final String INSERT_CATEGORIA15 = "INSERT INTO categorias (nombre) VALUES ('Arte')";
    public static final String INSERT_CATEGORIA16 = "INSERT INTO categorias (nombre) VALUES ('Escolar')";
    public static final String INSERT_CATEGORIA17 = "INSERT INTO categorias (nombre) VALUES ('Romance')";
    public static final String INSERT_CATEGORIA18 = "INSERT INTO categorias (nombre) VALUES ('Musica')";
    public static final String INSERT_CATEGORIA19 = "INSERT INTO categorias (nombre) VALUES ('Puzzle')";
    public static final String INSERT_CATEGORIA20 = "INSERT INTO categorias (nombre) VALUES ('Colorear')";
    public static final String INSERT_CATEGORIA21 = "INSERT INTO categorias (nombre) VALUES ('Humor')";

    //INSERT Libro Categoria
    public static final String INSERT_LIBROCATEGORIA1 = "INSERT INTO librocategoria VALUES (1,1, 'Novela')";
    public static final String INSERT_LIBROCATEGORIA2 = "INSERT INTO librocategoria VALUES (2,1, 'Thriller')";
    public static final String INSERT_LIBROCATEGORIA3 = "INSERT INTO librocategoria VALUES (3,1, 'Infantil')";
    public static final String INSERT_LIBROCATEGORIA4 = "INSERT INTO librocategoria VALUES (4,1, 'Magia')";

    public static final String INSERT_LIBROCATEGORIA5 = "INSERT INTO librocategoria VALUES (5,2, 'Acción')";
    public static final String INSERT_LIBROCATEGORIA6 = "INSERT INTO librocategoria VALUES (6,2, 'Ficción')";
    public static final String INSERT_LIBROCATEGORIA7 = "INSERT INTO librocategoria VALUES (2,2, 'Thriller')";
    public static final String INSERT_LIBROCATEGORIA8 = "INSERT INTO librocategoria VALUES (7,2, 'Misterio')";



}
