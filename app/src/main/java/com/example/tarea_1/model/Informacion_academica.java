package com.example.tarea_1.model;

import android.content.ContentValues;

import java.io.Serializable;

public class Informacion_academica implements Serializable {

    private String institucion;
    private String carrera;
    private int ano_inicio;
    private int ano_finalizacion;
    private String grado_obtenido;
    //ContentValues contentValues;
    public  Informacion_academica(String institucion, String carrera, int ano_inicio, int ano_finalizacion, String grado_obtenido){
        this.institucion = institucion;
        this.carrera = carrera;
        this.ano_inicio = ano_inicio;
        this.ano_finalizacion = ano_finalizacion;
        this.grado_obtenido = grado_obtenido;
        build_content();
    }
    private ContentValues build_content(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("institucion", institucion);
        contentValues.put("carrera", carrera);
        contentValues.put("ano_inicio", ano_inicio);
        contentValues.put("ano_finalizacion", ano_finalizacion);
        contentValues.put("grado_obtenido", grado_obtenido);
        return contentValues;
    }

    public ContentValues getContentValues() {
        return build_content();
    }

    public String getInstitucion(){
        return institucion;
    }
    public String getCarrera(){
        return carrera;
    }

    public int getAno_inicio() {
        return ano_inicio;
    }

    public int getAno_finalizacion() {
        return ano_finalizacion;
    }

    public String getGrado_obtenido() {
        return grado_obtenido;
    }

    public String informacionAcademicaToString(){
        return "Institucion: " + institucion + "\n" +
                "Carrera: " + carrera + "\n" +
                "Año de inicio: " + ano_inicio + "\n" +
                "Año de finalizacion: " + ano_finalizacion + "\n" +
                "Grado obtenido: " + grado_obtenido;
    }
}
