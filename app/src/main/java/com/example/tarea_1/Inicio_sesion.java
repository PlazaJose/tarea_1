package com.example.tarea_1;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.AdministradorSQLiteOpenHelper;
import com.example.tarea_1.model.Lista_usuarios;
import com.example.tarea_1.model.Usuario;

import java.util.Objects;

public class Inicio_sesion extends AppCompatActivity {

    String str_usuario = "";
    EditText edt_usuario;
    EditText edt_password;
    AdministradorSQLiteOpenHelper admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio_sesion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt_usuario = findViewById(R.id.is_edt_usuario);
        edt_password = findViewById(R.id.is_edt_password);
        admin = new AdministradorSQLiteOpenHelper(this, "agenda", null, 1);
    }

    public void sign_in(View v){
        String usuario_name = edt_usuario.getText().toString();
        String password = edt_password.getText().toString();
        if(!isNumeric(password)){
            wrong_arguments();
            return;
        }
        int id = Integer.parseInt(password);
        Usuario usuario = admin.consultar_usuario(id, usuario_name);
        if(!usuario.getNombre().equals("not foud")){
            str_usuario = password;
            inisio_sesion();
        }else{
            wrong_arguments();
        }
    }
    private void inisio_sesion(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra("usuario", str_usuario);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
    private void wrong_arguments(){
        edt_usuario.setBackgroundColor(Color.RED);
        edt_password.setBackgroundColor(Color.RED);
        Toast.makeText(this, "usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
    }
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}