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

    public String preferenciasToString(){
        // Convert hobbies array to a string (if not null or empty)
        String hobbiesString = (hobbies != null && hobbies.length > 0) ? String.join(", ", hobbies) : "No hobbies listed";

        // Convert generos_musicales array to a string (if not empty)
        String generosMusicalesString = (generos_musicales != null && generos_musicales.length > 0) ?
                String.join(", ", generos_musicales) : "No music genres listed";

        return "Hobbies: " + hobbiesString + "\n" +
                "Generos Musicales: " + generosMusicalesString;
    }
}
