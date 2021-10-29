package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.NhanVien;
import com.example.myapplication.NhanVienActivity;
import com.example.myapplication.R;
import com.example.myapplication.ThemNV;
import com.example.myapplication.them;
import com.example.myapplication.themphong;

import java.util.ArrayList;

public class NhanVienAdpter extends ArrayAdapter {
    Context context;
    ArrayList<NhanVien> nhanViens;
    TextView MaNV, TenNV, TenPhong, Sdt;
    ImageView imageGt, imageCall, imagesms;

    public NhanVienAdpter(@NonNull Context context, ArrayList<NhanVien> nhanViens) {
        super(context, 0, nhanViens);
        this.context = context;
        this.nhanViens = nhanViens;
    }

    @NonNull
    @Override

    public View getView(final int position, @Nullable View converView, @Nullable ViewGroup parent) {
        View nv = converView;
        if (nv == null) {
            nv = LayoutInflater.from(context).inflate(R.layout.list_nv, parent, false);
        }
        final NhanVien s = nhanViens.get(position);
        if (s != null) {
            TenPhong = (TextView) nv.findViewById(R.id.txttenPhong);
            MaNV = (TextView) nv.findViewById(R.id.txtmaNV);
            TenNV = (TextView) nv.findViewById(R.id.txtTen);
            Sdt = (TextView) nv.findViewById(R.id.txtsdt);
            imageCall = (ImageView) nv.findViewById(R.id.imagecall);
            imagesms = (ImageView) nv.findViewById(R.id.imagesms);
            imageGt = (ImageView) nv.findViewById(R.id.imageGT);
            TenPhong.setText(s.tenPhong);
            MaNV.setText(s.maNV);
            TenNV.setText(s.tenNV);

            if (s.gioitinh.equals("nu")) {
                imageGt.setImageResource(R.drawable.nu);
            } else {
                imageGt.setImageResource(R.drawable.nam);
            }
        }
            Sdt.setText(s.sdt);
            nv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Bạn có chắc muốn xóa !!!");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Toast.makeText(context, "Xóa không thành công ", Toast.LENGTH_LONG).show();
                        }
                    });
                    builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "Bạn vừa xóa dòng " + (position + 1), Toast.LENGTH_LONG).show();
                            ((ThemNV) context).xoaNV(s.maNV);
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                };
            });
            nv.setOnLongClickListener(new View.OnLongClickListener(){
               @Override
               public boolean onLongClick(View v){
                   Intent intent = new Intent(context, NhanVienActivity.class);

                   Bundle bundle = new Bundle();
                   bundle.putString("tenlop",s.tenPhong);
                   bundle.putString("maNv",s.maNV);
                   bundle.putString("TenNV",s.tenNV);
                   bundle.putString("gioitinh",s.gioitinh);
                   bundle.putString("sdt",s.sdt);

                   intent.putExtra("NhanVien", bundle);
                   ((ThemNV)context).startActivity(intent);
                   return  false;
               }
            });
        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo = Sdt.getText().toString();
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    ((ThemNV)context).startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));

                }else {
                    Toast.makeText(context, "Thực hiện cuộc gọi không thành công", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Vui lòng kiểm tra lại số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imagesms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonesms = Sdt.getText().toString();
                if(!TextUtils.isEmpty(phonesms)) {
                    String dialsms = "smsto:" + phonesms;
                    ((ThemNV)context).startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(dialsms)));
                }else {
                    Toast.makeText(context, "Thực hiện tin nhắn không thành công", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Vui lòng kiểm tra lại số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
            return nv;
        }
}

