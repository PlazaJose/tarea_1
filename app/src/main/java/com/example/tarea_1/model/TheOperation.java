package com.example.tarea_1.model;

import java.util.ArrayList;

public class TheOperation {
    ArrayList<String> operation_list;

    public static String SIGNO_RAIZ = "√";
    public static String SIGNO_POTENCIA = "²";
    String[] tier_list = {"(", SIGNO_POTENCIA, SIGNO_RAIZ, "*", "/", "+", "-"};
    String[] tier_list_r = {"(", SIGNO_POTENCIA, SIGNO_RAIZ, "*/", "+-"};

    public TheOperation(ArrayList<String> operation_list){
        this.operation_list = operation_list;
    }

    public ArrayList<String> operate_subsequence_r(ArrayList<String> ss){
        ArrayList<String> result_tier_1 = get_squeres(ss);
        if(check_tier(tier_list[1], result_tier_1))return operate_subsequence_r(result_tier_1);
        ArrayList<String> result_tier_2 = get_squer_rooth(result_tier_1);
        if(check_tier(tier_list[2], result_tier_2))return operate_subsequence_r(result_tier_2);
        //ArrayList<String> result_tier_3 = get_multiplication(result_tier_2);
        //if(check_tier(3, result_tier_3))return operate_subsequence_r(result_tier_3);
        //ArrayList<String> result_tier_4 = get_division(result_tier_3);
        //if(check_tier(4, result_tier_4))return operate_subsequence_r(result_tier_4);
        ArrayList<String> result_tier_4 = get_Mult_Div(result_tier_2);
        if(check_tier("*", result_tier_4))return operate_subsequence_r(result_tier_4);
        if(check_tier("/", result_tier_4))return operate_subsequence_r(result_tier_4);
        //ArrayList<String> result_tier_5 = get_sums(result_tier_4);   //nueva tier
        //if(check_tier(5, result_tier_5))return operate_subsequence_r(result_tier_5);//nueva tier
        //ArrayList<String> result_tier_6 = get_restas(result_tier_5);   //nueva tier
        //if(check_tier(6, result_tier_6))return operate_subsequence_r(result_tier_6);//nueva tier
        ArrayList<String> result_tier_6 = get_sum_res(result_tier_4);   //nueva tier
        if(check_tier("+", result_tier_6))return operate_subsequence_r(result_tier_6);//nueva tier
        if(check_tier("-", result_tier_6))return operate_subsequence_r(result_tier_6);//nueva tier
        return result_tier_6;//nueva tier
    }

    private boolean check_tier_old(int tier, ArrayList<String> ss){
        for (int i = 0; i < ss.size(); i++){
            if(ss.get(i).contains(tier_list[tier])){
                return true;
            }
        }
        return false;
    }
    private boolean check_tier(String tier, ArrayList<String> ss){
        for (int i = 0; i < ss.size(); i++){
            if(ss.get(i).contains(tier)){
                if(tier.equals("-") && ss.get(i).length()>1){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> get_squer_rooth(ArrayList<String> lista){
        ArrayList<String> result_tier_2 = new ArrayList<>();
        boolean entered = false;
        //int omitir = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).contains(SIGNO_RAIZ) && !entered) {
                entered = true;
                String sn = lista.get(i).replaceAll("[^0-9.-]", "");
                double number = Double.parseDouble(sn);
                if(number<0)number = number*-1;
                double r = Math.sqrt(number);
                String rs = (Double.parseDouble(sn)<0?"ERROR":""+r);
                result_tier_2.add(rs);
            }else{
                result_tier_2.add(lista.get(i));
            }
        }
        return result_tier_2;
    }
    private ArrayList<String> get_squeres(ArrayList<String> lista){
        ArrayList<String> result_tier_2 = new ArrayList<>();
        boolean entered = false;
        //int omitir = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).contains(SIGNO_POTENCIA) && !entered) {
                entered = true;
                String sn = lista.get(i).replaceAll("[^0-9.-]", "");
                double number = Double.parseDouble(sn);
                double r = number*number;
                result_tier_2.add(""+r);
            }else{
                result_tier_2.add(lista.get(i));
            }
        }
        return result_tier_2;
    }

    private ArrayList<String> get_multiplication(ArrayList<String> ss){
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
        return result_tier_3;
    }

