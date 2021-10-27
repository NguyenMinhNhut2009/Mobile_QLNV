package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.InsetDialogOnTouchListener;

public class Login  extends AppCompatActivity {
    String tk, mk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnRes = findViewById(R.id.btnRes);
        Button btnLogin = findViewById(R.id.btnLogin);
        final EditText txtUser = findViewById(R.id.txtUser);
        final EditText txtPassword = findViewById(R.id.txtPassword);
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tk= txtUser.getText().toString();
                mk= txtPassword.getText().toString();
                if(txtUser.getText().length() !=0 && txtPassword.getText().length()!=0){
                    if(txtUser.getText().toString().equals(tk) && txtPassword.getText().toString().equals(mk)){
                        txtUser.setText(tk);
                        txtPassword.setText(mk);
                        Toast.makeText(Login.this,"Bạn đăng ký thành công",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Login.this,"Bạn đăng ký thất bại",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUser.getText().length() !=0 && txtPassword.getText().length() !=0){
                    if (txtUser.getText().toString().equals("admin") &&txtPassword.getText().toString().equals("admin")){
                        Toast.makeText(Login.this,"Bạn đã đăng nhập thành công  ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    }else if(txtUser.getText().toString().equals(tk) && txtPassword.getText().toString().equals(mk)){
                        Toast.makeText(Login.this,"Bạn đã đăng nhập thành công  ",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this,"Bạn đăng nhập không thành công  ",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this,"Bạn đăng nhập thất bai",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}
