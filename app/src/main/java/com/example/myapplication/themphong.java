package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Database.PhongDao;
import com.example.myapplication.Model.Phong;

public class themphong extends AppCompatActivity {
    EditText edtmaPhong, edttenPhong;
    Button btnthemlop, btnxoa,btnsua;
    PhongDao phongDao;
    Phong phong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themphong);
        edtmaPhong= findViewById(R.id.edtmaPhong);
        edttenPhong=findViewById(R.id.edttenPhong);
        btnthemlop= findViewById(R.id.btnadd);
        btnxoa= findViewById(R.id.btnTrang);
        btnsua=findViewById(R.id.btnsua);

        phongDao = new PhongDao(this);
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmaPhong.setText("");
                edttenPhong.setText("");
                edtmaPhong.requestFocus();
            }
        });

        btnthemlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maPhong= edtmaPhong.getText().toString();
                String tenPhong= edttenPhong.getText().toString();
                phong  = new Phong(maPhong,tenPhong);

                if(edtmaPhong.getText().length() !=0 && edttenPhong.getText().length()!=0){
                    long kq = phongDao.insert(phong);
                    if(kq==-1){
                        Toast.makeText(themphong.this, "Thêm phòng thất bai ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(themphong.this, "Thêm phòng thành công ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(themphong.this,"Bạn phải điền đầy đủ thông tin ",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maPhong= edtmaPhong.getText().toString();
                String tenPhong= edttenPhong.getText().toString();
                phong= new Phong(maPhong,tenPhong);
                if(edtmaPhong.getText().length() !=0 && edttenPhong.getText().length()!=0){
                    long kq = phongDao.update(phong);
                    if(kq==-1){
                        Toast.makeText(themphong.this, "Thêm phòng thất bai ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(themphong.this, "Thêm phòng thành công ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(themphong.this,"Bạn phải điền đầy đủ thông tin ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Phong");
        if (bundle!=null){
            String maPhong = bundle.getString("maPhong","");
            edtmaPhong.setText(bundle.getString("maPhong",""));
            edttenPhong.setText(bundle.getString("tenPhong",""));
        }
    }
}