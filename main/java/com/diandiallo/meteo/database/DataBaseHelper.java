package com.diandiallo.meteo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.diandiallo.meteo.classesMeteo.Ville;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "ville_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "country";
    private static final String COL4 = "url";

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT ,"+COL3+" TEXT,"+COL4+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE  " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(Ville ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, ville.getName());
        contentValues.put(COL3, ville.getCountry());
        contentValues.put(COL4, ville.getUrl());

        long result=-1;

        // avant d'ajouter la ville on verifie d'abord qu'elle n'y est pas
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME +
                " WHERE " + COL4 + " = '" + ville.getUrl() + "'";
        Cursor data = db.rawQuery(query, null);

        if(data.moveToFirst() && data.getCount() > 0){
            result=-1;
        }else{
            result = db.insert(TABLE_NAME, null, contentValues);
        }

        //retourne -1 si la ville n'est pas insérée correctement
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Retourne toutes les données de la BD
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Retuourne seulement l'ID qui correspond à l'url de la ville
     * @param ville
     * @return
     */
    public Cursor getItemID(Ville ville){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL4 + " = '" + ville.getUrl() + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        db.execSQL(query);
    }

    /**
     * Supprime de la BD
     * @param id
     * @param url
     */
    public void deleteVille(String url){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL4 + " = '" + url + "'";
        db.execSQL(query);
    }

}
