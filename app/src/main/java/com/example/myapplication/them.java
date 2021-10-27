package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.example.myapplication.Adapter.PhongAdapter;
import com.example.myapplication.Database.PhongDao;
import com.example.myapplication.Model.Phong;

import java.util.ArrayList;

public class them extends AppCompatActivity {
    ArrayList<Phong> phongs;

    ListView lvNV;
    Button btnthem;
    public PhongAdapter adpter;

    PhongDao phongDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        lvNV= findViewById(R.id.Lv);
        btnthem=findViewById(R.id.btnadd);
        phongDao= new PhongDao(this);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(them.this,themphong.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        capnhaLV();
    }
    public void xoaPhong(String maPhong){
        phongDao.delete(maPhong);
        capnhaLV();
    }
    public  void capnhaLV(){
        phongs= (ArrayList<Phong>) phongDao.getLopALL();
        adpter = new PhongAdapter(this,phongs);
        lvNV.setAdapter(adpter);
    }
}
