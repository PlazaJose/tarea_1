package com.example.tarea_1;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_1.model.TheOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Calculadora extends AppCompatActivity {

    ArrayList<String> operation_list = new ArrayList<>();
    TheOperation theOperation;
    String current_info = "";

    String tier_list[] = {"(", "raiz", "*", "/", "+", "-"};

    //Interface
    EditText edt_operations;

    //Buttons
    Button n1, n2, n3, n4, n5, n6, n7, n8, n9, n0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculadora);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt_operations = findViewById(R.id.edt_operations);
        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);
        n7 = findViewById(R.id.n7);
        n8 = findViewById(R.id.n8);
        n9 = findViewById(R.id.n9);
        n0 = findViewById(R.id.n0);
        theOperation = new TheOperation(operation_list);
        open_saved_history();
        refresh_histoy();
    }

    public void back(View v){
        finish();
    }

    public void add_operation(String nop){
        if(current_info == "")return;
        operation_list.add(current_info);
        current_info = "";
        if(nop.equals(""))return;
        operation_list.add(nop);
        show_operation();
    }

    public void show_operation(){
        Object[] operations = operation_list.toArray();
        String op_complete = "";
        for (int i = 0; i<operations.length; i++){
            op_complete = op_complete + (String) (operations[i]);
        }
        op_complete = op_complete + current_info;
        edt_operations.setText(op_complete);
    }

    public void C_E(View v){
        operation_list.clear();
        current_info = "";
        show_operation();
    }

    public void C(View v){
        if(!current_info.isEmpty()){
            current_info = "";
        }else if (!operation_list.isEmpty()) {
            operation_list.remove(operation_list.size()-1);
            current_info = cplus();
        }
        show_operation();
    }

    private String cplus(){
        if(operation_list.isEmpty())return "";
        String ci = operation_list.get(operation_list.size()-1);
        boolean is_op = false;
        for(int i = 0; i< tier_list.length; i++){
            if(ci.equals(tier_list[i])){
                is_op = true;
            }
        }
        if(!is_op){
            operation_list.remove(operation_list.size()-1);
            return ci;
        }
        return "";
    }

    public void delete(View v){
        if(!current_info.isEmpty()){
            current_info = current_info.substring(0, current_info.length()-1);
        } else {
            C(v);
        }
        show_operation();
    }

    public void add_op(View v){
        if(v.getId() == R.id.n1){
            current_info += "1";
        } else if (v.getId() == R.id.n2) {
            current_info += "2";
        }else if (v.getId() == R.id.n3) {
            current_info += "3";
        }else if (v.getId() == R.id.n4) {
            current_info += "4";
        }else if (v.getId() == R.id.n5) {
            current_info += "5";
        }else if (v.getId() == R.id.n6) {
            current_info += "6";
        }else if (v.getId() == R.id.n7) {
            current_info += "7";
        }else if (v.getId() == R.id.n8) {
            current_info += "8";
        }else if (v.getId() == R.id.n9) {
            current_info += "9";
        }else if (v.getId() == R.id.n0) {
            current_info += "0";
        }else if (v.getId() == R.id.bt_dot){
            current_info += add_dot();
        }else if (v.getId() == R.id.bt_sign){
            current_info = change_sign();
        }else if (v.getId() == R.id.bt_potencia){
            current_info = add_potencia(current_info);
        }else if (v.getId() == R.id.bt_raiz){
            current_info = add_raiz(current_info);
        }
        show_operation();
    }
    public void add_operation(View v){
        if (v.getId() == R.id.b_div){
            add_operation("/");
        } else if (v.getId() == R.id.b_mul) {
            add_operation("*");
        } else if (v.getId() == R.id.b_res) {
            add_operation("-");
        } else if (v.getId() == R.id.b_sum) {
            add_operation("+");
        }
    }

    private String add_dot(){
        if(current_info == null) return "";
        if(current_info.isEmpty()) return "";
        if(current_info.contains(".")) return "";
        return ".";
    }

    private String add_potencia(String n){
        if(current_info == null) return "";
        if(current_info.isEmpty()) return "";
        if(current_info.contains(TheOperation.SIGNO_POTENCIA)) return n;
        if(current_info.contains(TheOperation.SIGNO_RAIZ)) return n.replaceAll("[^0-9.-]", "");
        return n+TheOperation.SIGNO_POTENCIA;
    }
    private String add_raiz(String n){
        if(current_info == null) return "";
        if(current_info.isEmpty()) return "";
        if(current_info.contains(TheOperation.SIGNO_RAIZ)) return n;
        if(current_info.contains("-")) return n;
        if(current_info.contains(TheOperation.SIGNO_POTENCIA)) return n.replaceAll("[^0-9.-]", "");
        return TheOperation.SIGNO_RAIZ+n;
    }

    private String change_sign(){
        if(current_info == null) return current_info;
        if(current_info.isEmpty()) return current_info;
        double current_number = Double.parseDouble(current_info);
        double changed = current_number*-1;
        return ""+changed;
    }

    public void equal(View v){
        add_operation("");
        //theOperation = new TheOperation(operation_list);
        current_info = theOperation.operate_subsequence_r(operation_list).get(0);
        theOperation.save_to_history(operation_list, current_info);
        refresh_histoy();
        operation_list = new ArrayList<>();
        show_operation();
    }

    void refresh_histoy(){
        LinearLayout linearLayout = findViewById(R.id.ll_calc_history);
        if(linearLayout.getChildCount()>1){
            linearLayout.removeViews(1, linearLayout.getChildCount()-1);
        }
        for(int i = TheOperation.HISTORY_LIMIT-1; i>=0; i--){
            if(this.theOperation.history[i]!=null){
                TextView tvh = new TextView(this);
                String h = this.theOperation.getHistory_string(i)+" = "+theOperation.history_results[i];
                tvh.setText(h);
                //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linearLayout.addView(tvh);
            }
        }
        save_history_txt();
    }

    void save_history_txt(){
        StringBuilder history = new StringBuilder();
        for(int i = TheOperation.HISTORY_LIMIT-1; i>=0; i--){
            if(this.theOperation.history[i]!=null){
                TextView tvh = new TextView(this);
                String h = this.theOperation.getHistory_string(i)+"="+theOperation.history_results[i];
                history.append(h);
                history.append(";");
            }
        }
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("history", Context.MODE_PRIVATE));
            file.write(history.toString());
            file.flush();
            file.close();
            Toast.makeText(this, "archivo guardado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void open_saved_history(){
        String nombre = "history";
        try {
            InputStreamReader file = new InputStreamReader(openFileInput(nombre));
            BufferedReader br = new BufferedReader(file);
            String linea = br.readLine();
            StringBuilder texto = new StringBuilder();
            while (linea !=null){
                texto.append(linea);
                linea = br.readLine();
            }
            theOperation.set_histoy_fromString(texto.toString());
        } catch (IOException e) {
            Toast.makeText(this, "ERROR"+e, Toast.LENGTH_SHORT).show();
        }
    }

}