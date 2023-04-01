package com.example.baseapp;


// ctrl + alt + o  : 불필요하게 임포트된 문장  & Alt + Enter : Button과 관련된 클래스가 자동으로 import문에 추가
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button button1;
    CheckBox check1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"버튼 눌림",
                        Toast.LENGTH_SHORT).show();

            }
        });

     }
}