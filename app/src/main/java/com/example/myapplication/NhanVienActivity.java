package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Database.NhanVienDao;
import com.example.myapplication.Model.NhanVien;

import java.util.List;

public class NhanVienActivity extends AppCompatActivity {
    EditText edtmaNv, edttenNV,edtGT, edtSdt;
    Button btnthem, btnXoa, btnSua;
    Spinner spinner;
    String spn;
    NhanVien nhanVien;
    NhanVienDao nhanVienDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        edtmaNv=findViewById(R.id.edtmaNv);
        edttenNV=findViewById(R.id.edttenNv);
        btnthem= findViewById(R.id.btnThemNv);
        btnXoa= findViewById(R.id.btnXoaTang);
        btnSua=findViewById(R.id.btnSua);
        spinner=findViewById(R.id.spinner);
        edtGT=findViewById(R.id.adtGT);
        edtSdt=findViewById(R.id.edtSdt);

        nhanVienDao= new NhanVienDao(this);
        List<String> list=nhanVienDao.getNhanvientenPhong();
        ArrayAdapter arrayAdapter= new ArrayAdapter(NhanVienActivity.this,R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                spn = adapterView.getItemAtPosition(position).toString();
            }

          @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtmaNv.setText("");
                edttenNV.setText("");
                edtGT.setText("");
                edtSdt.setText("");
                edtmaNv.requestFocus();
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV= edtmaNv.getText().toString();
                String tenNV= edttenNV.getText().toString();
                String gioitinh= edtGT.getText().toString();
                String tenPhong= spn;
                String sdt=edtSdt.getText().toString();

                nhanVien = new NhanVien(maNV,tenNV,gioitinh,tenPhong,sdt);

                if(edtmaNv.getText().length()!=0 && edttenNV.getText().length()!=0 && edtGT.getText().length()!=0){
                    long kq= nhanVienDao.insert(nhanVien);
                    if(kq==-1){
                        Toast.makeText(NhanVienActivity.this , "Thêm nhân viên thất bại ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NhanVienActivity.this, "Thêm nhân viên thành công ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(NhanVienActivity.this,"Bạn phải nhập đủ thông tin ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maNV= edtmaNv.getText().toString();
                String tenNV= edttenNV.getText().toString();
                String gioitinh= edtGT.getText().toString();
                String tenPhong= spn;
                String sdt=edtSdt.getText().toString();

                nhanVien = new NhanVien(maNV,tenNV,gioitinh,tenPhong,sdt);

                if(edtmaNv.getText().length()!=0 && edttenNV.getText().length()!=0 && edtGT.getText().length()!=0){
                    long kq= nhanVienDao.insert(nhanVien);
                    if(kq==-1){
                        Toast.makeText(NhanVienActivity.this , "Sửa  nhân viên thất bại ", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(NhanVienActivity.this, "Sửa nhân viên thành công ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(NhanVienActivity.this,"Bạn phải nhập đủ thông tin ",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent intent= getIntent();
        Bundle bundle= intent.getBundleExtra("NhanVien");

        if(bundle!=null){
            String maNv= bundle.getString("maNV","");
            edttenNV.setText(bundle.getString("tenNv"," "));
            edtmaNv.setText(bundle.getString("maNV",""));
            edtGT.setText(bundle.getString("gioitinh"," "));
            edtSdt.setText(bundle.getString("sdt"," "));
        }
    }
}