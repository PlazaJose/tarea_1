package com.example.tarea_1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.AdministradorSQLiteOpenHelper;
import com.example.tarea_1.model.Lista_usuarios;
import com.example.tarea_1.model.Usuario;

public class formulario_menu extends AppCompatActivity {

    EditText edt_id;
    Lista_usuarios lista_usuarios;
    AdministradorSQLiteOpenHelper admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt_id = findViewById(R.id.edt_busqueda_id_formulario);
        //lista_usuarios = (Lista_usuarios) getIntent().getSerializableExtra("lista_usuarios");

        //if(lista_usuarios !=null)Toast.makeText(this, "tamaño lista"+lista_usuarios.getUsuarios().size(), Toast.LENGTH_SHORT).show();

        admin = new AdministradorSQLiteOpenHelper(this, "agenda", null, 1);
        lista_usuarios = admin.consultar_lista_usuarios();
    }

    public void crear_usuario(View v){
        Intent intento = new Intent(this, Formulario_edicion.class);
        startActivityForResult(intento, 2);
    }

    public void buscar_usuario(View v){
        if(edt_id.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese un id", Toast.LENGTH_SHORT).show();
        }else{
            Usuario usuario = lista_usuarios.get_by_id(Integer.parseInt(edt_id.getText().toString()));
            Intent intento = new Intent(this, Formulario_edicion.class);
            intento.putExtra("usuario", usuario);
            startActivityForResult(intento, 3);
        }
    }

    public void salir(View v){
        guardar();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("lista_usuarios", lista_usuarios);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void guardar(){
        for (Usuario usuario:lista_usuarios.getUsuarios()) {
            if(usuario.getId()!=-1)admin.guardar_usuario(usuario);
        }
    }

    public void ver_lista_usuarios(View v){
        if(lista_usuarios!=null){
            Intent intent = new Intent(this, Lista_usuarios_view.class);
             intent.putExtra("lista_usuarios", lista_usuarios);
             startActivity(intent);
        }else {
            Toast.makeText(this, "No se han añadido usuarios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Usuario nuevo = (Usuario) data.getSerializableExtra("usuario_nuevo");
            lista_usuarios.add_user(nuevo);
            Toast.makeText(this, "Guardado: "+nuevo.getId()+":"+nuevo.getNombre(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "fallo el guardado", Toast.LENGTH_SHORT).show();
        }
    }
}