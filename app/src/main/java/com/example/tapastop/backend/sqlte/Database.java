package com.example.tapastop.backend.sqlte;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "tapas_top.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_AMIGOS = "t_amigos";
    public static final String TABLE_RESTAURANTE = "t_restaurante";
    public static final String TABLE_PLATO_COMIDA = "t_plato_comida";
    public static final String TABLE_DEGUSTACION = "t_degustacion";
    public static final String TABLE_GALARDON_PAELLAS = "t_galardon_paella";
    public static final String TABLE_GALARDON_PIZZA = "t_galardon_pizza";
    public static final String TABLE_GALARDON_TORTILLA = "t_galardon_tortilla";



    private static Context c;

    public Database(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
        c = context;
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USUARIOS +"(" +
                "Username TEXT PRIMARY KEY UNIQUE," +
                "Password TEXT NOT NULL," +
                "Edad TEXT NOT NULL," +
                "Email TEXT NOT NULL,"+
                "Nombre TEXT," +
                "Apellido1 TEXT," +
                "Apellido2 TEXT," +
                "Foto BLOB," +
                "Ciudad TEXT," +
                "Info TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_RESTAURANTE+"(" +
                "Nombre TEXT PRIMARY KEY UNIQUE," +
                "Username TEXT  NOT NULL," +
                "Direccion TEXT NOT NULL,"+
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PLATO_COMIDA +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                "Nombre TEXT NOT NULL,"+
                "Tipo_comida TEXT NOT NULL," +
                "Region TEXT NOT NULL," +
                "Sabor TEXT NOT NULL," +
                "Descripcion TEXT," +
                "Foto BLOB," +
                "Restaurante TEXT NOT NULL," +
                "FOREIGN KEY(Restaurante) REFERENCES TABLE_RESTAURANTE(Nombre))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DEGUSTACION +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "id_Plato_comida INTEGER NOT NULL," +
                "Calificacion INTEGER NOT NULL ," +
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username)," +
                "FOREIGN KEY(id) REFERENCES TABLE_PLATO_COMIDA(id))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_AMIGOS +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username1 TEXT NOT NULL," +
                "Username2 TEXT NOT NULL ," +
                "FOREIGN KEY(Username1) REFERENCES TABLE_USUARIOS(Username)," +
                "FOREIGN KEY(Username2) REFERENCES TABLE_USUARIOS(Username))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GALARDON_PAELLAS +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Degustaciones INTEGER NOT NULL,"+
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GALARDON_PIZZA +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Degustaciones INTEGER NOT NULL,"+
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username))");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GALARDON_TORTILLA +"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username TEXT NOT NULL," +
                "Degustaciones INTEGER NOT NULL,"+
                "FOREIGN KEY(Username) REFERENCES TABLE_USUARIOS(Username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

   /* public static void main(String args[]){
        Database database = new Database(this);
        SQLiteDatabase db = database.getWritableDatabase();
        db.onCreate();
    }*/
}
