package com.example.tarea_1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdministradorSQLiteOpenHelper extends SQLiteOpenHelper {
    private static String USUARIOS_TABLE_NAME = "usuarios";
    private static String ACADEMIC_TABLE_NAME = "informacion_academica";
    private static String PREFERENCIAS_TABLE_NAME = "preferencias";
    private static String sql_create_users_table = "CREATE TABLE IF NOT EXISTS "+USUARIOS_TABLE_NAME +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT(25), apellidos TEXT(25), " +
            "edad INTEGER(3), email TEXT(50), telefono TEXT(13), " +
            "addres TEXT(20), sexo TEXT(15), documento_identidad TEXT(12), " +
            "estado_civil TEXT(10))";
    private static String sql_create_academic_info_table = "CREATE TABLE IF NOT EXISTS "+ACADEMIC_TABLE_NAME +
            "(id_usuario INTEGER, " +
            "institucion TEXT(50), carrera TEXT(30), " +
            "ano_inicio INTEGER(4), ano_finalizacion INTEGER(4), " +
            "grado_obtenido TEXT(20), FOREIGN KEY(id_usuario) REFERENCES usuarios(id))";
    private static String sql_create_preferencias_table = "CREATE TABLE IF NOT EXISTS "+PREFERENCIAS_TABLE_NAME +
            "(id_usuario INTEGER, " +
            "hobbies TEXT(150), generos_musicales TEXT(150)," +
            "FOREIGN KEY(id_usuario) REFERENCES usuarios(id))";
    String database_name;
    public AdministradorSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.database_name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_create_users_table);
        sqLiteDatabase.execSQL(sql_create_academic_info_table);
        sqLiteDatabase.execSQL(sql_create_preferencias_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void guardar_usuario(Usuario usuario){
        //check if exists
        boolean usuario_existe = this.usuario_existe(usuario);
        //modify if exists
        //create if doesnt exists
        if(usuario_existe){
            this.modificar_usuario(usuario);
        }else {
            int id = this.crear_usuario(usuario);
            usuario.setId(id);
        }
        //save preferencias
        ContentValues preferencias = usuario.getPreferencias().getContentValues();
        preferencias.put("id_usuario", usuario.getId());
        crear_(PREFERENCIAS_TABLE_NAME, preferencias);
        //save informacion academica
        ContentValues informacion_academica = usuario.getInformacion_academica().getContentValues();
        informacion_academica.put("id_usuario", usuario.getId());
        crear_(ACADEMIC_TABLE_NAME, informacion_academica);
    }

    private boolean usuario_existe(Usuario usuario){
        //open conection
        SQLiteDatabase db = this.getReadableDatabase();
        //selection query
        String query = "SELECT * FROM usuarios WHERE id = ?";
        Cursor filtro = db.rawQuery(query, new String[]{String.valueOf(usuario.getId())});
        //return true if exists, false if doesnt
        boolean exists = filtro.getCount()>0;
        //db.close();
        return exists;
    }

    private int crear_usuario(Usuario usuario){
        //abrir conexion
        SQLiteDatabase db = this.getWritableDatabase();
        //insertar usuario
        long insert = db.insert("usuarios", null, usuario.getContentValues());
        //cerrar conexion
        //db.close();
        return (int) insert;
    }

    private boolean modificar_usuario(Usuario usuario){
        //abrir conexión
        SQLiteDatabase db = this.getWritableDatabase();
        //modificar
        int edit = db.update("usuarios", usuario.getContentValues(), "id = ?", new String[]{String.valueOf(usuario.getId())});
        //cerrar conexión
        //db.close();
        return edit>0;
    }

    private boolean crear_(String table_name, ContentValues contentValues){
        //abrir conexion
        SQLiteDatabase db = this.getWritableDatabase();
        //insertar usuario
        long insert = db.insert(table_name, null, contentValues);
        //cerrar conexion
        //db.close();
        return insert != -1;
    }

    private boolean modificar_(String table_name, ContentValues contentValues, String key_name, String value){
        //abrir conexión
        SQLiteDatabase db = this.getWritableDatabase();
        //modificar
        int edit = db.update(table_name, contentValues, key_name+" = ?", new String[]{value});
        //cerrar conexión
        //db.close();
        return edit>0;
    }

    public Usuario consultar_usuario(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        //db.rawQuery("SELECT * FROM "+USUARIOS_TABLE_NAME, new String[]{});
        Cursor cursor = db.query(USUARIOS_TABLE_NAME, null, "id = ?", new String[]{""+id}, null, null, null);
        String nombre = "not foud";
        String apellidos = "not foud";
        int edad = -1;
        String email ="not found";
        String telefono = "not found";
        String addres = "not found";
        String sexo = "not found";
        String documento_identidad = "not found";
        String estado_civil = "not found";
        //int id = -1;
        if (cursor.moveToFirst()){
            nombre = cursor.getString(1);
            apellidos = cursor.getString(2);
            edad = cursor.getInt(3);
            email = cursor.getString(4);
            telefono = cursor.getString(5);
            addres = cursor.getString(6);
            sexo = cursor.getString(7);
            documento_identidad = cursor.getString(8);
            estado_civil = cursor.getString(9);
        }

        //db.close();
        Usuario usuario = new Usuario(nombre, apellidos, edad, email, telefono, addres, sexo, documento_identidad, estado_civil, id);
        usuario.setPreferencias(consultar_pregerencias(id));
        usuario.setInformacion_academica(consultar_informacion_academica(id));
        usuario.setId(id);
        return usuario;
    }

    public Preferencias consultar_pregerencias(int id_usuario){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(PREFERENCIAS_TABLE_NAME, null, "id_usuario = ?", new String[]{""+id_usuario}, null, null, null);
        String hobbies = "not foud";
        String generos_musicales = "not foud";
        //int id = -1;
        if (cursor.moveToFirst()){
            hobbies = cursor.getString(1);
            generos_musicales = cursor.getString(2);
        }
        Preferencias preferencias = new Preferencias(hobbies.split(","), generos_musicales.split(","));

        //db.close();
        return preferencias;
    }
    public Informacion_academica consultar_informacion_academica(int id_usuario){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ACADEMIC_TABLE_NAME, null, "id_usuario = ?", new String[]{""+id_usuario}, null, null, null);
        String institucion = "not found";
        String carrera = "not found";
        int ano_inicio = -1;
        int ano_finalizacion = -1;
        String grado_obtenido = "not found";
        //int id = -1;
        if (cursor.moveToFirst()){
            institucion = cursor.getString(1);
            carrera = cursor.getString(2);
            ano_inicio = cursor.getInt(3);
            ano_finalizacion = cursor.getInt(4);
            grado_obtenido = cursor.getString(5);
        }
        Informacion_academica informacion_academica = new Informacion_academica(institucion, carrera, ano_inicio, ano_finalizacion, grado_obtenido);

        //db.close();
        return informacion_academica;
    }

    public Lista_usuarios consultar_lista_usuarios(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(USUARIOS_TABLE_NAME, new String[]{"id"}, null, null, null, null, null);
        Lista_usuarios lista_usuarios = new Lista_usuarios();
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                Usuario usuario = consultar_usuario(id);
                lista_usuarios.load_user(usuario);
            }while (cursor.moveToNext());
        }
        //db.close();
        return lista_usuarios;
    }
}
