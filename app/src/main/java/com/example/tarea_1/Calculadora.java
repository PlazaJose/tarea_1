package com.example.tarea_1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Calculadora extends AppCompatActivity {

    ArrayList<String> operation_list = new ArrayList<>();
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
        if(current_info.length()>0){
            current_info = "";
        }else if (operation_list.size()>0) {
            operation_list.remove(operation_list.size()-1);
            current_info = cplus();
        }
        show_operation();
    }

    private String cplus(){
        if(operation_list.size()<1)return "";
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
        if(current_info.length()>0){
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

    private String change_sign(){
        if(current_info == null) return current_info;
        if(current_info.isEmpty()) return current_info;
        double current_number = Double.parseDouble(current_info);
        double changed = current_number*-1;
        return ""+changed;
    }

    public void equal(View v){
        add_operation("");
        current_info = operate_subsequence_r(operation_list).get(0);
        operation_list = new ArrayList<>();
        show_operation();
    }

    ArrayList<String> operate_subsequence_r(ArrayList<String> ss){
        ArrayList<String> result_tier_3 = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < ss.size(); i++) {
            if (ss.get(i).equals("*") && !entered) {
                if (i + 1 < ss.size()) {
                    Double no1 = Double.valueOf(ss.get(i - 1));
                    Double no2 = Double.valueOf(ss.get(i + 1));
                    double r = no1 * no2;
                    result_tier_3.set(result_tier_3.size()-1, "" + r);
                    entered = true;
                    omitir = i+1;
                }
            }else{
                if(i!=omitir)result_tier_3.add(ss.get(i));
            }
        }
        if(!check_tier(2, result_tier_3))return operate_subsequence_r(result_tier_3);
        ArrayList<String> result_tier_4 = new ArrayList<>();
        entered = false;
        omitir = -1;
        for (int i = 0; i < result_tier_3.size(); i++) {
            if (result_tier_3.get(i).equals("/") && !entered) {
                if (i + 1 < result_tier_3.size()) {

                    Double no1 = Double.valueOf(result_tier_3.get(i - 1));
                    Double no2 = Double.valueOf(result_tier_3.get(i + 1));
                    double r = no1 / no2;
                    result_tier_4.set(result_tier_4.size()-1, "" + r);
                    entered = true;
                    omitir = i+1;
                    Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_4.add(result_tier_3.get(i));
            }
        }
        if(!check_tier(3, result_tier_4))return operate_subsequence_r(result_tier_4);
        ArrayList<String> result_tier_5 = new ArrayList<>();   //nueva tier
        entered = false;
        omitir = -1;
        for (int i = 0; i < result_tier_4.size(); i++) {      //vieja tier
            if (result_tier_4.get(i).equals("+") && !entered) {//vieja tier validar operacion
                if (i + 1 < result_tier_4.size()) {//vieja tier
                    Double no1 = Double.valueOf(result_tier_4.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(result_tier_4.get(i + 1));//vieja tier
                    double r = no1 + no2; //operacion
                    result_tier_5.set(result_tier_5.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                    Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_5.add(result_tier_4.get(i));//nueva   (   vieja tier
            }
        }
        if(!check_tier(4, result_tier_5))return operate_subsequence_r(result_tier_5);//nueva tier

        ArrayList<String> result_tier_6 = new ArrayList<>();   //nueva tier
        entered = false;
        omitir = -1;
        for (int i = 0; i < result_tier_5.size(); i++) {      //vieja tier
            if (result_tier_5.get(i).equals("-") && !entered) {//vieja tier validar operacion
                if (i + 1 < result_tier_5.size()) {//vieja tier
                    Double no1 = Double.valueOf(result_tier_5.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(result_tier_5.get(i + 1));//vieja tier
                    double r = no1 - no2; //operacion
                    result_tier_6.set(result_tier_6.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                    Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_6.add(result_tier_5.get(i));//nueva   (   vieja tier
            }
        }
        if(!check_tier(5, result_tier_6))return operate_subsequence_r(result_tier_6);//nueva tier
        return result_tier_6;//nueva tier
    }

    private boolean check_tier(int tier, ArrayList<String> ss){
        //String tier_list[] = {"(", "raiz", "*", "/", "+", "-"};
        for (int i = 0; i < ss.size(); i++){
            if(ss.get(i).equals(tier_list[tier])){
                return false;
            }
        }
        return true;
    }

}