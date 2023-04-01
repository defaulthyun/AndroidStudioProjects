package com.example.ch8_practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button fileprocess_basic, rawfile, sdread, sdcreate, fileaccess;

        fileprocess_basic = (Button) findViewById(R.id.btn1);

        fileprocess_basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), FileProcess_basic.class);
                startActivity(intent1);

            }
        });

        rawfile = (Button) findViewById(R.id.btn2);

        rawfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), RawFile.class);
                startActivity(intent2);
            }
        });

        sdread = (Button) findViewById(R.id.btn3);

        sdread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), SDcard_Read.class);
                startActivity(intent3);
            }
        });

        sdcreate = (Button) findViewById(R.id.btn4);

        sdcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getApplicationContext(), SDcard_create.class);
                startActivity(intent4);
            }
        });

        fileaccess = (Button) findViewById(R.id.btn5);

        fileaccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(getApplicationContext(), FileAccess.class);
                startActivity(intent5);
            }
        });

    }
}