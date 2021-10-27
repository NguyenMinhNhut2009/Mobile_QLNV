package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnthem,btnqlnv,btnfinish,btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnthem = findViewById(R.id.btnadd);
        btnqlnv = findViewById(R.id.btnQLNV);
        btnfinish= findViewById(R.id.btnfinish);
        btnlogout= findViewById(R.id.btnLogout);

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this, them.class);
                startActivity(i);
            }
        });
        btnqlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ThemNV.class);
                startActivity(i);
            }
        });
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có chắc muốn thoát ứng dụng ");
                builder.setCancelable(false);
                builder.setPositiveButton("Không ",(dialog,i)->{
                    Toast.makeText(MainActivity.this, "Thoát không thành công ", Toast.LENGTH_SHORT).show();

                });

                builder.setNegativeButton("Có ",(dialog,which)->{
                    Toast.makeText(MainActivity.this, "Thoát thành công ", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Intent.ACTION_MAIN);
                    i.addCategory(Intent.CATEGORY_HOME);
                    startActivity(i);
                    finish();
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có chác muốn đăng xuât ");
                builder.setCancelable(false);

                builder.setPositiveButton("Không ",(dialog,i)->{
                   Toast.makeText(MainActivity.this,"Dăng xuất không thành công ",Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("Có ",(dialog,which)->{
                   Toast.makeText(MainActivity.this," Đắng xuất thành công ",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);
                    });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });


    }
}