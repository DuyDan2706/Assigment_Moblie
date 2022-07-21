package com.example.petshop.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.petshop.model.User;
import com.example.petshop.util.DBContext;

public class UserDAO {
    private static final String TABLE_USER = "tbl_user";
    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";
    //    DBContext dbContext;
    Context context;
    SQLiteDatabase sqLiteDatabase;


    public UserDAO(Context context) {
        this.context = context;
    }


    //    public List<User> searchAll (String search){
//      List<User> list = new ArrayList<>();
//
//      return list;
//
//    };
    public DBContext connect() {
        return new DBContext(context);
    }


    public boolean createUser(User user) {
        DBContext connection = connect();
        connection.onOpen(sqLiteDatabase);
        sqLiteDatabase = connection.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME, user.getUserName());
        contentValues.put(PASSWORD, user.getPassword());
        long result = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        connection.close();
        if (result == -1) return false;
        else return true;
    }

    public boolean confirmPassword(String pwd, String rePwd) {
        if (pwd.isEmpty() || rePwd.isEmpty()) {
            return false;
        } else if (pwd.equals(rePwd)) {
            return true;
        }
        return false;
    }

    public boolean checkExistAcc(String userName) {
        DBContext connection = connect();
        sqLiteDatabase = connection.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_NAME + "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{userName});
        if (cursor.getCount() <= 0) {
            return false;
        }
        return true;
    }
}

