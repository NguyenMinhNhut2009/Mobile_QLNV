package com.example.myapplication.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Model.Phong;
import com.example.myapplication.R;
import com.example.myapplication.them;
import com.example.myapplication.themphong;

import java.util.ArrayList;

public class PhongAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Phong> phongs;
    TextView TvmaPhong, TvtenPhong;
    public PhongAdapter(@NonNull Context context,ArrayList<Phong> phongs){
        super(context,0,phongs);
        this.context=context;
        this.phongs=phongs;

    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View l= convertView;
        if(l==null){
            l= LayoutInflater.from(context).inflate(R.layout.list_phong,parent,false);
        }
        final Phong phong=phongs.get(position);
        if(phong!=null){
            TvmaPhong=(TextView) l.findViewById(R.id.txtmaPhong);
            TvtenPhong=(TextView) l.findViewById(R.id.txttenPhong);

            TvmaPhong.setText(phong.maPhong);
            TvtenPhong.setText(phong.tenPhong);
        }

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc muốn xóa!!! ");
                builder.setCancelable(false);

                builder.setPositiveButton("Không ",(dialog,i)->{
                    Toast.makeText(context, "Xóa không thành công ", Toast.LENGTH_SHORT).show();
                });
                builder.setNegativeButton("Có ",(dialog,i)->{
                    Toast.makeText(context, "Bạn vừa xóa dòng "+(position+1), Toast.LENGTH_SHORT).show();
                    ((them)context).xoaPhong(phong.maPhong);
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
        });
        l.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent= new Intent(context, themphong.class);
                Bundle bundle= new Bundle();

                bundle.putString("maPhong",phong.maPhong);
                bundle.putString("tenPhong",phong.tenPhong);

                intent.putExtra("Phong",bundle);
                ((them)context).startActivity(intent);
                    return false;
            }
        });

        return l;
    }


}
