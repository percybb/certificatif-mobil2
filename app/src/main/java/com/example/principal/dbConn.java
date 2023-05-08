package com.example.principal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbConn extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase db;

    public dbConn(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS gisement(id integer primary key autoincrement,nom text,type text,luminosite double,status text, util integer);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS gisement;";
        db.execSQL(query);
        onCreate(db);
    }

    public void Open()
    {
        this.db = this.getWritableDatabase();
    }

    public void Close()
    {
        this.db.close();
    }



    public void InsertItem(String nom,String type,double luminosite, String status, int util )
    {
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("type",type);
        cv.put("luminosite",luminosite);
        cv.put("status",status);
        cv.put("util",util);

        this.db.insert("gisement",null,cv);
    }

    public ArrayList<Gisement> SelectAllGisement()
    {

        ArrayList<Gisement> listOfItems = new ArrayList<Gisement>();

        Cursor cursor = this.db.rawQuery("select * from gisement",null);

        int idIndex = cursor.getColumnIndex("id");
        int nomIndex = cursor.getColumnIndex("nom");
        int typeIndex = cursor.getColumnIndex("type");
        int  lumiIndex = cursor.getColumnIndex("luminosite");
        int  statusIndex = cursor.getColumnIndex("status");
        int  utilIndex = cursor.getColumnIndex("util");



        if((cursor != null) && cursor.moveToFirst())
        {
            do{
                listOfItems.add(new Gisement(cursor.getInt(idIndex),cursor.getString(nomIndex),cursor.getString(typeIndex),cursor.getDouble(lumiIndex),cursor.getString(statusIndex),cursor.getInt(utilIndex)));
               // listOfItems.add(new Gisement(0,"sss","dos",23.2,"ok",0));
            }while(cursor.moveToNext());
        }

        return listOfItems;
    }

    public void deleteGisementId(int id)
    {
        this.db.delete("gisement","id=?",new String[]{String.valueOf(id)});
        // this.db.execSQL("DELETE FROM students where id = 1;");
    }

    public Gisement SelectGisementWithId(int id)
    {
        Gisement res = null;
        Cursor c = this.db.rawQuery("select * from gisement where id =?",
                new String[]{String.valueOf(id)});
        //nom text,prix double,date text, stock integer,img text
        int idIndex = c.getColumnIndex("id");
        int nomIndex = c.getColumnIndex("nom");
        int typeIndex = c.getColumnIndex("type");
        int  lumiIndex = c.getColumnIndex("luminosite");
        int  statusIndex = c.getColumnIndex("status");
        int  utilIndex = c.getColumnIndex("util");

        if(c !=null && c.moveToNext())
        {
            res = new Gisement(c.getInt(idIndex),c.getString(nomIndex),c.getString(typeIndex),c.getDouble(lumiIndex),c.getString(statusIndex),c.getInt(utilIndex));
        }

        return res;
    }

    public ArrayList<Gisement> SelectGisementFilter(float min, float max)
    {
        ArrayList<Gisement> listOfItems = new ArrayList<Gisement>();
        Cursor cursor = this.db.rawQuery("select * from gisement where luminosite> ? and luminosite <?",
                new String[]{String.valueOf(min),String.valueOf(max)});
        //nom text,prix double,date text, stock integer,img text
        int idIndex = cursor.getColumnIndex("id");
        int nomIndex = cursor.getColumnIndex("nom");
        int typeIndex = cursor.getColumnIndex("type");
        int  lumiIndex = cursor.getColumnIndex("luminosite");
        int  statusIndex = cursor.getColumnIndex("status");
        int  utilIndex = cursor.getColumnIndex("util");

        if((cursor != null) && cursor.moveToFirst())
        {
            do{
                listOfItems.add(new Gisement(cursor.getInt(idIndex),cursor.getString(nomIndex),cursor.getString(typeIndex),cursor.getDouble(lumiIndex),cursor.getString(statusIndex),cursor.getInt(utilIndex)));
                // listOfItems.add(new Gisement(0,"sss","dos",23.2,"ok",0));
            }while(cursor.moveToNext());
        }

        return listOfItems;
    }


    public void modifierGisementtWithId(int id, String type,String status)
    {
        ContentValues cv = new ContentValues();

        cv.put("type",type);

        cv.put("status",status);

        this.db.update("gisement",cv,"id =?",new String[]{String.valueOf(id)});
        //this.db.execSQL("update students set name =" + nom +"where id=1;");
    }

    /*public void modifierGisementtWithId(int id, String nom, String type,double luminosite,String status, int util)
    {
        ContentValues cv = new ContentValues();
        cv.put("nom",nom);
        cv.put("type",type);
        cv.put("luminosite",luminosite);
        cv.put("status",status);
        cv.put("util",util);
        this.db.update("gisement",cv,"id =?",new String[]{String.valueOf(id)});
        //this.db.execSQL("update students set name =" + nom +"where id=1;");
    }
*/

    public void modifierStatus(int id,int type)
    {
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        this.db.update("gisement",cv,"id =?",new String[]{String.valueOf(id)});
        //this.db.execSQL("update students set name =" + nom +"where id=1;");
    }

    public String getStatus(int id)
    {
        String res = "";
        Cursor c = this.db.rawQuery("select * from gisement where id =?",
                new String[]{String.valueOf(id)});
        //nom text,prix double,date text, stock integer,img text

        int  statusIndex = c.getColumnIndex("status");

        if(c !=null && c.moveToNext())
        {
            res = c.getString(statusIndex);
        }

        return res;
    }
}
