package com.example.ex1_dmiot;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS VACINA");

        sqLiteDatabase.execSQL("CREATE TABLE VACINA(\n" +
                "\tID INT NOT NULL, \n" +
                "\tNOME_VACINA VARCHAR(50) NOT NULL,\n" +
                "\tDATA_VACINA VARCHAR(8) NOT NULL,\n" +
                "\tUNIDADE VARCHAR(50) NOT NULL,\n" +
                "\tDOSE VARCHAR(50) NOT NULL\n" +
       ");");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS VACINA");
    }
    public void inserirVacina(Integer id, String nome, String data_vacina, String unidade, String dose) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("NOME_VACINA", nome);
        cv.put("DATA_VACINA", data_vacina);
        cv.put("UNIDADE", unidade);
        cv.put("DOSE", dose);
        db.insert("CLIENTE", "ID", cv);
    }

    Cursor listaVacina (){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT  ID, NOME_VACINA,DATA_VACINA,UNIDADE,DOSE FROM VACINA ORDER BY ID", null);
         return cur;
    }
    public void atualizarVacina(Integer id, String nome, String data_vacina, String unidade, String dose){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("NOME_VACINA", nome);
        cv.put("DATA_VACINA", data_vacina);
        cv.put("UNIDADE", unidade);
        cv.put("DOSE", dose);
        db.update("VACINA", cv, "ID=?", new String []{id.toString()});

    }
    public void removeVacina(String nome){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("VACINA","NOME=?", new String[]{nome});
    }

}
