package com.example.tarea_1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    }
}