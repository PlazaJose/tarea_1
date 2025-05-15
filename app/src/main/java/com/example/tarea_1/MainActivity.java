package com.example.tarea_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.Lista_usuarios;
import com.example.tarea_1.model.Usuario;

public class MainActivity extends AppCompatActivity {

    Lista_usuarios lista_usuarios;
    String usuario = "invitado";
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

    public void inicio_sesion(View v){
        Intent intento = new Intent(this, Inicio_sesion.class);
        startActivityForResult(intento, 10);
    }

    public void formularios(View v){
        Intent intento = new Intent(this, formulario_menu.class);
        intento.putExtra("lista_usuarios", lista_usuarios);
        startActivityForResult(intento, 1);
    }

    public void calculadora(View v){
        Intent intento = new Intent(this, Calculadora.class);
        startActivity(intento);
    }
    public void cine(View v){
        Intent intent = new Intent(this, CinePicasso.class);
        startActivity(intent);
    }

    public void exit(View v){
        System.exit(0);
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
            String usuario = data.getStringExtra("usuario");
            if (usuario != null) {
                if(!usuario.isEmpty()){
                    this.usuario = usuario;
                    TextView tv_title = findViewById(R.id.mv_tv_title);
                    tv_title.setText(String.format("BIENVENIDO %s", usuario));
                }
            }
        }
    }
}