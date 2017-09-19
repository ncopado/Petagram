package com.ncopado.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ncopado.petagram.pojo.Pet;

import java.util.ArrayList;

/**
 * Created by ncopado on 17/09/17.
 */

public class DataBase extends SQLiteOpenHelper {

    private  Context context;
    public DataBase(Context context) {
        super(context, dbConstants.DATABASE_NAME, null, dbConstants.DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String queryCrearTablaContacto="CREATE TABLE " +dbConstants.TABLE_PETS + "("+
                dbConstants.COLUMN_PET_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                dbConstants.COLUMN_PET_NAME +" TEXT,"+
                dbConstants.COLUMN_PET_PHOTO+" INTEGER "+
                ")";

        String queryCrearTablaLikesContacto="CREATE TABLE " +dbConstants.TABLE_LIKES_PETS + "("+
                dbConstants.COLUMN_PET_LIKES_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                dbConstants.COLUMN_PET_LIKES_ID_PET +" INTEGER,"+
                dbConstants.COLUMN_PET_LIKES_NUMERO+" INTEGER, "+
                "FOREIGN KEY ("+dbConstants.COLUMN_PET_LIKES_ID_PET+ ")  "+
                "REFERENCES "+ dbConstants.TABLE_PETS + "("+dbConstants.COLUMN_PET_ID+")"+
                ")";

        sqLiteDatabase.execSQL(queryCrearTablaContacto);
        sqLiteDatabase.execSQL(queryCrearTablaLikesContacto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+ dbConstants.TABLE_PETS);
        db.execSQL("DROP TABLE IF EXIST "+ dbConstants.TABLE_LIKES_PETS);
        onCreate(db);

    }


    public ArrayList<Pet> getAllPets(){
        ArrayList<Pet>lstPet=new ArrayList<Pet>();

        String query= "SELECT * FROM "+ dbConstants.TABLE_PETS ;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor registros= db.rawQuery(query,null);


        while (registros.moveToNext()){

            Pet pet=new Pet();

            pet.setId(registros.getInt(0));
            pet.setName(registros.getString(1));
            pet.setPhoto(registros.getInt(2));


            String queryLikes="SELECT COUNT("+dbConstants.COLUMN_PET_LIKES_NUMERO+")" +
                    " FROM "+dbConstants.TABLE_LIKES_PETS +
                    " WHERE "+ dbConstants.COLUMN_PET_LIKES_ID_PET +" = " +pet.getId();


            Cursor registroLikes=db.rawQuery(queryLikes,null);

            if(registroLikes.moveToNext()){
                pet.setReiting(registroLikes.getInt(0));
            }
            else{
                pet.setReiting(0);
            }


            lstPet.add(pet);


        }

        db.close();



        return  lstPet;



    }


    public ArrayList<Pet> getTopFivePets(){
        ArrayList<Pet>lstPet=new ArrayList<Pet>();

        String query= "SELECT  T1."+dbConstants.COLUMN_PET_ID +", T1."+dbConstants.COLUMN_PET_NAME+ ", T1."+dbConstants.COLUMN_PET_PHOTO + ", COUNT (T2."+dbConstants.COLUMN_PET_LIKES_NUMERO +") FROM "+ dbConstants.TABLE_PETS    +" T1 " +
                      " INNER JOIN " +dbConstants.TABLE_LIKES_PETS +" T2  ON T2." + dbConstants.COLUMN_PET_LIKES_ID_PET +" = T1." +dbConstants.COLUMN_PET_ID +
                      " GROUP BY  T1."+dbConstants.COLUMN_PET_ID +", T1."+dbConstants.COLUMN_PET_NAME+ ", T1."+dbConstants.COLUMN_PET_PHOTO + " "  +
                      " ORDER BY COUNT(T2."+dbConstants.COLUMN_PET_LIKES_NUMERO + ") DESC limit 5" ;

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor registros= db.rawQuery(query,null);


        while (registros.moveToNext()){

            Pet pet=new Pet();

            pet.setId(registros.getInt(0));
            pet.setName(registros.getString(1));
            pet.setPhoto(registros.getInt(2));


            String queryLikes="SELECT COUNT("+dbConstants.COLUMN_PET_LIKES_NUMERO+")" +
                    " FROM "+dbConstants.TABLE_LIKES_PETS +
                    " WHERE "+ dbConstants.COLUMN_PET_LIKES_ID_PET +" = " +pet.getId();


            Cursor registroLikes=db.rawQuery(queryLikes,null);

            if(registroLikes.moveToNext()){
                pet.setReiting(registroLikes.getInt(0));
            }
            else{
                pet.setReiting(0);
            }


            lstPet.add(pet);


        }

        db.close();



        return  lstPet;



    }



    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(dbConstants.TABLE_PETS,null,contentValues);
        db.close();
    }

    public void  insertRating(ContentValues contentValues){
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(dbConstants.TABLE_LIKES_PETS,null,contentValues);
        db.close();
    }

    public int getRatingPet(Pet pet){
        int rating=0;

        String query="SELECT COUNT("+dbConstants.COLUMN_PET_LIKES_NUMERO+")" +
                " FROM "+dbConstants.TABLE_LIKES_PETS +
                " WHERE "+ dbConstants.COLUMN_PET_LIKES_ID_PET +" = " +pet.getId();

        SQLiteDatabase db=this.getWritableDatabase();

        Cursor registro=db.rawQuery(query,null);

        while (registro.moveToNext()){
            rating=registro.getInt(0);
        }

        db.close();

        return  rating;
    }

}