    private ArrayList<String> get_division(ArrayList<String> result_tier_3){
        ArrayList<String> result_tier_4 = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < result_tier_3.size(); i++) {
            if (result_tier_3.get(i).equals("/") && !entered) {
                if (i + 1 < result_tier_3.size()) {

                    Double no1 = Double.valueOf(result_tier_3.get(i - 1));
                    Double no2 = Double.valueOf(result_tier_3.get(i + 1));
                    double r = no1 / no2;
                    result_tier_4.set(result_tier_4.size()-1, "" + r);
                    entered = true;
                    omitir = i+1;
                    //Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_4.add(result_tier_3.get(i));
            }
        }
        return result_tier_4;
    }

    private ArrayList<String> get_Mult_Div(ArrayList<String> lista){
        ArrayList<String> result = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < lista.size(); i++) {
            if ((lista.get(i).equals("*")) && !entered) {
                if (i + 1 < lista.size() && i-1 >= 0) {
                    Double no1 = Double.valueOf(lista.get(i - 1));
                    Double no2 = Double.valueOf(lista.get(i + 1));
                    double r = no1 * no2;
                    result.set(result.size()-1, "" + r);
                    entered = true;
                    omitir = i+1;
                }
            }else if (lista.get(i).equals("/") && !entered) {
                if (i + 1 < lista.size() && i-1 >= 0) {
                    Double no1 = Double.valueOf(lista.get(i - 1));
                    Double no2 = Double.valueOf(lista.get(i + 1));
                    double r = no1 / no2;
                    result.set(result.size()-1, "" + r);
                    entered = true;
                    omitir = i+1;
                }
            } else{
                if(i!=omitir)result.add(lista.get(i));
            }
        }
        return result;
    }
    private ArrayList<String> get_sums(ArrayList<String> result_tier_4){
        ArrayList<String> result_tier_5 = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < result_tier_4.size(); i++) {      //vieja tier
            if (result_tier_4.get(i).equals("+") && !entered) {//vieja tier validar operacion
                if (i + 1 < result_tier_4.size()) {//vieja tier
                    Double no1 = Double.valueOf(result_tier_4.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(result_tier_4.get(i + 1));//vieja tier
                    double r = no1 + no2; //operacion
                    result_tier_5.set(result_tier_5.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                    //Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_5.add(result_tier_4.get(i));//nueva   (   vieja tier
            }
        }
        return result_tier_5;
    }

    private ArrayList<String> get_restas(ArrayList<String> result_tier_5){
        ArrayList<String> result_tier_6 = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < result_tier_5.size(); i++) {      //vieja tier
            if (result_tier_5.get(i).equals("-") && !entered) {//vieja tier validar operacion
                if (i + 1 < result_tier_5.size()) {//vieja tier
                    Double no1 = Double.valueOf(result_tier_5.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(result_tier_5.get(i + 1));//vieja tier
                    double r = no1 - no2; //operacion
                    result_tier_6.set(result_tier_6.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                    //Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            }else{
                if(i!=omitir)result_tier_6.add(result_tier_5.get(i));//nueva   (   vieja tier
            }
        }
        return result_tier_6;
    }

    private ArrayList<String> get_sum_res(ArrayList<String> lista){
        ArrayList<String> result = new ArrayList<>();
        boolean entered = false;
        int omitir = -1;
        for (int i = 0; i < lista.size(); i++) {      //vieja tier
            if (lista.get(i).equals("+") && !entered) {//vieja tier validar operacion
                if (i + 1 < lista.size() && i - 1 >= 0) {//vieja tier
                    Double no1 = Double.valueOf(lista.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(lista.get(i + 1));//vieja tier
                    double r = no1 + no2; //operacion
                    result.set(result.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                }
            }else if (lista.get(i).equals("-") && !entered) {//vieja tier validar operacion
                if (i + 1 < lista.size() && i - 1 >= 0) {//vieja tier
                    Double no1 = Double.valueOf(lista.get(i - 1));//vieja tier
                    Double no2 = Double.valueOf(lista.get(i + 1));//vieja tier
                    double r = no1 - no2; //operacion
                    result.set(result.size()-1, "" + r);//nueva tier
                    entered = true;
                    omitir = i+1;
                    //Toast.makeText(this, no1+":"+no2+":"+r, Toast.LENGTH_SHORT).show();
                }
            } else{
                if(i!=omitir)result.add(lista.get(i));//nueva   (   vieja tier
            }
        }
        return result;
    }

}
