package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Start extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.start);
            Thread bamgio=new Thread(){
                public void run()
                {
                    try {
                        sleep(200);
                    } catch (Exception e) {

                    }
                    finally
                    {
                        Intent i =new Intent(getBaseContext(), Login.class);
                        startActivity(i);
                    }
                }
            };
            bamgio.start();
        }
        protected void onPause(){
            super.onPause();
            finish();
        }
    }
