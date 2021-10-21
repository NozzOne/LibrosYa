package com.aiep.librosya.entidades;

import java.io.Serializable;

public class PersonaLibros implements Serializable {

    int id, paginas, porcentaje;
    String nombre, author, sinopsis, imagen;
    float valoracion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public PersonaLibros(int id, String nombre, String author, String sinopsis, int paginas, float valoracion, String imagen, int porcentaje) {
        this.id = id;
        this.paginas = paginas;
        this.porcentaje = porcentaje;
        this.nombre = nombre;
        this.author = author;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.valoracion = valoracion;
    }


}
