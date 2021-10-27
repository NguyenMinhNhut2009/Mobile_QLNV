package com.example.myapplication.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName="QLNV";
    static final int versionDB=1;

    public DbHelper(Context context){
        super(context, dbName,null,versionDB);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlPhong ="create table Phong (" +
                "maPhong TEXT PRIMARY KEY, " +
                "tenPhong TEXT NOT NULL)";
        db.execSQL(sqlPhong);
        String sqlNV ="create table NhanVien (" +
                "maNV TEXT PRIMARY KEY,"+
                "tenNV TEXT NOT NULL," +
                "gioitinh TEXT NOT NULL,"+
                "sdt TEXT NOT NULL," +
                "tenPhong TEXT REFERENCES Phong(tenPhong))";
        db.execSQL(sqlNV);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sqlPhong = "drop table if exists Phong";
        db.execSQL(sqlPhong);

        onCreate(db);

        String sqlNV ="drop table if exists NhanVien";
        db.execSQL(sqlNV);

        onCreate(db);
    }
}
