package com.example.tarea_1.model;

import java.io.Serializable;

public class Informacion_academica implements Serializable {

    private String institucion;
    private String carrera;
    private int ano_inicio;
    private int ano_finalizacion;
    private String grado_obtenido;
    public  Informacion_academica(String institucion, String carrera, int ano_inicio, int ano_finalizacion, String grado_obtenido){
        this.institucion = institucion;
        this.carrera = carrera;
        this.ano_inicio = ano_inicio;
        this.ano_finalizacion = ano_finalizacion;
        this.grado_obtenido = grado_obtenido;
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
}
