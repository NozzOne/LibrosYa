package com.aiep.librosya.entidades;

import java.io.Serializable;

public class Persona implements Serializable {

    private Integer id, isadmin;
    private String nombre, email, password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public String getNombre(String string) {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail(String string) {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona(Integer id, String nombre, String email, String password, Integer isadmin) {
        this.id = id;
        this.isadmin = isadmin;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Persona(){ }

}
