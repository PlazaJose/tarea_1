package com.example.tarea_1.model;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista_usuarios implements Serializable {
    ArrayList<Usuario> usuarios;
    int id_counter = -1;
    public Lista_usuarios(){
        usuarios = new ArrayList<Usuario>();
        this.add_user(new Usuario("not found", "not found", 0,"not found", "not found", "not found", "not found", "not found", "not found"));
    }

    public void add_user_nf(Usuario nuevo){
        this.usuarios.add(nuevo);
        /*if(this.usuarios.stream().anyMatch(usuario -> usuario.getId() == nuevo.getId())){
            modify_user(nuevo);
        }else {
            this.usuarios.add(nuevo);
        }*/
    }
    public void add_user(Usuario nuevo){
        if(nuevo.getId() == -1){
            nuevo.setId(this.id_counter);
            this.usuarios.add(nuevo);
            this.id_counter++;
        }else {
            this.modify_user(nuevo);
        }
    }

    public void load_user(Usuario usuario){
        if(usuario.getId()!=-1){
            this.usuarios.add(usuario);
            if(this.id_counter<=usuario.getId()){
                this.id_counter = usuario.getId()+1;
            }
        }
    }

    public void modify_user(Usuario nuevo){
        Usuario old = get_by_id(nuevo.id);
        if(old.id != -1){
            for (int i = 0; i<this.usuarios.size(); i++){
                if(this.usuarios.get(i).getId() == nuevo.getId()){
                    this.usuarios.set(i, nuevo);
                }
            }
        }
    }
    public Usuario get_by_id(int id){
        for (int i = 0; i<this.usuarios.size(); i++){
            if(this.usuarios.get(i).getId() == id){
                return this.usuarios.get(i);
            }
        }
        return this.usuarios.get(0);
    }

    public ArrayList<Usuario> getUsuarios() {
        return this.usuarios;
    }
}
