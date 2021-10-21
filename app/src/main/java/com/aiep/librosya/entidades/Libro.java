package com.aiep.librosya.entidades;

import java.io.Serializable;

public class Libro implements Serializable {
    private Integer id, paginas;
    private String nombre, author, sinopsis, imagen;
    private float valoracion;

    public Libro(Integer id, String nombre, String author, String sinopsis, Integer paginas,  float valoracion, String imagen) {
        this.id = id;
        this.paginas = paginas;
        this.nombre = nombre;
        this.author = author;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.valoracion = valoracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
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
}
