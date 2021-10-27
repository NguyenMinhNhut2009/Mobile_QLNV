package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.Phong;

import java.util.ArrayList;
import java.util.List;

public class PhongDao {
    private SQLiteDatabase db;

    public PhongDao(Context context){
        DbHelper dbHelper= new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Phong s){
        ContentValues values = new ContentValues();

        values.put("maPhong",s.maPhong);
        values.put("tenPhong",s.tenPhong);

        return db.insert("Phong",null,values);

    }
    public long update(Phong s){
        ContentValues values = new ContentValues();

        values.put("tenPhong", s.tenPhong);

        return db.update("Phong",values,"maPhong=?", new String[]{(s.maPhong)});
    }
    public int delete(String maPhong){
        return db.delete("Phong","maPhong=?", new String[]{maPhong});
    }

    public List<Phong> getPhong(String sqlPhong, String...selectionArgs){
        List<Phong> list = new ArrayList<Phong>();

        Cursor c= db.rawQuery(sqlPhong,selectionArgs);

        while (c.moveToNext()){

            Phong s = new Phong();

            s.maPhong = c.getString(c.getColumnIndex("maPhong"));
            s.tenPhong= c.getString(c.getColumnIndex("tenPhong"));

            list.add(s);

        }
        return list;
    }

    public List<Phong> getLopALL(){
        String sqlPhong = "SELECT * FROM Phong";

        return getPhong(sqlPhong);
    }

    public List<Phong> getPhongTenPhong(String tenPhong){
        String sqlPhong ="select * from Phong where tenPhong=?";
        List<Phong> list= getPhong(sqlPhong,tenPhong);
        return list;
    }
}
