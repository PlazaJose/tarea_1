package com.example.tarea_1.model;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.ArrayList;

public class Preferencias implements Serializable {

    String[] hobbies;
    String[] generos_musicales;
    //ContentValues contentValues;
    public Preferencias(String[] hobbies, String[] generos_musicales){
        this.hobbies = hobbies;
        this.generos_musicales = new String[]{"","",""};
        for(int i = 0; i < generos_musicales.length; i++){
            if(i<this.generos_musicales.length)this.generos_musicales[i] = generos_musicales[i];
        }
        //this.generos_musicales = generos_musicales;
        build_content();
    }

    private ContentValues build_content(){
        ContentValues contentValues = new ContentValues();
        StringBuilder str_hobbies = new StringBuilder();
        for (String hobby : hobbies) {
            str_hobbies.append(hobby).append(",");
        }
        contentValues.put("hobbies", str_hobbies.toString().substring(0, str_hobbies.toString().length()-1));
        StringBuilder str_generos_musicales = new StringBuilder();
        for (String genero : generos_musicales) {
            str_generos_musicales.append(genero).append(",");
        }
        contentValues.put("generos_musicales", str_generos_musicales.toString().substring(0, str_generos_musicales.toString().length()-1));
        return contentValues;
    }

    public String[] getHobbies(){
        return hobbies;
    }
    public void setHobbies(String[] hobbies){
        this.hobbies = hobbies;
        build_content();
    }
    public String getHobbie(int position){
        if (position>=hobbies.length){
            return "not found";
        }else{
            return hobbies[position];
        }
    }

    public boolean hasHobbie(String key){
        if(!(hobbies != null && hobbies.length > 0))return false;
        for(int i = 0; i<hobbies.length; i++){
            if(hobbies[i].equals(key))return true;
        }
        return false;
    }
    public String[] getGeneros_musicales(){
        return generos_musicales;
    }
    public void setGeneros_musicales(String[] generosMusicales){
        this.generos_musicales = generosMusicales;
        build_content();
    }

    public ContentValues getContentValues() {
        return build_content();
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
