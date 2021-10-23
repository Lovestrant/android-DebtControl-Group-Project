package com.example.debtcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "DebtControl.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE authentication(phonenumber TEXT primary key,securityKey TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists authentication");
    }


    //method to insert dta to Db
    public Boolean insertUserData(String phonenumber, String securityKey, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("phonenumber", phonenumber);
        contentValues.put("securityKey", securityKey);
        contentValues.put("password", password);
        long result = DB.insert("authentication", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateUserData(String phonenumber,String password,String securityKey) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from authentication where phonenumber = ? and securityKey =?",
                new String[] {phonenumber,securityKey});

        if(cursor.getCount() >0) {
            long result = DB.update("authentication", contentValues,"phonenumber=?", new String[] {phonenumber});

            if (result == 1) {
                return true;
            } else {
                return false;
            }
        }else{
           return false;
        }

    }
    public Boolean deleteUserdata(String phonenumber){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from authentication where phonenumber=?", new String[]{phonenumber});
        if(cursor.getCount()>0) {
            long result = DB.delete("authentication", "phonenumber=?", new String[]{phonenumber});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

//    public Cursor getdata(String phone){
//        SQLiteDatabase DB=this.getWritableDatabase();
//
//       try (Cursor cursor = DB.rawQuery("Select * from authentication where phonenumber=?",
//                new String[] {phone})){
//            return cursor;
//        }
//
//
//    }
    public Cursor getdata(String phone){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from authentication where phonenumber=?", new String[] {phone});
        return cursor;

    }


    public Boolean checkUser(String phoneNumber, String password) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * from authentication where phonenumber = ? and password = ?",
               new String[] {phoneNumber,password});
        if(cursor.getCount() >0) {
            return true;
        }else{
            return false;
        }
    }


}
