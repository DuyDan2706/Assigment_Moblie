package com.example.petshop.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.petshop.model.User;

public class DBContext extends SQLiteOpenHelper {
private static final String DB_NAME = "CycleManagement.db";

    public DBContext(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE tbl_user (username TEXT, password TEXT)";
        db.execSQL(query);
        db.execSQL("INSERT INTO tbl_user values('dan','1234')");
        db.execSQL("INSERT INTO tbl_user values('dan','1234')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS tbl_user";
        db.execSQL(query);
        onCreate(db);
    }

    public String insertData( String userName, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", userName);
        cv.put("password", pwd);

        long res = db.insert("tbl_user", null, cv);

        if (res == -1){
            return "Failed";
        } else {
            return "Successfully";
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT tbl_user.username, tbl_user.password FROM tbl_user", null);
        return cursor;
    }

    public Boolean checkSignIn(String userName, String pwd){
//        String query = " SELECT * FROM tbl_user WHERE username=? and password=? ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" SELECT * FROM tbl_user WHERE username=? and password=?", new String[] {userName,pwd});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

//
//    public boolean checkExistAcc(String userName) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String query = "SELECT * FROM tbl_user WHERE username=?";
//        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{userName});
//        if (cursor.getCount() < 0)
//        {return false;}
//        else
//        {return true;}
//    }
//
//    public boolean createUser(User user) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("username", user.getUserName());
//        contentValues.put("password", user.getPassword());
//        long result = sqLiteDatabase.insert("tbl_user", null, contentValues);
//        if (result == -1) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//
//    public boolean confirmPassword(String pwd, String rePwd) {
//        if (pwd.isEmpty() || rePwd.isEmpty()) {
//            return false;
//        } else if (pwd.equals(rePwd)) {
//            return true;
//        }
//        return false;
//    }
}
