package com.example.tarea_1;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.Lista_usuarios;
import com.example.tarea_1.model.Usuario;
import com.example.tarea_1.view.Usuario_view;

public class Lista_usuarios_view extends AppCompatActivity {
    Lista_usuarios lista_usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_usuarios_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lista_usuarios = (Lista_usuarios) getIntent().getSerializableExtra("lista_usuarios");

        if(lista_usuarios !=null) {
            Toast.makeText(this, "tamaño lista" + lista_usuarios.getUsuarios().size(), Toast.LENGTH_SHORT).show();
            set_up_users();
        }
    }

    private void set_up_users(){
        LinearLayout linearLayout = findViewById(R.id.ll_luv_main);

        for (Usuario usuario : lista_usuarios.getUsuarios()) {
            if(usuario.getId()!=-1){
                Usuario_view usuario_view = new Usuario_view(this);
                usuario_view.setUsuario(usuario);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(usuario_view, params);
            }
        }
    }
}