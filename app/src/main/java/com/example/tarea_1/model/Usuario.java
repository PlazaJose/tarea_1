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
    private Preferencias preferencias;

    int id = -1;
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
        if(informacion_academica != null){
            return informacion_academica;
        }
        return new Informacion_academica("nn", "nn", -1, -1, "nn");
    }

    public void setInformacion_academica(Informacion_academica informacion_academica){
        this.informacion_academica = informacion_academica;
    }

    public Preferencias getPreferencias(){
        if(preferencias!=null)return preferencias;

        String[] hobiies = {"not defined"};
        String[] gustos_musicales = {"not defined", "not defined", "not defined"};
        return new Preferencias(hobiies, gustos_musicales);
    }

    public void setPreferencias(Preferencias preferencias){
        this.preferencias = preferencias;
    }

    public String userToString(){
        return "Nombre: " + nombre + " " + apellidos + "\n" +
                "Edad: " + edad + "\n" +
                "Email: " + email + "\n" +
                "Telefono: " + telefono + "\n" +
                "Address: " + addres + "\n" +
                "Documento de Identidad: " + documento_identidad + "\n" +
                "Sexo: " + sexo + "\n" +
                "Estado Civil: " + estado_civil + "\n" +
                "Informacion Academica: " + getInformacion_academica().informacionAcademicaToString() + "\n" +
                "Preferencias: " + getPreferencias().preferenciasToString()+
                '}';
    }

    public int getId() {
        return id;
    }
}
