package com.example.tarea_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.Informacion_academica;
import com.example.tarea_1.model.Preferencias;
import com.example.tarea_1.model.Usuario;

import java.util.ArrayList;

public class Formulario_edicion extends AppCompatActivity {

    Spinner spn_tipo_doc;
    String[] as_tipo_doc = {"","CC", "TI", "CE", "Pasaporte", "PEP"};
    Spinner spn_estado_civil;
    String[] as_estado_civil = {"","Soltero", "Casado", "Unión libre", "Separado", "Divorciado", "Viudo"};
    Spinner spn_generos_m1;
    Spinner spn_generos_m2;
    Spinner spn_generos_m3;
    String[] as_generos_musicales = {
            "","Rock", "Rock Indie", "Hard Rock", "Post-Hardcore", "Metal", "Metalcore", "Progresive Rock", "Rock pop", "Psych Rock",
            "Pop", "Reggaetón", "Latin Pop", "Pop Indie", "Trap", "Folk", "Reggae", "Dub", "Chillout", "Grime",
            "Salsa", "Son cubano", "Guaracha", "Timba", "Salsa romántica", "Rumba", "Cha-cha-cha", "Mambo",
            "Ballenato", "Cumbia", "Electro Cumbia", "Bachata",
            "Popular", "tango", "Milonga", "Bolero", "Flamenco",
            "Ponk", "Hip hop", "Electrónica",  "House", "Techno", "Trance",
            "JAZZ", "Swing", "Bebop", "Soul", "Latin Jazz", "Modal Jazz", "Ska", "Soul Jazz",
            "JAZZ Fusion", "R&B", "Blues", "Country", "Funk", "Post-Punk", "Bossa Nova",
            "Corridos", "Mariachi", "Tropical", "Norteña",
            "kpop", "Anime music", "CityPop", "J-Pop",
            "Música clásica", "Música de Videojuegos", "Música de películas", "Ambient",
            "Celtic", "Gothic Rock", "New Wave", "Celtic Rock",
            "Kizomba",        // Estilo musical originario de Angola, mezcla de semba y música latina con un ritmo lento y sensual.
    };
    EditText edt_nombre;
    EditText edt_apellido;
    EditText edt_edad;
    EditText edt_correo;
    EditText edt_telefono;
    EditText edt_direccion;
    EditText edt_identificacion;
    RadioButton rb_sexo_hombre;
    RadioButton rb_sexo_mujer;
    RadioButton rb_sexo_otro;
    RadioButton rb_sexo_nod;
    String[] sexos = {"hombre", "mujer", "otro", "prefiero no decir"};
    EditText edt_institucion;
    EditText edt_carrera;
    EditText edt_ano_inicio;
    EditText edt_ano_fin;
    EditText edt_grado;
    CheckBox cb_h_peliculas;
    CheckBox cb_h_videojuegos;
    CheckBox cb_h_deportes;
    CheckBox cb_h_baile;
    String[] hobies = {"PELICULAS", "VIDEOJUEGOS", "DEPORTES", "BAILE"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_edicion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spn_tipo_doc = findViewById(R.id.spin_tipo_doc);
        ArrayAdapter<String> ad_tipo_doc = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, as_tipo_doc);
        spn_tipo_doc.setAdapter(ad_tipo_doc);
        spn_estado_civil = findViewById(R.id.spin_estado_civil);
        ArrayAdapter<String> ad_estado_civil = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, as_estado_civil);
        spn_estado_civil.setAdapter(ad_estado_civil);


        spn_generos_m1 = findViewById(R.id.spn_generos_m1);
        ArrayAdapter<String> ad_generos_musicales = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, as_generos_musicales);
        spn_generos_m1.setAdapter(ad_generos_musicales);
        spn_generos_m2 = findViewById(R.id.spn_generos_m2);
        spn_generos_m2.setAdapter(ad_generos_musicales);
        spn_generos_m3 = findViewById(R.id.spn_generos_m3);
        spn_generos_m3.setAdapter(ad_generos_musicales);

        edt_nombre = findViewById(R.id.edt_nombre);
        edt_apellido = findViewById(R.id.edt_apellido);
        edt_edad = findViewById(R.id.edt_edad);
        edt_correo = findViewById(R.id.edt_email);
        edt_telefono = findViewById(R.id.edt_telefono);
        edt_direccion = findViewById(R.id.edt_direccion);
        edt_identificacion = findViewById(R.id.edt_identificacion);
        rb_sexo_hombre = findViewById(R.id.rb_hombre);
        rb_sexo_mujer = findViewById(R.id.rb_mujer);
        rb_sexo_otro = findViewById(R.id.rb_otro);
        rb_sexo_nod = findViewById(R.id.rb_no_decir);
        edt_institucion = findViewById(R.id.edt_institucion);
        edt_carrera = findViewById(R.id.edt_carrera);
        edt_ano_inicio = findViewById(R.id.edt_ano_inicio);
        edt_ano_fin = findViewById(R.id.edt_ano_fin);
        edt_grado = findViewById(R.id.edt_grado);
        cb_h_peliculas = findViewById(R.id.cb_peliculas);
        cb_h_videojuegos = findViewById(R.id.cb_videojuegos);
        cb_h_deportes = findViewById(R.id.cb_deportes);
        cb_h_baile = findViewById(R.id.cb_baile);

        Intent intent = getIntent();
        Usuario user = (Usuario) intent.getSerializableExtra("usuario");

        // Display the user's details
        if (user != null) {
            set_usuario(user);
        }
    }

    public void guardar(View v){
        String sexo = sexos[3];
        if(rb_sexo_hombre.isChecked())sexo = sexos[0];
        if(rb_sexo_mujer.isChecked())sexo = sexos[1];
        if(rb_sexo_otro.isChecked())sexo = sexos[2];
        Usuario usuario = new Usuario(
                edt_nombre.getText().toString(),
                edt_apellido.getText().toString(),
                Integer.parseInt(edt_edad.getText().toString()),
                edt_correo.getText().toString(),
                edt_telefono.getText().toString(),
                edt_direccion.getText().toString(),
                sexo,
                spn_tipo_doc.getSelectedItem().toString()+"."+edt_identificacion.getText().toString(),
                spn_estado_civil.getSelectedItem().toString()
        );
        Informacion_academica informacion_academica = new Informacion_academica(
                edt_institucion.getText().toString(),
                edt_carrera.getText().toString(),
                Integer.parseInt(edt_ano_inicio.getText().toString()),
                Integer.parseInt(edt_ano_fin.getText().toString()),
                edt_grado.getText().toString()
        );
        usuario.setInformacion_academica(informacion_academica);
        ArrayList<String> al_hobbies = new ArrayList<String>();
        if(cb_h_peliculas.isChecked())al_hobbies.add(hobies[0]);
        if(cb_h_videojuegos.isChecked())al_hobbies.add(hobies[1]);
        if(cb_h_deportes.isChecked())al_hobbies.add(hobies[2]);
        if(cb_h_baile.isChecked())al_hobbies.add(hobies[3]);
        String[] hobbies = (al_hobbies.toArray(new String[0]));
        String[] generos_musicales= {
                spn_generos_m1.getSelectedItem().toString(),
                spn_generos_m2.getSelectedItem().toString(),
                spn_generos_m3.getSelectedItem().toString()
        };
        Preferencias preferencias = new Preferencias(hobbies, generos_musicales);
        usuario.setPreferencias(preferencias);
        //Toast.makeText(this, usuario.userToString(), Toast.LENGTH_SHORT).show();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("usuario_nuevo", usuario);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void set_usuario(Usuario usuario){
        edt_nombre.setText(usuario.getNombre());
        edt_apellido.setText(usuario.getApellidos());
        edt_edad.setText(usuario.getEdad());
        edt_correo.setText(usuario.getEmail());
        edt_telefono.setText(usuario.getTelefono());
        edt_direccion.setText(usuario.getAddres());
        edt_identificacion.setText(usuario.getDocumento_identidad());
        rb_sexo_hombre.setChecked(usuario.getSexo().equals(sexos[0]));
        rb_sexo_mujer.setChecked(usuario.getSexo().equals(sexos[1]));
        rb_sexo_otro.setChecked(usuario.getSexo().equals(sexos[2]));
        rb_sexo_nod.setChecked(usuario.getSexo().equals(sexos[3]));
        edt_institucion.setText(usuario.getInformacion_academica().getInstitucion());
        edt_carrera.setText(usuario.getInformacion_academica().getCarrera());
        edt_ano_inicio.setText(""+(usuario.getInformacion_academica().getAno_inicio()));
        edt_ano_fin.setText(""+(usuario.getInformacion_academica().getAno_finalizacion()));
        edt_grado.setText(usuario.getInformacion_academica().getGrado_obtenido());
        cb_h_peliculas.setChecked(usuario.getPreferencias().hasHobbie(hobies[0]));
        cb_h_videojuegos.setChecked(usuario.getPreferencias().hasHobbie(hobies[1]));
        cb_h_deportes.setChecked(usuario.getPreferencias().hasHobbie(hobies[2]));
        cb_h_baile.setChecked(usuario.getPreferencias().hasHobbie(hobies[3]));

        int position = ((ArrayAdapter<String>)spn_tipo_doc.getAdapter()).getPosition(usuario.getDocumento_identidad().split("\\.")[0]);
        if (position >= 0) {
            spn_tipo_doc.setSelection(position);
        }
        position = ((ArrayAdapter<String>)spn_estado_civil.getAdapter()).getPosition(usuario.getEstado_civil());
        if (position >= 0) {
            spn_estado_civil.setSelection(position);
        }
        position = ((ArrayAdapter<String>)spn_generos_m1.getAdapter()).getPosition(usuario.getPreferencias().getGeneros_musicales()[0]);
        if (position >= 0) {
            spn_generos_m1.setSelection(position);
        }
        position = ((ArrayAdapter<String>)spn_generos_m2.getAdapter()).getPosition(usuario.getPreferencias().getGeneros_musicales()[1]);
        if (position >= 0) {
            spn_generos_m2.setSelection(position);
        }
        position = ((ArrayAdapter<String>)spn_generos_m3.getAdapter()).getPosition(usuario.getPreferencias().getGeneros_musicales()[2]);
        if (position >= 0) {
            spn_generos_m3.setSelection(position);
        }
    }
}