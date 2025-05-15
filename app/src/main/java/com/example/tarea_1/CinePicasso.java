package com.example.tarea_1;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CinePicasso extends AppCompatActivity {

    String url_sala_1;
    String url_sala_2;
    String url_sala_3;
    EditText edt_sala_1;
    ImageView iv_sala_1;
    EditText edt_sala_2;
    ImageView iv_sala_2;
    EditText edt_sala_3;
    ImageView iv_sala_3;
    ImageView[] salas;
    String[] urls;
    EditText[] edts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cine_picasso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edt_sala_1 = findViewById(R.id.cp_edt_sala_1);
        iv_sala_1 = findViewById(R.id.cp_iv_sala_1);
        iv_sala_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_sala(0);
            }
        });
        edt_sala_2 = findViewById(R.id.cp_edt_sala_2);
        iv_sala_2 = findViewById(R.id.cp_iv_sala_2);
        iv_sala_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_sala(1);
            }
        });
        edt_sala_3 = findViewById(R.id.cp_edt_sala_3);
        iv_sala_3 = findViewById(R.id.cp_iv_sala_3);
        iv_sala_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_sala(2);
            }
        });
        urls = new String[]{url_sala_1, url_sala_2, url_sala_3};
        salas = new ImageView[]{iv_sala_1, iv_sala_2, iv_sala_3};
        edts = new EditText[]{edt_sala_1, edt_sala_2, edt_sala_3};
        update_sala(0);
        update_sala(1);
        update_sala(2);
    }

    String check_url(String url){
        String default_image_urrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/1200px-Google_2015_logo.svg.png";
        return (url.isEmpty()?default_image_urrl:url);
    }

    void update_sala(int sala){
        urls[sala] = check_url(edts[sala].getText().toString());
        Toast.makeText(this, urls[sala], Toast.LENGTH_SHORT).show();
        edts[sala].setText("");
        Picasso.get()
                .load(urls[sala])
                .resize(700,500)
                //.centerCrop()
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(salas[sala]);
    }
}