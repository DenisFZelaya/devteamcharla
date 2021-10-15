package com.dfzdev.devteamcharla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dfzdev.devteamcharla.models.Charla;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "charlasdev", null, 1);
    }

    //1.2 - Crear tablas en db
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCharlas = "" +
                "        CREATE TABLE Charlas (" +
                "                Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "                Tema TEXT," +
                "                Descripcion TEXT," +
                "                Fecha TEXT" +
                ");";

        //Ejecutar consulta SQL
        db.execSQL(createTableCharlas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Funciones CRUD

    //1.4 - Get all
    public List<Charla> getAllCharlas(){
        List<Charla> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM Charlas";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tema = cursor.getString(1);
                String descripcion = cursor.getString(2);
                String fecha = cursor.getString(3);

                Charla charlaModel = new Charla(id,tema,descripcion,fecha);

                returnList.add(charlaModel);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    //1.5 - Get by id
    public Charla getCharlaById(int id) {
        Charla charla = new Charla();

        String queryString = "SELECT * FROM Charlas WHERE Id = " + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                charla.setId(cursor.getInt(0));
                charla.setTema(cursor.getString(1));
                charla.setDescripcion(cursor.getString(2));
                charla.setFecha(cursor.getString(3));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return charla;

    }

    //1.6 - Add
    public boolean addCharla(Charla charla) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Id", (Integer) null);
        cv.put("Tema", charla.getTema());
        cv.put("Descripcion", charla.getDescripcion());
        cv.put("Fecha", charla.getFecha());

        long insert = db.insert("Charlas", null, cv);

        if(insert == -1){
            return  false;
        }else {
            return  true;
        }
    }

    //1.7 - Update
    public  boolean updateCharla(Charla charla){
        SQLiteDatabase db = getWritableDatabase();
        String queryString = "UPDATE Charlas " +
                "SET Tema = '" + charla.getTema() + "', "+
                " Descripcion = '" + charla.getDescripcion()+ "', "+
                " Fecha = '" + charla.getFecha() + "' "+
                " WHERE Id= " + charla.getId() + ";";

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return  false;
        }else  {
            return  true;
        }
    }

    //1.8 - Delete
    public boolean deleteCharlaById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM Charlas WHERE Id = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            return  false;
        }else  {
            return  true;
        }
    }
}
