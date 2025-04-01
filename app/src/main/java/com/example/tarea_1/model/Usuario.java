package com.example.tarea_1.model;
import java.io.Serializable;
public class Usuario implements Serializable {
    String nombre;
    String apellidos;
    int edad;
    String email;
    String telefono;
    String addres;

    String documento_identidad;
    String sexo;//[opciones: Masculino, Femenino, Otro, Prefiero no decirlo]
    String estado_civil;//[opciones: Soltero, Casado, Otro]

    private Informacion_academica informacion_academica;

    int id;
    public Usuario(String nombre, String apellido, int edad, String email, String telefono, String addres, String sexo, String documento_identidad, String estado_civil, int id){
        this.nombre = nombre;
        this.apellidos = apellido;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.addres = addres;
        this.sexo = sexo;
        this.estado_civil = estado_civil;
        this.documento_identidad = documento_identidad;
        this.id = id;
    }
    public Usuario(String nombre, String apellido, int edad, String email, String telefono, String addres, String sexo, String documento_identidad, String estado_civil){
        this.nombre = nombre;
        this.apellidos = apellido;
        this.edad = edad;
        this.email = email;
        this.telefono = telefono;
        this.addres = addres;
        this.sexo = sexo;
        this.estado_civil = estado_civil;
        this.documento_identidad = documento_identidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public String getAddres() {
        return addres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDocumento_identidad(){
        return documento_identidad;
    }

    public String getSexo(){
        return sexo;
    }

    public String getEstado_civil(){
        return estado_civil;
    }

    public Informacion_academica getInformacion_academica(){
        return informacion_academica;
    }

    public void setInformacion_academica(Informacion_academica informacion_academica){
        this.informacion_academica = informacion_academica;
    }

    @Override
    public String toString(){
        return "Usuario<"+id+">{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + email + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
