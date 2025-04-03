package com.example.tarea_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.Lista_usuarios;

public class MainActivity extends AppCompatActivity {

    Lista_usuarios lista_usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lista_usuarios = new Lista_usuarios();

    }

    public void formularios(View v){
        Intent intento = new Intent(this, formulario_menu.class);
        intento.putExtra("lista_usuarios", lista_usuarios);
        startActivityForResult(intento, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            Lista_usuarios nueva = (Lista_usuarios) data.getSerializableExtra("lista_usuarios");
            if(nueva != null){
                lista_usuarios = nueva;
                Toast.makeText(this, "lista guardada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "lista null", Toast.LENGTH_SHORT).show();
            }
        }
    }
}