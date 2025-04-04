package com.example.tarea_1.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Lista_usuarios implements Serializable {
    ArrayList<Usuario> usuarios;
    int id_counter = -1;
    public Lista_usuarios(){
        usuarios = new ArrayList<Usuario>();
        this.add_user(new Usuario("not found", "not found", 0,"not found", "not found", "not found", "not found", "not found", "not found"));
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
        return usuarios;
    }
}
