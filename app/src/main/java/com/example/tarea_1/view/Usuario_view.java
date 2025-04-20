package com.example.tarea_1.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.tarea_1.model.Usuario;

public class Usuario_view extends TextView {

    Usuario usuario = null;
    public Usuario_view(Context context) {
        super(context);
        set_up(context);
    }
    private void set_up(Context context){
        update();
    }

    private void update(){
        if(usuario == null) {
            return;
        }
        String texto = "id: "+usuario.getId()+" -> "+usuario.getNombre()+" : "+usuario.getDocumento_identidad();
        setText(texto);
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
        update();
    }
    public Usuario getUsuario() {
        return usuario;
    }
}
