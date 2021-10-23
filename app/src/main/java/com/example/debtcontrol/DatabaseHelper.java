package com.example.debtcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "DebtorsDb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE DebtorsListTable(id INTEGER  primary key autoincrement,sellerPhone TEXT,name TEXT, itemList TEXT, Total TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE if exists DebtorsListTable");
    }


    //method to insert dta to Db
    public Boolean insertUserData(String sellerPhone, String name, String itemList, String Total) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

       // contentValues.put("id", id);
        contentValues.put("sellerPhone", sellerPhone);
        contentValues.put("name", name);
        contentValues.put("itemList", itemList);
        contentValues.put("Total", Total);
        long result = DB.insert("DebtorsListTable", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updateUserData(String id,String itemList, String Total) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

         contentValues.put("Total", Total);
        contentValues.put("itemList", itemList);
        Cursor cursor = DB.rawQuery("Select * from DebtorsListTable where id = ?",
                new String[] {id});

        if(cursor.getCount() >0) {
            long result = DB.update("DebtorsListTable", contentValues,"id=?", new String[] {id});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }

    }
    public Boolean deleteUserdata(String id){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("Select * from DebtorsListTable where id=?", new String[]{id});
        if(cursor.getCount()>0) {
            long result = DB.delete("DebtorsListTable", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(String sellerPhone){
        SQLiteDatabase DB=this.getWritableDatabase();

        try (Cursor cursor = DB.rawQuery("Select * from DebtorsListTable where sellerPhone=?",
                new String[] {sellerPhone})) {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;

        }


    }

}
