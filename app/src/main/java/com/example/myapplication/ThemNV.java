package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myapplication.Adapter.NhanVienAdpter;
import com.example.myapplication.Database.NhanVienDao;
import com.example.myapplication.Model.NhanVien;

import java.util.ArrayList;

public class ThemNV extends AppCompatActivity {
    ArrayList<NhanVien> nhanViens;
    ListView lvNV;
    Button btnthem;

    public NhanVienAdpter adpter;
    NhanVienDao nhanVienDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nv);
        lvNV = findViewById(R.id.Lv);
        btnthem=findViewById(R.id.btnThemNV);
        nhanVienDao= new NhanVienDao(this);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ThemNV.this,NhanVienActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();

        capnhatLV();
    }

    public void capnhatLV() {
            nhanViens=(ArrayList<NhanVien>) nhanVienDao.getNhaVienAll();

        adpter= new NhanVienAdpter(this,nhanViens);

        lvNV.setAdapter(adpter);
    }


}