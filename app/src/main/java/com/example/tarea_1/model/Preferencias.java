package com.example.tarea_1.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Preferencias implements Serializable {

    String[] hobbies;
    String[] generos_musicales = {"","",""};
    public Preferencias(String[] hobbies, String[] generos_musicales){
        this.hobbies = hobbies;
        this.generos_musicales = generos_musicales;
    }

    public String[] getHobbies(){
        return hobbies;
    }
    public void setHobbies(String[] hobbies){
        this.hobbies = hobbies;
    }
    public String getHobbie(int position){
        if (position>=hobbies.length){
            return "not found";
        }else{
            return hobbies[position];
        }
    }
    public String[] getGeneros_musicales(){
        return generos_musicales;
    }
    public void setGeneros_musicales(String[] generosMusicales){
        this.generos_musicales = generosMusicales;
    }
}
