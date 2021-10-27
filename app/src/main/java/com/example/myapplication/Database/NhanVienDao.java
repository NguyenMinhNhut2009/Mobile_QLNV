package com.example.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDao {
    private SQLiteDatabase db;

    public NhanVienDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db= dbHelper.getWritableDatabase();
    }
    public long insert(NhanVien s){
        ContentValues values  = new ContentValues();
        values.put("maNV", s.maNV);
        values.put("tenNV", s.tenNV);
        values.put("gioitinh", s.gioitinh);
        values.put("tenPhong", s.tenPhong);
        values.put("sdt", s.sdt);
        return db.insert("NhanVien",null, values);
    }
    public long update(NhanVien s){
        ContentValues values = new ContentValues();
        values.put("TenNV", s.tenNV);
        values.put("TenPhong", s.tenPhong);
        values.put("gioitinh", s.gioitinh);
        values.put("sdt", s.sdt);
        return db.update("NhanVien",values,"MaNV=?",new String[]{(s.maNV)});
    }
    public int delete(String maNV){
        return db.delete("NhanVien","maNv=?",new String[]{maNV});
    }

    public List<NhanVien> getNhanVien(String sqlNV, String...selectionArgs){
        List<NhanVien> list = new ArrayList
                <NhanVien>();

        Cursor c= db.rawQuery(sqlNV,selectionArgs);

        while (c.moveToNext()){
            NhanVien s= new NhanVien();

           s.maNV=c.getString(c.getColumnIndex("maNV"));
            s.tenNV=c.getString(c.getColumnIndex("tenNV"));
            s.gioitinh=c.getString(c.getColumnIndex("gioitinh"));
            s.tenPhong=c.getString(c.getColumnIndex("tenPhong"));
            s.sdt=c.getString(c.getColumnIndex("sdt"));
            list.add(s);
        }
        return list;
    }

    public List<NhanVien> getNhaVienAll(){
        String sqlNv ="SELECT * FROM NhanVien";

        return getNhanVien(sqlNv);
    }

    public List<String> getNhanvientenPhong(){
        String sql="SELECT tenPhong FROM Phong";
        List<String> list = new ArrayList<String>();


        Cursor c= db.rawQuery(sql,null);
        while (c.moveToNext()){
            String tenPhong= c.getString(c.getColumnIndex("tenPhong"));

            list.add(tenPhong);
        }
        return list;
    }
    public List<NhanVien> getNhanVienTenNV(String tenNV){
        String sqlNV ="select *from NhanVien where tenNv=?";

        List<NhanVien> list = getNhanVien(sqlNV,tenNV);
        return list;
    }
}
