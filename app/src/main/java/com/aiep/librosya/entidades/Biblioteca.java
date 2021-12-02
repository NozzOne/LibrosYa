package com.aiep.librosya.entidades;

import java.io.Serializable;

public class Biblioteca implements Serializable {
    int id;
    String nombre, imagen, direccion, zipcode, telefono;



    public Biblioteca() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Biblioteca(int id, String nombre, String imagen, String direccion, String zipcode, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.direccion = direccion;
        this.zipcode = zipcode;
        this.telefono = telefono;
    }
}
